package com.conversadordemoneda;

public class FileManager {
    public static void saveConversion(String base, String target, double amount, double converted) {
        System.out.println("✅ Conversión guardada: " + amount + " " + base + " → " + converted + " " + target);
    }
}