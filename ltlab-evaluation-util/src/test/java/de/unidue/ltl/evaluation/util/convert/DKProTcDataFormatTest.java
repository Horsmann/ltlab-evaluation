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
	
	String labelA = "comp.graphics";
	String labelB = "alt.atheism";
	
	@Test
	public void testDocumentModeDataFormat() throws Exception{
		EvaluationData<String> data = DKProTcDataFormatConverter
				.convertId2Outcome(new File("src/test/resources/DKProTC/documentModeID2outcome.txt"));
		
		assertEquals(8, data.size());
		
		//values
		assertEquals(labelB, data.get(0).getGold());
		assertEquals(labelA, data.get(0).getPredicted());
		
		assertEquals(labelB, data.get(1).getGold());
		assertEquals(labelB, data.get(1).getPredicted());
		
		assertEquals(labelB, data.get(2).getGold());
		assertEquals(labelA, data.get(2).getPredicted());
		
		//names
		assertEquals("alt.atheism/53068.txt", data.get(0).getName());
		assertEquals("alt.atheism/53257.txt", data.get(1).getName());
		assertEquals("alt.atheism/53260.txt", data.get(2).getName());
		
	}

}
