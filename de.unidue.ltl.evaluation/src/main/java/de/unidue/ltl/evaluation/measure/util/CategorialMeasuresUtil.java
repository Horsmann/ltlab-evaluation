package de.unidue.ltl.evaluation.measure.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.Accuracy;

public class CategorialMeasuresUtil {

	public static Map<String, EvaluationResult> computeCategorialResults(Collection<EvaluationEntry<String>> entries) {
		Map<String, EvaluationResult> results = new HashMap<String, EvaluationResult>();
		
		int tp = 0;
		int fp = 0;
		int fn = 0;
		int tn = 0;
		
		for (EvaluationEntry<String> entry : entries) {
			String gold = entry.getGold();
			String pred = entry.getPredicted();
			
			if (gold.equals(pred)) {
				tp++;
			}
		}
		int n = entries.size();
		
		double acc = (double) tp / n;
		
		results.put(Accuracy.ACC_MEASURE, new EvaluationResult(acc));
		return results;
	}
}
