package de.unidue.ltl.evaluation.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import de.unidue.ltl.evaluation.EvaluationEntry;

public class TestUtils {

	public static Collection<EvaluationEntry<Double>> getRandomEntries(int n) {
		Random rand = new Random();
		List<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		for (int i=0; i<n; i++) {
			entries.add(new EvaluationEntry<Double>(rand.nextGaussian(), rand.nextGaussian()));
		}
		return entries;
	}
}
