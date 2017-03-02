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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.unidue.ltl.evaluation.Evaluation;

public class McNemarBulkTest {

	private ArrayList<Evaluation<String>> evalObjects;
	
	public McNemarBulkTest(){
		evalObjects = new ArrayList<Evaluation<String>>();
	}
	
	public McNemarBulkTest(ArrayList<Evaluation<String>> evaluations){
		evalObjects = evaluations;
	}
	
	public void register (Evaluation<String> eval){
		evalObjects.add(eval);
	}	
	
	public Map<String, Map<String, Double>> computeBulkTable() throws Exception{
		Map<String, Map<String, Double>> resultTable = new HashMap<String, Map<String, Double>>();
		for (Evaluation<String> eval1 : this.evalObjects){
			resultTable.put(eval1.getEvalMetaData().getName(), new HashMap<String, Double>());
			for (Evaluation<String> eval2 : this.evalObjects){
				 resultTable.get(eval1.getEvalMetaData().getName())
				 .put(eval2.getEvalMetaData().getName(), 
						 ChiSquare.getPvalue(
								 McNemarTest.computeSignificance(eval1, eval2, "Yates"), 1));
			}	
		}
		return resultTable;
	}
	
	
}
