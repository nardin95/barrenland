package com.target.barrenland.model;

import org.junit.jupiter.api.Test;
import com.target.barrenland.model.BarrenLand;

import static org.junit.jupiter.api.Assertions.*;

public class BarrenLandTest {
    @Test
    public void testBarrenLandConstructor() {
        BarrenLand barrenLand = new BarrenLand(300,400);
        assertEquals(barrenLand.getWidth(), 300);
        assertEquals(barrenLand.getLength(),400);
    }

    @Test
    public void testBarrenLandCreationSuccessful() {
        String s = "{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        BarrenLand barrenLand = new BarrenLand(400,600);
        assertTrue(barrenLand.createBarrenLand(s));
    }

    @Test
    public void testBarrenLandCreationFailsForMissingBrace() {
        String s = "\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        BarrenLand barrenLand = new BarrenLand(400,600);
        assertFalse(barrenLand.createBarrenLand(s));
    }
}
