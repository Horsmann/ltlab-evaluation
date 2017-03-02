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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationMetaData<T> {

	private String name;
	private List<T> labels;
	private Collection<EvaluationEntry<T>> entries;
	private Map<T,Integer> distributionsPerLabelPredicted;
	private Map<T,Integer> distributionsPerLabelGold;
	private int numberOfInstances;
	
	public EvaluationMetaData(String name, List<T> labels, Collection<EvaluationEntry<T>> entries) {
		this.setName(name);
		this.setLabels(labels);
		this.setEntries(entries);
		this.distributionsPerLabelPredicted = getDistributionsPerLabel(true);
		this.distributionsPerLabelGold = getDistributionsPerLabel(false);
		this.numberOfInstances=entries.size();
	}
	
	private void setEntries(Collection<EvaluationEntry<T>> entries) {
		this.entries=entries;		
	}

	/**
	 * distribution characteristics*
	 * # of registered instances
	 * ordinal or scale measures
	 * @return
	 */

	public String getStats(){
		
		StringBuilder sb= new StringBuilder();
		
		
		// add stats
		
		
		return sb.toString();
	}

	private Map<T, Integer> getDistributionsPerLabel(boolean isPredicted) {
		Map<T,Integer> distributionsPerLabel= new HashMap<>();
		
		for(T label:labels){
			int count =getCount4label(label,isPredicted);
			distributionsPerLabel.put(label, count);
		}
		return distributionsPerLabel;
	}

	private int getCount4label(T label, boolean isPredicted) {
		int counter = 0;
		for (EvaluationEntry<T> entry : entries) {
			if (isPredicted) {
				if (entry.getPredicted().equals(label))
					counter++;
			} else {
				if (entry.getGold().equals(label))
					counter++;
			}
		}
		return counter;
	}

	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(this.name);
		sb.append(this.labels);
		sb.append(getStats());
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T> getLabels() {
		return labels;
	}

	public void setLabels(List<T> labels) {
		this.labels = labels;
	}

	public Collection<EvaluationEntry<T>> getEntries() {
		return entries;
	}

	public Map<T, Integer> getDistributionsPerLabelPredicted() {
		return distributionsPerLabelPredicted;
	}

	public Map<T, Integer> getDistributionsPerLabelGold() {
		return distributionsPerLabelGold;
	}

	public int getNumberOfInstances() {
		return numberOfInstances;
	}

	public void setNumberOfInstances(int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

	public void setDistributionsPerLabelPredicted(Map<T, Integer> distributionsPerLabelPredicted) {
		this.distributionsPerLabelPredicted = distributionsPerLabelPredicted;
	}

	public void setDistributionsPerLabelGold(Map<T, Integer> distributionsPerLabelGold) {
		this.distributionsPerLabelGold = distributionsPerLabelGold;
	}
}
