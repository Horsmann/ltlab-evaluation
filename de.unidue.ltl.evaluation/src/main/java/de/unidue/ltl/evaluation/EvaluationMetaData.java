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

import java.util.List;

public class EvaluationMetaData<T> {

	private String name;
	private List<T> labels;
	
	public EvaluationMetaData(String name, List<T> labels) {
		this.setName(name);
		this.setLabels(labels);
	}
	
	/**
	 * labels distribution 
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
}
