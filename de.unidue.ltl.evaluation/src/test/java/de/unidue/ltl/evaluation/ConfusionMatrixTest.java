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
    public void getValues()
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
    }

}
