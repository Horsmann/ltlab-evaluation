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

package de.unidue.ltl.evaluation.measure;

import java.util.List;

import org.dkpro.statistics.correlation.PearsonCorrelation;
import org.dkpro.statistics.correlation.SpearmansRankCorrelation_old;

import de.unidue.ltl.evaluation.EvaluationResult;


public class ScaleMeasure {

	private List<Double> val1;
	private List<Double> val2;
	
	
	public ScaleMeasure(List<Double> val1, List<Double> val2) {
		super();
		this.val1 = val1;
		this.val2 = val2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public EvaluationResult getPearson(){
		
		EvaluationResult eval = new EvaluationResult();
		
		double result = PearsonCorrelation.computeCorrelation(val1, val1);
		
		eval.setResult(result);
		
		return eval;
	}

	public EvaluationResult getSpearman(){
		
		EvaluationResult eval = new EvaluationResult();
		
		double result = SpearmansRankCorrelation_old.computeCorrelation(val1, val1);
		
		eval.setResult(result);
		
		return eval;

	}

}
