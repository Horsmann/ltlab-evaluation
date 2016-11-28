package de.unidue.ltl.evaluation.measure;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.util.ScaleMeasureUtil;

public class CorrelationTest {
	
	@Test
	public void correlationTest(){
		Collection<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		entries.add(new EvaluationEntry<Double>(1.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 3.0));
		entries.add(new EvaluationEntry<Double>(3.0, 4.0));
		entries.add(new EvaluationEntry<Double>(4.0, 5.0));
		
		Map<String, EvaluationResult> results = ScaleMeasureUtil.computeScaleResults(entries);
		assertEquals(2, results.size()); 
		
		assertEquals(1.0, results.get(PearsonCorrelation.PEARSON).getResult(), 0.001);
		assertEquals(1.0, results.get(SpearmanCorrelation.SPEARMAN).getResult(), 0.001);
				
		EvaluationMeasure<Double> pearson = new PearsonCorrelation(entries);
		assertEquals("PearsonCorrelation", pearson.getName());
		
		Map<String, EvaluationResult> resultsPearson = pearson.calculate();

		assertEquals(2, resultsPearson.size()); 
		
		assertEquals(1.0, resultsPearson.get(PearsonCorrelation.PEARSON).getResult(), 0.001);
		assertEquals(1.0, resultsPearson.get(SpearmanCorrelation.SPEARMAN).getResult(), 0.001);
	}
}
