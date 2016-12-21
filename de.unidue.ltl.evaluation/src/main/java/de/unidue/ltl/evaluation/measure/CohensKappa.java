package de.unidue.ltl.evaluation.measure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dkpro.statistics.agreement.coding.CodingAnnotationStudy;
import org.dkpro.statistics.agreement.coding.CohenKappaAgreement;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.util.VectorPair;

public class CohensKappa 
extends EvaluationMeasure<String> {

	public static final String KAPPA_MEASURE = "CohensKappa";

	public CohensKappa(Collection<EvaluationEntry<String>> entries) {
		super(entries);
	}


	@Override
	public Map<String, EvaluationResult> calculate() {
		Map<String, EvaluationResult> results = new HashMap<>();

		CodingAnnotationStudy study = new CodingAnnotationStudy(2);
		for (EvaluationEntry<String> entry : entries){
			study.addItem(entry.getGold(), entry.getPredicted());
		}
		CohenKappaAgreement kappa = new CohenKappaAgreement(study);
		double kappaValue = kappa.calculateAgreement();
		results.put(this.getClass().getSimpleName(), new EvaluationResult(kappaValue));

		return results;	
	}

	@Override
	public String getName() {
		return KAPPA_MEASURE;
	}


}
