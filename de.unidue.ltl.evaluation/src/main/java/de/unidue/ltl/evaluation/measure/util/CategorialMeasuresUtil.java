package de.unidue.ltl.evaluation.measure.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.Accuracy;
import de.unidue.ltl.evaluation.measure.Fscore;
import de.unidue.ltl.evaluation.measure.Precision;
import de.unidue.ltl.evaluation.measure.Recall;

public class CategorialMeasuresUtil {

	public static Map<String, EvaluationResult> computeCategorialResults(Collection<EvaluationEntry<String>> entries) {
		Map<String, EvaluationResult> results = new HashMap<String, EvaluationResult>();

		Set<String> categories = listCategories(entries);

		for(String category : categories){
			int tp = 0;
			int fp = 0;
			int fn = 0;
			int tn = 0;

			for (EvaluationEntry<String> entry : entries) {
				String gold = entry.getGold();
				String pred = entry.getPredicted();
				if (gold.equals(category)){
					if (gold.equals(pred)){
						tp++;
					} else {
						fn++;
					}
				} else {
					if (pred.equals(category)){
						fp++;
					} else {
						tn++;
					}
				}
			}	
	//		System.out.println(category+"\t"+tp+"\t"+fp+"\t"+fn);
			double precision = (double) tp/(tp+fp);
			double recall =  (double) tp/(tp+fn);
			double fscore = 2.0*precision*recall/(precision+recall);
			results.put(Precision.PREC_MEASURE+"_"+category, new EvaluationResult(precision));
			results.put(Recall.REC_MEASURE+"_"+category, new EvaluationResult(recall));
			results.put(Fscore.F_MEASURE+"_"+category, new EvaluationResult(fscore));
				}


		int tp = 0;
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

	public static Set<String> listCategories(
			Collection<EvaluationEntry<String>> entries) {
		Set<String> categories = new HashSet<String>();
		for(EvaluationEntry<String> entry : entries){
			categories.add(entry.getGold());
			categories.add(entry.getPredicted());
		}
		return categories;
	}
}
