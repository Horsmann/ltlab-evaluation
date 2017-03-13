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
package de.unidue.ltl.evaluation;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class EvaluationMetaData<T>
{
    public static final String DEFAULT_NAME = "no-name-set";
    private List<T> labels;
    private Map<T, Integer> distributionsPerLabelPredicted;
    private Map<T, Integer> distributionsPerLabelGold;
    private long numberOfEntries;
    private String name;

    public EvaluationMetaData(String name, Iterable<EvaluationEntry<T>> data)
    {
        this.name = name;
        this.labels = getDistinctLabels(data);
        this.distributionsPerLabelPredicted = getDistributionsPerLabel(data,true);
        this.distributionsPerLabelGold = getDistributionsPerLabel(data,false);
        this.numberOfEntries = getEntryCount(data);
    }
    
    private long getEntryCount(Iterable<EvaluationEntry<T>> data)
    {
        int count=0;
        Iterator<EvaluationEntry<T>> iterator = data.iterator();
        while(iterator.hasNext()){
            count++;
            iterator.next();
        }
        return count;
    }

    public EvaluationMetaData()
    {
        this.name = DEFAULT_NAME;
        
        List<EvaluationEntry<T>> emptyList = new ArrayList<>();
        this.labels = getDistinctLabels(emptyList);
        this.distributionsPerLabelPredicted = getDistributionsPerLabel(emptyList,true);
        this.distributionsPerLabelGold = getDistributionsPerLabel(emptyList,false);
        this.numberOfEntries = emptyList.size();
    }

    private List<T> getDistinctLabels(Iterable<EvaluationEntry<T>> data)
    {
        Set<T> labels = new HashSet<>();
        for(EvaluationEntry<T> e : data){
            labels.add(e.getGold());
            labels.add(e.getPredicted());
        }
        
        return new ArrayList<T>(labels);
    }

    public void getPieChart() throws Exception
    {
        getPieChart(true);
        getPieChart(false);
    }

    public void getPieChart(boolean isPredicted) throws Exception
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (isPredicted) {
            for (T label : distributionsPerLabelPredicted.keySet()) {
                dataset.setValue(String.valueOf(label), distributionsPerLabelPredicted.get(label));
            }
        }
        else {
            for (T label : distributionsPerLabelGold.keySet()) {
                dataset.setValue(String.valueOf(label), distributionsPerLabelGold.get(label));
            }
        }
        JFreeChart someChart = null;
        if (isPredicted) {
            someChart = ChartFactory.createPieChart(name + " predicted label distribution", dataset,
                    true, true, false);
        }
        else {
            someChart = ChartFactory.createPieChart(name + " gold label distribution", dataset,
                    true, true, false);
        }

        PiePlot plot4 = (PiePlot) someChart.getPlot();
        plot4.setSimpleLabels(true);

        PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} = {2}",
                new DecimalFormat("0"), new DecimalFormat("0.00%"));
        plot4.setLabelGenerator(generator);

        try {
            if (isPredicted) {
                ChartUtilities.saveChartAsJPEG(new File("target/pieChart_predicted.jpeg"),
                        someChart, 1200, 1000);
            }
            else {
                ChartUtilities.saveChartAsJPEG(new File("target/pieChart_gold.jpeg"), someChart,
                        1200, 1000);
            }
        }
        catch (Exception e) {
           throw new Exception("couldn't write chart");
        }
    }

    public String getStats()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("Name:" + "\t" + name + System.lineSeparator());
        sb.append("Labels:" + "\t" + labels + System.lineSeparator());
        sb.append("# of Instances:" + "\t" + numberOfEntries + System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("Distribution of Gold Labels:" + System.lineSeparator());
        sb.append("\t" + "L : #" + System.lineSeparator());
        for (T key : distributionsPerLabelGold.keySet()) {
            sb.append("\t" + key + " : " + distributionsPerLabelGold.get(key)
                    + System.lineSeparator());
        }
        sb.append("Distribution of Predicted Labels:" + System.lineSeparator());
        sb.append("\t" + "L : #" + System.lineSeparator());
        for (T key : distributionsPerLabelPredicted.keySet()) {
            sb.append("\t" + key + " : " + distributionsPerLabelPredicted.get(key)
                    + System.lineSeparator());
        }

        return sb.toString();
    }

    private Map<T, Integer> getDistributionsPerLabel(Iterable<EvaluationEntry<T>> data, boolean isPredicted)
    {
        Map<T, Integer> distributionsPerLabel = new HashMap<>();

        for (T label : labels) {
            int count = getCount4label(data, label, isPredicted);
            distributionsPerLabel.put(label, count);
        }
        return distributionsPerLabel;
    }

    private int getCount4label(Iterable<EvaluationEntry<T>> entries, T label, boolean isPredicted)
    {
        int counter = 0;
        for (EvaluationEntry<T> entry : entries) {
            if (isPredicted) {
                if (entry.getPredicted().equals(label))
                    counter++;
            }
            else {
                if (entry.getGold().equals(label))
                    counter++;
            }
        }
        return counter;
    }

    public String getName()
    {
        if(name==null){
            return "no-name-set";
        }
        return name;
    }

    public List<T> getLabels()
    {
        return labels;
    }
    
    public Map<T, Integer> getDistributionsPerLabelPredicted()
    {
        return distributionsPerLabelPredicted;
    }

    public Map<T, Integer> getDistributionsPerLabelGold()
    {
        return distributionsPerLabelGold;
    }

	public void setName(String name) {
		this.name = name;
	}

}
