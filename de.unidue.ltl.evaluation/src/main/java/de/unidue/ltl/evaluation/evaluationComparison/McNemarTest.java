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
package de.unidue.ltl.evaluation.evaluationComparison;

import java.util.ArrayList;
import java.util.List;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class McNemarTest<T> {

	Evaluation<T> evaluation1;
	Evaluation<T> evaluation2;

	public double computeSignificance() {
		List<EvaluationEntry<T>> c1 = new ArrayList<>(evaluation1.getEntries());
		List<EvaluationEntry<T>> c2 = new ArrayList<>(evaluation2.getEntries());

		double sample1negative = 0;
		double sample2negative = 0;
		for (EvaluationEntry<T> entry : c1) {
			if (!positive(entry)) {
				sample1negative++;
			}
		}

		for (EvaluationEntry<T> entry : c2) {
			if (!positive(entry)) {
				sample2negative++;
			}
		}
		double mcNemar = Math.pow(Math.abs(sample2negative - sample1negative) - 0.5, 2)
				/ (sample1negative + sample2negative);

		return mcNemar;

	}

	private boolean positive(EvaluationEntry<T> entry) {
		if (entry.getGold().equals(entry.getPredicted())) {
			return true;
		}
		return false;
	}

	public McNemarTest(Evaluation<T> evaluation1, Evaluation<T> evaluation2) {
		this.evaluation1 = evaluation1;
		this.evaluation2 = evaluation2;
	}
}
