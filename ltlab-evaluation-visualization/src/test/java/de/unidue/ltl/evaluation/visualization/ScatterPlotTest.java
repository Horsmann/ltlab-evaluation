/*******************************************************************************
 * Copyright 2018
 * Language Technology Lab
 * University of Duisburg-Essen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.unidue.ltl.evaluation.visualization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.unidue.ltl.evaluation.testing.TestUtils;

public class ScatterPlotTest {

    //FIXME: Tests nothing
	@Test
	public void scatterPlotTest() 
			throws IOException
	{
		Collection<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		entries.add(new EvaluationEntry<Double>(1.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 3.0));
		entries.add(new EvaluationEntry<Double>(3.0, 4.0));
		entries.add(new EvaluationEntry<Double>(4.0, 5.0));
		
		EvaluationData<Double> eval = new EvaluationData<>(entries);
		
		ScatterPlot.writePlot(eval, new File("target/test.png"));
	}
	
	@Test
	public void randomPlotTest() 
			throws IOException
	{
        EvaluationData<Double> evaluationData = new EvaluationData<Double>(TestUtils.getRandomGaussianEntries(100));
		ScatterPlot.writePlot(evaluationData, new File("target/testRandom.png"));
	}
}
