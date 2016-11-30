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
import java.util.HashMap;
import java.util.Map;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.io.TextReader;
import de.unidue.ltl.evaluation.measure.EvaluationMeasure;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class CalculateMeasuresFromGold2PredictedFile {

	//labelsMapping = {'none': 0, 'favor': 1, 'against': 2}
	public static void main(String[] args) throws IOException {
//		File folder= new File("/Users/michael/git/ucsm_git/stance_lstm/result/");
		File folder= new File("/Users/michael/git/ucsm_git/stance_lstm/result/cv/");
		Map<String,Double> resultsFSemeval= new HashMap<>();
		Map<String,Double> resultsF_micro= new HashMap<>();
		for(File file:folder.listFiles()){
			if(file.isDirectory())continue;
			Evaluation<String> evaluation= TextReader.fromTabSeparated(file);
			Map<String, EvaluationResult> results = CategorialMeasuresUtil.computeCategorialResults(evaluation.getEntries());
			double f_favor=0;
			double f_against=0;
			double f_micro=0;
			for(String measure:results.keySet()){
//				System.out.println(measure+" "+results.get(measure).getResult());
				if(measure.equals("Fscore_1")){
					f_favor=results.get(measure).getResult();
				}
				if(measure.equals("Fscore_2")){
					f_against=results.get(measure).getResult();
				}
				if(measure.equals("Fscore_MICRO")){
					f_micro=results.get(measure).getResult();
				}
				resultsF_micro.put(file.getName(), f_micro);
				resultsFSemeval.put(file.getName(),(f_against+f_favor)/2);
			}
		}
		System.out.println("##### SEMEVAL #####");
		for(String file:resultsFSemeval.keySet()){
			System.out.println(file+" : "+resultsFSemeval.get(file));
		}
		System.out.println("##### F1 #####");
		for(String file:resultsF_micro.keySet()){
			System.out.println(file+" : "+resultsF_micro.get(file));
		}

	}

}
