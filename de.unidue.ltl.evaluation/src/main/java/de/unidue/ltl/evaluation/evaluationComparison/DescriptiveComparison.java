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
package de.unidue.ltl.evaluation.evaluationComparison;

import java.util.ArrayList;
import java.util.List;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class DescriptiveComparison<T> {
	private Evaluation<T> evaluation1;
	private Evaluation<T> evaluation2;
	private int overlap;
	private int dicrepancy;
	private int firstPositiveThenPositive;
	private int firstPositiveThenNegative;
	private int firstNegativeThenPositive;
	private int firstNegativeThenNegative;
	
	public DescriptiveComparison(Evaluation<T> evaluation1, Evaluation<T> evaluation2) throws Exception {
		this.evaluation1 = evaluation1;
		this.evaluation2 = evaluation2;
		this.overlap=0;
		this.dicrepancy=0;
		this.firstPositiveThenPositive=0;
		this.firstPositiveThenNegative=0;
		this.firstNegativeThenPositive=0;
		this.firstNegativeThenNegative=0;
		
		calculateMeasures();
	}
	
	private void calculateMeasures() throws Exception {
		List<EvaluationEntry<T>> entries1=new ArrayList<>(evaluation1.getEntries());
		List<EvaluationEntry<T>> entries2=new ArrayList<>(evaluation2.getEntries());
		if(entries1.size()!=entries2.size()){
			throw new Exception("Evaluations don't have equal size: "+evaluation1.getEntries().size()+"/"+evaluation2.getEntries().size());
		}
		int i=0;
		for(EvaluationEntry<T> entry1: entries1){
			boolean positiveEntry1=positive(entry1);
			boolean positiveEntry2=positive(entries2.get(i));
			if(positiveEntry1&&positiveEntry2){
				overlap++;
				firstPositiveThenPositive++;
			}
			if(!positiveEntry1&&positiveEntry2){
				dicrepancy++;
				firstNegativeThenPositive++;
			}
			if(positiveEntry1&&!positiveEntry2){
				dicrepancy++;
				firstPositiveThenNegative++;
			}
			if(!positiveEntry1&&!positiveEntry2){
				overlap++;
				firstNegativeThenNegative++;
			}
			i++;
		}
	}

	private boolean positive(EvaluationEntry<T> entry) {
		if(entry.getGold().equals(entry.getPredicted())){
			return true;
		}
		return false;
	}

	public int getOverlap() {
		return overlap;
	}

	public int getDicrepancy() {
		return dicrepancy;
	}

	public int getFirstPositiveThenPositive() {
		return firstPositiveThenPositive;
	}

	public int getFirstPositiveThenNegative() {
		return firstPositiveThenNegative;
	}
	
	public int getFirstNegativeThenNegative() {
		return firstNegativeThenNegative;
	}

	public int getFirstNegativeThenPositive() {
		return firstNegativeThenPositive;
	}
}
