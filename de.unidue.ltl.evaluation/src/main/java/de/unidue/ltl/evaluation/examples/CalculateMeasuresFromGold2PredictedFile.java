/*******************************************************************************
 * Copyright 2016
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
import java.io.IOException;
import java.util.Map;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.io.TextReader;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class CalculateMeasuresFromGold2PredictedFile {

	// TODO shouldn't that be a test
	public static void main(String[] args)
			throws IOException
	{
		Evaluation<String> evaluation = TextReader
				.read(new File("src/test/resources/io/tab-separated_gold2predicted.txt"));
		Map<String, EvaluationResult> results = CategorialMeasuresUtil
				.computeCategorialResults(evaluation.getEntries());
		for (String measure : results.keySet()) {
			System.out.println(measure + " " + results.get(measure).getResult());
		}
	}

}
