package puzzle.model.Action;

import puzzle.model.Action.Signal.Signal;
import puzzle.model.Action.StateModifier.StateModifier;
import puzzle.model.StateData;
import puzzle.model.StatefulObject;
import puzzle.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class StateChangeAction extends Action {
    private List<StateModifier> stateModifiers;

    public StateChangeAction() {
        stateModifiers = new ArrayList<>();
    }

    public StateChangeAction(List<StateModifier> stateModifiers) {
        this.stateModifiers = stateModifiers;
    }

    public void apply(Signal signal, StatefulObject object) {
        StateData newData = new StateData();
        newData.put("signal", signal.getState());
        newData.put("object", object.getState());

        stateModifiers.forEach(modifier -> {
            modifier.modify(object.getState(), newData);
        });
    }

    @Override
    public StateChangeAction copy() {
        return new StateChangeAction(ObjectUtil.copyList(stateModifiers));
    }
}