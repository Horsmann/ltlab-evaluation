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

package de.unidue.ltl.evaluation.measures.significance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.measures.significance.mcnemar.McNemar;
import de.unidue.ltl.evaluation.measures.significance.mcnemar.McNemarType;

public class McNemarTestUnitTest {

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
		
		assertEquals(5.25, McNemar.computeSignificance(evaluation1, evaluation2, McNemarType.YATES), 0.001);
		
	}

}
