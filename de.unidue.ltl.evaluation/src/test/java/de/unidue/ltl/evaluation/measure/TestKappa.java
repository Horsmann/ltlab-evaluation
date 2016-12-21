package de.unidue.ltl.evaluation.measure;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;
import de.unidue.ltl.evaluation.util.TestUtils;

public class TestKappa {

	// gold: 50 A, 18 B, 32 C
	@Test
	public void kappaTest(){
		Collection<EvaluationEntry<String>> entries = TestUtils.getExample();

		assertEquals(100, entries.size());
		//	System.out.println(entries.size());

		CohensKappa kappa = new CohensKappa(entries);
		Map<String, EvaluationResult> results = kappa.calculate();
		assertEquals(1, results.size()); 

		EvaluationResult result = results.get(CohensKappa.class.getSimpleName());
		System.out.println("Kappa: "+result.getResult());
		assertEquals(0.4557, result.getResult(), 0.001);



	}
}