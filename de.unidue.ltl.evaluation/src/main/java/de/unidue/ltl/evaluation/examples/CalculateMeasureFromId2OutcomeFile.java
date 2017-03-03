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

import java.io.File;
import java.util.Map;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.io.TcId2OutcomeReader;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class CalculateMeasureFromId2OutcomeFile {
	
//	// TODO shouldn't that be a test
//	public static void main(String[] args)
//			throws Exception
//	{
//		Evaluation<String> evaluation = TcId2OutcomeReader.read(new File("src/test/resources/io/id2Outcome_gunshot.txt"));
//		Map<String, EvaluationResult> results = CategorialMeasuresUtil
//				.computeCategorialResults(evaluation.getEntries());
//		for (String measure : results.keySet()) {
//			System.out.println(measure + " " + results.get(measure).getResult());
//		}
//	}
}
