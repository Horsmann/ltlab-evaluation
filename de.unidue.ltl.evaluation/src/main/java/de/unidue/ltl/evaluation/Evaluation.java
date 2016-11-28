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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.measure.EvaluationMeasure;

public class Evaluation {

	private Collection<EvaluationEntry> entries;
	private Map<String, EvaluationResult> calculatedMeasures;
	
	public Evaluation() {
		this.init();
		this.entries = new ArrayList<>();
	}
	
	private void init() {
		this.calculatedMeasures= new HashMap<String, EvaluationResult>();
	}

	public Collection<EvaluationEntry> getEntries() {
		return entries;
	}

	public Evaluation(Collection<EvaluationEntry> entries) {
		this.init();
		this.entries = entries;
	}
	
	public void register(String gold, String predicted){
		EvaluationEntry entry= new EvaluationEntry(gold, predicted);
		entries.add(entry);
		this.update();
	}

	public Set<String> getCalculatedMeasureNames(){
		return this.calculatedMeasures.keySet();
	}

	/**
	 * update Measures etc. because new entry added
	 */
	private void update() {
		calculatedMeasures= new HashMap<String, EvaluationResult>();
	}
	
	public EvaluationResult calculate(EvaluationMeasure measure){
		if(calculatedMeasures.containsKey(measure.getName())){
			return calculatedMeasures.get(measure.getName());
		}
		else{
			for(EvaluationResult result: measure.calculate()){
				calculatedMeasures.put(result.getName(), result);
			}
			return calculatedMeasures.get(measure.getName());
		}		
	}
	
}
