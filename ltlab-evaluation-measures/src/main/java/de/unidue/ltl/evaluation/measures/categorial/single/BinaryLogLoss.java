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

package de.unidue.ltl.evaluation.measures.categorial.single;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public class BinaryLogLoss<T extends Number>
    extends EvaluationMeasure<Double>
{
	private static final double EPS = 1e-15;

    boolean didCalculate = false;
    double binaryLogLoss;

    public BinaryLogLoss(EvaluationData<Double> data)
    {
        super(data);
    }
    
    void calculate()
    {
        if (didCalculate) {
            return;
        }
    
        double sum = 0.0;
        for (EvaluationEntry<Double> e : data) {
        	Double prediction = e.getPredicted();
        	Double gold = e.getGold();

        	prediction = Math.min(Math.max(prediction, EPS), 1 - EPS);
        	
        	sum += - gold * Math.log(prediction) + (1-gold) * Math.log(1 - prediction);
        }
        
        binaryLogLoss = sum / data.size();

        didCalculate = true;
    }

	@Override
	public double getResult() {
		if (!didCalculate) {
			calculate();
		}

		return binaryLogLoss;
	}
}