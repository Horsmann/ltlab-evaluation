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

public class CategoricalAccuracy<T> extends CategoricalMeasure<T> {
	Map<T, Double> accuracies = new HashMap<>();
	Map<T, Double> fns = new HashMap<>();
	Map<T, Double> tns = new HashMap<>();
	Map<T, Double> tps = new HashMap<>();
	Map<T, Double> fps = new HashMap<>();
	private boolean didCalculate = false;

	public CategoricalAccuracy(EvaluationData<T> data) {
		super(data);
	}

	void calculate() {

		if (didCalculate) {
			return;
		}

		Set<T> categories = getDistinctLabels(data);

		for (T category : categories) {
			Category cvb = getCategoryBaseValues(category);

			double positive = (double) cvb.tp + cvb.tn;
			double all = (double) positive + cvb.tn + cvb.fn;
			double accuracy = (double) positive / all;

			if (Double.isNaN(accuracy)) {
				accuracy = 0.0;
			}
			fns.put(category, (double) cvb.fn);
			tns.put(category, (double) cvb.tn);
			tps.put(category, (double) cvb.tp);
			fps.put(category, (double) cvb.fp);
			accuracies.put(category, accuracy);
		}

		didCalculate = true;
	}

	
	public double getScoreForLabel(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, accuracies);
		return accuracies.get(category);
	}

	public double getFN(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, accuracies);
		return fns.get(category);
	}

	public double getTP(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, accuracies);
		return tps.get(category);
	}

	public double getTN(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, accuracies);
		return tns.get(category);
	}

	public double getFP(T category) {
		if (!didCalculate) {
			calculate();
		}
		verifyLabelKnown(category, accuracies);
		return fps.get(category);
	}
	

}
