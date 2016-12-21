package de.unidue.ltl.evaluation.measure;

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
