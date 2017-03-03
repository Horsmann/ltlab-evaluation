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

	public Fscore(EvaluationData<T> data) {
		super(data);
	}

	@Override
	public void calculate() {

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

			f1Measures.put(category, fscore);
		}

		macro_fscore = 2.0 * macro_recall * macro_recall / (macro_recall + macro_recall);
		micro_fscore = 2.0 * micro_precision * micro_recall / (micro_precision + micro_recall);
		weighted_fscore = 2.0 * weighted_precision * weighted_recall / (weighted_precision + weighted_recall);

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
