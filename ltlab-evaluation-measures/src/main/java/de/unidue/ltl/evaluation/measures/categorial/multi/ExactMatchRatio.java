package de.unidue.ltl.evaluation.measures.categorial.multi;

import java.util.List;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.measures.EvaluationMeasure;

public class ExactMatchRatio<T> extends EvaluationMeasure<T> {

	boolean didCalculate = false;
	private double result;

	public ExactMatchRatio(EvaluationData<T> data) {
		super(data);
	}

	private void calculate() {
		double exactMatches=.0;
		for(EvaluationEntry<T> e : data){
			List<T> goldMulti = e.getGoldMultiLabel();
			List<T> predictedMulti = e.getPredictedMultiLabel();
			
			boolean match=true;
			for(int i=0; i < goldMulti.size(); i++){
				if (!goldMulti.get(i).equals(predictedMulti.get(i))){
					match=false;
					break;
				}
			}
			
			exactMatches += match ? 1 : 0;
		}
		
		result = exactMatches / data.size();
	}

	@Override
	public double getResult() {

		if (didCalculate) {
			return result;
		}

		calculate();

		return result;
	}

}
