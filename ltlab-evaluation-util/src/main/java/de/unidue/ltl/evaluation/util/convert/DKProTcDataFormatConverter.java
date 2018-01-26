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
package de.unidue.ltl.evaluation.util.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class DKProTcDataFormatConverter {

	/**
	 * Loads a single-label DKPro TC id2outcome file into the evaluation data format
	 * 
	 * @param id2OutcomeFile
	 * @return an evaluation data object
	 * @throws Exception
	 *             in case of error
	 */
	public static EvaluationData<String> convertSingleLabelModeId2Outcome(File id2OutcomeFile) throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(id2OutcomeFile), "utf-8"));

		reader.readLine(); // pop first line

		Map<String, String> map = buildMappingFromHeader(reader.readLine());

		EvaluationData<String> data = new EvaluationData<>();

		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty() || line.startsWith("#")) {
				continue;
			}

			String[] split = line.split("=");
			String docName = split[0];
			String values = split[1];

			String[] valSplit = values.split(";");
			String prediction = map.get(valSplit[0]);
			String gold = map.get(valSplit[1]);
			// String threshold = valSplit[2];

			data.register(gold, prediction, docName);
		}

		reader.close();

		return data;
	}
	
	/**
	 * Loads a single-label DKPro TC id2outcome file into the evaluation data format
	 * 
	 * @param id2OutcomeFile
	 * @return an evaluation data object
	 * @throws Exception
	 *             in case of error
	 */
	public static EvaluationData<String> convertMultiLabelModeId2Outcome(File id2OutcomeFile) throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(id2OutcomeFile), "utf-8"));

		reader.readLine(); // pop first line

		Map<String, String> map = buildMappingFromHeader(reader.readLine());

		EvaluationData<String> data = new EvaluationData<>();

		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty() || line.startsWith("#")) {
				continue;
			}

			String[] split = line.split("=");
			String docName = split[0];
			String values = split[1];

			String[] valSplit = values.split(";");
			String prediction = map.get(valSplit[0]);
			String gold = map.get(valSplit[1]);
			// String threshold = valSplit[2];

			data.register(gold, prediction, docName);
		}

		reader.close();

		return data;
	}

	private static Map<String, String> buildMappingFromHeader(String header) {

		header = header.replaceAll("#labels", "").trim();

		Map<String, String> map = new HashMap<>();

		String[] split = header.split(" ");
		for (String entry : split) {
			int indexOf = entry.indexOf("=");
			String key = entry.substring(0, indexOf).trim();
			String val = entry.substring(indexOf + 1).trim();
			map.put(key, val);
		}

		return map;
	}

	/**
	 * Loads a regression DKPro TC id2outcome file into the evaluation data format
	 * 
	 * @param id2OutcomeFile
	 * @return an evaluation data object
	 * @throws Exception
	 *             in case of error
	 */
	public static EvaluationData<Double> convertRegressionModeId2Outcome(File id2OutcomeFile) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(id2OutcomeFile), "utf-8"));

		reader.readLine(); // pop head line
		reader.readLine(); // pop header (not needed for regression)

		EvaluationData<Double> data = new EvaluationData<>();
		
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty() || line.startsWith("#")) {
				continue;
			}

			String[] split = line.split("=");
			String docName = split[0];
			String values = split[1];

			String[] valSplit = values.split(";");
			Double prediction = Double.valueOf(valSplit[0]);
			Double gold = Double.valueOf(valSplit[1]);

			data.register(gold, prediction, docName);
		}
		
		reader.close();

		return data;
	}

}
