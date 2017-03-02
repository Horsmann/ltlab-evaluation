package de.unidue.ltl.evaluation.transformation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class EvaluationTransformer {

	public static <T> Evaluation<T> deleteLabels(Evaluation<T> eval, T ... labels) {
		
		Evaluation<T> transformed = new Evaluation<T>();		
		
		Set<T> labelSet = new HashSet<T>(Arrays.asList(labels));
				
		for (EvaluationEntry<T> entry : eval.getEntries()) {
			
			if (!labelSet.contains(entry.getGold()) && !labelSet.contains(entry.getPredicted())) {
				transformed.register(entry.getGold(), entry.getPredicted());				
			}
		}
		
		return transformed;
	}

}
