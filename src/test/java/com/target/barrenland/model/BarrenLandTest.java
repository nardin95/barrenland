package com.target.barrenland.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BarrenLandTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    private String errorMsg = "Invalid input detected\nInput should look like:\n" +
            "{“48 192 351 207”, “48 392 351 407”,“120 52 135 547”, “260 52 275 547”}\n\n";
    private BarrenLand barrenLand;

    @BeforeEach
    public void setUp() {
        barrenLand = new BarrenLand(400,600);
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    public void testBarrenLandConstructor() {
        assertEquals(barrenLand.getWidth(), 400);
        assertEquals(barrenLand.getLength(), 600);
    }

    @Test
    public void testBarrenLandCreationSuccessful() throws Exception{
        String input = "{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        List<List<Integer>> refinedInput = new ArrayList<>();
        refinedInput.add(Arrays.asList(48, 192, 351, 207));
        refinedInput.add(Arrays.asList(48, 392, 351, 407));
        refinedInput.add(Arrays.asList(120, 52, 135, 547));
        refinedInput.add(Arrays.asList(260, 52, 275, 547));
        assertEquals(refinedInput,validateAndTranslateInput.invoke(barrenLand, input));
        assertTrue(barrenLand.createBarrenLand(input));
    }

    @Test
    public void testValidateAndTranslateInputFailsForMissingBrace() throws Exception {
        String input = "\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputFailsForNonInteger() throws Exception {
        String input = "{\"48.1 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputFailsForInvalidNumberOfCoordinates() throws Exception {
        String input = "{\" 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputFailsForInvalidSeparator() throws Exception {
        String input = "{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\"; \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputFailsForMissingQuotes() throws Exception {
        String input = "{48 192 351 207, \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputFailsForInvalidDimensions() throws Exception {
        String input = "{\"48 192 351 100\", \"48 392 351 407\", \"120 52 135 547\"; \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputFailsForOutOfBounds() throws Exception {
        String input = "{\"48 192 1000 100\", \"48 392 351 407\", \"120 52 135 547\"; \"260 52 275 547\"}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertNull(validateAndTranslateInput.invoke(barrenLand, input));
        assertEquals(errorMsg, errContent.toString());
    }

    @Test
    public void testValidateAndTranslateInputSuccessForEmptyInput() throws Exception {
        String input = "{}";
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertEquals(new ArrayList<ArrayList<Integer>>(),validateAndTranslateInput.invoke(barrenLand, input));
        assertTrue(barrenLand.createBarrenLand(input));
    }

    @Test
    public void testValidateAndTranslateInputSuccessForNull() throws Exception {
        String input = null;
        Method validateAndTranslateInput = BarrenLand.class.getDeclaredMethod("validateAndTranslateInput", String.class);
        validateAndTranslateInput.setAccessible(true);
        assertEquals(new ArrayList<ArrayList<Integer>>(),validateAndTranslateInput.invoke(barrenLand, input));
        assertTrue(barrenLand.createBarrenLand(input));
    }
}