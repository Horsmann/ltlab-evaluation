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

import java.util.Collection;
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.util.TestUtils;

public class TestKappa {

	@Test
	public void kappaTest(){
		Collection<EvaluationEntry<Double>> entries = TestUtils.getExampleNumeric();

		assertEquals(100, entries.size());
		//	System.out.println(entries.size());

		CohensKappa kappa = new CohensKappa(entries);
		Map<String, EvaluationResult> results = kappa.calculate();
		assertEquals(1, results.size()); 

		EvaluationResult result = results.get(CohensKappa.class.getSimpleName());
		System.out.println("Kappa: "+result.getResult());
		assertEquals(0.4557, result.getResult(), 0.001);

		
		LinearlyWeightedKappa linWeightedKappa = new LinearlyWeightedKappa(entries);
		results = linWeightedKappa.calculate();
		
		result = results.get(LinearlyWeightedKappa.class.getSimpleName());
		System.out.println("Linearlyly Weighted Kappa: "+result.getResult());
		assertEquals(0.3975, result.getResult(), 0.001);
		
		QuadraticallyWeightedKappa weightedKappa = new QuadraticallyWeightedKappa(entries);
		results = weightedKappa.calculate();
		
		result = results.get(QuadraticallyWeightedKappa.class.getSimpleName());
		System.out.println("Quadraticaly Weighted Kappa: "+result.getResult());
		assertEquals(0.3492, result.getResult(), 0.001);

		

	}
}