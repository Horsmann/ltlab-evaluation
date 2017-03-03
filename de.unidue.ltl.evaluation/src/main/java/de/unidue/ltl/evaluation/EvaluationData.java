package de.unidue.ltl.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EvaluationData<T>
    implements Iterable<EvaluationEntry<T>>
{

    private List<EvaluationEntry<T>> entries;
    private String name = "<no-name-set>";

    public EvaluationData()
    {
        this.entries = new ArrayList<>();
    }

    public EvaluationData(Collection<EvaluationEntry<T>> entries)
    {
        this.entries = new ArrayList<>(entries);
    }

    public EvaluationData(Collection<EvaluationEntry<T>> entries, String name)
    {
        this.name = name;
        this.entries = new ArrayList<>(entries);
    }

    public EvaluationData(String name)
    {
        this.name = name;
    }

    public void register(T gold, T predicted)
    {
        EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, predicted);
        entries.add(entry);
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

    public String getName()
    {
        return name;
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
        return null;
    }
}