package com.keyin.airtravelcli;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    @Test
    void printsBannerAndExitsOnZero() {
        String input = "0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBuf);

        var oldIn = System.in;
        var oldOut = System.out;
        try {
            System.setIn(in);
            System.setOut(out);

            com.keyin.airtravelcli.Main.main(new String[0]);

            String printed = outBuf.toString();
            assertTrue(printed.contains("=== Air Travel CLI ==="));
            assertTrue(printed.toLowerCase().contains("goodbye"));
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }
}
