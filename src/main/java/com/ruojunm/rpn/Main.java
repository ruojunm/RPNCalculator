package com.ruojunm.rpn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void start(){
        Scanner inputScanner = new Scanner(new BufferedInputStream(System.in));
        RPNCalculator calculator = new RPNCalculator();
        while(inputScanner.hasNext()){
            String line = inputScanner.nextLine();
            calculator.processRPNInput(line);
        }
    }

    public static void main(String[] args){
        System.out.println("Welcome to use RPN Calculator! Please input RPN sequences, containing " +
                "whitespace separated lists of numbers and operators!");
        System.out.println("All the following operators are supported:  \"+, -, *, /, sqrt, undo, clear\" ");
        start();
    }
}
