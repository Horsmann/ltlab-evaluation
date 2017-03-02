package de.unidue.ltl.evaluation.transformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;

import org.junit.Test;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class EvaluationTransformerTest {

	@Test
	public void deleteLabelsTest() 
	{
		
		Evaluation<String> eval= new Evaluation<>();
		
		eval.register("A", "B");
		eval.register("B", "B");
		eval.register("A", "C");
		eval.register("C", "A");
		eval.register("C", "C");
		
				
		Evaluation<String> transformed  = EvaluationTransformer.deleteLabels(eval, "C" );
						
		Collection<EvaluationEntry<String>> entries = transformed.getEntries();
		assertEquals(2, entries.size());
	
		for (EvaluationEntry<String> entry : entries) {
			assertNotEquals("C", entry.getGold());
			assertNotEquals("C", entry.getPredicted());
		}
	}
}
