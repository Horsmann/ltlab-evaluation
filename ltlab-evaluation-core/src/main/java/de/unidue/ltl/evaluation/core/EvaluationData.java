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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class EvaluationData<T> implements Iterable<EvaluationEntry<T>> {

	private List<EvaluationEntry<T>> entries;
	private UUID id;

	public EvaluationData() {
		this.entries = new ArrayList<>();
	}

	@SafeVarargs
	public EvaluationData(Iterable<EvaluationEntry<T>>... entries) {
		this.entries = new ArrayList<>();

		for (Iterable<EvaluationEntry<T>> e : entries) {
			Iterator<EvaluationEntry<T>> iterator = e.iterator();
			while (iterator.hasNext()) {
				this.entries.add(iterator.next());
			}
		}
	}

	/**
	 * This registers a single-label classification result where gold and
	 * prediction are exactly one label
	 * 
	 * @param gold
	 * 			the expected value
	 * @param prediction
	 * 			the actually predicted value
	 */
	public void register(T gold, T prediction) {
		EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, prediction);
		entries.add(entry);
	}
	
	/**
	 * This registers a single-label classification result where gold and
	 * prediction are exactly one label
	 * 
	 * @param gold
	 * 			the expected value
	 * @param prediction
	 * 			the actually predicted value
	 * @param name
	 * 			a name that identifies the added entry
	 */
	public void register(T gold, T prediction, String name) {
		EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, prediction);
		entry.setName(name);
		entries.add(entry);
	}

	/**
	 * This registers a multi-label classification result where gold and
	 * prediction can consists of two or more labels that have to be predicted
	 * simultaneously
	 * 
	 * @param gold
	 * 			the expected value
	 * @param prediction
	 * 			the actually predicted value
	 */
	public void registerMultiLabel(Collection<T> gold, Collection<T> prediction) {
		EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, prediction);
		entries.add(entry);
	}
	
	/**
	 * This registers a multi-label classification result where gold and
	 * prediction can consists of two or more labels that have to be predicted
	 * simultaneously
	 * 
	 * @param gold
	 * 			the expected value
	 * @param prediction
	 * 			the actually predicted value
	 * @param name
	 * 			a name that identifies the added entry
	 */
	public void registerMultiLabel(Collection<T> gold, Collection<T> prediction, String name) {
		EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, prediction);
		entry.setName(name);
		entries.add(entry);
	}

	/**
	 * This registers a multi-label classification result where gold and
	 * prediction can consists of two or more labels that have to be predicted
	 * simultaneously
	 * 
	 * @param gold
	 * 			the expected value
	 * @param prediction
	 * 			the actually predicted value
	 */
	public void registerMultiLabel(T[] gold, T[] prediction) {
		EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, prediction);
		entries.add(entry);
	}
	
	/**
	 * This registers a multi-label classification result where gold and
	 * prediction can consists of two or more labels that have to be predicted
	 * simultaneously
	 * 
	 * @param gold
	 * 			the expected value
	 * @param prediction
	 * 			the actually predicted value
	 * @param name
	 * 			a name that identifies the added entry 
	 */
	public void registerMultiLabel(T[] gold, T[] prediction, String name) {
		EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, prediction);
		entry.setName(name);
		entries.add(entry);
	}

	@Override
	public Iterator<EvaluationEntry<T>> iterator() {
		return new Iterator<EvaluationEntry<T>>() {

			List<EvaluationEntry<T>> data = new ArrayList<>(entries);
			int idx = 0;

			@Override
			public boolean hasNext() {
				return idx < data.size();
			}

			@Override
			public EvaluationEntry<T> next() {
				return data.get(idx++);
			}
		};
	}

	public long size() {
		return entries.size();
	}

	public EvaluationEntry<T> get(int idx) {
		return entries.get(idx);
	}

	public UUID getId() {
		if (id == null) {
			id = UUID.randomUUID();
		}
		return id;
	}

	public void registerBulk(Iterable<EvaluationEntry<T>> data) {
		Iterator<EvaluationEntry<T>> iterator = data.iterator();
		while (iterator.hasNext()) {
			EvaluationEntry<T> next = iterator.next();
			register(next.getGold(), next.getPredicted());
		}
	}

}