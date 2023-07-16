package com.main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CA1d {
    private int height = 0;
    private int width = 0;

    private int[][] mainCA;
    private int[] ruleset;

    private JButton[][] grid;

    public CA1d(int width, int height, JButton[][] grid) {
        this.width = width;
        this.height = height;

        this.grid = grid;

        mainCA = new int[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                mainCA[i][j] = 0;
            }
        }

        mainCA[0][height/2] = 1;

        update();
    }

    public void start(int ruleNum) {
        setRule(ruleNum);
        for(int i = 0; i < width; i++) {
            generate(i);
            update();
        }
    }

    private void setRule(int ruleNum) {
        String bin = Integer.toBinaryString(ruleNum);
        char[] charArr = bin.toCharArray();
        char[] tempArr = new char[charArr.length];
        ruleset = new int[8];
        int j = charArr.length;
        for(int i = 0; i < charArr.length; i++) {
            tempArr[i] = charArr[j- 1];
            j--;
        }
        for(int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == '0') {
                ruleset[i] = 0;
            } else {
                ruleset[i] = 1;
            }
        }
    }

    public void generate(int row) {
        int[] tempCA = new int[mainCA.length];
        int left, middle, right;

        for(int i = 0; i < width; i++) {
            if(i == 0) {
                left = mainCA[row][width - 1];
                middle = mainCA[row][i];
                right = mainCA[row][i + 1];
            } else if(i == (width - 1)) {
                left = mainCA[row][i - 1];
                middle = mainCA[row][i];
                right = mainCA[row][0];
            } else {
                left = mainCA[row][i - 1];
                middle = mainCA[row][i];
                right = mainCA[row][i + 1];
            }

            int newState = rules(left, middle, right);
            tempCA[i] = newState;
        }

        for(int j = 0; j < width; j++) {
            int temp = 0;
            temp = tempCA[j];
            if(row + 1 < height) {
                mainCA[row + 1][j] = temp;
            }
        }
    }

    public int rules(int left, int middle, int right) {
        String s = "" + left + middle + right;
        int index = Integer.parseInt(s,2);
        return ruleset[index];
    }

    public void update() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(mainCA[i][j] == 0) {
                    grid[i][j].setBackground(Color.WHITE);
                } else {
                    grid[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }
}
