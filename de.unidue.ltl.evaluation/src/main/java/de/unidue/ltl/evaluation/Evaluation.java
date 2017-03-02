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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.measure.EvaluationMeasure;

public class Evaluation<T> {

	private Collection<EvaluationEntry<T>> entries;
	private Map<String, EvaluationResult> calculatedMeasures;
	private ConfusionMatrix<T> confusionMatrix;

	public Evaluation() {
		this.init();
		this.entries = new ArrayList<>();
	}

	private void init() {
		this.calculatedMeasures= new HashMap<>();
		this.confusionMatrix = new ConfusionMatrix<T>();
	}

	public ConfusionMatrix<T> getConfusionMatrix() {
		return confusionMatrix;
	}

	public Collection<EvaluationEntry<T>> getEntries() {
		return entries;
	}

	public Evaluation(Collection<EvaluationEntry<T>> entries) {
		this.init();
		this.entries = entries;
		ConfusionMatrix<T> matrix = new ConfusionMatrix<T>();
		for (EvaluationEntry<T> entry : entries){
			matrix.register(entry.getGold(), entry.getPredicted());
		}
		this.confusionMatrix = matrix;
	}

	public void register(T gold, T predicted){
		EvaluationEntry<T> entry= new EvaluationEntry<T>(gold, predicted);
		entries.add(entry);
		confusionMatrix.register(gold, predicted);
		this.update();
	}

	public Set<String> getCalculatedMeasureNames(){
		return this.calculatedMeasures.keySet();
	}

	/**
	 * update Measures etc. because new entry added
	 */
	private void update() {
		calculatedMeasures.clear();
	}

	public EvaluationResult calculate(EvaluationMeasure<T> measure){
		if (!calculatedMeasures.containsKey(measure.getClass().getName())) {
			calculatedMeasures.putAll(measure.calculate());
		}
		return calculatedMeasures.get(measure.getName());
	}
}
