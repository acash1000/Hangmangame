package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    private Hangman hang;
    private String word;
    private char letter;
    @BeforeEach
    void setUpTest(){
        word = "pizza";
         hang = new Hangman();
         letter = 'z';
    }

    @Test
    void test_countAlphebet(){
        char letter = 'x';
      int count =  hang.countAlphebet(word,letter);
        assertEquals(0,count);
    }
    @Test
    void test_getBlanks(){
       String[] blanks = hang.getBlanks(word);
       String[] expected = {"_","_","_","_","_"};
        assertArrayEquals(expected,blanks);
    }
    @Test
    void test_changeClue(){
        char correctguess = 'z';
        String[] blanks = {"_","_","_","_","_"};
        String[] test=  hang.changeClue(word,blanks,correctguess);
        String[] expected = {"_","_","z","z","_"};
        assertArrayEquals(expected,test);
    }

    @Test
    void test_moneyExecute(){
        int space = 5 ;
        String[] blanks = {"_","_","_","_","_"};
        String[] result = hang.moneyExecute(word,space,blanks);
        String[] testcase = {"_","_","_","_","a"};
        assertArrayEquals(testcase,result);
    }

    @AfterEach
    void tearDownTest(){
        word = null;
        hang = null;
    }
}