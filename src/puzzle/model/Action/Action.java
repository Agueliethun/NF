package puzzle.model.Action;

import puzzle.model.*;
import puzzle.model.Action.Signal.Signal;
import puzzle.util.Copyable;

public abstract class Action implements Copyable<Action> {

	public abstract void apply(Signal signal, StatefulObject object);
}