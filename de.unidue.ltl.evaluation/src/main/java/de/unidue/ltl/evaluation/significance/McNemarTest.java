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
package de.unidue.ltl.evaluation.significance;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class McNemarTest {

	public static double computeSignificance(Evaluation<String> e1, Evaluation<String> e2) {

		double sample1negative = 0;
		double sample2negative = 0;
		
		for (EvaluationEntry<String> entry : e1.getEntries()) {
			if (!positive(entry)) {
				sample1negative++;
			}
		}

		for (EvaluationEntry<String> entry : e2.getEntries()) {
			if (!positive(entry)) {
				sample2negative++;
			}
		}
		double mcNemar = Math.pow(Math.abs(sample2negative - sample1negative) - 0.5, 2)
				/ (sample1negative + sample2negative);

		return mcNemar;
	}

	private static boolean positive(EvaluationEntry<String> entry) {
		return entry.getGold().equals(entry.getPredicted());
	}
}