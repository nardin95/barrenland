package com.target.barrenland;

import com.target.barrenland.model.BarrenLand;
import com.target.barrenland.solver.Solver;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    private static final int WIDTH = 400;
    private static final int LENGTH = 600;


    public static void main(String args[]) {

        String barrenLands;
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
