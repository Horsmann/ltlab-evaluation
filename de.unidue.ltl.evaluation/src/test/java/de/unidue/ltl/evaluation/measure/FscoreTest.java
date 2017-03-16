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
package de.unidue.ltl.evaluation.measure;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.measure.categorial.Fscore;
import de.unidue.ltl.evaluation.measure.categorial.Precision;
import de.unidue.ltl.evaluation.measure.categorial.Recall;

public class FscoreTest {
	
    EvaluationData<String> data;
    @Before
    public void setup(){
        Collection<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
        entries.add(new EvaluationEntry<String>("A", "B"));
        entries.add(new EvaluationEntry<String>("B", "C"));
        entries.add(new EvaluationEntry<String>("C", "A"));
        entries.add(new EvaluationEntry<String>("A", "A"));
        entries.add(new EvaluationEntry<String>("B", "A"));
        entries.add(new EvaluationEntry<String>("C", "C"));
        data = new EvaluationData<>(entries);
    }
    
	@Test
	public void fscoreTest(){
	    
        assertEquals(0.3333, new Fscore<>(data).getMicroFscore(), 0.0001);
        assertEquals(0.3030, new Fscore<>(data).getMacroFscore(), 0.0001);
        assertEquals(0.3030, new Fscore<>(data).getWeightedFscore(), 0.0001);
		assertEquals(0.4000, new Fscore<>(data).getScoreForLabel("A"), 0.001);
        assertEquals(0.0000, new Fscore<>(data).getScoreForLabel("B"), 0.001);
        assertEquals(0.5000, new Fscore<>(data).getScoreForLabel("C"), 0.001);
	}
	
	@Test
	public void crossEvaluation(){
	    
	    double p = new Precision<String>(data).getMicroPrecision();
	    double r = new Recall<String>(data).getMicroRecall();
	    
	}
	

}
