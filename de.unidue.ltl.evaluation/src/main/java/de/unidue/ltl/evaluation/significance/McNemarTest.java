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
package de.unidue.ltl.evaluation.significance;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class McNemarTest
{

    public static double computeSignificance(EvaluationData<String> e1, EvaluationData<String> e2,
            String correctionType)
                throws Exception
    {

        double sample1negative = 0;
        double sample2negative = 0;

        if (e1.size() != e2.size()) {
            System.err.println("Lists must have the same length!");
            System.exit(-1);
        }

        for (int i = 0; i < e1.size(); i++) {
            EvaluationEntry<String> entry1 = e1.get(i);
            EvaluationEntry<String> entry2 = e2.get(i);
            if (positive(entry1) && !positive(entry2)) {
                sample1negative++;
            }
            if (!positive(entry1) && positive(entry2)) {
                sample2negative++;
            }
        }

        if (sample1negative == 0 && sample2negative == 0) {
            return 0.0;
        }
        double mcNemarYates = Math.pow(Math.abs(sample2negative - sample1negative) - 0.5, 2)
                / (sample1negative + sample2negative);
        double mcNemarEdwards = Math.pow(Math.abs(sample2negative - sample1negative) - 1, 2)
                / (sample1negative + sample2negative);

        if (correctionType.equals("Yates")) {
            return mcNemarYates;
        }
        else if (correctionType.equals("Edwards")) {
            return mcNemarEdwards;
        }
        else {
            throw new Exception(
                    "Unknown correction Type for McNemar test. Only Yates and Edwards known.");
        }
    }

    private static boolean positive(EvaluationEntry<String> entry)
    {
        return entry.getGold().equals(entry.getPredicted());
    }
}