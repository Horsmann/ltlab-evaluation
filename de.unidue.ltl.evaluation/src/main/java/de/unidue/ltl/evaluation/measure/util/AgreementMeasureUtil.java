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
package de.unidue.ltl.evaluation.measure.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.dkpro.statistics.agreement.coding.BennettSAgreement;
import org.dkpro.statistics.agreement.coding.CodingAnnotationStudy;
import org.dkpro.statistics.agreement.coding.CohenKappaAgreement;
import org.dkpro.statistics.agreement.coding.FleissKappaAgreement;
import org.dkpro.statistics.agreement.coding.KrippendorffAlphaAgreement;
import org.dkpro.statistics.agreement.coding.RandolphKappaAgreement;
import org.dkpro.statistics.agreement.coding.ScottPiAgreement;
import org.dkpro.statistics.agreement.distance.OrdinalDistanceFunction;

import de.unidue.ltl.evaluation.EvaluationEntry;
import de.unidue.ltl.evaluation.EvaluationResult;
import de.unidue.ltl.evaluation.measure.agreement.BennettS;
import de.unidue.ltl.evaluation.measure.agreement.CohenKappa;
import de.unidue.ltl.evaluation.measure.agreement.FleissKappa;
import de.unidue.ltl.evaluation.measure.agreement.KrippendorffAlpha;
import de.unidue.ltl.evaluation.measure.agreement.RandolphKappa;
import de.unidue.ltl.evaluation.measure.agreement.ScottPi;

public class AgreementMeasureUtil {

	public static Map<String, EvaluationResult> computeAgreementResults(Collection<EvaluationEntry<String>> entries) {
		Map<String, EvaluationResult> results = new HashMap<>();
		
		CodingAnnotationStudy study = new CodingAnnotationStudy(2);
		for (EvaluationEntry<String> entry : entries) {
			study.addItem(entry.getGold(), entry.getPredicted());
		}
			
		BennettSAgreement bennetS                   = new BennettSAgreement(study);
		CohenKappaAgreement cohenKappa              = new CohenKappaAgreement(study);
		FleissKappaAgreement fleissKappa            = new FleissKappaAgreement(study);
		KrippendorffAlphaAgreement krippendorfAlpha = new KrippendorffAlphaAgreement(study, new OrdinalDistanceFunction());
		RandolphKappaAgreement randolphKappa        = new RandolphKappaAgreement(study);
		ScottPiAgreement scottPi                    = new ScottPiAgreement(study);

		
		results.put(BennettS.class.getSimpleName(),          new EvaluationResult(bennetS.calculateAgreement()));
		results.put(CohenKappa.class.getSimpleName(),        new EvaluationResult(cohenKappa.calculateAgreement()));
		results.put(FleissKappa.class.getSimpleName(),       new EvaluationResult(fleissKappa.calculateAgreement()));
		results.put(KrippendorffAlpha.class.getSimpleName(), new EvaluationResult(krippendorfAlpha.calculateAgreement()));
		results.put(RandolphKappa.class.getSimpleName(),     new EvaluationResult(randolphKappa.calculateAgreement()));
		results.put(ScottPi.class.getSimpleName(),           new EvaluationResult(scottPi.calculateAgreement()));
		
		return results;
	}
}
