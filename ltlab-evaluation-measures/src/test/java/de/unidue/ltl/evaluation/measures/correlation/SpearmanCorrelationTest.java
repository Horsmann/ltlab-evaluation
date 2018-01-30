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
package de.unidue.ltl.evaluation.measures.correlation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class SpearmanCorrelationTest {
	
	@Test
	public void correlationTest(){
        EvaluationData<Double> data = new EvaluationData<>();
		data.register(1.0, 2.0);
		data.register(2.0, 3.0);
		data.register(3.0, 4.0);
		data.register(4.0, 5.0);
		
		assertEquals(1.0, new SpearmanCorrelation(data).getResult(), 0.001);
	}
	
	@Test
	public void correlationTest2(){
        EvaluationData<Double> data = new EvaluationData<>();
		data.register(1.0, -1.0);
		data.register(2.0, -2.0);
		data.register(3.0, -3.0);
		data.register(4.0, -4.0);
		
		assertEquals(-0.99999, new SpearmanCorrelation(data).getResult(), 0.001);
	}
	
	@Test
	public void correlationTest3(){
        EvaluationData<Double> data = new EvaluationData<>();
        data.register(3.0, 1.0);
        data.register(2.0, 2.0);
        data.register(1.0, 3.0);
		
		assertEquals(-1.0, new SpearmanCorrelation(data).getResult(), 0.001);
	}
	
	@Test
	public void correlationTest4(){
        EvaluationData<Double> data = new EvaluationData<>();
        data.register(3.0, 1.0);
        data.register(2.0, 1.0);
        data.register(3.0, 3.0);
        data.register(4.0, 1.0);
		
		assertEquals(0.0, new SpearmanCorrelation(data).getResult(), 0.001);
	}
}
