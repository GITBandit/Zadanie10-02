import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherWriter {

    private WeatherReader weatherReader = new WeatherReader();
    private WeatherInfo weatherInfo;

    private List<String> cities = weatherReader.getCities();
    private List<WeatherInfo> weatherInfoList = new ArrayList<>();


    public void saveWeather() {

        try (FileWriter fileWriter = new FileWriter("citiesWeather.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String x : cities) {
                // fixing inproper url for Poznań
                if (x.equals("Poznań")) {
                    x = "poznań-103088171";
                }
                try {
                    WeatherApi api = new WeatherApi();
                    int temperature = api.getTemperature(x);
                    String description = api.getDescription(x);
                    // saving in a file - CSV form
                    if(x.equals("poznań-103088171")){
                        x = "Poznań";
                    }
                    bufferedWriter.write( x + ";" + temperature + ";" + description );
                    bufferedWriter.newLine();
                    // console display
                    System.out.printf("Pogoda w mieście %s: %s\n", x, description);
                    System.out.printf("Aktualna temperatura: %d stopni\n", temperature);
                    System.out.println("-------------------------------------------------------------------------");
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
                    weatherInfo = new WeatherInfo(x,"Nie udało się pobrać informacji dla tego miasta.",0);
                    weatherInfoList.add(weatherInfo);
                }
            }


        } catch (IOException e) {
            System.out.println("Nie udało się zapisać informacji");
        }
    }

    public void printWeatherInfoList(){
        // checking if the list is correct
        for (WeatherInfo s : weatherInfoList) {
            System.out.println(s.toString());
        }
    }
}
