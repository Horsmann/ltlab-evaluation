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

public class RSquaredTest {
	
	EvaluationData<Double> data;

	@Before
	public void setup(){
		data = new EvaluationData<>();
		data.register(40.0, 40.88);
		data.register(35.0, 34.3);
		data.register(30.0, 33.36);
		data.register(32.0,29.6);
		data.register(19.0,23.02);
		data.register(26.0,23.02);
		data.register(24.0,22.08);
		data.register(22.0,17.38);
		data.register(18.0,17.38);
		data.register(6.0,10.8);
	}
	
	@Test
	public void runTest(){
		RSquared r2 = new RSquared(data);
		assertEquals(0.8797, r2.getResult(), 0.0001);
	}
	
}
