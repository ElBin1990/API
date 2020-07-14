package Api;

import org.jibble.pircbot.*;

public class bot extends PircBot {

	public bot() {
		this.setName("Smart_Bot");
	}

	// sending command containing message to user

	public void commands(String channel, String sender, String login, String hostname, String msg) {

		sendMessage(channel, "weather: city_name,state or city_name,country_name");
		sendMessage(channel, "stock: company symbol(ticker symbol)");
	}

	// reading and sending a message from/to the channel

	public void onMessage(String channel, String sender, String login, String hostname, String message) {

		String temp, stock;
		String cityName = "", companyName = "";
		API api = new API();
		message = message.toLowerCase();

		// splits user message by space
		String[] words = message.split(" ");

		if (message.contains("hello") || message.contains("hi") || message.contains("hey")) {

			sendMessage(channel, "Hey✋ " + sender + "!😁	");
			sendMessage(channel, "What would you like to know today? Here are the commands you can use");

			// calling command method
			commands(channel, sender, login, hostname, message);

		}

		else if (message.contains("weather:")) {

			// if user message contains 'weather' split weather and city name by array index
			if (words[0].equals("weather:") && cityName != " ") {

				cityName = words[1];

				// replacing _ by + in order to combine cities names that contain more than one
				// word

				if (cityName.contains("_")) {
					cityName = cityName.replaceAll("_", "+");
				}

			}

			// calling requestweather by pass city name

			temp = api.requestWeather(cityName);

			// sending response to user

			sendMessage(channel, temp);
		}

		else if (message.contains("stock:")) {

			// if user message contains 'stock' next word will be company symbol

			if (words[0].equals("stock:") && companyName != " ") {
				companyName = words[1];

			}
			// calling requeststock by passing company symbol

			stock = api.requestStock(companyName);

			// sending response

			sendMessage(channel, stock);

		} else {
			sendMessage(channel, "Command not found 😔. Please use the correct commands below");
			commands(channel, sender, login, hostname, message);

		}

	}

}
