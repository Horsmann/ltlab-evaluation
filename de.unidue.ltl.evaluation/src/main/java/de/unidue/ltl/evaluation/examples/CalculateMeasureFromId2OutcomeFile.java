package de.unidue.ltl.evaluation.examples;

import java.io.File;
import java.util.Map;

import org.apache.uima.resource.ResourceInitializationException;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.io.TcId2OutcomeReader;
import de.unidue.ltl.evaluation.io.TextReader;
import de.unidue.ltl.evaluation.measure.util.CategorialMeasuresUtil;

public class CalculateMeasureFromId2OutcomeFile {
	public static void main(String[] args) throws ResourceInitializationException {
		Evaluation<String> evaluation = TcId2OutcomeReader.read(new File("src/test/resources/io/id2Outcome_gunshot.txt"));
		Map<String, EvaluationResult> results = CategorialMeasuresUtil
				.computeCategorialResults(evaluation.getEntries());
		for (String measure : results.keySet()) {
			System.out.println(measure + " " + results.get(measure).getResult());
		}
	}
}
