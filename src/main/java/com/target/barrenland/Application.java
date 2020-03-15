package com.target.barrenland;

import com.target.barrenland.model.BarrenLand;
import com.target.barrenland.model.BarrenLandNode;
import com.target.barrenland.solver.Solver;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    private static final int WIDTH = 400;
    private static final int LENGTH = 600;


    public static void main(String args[]) {
        BarrenLandNode root = new BarrenLandNode();
        BarrenLandNode[][] grid = new BarrenLandNode[WIDTH][LENGTH];
        for(int i=0; i<WIDTH; i++) {
            for(int j=0; j<LENGTH; j++) {
                BarrenLandNode bln = new BarrenLandNode();
                bln.setX(i);
                bln.setY(j);
                bln.setValue(1);
                grid[i][j] = bln;
                if(i==0 && j==0) {
                    root = bln;
                } else if (j==0) {
                    //left edge
                    bln.setPrev(grid[i-1][LENGTH-1]);
                    bln.getPrev().setNext(bln);
                } else {
                    //we always have to get prev and set its next to bln
                    // we also have to set prev
                    bln.setPrev(grid[i][j-1]);
                    bln.getPrev().setNext(bln);
                }
            }
        }
        //this removes the node after [2][4]
        grid[2][4].setNext(grid[2][4].getNext().getNext());
        grid[2][4].getNext().setPrev(grid[2][4]);
        //end removes

        String barrenLands = null;
        System.out.println("Please input one or more regions of barren land in the format" +
                " {\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}");
        try {
            while ((barrenLands = new Scanner(System.in).nextLine()) != null) {
                BarrenLand barrenLand = new BarrenLand(WIDTH, LENGTH);
                if(barrenLand.createBarrenLand(barrenLands)) {
                    Solver solver = new Solver(barrenLand);
                    solver.solve();
                    solver.printResults();
                }
            }
        } catch(NoSuchElementException e) {
            //normal exit with EOF
        }
    }
}
