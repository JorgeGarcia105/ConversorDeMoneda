import com.google.gson.JsonObject;
import conversorMoneda.APICliente;
import conversorMoneda.CurrencyConverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        APICliente apiClient = new APICliente();
        CurrencyConverter converter = new CurrencyConverter();

        try (Scanner scanner = new Scanner(System.in)) {
            // Obtener las tasas de cambio desde la API con USD como moneda base
            JsonObject rates = apiClient.getExchangeRates("USD");

            // Verificar el resultado de la API
            String result = rates.get("result").getAsString();
            if ("success".equals(result)) {
                System.out.println("Tasas de cambio obtenidas con éxito.");

                // Solicitar al usuario la cantidad y las monedas
                System.out.print("Ingrese la cantidad que desea convertir: ");
                double amount = scanner.nextDouble();

                System.out.print("Ingrese la moneda de origen (ejemplo: USD): ");
                String fromCurrency = scanner.next().toUpperCase();

                System.out.print("Ingrese la moneda de destino (ejemplo: COP): ");
                String toCurrency = scanner.next().toUpperCase();

                // Convertir la moneda
                double resultConversion = converter.convert(amount, fromCurrency, toCurrency, rates);
                System.out.printf("%.2f %s equivalen a %.2f %s%n", amount, fromCurrency, resultConversion, toCurrency);
            } else {
                System.out.println("Error en la solicitud: " + result);
            }

        } catch (Exception e) {
            //Error en la solicitud: java.net.UnknownHostException: v6.exchangerate-api.com
            System.out.println("Error en la solicitud: " + e.getMessage());
        }
    }
}
