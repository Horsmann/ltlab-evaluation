/*******************************************************************************
 * Copyright 2018
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
package de.unidue.ltl.evaluation.measures.categorial;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static de.unidue.ltl.evaluation.core.EvaluationConstants.EPSILON;
import de.unidue.ltl.evaluation.core.EvaluationData;

public class Fscore<T> extends CategoricalMeasure<T> {

	Map<T, Double> f1Measures = new HashMap<>();
	double macro_fscore;
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

		for (T category : categories) {
			Category cvb = getCategoryBaseValues(category);

			double precision = (double) cvb.tp / (cvb.tp + cvb.fp + EPSILON);
			double recall = (double) cvb.tp / (cvb.tp + cvb.fn + EPSILON);
			double fscore = 2.0 * precision * recall / (precision + recall);

			if (Double.isNaN(fscore)) {
				fscore = 0.0;
			}

			precision_sum += precision;
			recall_sum += recall;
			precision_weighted += precision * (1.0 * (cvb.tp + cvb.fn) / (cvb.tp + cvb.fp + cvb.tn + cvb.fn));
			recall_weighted += recall * (1.0 * (cvb.tp + cvb.fn) / (cvb.tp + cvb.fp + cvb.tn + cvb.fn));

			tp_sum += cvb.tp;
			fp_sum += cvb.fp;
			tn_sum += cvb.tn;
			fn_sum += cvb.fn;

			f1Measures.put(category, fscore);
		}

		double macro_precision = precision_sum / categories.size();
		double macro_recall = recall_sum / categories.size();
		macro_fscore = 2.0 * macro_precision * macro_recall / (macro_precision + macro_recall);

		double micro_precision = (double) tp_sum / (tp_sum + fp_sum);
		double micro_recall = (double) tp_sum / (tp_sum + fn_sum);
		micro_fscore = 2.0 * micro_precision * micro_recall / (micro_precision + micro_recall);
		weighted_fscore = 2.0 * precision_weighted * recall_weighted / (precision_weighted + recall_weighted);

		didCalculate = true;
	}

	public double getScoreForLabel(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, f1Measures);
		return f1Measures.get(category);
	}

	public double getMacroFscore() {
		if (!didCalculate) {
			calculate();
		}
		return macro_fscore;
	}

	public double getMicroFscore() {
		if (!didCalculate) {
			calculate();
		}
		return micro_fscore;
	}

	public double getWeightedFscore() {
		if (!didCalculate) {
			calculate();
		}
		return weighted_fscore;
	}

}
