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

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfusionMatrixTest
{
    @Test
    public void testConfusionMatrix()
    {
        ConfusionMatrix<String> matrix = new ConfusionMatrix<>();
        matrix.register("A", "B");
        matrix.register("A", "A");
        matrix.register("A", "A");
        matrix.register("B", "B");
        matrix.register("B", "B");
        matrix.register("B", "B");
        matrix.register("B", "B");
        matrix.register("B", "A");
        matrix.register("B", "A");
        matrix.register("B", "A");

        System.out.println(matrix.toString());
        
        assertEquals(2, matrix.getTruePositives("A"));
        assertEquals(4, matrix.getTrueNegatives("A"));        
        assertEquals(3, matrix.getFalsePositives("A"));
        assertEquals(1, matrix.getFalseNegative("A"));
        
        assertEquals(4, matrix.getTruePositives("B"));
        assertEquals(2, matrix.getTrueNegatives("B"));        
        assertEquals(1, matrix.getFalsePositives("B"));
        assertEquals(3, matrix.getFalseNegative("B"));
    
        ConfusionMatrix<String> matrix2 = new ConfusionMatrix<>();
        matrix2.register("Z", "B");
        matrix2.register("A", "A");
        matrix2.register("A", "A");
        matrix2.register("Z", "B");
        matrix2.register("B", "B");
        matrix2.register("B", "Z");
        matrix2.register("B", "B");
        matrix2.register("B", "A");
        matrix2.register("B", "Z");
        matrix2.register("B", "A");

        System.out.println(matrix2.toString());
        
        assertEquals(2, matrix2.getTruePositives("A"));
        assertEquals(2, matrix2.getFalsePositives("A"));
        assertEquals(0, matrix2.getFalseNegative("A"));
        assertEquals(6, matrix2.getTrueNegatives("A")); 
    }
    
    
}
