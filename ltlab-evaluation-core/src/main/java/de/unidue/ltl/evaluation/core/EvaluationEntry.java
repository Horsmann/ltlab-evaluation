/*******************************************************************************
 * Copyright 2018
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
package de.unidue.ltl.evaluation.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EvaluationEntry<T> {
	private List<T> gold;
	private List<T> predicted;
	private String entryName;

	/**
	 * A single label/value entry
	 * @param gold
	 * 			the gold labels
	 * @param predicted	
	 * 			the predicted labels
	 */
	public EvaluationEntry(T gold, T predicted) {
		this.gold = new ArrayList<T>();
		this.predicted = new ArrayList<T>();

		this.gold.add(gold);
		this.predicted.add(predicted);
	}
	
	/**
	 * A multi label entry, gold and result values are provided as collection
	 * @param gold
	 * 			the gold labels
	 * @param predicted	
	 * 			the predicted labels
	 */
	public EvaluationEntry(Collection<T> gold, Collection<T> predicted) {
		this.gold = new ArrayList<T>(gold);
		this.predicted = new ArrayList<T>(predicted);
	}
	
	/**
	 * A multi label entry, gold and result values are provided as collection
	 * @param gold
	 * 			the gold labels
	 * @param predicted	
	 * 			the predicted labels
	 */
	public EvaluationEntry(T [] gold, T [] predicted) {
		this.gold = new ArrayList<T>(Arrays.asList(gold));
		this.predicted = new ArrayList<T>(Arrays.asList(predicted));
	}

	public T getGold() {
		return gold.get(0);
	}

	public List<T> getGoldMultiLabel() {
		return new ArrayList<T>(gold);
	}

	public void setGold(T gold) {
		this.gold.set(0, gold);
	}

	public void setGoldMultiLabel(Collection<T> gold) {
		this.gold = new ArrayList<T>(gold);
	}

	public T getPredicted() {
		return predicted.get(0);
	}

	public List<T> getPredictedMultiLabel() {
		return new ArrayList<T>(predicted);
	}

	public void setPredicted(T predicted) {
		this.predicted.set(0, predicted);
	}

	public void setPredicted(Collection<T> predicted) {
		this.predicted = new ArrayList<T>(predicted);
	}
	
	/**
	 * Passes the entry an additional name that can be used to carry some
	 * identification information with each instance
	 * 
	 * @param name
	 * 			the name of the entry
	 */
	public void setName(String name){
		this.entryName = name;
	}
	
	/**
	 * Returns the name of an entry
	 * @return
	 * 		the name or an empty string if the value has not been set
	 */
	public String getName() {
		return this.entryName == null ? "" : this.entryName;
	}

	@Override
	public String toString() {
		return gold.toString() + "\t" + predicted.toString();
	}

	public int getSize() {
		return gold.size();
	}
}