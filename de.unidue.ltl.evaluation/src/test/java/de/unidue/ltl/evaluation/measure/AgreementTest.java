/*******************************************************************************
 * Copyright 2016
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
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.agreement.CohenKappa;
import de.unidue.ltl.evaluation.measure.agreement.KrippendorffAlpha;
import de.unidue.ltl.evaluation.measure.util.AgreementMeasureUtil;

public class AgreementTest {
	
	@Test
	public void correlationTest(){
		Collection<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		
		Map<String, EvaluationResult> results = AgreementMeasureUtil.computeAgreementResults(entries);
		assertEquals(6, results.size()); 
		
		assertEquals(0.5, results.get(CohenKappa.COHEN_KAPPA).getResult(), 0.001);
		assertEquals(0.533, results.get(KrippendorffAlpha.KRIPPENDORFF_ALPHA).getResult(), 0.001);
				
		EvaluationMeasure<String> kappa = new CohenKappa(entries);
		assertEquals("CohenKappa", kappa.getName());
		
		Map<String, EvaluationResult> kappaResults = kappa.calculate();
		assertEquals(6, kappaResults.size()); 
		
		assertEquals(0.5, kappaResults.get(CohenKappa.COHEN_KAPPA).getResult(), 0.001);
		assertEquals(0.533, kappaResults.get(KrippendorffAlpha.KRIPPENDORFF_ALPHA).getResult(), 0.001);
	}
}
