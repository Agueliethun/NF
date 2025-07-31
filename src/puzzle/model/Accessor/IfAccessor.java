package puzzle.model.Accessor;

import puzzle.model.StateData;

public class IfAccessor <T extends Comparable<T>> extends Accessor<T> {

    private Accessor<Boolean> booleanAccessor;
    private Accessor<T> trueAccessor;
    private Accessor<T> falseAccessor;

    public IfAccessor() {
    }

    public IfAccessor(Accessor<Boolean> booleanAccessor, Accessor<T> trueAccessor, Accessor<T> falseAccessor) {
        this.booleanAccessor = booleanAccessor;
        this.trueAccessor = trueAccessor;
        this.falseAccessor = falseAccessor;
    }

    public Accessor<Boolean> getBooleanAccessor() {
        return booleanAccessor;
    }

    public void setBooleanAccessor(Accessor<Boolean> booleanAccessor) {
        this.booleanAccessor = booleanAccessor;
    }

    public Accessor<T> getTrueAccessor() {
        return trueAccessor;
    }

    public void setTrueAccessor(Accessor<T> trueAccessor) {
        this.trueAccessor = trueAccessor;
    }

    public Accessor<T> getFalseAccessor() {
        return falseAccessor;
    }

    public void setFalseAccessor(Accessor<T> falseAccessor) {
        this.falseAccessor = falseAccessor;
    }

    @Override
    public T get(StateData data) {
        return booleanAccessor.get(data) ? trueAccessor.get(data) : falseAccessor.get(data);
    }

    @Override
    public Accessor<T> copy() {
        return new IfAccessor<>(booleanAccessor.copy(), trueAccessor.copy(), falseAccessor.copy());
    }
}
