package de.unidue.ltl.evaluation.measures.regression;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;

public class MeanSquaredError extends RegressionMeasure<Double> {

	double result=0.0;
	boolean didCalculate=false;
	
	public MeanSquaredError(EvaluationData<Double> data) {
		super(data);
	}

	void calculate(){
		
		double val=0.0;
		
		for(EvaluationEntry<Double> e : data){
			Double gold = e.getGold();
			Double pred = e.getPredicted();
			
			Double diff = gold-pred;
			double pow = Math.pow(diff, 2);
			val+=pow;
		}
		
		result = val/data.size();
	}

	@Override
	public double getResult() {
		
		if(didCalculate){
			return result;
		}
		calculate();
		
		return result;
	}
	
	
	
}
