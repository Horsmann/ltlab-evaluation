package de.unidue.ltl.evaluation.significance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChiSquare {

	
	
	static Map<Integer, Map<Double, Double>> chiSquareTable = 
	new HashMap<Integer, Map<Double, Double>>();
	static {
		Map<Double, Double> pvalues_df1 = new HashMap<Double, Double>();
		pvalues_df1.put(2.706, 0.1);
		pvalues_df1.put(3.841, 0.05);
		pvalues_df1.put(5.024, 0.025);
		pvalues_df1.put(6.635, 0.01);
		pvalues_df1.put(7.88, 0.005);
		pvalues_df1.put(10.828, 0.001);
		chiSquareTable.put(1, pvalues_df1);
		}
	
	
	public static double getPvalue(double chiSquare, int degreesOfFreedom){
		Map<Double, Double> pvalues = chiSquareTable.get(degreesOfFreedom);
		Iterator<Double> iter =pvalues.keySet().iterator();
		double lowestP = 1.0;
		while (iter.hasNext()){
			double threshold = iter.next();
			if (chiSquare > threshold){
				if (pvalues.get(threshold) < lowestP){
					lowestP = pvalues.get(threshold);
				}
			}
		}
		return lowestP;
	}
	
	
	
	
}
