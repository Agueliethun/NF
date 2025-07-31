package puzzle.model.Accessor;

import puzzle.model.StateData;
import puzzle.util.Copyable;

public abstract class Accessor <T extends Comparable<T>> implements Copyable<Accessor<T>> {

    public abstract T get(StateData data);
    public abstract Accessor<T> copy();
}
