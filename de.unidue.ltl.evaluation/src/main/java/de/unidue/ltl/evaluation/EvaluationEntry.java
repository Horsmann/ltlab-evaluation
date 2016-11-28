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
package de.unidue.ltl.evaluation;

public class EvaluationEntry {
	private String gold;
	private String predicted;
	
	public EvaluationEntry(String gold, String predicted) {
		this.gold = gold;
		this.predicted = predicted;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getPredicted() {
		return predicted;
	}
	public void setPredicted(String predicted) {
		this.predicted = predicted;
	}

}
