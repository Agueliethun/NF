package puzzle.model;

import puzzle.model.Action.Signal.Signal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle {

	public enum Input {
		LEFT,
		RIGHT,
		UP,
		DOWN,
		ONE,
		TWO
	}

	public static class Tuple <A,B> {
		public A a;
		public B b;

		public Tuple(A a, B b) {
			this.a = a;
			this.b = b;
		}
	}

	private String name;

	private State state;
	private List<Rule> winConditions;

	public static List<Interaction> globalInteractions = new ArrayList<>();

	private transient Map<State, List<Tuple<Input, State>>> stateMap;

	public Puzzle() {
		name = "";
		state = new State();
		winConditions = new ArrayList<>();
	}

	public void calculateStateMap() {
		stateMap = new HashMap<>();

		List<State> toCalculate = new ArrayList<>();
		List<State> alreadyDone = new ArrayList<>();
		toCalculate.add(state);
		alreadyDone.add(state);

		while (toCalculate.size() > 0) {
			State currentState = toCalculate.remove(0);
			stateMap.put(currentState, new ArrayList<>());

			if (!isWin(currentState)) {
				for (Input input : Input.values()) {
					State newState = simulateInput(input, currentState);

					stateMap.get(currentState).add(new Tuple<>(input, newState));

					if (!alreadyDone.contains(newState)) {
						toCalculate.add(newState);
						alreadyDone.add(newState);
					}
				}
			}
		}

		System.out.println(stateMap.size());
	}

	public boolean isWin(State state) {
		for (StatefulObject obj : state.getAllObjects()) {
			for (Rule rule : winConditions) {
				if (rule.fulfilledBy(obj.getState())) {
					return true;
				}
			}
		}

		return false;
	}

	public static State simulateInput(Input input, State state) {
		State newState = state.copy();

		Signal signal = new Signal();
		signal.getState().put("inputName", input.name());
		for (StatefulObject object : newState.getAllObjects()) {
			object.apply(signal);
		}

		return newState;
	}

	public void input(Input input) {
		Signal signal = new Signal();
		signal.getState().put("inputName", input.name());
		signal.getState().put("type", "input");
		for (StatefulObject object : state.getAllObjects()) {
			object.apply(signal);
		}
	}

	public State getState() {
		return state;
	}

	public void addObject(StatefulObject object) {
		List<StatefulObject> objects = getState().getObjects().getOrDefault(object.getPosition(), new ArrayList<>());
		objects.add(object);

		object.setParentState(getState());

		getState().getObjects().put(object.getPosition(), objects);
	}
}