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
package de.unidue.ltl.evaluation.transformation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

/**
 * @author zesch
 *
 */
public class EvaluationTransformer {

	/**
	 * Delete selected labels from evaluation
	 * @param eval An evaluation
	 * @param labels A list of labels to be deleted.
	 * @return A new evaluation object without the selected labels
	 */
	public static <T> Evaluation<T> deleteLabels(Evaluation<T> eval, T ... labels) {
		
		Evaluation<T> transformed = new Evaluation<T>();		
		
		Set<T> labelSet = new HashSet<T>(Arrays.asList(labels));
				
		for (EvaluationEntry<T> entry : eval.getEntries()) {
			
			if (!labelSet.contains(entry.getGold()) && !labelSet.contains(entry.getPredicted())) {
				transformed.register(entry.getGold(), entry.getPredicted());				
			}
		}
		
		return transformed;
	}
	
	
	/**
	 * @param eval An evaluation
	 * @param mapping A mapping from gold values to be changed to new gold values
	 * @return An evaluation with changed gold labels
	 */
	public static <T> Evaluation<T> changeGoldLabel(Evaluation<T> eval, Map<T,T> mapping) {
		
		Evaluation<T> transformed = new Evaluation<T>();		
						
		for (EvaluationEntry<T> entry : eval.getEntries()) {			
			if (mapping.containsKey(entry.getGold())) {
				transformed.register(mapping.get(entry.getGold()), entry.getPredicted());				
			}
			else {
				transformed.register(entry.getGold(), entry.getPredicted());
			}
		}
		
		return transformed;
	}
	
	/**
	 * @param eval An evaluation
	 * @param mapping A mapping from predicted values to be changed to new predicted values
	 * @return An evaluation with changed predicted labels
	 */
	public static <T> Evaluation<T> changePredictedLabel(Evaluation<T> eval, Map<T,T> mapping) {
		
		Evaluation<T> transformed = new Evaluation<T>();		
						
		for (EvaluationEntry<T> entry : eval.getEntries()) {			
			if (mapping.containsKey(entry.getPredicted())) {
				transformed.register(entry.getGold(), mapping.get(entry.getPredicted()));				
			}
			else {
				transformed.register(entry.getGold(), entry.getPredicted());
			}
		}
		
		return transformed;
	}
}
