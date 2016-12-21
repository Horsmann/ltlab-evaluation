package de.unidue.ltl.evaluation.measure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dkpro.statistics.agreement.coding.CodingAnnotationStudy;
import org.dkpro.statistics.agreement.coding.CohenKappaAgreement;
import org.dkpro.statistics.agreement.coding.WeightedKappaAgreement;
import org.dkpro.statistics.agreement.distance.IntervalDistanceFunction;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.util.VectorPair;

public class QuadraticallyWeightedKappa 
extends EvaluationMeasure<Double> {

	public static final String KAPPA_MEASURE_QUADRATIC = "QuadraticallyWeightedKappa";

	public QuadraticallyWeightedKappa(Collection<EvaluationEntry<Double>> entries) {
		super(entries);
	}


	@Override
	public Map<String, EvaluationResult> calculate() {
		Map<String, EvaluationResult> results = new HashMap<>();

		CodingAnnotationStudy study = new CodingAnnotationStudy(2);
		for (EvaluationEntry<Double> entry : entries){
			study.addItem(entry.getGold(), entry.getPredicted());
		}
		IntervalDistanceFunction  dist = new IntervalDistanceFunction();
		WeightedKappaAgreement kappa = new WeightedKappaAgreement(study, dist);
		double kappaValue = kappa.calculateAgreement();
		results.put(this.getClass().getSimpleName(), new EvaluationResult(kappaValue));

		return results;	
	}

	@Override
	public String getName() {
		return KAPPA_MEASURE_QUADRATIC;
	}


}
