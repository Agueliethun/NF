package puzzle.model.Action.Signal;

import puzzle.model.Position;
import puzzle.model.Action.Signal.Propagation.Propagation;
import puzzle.model.StateData;
import puzzle.util.Copyable;

public class Signal implements Copyable<Signal> {
	private StateData state;

	public Signal() {
		state = new StateData();
	}

	public Signal(StateData state) {
		this.state = state;
	}

	public StateData getState() {
		return state;
	}

	public void setState(StateData state) {
		this.state = state;
	}

	@Override
	public Signal copy() {
		return new Signal(state.copy());
	}
}