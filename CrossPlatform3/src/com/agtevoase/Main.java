package agtevoase;

import java.io.*;

import java.util.Scanner;

public class Main {

    private static final String directory = "C:\\Users\\andro\\IdeaProjects\\CrossPlatform3\\files\\";
    //"C:\\Users\\andro\\Desktop\\nulp\\labs\\CP\\cp3\\";

    public static void main(String[] args) throws IOException {
        System.out.println("Part 1.");
        Utils.findEmailAddressesAndEditFile(directory + "en.txt",directory + "en.out.txt");
        Utils.findEmailAddressesAndEditFile(directory + "ua.txt",directory + "ua.out.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPart 2.\nWhat length word need to be removed? ");
        int len = scanner.nextInt();
        System.out.println("Enter text from console? (Y/N)");
        String answer = scanner.next();
        scanner.nextLine();

        if (answer.equals("Y")) {
            System.out.println("Enter your text:");
            String text = scanner.nextLine();
            System.out.println("\nEdited text:\n" + Utils.removeConsonantsOfGivenLength(text, len));
        } else {
            Utils.removeStuffAndEditFile(directory + "en.txt",directory + "en.regex.out.txt", len);
        }
    }

}

