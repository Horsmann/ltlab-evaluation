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
package de.unidue.ltl.evaluation.measures.categorical.multi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.measures.categorial.multi.ExactMatchRatio;

public class ExactMatchRatioTest {
	
	EvaluationData<String> data;

	@Before
	public void setup(){
		data = new EvaluationData<>();
		data.registerMultiLabel(new String[] {"A", "B", "C"}, new String[] {"A", "B", "C"});
		data.registerMultiLabel(new String[] {"A", "B", "C"}, new String[] {"A", "C", "C"});
		data.registerMultiLabel(new String[] {"B", "B", "C"}, new String[] {"B", "B", "C"});
	}
	
	@Test
	public void runTest(){
		ExactMatchRatio<String> emr = new ExactMatchRatio<String>(data);
		assertEquals(0.6666, emr.getResult(), 0.0001);
	}
	
}
