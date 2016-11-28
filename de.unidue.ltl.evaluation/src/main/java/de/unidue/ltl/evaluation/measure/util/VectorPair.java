package de.unidue.ltl.evaluation.measure.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.unidue.ltl.evaluation.EvaluationEntry;

public class VectorPair<T> {
	
	private List<T> val1;
	private List<T> val2;
	
	public VectorPair(List<T> val1, List<T> val2) {
		this.val1 = val1;
		this.val2 = val2;
	}
	
	public VectorPair(Collection<EvaluationEntry<T>> entries) {
		this.val1 = new ArrayList<>();
		this.val2 = new ArrayList<>();
		
		for (EvaluationEntry<T> entry : entries) {
			val1.add(entry.getGold());
			val2.add(entry.getPredicted());
			
		}
	}

	public List<T> getVal1() {
		return val1;
	}

	public void setVal1(List<T> val1) {
		this.val1 = val1;
	}

	public List<T> getVal2() {
		return val2;
	}

	public void setVal2(List<T> val2) {
		this.val2 = val2;
	}
}