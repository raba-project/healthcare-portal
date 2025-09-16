package com.healthcare.ui;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }
}
