package com.x.main;

import com.x.helpers.Api;
import com.x.helpers.HelperCurrency;
import com.x.models.PairConversion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HelperCurrency helper = new HelperCurrency();
        System.out.println("***conversor de divisas***");

        String menu = """
                \nSeleccione una opción:
                1. Convertir divisa
                2. Buscar divisa por país
                3. Salir
                """;

        int option;

        do{
            System.out.println(menu);

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                switch (option) {
                    case 1:
                        convertCurrencies(scanner);
                        break;
                    case 2:
                        searchByCountry(scanner,helper);
                        break;
                    case 3:
                        System.out.println("¡Hasta luego!");
                        scanner.close();
                        System.exit(0); // Terminar el programa
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            }catch (Exception e){
                scanner.next();
                System.out.println(e.getMessage());
                option = -1;
            }
        }while(option!=0);

    }

    public static boolean breaker(String s){
        return s.equalsIgnoreCase("q") ? true : false;
    }

    private static void convertCurrencies(Scanner scanner) {
        String subMenu = """
                \nConvertir divisas
                Multiples divisas disponibles: USD, EUR, PEN,...
                Para salir, presiona 'Q'.
                """;

        while(true){
            System.out.println(subMenu);

            try{
                System.out.print("Divisa de origen (ejemplo, USD): ");
                String sourceCurrency = scanner.nextLine().toUpperCase();
                if (breaker(sourceCurrency)) break;

                System.out.print("Divisa de destino (por ejemplo, PEN): ");
                String targetCurrency = scanner.nextLine().toUpperCase();
                if (breaker(targetCurrency)) break;

                System.out.print("Ingrese cantidad (por ejemplo, 7.50): ");
                String input = scanner.nextLine().toUpperCase();
                if (breaker(input)) break;

                double amount = Double.parseDouble(input);

                PairConversion conversion = Api.pairConversion(amount,sourceCurrency,targetCurrency);

                double result = conversion.conversion_result();

                System.out.printf("\n%.2f [ %s ] = %.2f [ %s ]\n",amount,sourceCurrency,result,targetCurrency);

            }catch (Exception e){
                System.out.printf(e.getMessage());
            }finally{
                System.out.println();
            }
        }

    }

    private static void searchByCountry(Scanner scanner,HelperCurrency op) {

        while(true){
            System.out.println("\nIngrese nombre de País");
            System.out.println("Para salir, presiona 'Q'.");
            String countryName = scanner.nextLine();

            if (breaker(countryName)) break;

            String response = op.searchByCountry(countryName);
            System.out.println(response);
        }
    }
}