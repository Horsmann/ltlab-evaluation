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
package de.unidue.ltl.evaluation.measures.regression;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public class RSquared extends EvaluationMeasure<Double> {

	double result=0.0;
	boolean didCalculate=false;
	
	public RSquared(EvaluationData<Double> data) {
		super(data);
	}

	void calculate(){
		
		double squared_error=.0;
		double predicted_avg=.0;
		
		for(EvaluationEntry<Double> e : data){
			Double gold = e.getGold();
			Double pred = e.getPredicted();
			
			squared_error+=Math.pow(gold-pred, 2);
			predicted_avg+=pred;
		}
		predicted_avg/=data.size();
		
		double sum_prediction_avg_diff=.0;
		for(EvaluationEntry<Double> e : data){
			sum_prediction_avg_diff+=Math.pow(e.getPredicted()-predicted_avg, 2);
		}
		
		result = 1 - (squared_error/sum_prediction_avg_diff);
		
	}

	@Override
	public double getResult() {
		
        if (!didCalculate) {
            calculate();
        }
        
        return result;
	}
	
	
	
}
