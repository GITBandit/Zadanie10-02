import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherReader {

    private static List<String> cityList = new ArrayList<>();

    public void readCityList() {
        try (
            FileReader fileReader = new FileReader("cities.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)
        ){
            String read = null;
            while ((read = bufferedReader.readLine()) != null){
                cityList.add(read);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie znaleziono.");
        } catch (IOException e) {
            System.out.println("Nie udało się pobrać informacji.");;
        }
    }

    public List<String> getCities(){
        return cityList;
    }



}
