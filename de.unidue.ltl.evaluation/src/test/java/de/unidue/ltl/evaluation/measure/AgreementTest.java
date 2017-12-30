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
import de.unidue.ltl.evaluation.measure.agreement.BennettS;
import de.unidue.ltl.evaluation.measure.agreement.CohenKappa;
import de.unidue.ltl.evaluation.measure.agreement.FleissKappa;
import de.unidue.ltl.evaluation.measure.agreement.KrippendorffAlpha;
import de.unidue.ltl.evaluation.measure.agreement.LinearlyWeightedKappa;
import de.unidue.ltl.evaluation.measure.agreement.QuadraticallyWeightedKappa;
import de.unidue.ltl.evaluation.measure.agreement.RandolphKappa;
import de.unidue.ltl.evaluation.measure.agreement.ScottPi;
import de.unidue.ltl.evaluation.util.TestUtils;

public class AgreementTest {
	
	@Test
	public void agreementTest(){

		EvaluationData<String> data = new EvaluationData<>();
		data.register("A", "B");
		data.register("B", "B");
		data.register("A", "A");
		data.register("A", "A");
		
		assertEquals(0.5, new CohenKappa<String>(data).getAgreement(), 0.001);
		assertEquals(0.533, new KrippendorffAlpha<String>(data).getAgreement(), 0.001);
				
	}
	
	@Test
    public void kappaTest(){
        EvaluationData<Double> entries = new EvaluationData<>(TestUtils.getExampleNumeric());
        
        assertEquals(0.3975, new LinearlyWeightedKappa<Double>(entries).getAgreement(), 0.001);
        assertEquals(0.3492, new QuadraticallyWeightedKappa<Double>(entries).getAgreement(), 0.001);
        assertEquals(0.4523, new FleissKappa<Double>(entries).getAgreement(), 0.001);
        assertEquals(0.4750, new RandolphKappa<Double>(entries).getAgreement(), 0.001);
        assertEquals(0.4523, new ScottPi<Double>(entries).getAgreement(), 0.001);
        assertEquals(0.4750, new BennettS<Double>(entries).getAgreement(), 0.001);
    }
}
