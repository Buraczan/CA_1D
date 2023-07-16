package com.main;

public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setup("ECA 1D!");

        CA1d cellularAutomata = new CA1d(frame.cax, frame.cay, frame.getGrid());

        cellularAutomata.start(90); // SET THE NUMBER OF AUTOMATA

    }
}
