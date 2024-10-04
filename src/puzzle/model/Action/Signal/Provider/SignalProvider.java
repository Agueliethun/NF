package puzzle.model.Action.Signal.Provider;

import puzzle.model.Action.Signal.Signal;
import puzzle.util.Copyable;

public abstract class SignalProvider implements Copyable<SignalProvider> {
	
	public abstract Signal getSignal(Signal oldSignal);

}