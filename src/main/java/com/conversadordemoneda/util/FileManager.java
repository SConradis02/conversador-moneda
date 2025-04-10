package com.conversadordemoneda.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileManager {
    private static final String HISTORY_DIR = "historial";
    private static final String FILE_NAME = "conversiones.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    static {
        try {
            Files.createDirectories(Paths.get(HISTORY_DIR));
        } catch (IOException e) {
            System.err.println("⚠️ Error al crear directorio de historial: " + e.getMessage());
        }
    }

    public static void saveConversion(String fromCurrency, String toCurrency, 
                                    double amount, double convertedAmount) {
        Path filePath = Paths.get(HISTORY_DIR, FILE_NAME);
        String record = String.format("[%s] %.2f %s → %.2f %s%n",
                LocalDateTime.now().format(DATE_FORMAT),
                amount,
                fromCurrency,
                convertedAmount,
                toCurrency);

        try {
            Files.write(filePath, record.getBytes(), java.nio.file.StandardOpenOption.CREATE, 
                      java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar en historial: " + e.getMessage());
        }
    }

    public static void showHistory() {
        Path filePath = Paths.get(HISTORY_DIR, FILE_NAME);
        
        try {
            if (Files.exists(filePath)) {
                System.out.println("\n📜 Historial de Conversiones:");
                List<String> lines = Files.readAllLines(filePath);
                lines.forEach(System.out::println);
            } else {
                System.out.println("\nNo hay historial disponible aún.");
            }
        } catch (IOException e) {
            System.err.println("⚠️ Error al leer historial: " + e.getMessage());
        }
    }

    public static void clearHistory() {
        try {
            Files.deleteIfExists(Paths.get(HISTORY_DIR, FILE_NAME));
            System.out.println("\n♻️ Historial borrado exitosamente");
        } catch (IOException e) {
            System.err.println("⚠️ Error al borrar historial: " + e.getMessage());
        }
    }
}
