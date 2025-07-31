package puzzle.model;

import puzzle.model.Action.Signal.Signal;
import puzzle.util.Copyable;
import puzzle.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class StatefulObject implements Copyable<StatefulObject> {
	private Position position;
	private StateData state;

	private List<Interaction> interactions;

	private transient State parentState;

	public StatefulObject() {
		position = new Position();
		state = new StateData();

		interactions = new ArrayList<>();
	}

	public StatefulObject(Position position, StateData state, List<Interaction> interactions) {
		this.position = position;
		this.state = state;
		this.interactions = interactions;
	}

	public void apply(Signal signal) {
		Puzzle.globalInteractions.forEach(i -> i.apply(signal, this));
		interactions.forEach(i -> i.apply(signal, this));
	}

	public StateData getState() {
		return state;
	}

	public Position getPosition() {
		return position;
	}

	public State getParentState() {
		return parentState;
	}

	public void setParentState(State parentState) {
		this.parentState = parentState;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setState(StateData state) {
		this.state = state;
	}

	public List<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}

	public StatefulObject copy() {
		return new StatefulObject(position.copy(), state.copy(), ObjectUtil.copyList(interactions));
	}
}