package puzzle.model.Accessor;

import puzzle.model.StateData;

public class NotAccessor extends Accessor<Boolean> {

    private Accessor<Boolean> accessor;

    public NotAccessor(Accessor<Boolean> accessor) {
        this.accessor = accessor;
    }

    @Override
    public Boolean get(StateData data) {
        return !accessor.get(data);
    }

    @Override
    public Accessor<Boolean> copy() {
        return new NotAccessor(accessor.copy());
    }
}
