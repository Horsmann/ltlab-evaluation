package de.unidue.ltl.evaluation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.measure.Accuracy;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class AccuracyTest {
	
	@Test
	public void accuracyTest(){
		Collection<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		
		Map<String, EvaluationResult> results = CategorialMeasuresUtil.computeCategorialResults(entries);
		assertEquals(1, results.size()); 
		
		EvaluationResult result = results.get(Accuracy.ACC_MEASURE);
		assertEquals(0.75, result.getResult(), 0.001);
		
				
	}

}
