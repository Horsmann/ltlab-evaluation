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
import de.unidue.ltl.evaluation.measure.util.ScaleMeasureUtil;

public class CorrelationTest {
	
	@Test
	public void correlationTest(){
		Collection<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		entries.add(new EvaluationEntry<Double>(1.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 3.0));
		entries.add(new EvaluationEntry<Double>(3.0, 4.0));
		entries.add(new EvaluationEntry<Double>(4.0, 5.0));
		
		Map<String, EvaluationResult> results = ScaleMeasureUtil.computeScaleResults(entries);
		assertEquals(2, results.size()); 
		
		assertEquals(1.0, results.get(PearsonCorrelation.class.getSimpleName()).getResult(), 0.001);
		assertEquals(1.0, results.get(SpearmanCorrelation.class.getSimpleName()).getResult(), 0.001);
				
		EvaluationMeasure<Double> pearson = new PearsonCorrelation(entries);
		assertEquals(PearsonCorrelation.class.getSimpleName(), pearson.getName());
		
		Map<String, EvaluationResult> resultsPearson = pearson.calculate();

		assertEquals(2, resultsPearson.size()); 
		
		assertEquals(1.0, resultsPearson.get(PearsonCorrelation.class.getSimpleName()).getResult(), 0.001);
		assertEquals(1.0, resultsPearson.get(SpearmanCorrelation.class.getSimpleName()).getResult(), 0.001);
	}
}
