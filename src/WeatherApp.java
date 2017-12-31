import java.io.IOException;

public class WeatherApp {
    public static void main(String[] args) {
        String city = "Wrocław";
        try {
            WeatherApi api = new WeatherApi();
            int temperature = api.getTemperature(city);
            String description = api.getDescription(city);
            System.out.printf("Pogoda w mieście %s: %s\n", city, description);
            System.out.printf("Aktualna temperatura: %d stopni\n", temperature);
        } catch (IOException e) {
            System.err.println("Nie udało się pobrać informacji dla miasta " + city);
        }

        WeatherReader weatherReader = new WeatherReader();
        WeatherWriter weatherWriter = new WeatherWriter();

        weatherReader.readCityList();
//        weatherReader.printTest();
        weatherWriter.printCities();
        weatherWriter.saveWeather();

        System.out.println("testowanie drukowania listy");
        weatherWriter.printWeatherInfoList();
    }
}