package de.unidue.ltl.evaluation.significance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.unidue.ltl.evaluation.Evaluation;

public class McNemarBulkTest {

	private ArrayList<Evaluation<String>> evalObjects;
	
	public McNemarBulkTest(){
		evalObjects = new ArrayList<Evaluation<String>>();
	}
	
	public McNemarBulkTest(ArrayList<Evaluation<String>> evaluations){
		evalObjects = evaluations;
	}
	
	public void register (Evaluation<String> eval){
		evalObjects.add(eval);
	}	
	
	public Map<String, Map<String, Double>> computeBulkTable() throws Exception{
		Map<String, Map<String, Double>> resultTable = new HashMap<String, Map<String, Double>>();
		for (Evaluation<String> eval1 : this.evalObjects){
			resultTable.put(eval1.getEvalMetaData().getName(), new HashMap<String, Double>());
			for (Evaluation<String> eval2 : this.evalObjects){
				 resultTable.get(eval1.getEvalMetaData().getName())
				 .put(eval2.getEvalMetaData().getName(), 
						 ChiSquare.getPvalue(
								 McNemarTest.computeSignificance(eval1, eval2, "Yates"), 1));
			}	
		}
		return resultTable;
	}
	
	
}
