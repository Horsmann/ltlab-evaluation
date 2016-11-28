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
import de.unidue.ltl.evaluation.measure.Accuracy;
import de.unidue.ltl.evaluation.measure.Precision;
import de.unidue.ltl.evaluation.measure.Recall;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class PRFTest {
	
	@Test
	public void prfTest(){
		Collection<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("B", "A"));
		entries.add(new EvaluationEntry<String>("B", "A"));
		entries.add(new EvaluationEntry<String>("B", "A"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		//entries.add(new EvaluationEntry<String>("B", "C"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		
		assertEquals(100, entries.size());
	//	System.out.println(entries.size());
		
		Map<String, EvaluationResult> results = CategorialMeasuresUtil.computeCategorialResults(entries);
		assertEquals(16, results.size()); 
		
		assertEquals(3, CategorialMeasuresUtil.listCategories(entries).size());
		
		EvaluationResult result = results.get(Accuracy.ACC_MEASURE);
		assertEquals(0.65, result.getResult(), 0.001);
		
		result = results.get(Precision.PREC_MEASURE+"_A");
		System.out.println(result.getResult());
		assertEquals(0.75, result.getResult(), 0.001);
		
		result = results.get(Recall.REC_MEASURE+"_A");
		System.out.println(result.getResult());
		assertEquals(0.6, result.getResult(), 0.001);
		
		result = results.get(Fscore.F_MEASURE+"_A");
		System.out.println(result.getResult());
		assertEquals(0.6666, result.getResult(), 0.001);	
		
		result = results.get(Precision.PREC_MEASURE+"_B");
		System.out.println(result.getResult());
		assertEquals(0.6, result.getResult(), 0.001);
		
		result = results.get(Recall.REC_MEASURE+"_B");
		System.out.println(result.getResult());
		assertEquals(0.833, result.getResult(), 0.001);
		
		result = results.get(Precision.PREC_MEASURE+"_C");
		System.out.println(result.getResult());
		assertEquals(0.57143, result.getResult(), 0.001);
		
		result = results.get(Recall.REC_MEASURE+"_C");
		System.out.println(result.getResult());
		assertEquals(0.625, result.getResult(), 0.001);
		
		result = results.get(Precision.PREC_MEASURE+"_MACRO");
		System.out.println(result.getResult());
		assertEquals(0.6405, result.getResult(), 0.001);
	
		result = results.get(Recall.REC_MEASURE+"_MACRO");
		System.out.println(result.getResult());
		assertEquals(0.686, result.getResult(), 0.001);
		
	}
	

}
