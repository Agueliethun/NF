package puzzle.model.Action.StateModifier;

import puzzle.model.Accessor.Accessor;
import puzzle.model.State;
import puzzle.model.StateData;

public class AccessorModifier extends StateModifier {
    private Accessor accessor;

    public AccessorModifier(String key, Accessor accessor) {
        super(key);
        this.accessor = accessor;
    }

    @Override
    public void modify(StateData data, StateData context) {
        data.put(getKey(), accessor.get(context));
    }

    @Override
    public StateModifier copy() {
        return new AccessorModifier(getKey(), accessor.copy());
    }
}
