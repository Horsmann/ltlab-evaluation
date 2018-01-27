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
package de.unidue.ltl.evaluation.measures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class AccuracyTest {
	
	@Test
	public void accuracyTest(){
		
		EvaluationData<String> data = new EvaluationData<>();
		data.register("A", "B");
		data.register("B", "B");
		data.register("A", "A");
		data.register("A", "A");
		
		assertEquals(0.75, new Accuracy<>(data).getResult(), 0.001);
		assertEquals(3, new Accuracy<>(data).getCorrect());
		assertEquals(1, new Accuracy<>(data).getIncorrect());
		assertEquals(4, new Accuracy<>(data).getNumberInstances());
	}

}
