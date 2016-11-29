package de.unidue.ltl.evaluation.measure.agreement;

import java.util.Collection;
import java.util.Map;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.EvaluationMeasure;
import de.unidue.ltl.evaluation.measure.util.AgreementMeasureUtil;

public class CohenKappa 
	extends EvaluationMeasure<String>
{

	public static final String COHEN_KAPPA = "CohenKappa";
	
	public CohenKappa(Collection<EvaluationEntry<String>> entries) {
		super(entries);
	}

	@Override
	public Map<String, EvaluationResult> calculate() {
		return AgreementMeasureUtil.computeAgreementResults(entries);
	}

	@Override
	public String getName() {
		return COHEN_KAPPA;
	}

}
