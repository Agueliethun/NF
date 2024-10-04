package puzzle.model.Action;

import puzzle.model.Position;
import puzzle.model.Action.Signal.Signal;
import puzzle.model.Action.Signal.Provider.SignalProvider;
import puzzle.model.StatefulObject;
import puzzle.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class SignalAction extends Action {
    private List<SignalProvider> signalProviders;

    public SignalAction() {
        signalProviders = new ArrayList<>();
    }

    public SignalAction(List<SignalProvider> signalProviders) {
        this.signalProviders = signalProviders;
    }

    public void apply(Signal signal, StatefulObject object) {
        for (SignalProvider signalProvider : signalProviders) {
            if (signalProvider != null) {
                Signal newSignal = signalProvider.getSignal(signal);
                Position newPosition = newSignal.propagate(object.getPosition().copy());
                object.getParentState().getObjects().get(newPosition).forEach(o -> o.apply(newSignal));
            }
        }
    }

    public List<SignalProvider> getSignalProviders() {
        return signalProviders;
    }

    public void setSignalProviders(List<SignalProvider> signalProviders) {
        this.signalProviders = signalProviders;
    }

    @Override
    public SignalAction copy() {
        return new SignalAction(ObjectUtil.copyList(signalProviders));
    }
}