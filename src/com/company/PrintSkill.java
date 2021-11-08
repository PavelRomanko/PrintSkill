package com.company;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PrintSkill {

    protected static final String[] WordsEasy = {"Tomato", "Friend", "Excide", "Fabric", "Denote", "Naught", "Burman", "Potent", "Malaga", "Hacker", "Custom", "Hamper", "Civism", "Odible", "Rocket", "Guitar", "Swatch", "Burned", "Protect"};
    protected static final String[] WordsMedium = {"Etymology", "Subvention", "Leucopathy", "Undergroan", "Haematosis", "Interpoint", "Distringas", "Reexchange", "Volitional", "Serjeantcy", "Unpitousty", "Torpidness", "Culvertail", "Sapiential", "Funambulus", "Eliquation", "Ruffianous", "Eikosylene", "Adjectived", "Inbreaking"};
    protected static final String[] WordsHard = {"Galactometries", "Factionalising", "Eccentricities", "Dactylographer", "Cabinetworkers", "Abominableness", "Labiogressions", "Macroaggregate", "Nanotechnology", "Paddleboatings", "Quadriennially", "Easterlinesses", "Backwardations", "Xanthosiderite", "Zombifications", "Yieldabilities", "Wafflestompers", "Valedictorians", "Tachygraphical", "Karyoplasmatic"};

    public static String userPath;

    public static void presentProgram() {
        System.err.println("Welcome to PrintSkill! This program will help you improve your typing skills!\n");
    }

    public static int getResultsNumber() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(userPath));
        int i = 0;
        boolean isCorrect;
        do {
            isCorrect = true;
            try {
                scan.nextFloat();
                i = i + 1;
            } catch (NoSuchElementException e) {
                isCorrect = false;
            }
        } while (isCorrect);
        return i;
    }

    public static void updateUserInfo() {
        System.err.print("Enter username: ");
        Scanner in = new Scanner(System.in);
        String userName = in.nextLine();
        userPath = "users\\" + userName + ".txt";
        try {
            Scanner scan = new Scanner(new File(userPath));
            int size = getResultsNumber();
            float [] results = new float[size];
            int i, n;
            for(n = 0; n < size; n++) {
                results[n] = Float.parseFloat(scan.nextLine());
            }
            System.err.println("Hello, " + userName + ", here are your previous results: ");
            for (i = 0; i < 10; i++) {
                size = size - 1;
                if(size > -1)
                System.err.print("|" + results[size] + "| ");
            }
            System.err.println();
        } catch (FileNotFoundException e){
            System.err.println("Hello, " + userName + "!");
            System.err.println("TIP: Use this username next time to upload the results.");
        }
    }

    public static byte selectDifficulty() {
        boolean isCorrect;
        byte difficulty = 0;
        do {
            System.err.print("\nSelect difficulty (1 - Easy / 2 - Medium / 3 - Hard): ");
            Scanner sc_diff = new Scanner(System.in);
            isCorrect = true;
            try {
                difficulty = (byte) Integer.parseInt(sc_diff.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter the difficulty correctly!");
                isCorrect = false;
            }
            if ((difficulty < 1 || difficulty > 3) && isCorrect) {
                System.err.println("Enter the difficulty correctly!");
                isCorrect = false;
            }
        } while (!isCorrect);
        return difficulty;
    }

    public static void echoDifficulty(byte difficulty) {
        if (difficulty == 1)
            System.err.println("\nEasy difficulty was selected.");
        else if (difficulty == 2)
            System.err.println("\nMedium difficulty was selected.");
        else
            System.err.println("\nHard difficulty was selected.");
    }

    public static void outWords() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.err.println("\nYour words are ready. They will be withdrawn now.");
        TimeUnit.SECONDS.sleep(2);
        System.err.println("\nGet ready! You have 5 seconds!");
        TimeUnit.SECONDS.sleep(5);
    }

    public static void inputWords(byte difficulty) throws InterruptedException {
        boolean isPrecise;
        String TextIn;
        String[] Array = WordsEasy;
        if (difficulty == 2) {
            Array = WordsMedium;
        }
        else if (difficulty == 3) {
            Array = WordsHard;
        }
        Collections.shuffle(Arrays.asList(Array));
        for (int n = 0; n < 5; n++) {
            do {
                byte length = (byte)Array[n].length();
                System.err.print("\n===");
                for(int i = 0; i < length; i++) {
                    System.err.print("=");
                }
                System.err.println("===");
                System.err.println("   " + Array[n]);
                System.err.print("===");
                for(int i = 0; i < length; i++) {
                    System.err.print("=");
                }
                System.err.println("===");
                System.err.print("\nEnter the word that appeared on your screen: ");
                Scanner sc_text = new Scanner(System.in);
                TextIn = sc_text.nextLine();
                if (TextIn.equalsIgnoreCase(Array[n])) {
                    System.err.println("\nFine! You entered word correctly!");
                    isPrecise = true;
                } else {
                    System.err.println("\nEnter the word correctly, without errors and extra spaces. Penalty 3 seconds!\n");
                    TimeUnit.SECONDS.sleep(2);
                    isPrecise = false;
                }
            } while (!isPrecise);
        }
    }

    public static void calculateResults() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.err.print("Calculating results\n");
        TimeUnit.SECONDS.sleep(1);
        System.err.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.err.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.err.println(".");
    }

    public static void outResults(float result, byte difficulty) {
        String FastestHand = "Your rating: " + (char)34 + "The fastest hand in the Wild West" + (char)34 + ".";
        String Good = "Your rating: Good, but it could be better!";
        String Excellent = "Your rating: Excellent! Keep it up!";
        String Okay = "Your rating: Okay, but try your best next time!";
        String Satisfactory = "Your rating: Satisfactory. Practice more.";
        String Awful = "Your rating: Awful. You may have forgotten the location of the buttons on the keyboard.";
        if (difficulty == 2) {
            difficulty = (byte) (difficulty + 3);
        }
        else if (difficulty == 3) {
            difficulty = (byte) (difficulty + 7);
        }
        if (result <= (15 + difficulty)) {
            System.err.println(FastestHand);
        }
        else if (result < (21 + difficulty) && result > (16 + difficulty)) {
            System.err.println(Excellent);
        }
        else if (result < (27 + difficulty) && result > (22 + difficulty)) {
            System.err.println(Good);
        }
        else if (result < (33 + difficulty) && result > (28 + difficulty)) {
            System.err.println(Okay);
        }
        else if (result < (39 + difficulty) && result > (34 + difficulty)) {
            System.err.println(Satisfactory);
        }
        else {
            System.err.println(Awful);
        }
    }

    public static void pressAnyKey() {
        Scanner scan = new Scanner(System.in);
        System.err.print("Press any key to close...");
        scan.nextLine();
    }

    public static void main (String[] args) throws InterruptedException, IOException {
        byte difficulty;
        presentProgram();
        updateUserInfo();
        difficulty = selectDifficulty();
        echoDifficulty(difficulty);
        outWords();
        long startTime = System.currentTimeMillis();
        inputWords(difficulty);
        long endTime = System.currentTimeMillis();
        long time = (endTime - startTime);
        float result = (float)time/1000;
        System.err.println("\nTime spent: " + result + " seconds.");
        FileWriter writer = new FileWriter(userPath, true);
        writer.write(result + "\n");
        writer.close();
        calculateResults();
        outResults(result, difficulty);
        pressAnyKey();
    }
}
