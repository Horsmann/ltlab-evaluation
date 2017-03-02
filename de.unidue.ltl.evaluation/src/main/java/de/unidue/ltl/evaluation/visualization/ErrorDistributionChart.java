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
import java.util.Collection;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class ErrorDistributionChart<T> {
	
	/**
	 * code broken, do not use yet :P
	 * @param evaluation
	 * @param targetFile
	 * @throws IOException
	 */
	public void plotDistribution(Evaluation<T> evaluation, File targetFile) throws IOException{
//		DefaultCategoryDataset data = new DefaultCategoryDataset();
//		Collection<EvaluationEntry<T>> entries=evaluation.getEntries();
//		for(EvaluationEntry<T> entry: entries){
//			data.addValue((double)1/(double)entries.size(), trueOrFalse(entry), "");
//		}
//		final JFreeChart chart = ChartFactory.createStackedBarChart(
//				"Stacked Bar Chart Demo 4", // chart
//				"Category", // domain axis label
//				"Value", // range axis label
//				data, // data
//				PlotOrientation.VERTICAL, // the plot orientation
//				true, // legend
//				true, // tooltips
//				false // urls
//		);
//		ChartUtilities.saveChartAsPNG(targetFile, chart, 400, 400);
	}

	private Comparable trueOrFalse(EvaluationEntry<T> entry) {
		if(entry.getGold().equals(entry.getPredicted())){
			return "true";
		}
		return "false";
	}

}

