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

package de.unidue.ltl.evaluation.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.io.TcId2OutcomeReader;

public class Id2OutcomeReaderTest {
    
    File datafile = new File("src/test/resources/io/id2Outcome_gunshot.txt");
	
    @Test
	public void testReadFile()
			throws Exception
	{
	    EvaluationData<String> evaluation = TcId2OutcomeReader.read(datafile);
	    assertEquals(821, evaluation.size());
	}
    
    @Test
    public void testReadMultipleFile()
            throws Exception
    {
        EvaluationData<String> evaluation = TcId2OutcomeReader.readMultipleFiles(datafile,datafile,datafile);
        assertEquals(821*3, evaluation.size());
    }
}
