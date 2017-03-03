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

import de.unidue.ltl.evaluation.EvaluationData;

public class ErrorDistributionChartTest {

	@Test
	public void errorDistributionTest() throws IOException {
		EvaluationData<String> eval = new EvaluationData<>();
		eval.register("A", "C");
		eval.register("B", "A");
		eval.register("B", "B");
		eval.register("A", "B");
		eval.register("C", "A");
		eval.register("A", "A");
		ErrorDistributionChart<String> chart = new ErrorDistributionChart<>();
		chart.plotDistribution(eval, new File("target/testDis.png"));
	}
}
