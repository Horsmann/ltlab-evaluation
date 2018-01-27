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
package de.unidue.ltl.evaluation.visualization;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.core.EvaluationMetaData;
import de.unidue.ltl.evaluation.testing.TestUtils;

public class MetaDataVisualizationTest
{
    private EvaluationMetaData<String> meta;

    @Before
    public void setup(){
        Collection<EvaluationEntry<String>> exampleCategorial = TestUtils.getExampleCategorial();
        meta = new EvaluationMetaData<>("meta", exampleCategorial);
        new File("target/", MetaDataVisualizer.PREDICTED_FILE).delete();
        new File("target/", MetaDataVisualizer.GOLD_FILE).delete();
    }
    
    @Test
    public void testPlotCreation() throws Exception{
        
        assertTrue(!new File("target/", MetaDataVisualizer.PREDICTED_FILE).exists());
        assertTrue(!new File("target/", MetaDataVisualizer.GOLD_FILE).exists());
        
        MetaDataVisualizer.getPieChart(meta, "target/");
        
        assertTrue(new File("target/", MetaDataVisualizer.PREDICTED_FILE).exists());
        assertTrue(new File("target/", MetaDataVisualizer.GOLD_FILE).exists());
    }
}
