/*******************************************************************************
 * Copyright 2016
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

package de.unidue.ltl.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;

public class ConfusionMatrix<T>
{
    Set<T> allLabels = new HashSet<>();
    ConditionalFrequencyDistribution<T, T> cfd = new ConditionalFrequencyDistribution<>();

    public void register(T gold, T predicted)
    {
        cfd.addSample(gold, predicted, 1);
        
        allLabels.add(gold);
        allLabels.add(predicted);
    }

    public String toString()
    {

        List<T> conditions = new ArrayList<T>(allLabels);
        Collections.sort(conditions, new Comparator<T>()
        {

            @Override
            public int compare(T o1, T o2)
            {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1.toString().compareTo(o2.toString());
            }
        });

        StringBuilder sb = new StringBuilder();
        
        sb.append("Gold (row) / Prediction (col)\n\n");
        conditions.forEach(x -> sb.append(String.format("%10s\t", x)));
        sb.append("\n");

        for (T key : conditions) {
            FrequencyDistribution<T> fd = cfd.getFrequencyDistribution(key);
            sb.append(key);
            boolean first = true;
            for (T t : conditions) {
                if (first) {
                    sb.append(String.format("%9s\t", fd.getCount(t)));
                    first = false;
                }
                else {
                    sb.append(String.format("%10s\t", fd.getCount(t)));
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
