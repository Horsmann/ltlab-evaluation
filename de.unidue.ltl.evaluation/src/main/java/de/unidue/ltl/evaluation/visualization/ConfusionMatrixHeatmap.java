package de.unidue.ltl.evaluation.visualization;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

import de.unidue.ltl.evaluation.ConfusionMatrix;

public class ConfusionMatrixHeatmap {

	private ConfusionMatrix<String> cf;
	private List<String> labels;
	
	public ConfusionMatrixHeatmap(ConfusionMatrix<String> confusionMatrix) {
		cf = confusionMatrix;
		labels = cf.getLabels();
	}

	public void writePlot(File targetFile) 
			throws IOException 
	{	
		DecimalFormat formatter = new DecimalFormat();
	    formatter.setMaximumFractionDigits(2);
	    formatter.setMinimumFractionDigits(2);
	
	    XYBlockRenderer renderer = new XYBlockRenderer();
	    renderer.setBlockHeight(1.0);
	    renderer.setBlockWidth(1.0);
	    renderer.setPaintScale(new GrayPaintScale(0.0, 1.0));
	    
	    for (int i = 0; i < labels.size(); i++) {
	        renderer.setSeriesShape(i, null);
	        renderer.setSeriesCreateEntities(i, false);
	    }
	
	    final NumberAxis axisA = new NumberAxis("Gold");
	    final NumberAxis axisB = new NumberAxis("Predicted");
	    
	    axisA.setTickUnit(new NumberTickUnit(1.0));
	    axisB.setTickUnit(new NumberTickUnit(1.0));
	    axisA.setRange(0.5, labels.size() + 0.5);
	    axisB.setRange(0.5, labels.size() + 0.5);
	    
	    axisB.setInverted(true);
	    XYZDataset dataset = getDataset();
	    final XYPlot aPlot = new XYPlot(dataset, axisA, axisB, renderer);
	    
	    aPlot.setOutlinePaint(Color.black);
	    
	    for (int i = 0; i < labels.size(); i++) {
	        for (int j = 0; j < labels.size(); j++) {
	        		Double textValue = 1.0 - dataset.getZValue(i, j);
	        		XYTextAnnotation cellText = new XYTextAnnotation(
	                		formatter.format(textValue),
	                		dataset.getXValue(i,j),
	                		dataset.getYValue(i,j)
	                );
	        		cellText.setPaint(Color.black);
	                aPlot.addAnnotation(cellText);
	        }
	    }
	
	    //create chart
	    JFreeChart chart = new JFreeChart(
	    		"Confusion Matrix Heatmap",
	    		JFreeChart.DEFAULT_TITLE_FONT,
	    		aPlot,
	    		false
	    );
	    
	    chart.setBackgroundPaint(Color.white);
	    
	    final ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
	    panel.setPreferredSize(new java.awt.Dimension(900, 850));
	
        ChartUtilities.writeChartAsPNG( new FileOutputStream(targetFile),
                chart, 50*labels.size(), 40*labels.size());
	}

	private XYZDataset getDataset() {
	    final DefaultXYZDataset data = new DefaultXYZDataset();
	    
	    for (int i=0; i<labels.size(); i++) {
	    	String label = labels.get(i);
	    	double[][] series = getSeries(label, i, cf.getNumberOfEntries());
	        data.addSeries(i + ": " + label, series);
	    }
	    
	    return data;
	}
	
	private double[][] getSeries(String label, int i, long n) {
		
		double[][] series = new double[3][labels.size()];
		for (int j=0; j<labels.size(); j++) {
			String currentConfusionLabel = labels.get(j);
				
			double confusionRatio = 0.0;
			if (!label.equals(currentConfusionLabel)) {
				confusionRatio = (double) cf.getNumberOfConfusions(label, currentConfusionLabel) / n;
			}
			series[0][j] = i+1;
			series[1][j] = j+1;
			series[2][j] = 1 - confusionRatio;
		}
		
		return series;
	}
}
