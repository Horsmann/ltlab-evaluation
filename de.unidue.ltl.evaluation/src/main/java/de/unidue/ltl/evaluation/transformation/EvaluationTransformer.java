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
import java.util.Set;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class EvaluationTransformer {

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

}
