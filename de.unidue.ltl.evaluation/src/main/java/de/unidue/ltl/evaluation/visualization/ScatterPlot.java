/*******************************************************************************
 * Copyright 2017
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

import org.apache.commons.lang.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.measure.correlation.VectorPair;

public class ScatterPlot {

	public static void writePlot(Evaluation<Double> evaluation, File targetFile) 
			throws IOException 
	{
		   
		VectorPair<Double> vp = new VectorPair<>(evaluation.getEntries());
		
		DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] data = new double[2][vp.getVal1().size()];
        data[0] = ArrayUtils.toPrimitive(vp.getVal1().toArray(new Double[0]));
        data[1] = ArrayUtils.toPrimitive(vp.getVal2().toArray(new Double[0]));
        dataset.addSeries("Scatterplot", data);
 
        JFreeChart chart = ChartFactory.createScatterPlot("Scatterplot", "Gold", "Prediction", dataset,
                PlotOrientation.VERTICAL, false, false, false);
		             
		ChartUtilities.saveChartAsPNG(targetFile, chart, 400, 400);
	}
}
