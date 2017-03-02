package de.unidue.ltl.evaluation;

import java.util.List;

public class EvaluationMetaData<T> {

	private String name;
	private List<T> labels;
	
	public EvaluationMetaData(String name, List<T> labels) {
		this.setName(name);
		this.setLabels(labels);
	}
	
	/**
	 * labels distribution 
	 * distribution characteristics*
	 * # of registered instances
	 * ordinal or scale measures
	 * @return
	 */
	public String getStats(){
		StringBuilder sb= new StringBuilder();
		
		// add stats
		
		
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(this.name);
		sb.append(this.labels);
		sb.append(getStats());
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T> getLabels() {
		return labels;
	}

	public void setLabels(List<T> labels) {
		this.labels = labels;
	}
}
