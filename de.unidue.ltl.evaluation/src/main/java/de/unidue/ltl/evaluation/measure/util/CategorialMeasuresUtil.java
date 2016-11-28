/*******************************************************************************
 * Copyright 2016
 * Language Technology Lab
 * University of Duisburg-Essen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.unidue.ltl.evaluation.measure.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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

		double recall_sum = 0d;
		double precision_sum = 0d;
		int tp_sum = 0;
		int fp_sum = 0;
		int fn_sum = 0;
		int tn_sum = 0;

		
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
			precision_sum += precision;
			recall_sum += recall;
			tp_sum += tp;
			fp_sum += fp;
			tn_sum += tn;
			fn_sum += fn;
			results.put(Precision.PREC_MEASURE+"_"+category, new EvaluationResult(precision));
			results.put(Recall.REC_MEASURE+"_"+category, new EvaluationResult(recall));
			results.put(Fscore.F_MEASURE+"_"+category, new EvaluationResult(fscore));
		}
		double precision_macro = precision_sum/categories.size();
		double recall_macro = recall_sum/categories.size();
		double fscore_macro = 2.0*precision_macro*recall_macro/(precision_macro+recall_macro);
		results.put(Precision.PREC_MEASURE+"_MACRO", new EvaluationResult(precision_macro));
		results.put(Recall.REC_MEASURE+"_MACRO", new EvaluationResult(recall_macro));
		results.put(Fscore.F_MEASURE+"_MACRO", new EvaluationResult(fscore_macro));
		double precision_micro = (double) tp_sum/(tp_sum+fp_sum);
		double recall_micro = (double) tp_sum/(tp_sum+fn_sum);
		double fscore_micro = 2.0*precision_micro*recall_micro/(precision_micro+recall_micro);
		results.put(Precision.PREC_MEASURE+"_MICRO", new EvaluationResult(precision_micro));
		results.put(Recall.REC_MEASURE+"_MICRO", new EvaluationResult(recall_micro));
		results.put(Fscore.F_MEASURE+"_MICRO", new EvaluationResult(fscore_micro));
		

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
