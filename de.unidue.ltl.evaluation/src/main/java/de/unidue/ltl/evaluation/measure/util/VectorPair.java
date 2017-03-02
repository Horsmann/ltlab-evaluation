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
package de.unidue.ltl.evaluation.measure.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class VectorPair<T> {
	
	private List<T> val1;
	private List<T> val2;
	
	public VectorPair(List<T> val1, List<T> val2) {
		this.val1 = val1;
		this.val2 = val2;
	}
	
	public VectorPair(Collection<EvaluationEntry<T>> entries) {
		this.val1 = new ArrayList<>();
		this.val2 = new ArrayList<>();
		
		for (EvaluationEntry<T> entry : entries) {
			val1.add(entry.getGold());
			val2.add(entry.getPredicted());
			
		}
	}
	
	public VectorPair(EvaluationData<T> data) {
        this.val1 = new ArrayList<>();
        this.val2 = new ArrayList<>();
        
        for (EvaluationEntry<T> entry : data) {
            val1.add(entry.getGold());
            val2.add(entry.getPredicted());
        }
    }

	public List<T> getVal1() {
		return val1;
	}

	public void setVal1(List<T> val1) {
		this.val1 = val1;
	}

	public List<T> getVal2() {
		return val2;
	}

	public void setVal2(List<T> val2) {
		this.val2 = val2;
	}
}