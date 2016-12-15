/*******************************************************************************
 * Copyright 2016
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
package de.unidue.ltl.evaluation.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.resource.ResourceInitializationException;

import de.unidue.ltl.evaluation.Evaluation;

public class TcId2OutcomeReader {
	public static Evaluation<String> read(File id2OutcomeFile) throws ResourceInitializationException{
		Evaluation<String> evaluation= new Evaluation<>();
		evaluation=registerId2OutcomePairs(evaluation,id2OutcomeFile);
		return evaluation;
	}
	
	private static Evaluation<String> registerId2OutcomePairs(Evaluation<String> evaluation, File id2OutcomeFile) throws ResourceInitializationException {
		List<String> labels=null;
		try (BufferedReader br = new BufferedReader(new FileReader(id2OutcomeFile))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if(line.startsWith("#labels")){
					labels=getLabels(line);
				}
				if (!line.startsWith("#")) {
					String prediction = line.split(";")[0];
					String gold = line.split(";")[1];
					int indexOfOnePredicted=getIndexOfOne(prediction.split("=")[1]);
					int indexOfOneGold=getIndexOfOne(gold);
					String labelPredicted =labels.get(indexOfOnePredicted);
					String labelGold=labels.get(indexOfOneGold);
					evaluation.register(labelGold, labelPredicted);
				}
			}
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		return evaluation;
	}

	public static Evaluation<String> readMultipleFiles(List<File> id2OutcomeFiles) throws ResourceInitializationException{
		Evaluation<String> evaluation = new Evaluation<>();
		for (File id2OutcomeFile : id2OutcomeFiles) {
			evaluation=registerId2OutcomePairs(evaluation,id2OutcomeFile);
		}
		return evaluation;
	}
	
	/**
     * Retrieves the list of labels from the respective header line of the id2outcome file, sorted
     * according to their index.
     * 
     * @param line
     *            the line of the id2outcome file which contains the labels and their indices
     * @return a list of labels sorted by index, ascending
     * @throws UnsupportedEncodingException
     */
    private static List<String> getLabels(String line)
        throws UnsupportedEncodingException
    {
        String[] numberedClasses = line.split(" ");
        List<String> labels = new ArrayList<String>();

        for (int i = 1; i < numberedClasses.length; i++) {
            String className = numberedClasses[i].split("=")[1];
            labels.add(URLDecoder.decode(className, "UTF-8"));
        }
        return labels;
    }
    
    /**
     * returns the first position in the vector that is marked with a on
     * @param resultVector
     * @return
     * @throws Exception
     */
    private static int getIndexOfOne(String resultVector) throws Exception {
		int i=0;
		for(String dim:resultVector.split(",")){
			if(dim.equals("1")){
				return i;
			}
			i++;
		}
		throw new Exception(resultVector+"does not contain a 1");
	}

}
