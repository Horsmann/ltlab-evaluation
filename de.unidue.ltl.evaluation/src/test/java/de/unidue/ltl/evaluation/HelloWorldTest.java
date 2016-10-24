package de.unidue.ltl.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldTest
{
    @Test
    public void run() {
        
        HelloWorld hw = new HelloWorld();
        assertEquals("Hello World", hw.returnHelloWorld());
        
    }

}
