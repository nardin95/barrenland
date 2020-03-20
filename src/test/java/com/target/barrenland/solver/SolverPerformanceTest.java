package com.target.barrenland.solver;

import com.target.barrenland.model.BarrenLand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverPerformanceTest {
    @Test
    public void testTotalPerformance() {
        long startTime = System.currentTimeMillis();
        for(int i=0; i<100; i++) {
            BarrenLand barrenLand = new BarrenLand(400, 600);
            String input = "{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
            barrenLand.createBarrenLand(input);
            Solver solver = new Solver(barrenLand);
            solver.solve();
            assertEquals(solver.getResults().size(), 2);
            assertEquals(solver.getResults().get(0), Integer.valueOf(22816));
            assertEquals(solver.getResults().get(1), Integer.valueOf(192608));
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time to run 100 iterations with array only solver" +
                " (including time to setup): " +  (endTime-startTime) + " ms.");
    }


}
