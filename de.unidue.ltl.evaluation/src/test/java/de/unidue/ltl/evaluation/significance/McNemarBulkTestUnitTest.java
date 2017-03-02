package de.unidue.ltl.evaluation.significance;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationMetaData;

public class McNemarBulkTestUnitTest {

	@Test
	public void mcnemareSignificanceTest() throws Exception {
		Evaluation<String> evaluation1 = new Evaluation<String>("eval_a");
		Evaluation<String> evaluation2 = new Evaluation<String>("eval_b");
		
		
		for(int i=0; i< 8; i++){
			evaluation1.register("A", "A");
			evaluation2.register("A", "A");
		}
		
		for(int i=0; i< 11; i++){
			evaluation1.register("A", "B");
			evaluation2.register("A", "B");
		}
		
		for(int i=0; i< 16; i++){
			evaluation1.register("A", "B");
			evaluation2.register("A", "A");
		}
		
		for(int i=0; i< 5; i++){
			evaluation1.register("A", "A");
			evaluation2.register("A", "B");
		}
	
		McNemarBulkTest test = new McNemarBulkTest();
		test.register(evaluation1);
		test.register(evaluation2);
		Map<String, Map<String, Double>> table = test.computeBulkTable();
		System.out.println("McNemarBulk p-values");
		for (String row : table.keySet()) {
			for (String column : table.get(row).keySet()) {
				System.out.print(table.get(row).get(column)+"\t");
			}
			System.out.print("\n");
		}
		assertEquals(0.025, table.get(evaluation1.getEvalMetaData().getName())
				.get(evaluation2.getEvalMetaData().getName()), 0.001);
		
	}
	
	
}
