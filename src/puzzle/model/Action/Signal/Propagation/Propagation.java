package puzzle.model.Action.Signal.Propagation;

import puzzle.model.Position;
import puzzle.util.Copyable;

public abstract class Propagation implements Copyable<Propagation> {
	public abstract Position propagate(Position oldPos);
}