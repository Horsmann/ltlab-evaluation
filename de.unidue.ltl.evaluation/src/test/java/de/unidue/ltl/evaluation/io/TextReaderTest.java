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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.unidue.ltl.evaluation.Evaluation;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class TextReaderTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void fromTabSeparatedTest() 
			throws Exception
	{
		File tempFile = new File(folder.newFolder(), "tab-separated_gold2predicted.txt");
		
		List<EvaluationEntry<String>> entries= new ArrayList<>();
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		
		StringBuilder sb = new StringBuilder();
		for (EvaluationEntry<String> entry: entries){
			sb.append(entry);
			sb.append("\n");
		}
		FileUtils.writeStringToFile(tempFile, sb.toString());
		
		assertEquals(
				FileUtils.readFileToString(tempFile),
				FileUtils.readFileToString(new File("src/test/resources/io/tab-separated_gold2predicted.txt"))
		);
		
		Evaluation<String> evaluation = TextReader.read(tempFile);
		Collection<EvaluationEntry<String>> entriesFromFile = evaluation.getEntries();
		assertEquals(4, entriesFromFile.size());
		
		int i=0;
		for (EvaluationEntry<String> entry: entriesFromFile) {
			assertEquals(entry.getGold(), entries.get(i).getGold());
			assertEquals(entry.getPredicted(), entries.get(i).getPredicted());
			i++;
		}
	}
}
