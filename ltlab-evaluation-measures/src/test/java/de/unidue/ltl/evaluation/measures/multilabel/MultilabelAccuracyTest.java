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
package de.unidue.ltl.evaluation.measures.multilabel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class MultilabelAccuracyTest {
	
	EvaluationData<Integer> data;

	@Before
	public void setup(){
		data = new EvaluationData<>();
		data.registerMultiLabel(new Integer[] {1, 0, 1, 0}, new Integer[] {1, 0, 0, 1});
		data.registerMultiLabel(new Integer[] {0 ,1, 0, 1}, new Integer[] {0, 1, 0, 1});
		data.registerMultiLabel(new Integer[] {1, 0, 0, 1}, new Integer[] {1, 0, 0, 1});
		data.registerMultiLabel(new Integer[] {0, 1, 1, 0}, new Integer[] {0, 1, 0, 0});
		data.registerMultiLabel(new Integer[] {1, 0, 0, 0}, new Integer[] {1, 0, 0, 1});
	}
	
	@Test
	public void runTest(){
		MultilabelAccuracy mla = new MultilabelAccuracy(data);
		assertEquals(0.67000000, mla.getResult(), 0.01);
	}
	
}
