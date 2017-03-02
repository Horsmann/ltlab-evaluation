package de.unidue.ltl.evaluation.measure.categorial;

class Category
{
    long tp;
    long fp;
    long fn;
    long tn;

    Category(long tp, long fp, long fn, long tn){
        this.tp = tp;
        this.fp = fp;
        this.fn = fn;
        this.tn = tn;
    }

}
