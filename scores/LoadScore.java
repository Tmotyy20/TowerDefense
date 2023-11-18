package scores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadScore {

    public static void CreateFile() {
        File txtFile = new File("res/scoreboard.txt");
        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteToFile(int score, String playerName) {
        File txtFile = new File("res/scoreboard.txt");
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(txtFile, true));
            pw.println(playerName + " " + score); 
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> ReadFromFile() {
        ArrayList<Integer> scores = new ArrayList<>(); 
        File txtFile = new File("res/scoreboard.txt");
        try {
            Scanner sc = new Scanner(txtFile);
            while (sc.hasNextInt()) {
                scores.add(sc.nextInt()); 
            }
            sc.close();
            return scores; 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // public static int[][] GetLevelScore(String name) {
    // File lvlScore = new File("res/" + name + ".txt");

    // if (lvlScore.exists()) {
    // ArrayList<Integer> list = ReadFromFile(lvlScore;
    // return ;

    // } else {
    // System.out.println("File: " + name + " does not exists! ");
    // return null;
    // }

    // }
}
