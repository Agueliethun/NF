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
	private List<Rule> generalRules;
	private List<Action> actions;

	public Interaction() {
		signalRules = new ArrayList<>();
		stateRules = new ArrayList<>();
		generalRules = new ArrayList<>();
		actions = new ArrayList<>();
	}

	public Interaction(List<Rule> signalRules, List<Rule> stateRules, List<Rule> generalRules, List<Action> actions) {
		this.signalRules = signalRules;
		this.stateRules = stateRules;
		this.generalRules = generalRules;
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

		StateData comboState = new StateData();
		comboState.put("signal", signal.getState());
		comboState.put("object", object.getState());

		for (Rule rule : generalRules) {
			if (!rule.fulfilledBy(comboState)) {
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

	public List<Rule> getGeneralRules() {
		return generalRules;
	}

	public void setGeneralRules(List<Rule> generalRules) {
		this.generalRules = generalRules;
	}

	@Override
	public Interaction copy() {
		return new Interaction(ObjectUtil.copyList(signalRules), ObjectUtil.copyList(stateRules),
				ObjectUtil.copyList(generalRules), ObjectUtil.copyList(actions));
	}
}