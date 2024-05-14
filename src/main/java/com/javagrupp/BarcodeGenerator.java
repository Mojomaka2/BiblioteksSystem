package com.javagrupp;

import java.util.Random;

public class BarcodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 8; // Barcode längd

    public static String generateBarcode() {
        Random random = new Random();
        StringBuilder barcode = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char character = CHARACTERS.charAt(index);
            barcode.append(character);
        }

        return barcode.toString();
    }

    public static void main(String[] args) {
        String barcode = generateBarcode();
        System.out.println("Slumpmässig barcode: " + barcode);
    }
}
