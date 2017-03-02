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

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.measure.EvaluationMeasureNeu;

public class Accuracy<T>
    extends EvaluationMeasureNeu<T>
{
    boolean didCalculate = false;
    long numberInstances;
    long correct;
    long incorrect;

    public Accuracy(EvaluationData<T> data)
    {
        super(data);
    }

    @Override
    public void calculate()
    {
        if (didCalculate) {
            return;
        }

        for (EvaluationEntry<T> e : data) {
            if (e.getGold().equals(e.getPredicted())) {
                correct++;
            }
            else {
                incorrect++;
            }
            numberInstances++;
        }

        didCalculate = true;
    }

    public double getAccuracy()
    {
        return (double) correct / numberInstances;
    }

    public long getNumberInstances()
    {
        return numberInstances;
    }

    public long getCorrect()
    {
        return correct;
    }

    public long getIncorrect()
    {
        return incorrect;
    }
}
