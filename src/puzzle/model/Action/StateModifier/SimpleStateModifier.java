package puzzle.model.Action.StateModifier;

import puzzle.model.StateData;

public class SimpleStateModifier extends StateModifier {
    private Comparable value;

    public SimpleStateModifier() {
        super("");
        this.value = "";
    }

    public SimpleStateModifier(String key, Comparable value) {
        super(key);
        this.value = value;
    }

    @Override
    public void modify(StateData data, StateData context) {
        data.put(getKey(), value);
    }

    @Override
    public SimpleStateModifier copy() {
        return new SimpleStateModifier(getKey(), value);
    }
}