package puzzle.model.Action;

import puzzle.model.Accessor.Accessor;
import puzzle.model.Position;
import puzzle.model.Action.Signal.Signal;
import puzzle.model.Action.Signal.Provider.SignalProvider;
import puzzle.model.StateData;
import puzzle.model.StatefulObject;
import puzzle.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class SignalAction extends Action {

    private List<SignalProvider> signalProviders;
    private Accessor<Position> positionAccessor;

    public SignalAction() {
        signalProviders = new ArrayList<>();
    }

    public SignalAction(List<SignalProvider> signalProviders, Accessor<Position> positionAccessor) {
        this.signalProviders = signalProviders;
        this.positionAccessor = positionAccessor;
    }

    public void apply(Signal signal, StatefulObject object) {
        StateData combined = new StateData();
        combined.put("signal", signal.getState());
        combined.put("object", object.getState());

        for (SignalProvider signalProvider : signalProviders) {
            if (signalProvider != null) {
                Signal newSignal = signalProvider.getSignal(combined);
                Position newPosition = object.getPosition().copy();
                        newPosition.offset(positionAccessor.get(combined));

                List<StatefulObject> objects = object.getParentState().getObjects().get(newPosition);

                if (objects != null) {
                    objects.forEach(o -> o.apply(newSignal));
                }
            }
        }
    }

    public List<SignalProvider> getSignalProviders() {
        return signalProviders;
    }

    public void setSignalProviders(List<SignalProvider> signalProviders) {
        this.signalProviders = signalProviders;
    }

    public Accessor<Position> getPositionAccessor() {
        return positionAccessor;
    }

    public void setPositionAccessor(Accessor<Position> positionAccessor) {
        this.positionAccessor = positionAccessor;
    }

    @Override
    public SignalAction copy() {
        return new SignalAction(ObjectUtil.copyList(signalProviders), positionAccessor.copy());
    }
}