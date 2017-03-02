package de.unidue.ltl.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EvaluationData<T> implements Iterable<EvaluationEntry<T>>{

	private Collection<EvaluationEntry<T>> entries;
    private EvaluationMetaData<T> metaData;

	public EvaluationData(EvaluationMetaData<T> metaData) {
		this.metaData = metaData;
        this.entries = new ArrayList<>();
	}
	
	public EvaluationData(Collection<EvaluationEntry<T>> entries) {
		this.entries = entries;
	}
	
	public void register(T gold, T predicted) {
			EvaluationEntry<T> entry = new EvaluationEntry<T>(gold, predicted);
			entries.add(entry);
		}

	@Override
	public Iterator<EvaluationEntry<T>> iterator() {
		return new Iterator<EvaluationEntry<T>>() {

			List<EvaluationEntry<T>> data = new ArrayList<>(entries);
			int idx=0;
			
			@Override
			public boolean hasNext() {
				return idx < data.size();
			}

			@Override
			public EvaluationEntry<T> next() {
				return data.get(idx++);
			}
		};
	}
}