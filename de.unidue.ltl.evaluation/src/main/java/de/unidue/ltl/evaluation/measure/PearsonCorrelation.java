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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dkpro.statistics.correlation.*;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;


public class PearsonCorrelation 
	extends EvaluationMeasure
{

	public PearsonCorrelation(Collection<EvaluationEntry> entries) {
		super(entries);
	}

	private List<Double> val1;
	private List<Double> val2;
	
	@Override
	public List<EvaluationResult> calculate() {
		List <EvaluationResult> evals = new ArrayList<EvaluationResult>();
		
		val1 = new ArrayList<Double>();
		val2 = new ArrayList<Double>(); 
		
		for (EvaluationEntry entry : entries) {
			val1.add((double) entry.getGold());
			val2.add((double) entry.getPredicted());
			
		}
		
		double result = org.dkpro.statistics.correlation.PearsonCorrelation.computeCorrelation(val1, val1);
		
		EvaluationResult obj = new EvaluationResult();
		obj.setResult(result);
		evals.add(obj);
		
		return evals;
	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "PearsonCorrelation";
	}

}
