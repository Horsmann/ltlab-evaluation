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
package de.unidue.ltl.evaluation.measures.multilabel;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public class HammingLoss extends EvaluationMeasure<Integer> {

	private double result;
	private boolean didCalculate=false;

	public HammingLoss(EvaluationData<Integer> data) {
		super(data);
	}

	private void calculate() {
		
		double avg=0.0;
		
		for (int i=0; i < data.size(); i++) {
			double diff=0;
			EvaluationEntry<Integer> entry = data.get(i);
			for (int j=0; j < entry.getSize(); j++) {
				Integer g = entry.getGoldMultiLabel().get(j);
				Integer p = entry.getPredictedMultiLabel().get(j);
				if (!g.equals(p)) {
					diff++;
				}
			}
			avg += diff/entry.getSize();
		}
		
		result = avg / data.size();
		didCalculate = true;
	}

	@Override
	public double getResult() {
		
		if(!didCalculate) {
			calculate();
		}
		
		return result;
	}

}
