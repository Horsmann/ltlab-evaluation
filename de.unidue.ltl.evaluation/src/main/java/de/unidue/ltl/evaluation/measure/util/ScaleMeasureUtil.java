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
package de.unidue.ltl.evaluation.measure.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.correlation.PearsonCorrelation;
import de.unidue.ltl.evaluation.measure.correlation.SpearmanCorrelation;

public class ScaleMeasureUtil {

	public static Map<String, EvaluationResult> computeScaleResults(Collection<EvaluationEntry<Double>> entries) {
		Map<String, EvaluationResult> results = new HashMap<>();
		
		VectorPair<Double> vectors = new VectorPair<>(entries);
			
		double pearson = org.dkpro.statistics.correlation.PearsonCorrelation.computeCorrelation(vectors.getVal1(), vectors.getVal2());
		double spearman = org.dkpro.statistics.correlation.SpearmansRankCorrelation.computeCorrelation(vectors.getVal1(), vectors.getVal2());
		
		results.put(PearsonCorrelation.class.getSimpleName(), new EvaluationResult(pearson));
		results.put(SpearmanCorrelation.class.getSimpleName(), new EvaluationResult(spearman));
		
		return results;
	}
}
