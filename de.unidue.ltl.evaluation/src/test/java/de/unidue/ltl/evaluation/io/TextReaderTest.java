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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class TextReaderTest {

	@Test
	public void fromTabSeparatedTest() throws IOException{
		File testFile=new File("src/test/resources/io/tab-separated_gold2predicted.txt");
		List<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		for(EvaluationEntry entry: entries){
			FileUtils.write(testFile, entry.getGold()+"\t"+entry.getPredicted()+"\n", true);
		}
		
		Evaluation<String> evaluation= TextReader.fromTabSeparated(testFile);
		int i=0;
		for(EvaluationEntry entry: evaluation.getEntries()){
			assertEquals(entry.getGold(),entries.get(i).getGold());
			assertEquals(entry.getPredicted(),entries.get(i).getPredicted());
			i++;
		}
		
	}
}
