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
package de.unidue.ltl.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class EvaluationMetaDataTest
{

    EvaluationData<String> evaluation;
    EvaluationMetaData<String> meta;

    @Before
    public void setup()
    {
        Collection<EvaluationEntry<String>> entries = new ArrayList<>();
        entries.add(new EvaluationEntry<String>("A", "B"));
        entries.add(new EvaluationEntry<String>("A", "A"));
        evaluation = new EvaluationData<>(entries);
        meta = new EvaluationMetaData<>("name", evaluation);
    }

    @Test
    public void evaluationMetaDataNameTest()
    {
        assertNotNull(evaluation.getId().toString());
    }

    @Test
    public void evaluationMetaDataLabelTest()
    {
        assertEquals(StringUtils.join(Arrays.asList("A", "B"), " "),
                StringUtils.join(meta.getLabels(), " "));
    }

    @Test
    public void evaluationMetaDataLabelDistributionTest()
    {
        assertEquals(0, meta.getDistributionsPerLabelGold().get("B").intValue());
        assertEquals(1, meta.getDistributionsPerLabelPredicted().get("B").intValue());
        assertEquals(2, meta.getDistributionsPerLabelGold().get("A").intValue());
        assertEquals(1, meta.getDistributionsPerLabelPredicted().get("A").intValue());
    }

    @Test
    public void testStats(){
        assertTrue(meta.getStats() != null);
    }
}
