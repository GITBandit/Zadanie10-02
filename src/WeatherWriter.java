import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherWriter {

    WeatherReader weatherReader = new WeatherReader();
    WeatherInfo weatherInfo;

    List<String> cities = weatherReader.getCities();
    List<WeatherInfo> weatherInfoList = new ArrayList<>();

    public void printCities(){
        System.out.println(cities.get(0));
    }

    public void saveWeather() {

        try (FileWriter fileWriter = new FileWriter("citiesWeather.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String x : cities) {
                //x ==
                try {
                    WeatherApi api = new WeatherApi();
                    int temperature = api.getTemperature(x);
                    String description = api.getDescription(x);
                    // saving in a file tests - to be removed
                    bufferedWriter.write("Pogoda w mieście " + x + " : " + description );
                    bufferedWriter.newLine();
                    bufferedWriter.write("Aktualna temperatura to : " + temperature);
                    bufferedWriter.newLine();
                    bufferedWriter.write("-----------------------------------------------------------------");
                    bufferedWriter.newLine();
                    // console display
                    System.out.printf("Pogoda w mieście %s: %s\n", x, description);
                    System.out.printf("Aktualna temperatura: %d stopni\n", temperature);
                    // adding data to the list
                    weatherInfo = new WeatherInfo(x,description,temperature);
                    weatherInfoList.add(weatherInfo);
                } catch (IOException e) {
                    // saving in a file tests - to be removed
                    bufferedWriter.write("Niestety nie udało się pobrać informacji dla miasta " + x);
                    bufferedWriter.newLine();
                    bufferedWriter.write("-----------------------------------------------------------------");
                    bufferedWriter.newLine();
                    // console display
                    System.err.println("Nie udało się pobrać informacji dla miasta " + x);
                    // adding object when failed
                    //WeatherApi api = new WeatherApi();
                    weatherInfo = new WeatherInfo(x,"Nie udało się pobrać informacji dla tego miasta.",0);
                    weatherInfoList.add(weatherInfo);
                }
            }


        } catch (IOException e) {
            System.out.println("Nie udało się zapisać informacji");
        }
    }

    public void printWeatherInfoList(){
        System.out.println(weatherInfoList.size());
        for (WeatherInfo s : weatherInfoList) {
            System.out.println(s.toString());
        }
    }
}
