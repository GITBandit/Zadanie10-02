import java.io.IOException;

public class WeatherApp {
    public static void main(String[] args) {

        WeatherReader weatherReader = new WeatherReader();
        WeatherWriter weatherWriter = new WeatherWriter();

        weatherReader.readCityList();
        weatherWriter.saveWeather();

        // checking if the list is correct
        System.out.println("Dane z listy : ");
        weatherWriter.printWeatherInfoList();
    }
}