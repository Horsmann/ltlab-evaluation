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