package de.unidue.ltl.evaluation.visualization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.mockito.Mockito;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.util.TestUtils;

public class ScatterPlotTest {

	@Test
	public void scatterPlotTest() 
			throws IOException
	{
		Collection<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		entries.add(new EvaluationEntry<Double>(1.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 3.0));
		entries.add(new EvaluationEntry<Double>(3.0, 4.0));
		entries.add(new EvaluationEntry<Double>(4.0, 5.0));
		
        @SuppressWarnings("unchecked")
		Evaluation<Double> mock = (Evaluation<Double>) Mockito.mock(Evaluation.class);
        Mockito.when(mock.getEntries()).thenReturn(entries);

		ScatterPlot.writePlot(mock, new File("target/test.png"));
	}
	
	@Test
	public void randomPlotTest() 
			throws IOException
	{
        @SuppressWarnings("unchecked")
		Evaluation<Double> mock = (Evaluation<Double>) Mockito.mock(Evaluation.class);
        Mockito.when(mock.getEntries()).thenReturn(TestUtils.getRandomEntries(100));

		ScatterPlot.writePlot(mock, new File("target/testRandom.png"));
	}
}
