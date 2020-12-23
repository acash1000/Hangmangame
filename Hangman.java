package com.company;

import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
   int points;
   int lives;
   int cash;

    public Hangman() {
        this.points = 0;
        this.lives = 10;
        this.cash =3;
    }
    public void game(){
        String word = getWord();
        String[] board =getBlanks(word);
        while(points< word.length()&& lives>0){
            displayGame(board);
            Scanner in = new Scanner(System.in);
            if(cash >0) {
                System.out.println("do you want use cash?");
                String money = in.next();
                if (money.equals("yes")) {
                    Random rand = new Random();
                    boolean ran = false;
                    while (ran == false) {
                        int place = rand.nextInt(word.length() - 1) + 1;
                        if (board[place] == "_") {
                          board =  moneyExecute(word,place,board);
                          break;
                        }
                    }
                    continue;
                }
            }
            boolean valid = false;
            String input = "";
            System.out.println("enter letter or full word");
            while(valid == false) {
                try {
                 input = in.next();
                } catch (Exception e) {
                    System.out.println("invailid input");
                }
                if((input.length() == 1&& input.matches("[a-zA-Z]"))||(input.length() == word.length() )){
                    break;
                }
                System.out.println("please enter a letter or word");
            }
            if(input.length()==1) {
                char[] letter = input.toCharArray();
                maintenance(word, letter[0]);
                board = changeClue(word, board, letter[0]);
            }
            else if (input.length() ==word.length() && input.equals(word)){
                System.out.println("you win!!!");
                break;
            }
            else{
                System.out.println("not the word sorry");
                lives--;
            }
        }
        if(lives ==0){
            System.out.println("you lose\n the word was: " +word);
        }else{
            System.out.println(" you win!!!!!");
        }

    }
    public void displayGame(String[] clue){
        String coverted = toString(clue);
       for(int i =0; i<coverted.length();i++){
           System.out.print(Character.toString(coverted.charAt(i))+"   ");
       }
        System.out.println("\n"+"points: "+points);
        System.out.println("lives: "+lives);
        System.out.println("cash: "+cash);
    }

    public int countAlphebet(String word, char letter){
        int count = 0;
        for(int i = 0; i< word.length(); i++){
            if(word.charAt(i)== letter){
                count++;
            }
        }
        return count;
    }
    public  String[] getBlanks(String word){
        int count = word.length();
        String[] blanks = new String[count];
        for(int i = 0; i<count;i++){
            blanks[i] = "_";
        }
        return blanks;
    }

    public  String[] changeClue(String word, String[] currentclue, char guess){
        for(int i = 0; i< word.length(); i++){
            if(word.charAt(i) == guess){
                currentclue[i]=Character.toString(guess);
            }
        }
        return currentclue;
    }

    public void maintenance(String word, char letter){
      int res =  countAlphebet(word,letter);
      points+=res;
      if(res ==0){
          lives --;
      }
    }
    public String[] moneyExecute(String word, int place,String[] board){
        cash --;
       return specificChange(word, place,board);
    }
    private  String getWord(){
        Random line = new Random();
        String word = "";
        File dictionary = new File("/Users/alexdudin/IdeaProjects/Hangman/src/com/company/Words.txt");
        try(FileReader reader =new FileReader(dictionary);
            BufferedReader read = new BufferedReader(reader)) {
            for(int i = 0 ; i<line.nextInt(466551);i++){
                word= read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    private String toString(String[] given){
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i<given.length;i++){
           builder.append(given[i]);
       }
       return builder.toString();
    }
    private String[] specificChange(String word,int space,String[] board){
        board[space-1]= Character.toString(word.charAt(space-1));
        return board;
    }
}
