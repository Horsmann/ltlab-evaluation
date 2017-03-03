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
package de.unidue.ltl.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EvaluationData<T>
    implements Iterable<EvaluationEntry<T>>
{

    private List<EvaluationEntry<T>> entries;
    private EvaluationMetaData<T> meta = null;

    public EvaluationData()
    {
        this.entries = new ArrayList<>();
        this.meta = new EvaluationMetaData<T>();
    }

    public EvaluationData(Collection<EvaluationEntry<T>> entries)
    {
        this.entries = new ArrayList<>(entries);
        this.meta = new EvaluationMetaData<>(EvaluationMetaData.DEFAULT_NAME, entries);
    }

    public void register(T gold, T predicted)
    {
        EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, predicted);
        entries.add(entry);
    }

    public void setMetaData(EvaluationMetaData<T> meta)
    {
        this.meta = meta;
    }
    
    
    public void setName(String name){
    	this.meta.setName(name);
    }

    @Override
    public Iterator<EvaluationEntry<T>> iterator()
    {
        return new Iterator<EvaluationEntry<T>>()
        {

            List<EvaluationEntry<T>> data = new ArrayList<>(entries);
            int idx = 0;

            @Override
            public boolean hasNext()
            {
                return idx < data.size();
            }

            @Override
            public EvaluationEntry<T> next()
            {
                return data.get(idx++);
            }
        };
    }

    public long size()
    {
        return entries.size();
    }

    public EvaluationEntry<T> get(int idx)
    {
        return entries.get(idx);
    }

    public EvaluationMetaData<T> getMetaData()
    {
        return meta;
    }

}