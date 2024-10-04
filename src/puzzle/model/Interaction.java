package puzzle.model;

import puzzle.model.Action.Action;
import puzzle.model.Action.Signal.Signal;
import puzzle.util.Copyable;
import puzzle.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;


public class Interaction implements Copyable<Interaction> {
	private List<Rule> signalRules;
	private List<Rule> stateRules;
	private List<Action> actions;

	public Interaction() {
		signalRules = new ArrayList<>();
		stateRules = new ArrayList<>();
		actions = new ArrayList<>();
	}

	public Interaction(List<Rule> signalRules, List<Rule> stateRules, List<Action> actions) {
		this.signalRules = signalRules;
		this.stateRules = stateRules;
		this.actions = actions;
	}

	public void apply(Signal signal, StatefulObject object) {
		boolean allFulfilled = true;

		for (Rule rule : signalRules) {
			if (!rule.fulfilledBy(signal.getState())) {
				allFulfilled = false;
			}
		}

		for (Rule rule : stateRules) {
			if (!rule.fulfilledBy(object.getState())) {
				allFulfilled = false;
			}
		}

		if (allFulfilled) {
			for (Action action : actions) {
				action.apply(signal, object);
			}
		}
	}

	public List<Rule> getSignalRules() {
		return signalRules;
	}

	public void setSignalRules(List<Rule> signalRules) {
		this.signalRules = signalRules;
	}

	public List<Rule> getStateRules() {
		return stateRules;
	}

	public void setStateRules(List<Rule> stateRules) {
		this.stateRules = stateRules;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	@Override
	public Interaction copy() {
		return new Interaction(ObjectUtil.copyList(signalRules), ObjectUtil.copyList(stateRules), ObjectUtil.copyList(actions));
	}
}