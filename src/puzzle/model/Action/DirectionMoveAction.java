package puzzle.model.Action;

import puzzle.model.Accessor.Accessor;
import puzzle.model.Accessor.PositionFromDirection;
import puzzle.model.Accessor.PropertyAccessor;
import puzzle.model.Action.Signal.Signal;
import puzzle.model.Position;
import puzzle.model.StatefulObject;

public class DirectionMoveAction extends Action {
    @Override
    public void apply(Signal signal, StatefulObject object) {
        PositionFromDirection pfd = new PositionFromDirection(new PropertyAccessor("direction"));
        Position newPosition = object.getPosition().copy();
        newPosition.offset(pfd.get(object.getState()));
        object.getParentState().moveObject(object, newPosition);
    }

    @Override
    public Action copy() {
        return new DirectionMoveAction();
    }
}
