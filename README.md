# API
In this project I have use two api calls with 3 java files 

1. openweathermap api -  The user input has the following options
	command of 'weather:' and cityName if city name only contains one word
	weather: underscore between city names if city names contian two or more words EX: Los_angeles
	weather: cityName, full state name
	weather: cityName, countryname(full name or abbreviation)
OUTPUT: After the chatbot receives the following commands, it returns the temprature of the City, Temp maximum and Temp minimum

If user inputs incorrect command or location name, the program displays error message 

2.Finnhub stock api - This api is gets stock market news and informations about current stock market
	Command of 'stock' followed by the company ticker symbol. 
	EX: stock amzn ----- calls stock of amazon api
OUTPUT: After the chatbot receives the following commands, it returns the following responses
	company name, market capitalization , share outstanding and comapny url

If user inputs incorrect command or company ticker symbol, the program displays error message 


I used Gson library to parse my json obejct and pircbot library.
so the program needs those two jar files to run. I have attached both gson and pircbot jar file in my project folder.
