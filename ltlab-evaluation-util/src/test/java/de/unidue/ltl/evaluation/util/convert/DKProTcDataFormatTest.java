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
package de.unidue.ltl.evaluation.util.convert;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class DKProTcDataFormatTest {

	@Test
	public void testDocumentModeDataFormat() throws Exception {
		String labelA = "comp.graphics";
		String labelB = "alt.atheism";

		EvaluationData<String> data = DKProTcDataFormatConverter
				.convertSingleLabelModeId2Outcome(new File("src/test/resources/DKProTC/singleLabelID2outcome.txt"));

		assertEquals(8, data.size());

		// values
		assertEquals(labelB, data.get(0).getGold());
		assertEquals(labelA, data.get(0).getPredicted());

		assertEquals(labelB, data.get(1).getGold());
		assertEquals(labelB, data.get(1).getPredicted());

		assertEquals(labelB, data.get(2).getGold());
		assertEquals(labelA, data.get(2).getPredicted());

		// names
		assertEquals("alt.atheism/53068.txt", data.get(0).getName());
		assertEquals("alt.atheism/53257.txt", data.get(1).getName());
		assertEquals("alt.atheism/53260.txt", data.get(2).getName());

	}

	@Test
	public void testRegressionModeDataFormat() throws Exception {
		EvaluationData<Double> data = DKProTcDataFormatConverter
				.convertRegressionModeId2Outcome(new File("src/test/resources/DKProTC/regressionID2outcome.txt"));

		assertEquals(50, data.size());

		assertEquals(1.285762, data.get(0).getPredicted(), 0.0001);
		assertEquals(1.0, data.get(0).getGold(), 0.0001);

		assertEquals(1.999316, data.get(1).getPredicted(), 0.0001);
		assertEquals(1.0, data.get(1).getGold(), 0.0001);

		assertEquals(4.10318, data.get(2).getPredicted(), 0.0001);
		assertEquals(7.0, data.get(2).getGold(), 0.0001);

		assertEquals(3.842336, data.get(3).getPredicted(), 0.0001);
		assertEquals(3.0, data.get(3).getGold(), 0.0001);

		// names
		assertEquals("Document\\ 0", data.get(0).getName());
		assertEquals("Document\\ 1", data.get(1).getName());
		assertEquals("Document\\ 10", data.get(2).getName());
		assertEquals("Document\\ 11", data.get(3).getName());

	}

	@Test
	public void testMultilabelModeDataFormat() throws Exception {
		EvaluationData<String> data = DKProTcDataFormatConverter
				.convertMultiLabelModeId2Outcome(new File("src/test/resources/DKProTC/multiLabelId2outcome.txt"));

		assertEquals(3, data.size());

		// Match prediction/gold value mapping
		assertTrue(isMultiLabelMatch(data.get(0).getPredictedMultiLabel().toArray(new String[0]),
				new String[] { "__grain" }));
		
		assertTrue(isMultiLabelMatch(data.get(1).getPredictedMultiLabel().toArray(new String[0]),
				new String[] { "__grain", "__crude", "__corn" }));
		
		assertTrue(isMultiLabelMatch(data.get(2).getPredictedMultiLabel().toArray(new String[0]),
				new String[] { "__grain", "__earn", "__corn" }));
		
		//names
		assertEquals("0", data.get(0).getName());
		assertEquals("1", data.get(1).getName());
		assertEquals("2", data.get(2).getName());
	}

	private boolean isMultiLabelMatch(String[] actual, String[] gold) {

		if (!(actual.length == gold.length)) {
			return false;
		}

		boolean equal = true;
		for (int i = 0; i < actual.length; i++) {
			if (!actual[i].equals(gold[i])) {
				equal = false;
				break;
			}
		}

		return equal;
	}
}