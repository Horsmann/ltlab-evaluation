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

package de.unidue.ltl.evaluation.measures.correlation;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.VectorPair;


public class SpearmanCorrelation
	extends CorrelationMeasure<Double>
{
    boolean didCalculate=false;
    double computeCorrelation;

    public SpearmanCorrelation(EvaluationData<Double> data)
    {
        super(data);
    }

    @Override
    public double getCorrelation()
    {
        if(!didCalculate){
            calculate();
        }
        
        return computeCorrelation;
    }

    void calculate()
    {
        if(didCalculate){
            return;
        }
        VectorPair<Double> vectors = new VectorPair<>(data);
        
        computeCorrelation = org.dkpro.statistics.correlation.SpearmansRankCorrelation.computeCorrelation(vectors.getVal1(), vectors.getVal2());
    }
}