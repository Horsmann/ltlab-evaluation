package de.unidue.ltl.evaluation.measures.multilabel;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public class HammingLoss extends EvaluationMeasure<Integer> {

	private double result;
	private boolean didCalculate=false;

	public HammingLoss(EvaluationData<Integer> data) {
		super(data);
	}

	private void calculate() {
		
		double avg=0.0;
		
		for (int i=0; i < data.size(); i++) {
			double diff=0;
			EvaluationEntry<Integer> entry = data.get(i);
			for (int j=0; j < entry.getSize(); j++) {
				Integer g = entry.getGoldMultiLabel().get(j);
				Integer p = entry.getPredictedMultiLabel().get(j);
				if (!g.equals(p)) {
					diff++;
				}
			}
			avg += diff/entry.getSize();
		}
		
		result = avg / data.size();
	}

	@Override
	public double getResult() {
		
		if(!didCalculate) {
			calculate();
		}
		
		return result;
	}

}
