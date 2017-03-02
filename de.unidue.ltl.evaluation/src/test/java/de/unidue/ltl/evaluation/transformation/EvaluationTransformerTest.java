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
package de.unidue.ltl.evaluation.transformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class EvaluationTransformerTest {

	@Test
	public void deleteLabelsTest() 
	{
		
		Evaluation<String> eval= new Evaluation<>();
		
		eval.register("A", "B");
		eval.register("B", "B");
		eval.register("A", "C");
		eval.register("C", "A");
		eval.register("C", "C");
		
				
		Evaluation<String> transformed  = EvaluationTransformer.deleteLabels(eval, "C" );
						
		Collection<EvaluationEntry<String>> entries = transformed.getEntries();
		assertEquals(2, entries.size());
	
		for (EvaluationEntry<String> entry : entries) {
			assertNotEquals("C", entry.getGold());
			assertNotEquals("C", entry.getPredicted());
		}
	}
	
	@Test
	public void changeGoldLabelTest() 
	{	
		Evaluation<String> eval= new Evaluation<>();
		
		eval.register("A", "B");
		eval.register("B", "B");
		eval.register("A", "C");
		eval.register("C", "A");
		eval.register("C", "C");
		
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("A", "B");

		Evaluation<String> transformed  = EvaluationTransformer.changeGoldLabel(eval, mapping);
		
		Collection<EvaluationEntry<String>> entries = transformed.getEntries();
		assertEquals(5, entries.size());
		
		System.out.println(transformed.getConfusionMatrix());

		for (EvaluationEntry<String> entry : entries) {
			assertNotEquals("A", entry.getGold());
		}		
	}
	
	@Test
	public void changePredictedLabelTest() 
	{	
		Evaluation<String> eval= new Evaluation<>();
		
		eval.register("A", "B");
		eval.register("B", "B");
		eval.register("A", "C");
		eval.register("C", "A");
		eval.register("C", "C");
		
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("A", "B");

		Evaluation<String> transformed  = EvaluationTransformer.changePredictedLabel(eval, mapping);
		
		Collection<EvaluationEntry<String>> entries = transformed.getEntries();
		assertEquals(5, entries.size());
		
		System.out.println(transformed.getConfusionMatrix());

		for (EvaluationEntry<String> entry : entries) {
			assertNotEquals("A", entry.getPredicted());
		}		
	}

}
