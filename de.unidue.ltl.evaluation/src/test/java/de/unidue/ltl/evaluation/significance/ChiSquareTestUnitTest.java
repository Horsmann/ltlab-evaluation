package de.unidue.ltl.evaluation.significance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChiSquareTestUnitTest {

	@Test
	public void chiSquareTest(){
		assertEquals(0.005, ChiSquare.getPvalue(8.0, 1), 0.0001);
		assertEquals(0.05, ChiSquare.getPvalue(4.0, 1), 0.0001);
		assertEquals(1.00, ChiSquare.getPvalue(0.01, 1), 0.0001);
			}
	
	
	
}
