/*******************************************************************************
w * Copyright 2016
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

import java.util.Collection;
import java.util.Map;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class Fscore 
	extends EvaluationMeasure<String>
{

	public static final String F_MEASURE = "Fscore";
	
	public Fscore(Collection<EvaluationEntry<String>> entries) {
		super(entries);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, EvaluationResult> calculate() {
		return CategorialMeasuresUtil.computeCategorialResults(entries);
	}

	@Override
	public String getName() {
		return F_MEASURE;
	}




}
