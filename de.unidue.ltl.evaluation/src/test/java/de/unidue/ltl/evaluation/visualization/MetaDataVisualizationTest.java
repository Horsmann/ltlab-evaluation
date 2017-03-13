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
package de.unidue.ltl.evaluation.visualization;

import java.util.Collection;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationMetaData;
import de.unidue.ltl.evaluation.MetaVisualizer;
import de.unidue.ltl.evaluation.util.TestUtils;

public class MetaDataVisualizationTest
{
    @Test
    public void testPlotCreation() throws Exception{
        Collection<EvaluationEntry<String>> exampleCategorial = TestUtils.getExampleCategorial();
        EvaluationMetaData<String> meta = new EvaluationMetaData<>("meta", exampleCategorial);
        MetaVisualizer.getPieChart(meta, "target/");
        
    }
}
