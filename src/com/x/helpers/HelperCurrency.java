package com.x.helpers;

import com.x.models.Currency;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class  HelperCurrency{
    private static final List<Currency> values=new ArrayList<>();

    public HelperCurrency(){
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

    public static void loadData(){
        //String csvFile = Paths.get( "/src/com/x/data/currency-options.csv").toAbsolutePath().toString();
        String csvFile = "src/com/x/data/currency-options.csv";
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
