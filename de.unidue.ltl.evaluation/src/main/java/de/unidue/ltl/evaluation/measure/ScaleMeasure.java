package de.unidue.ltl.evaluation.measure;

import java.util.List;

import org.dkpro.statistics.correlation.PearsonCorrelation;
import org.dkpro.statistics.correlation.SpearmansRankCorrelation_old;

import de.unidue.ltl.evaluation.EvaluationResult;


public class ScaleMeasure {

	private List<Double> val1;
	private List<Double> val2;
	
	
	public ScaleMeasure(List<Double> val1, List<Double> val2) {
		super();
		this.val1 = val1;
		this.val2 = val2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public EvaluationResult getPearson(){
		
		EvaluationResult eval = new EvaluationResult();
		
		double result = PearsonCorrelation.computeCorrelation(val1, val1);
		
		eval.setResult(result);
		
		return eval;
	}

	public EvaluationResult getSpearman(){
		
		EvaluationResult eval = new EvaluationResult();
		
		double result = SpearmansRankCorrelation_old.computeCorrelation(val1, val1);
		
		eval.setResult(result);
		
		return eval;

	}

}
