package de.unidue.ltl.evaluation;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
public class EvaluationMetaDataTest {
	
	@Test
	public void evaluationMetaDataNameTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		Evaluation<String> evaluation= new Evaluation<>(entries,"NamedEvaluation");
		assertEquals("NamedEvaluation",evaluation.getEvalMetaData().getName());
	}
}
