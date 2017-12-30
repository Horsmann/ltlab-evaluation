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

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.measure.categorial.BinaryLogLoss;

public class LossTest {
	
	@Test
	public void binaryLogLoss(){
        EvaluationData<Double> data1 = new EvaluationData<>();
		data1.register(1.0, 0.5);
		assertEquals(0.69315, new BinaryLogLoss<Double>(data1).getAgreement(), 0.00001);
		
        EvaluationData<Double> data2 = new EvaluationData<>();
		data2.register(1.0, 0.9);
		assertEquals(0.10536, new BinaryLogLoss<Double>(data2).getAgreement(), 0.00001);

        EvaluationData<Double> data3 = new EvaluationData<>();
		data3.register(1.0, 0.1);
		assertEquals(2.3026, new BinaryLogLoss<Double>(data3).getAgreement(), 0.0001);

        EvaluationData<Double> data4 = new EvaluationData<>();
		data4.register(1.0, 0.9);
		data4.register(1.0, 0.5);
		data4.register(1.0, 0.1);
		data4.register(0.0, 0.1);
		data4.register(0.0, 0.2);
		data4.register(0.0, 0.3);
		assertEquals(0.4027, new BinaryLogLoss<Double>(data4).getAgreement(), 0.0001);
	}
}