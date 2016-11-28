package de.unidue.ltl.evaluation.evaluationComparison;

import java.util.ArrayList;
import java.util.List;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class McNemarTest<T> {

	Evaluation<T> evaluation1;
	Evaluation<T> evaluation2;

	public double computeSignificance() {
		List<EvaluationEntry<T>> c1 = new ArrayList<>(evaluation1.getEntries());
		List<EvaluationEntry<T>> c2 = new ArrayList<>(evaluation2.getEntries());

		double sample1negative = 0;
		double sample2negative = 0;
		for (EvaluationEntry<T> entry : c1) {
			if (!positive(entry)) {
				sample1negative++;
			}
		}

		for (EvaluationEntry<T> entry : c2) {
			if (!positive(entry)) {
				sample2negative++;
			}
		}
		double mcNemare = Math.pow((Math.abs(sample2negative - sample1negative) - 0.5), 2)
				/ (sample1negative + sample2negative);

		return mcNemare;

	}

	private boolean positive(EvaluationEntry<T> entry) {
		if (entry.getGold().equals(entry.getPredicted())) {
			return true;
		}
		return false;
	}

	public McNemarTest(Evaluation<T> evaluation1, Evaluation<T> evaluation2) {
		this.evaluation1 = evaluation1;
		this.evaluation2 = evaluation2;
	}
}
