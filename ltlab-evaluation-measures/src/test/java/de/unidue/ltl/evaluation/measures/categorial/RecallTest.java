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
package de.unidue.ltl.evaluation.measures.categorial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.measures.categorial.Recall;
import de.unidue.ltl.evaluation.testing.TestUtils;

public class RecallTest {
	
	@Test
	public void recallTest(){
		EvaluationData<String> data = new EvaluationData<>(TestUtils.getExampleCategorial());
		assertEquals(0.6, new Recall<>(data).getRecallForLabel("A"), 0.001);
        assertEquals(0.833, new Recall<>(data).getRecallForLabel("B"), 0.001);
        assertEquals(0.625, new Recall<>(data).getRecallForLabel("C"), 0.001);
        assertEquals(0.686, new Recall<>(data).getMacroRecall(), 0.001);
	}
	

}
