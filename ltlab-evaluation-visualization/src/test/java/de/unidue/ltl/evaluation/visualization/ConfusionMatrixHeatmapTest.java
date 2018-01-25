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

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.unidue.ltl.evaluation.core.EvaluationData;

public class ConfusionMatrixHeatmapTest {

	@Test
	public void confusionMatrixHeatmapTest() 
			throws IOException
	{
		EvaluationData<String> eval = new EvaluationData<>(TestUtils.getRandomCategorialEntries(100, 6));
		
		ConfusionMatrixHeatmap cmh = new ConfusionMatrixHeatmap(eval);
		cmh.writePlot(new File("target/test.png"));
	}
}
