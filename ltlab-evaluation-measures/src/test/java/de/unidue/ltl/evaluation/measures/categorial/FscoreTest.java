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
package de.unidue.ltl.evaluation.measures.categorial;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.measures.categorial.Fscore;
import de.unidue.ltl.evaluation.measures.categorial.Precision;
import de.unidue.ltl.evaluation.measures.categorial.Recall;

public class FscoreTest {
	
    EvaluationData<String> data;
    @Before
    public void setup(){
        data = new EvaluationData<>();
        data.register("A", "B");
        data.register("B", "C");
        data.register("C", "A");
        data.register("A", "A");
        data.register("B", "A");
        data.register("C", "C");
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
	    double fmicro = 2 * p * r /(p+r);
	    assertEquals(new Fscore<>(data).getMicroFscore(), fmicro, 0.001);
	}
	
	
	@Test
	public void numericalTest(){
		EvaluationData<Integer> d = new EvaluationData<>();
		d.register(0, 0);
		d.register(1,2);
		d.register(2, 1);
		d.register(0, 0);
		d.register(1, 0);
		d.register(2, 1);
		
		Fscore<Integer> f = new Fscore<>(d);
		assertEquals(0.2666, f.getMacroFscore(), 0.001);
		assertEquals(0.3333, f.getMicroFscore(), 0.001);
		assertEquals(0.2666, f.getWeightedFscore(), 0.001);
	}

}
