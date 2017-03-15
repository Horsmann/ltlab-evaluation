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
package de.unidue.ltl.evaluation.measure.categorial;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.EvaluationData;

public class Fscore<T> extends CategoricalMeasure<T> {
	Map<T, Double> f1Measures = new HashMap<>();
	double macro_fscore;
	double macro_recall;
	double macro_precision;
	double micro_precision;
	double micro_recall;
	double micro_fscore;
	double weighted_fscore;

	private boolean didCalculate = false;
    private double precision_sum;
    private double recall_sum;
    private double precision_weighted;
    private double recall_weighted;
    private long tp_sum;
    private long fp_sum;
    private long tn_sum;
    private long fn_sum;

	public Fscore(EvaluationData<T> data) {
		super(data);
	}

	void calculate() {

		if (didCalculate) {
			return;
		}

		Set<T> categories = getDistinctLabels(data);

		double weighted_precision = 0d;
		double weighted_recall = 0d;

		for (T category : categories) {
			Category cvb = getCategoryBaseValues(category);
 
			double precision = (double) cvb.tp / (cvb.tp + cvb.fp);
			double recall = (double) cvb.tp / (cvb.tp + cvb.fn);
			double fscore = 2.0 * precision * recall / (precision + recall);
			
			if(Double.isNaN(fscore)){
			    fscore=0.0;
			}
			
			precision_sum += precision;
			recall_sum += recall;
			precision_weighted += precision*(1.0*(cvb.tp+cvb.fn)/(cvb.tp+cvb.fp+cvb.tn+cvb.fn));
			recall_weighted += recall*(1.0*(cvb.tp+cvb.fn)/(cvb.tp+cvb.fp+cvb.tn+cvb.fn));

			tp_sum += cvb.tp;
			fp_sum += cvb.fp;
			tn_sum += cvb.tn;
			fn_sum += cvb.fn;
			
			f1Measures.put(category, fscore);
		}

		macro_precision = precision_sum/categories.size();
		macro_recall = recall_sum/categories.size();
		macro_fscore = 2.0* macro_precision*macro_recall/(macro_precision+macro_recall);
		weighted_fscore = 2.0 * weighted_precision * weighted_recall / (weighted_precision + weighted_recall);
		
		micro_precision = (double) tp_sum/(tp_sum+fp_sum);
		micro_recall = (double) tp_sum/(tp_sum+fn_sum);
		micro_fscore = 2.0*micro_precision*micro_recall/(micro_precision+micro_recall);
		weighted_fscore = 2.0*precision_weighted*recall_weighted/(precision_weighted+recall_weighted);

		didCalculate = true;
	}

	public double getScoreForLabel(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, f1Measures);
		return f1Measures.get(category);
	}

	public double getMacro_fscore() {
		if (!didCalculate) {
			calculate();
		}
		return macro_fscore;
	}

	public double getMacro_recall() {
		if (!didCalculate) {
			calculate();
		}
		return macro_recall;
	}

	public double getMacro_precision() {
		if (!didCalculate) {
			calculate();
		}
		return macro_precision;
	}

	public double getMicro_precision() {
		if (!didCalculate) {
			calculate();
		}
		return micro_precision;
	}

	public double getMicro_recall() {
		if (!didCalculate) {
			calculate();
		}
		return micro_recall;
	}

	public double getMicro_fscore() {
		if (!didCalculate) {
			calculate();
		}
		return micro_fscore;
	}

	public double getWeighted_fscore() {
		if (!didCalculate) {
			calculate();
		}
		return weighted_fscore;
	}

}
