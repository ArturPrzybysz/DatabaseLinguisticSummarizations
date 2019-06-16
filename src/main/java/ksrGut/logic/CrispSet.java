package ksrGut.logic;

import ksrGut.data.Tuple;
import tech.tablesaw.api.Table;

import java.util.HashSet;
import java.util.Set;

public class CrispSet {
    private CrispSet space;
    private Set<Tuple> elements = new HashSet<>();

    public CrispSet(Table t) {
        this.space = null;
        t.forEach(row -> elements.add(new Tuple(row)));
    }

    public CrispSet(Set<Tuple> elements, CrispSet space) {
        this.elements = elements;
        this.space = space;
    }

    CrispSet complement() {
        Set<Tuple> complementElements = new HashSet<>(space.getElements());
        complementElements.removeAll(elements);
        return new CrispSet(complementElements, space);
    }

    CrispSet sum(CrispSet set) {
        Set<Tuple> sumElements = new HashSet<>(elements);
        sumElements.addAll(set.getElements());
        return new CrispSet(sumElements, space);
    }

    CrispSet intersection(CrispSet set) {
        Set<Tuple> intersectionElements = new HashSet<>(elements);
        intersectionElements.retainAll(set.getElements());
        return new CrispSet(intersectionElements, space);
    }

    public Set<Tuple> getElements() {
        return elements;
    }

}
