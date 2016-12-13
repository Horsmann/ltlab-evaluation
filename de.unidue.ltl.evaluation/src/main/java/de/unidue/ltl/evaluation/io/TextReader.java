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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import de.unidue.ltl.evaluation.Evaluation;

public class TextReader{

	public static Evaluation<String> read(File txtFile) throws IOException{
		Evaluation<String> evaluation= new Evaluation<>();
		for(String line:FileUtils.readLines(txtFile)){
			String[] toRegister= line.split("\t");
			evaluation.register(toRegister[0], toRegister[1]);
		}
		return evaluation;
	}
	
}
