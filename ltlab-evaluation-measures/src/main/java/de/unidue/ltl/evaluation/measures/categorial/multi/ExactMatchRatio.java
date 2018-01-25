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
package de.unidue.ltl.evaluation.measures.categorial.multi;

import java.util.List;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public class ExactMatchRatio<T> extends EvaluationMeasure<T> {

	boolean didCalculate = false;
	private double result;

	public ExactMatchRatio(EvaluationData<T> data) {
		super(data);
	}

	private void calculate() {
		double exactMatches=.0;
		for(EvaluationEntry<T> e : data){
			List<T> goldMulti = e.getGoldMultiLabel();
			List<T> predictedMulti = e.getPredictedMultiLabel();
			
			boolean match=true;
			for(int i=0; i < goldMulti.size(); i++){
				if (!goldMulti.get(i).equals(predictedMulti.get(i))){
					match=false;
					break;
				}
			}
			
			exactMatches += match ? 1 : 0;
		}
		
		result = exactMatches / data.size();
	}

	@Override
	public double getResult() {

		if (didCalculate) {
			return result;
		}

		calculate();

		return result;
	}

}
