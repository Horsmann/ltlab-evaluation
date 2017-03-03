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
package de.unidue.ltl.evaluation.comparison;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class DescriptiveComparison<T>
{
    private EvaluationData<T> evaluation1;
    private EvaluationData<T> evaluation2;
    private int overlap;
    private int dicrepancy;
    private int firstPositiveThenPositive;
    private int firstPositiveThenNegative;
    private int firstNegativeThenPositive;
    private int firstNegativeThenNegative;

    public DescriptiveComparison(EvaluationData<T> evaluation1, EvaluationData<T> evaluation2)
        throws Exception
    {
        this.evaluation1 = evaluation1;
        this.evaluation2 = evaluation2;
        this.overlap = 0;
        this.dicrepancy = 0;
        this.firstPositiveThenPositive = 0;
        this.firstPositiveThenNegative = 0;
        this.firstNegativeThenPositive = 0;
        this.firstNegativeThenNegative = 0;

        calculateMeasures();
    }

    private void calculateMeasures()
        throws Exception
    {
        if (evaluation1.size() != evaluation2.size()) {
            throw new Exception("Evaluations don't have equal size: " + evaluation1.size() + "/"
                    + evaluation2.size());
        }
        int i = 0;
        for (EvaluationEntry<T> entry1 : evaluation1) {
            boolean positiveEntry1 = positive(entry1);
            boolean positiveEntry2 = positive(evaluation2.get(i));
            if (positiveEntry1 && positiveEntry2) {
                overlap++;
                firstPositiveThenPositive++;
            }
            if (!positiveEntry1 && positiveEntry2) {
                dicrepancy++;
                firstNegativeThenPositive++;
            }
            if (positiveEntry1 && !positiveEntry2) {
                dicrepancy++;
                firstPositiveThenNegative++;
            }
            if (!positiveEntry1 && !positiveEntry2) {
                overlap++;
                firstNegativeThenNegative++;
            }
            i++;
        }
    }

    private boolean positive(EvaluationEntry<T> entry)
    {
        if (entry.getGold().equals(entry.getPredicted())) {
            return true;
        }
        return false;
    }

    public int getOverlap()
    {
        return overlap;
    }

    public int getDicrepancy()
    {
        return dicrepancy;
    }

    public int getFirstPositiveThenPositive()
    {
        return firstPositiveThenPositive;
    }

    public int getFirstPositiveThenNegative()
    {
        return firstPositiveThenNegative;
    }

    public int getFirstNegativeThenNegative()
    {
        return firstNegativeThenNegative;
    }

    public int getFirstNegativeThenPositive()
    {
        return firstNegativeThenPositive;
    }
}
