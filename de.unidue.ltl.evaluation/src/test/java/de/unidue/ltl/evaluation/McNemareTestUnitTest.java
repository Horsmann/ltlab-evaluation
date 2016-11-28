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

package de.unidue.ltl.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unidue.ltl.evaluation.evaluationComparison.McNemarTest;

public class McNemareTestUnitTest {

	@Test
	public void mcnemareSignificanceTest() {
		Evaluation<String> evaluation1 = new Evaluation<String>();
		for (int i = 0; i < 8; i++) {
			evaluation1.register("A", "A");
		}
		for (int i = 0; i < 16; i++) {
			evaluation1.register("A", "B");
		}

		Evaluation<String> evaluation2 = new Evaluation<String>();
		for (int i = 0; i < 11; i++) {
			evaluation2.register("A", "A");
		}
		for (int i = 0; i < 5; i++) {
			evaluation2.register("A", "B");
		}

		McNemarTest<String> mcNemare = new McNemarTest<String>(evaluation1, evaluation2);
		assertEquals(5.25, mcNemare.computeSignificance(),0.001);
	}

}
