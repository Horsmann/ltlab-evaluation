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

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.measure.agreement.CohenKappa;
import de.unidue.ltl.evaluation.measure.agreement.KrippendorffAlpha;

public class AgreementTest {
	
	@Test
	public void agreementTest(){
		Collection<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		EvaluationData<String> data = new EvaluationData<>(entries);
		
		assertEquals(0.5, new CohenKappa<String>(data).getAgreement(), 0.001);
		assertEquals(0.533, new KrippendorffAlpha<String>(data).getAgreement(), 0.001);
				
	}
}
