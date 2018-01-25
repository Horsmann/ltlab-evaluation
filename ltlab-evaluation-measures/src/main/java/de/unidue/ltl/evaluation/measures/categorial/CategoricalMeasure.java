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
package de.unidue.ltl.evaluation.measures.categorial;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public abstract class CategoricalMeasure<T>
    extends EvaluationMeasure<T>
{

    public CategoricalMeasure(EvaluationData<T> data)
    {
        super(data);
    }

    protected Category getCategoryBaseValues(T category)
    {
        int tp = 0;
        int fp = 0;
        int fn = 0;
        int tn = 0;

        for (EvaluationEntry<T> entry : data) {
            T gold = entry.getGold();
            T pred = entry.getPredicted();
            if (gold.equals(category)) {
                if (gold.equals(pred)) {
                    tp++;
                }
                else {
                    fn++;
                }
            }
            else {
                if (pred.equals(category)) {
                    fp++;
                }
                else {
                    tn++;
                }
            }
        }

        return new Category(tp, fp, fn, tn);
    }

    protected Set<T> getDistinctLabels(EvaluationData<T> data)
    {
        Set<T> labels = new HashSet<T>();
        for (EvaluationEntry<T> entry : data) {
            labels.add(entry.getGold());
            labels.add(entry.getPredicted());
        }
        return labels;
    }

    protected void verifyLabelKnown(T label, Map<T, Double> m)
    {
        if (!m.keySet().contains(label)) {
            throw new IllegalArgumentException("The label [" + label + "] is unknown");
        }
    }

}
