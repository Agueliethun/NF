package puzzle.model.Action.Signal.Provider;

import puzzle.model.Accessor.Accessor;
import puzzle.model.Action.Signal.Propagation.SimplePropagation;
import puzzle.model.Action.Signal.Signal;
import puzzle.model.Position;
import puzzle.model.StateData;

import java.util.HashMap;
import java.util.Map;

public class DynamicSignalProvider extends SignalProvider {

    private Map<String, Accessor> newData;

    public DynamicSignalProvider() {
        newData = new HashMap<>();
    }

    public DynamicSignalProvider(Map<String, Accessor> newData) {
        this.newData = newData;
    }

    public Map<String, Accessor> getNewData() {
        return newData;
    }

    public void setNewData(Map<String, Accessor> newData) {
        this.newData = newData;
    }

    @Override
    public Signal getSignal(StateData data) {
        Signal s = new Signal();
        newData.forEach((key, value) -> s.getState().put(key, value.get(data)));
        return s;
    }

    @Override
    public SignalProvider copy() {
        Map<String, Accessor> newMap = new HashMap<>();
        newData.forEach((key, value) -> newMap.put(key, value.copy()));
        return new DynamicSignalProvider(newMap);
    }
}
