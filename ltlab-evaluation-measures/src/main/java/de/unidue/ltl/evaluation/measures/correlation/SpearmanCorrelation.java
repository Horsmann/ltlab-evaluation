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

package de.unidue.ltl.evaluation.measures.correlation;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.VectorPair;

public class SpearmanCorrelation extends CorrelationMeasure<Double> {
	boolean didCalculate = false;
	double correlation;

	public SpearmanCorrelation(EvaluationData<Double> data) {
		super(data);
	}

	void calculate() {
		if (didCalculate) {
			return;
		}
		VectorPair<Double> vectors = new VectorPair<>(data);

		correlation = org.dkpro.statistics.correlation.SpearmansRankCorrelation
				.computeCorrelation(vectors.getVal1(), vectors.getVal2());
		
		didCalculate = true;
	}

	@Override
	public double getResult() {
		if (!didCalculate) {
			calculate();
		}

		return correlation;
	}
}