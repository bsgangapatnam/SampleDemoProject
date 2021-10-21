package com.martian.robots;

public class Main {
    public static void main(String[] args) {
        String file = args[0];
        InputParser parser = new InputParser(file);
        StateMachine stateMachine = parser.generateApplicationState();
        String output = stateMachine.triggerStateMachine();
        System.out.println(output);
    }
}
