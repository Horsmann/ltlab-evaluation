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

package de.unidue.ltl.evaluation.measures;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;

public class Accuracy<T>
    extends EvaluationMeasure<T>
{
    boolean didCalculate = false;
    long numberInstances;
    long correct;
    long incorrect;

    public Accuracy(EvaluationData<T> data)
    {
        super(data);
    }

    void calculate()
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

    public long getNumberInstances()
    {
        if (!didCalculate) {
            calculate();
        }
        return numberInstances;
    }

    public long getCorrect()
    {
        if (!didCalculate) {
            calculate();
        }
        return correct;
    }

    public long getIncorrect()
    {
        if (!didCalculate) {
            calculate();
        }
        return incorrect;
    }

	@Override
	public double getResult() {
        if (!didCalculate) {
            calculate();
        }
        return (double) correct / numberInstances;
	}
}
