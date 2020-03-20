package com.target.barrenland.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class BarrenLand {

    private int width;
    private int length;
    private BarrenLandNode[][] land;
    private BarrenLandNode root;

    public BarrenLand(int width, int length) {
        this.width = width;
        this.length = length;
        land = new BarrenLandNode[width][length];
    }

    private void populateLand () {
        for(int i=0; i<width; i++) {
            for (int j = 0; j < length; j++) {
                BarrenLandNode bln = new BarrenLandNode();
                bln.setX(i);
                bln.setY(j);
                bln.setValue(0);
                land[i][j] = bln;
                if (i == 0 && j == 0) {
                    root = bln;
                } else if (j == 0) {
                    // left edge
                    bln.setPrev(land[i - 1][length - 1]);
                    bln.getPrev().setNext(bln);
                } else {
                    // we always have to get prev and set its next to bln
                    // we also have to set prev
                    bln.setPrev(land[i][j - 1]);
                    bln.getPrev().setNext(bln);
                }
            }
        }
    }

    private void populateBarrenLand(List<List<Integer>> barrenLands) {
        populateLand();
        for (List<Integer> barrenLand : barrenLands) {
            for (int x = barrenLand.get(0); x <= barrenLand.get(2); x++) {
                for (int y = barrenLand.get(1); y <= barrenLand.get(3); y++) {
                    if (root.getX() == x && root.getY() == y) {
                        root = root.getNext();
                    }
                    if(!land[x][y].isCounted()) {
                        land[x][y].setValue(-1);
                        land[x][y].delete();
                        land[x][y].setCounted(true);
                    }
                }
            }
        }
    }

    public boolean createBarrenLand(String barrenLands) {
        List<List<Integer>> refinedInput = validateAndTranslateInput(barrenLands);
        if(refinedInput != null) {
            populateBarrenLand(refinedInput);
            return true;
        }
        return false;
    }

    private List<List<Integer>> validateAndTranslateInput(String input) {
        List<List<Integer>> refinedInput = new ArrayList<>();
        //make sure empty list of barren land is still valid
        if(StringUtils.isBlank(input) || "{}".equals(input.trim())) {
            return refinedInput;
        }
        input=input.trim();
        try {
            if (input.charAt(0) == '{' && input.charAt(input.length() - 1) == '}') {
                input = input.substring(1, input.length() - 1);
                String[] rectangles = input.split(",");
                for (String rectangle : rectangles) {
                    rectangle = rectangle.trim();
                    if ((rectangle.charAt(0) == '"' && rectangle.charAt(rectangle.length()-1) == '"') ||
                            (rectangle.charAt(0) == '“' && rectangle.charAt(rectangle.length()-1) == '”')) { // fancy quotes
                        rectangle = rectangle.substring(1, rectangle.length()-1);
                        //Check there are 4 things in rectangle and they are within range
                        List<Integer> coords = (Stream.of(rectangle.split(" "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()));
                        if(coords.size() == 4
                                && coords.get(0) >= 0 && coords.get(0) < width
                                && coords.get(1) >= 0 && coords.get(1) < length
                                && coords.get(2) >= coords.get(0) && coords.get(2) < width
                                && coords.get(3) >= coords.get(1) && coords.get(3) < length) {
                            refinedInput.add(coords);
                        } else {
                            throw new Exception();
                        }

                    } else {
                        throw new Exception();
                    }
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Invalid input detected\nInput should look like:\n" +
                    "{“48 192 351 207”, “48 392 351 407”,“120 52 135 547”, “260 52 275 547”}\n");
            return null;
        }
        return refinedInput;
    }
}
