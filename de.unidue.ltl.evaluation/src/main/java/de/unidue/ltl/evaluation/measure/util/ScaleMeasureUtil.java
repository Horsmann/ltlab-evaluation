package de.unidue.ltl.evaluation.measure.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.PearsonCorrelation;
import de.unidue.ltl.evaluation.measure.SpearmanCorrelation;

public class ScaleMeasureUtil {

	public static Map<String, EvaluationResult> computeScaleResults(Collection<EvaluationEntry<Double>> entries) {
		Map<String, EvaluationResult> results = new HashMap<String, EvaluationResult>();
		
		 List<Double> val1 = new ArrayList<Double>();
		 List<Double> val2 = new ArrayList<Double>();
			
		for (EvaluationEntry<Double> entry : entries) {
			val1.add(entry.getGold());
			val2.add(entry.getPredicted());
			
		}
		double pearson = org.dkpro.statistics.correlation.PearsonCorrelation.computeCorrelation(val1, val2);
		double spearman = org.dkpro.statistics.correlation.SpearmansRankCorrelation.computeCorrelation(val1, val2);
		
		results.put(PearsonCorrelation.PEARSON, new EvaluationResult(pearson));
		results.put(SpearmanCorrelation.SPEARMAN, new EvaluationResult(spearman));
		
		return results;
	}
}
