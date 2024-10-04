package puzzle.model.Action;

import puzzle.model.Action.Signal.Signal;
import puzzle.model.Action.StateModifier.StateModifier;
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
        stateModifiers.forEach(modifier -> {
            modifier.modify(object.getState());
        });
    }

    @Override
    public StateChangeAction copy() {
        return new StateChangeAction(ObjectUtil.copyList(stateModifiers));
    }
}