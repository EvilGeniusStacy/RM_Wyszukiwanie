package RM_Wyszukiwanie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wyszkukiwanie {
    public static void main(String[] args) {
    
        System.out.print("Co chcesz wyszukać? ");
        String input = System.console().readLine();

        File folder = new File(".");
        File[] pliki = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        for (File plik : pliki) {
            try (BufferedReader reader = new BufferedReader(new FileReader(plik))) {
                String linia;
                int numerLinii = 0;

                while ((linia = reader.readLine()) != null) {
                    numerLinii++;

                    if (pasuje(linia, input)) {
                        System.out.println("Plik: " + plik.getName() +
                                ", Linia: " + numerLinii +
                                ", Fragment tekstu: " + linia);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean pasuje(String linia, String input) {
        input = input.replace("*", ".*").replace("?", ".");

        Pattern pattern = Pattern.compile(input);
        Matcher matcher = pattern.matcher(linia);
        return matcher.find();
    }
}