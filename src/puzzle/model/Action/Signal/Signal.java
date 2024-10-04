package puzzle.model.Action.Signal;

import puzzle.model.Position;
import puzzle.model.Action.Signal.Propagation.Propagation;
import puzzle.model.StateData;
import puzzle.util.Copyable;

public class Signal implements Copyable<Signal> {
	private StateData state;
	private Propagation propagation;

	public Signal() {
		state = new StateData();
		propagation = null;
	}

	public Signal(StateData state, Propagation propagation) {
		this.state = state;
		this.propagation = propagation;
	}

	public StateData getState() {
		return state;
	}

	public Position propagate(Position position) {
		return propagation.propagate(position);
	}

	@Override
	public Signal copy() {
		return new Signal(state.copy(), propagation.copy());
	}
}