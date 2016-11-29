package de.unidue.ltl.evaluation.measure.agreement;

import java.util.Collection;
import java.util.Map;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.EvaluationMeasure;
import de.unidue.ltl.evaluation.measure.util.AgreementMeasureUtil;

public class FleissKappa 
	extends EvaluationMeasure<String>
{

	public static final String FLEISS_KAPPA = "FleissKappa";
	
	public FleissKappa(Collection<EvaluationEntry<String>> entries) {
		super(entries);
	}

	@Override
	public Map<String, EvaluationResult> calculate() {
		return AgreementMeasureUtil.computeAgreementResults(entries);
	}

	@Override
	public String getName() {
		return FLEISS_KAPPA;
	}

}
