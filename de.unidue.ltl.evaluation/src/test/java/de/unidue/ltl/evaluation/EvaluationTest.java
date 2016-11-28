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

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Test;

public class EvaluationTest{

	@Test
	public void entryTest(){
		Collection<EvaluatioEntry> entries= new ArrayList<>();
		entries.add(new EvaluatioEntry("A", "B"));
		entries.add(new EvaluatioEntry("A", "A"));
		Evaluation evaluation= new Evaluation(entries);
		assertEquals(2,evaluation.getEntries().size());
	}
	
	@Test
	public void addEntryTest(){
		Evaluation evaluation= new Evaluation();
		evaluation.register("A", "B");
		evaluation.register("B", "B");
		assertEquals(2,evaluation.getEntries().size());
	}
}
