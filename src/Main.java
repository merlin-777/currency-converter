import com.x.models.ExchangeRate;
import com.x.models.PairConversion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String x = """
                Local currency = 
                1   SOl
                3.5 USD
                """;
        System.out.println("        ***Currency converter***        ");
        try{
            System.out.println("Pair conversion");
            PairConversion response =Api.pairConversion(3.50,"PEN","USD") ;
            System.out.println("response "+response);


            System.out.println("Exchange rate");
            ExchangeRate response2=Api.exchangeRate("PEN");
            System.out.println("response2 "+response2);

        } catch (NumberFormatException e){
            System.out.println("Número no encontrado "+e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación.");
        }
    }
}
