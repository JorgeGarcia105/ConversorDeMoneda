package conversorMoneda;

import com.google.gson.JsonObject;

public class CurrencyConverter {

    // Método para convertir la cantidad de una moneda a otra
    public double convert(double amount, String fromCurrency, String toCurrency, JsonObject rates) {
        // Obtener las tasas de cambio
        double fromRate = rates.get("conversion_rates").getAsJsonObject().get(fromCurrency).getAsDouble();
        double toRate = rates.get("conversion_rates").getAsJsonObject().get(toCurrency).getAsDouble();

        // Realizar la conversión
        return (amount / fromRate) * toRate;
    }
}