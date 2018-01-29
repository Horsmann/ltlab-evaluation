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
package de.unidue.ltl.evaluation.core;

import java.util.List;

public abstract class AbstractConfusionMatrix<T> {

	public abstract long getTruePositives(T label);

	public abstract long getFalseNegatives(T label);

	public abstract long getFalsePositives(T label);

	public abstract long getTrueNegatives(T label);
	
	public abstract String toText(); 
	
	public abstract List<T> getLabels();
	
	public abstract int [][] getTwoDimensionalArray();

}
