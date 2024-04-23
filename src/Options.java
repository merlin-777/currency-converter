import com.google.gson.Gson;
import com.x.models.Currency;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Options {
    private static final List<Currency> values=new ArrayList<>();

    public Options(){
        loadData();
    }

    public String searchByCountry(String countryName){
        String response="";
        for (Currency currency : values) {
            if (currency.country().toLowerCase().contains(countryName.toLowerCase())) {
                response+=currency.toString()+"'\n";
            }
        }
        return response;
    }


    public static void main(String[] args) {

        System.out.println("*** init ***");

        Options op = new Options();
        String r = op.searchByCountry("pe");
        System.out.println("r: "+r);
    }

    public void readJson(){
        String jsonFilePath = "currency-options.json";
        Gson gson = new Gson();
        try (Reader reader = new FileReader(jsonFilePath)) {
            Type mapType = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> currencyOpetionsList = gson.fromJson(reader, mapType);
            System.out.println("Currency Info:");
            currencyOpetionsList.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(){
        String csvFile = "currency-options.csv";
        String delimiter = ";";
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            if (scanner.hasNextLine()) {
                while (scanner.hasNextLine()) {
                    String[] currencyString = scanner.nextLine().split(delimiter);
                    Currency currency = new Currency(currencyString[0],currencyString[1],currencyString[2]);
                    values.add(currency);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
