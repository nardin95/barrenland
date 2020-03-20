package com.target.barrenland.solver;


import com.target.barrenland.model.BarrenLand;
import com.target.barrenland.model.BarrenLandNode;

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
        BarrenLandNode[][] grid = barrenLand.getLand();
        BarrenLandNode root = barrenLand.getRoot();

        while (root != null && root.hasNext()) {
            if (root.getValue() == 0) {
                int fertileLandSize = 0;
                Stack<BarrenLandNode> stack = new Stack<>();
                stack.push(root);
                while (!stack.empty()) {
                    BarrenLandNode lastElement = stack.pop();
                    int lastElementX = lastElement.getX();
                    int lastElementY = lastElement.getY();
                    if (grid[lastElementX][lastElementY].getValue() == 0) {
                        fertileLandSize++;
                        grid[lastElementX][lastElementY].setValue(1);
                        grid[lastElementX][lastElementY].delete();
                        if (root.getX() == lastElementX && root.getY() == lastElementY) {
                            root = root.getNext();
                        }

                        pushElement(stack, grid, lastElementX, lastElementY+1);
                        pushElement(stack, grid, lastElementX, lastElementY-1);
                        pushElement(stack, grid, lastElementX+1, lastElementY);
                        pushElement(stack, grid, lastElementX-1, lastElementY);
                    }
                }
                results.add(fertileLandSize);
            }
            if (root != null) {
                root = root.getNext();
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

    private void pushElement (Stack<BarrenLandNode> stack, BarrenLandNode[][] grid, int widthX, int lengthY) {
        if (isInRange(widthX, lengthY) && grid[widthX][lengthY].getValue() == 0) {
            stack.push(new BarrenLandNode(null, null, widthX, lengthY, 0, false));
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