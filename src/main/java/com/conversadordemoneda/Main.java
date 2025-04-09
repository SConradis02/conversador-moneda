package com.conversadordemoneda;

import java.util.Scanner;

public class Main{
  public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String PINK_BRIGHT = "\033[1;95m";
    public static final String CYAN_BRIGHT = "\033[1;96m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
   
        System.out.println(PURPLE_BOLD + "╔════════════════════════════════════════╗" + RESET);
        System.out.println(PINK_BRIGHT + "   💜💖 CONVERSOR DE MONEDAS GLAM 💖💜   " + RESET);
        System.out.println(PURPLE_BOLD + "╠════════════════════════════════════════╣" + RESET);
        System.out.println(CYAN_BRIGHT + "  Monedas disponibles: USD | EUR | MXN  " + RESET);
        System.out.println(PURPLE_BOLD + "╚════════════════════════════════════════╝" + RESET);

        System.out.print(PINK_BRIGHT + "\n✨ Moneda origen (ej: USD): " + RESET);
        String base = scanner.nextLine().toUpperCase();

        System.out.print(PINK_BRIGHT + "✨ Moneda destino (ej: EUR): " + RESET);
        String target = scanner.nextLine().toUpperCase();

        System.out.print(PINK_BRIGHT + "✨ Cantidad a convertir: " + RESET);
        double amount = scanner.nextDouble();

        try {
            double rate = ExchangeRateAPI.getExchangeRate(base, target);
            double converted = amount * rate;

      System.out.println(PURPLE_BOLD + "\n╔════════════════════════════════════════╗" + RESET);
            System.out.printf(PINK_BRIGHT + "  🦄 %.2f %s = " + CYAN_BRIGHT + "%.2f %s\n" + RESET, 
                            amount, base, converted, target);
            System.out.println(PURPLE_BOLD + "╚════════════════════════════════════════╝" + RESET);
            
            FileManager.saveConversion(base, target, amount, converted);
        } catch (Exception e) {
            System.err.println(PURPLE_BOLD + "❌ Error: " + e.getMessage() + RESET);
        } finally {
            scanner.close();
        }
    }
}
