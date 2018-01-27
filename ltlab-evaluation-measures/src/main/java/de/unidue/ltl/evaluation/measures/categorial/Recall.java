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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class Recall<T>
    extends CategoricalMeasure<T>
{
    Map<T, Double> recallMeasures = new HashMap<>();
    double macro_recall;
    double micro_recall;

    private boolean didCalculate = false;
    private double weighted_recall;

    public Recall(EvaluationData<T> data)
    {
        super(data);
    }

    void calculate()
    {

        if (didCalculate) {
            return;
        }

        Set<T> labels = getDistinctLabels(data);

        double recall_sum = 0d;
        int tp_sum = 0;
        int fn_sum = 0;

        for (T label : labels) {

            Category cbv = getCategoryBaseValues(label);
            
            // System.out.println(category+"\t"+tp+"\t"+fp+"\t"+fn);
            double recall = (double) cbv.tp / (cbv.tp + cbv.fn);
            recall_sum += recall;
            weighted_recall += recall * (1.0 * (cbv.tp + cbv.fn) / (cbv.tp + cbv.fp + cbv.tn + cbv.fn));

            tp_sum += cbv.tp;
            fn_sum += cbv.fn;
            recallMeasures.put(label, recall);
        }

        macro_recall = recall_sum / labels.size();

        micro_recall = (double) tp_sum / (tp_sum + fn_sum);

        didCalculate = true;
    }

    public double getRecallForLabel(T label)
    {
        if (!didCalculate) {
            calculate();
        }
        verifyLabelKnown(label, recallMeasures);
        return recallMeasures.get(label);
    }

    public double getMacroRecall()
    {
        if (!didCalculate) {
            calculate();
        }
        return macro_recall;
    }

    public double getMicroRecall()
    {
        if (!didCalculate) {
            calculate();
        }
        return micro_recall;
    }

}
