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
package de.unidue.ltl.evaluation.measure.agreement;

import org.dkpro.statistics.agreement.coding.CodingAnnotationStudy;
import org.dkpro.statistics.agreement.coding.KrippendorffAlphaAgreement;
import org.dkpro.statistics.agreement.distance.OrdinalDistanceFunction;

import de.unidue.ltl.evaluation.EvaluationData;
import de.unidue.ltl.evaluation.EvaluationEntry;

public class KrippendorffAlpha<T>
    extends AgreementMeasure<T>
{
    boolean didCalculate = false;
    double calculateAgreement;

    public KrippendorffAlpha(EvaluationData<T> data)
    {
        super(data);
    }

    void calculate()
    {
        if (didCalculate) {
            return;
        }

        CodingAnnotationStudy study = new CodingAnnotationStudy(2);
        for (EvaluationEntry<T> entry : data) {
            study.addItem(entry.getGold(), entry.getPredicted());
        }

        KrippendorffAlphaAgreement agreement = new KrippendorffAlphaAgreement(study,
                new OrdinalDistanceFunction());
        calculateAgreement = agreement.calculateAgreement();

        didCalculate = true;
    }

    @Override
    public double getAgreement()
    {
        if (!didCalculate) {
            calculate();
        }

        return calculateAgreement;
    }
}
