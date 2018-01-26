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

public class MeanSquaredError extends EvaluationMeasure<Double> {

	double result=0.0;
	boolean didCalculate=false;
	
	public MeanSquaredError(EvaluationData<Double> data) {
		super(data);
	}

	void calculate(){
		
		double val=0.0;
		
		for(EvaluationEntry<Double> e : data){
			Double gold = e.getGold();
			Double pred = e.getPredicted();
			
			Double diff = gold-pred;
			double pow = Math.pow(diff, 2);
			val+=pow;
		}
		
		result = val/data.size();
		didCalculate=true;
	}

	@Override
	public double getResult() {
		
        if (!didCalculate) {
            calculate();
        }
        
        return result;
	}
	
	
	
}
