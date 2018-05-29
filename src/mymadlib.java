//Name: Erika Vargas
//email: erika.vargas@bellevuecollege.edu

import java.io.*;
import java.util.Scanner;

public class mymadlib {
    public static void main(String[] args) throws FileNotFoundException {
        giveIntro();
        Scanner consoleScanner = new Scanner(System.in);
        Scanner inputFile = readingFile(consoleScanner);
        String outputFilename = outputFileName(consoleScanner);
        PrintStream output = new PrintStream(new File(outputFilename));
        fillingSpaces(consoleScanner, inputFile, output);
        results(outputFilename);
    }

    private static String outputFileName(Scanner consoleScanner) {
        System.out.println("Output file name:");
        return consoleScanner.nextLine();
    }

    //this method helps detect if the file exists and can be read.
    public static Scanner readingFile(Scanner console) throws FileNotFoundException {
        System.out.print("input file name:");
        File f = new File(console.nextLine());
        while (!f.exists()) {
            System.out.println("File not found. Try again:");
            System.out.print("input file name:");
            f = new File(console.nextLine());
        }
        return new Scanner(f);
    }


    public static void giveIntro() {
        System.out.println("Welcome to the game of Mad Libs.\n" +
                "I will ask you to provide various words\n" +
                "and phrases to fill in a story.");

    }

    public static void results(String fileName) throws FileNotFoundException {
        System.out.println("Your mad-lib has been created!\n" +
                "you can access your story in your C:/directory\n");
        Scanner output = new Scanner(new File(fileName));
        int countLines = 0;
        int countWords = 0;
        while (output.hasNext()) {
            String line = output.nextLine();
            Scanner lineScan = new Scanner(line);
            countLines++;
            while (lineScan.hasNext()) {
                String word = lineScan.next();
                countWords++;
            }

        }

        System.out.println("Your story made out " + countLines + " lines with " + countWords + " words.");


    }

    public static void fillingSpaces(Scanner console, Scanner inputFile, PrintStream output) {
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            Scanner lineScan = new Scanner(line);
            while (lineScan.hasNext()) {
                String nextWord = lineScan.next();
                if (nextWord.startsWith("<") && nextWord.endsWith(">")) {
                    String placeholder = nextWord.substring(1, nextWord.length() - 1);
                    placeholder = placeholder.replace('-', ' ');

                    System.out.println("Please type a " + placeholder);

                    nextWord = console.nextLine();
                }
                output.print(nextWord + " ");
            }
            output.println();
        }


    }
}