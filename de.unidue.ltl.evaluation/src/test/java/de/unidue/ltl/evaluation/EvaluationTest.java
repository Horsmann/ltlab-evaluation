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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

import de.unidue.ltl.evaluation.measure.EvaluationMeasure;

public class EvaluationTest{

	@Test
	public void entryTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		Evaluation<String> evaluation= new Evaluation<String>(entries);
		assertEquals(2,evaluation.getEntries().size());
	}
	
	@Test
	public void addEntryTest(){
		Evaluation<String> evaluation= new Evaluation<String>();
		evaluation.register("A", "B");
		evaluation.register("B", "B");
		assertEquals(2,evaluation.getEntries().size());
	}
	
	@Test
	public void addMeasureTest(){
		Evaluation<String> evaluation= new Evaluation<String>();
		evaluation.register("A", "B");
		evaluation.register("B", "B");
		
		EvaluationMeasure mock = Mockito.mock(EvaluationMeasure.class);
		List<EvaluationResult> evalResults = new ArrayList<>();
		
		EvaluationResult r1 = Mockito.mock(EvaluationResult.class);
		Mockito.when(r1.getName()).thenReturn("Accuracy");
		evalResults.add(r1);
		
        Mockito.when(mock.calculate()).thenReturn(evalResults);
		
		evaluation.calculate(mock);
		Set<String> expectedMeasures = new HashSet<>();
		expectedMeasures.add("Accuracy");
		assertEquals(expectedMeasures,evaluation.getCalculatedMeasureNames());
		evaluation.calculate(mock);
		assertEquals(expectedMeasures,evaluation.getCalculatedMeasureNames());

	}
	
}
