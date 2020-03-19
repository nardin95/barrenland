package com.target.barrenland.solver;

import com.target.barrenland.model.BarrenLand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolverTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testNoBarrenRegions() {
        BarrenLand barrenLand = new BarrenLand(400, 600);
        barrenLand.createBarrenLand("{}");
        Solver solver = new Solver(barrenLand);
        solver.solve();
        solver.printResults();
        assertEquals(1, solver.getResults().size());
        assertEquals(Integer.valueOf(400*600) ,solver.getResults().get(0));
        assertEquals("240000 \n",outContent.toString());
    }

    @Test
    public void testInput1() {
        BarrenLand barrenLand = new BarrenLand(400, 600);
        String input = "{“0 292 399 307”}";
        barrenLand.createBarrenLand(input);
        Solver solver = new Solver(barrenLand);
        solver.solve();
        solver.printResults();
        List<Integer> result= Arrays.asList(116800, 116800);
        assertEquals(2, solver.getResults().size());
        assertEquals(result, solver.getResults());
        assertEquals("116800 116800 \n",outContent.toString());
    }

    @Test
    public void testInput2() {
        BarrenLand barrenLand = new BarrenLand(400, 600);
        String input = "{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        barrenLand.createBarrenLand(input);
        Solver solver = new Solver(barrenLand);
        solver.solve();
        solver.printResults();
        List<Integer> result= Arrays.asList(22816, 192608);
        assertEquals(2, solver.getResults().size());
        assertEquals(result, solver.getResults());
        assertEquals("22816 192608 \n",outContent.toString());
    }
}