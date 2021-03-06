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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class ConfusionMatrixTest
{
    @Test
    public void testConfusionMatrix()
    {
        EvaluationData<String> data = new EvaluationData<String>();
        data.register("A", "B");
        data.register("A", "A");
        data.register("A", "A");
        data.register("B", "B");
        data.register("B", "B");
        data.register("B", "B");
        data.register("B", "B");
        data.register("B", "A");
        data.register("B", "A");
        data.register("B", "A");
        
        ConfusionMatrix<String> matrix = new ConfusionMatrix<>(data);

        System.out.println(matrix.toString());
        
        assertEquals(2, matrix.getLabels().size());
        assertTrue(matrix.getLabels().contains("A"));
        assertTrue(matrix.getLabels().contains("B"));
        
        assertEquals(2, matrix.getTruePositives("A"));
        assertEquals(4, matrix.getTrueNegatives("A"));        
        assertEquals(3, matrix.getFalsePositives("A"));
        assertEquals(1, matrix.getFalseNegatives("A"));
        
        assertEquals(4, matrix.getTruePositives("B"));
        assertEquals(2, matrix.getTrueNegatives("B"));        
        assertEquals(1, matrix.getFalsePositives("B"));
        assertEquals(3, matrix.getFalseNegatives("B"));
    
        int[][] array2d = matrix.getTwoDimensionalArray();
        assertEquals(2, array2d.length);
        assertEquals(2, array2d[0].length);
        assertEquals(2, array2d[0][0]);
        assertEquals(1, array2d[0][1]);
        assertEquals(3, array2d[1][0]);
        assertEquals(4, array2d[1][1]);
        
        
        data = new EvaluationData<String>();
        data.register("Z", "B");
        data.register("A", "A");
        data.register("A", "A");
        data.register("Z", "B");
        data.register("B", "B");
        data.register("B", "Z");
        data.register("B", "B");
        data.register("B", "A");
        data.register("B", "Z");
        data.register("B", "A");
        
        ConfusionMatrix<String> matrix2 = new ConfusionMatrix<>(data);

        System.out.println(matrix2.toText());
        
        assertEquals(2, matrix2.getTruePositives("A"));
        assertEquals(2, matrix2.getFalsePositives("A"));
        assertEquals(0, matrix2.getFalseNegatives("A"));
        assertEquals(6, matrix2.getTrueNegatives("A")); 
    }
    
    @Test
    public void testConfusionMatrixLongLabels()
    {
        EvaluationData<String> data = new EvaluationData<String>();
        data.register("diamond", "copper");
        data.register("gold", "gold");
        data.register("gold", "gold");
        data.register("gold", "gold");
        data.register("gold", "gold");
        data.register("gold", "copper");
        data.register("diamond", "diamond");
        data.register("copper", "diamond");
        data.register("copper", "diamond");
        data.register("copper", "copper");
        
        ConfusionMatrix<String> cm = new ConfusionMatrix<>(data);
        System.out.println(cm.toText());
        
        assertEquals(2, cm.getNumberOfConfusions("copper", "diamond"));
    }
}
