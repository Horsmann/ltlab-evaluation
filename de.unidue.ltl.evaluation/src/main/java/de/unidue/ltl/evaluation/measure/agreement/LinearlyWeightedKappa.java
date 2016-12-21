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

package de.unidue.ltl.evaluation.measure.agreement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dkpro.statistics.agreement.IAnnotationStudy;
import org.dkpro.statistics.agreement.coding.CodingAnnotationStudy;
import org.dkpro.statistics.agreement.coding.CohenKappaAgreement;
import org.dkpro.statistics.agreement.coding.WeightedKappaAgreement;
import org.dkpro.statistics.agreement.distance.IDistanceFunction;
import org.dkpro.statistics.agreement.distance.IntervalDistanceFunction;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.EvaluationMeasure;
import de.unidue.ltl.evaluation.measure.util.VectorPair;

public class LinearlyWeightedKappa 
extends EvaluationMeasure<Double> {

	public static final String KAPPA_MEASURE_LINEAR = "LinearlyWeightedKappa";

	public LinearlyWeightedKappa(Collection<EvaluationEntry<Double>> entries) {
		super(entries);
	}


	@Override
	public Map<String, EvaluationResult> calculate() {
		Map<String, EvaluationResult> results = new HashMap<>();

		CodingAnnotationStudy study = new CodingAnnotationStudy(2);
		for (EvaluationEntry<Double> entry : entries){
			study.addItem(entry.getGold(), entry.getPredicted());
		}
		LinearDistanceFunction  dist = new LinearDistanceFunction();
		WeightedKappaAgreement kappa = new WeightedKappaAgreement(study, dist);
		double kappaValue = kappa.calculateAgreement();
		results.put(this.getClass().getSimpleName(), new EvaluationResult(kappaValue));

		return results;	
	}

	@Override
	public String getName() {
		return KAPPA_MEASURE_LINEAR;
	}

}


class LinearDistanceFunction implements IDistanceFunction {

	@Override
	public double measureDistance(final IAnnotationStudy study, 
			final Object category1, final Object category2) {
		if (category1 instanceof Integer && category2 instanceof Integer){
			return Math.abs(((Integer) category1) - ((Integer) category2));
		}

		if (category1 instanceof Double && category2 instanceof Double){
			return Math.abs(((Double) category1) - ((Double) category2));
		}

		return (category1.equals(category2) ? 0.0 : 1.0);
	}

}
