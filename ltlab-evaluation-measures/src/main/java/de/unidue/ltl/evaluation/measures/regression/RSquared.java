package de.unidue.ltl.evaluation.measures.regression;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;

public class RSquared extends RegressionMeasure<Double> {

	double result=0.0;
	boolean didCalculate=false;
	
	public RSquared(EvaluationData<Double> data) {
		super(data);
	}

	void calculate(){
		
		double squared_error=.0;
		double predicted_avg=.0;
		
		for(EvaluationEntry<Double> e : data){
			Double gold = e.getGold();
			Double pred = e.getPredicted();
			
			squared_error+=Math.pow(gold-pred, 2);
			predicted_avg+=pred;
		}
		predicted_avg/=data.size();
		
		double sum_prediction_avg_diff=.0;
		for(EvaluationEntry<Double> e : data){
			sum_prediction_avg_diff+=Math.pow(e.getPredicted()-predicted_avg, 2);
		}
		
		result = 1 - (squared_error/sum_prediction_avg_diff);
		
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
