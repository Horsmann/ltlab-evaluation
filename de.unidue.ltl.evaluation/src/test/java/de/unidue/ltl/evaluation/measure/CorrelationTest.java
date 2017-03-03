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
package de.unidue.ltl.evaluation.measure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationMetaData;
import de.unidue.ltl.evaluation.measure.correlation.PearsonCorrelation;
import de.unidue.ltl.evaluation.measure.correlation.SpearmanCorrelation;

public class CorrelationTest {
	
	@Test
	public void correlationTest(){
		@SuppressWarnings("unchecked")
        EvaluationData<Double> data = new EvaluationData<>(Mockito.mock(EvaluationMetaData.class));
		data.register(1.0, 2.0);
		data.register(2.0, 3.0);
		data.register(3.0, 4.0);
		data.register(4.0, 5.0);
		
		assertEquals(1.0, new PearsonCorrelation(data).getCorrelation(), 0.001);
		assertEquals(1.0, new SpearmanCorrelation(data).getCorrelation(), 0.001);
	}
}
