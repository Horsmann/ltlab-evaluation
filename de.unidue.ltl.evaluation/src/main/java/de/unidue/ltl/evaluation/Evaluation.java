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
import java.util.Collections;
import java.util.Set;

import de.unidue.ltl.evaluation.measure.EvaluationMeasure;

public class Evaluation {

	private Collection<EvaluationEntry> entries;
	
	public Evaluation() {
		this.entries = new ArrayList<>();
	}
	
	public Collection<EvaluationEntry> getEntries() {
		return entries;
	}

	public Evaluation(Collection<EvaluationEntry> entries) {
		this.entries = entries;
	}
	
	public void register(String gold, String predicted){
		EvaluationEntry entry= new EvaluationEntry(gold, predicted);
		entries.add(entry);
		this.update();
	}


	/**
	 * update Measures etc. because new entry added
	 */
	private void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Set<EvaluationResult> calculate(EvaluationMeasure result){
		return result.calculate();
	}
	
}
