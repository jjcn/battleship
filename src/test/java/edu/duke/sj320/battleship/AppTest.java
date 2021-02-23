package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.Test;

public class AppTest {
	
	@Test
	@ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
	public void test_main() throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    PrintStream out = new PrintStream(bytes, true);
	    
	    InputStream input = getClass().getClassLoader().getResourceAsStream("input.txt");
	    assertNotNull(input);
	    
	    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output.txt");
	    assertNotNull(expectedStream);
	    
	    InputStream oldIn = System.in;
	    PrintStream oldOut = System.out;
	    
		try {
			System.setIn(input);
			System.setOut(out);
			App.main(new String[0]);
		} finally {
			System.setIn(oldIn);
			System.setOut(oldOut);
		}
		
		String expected = new String(expectedStream.readAllBytes());
	    String actual = bytes.toString();
		assertEquals(expected, actual);
	}
	
}
