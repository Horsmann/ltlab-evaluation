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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.ConditionalFrequencyDistribution;
import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.core.EvaluationEntry;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ConfusionMatrix<T>
{
    Set<T> allLabels = new HashSet<>();
    ConditionalFrequencyDistribution<T, T> cfd = new ConditionalFrequencyDistribution<>();
    
    public ConfusionMatrix(EvaluationData<T> data){
        for(EvaluationEntry<T> e : data){
            T gold = e.getGold();
            T predicted = e.getPredicted();
            cfd.addSample(gold, predicted, 1);

            allLabels.add(gold);
            allLabels.add(predicted);
        }
    }

    public String toString()
    {
        List<T> labels = getLabels();
        int[][] array = getTwoDimensionalArray();
        
        V2_AsciiTable at = new V2_AsciiTable();

		at.addStrongRule();
        at.addRow(getTableHeader("Predicted"));
        at.addRow(getLabelHeader(labels));
		at.addStrongRule();

        for (int i=0; i<labels.size(); i++) {
        	T label = labels.get(i);
			Object[] values = new Object[labels.size()+1];
			values[0] = label;
	        for (int j=0; j<labels.size(); j++) {
	        	values[j+1] = array[i][j];
	        }
			at.addRow(values);
			at.addRule();
		}
		at.addStrongRule();
		
        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.NO_BORDERS.get());
		rend.setWidth(new WidthLongestWord());

        return rend.render(at).toString();
    }
    
    private Object[] getTableHeader(String title) {
    	Object[] header = new Object[allLabels.size()+1];
    	for (int i=0; i<header.length; i++) {
    			header[i] = null;
    	}
    	header[0] = "";
    	header[header.length-1] = title;
    	
    	return header;
    }
    
    private Object[] getLabelHeader(List<T> labels) {
    	List<Object> values = new ArrayList<>();
    	values.add("");
    	values.addAll(labels);
    	
    	return values.toArray();
    }
    
    public long getNumberOfEntries() {
    	return cfd.getN();
    }

    public long getNumberOfConfusions(T goldLabel, T confusedLabel) {
    	return cfd.getCount(goldLabel, confusedLabel);
    }
    
    public long getTruePositives(T label)
    {
        return cfd.getCount(label, label);
    }

    public long getFalseNegatives(T label)
    {
        FrequencyDistribution<T> fd = cfd.getFrequencyDistribution(label);
        long total = 0L;
        if(fd == null){
        	return total;
        }
        for (T key : fd.getKeys()) {
            if (!key.equals(label)) {
                total += fd.getCount(key);
            }
        }

        return total;
    }

    public long getFalsePositives(T label)
    {
        long total = 0L;
        for (T c : cfd.getConditions()) {
            if (!c.equals(label)) {
                total += cfd.getFrequencyDistribution(c).getCount(label);
            }
        }
        return total;
    }

    public long getTrueNegatives(T label)
    {
        long total = 0L;
        for (T c : cfd.getConditions()) {
            if (!c.equals(label)) {
                FrequencyDistribution<T> fd = cfd.getFrequencyDistribution(c);
                for (T key : fd.getKeys()) {
                    if (!key.equals(label)) {
                        total += fd.getCount(key);
                    }
                }
            }
        }
        return total;
    }
    
    /**
     * @return A list of the alphabetically sorted labels that are registered in this ConfusionMatrix.
     */
    public List<T> getLabels() {
        List<T> labels = new ArrayList<T>(allLabels);
        Collections.sort(labels, new Comparator<T>()
        {

            @Override
            public int compare(T o1, T o2)
            {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1.toString().compareTo(o2.toString());
            }
        });
        return labels;
    }
    
    public int[][] getTwoDimensionalArray() {
    	List<T> labels = getLabels();
    	int n = labels.size();
    	
        int[][] array =  new int[n][n];

        int i=0;
        for (T key : labels) {
            FrequencyDistribution<T> fd = cfd.getFrequencyDistribution(key);
            int j=0;
            for (T t : labels) {
            	if (fd != null) {
                	array[i][j] = Long.valueOf(fd.getCount(t)).intValue();
            	}
            	else {
                	array[i][j] = 0;
            	}
                j++;
            }
        	i++;
        }
        
        return array;
    }
}
