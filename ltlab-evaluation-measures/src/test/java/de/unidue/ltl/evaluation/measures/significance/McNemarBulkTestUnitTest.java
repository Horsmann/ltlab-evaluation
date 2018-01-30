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
package de.unidue.ltl.evaluation.measures.significance;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.measure.significance.McNemarBulkTest;
import de.unidue.ltl.evaluation.measure.significance.McNemarType;

public class McNemarBulkTestUnitTest {

	@Test
	public void mcnemareSignificanceTest() throws Exception {
		EvaluationData<String> evaluation1 = new EvaluationData<String>();
		EvaluationData<String> evaluation2 = new EvaluationData<String>();
		
		
		for(int i=0; i< 8; i++){
			evaluation1.register("A", "A");
			evaluation2.register("A", "A");
		}
		
		for(int i=0; i< 11; i++){
			evaluation1.register("A", "B");
			evaluation2.register("A", "B");
		}
		
		for(int i=0; i< 16; i++){
			evaluation1.register("A", "B");
			evaluation2.register("A", "A");
		}
		
		for(int i=0; i< 5; i++){
			evaluation1.register("A", "A");
			evaluation2.register("A", "B");
		}
	
		McNemarBulkTest test = new McNemarBulkTest();
		test.register(evaluation1);
		test.register(evaluation2);
		Map<String, Map<String, Double>> table = test.computeBulkTable(McNemarType.YATES);
		System.out.println("McNemarBulk p-values");
		for (String row : table.keySet()) {
			for (String column : table.get(row).keySet()) {
				System.out.print(table.get(row).get(column)+"\t");
			}
			System.out.print("\n");
		}
		assertEquals(0.025, table.get(evaluation1.getId().toString())
				.get(evaluation2.getId().toString()), 0.001);
		
	}
	
	
}
