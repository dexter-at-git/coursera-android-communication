package vandy.mooc.model.aidl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vandy.mooc.model.aidl.WeatherData.Main;
import vandy.mooc.model.aidl.WeatherData.Sys;
import vandy.mooc.model.aidl.WeatherData.Weather;
import vandy.mooc.model.aidl.WeatherData.Wind;
import android.util.JsonReader;
import android.util.JsonToken;

/**
 * Parses the Json weather data returned from the Weather Services API
 * and returns a List of WeatherData objects that contain this data.
 */
public class WeatherDataJsonParser {
    /**
     * Used for logging purposes.
     */
    private final String TAG =
        this.getClass().getCanonicalName();

    /**
     * Parse the @a inputStream and convert it into a List of JsonWeather
     * objects.
     */
    public List<WeatherData> parseJsonStream(InputStream inputStream)
        throws IOException {
        // TODO +- you fill in here.
        // Create a JsonReader for the inputStream.
        try (JsonReader reader =
             new JsonReader(new InputStreamReader(inputStream,
                                                  "UTF-8"))) {
            // Log.d(TAG, "Parsing the results returned as an array");

            // Handle the array returned from the Weather Service.
            return parseJsonWeatherDataArray(reader);        
        }
    }

    /**
     * Parse a Json stream and convert it into a List of WeatherData
     * objects.
     */
    public List<WeatherData> parseJsonWeatherDataArray(JsonReader reader)
        throws IOException {
        // TODO +- you fill in here.
        reader.beginObject();
        try {
            List<WeatherData> weatherData = new ArrayList<WeatherData>();
            while (reader.hasNext()) 
            	weatherData.add(parseJsonWeatherData(reader));            
            return weatherData;
        } finally {
            reader.endObject();
        }
    }

    /**
     * Parse a Json stream and return a WeatherData object.
     */
    public WeatherData parseJsonWeatherData(JsonReader reader) 
        throws IOException {
    	WeatherData weatherData = new WeatherData();
    	
        // TODO +- you fill in here.
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {

                case WeatherData.weather_JSON:
                	List<Weather> weathers = parseWeathers(reader);
                	weatherData.setWeathers(weathers);
                    break;
                case WeatherData.cod_JSON:
                	weatherData.setCod(reader.nextLong());
                    break;
                case WeatherData.name_JSON:
                	weatherData.setName(reader.nextString());
                    break;
                case WeatherData.dt_JSON:
                	weatherData.setDate(reader.nextLong());
                    break;
                case WeatherData.message_JSON:
                	weatherData.setMessage(reader.nextString());
                    break;
                case WeatherData.main_JSON:
                	Main main = parseMain(reader);
                	weatherData.setMain(main);
                    break;
                case WeatherData.sys_JSON:
                	Sys sys = parseSys(reader);
                	weatherData.setSys(sys);
                    break;
                case WeatherData.wind_JSON:
                	Wind wind = parseWind(reader);
                	weatherData.setWind(wind);
                    break;
                default:
		    reader.skipValue();
                    // Log.d(TAG, "weird problem with " + name + " field");
                    break;
                }
            }
    	return weatherData;
    }
    
    /**
     * Parse a Json stream and return a List of Weather objects.
     */
    public List<Weather> parseWeathers(JsonReader reader) throws IOException {
        // TODO +- you fill in here.      
        reader.beginArray();
        try {
            List<Weather> weathers = new ArrayList<Weather>();
            while (reader.hasNext()) 
            	weathers.add(parseWeather(reader));            
            return weathers;
        } finally {
            reader.endArray();
        }
    }

    /**
     * Parse a Json stream and return a Weather object.
     */
    public Weather parseWeather(JsonReader reader) throws IOException {
        // TODO +- you fill in here.
        reader.beginObject();
        Weather weather = new Weather();
        try {
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                case Weather.icon_JSON:
                	weather.setIcon(reader.nextString());
                    break;
                case Weather.description_JSON:
                	weather.setDescription(reader.nextString());
                    break;
                case Weather.id_JSON:
                	weather.setId(reader.nextLong());
                    break;
                case Weather.main_JSON:
                	weather.setMain(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
                }
            } 
        } finally {
                reader.endObject();
        }
        return weather;
    }

    /**
     * Parse a Json stream and return a Main Object.
     */
    public Main parseMain(JsonReader reader) 
        throws IOException {
        // TODO +- you fill in here.
        reader.beginObject();
        Main main = new Main();
        try {
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                case Main.humidity_JSON:
                	main.setHumidity(reader.nextLong());
                    break;
                case Main.pressure_JSON:
                	main.setPressure(reader.nextDouble());
                    break;
                case Main.temp_JSON:
                	main.setTemp(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
                }
            } 
        } finally {
                reader.endObject();
        }
        return main;
    }

    /**
     * Parse a Json stream and return a Wind Object.
     */
    public Wind parseWind(JsonReader reader) throws IOException {
        // TODO +- you fill in here.
        reader.beginObject();
        Wind wind = new Wind();
        try {
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                case Wind.deg_JSON:
                	wind.setDeg(reader.nextDouble());
                    break;
                case Wind.speed_JSON:
                	wind.setSpeed(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
                }
            } 
        } finally {
                reader.endObject();
        }
        return wind;
    }

    /**
     * Parse a Json stream and return a Sys Object.
     */
    public Sys parseSys(JsonReader reader)
        throws IOException {
        // TODO +- you fill in here.
        reader.beginObject();
        Sys sys = new Sys();
        try {
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                case Sys.country_JSON:
                	sys.setCountry(reader.nextString());
                    break;
                case Sys.message_JSON:
                	sys.setMessage(reader.nextDouble());
                    break;
                case Sys.sunrise_JSON:
                	sys.setSunrise(reader.nextLong());
                    break;
                case Sys.sunset_JSON:
                	sys.setSunset(reader.nextLong());
                    break;
                default:
                    reader.skipValue();
                    break;
                }
            } 
        } finally {
                reader.endObject();
        }
        return sys;
    }
    
    /*
    {
     "coord":{"lon":37.62,"lat":55.75},
     "weather":[{"id":800,"main":"Clear","description":"Sky is Clear","icon":"01n"}],
     "base":"cmc stations",
     "main":{"temp":256.87,"pressure":1015,"humidity":77,"temp_min":254.15,"temp_max":258.15},
     "wind":{"speed":3,"deg":130},
     "clouds":{"all":0},
     "dt":1452024640,
     "sys":{"type":1,"id":7323,"message":0.0032,"country":"RU","sunrise":1451973447,"sunset":1451999571},
     "id":524901,
     "name":"Moscow",
     "cod":200
    } 
     */
}
