package puzzle.model.Action.StateModifier;

import puzzle.model.StateData;
import puzzle.util.Copyable;

public abstract class StateModifier implements Copyable<StateModifier> {
    private String key;

    public StateModifier() {
        key = "";
    }

    public StateModifier(String key) {
        this.key = key;
    }

    public abstract void modify(StateData data);

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
