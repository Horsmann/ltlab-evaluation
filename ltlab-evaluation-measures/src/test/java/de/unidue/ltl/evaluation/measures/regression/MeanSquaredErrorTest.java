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
package de.unidue.ltl.evaluation.measures.regression;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class MeanSquaredErrorTest {
	
	EvaluationData<Double> data;

	@Before
	public void setup(){
		data = new EvaluationData<>();
		data.register(43.6, 41.0);
		data.register(44.4, 43.8);
		data.register(45.2, 41.4);
		data.register(46.0, 45.0);
		data.register(46.8, 44.0);
	}
	
	@Test
	public void runTest(){
		MeanSquaredError mse = new MeanSquaredError(data);
		assertEquals(6.08, mse.getResult(), 0.0001);
	}
	
}
