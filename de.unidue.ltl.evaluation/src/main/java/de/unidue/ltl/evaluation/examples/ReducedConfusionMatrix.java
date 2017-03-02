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
package de.unidue.ltl.evaluation.examples;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.transformation.EvaluationTransformer;

public class ReducedConfusionMatrix {

	public static void main(String[] args) {
		Evaluation<String> eval = new Evaluation<>();
		eval.register("Z", "B");
		eval.register("A", "A");
		eval.register("A", "A");
		eval.register("Z", "B");
		eval.register("B", "B");
		eval.register("B", "Z");
		eval.register("B", "B");
		eval.register("B", "A");
		eval.register("B", "Z");
		eval.register("B", "A");
		
		System.out.println(eval.getConfusionMatrix());
		
		Evaluation<String> transformed = EvaluationTransformer.deleteLabels(eval, "Z");
		
		System.out.println(transformed.getConfusionMatrix());
	}
}
