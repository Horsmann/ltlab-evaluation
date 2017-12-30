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

package de.unidue.ltl.evaluation.measure.agreement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class QuadraticallyWeightedKappa<T extends Number>
    extends AgreementMeasure<T>
{

    boolean didCalculate = false;
    double calculateAgreement;

    public QuadraticallyWeightedKappa(EvaluationData<T> data)
    {
        super(data);
    }

    void calculate()
    {
        if (didCalculate) {
            return;
        }
        
        List<Integer> gold = new ArrayList<Integer>();
        List<Integer> predictions = new ArrayList<Integer>();
        Set<Integer> labels = new HashSet<Integer>();
        
        for (EvaluationEntry<T> e : data) {
        	
        	gold.add((int) Math.round(e.getGold().doubleValue()));
        	predictions.add((int) Math.round(e.getPredicted().doubleValue()));
        	labels.add((int) Math.round(e.getGold().doubleValue()));
        	labels.add((int) Math.round(e.getPredicted().doubleValue()));
        }
        calculateAgreement = getKappa(
        		gold.toArray(new Integer[gold.size()]),
        		predictions.toArray(new Integer[predictions.size()]),
        		labels.toArray(new Integer[0])
        );

        didCalculate = true;
    }

    @Override
    public double getAgreement()
    {
        if (!didCalculate) {
            calculate();
        }

        return calculateAgreement;
    }
    
  

    public static double getKappa(Integer[] ratingsA, Integer[] ratingsB, Integer... categories) {
        if (ratingsA.length != ratingsB.length) {
            throw new IllegalArgumentException("Rating vectors need to be of equal size.");
        }
        
        if (Arrays.equals(ratingsA,ratingsB)) {
        	return 1.0;
        }
    
        ConfusionMatrix confMatrix = new ConfusionMatrix(ratingsA, ratingsB, categories);

        int nrofRatings = categories.length;
        int nrofScoredItems = ratingsA.length;

        FrequencyDistribution<Integer> freqDistA = new FrequencyDistribution<Integer>(Arrays.asList(ratingsA));
        FrequencyDistribution<Integer> freqDistB = new FrequencyDistribution<Integer>(Arrays.asList(ratingsB));
        
        double numerator = 0.0;
        double denominator = 0.0;

        for (int outerCategory : categories) {
            for (int innerCategory : categories) {
                int nMinusOne = nrofRatings - 1;
                int distance = outerCategory - innerCategory;
                double weight = (double) (distance*distance) / (nMinusOne*nMinusOne);
                
                double expectedCount = (double) (freqDistA.getCount(outerCategory) * freqDistB.getCount(innerCategory)) / nrofScoredItems;
                numerator += weight * confMatrix.getElement(outerCategory, innerCategory) / nrofScoredItems;
                denominator += weight * expectedCount / nrofScoredItems;
            }
        }
        return 1.0 - numerator / denominator;
    }
}
