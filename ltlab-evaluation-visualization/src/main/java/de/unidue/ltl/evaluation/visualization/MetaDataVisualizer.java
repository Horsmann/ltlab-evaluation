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
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import de.unidue.ltl.evaluation.core.EvaluationMetaData;

public class MetaDataVisualizer<T>
{
    static final String PREDICTED_FILE = "pieChart_predicted.jpeg";
    static final String GOLD_FILE = "pieChart_gold.jpeg";

    public static <T> File[] getPieChart(EvaluationMetaData<T> meta, String outputFolder)
        throws Exception
    {
        getPieChart(meta, true, outputFolder);
        getPieChart(meta, false, outputFolder);
        
        return new File[] {getPieChart(meta, true, outputFolder), getPieChart(meta, true, outputFolder)};
    }

    public static <T> File getPieChart(EvaluationMetaData<T> meta, boolean isPredicted,
            String outputFolder)
                throws Exception
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (isPredicted) {
            for (T label : meta.getDistributionsPerLabelPredicted().keySet()) {
                dataset.setValue(String.valueOf(label),
                        meta.getDistributionsPerLabelPredicted().get(label));
            }
        }
        else {
            for (T label : meta.getDistributionsPerLabelGold().keySet()) {
                dataset.setValue(String.valueOf(label),
                        meta.getDistributionsPerLabelGold().get(label));
            }
        }
        JFreeChart someChart = null;
        if (isPredicted) {
            someChart = ChartFactory.createPieChart(
                    meta.getName() + " predicted label distribution", dataset, true, true, false);
        }
        else {
            someChart = ChartFactory.createPieChart(meta.getName() + " gold label distribution",
                    dataset, true, true, false);
        }

        PiePlot plot4 = (PiePlot) someChart.getPlot();
        plot4.setSimpleLabels(true);

        PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} = {2}",
                new DecimalFormat("0"), new DecimalFormat("0.00%"));
        plot4.setLabelGenerator(generator);

        try {
            if (isPredicted) {
                File target = new File(outputFolder, PREDICTED_FILE);
                ChartUtilities.saveChartAsJPEG(target, someChart, 1200, 1000);
                return target;
            }
            else {
                File target = new File(outputFolder, GOLD_FILE);
                ChartUtilities.saveChartAsJPEG(target, someChart, 1200, 1000);
                return target;
            }
        }
        catch (Exception e) {
            throw new Exception("Could not write chart to [" + outputFolder + "]");
        }
    }

}
