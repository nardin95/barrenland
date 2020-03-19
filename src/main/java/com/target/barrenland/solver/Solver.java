package com.target.barrenland.solver;


import com.target.barrenland.model.BarrenLand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solver {

    private BarrenLand barrenLand;
    private List<Integer> results;

    public Solver(BarrenLand barrenLand) {
        results = new ArrayList<>();
        this.barrenLand = barrenLand;
    }

    public List<Integer> getResults() {
        return results;
    }

    public void solve() {
        int[][] grid = barrenLand.getLand();

        for (int x = 0; x < barrenLand.getWidth(); x++) {
            for (int y = 0; y < barrenLand.getLength(); y++) {
                if (grid[x][y] == 0 ) {
                    int fertileLandSize = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[] {x, y});
                    while (!stack.empty()) {
                        int[] lastElement = stack.pop();
                        int lastElementX = lastElement[0];
                        int lastElementY = lastElement[1];
                        if (grid[lastElementX][lastElementY] == 0) {
                            grid[lastElementX][lastElementY] = 1;
                            fertileLandSize++;
                            pushElement(stack, grid, lastElementX, lastElementY+1);
                            pushElement(stack, grid, lastElementX, lastElementY-1);
                            pushElement(stack, grid, lastElementX+1, lastElementY);
                            pushElement(stack, grid, lastElementX-1, lastElementY);
                        }
                    }
                    results.add(fertileLandSize);
                }
            }
        }
        Collections.sort(results);
    }

    public void printResults() {
        for (int result : results) {
            System.out.print(result + " ");
        }
        System.out.println();
    }

    private void pushElement (Stack<int[]> stack, int[][] grid, int widthX, int lengthY) {
        if (isInRange(widthX, lengthY) && grid[widthX][lengthY] == 0) {
            stack.push(new int[] {widthX, lengthY});
        }
    }

    private boolean isWidthInRange (int width) {
        return width >= 0 && width < barrenLand.getWidth();
    }
    private boolean isLengthInRange (int length) {
        return length >= 0 && length < barrenLand.getLength();
    }
    private boolean isInRange (int width, int length) {
        return isWidthInRange(width) && isLengthInRange(length);
    }
}
