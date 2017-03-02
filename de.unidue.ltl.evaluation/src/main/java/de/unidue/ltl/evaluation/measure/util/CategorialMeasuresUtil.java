/*******************************************************************************
 * Copyright 2017
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
import de.unidue.ltl.evaluation.measure.categorial.Accuracy;
import de.unidue.ltl.evaluation.measure.categorial.Fscore;
import de.unidue.ltl.evaluation.measure.categorial.Precision;
import de.unidue.ltl.evaluation.measure.categorial.Recall;

public class CategorialMeasuresUtil {

	public static Map<String, EvaluationResult> computeCategorialResults(Collection<EvaluationEntry<String>> entries) {
		Map<String, EvaluationResult> results = new HashMap<>();

		Set<String> categories = listCategories(entries);

		double recall_sum = 0d;
		double precision_sum = 0d;
		double precision_weighted = 0d;
		double recall_weighted = 0d;
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
			precision_weighted += precision*(1.0*(tp+fn)/(tp+fp+tn+fn));
			recall_weighted += recall*(1.0*(tp+fn)/(tp+fp+tn+fn));
			
			tp_sum += tp;
			fp_sum += fp;
			tn_sum += tn;
			fn_sum += fn;
			results.put(Precision.class.getSimpleName() + "_"+category, new EvaluationResult(precision));
			results.put(Recall.class.getSimpleName() + "_"+category, new EvaluationResult(recall));
			results.put(Fscore.class.getSimpleName() + "_"+category, new EvaluationResult(fscore));
		}
		
		double precision_macro = precision_sum/categories.size();
		double recall_macro = recall_sum/categories.size();
		double fscore_macro = 2.0*precision_macro*recall_macro/(precision_macro+recall_macro);
		results.put(Precision.class.getSimpleName()+"_MACRO", new EvaluationResult(precision_macro));
		results.put(Recall.class.getSimpleName()+"_MACRO", new EvaluationResult(recall_macro));
		results.put(Fscore.class.getSimpleName()+"_MACRO", new EvaluationResult(fscore_macro));
		
		double precision_micro = (double) tp_sum/(tp_sum+fp_sum);
		double recall_micro = (double) tp_sum/(tp_sum+fn_sum);
		double fscore_micro = 2.0*precision_micro*recall_micro/(precision_micro+recall_micro);
		results.put(Precision.class.getSimpleName()+"_MICRO", new EvaluationResult(precision_micro));
		results.put(Recall.class.getSimpleName()+"_MICRO", new EvaluationResult(recall_micro));
		results.put(Fscore.class.getSimpleName()+"_MICRO", new EvaluationResult(fscore_micro));
		
		// weighted by class size (as is done e.g. in WEKA)
		double fscore_weighted = 2.0*precision_weighted*recall_weighted/(precision_weighted+recall_weighted);
		results.put(Precision.class.getSimpleName()+"_WEIGHTED", new EvaluationResult(precision_weighted));
		results.put(Recall.class.getSimpleName()+"_WEIGHTED", new EvaluationResult(recall_weighted));
		results.put(Fscore.class.getSimpleName()+"_WEIGHTED", new EvaluationResult(fscore_weighted));
		

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

		results.put(Accuracy.class.getSimpleName(), new EvaluationResult(acc));
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
