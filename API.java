
package Api;

import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;
import java.io.*;

public class API {

	// Creating an HTTPURLConnection

	private static HttpURLConnection connection;

	public static String requestWeather(String cityName) {

		String result = "";

		try {

			String key = "*****";

			// creating url object using openweathermap api url endpoint

			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + key);

			connection = (HttpURLConnection) url.openConnection();

			// Using http url connection object to create a GET Request

			connection.setRequestMethod("GET");

			connection.connect();

			// BufferReader to read the connection inputStream

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;

			// Convert BufferReader to String and store it in a result string variable

			while ((line = reader.readLine()) != null) {
				result += line;
			}

			reader.close();

		} catch (Exception e) {

			return "Location not found 😔. Please Enter a valid location name";
		}

		// closing connection

		finally {
			connection.disconnect();
		}

		return parseJson(result);// Calling parseJson function to parse the JSON response

	}

	private static String parseJson(String result) {

		// creating json object to parse string to java object(string)

		JsonObject object = JsonParser.parseString(result).getAsJsonObject();

		// saving cityname, temp, temp_min and temp_max from json object to java object

		String cityName = object.get("name").getAsString();

		JsonObject main = object.getAsJsonObject("main");

		double temp = main.get("temp").getAsDouble();

		double tempMin = main.get("temp_min").getAsDouble();

		double tempMax = main.get("temp_max").getAsDouble();

		// return parsed string

		return "The temperature in " + cityName + " is  " + (int) (temp * 9 / 5 - 459.67) + "˚F, with a high of 🌞 "
				+ (int) (tempMax * 9 / 5 - 459.67) + "˚F and a low of ❄" + (int) (tempMin * 9 / 5 - 459.67) + "˚F";

	}

	public static String requestStock(String company) {

		String key = "*********";

		String result = "";

		try {
			// creating url object using finnhub api url endpoint

			URL url = new URL("https://finnhub.io/api/v1/stock/profile2?symbol=" + company + "&token=" + key);

			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;

			// Convert BufferReader to String and storing it in a result variable

			while ((line = reader.readLine()) != null) {
				result += line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		return parseStock(result);

	}

	private static String parseStock(String result) {

		double marketCapital = 0.0, share = 0.0;

		String name = "", url = "";

		try {

			// parsing json object

			JsonObject object = JsonParser.parseString(result).getAsJsonObject();

			marketCapital = object.get("marketCapitalization").getAsDouble();

			share = object.get("shareOutstanding").getAsDouble();

			name = object.get("name").getAsString();

			url = object.get("weburl").getAsString();

		} catch (Exception e) {

			return "Stock company symbol not found 😔. Please enter valid ticker symbol";
		}

		// return parsed data

		return "The Market Capitalization  📈 of " + name + " is : $" + marketCapital + " , "
				+ "Number of outstanding shares are 📈  $" + share
				+ " , Here is the company website for more information 😊: " + url;

	}

}
