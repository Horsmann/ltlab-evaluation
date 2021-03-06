/*******************************************************************************
 * Copyright 2018
 * Language Technology Lab
 * University of Duisburg-Essen
 *
 * Licensed under the Apache License, Version 2.0.0.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0.0.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.unidue.ltl.evaluation.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import de.unidue.ltl.evaluation.core.EvaluationEntry;

public class TestUtils {

	public static Collection<EvaluationEntry<Double>> getRandomGaussianEntries(int n) {
		Random rand = new Random();
		List<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		for (int i = 0; i < n; i++) {
			entries.add(new EvaluationEntry<Double>(rand.nextGaussian(), rand.nextGaussian()));
		}
		return entries;
	}

	public static Collection<EvaluationEntry<String>> getRandomCategorialEntries(int n, int k) {
		Random rand = new Random();
		List<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
		for (int i = 0; i < n; i++) {
			String label1 = new Integer(rand.nextInt(k)).toString();
			String label2 = new Integer(rand.nextInt(k)).toString();
			entries.add(new EvaluationEntry<String>(label1, label2));
		}
		return entries;
	}

	public static Collection<EvaluationEntry<String>> getExampleCategorial() {
		Collection<EvaluationEntry<String>> entries = new ArrayList<EvaluationEntry<String>>();
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "A"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "B"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("A", "C"));
		entries.add(new EvaluationEntry<String>("B", "A"));
		entries.add(new EvaluationEntry<String>("B", "A"));
		entries.add(new EvaluationEntry<String>("B", "A"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("B", "B"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "A"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "B"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		entries.add(new EvaluationEntry<String>("C", "C"));
		return entries;
	}

	// TODO if all the doubles are exactly the same, this is more like a
	// categorial example
	public static Collection<EvaluationEntry<Double>> getExampleNumeric() {
		Collection<EvaluationEntry<Double>> entries = new ArrayList<EvaluationEntry<Double>>();
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 0.0));
		entries.add(new EvaluationEntry<Double>(0.0, 1.0));
		entries.add(new EvaluationEntry<Double>(0.0, 1.0));
		entries.add(new EvaluationEntry<Double>(0.0, 1.0));
		entries.add(new EvaluationEntry<Double>(0.0, 1.0));
		entries.add(new EvaluationEntry<Double>(0.0, 1.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(0.0, 2.0));
		entries.add(new EvaluationEntry<Double>(1.0, 0.0));
		entries.add(new EvaluationEntry<Double>(1.0, 0.0));
		entries.add(new EvaluationEntry<Double>(1.0, 0.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(1.0, 1.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 0.0));
		entries.add(new EvaluationEntry<Double>(2.0, 1.0));
		entries.add(new EvaluationEntry<Double>(2.0, 1.0));
		entries.add(new EvaluationEntry<Double>(2.0, 1.0));
		entries.add(new EvaluationEntry<Double>(2.0, 1.0));
		entries.add(new EvaluationEntry<Double>(2.0, 1.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		entries.add(new EvaluationEntry<Double>(2.0, 2.0));
		return entries;
	}

}
