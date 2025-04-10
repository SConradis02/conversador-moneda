package com.conversadordemoneda;

public class ExchangeRateAPI {
    public static double getExchangeRate(String base, String target) {
        if (base.equals("USD") && target.equals("EUR")) return 0.93;
        if (base.equals("USD") && target.equals("MXN")) return 17.0;
        if (base.equals("EUR") && target.equals("USD")) return 1.07;
        if (base.equals("EUR") && target.equals("MXN")) return 18.0;
        if (base.equals("MXN") && target.equals("USD")) return 0.059;
        if (base.equals("MXN") && target.equals("EUR")) return 0.056;
        return 1.0;
    }
}