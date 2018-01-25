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
package de.unidue.ltl.evaluation.significance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class McNemarBulkTest
{

    private List<EvaluationData<String>> evalObjects;

    public McNemarBulkTest()
    {
        evalObjects = new ArrayList<EvaluationData<String>>();
    }

    public McNemarBulkTest(ArrayList<EvaluationData<String>> evaluations)
    {
        evalObjects = evaluations;
    }

    public void register(EvaluationData<String> eval)
    {
        evalObjects.add(eval);
    }

    public Map<String, Map<String, Double>> computeBulkTable(McNemarType type)
        throws Exception
    {
        Map<String, Map<String, Double>> resultTable = new HashMap<String, Map<String, Double>>();
        for (EvaluationData<String> eval1 : this.evalObjects) {
            resultTable.put(eval1.getId().toString(), new HashMap<String, Double>());
            for (EvaluationData<String> eval2 : this.evalObjects) {
                Map<String, Double> map = resultTable.get(eval1.getId().toString());
                String id = eval2.getId().toString();
                double computeSignificance = McNemarTest.computeSignificance(eval1, eval2, type);
                double pvalue = ChiSquare.getPvalue(computeSignificance, 1);
                map.put(id, pvalue);
            }
        }
        return resultTable;
    }

}
