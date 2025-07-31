package puzzle.model.Accessor;

import puzzle.model.StateData;

public class ConstantAccessor <T extends Comparable<T>> extends Accessor<T> {

    private T t;

    public ConstantAccessor(T t) {
        this.t = t;
    }

    @Override
    public T get(StateData data) {
        return t;
    }

    @Override
    public ConstantAccessor<T> copy() {
        return new ConstantAccessor<>(t);
    }
}
