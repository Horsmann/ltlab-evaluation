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
package de.unidue.ltl.evaluation;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
public class EvaluationMetaDataTest {
	
	@Test
	public void evaluationMetaDataNameTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		EvaluationData<String> evaluation= new EvaluationData<>(entries,"NamedEvaluation");
		assertEquals("NamedEvaluation",evaluation.getMetaData().getName());
	}
	
	@Test
	public void evaluationMetaDataLabelTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		EvaluationData<String> evaluation= new EvaluationData<>(entries,"NamedEvaluation");
		assertEquals(StringUtils.join(Arrays.asList("A","B"), " "),StringUtils.join(evaluation.getMetaData().getLabels()," "));
	}
	
	@Test
	public void evaluationMetaDataLabelDistributionTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		EvaluationData<String> evaluation= new EvaluationData<>(entries,"NamedEvaluation");
		assertEquals(0,evaluation.getMetaData().getDistributionsPerLabelGold().get("B").intValue());
		assertEquals(3,evaluation.getMetaData().getDistributionsPerLabelPredicted().get("B").intValue());
		assertEquals(7,evaluation.getMetaData().getDistributionsPerLabelGold().get("A").intValue());
		assertEquals(4,evaluation.getMetaData().getDistributionsPerLabelPredicted().get("A").intValue());
	}
	
	@Test
	public void evaluationMetaDataLabelUpdateTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		EvaluationData<String> evaluation= new EvaluationData<>(entries,"NamedEvaluation");
		assertEquals(2,evaluation.getMetaData().getNumberOfInstances());
		
		evaluation.register("A", "A");
		assertEquals(3,evaluation.getMetaData().getNumberOfInstances());

	}
	
	@Test
	public void evaluationMetaDataPieChartStatsTest(){
		Collection<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		EvaluationData<String> evaluation= new EvaluationData<>(entries,"NamedEvaluation");
		evaluation.getMetaData().getPieChart();
	}
	
}
