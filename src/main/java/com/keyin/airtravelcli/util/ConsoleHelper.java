package com.keyin.airtravelcli.util;

import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner in = new Scanner(System.in);

    public static String prompt(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    public static long promptLong(String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine().trim();
            try { return Long.parseLong(s); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }

    public static void pause() {
        System.out.print("\nPress ENTER to continue...");
        in.nextLine();
    }
}
