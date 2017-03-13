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
package de.unidue.ltl.evaluation.measure.categorial;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.EvaluationData;

public class Precision<T>
    extends CategoricalMeasure<T>
{
    Map<T, Double> precisionMeasures = new HashMap<>();
    double macro_precision;
    double micro_precision;
    double weighted_precision;

    private boolean didCalculate = false;

    public Precision(EvaluationData<T> data)
    {
        super(data);
    }

    void calculate()
    {

        if (didCalculate) {
            return;
        }

        Set<T> labels = getDistinctLabels(data);

        double precision_sum = 0d;
        int tp_sum = 0;
        int fp_sum = 0;

        for (T label : labels) {
            
            Category cbv = getCategoryBaseValues(label);

            double precision = (double) cbv.tp / (cbv.tp + cbv.fp);
            precision_sum += precision;
            weighted_precision += precision * (1.0 * (cbv.tp + cbv.fn) / (cbv.tp + cbv.fp + cbv.tn + cbv.fn));

            tp_sum += cbv.tp;
            fp_sum += cbv.fp;
            precisionMeasures.put(label, precision);
        }

        macro_precision = precision_sum / labels.size();
        micro_precision = (double) tp_sum / (tp_sum + fp_sum);

        didCalculate = true;
    }

    public double getPrecisionForLabel(T label)
    {
        if (!didCalculate) {
            calculate();
        }
        verifyLabelKnown(label, precisionMeasures);
        return precisionMeasures.get(label);
    }

    public double getMacroPrecision()
    {
        if (!didCalculate) {
            calculate();
        }
        return macro_precision;
    }

    public double getMicro_precision()
    {
        if (!didCalculate) {
            calculate();
        }
        return micro_precision;
    }

}
