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

import org.junit.Test;

import de.unidue.ltl.evaluation.measures.significance.ChiSquare;

public class ChiSquareTestUnitTest {

	@Test
	public void chiSquareTest() {
		assertEquals(0.005, ChiSquare.getPvalue(8.0, 1), 0.0001);
		assertEquals(0.05, ChiSquare.getPvalue(4.0, 1), 0.0001);
		assertEquals(1.00, ChiSquare.getPvalue(0.01, 1), 0.0001);
	}

}
