package puzzle.model.Action;

import puzzle.model.Position;
import puzzle.model.Action.Signal.Signal;
import puzzle.model.StatefulObject;

public class MoveAction extends Action {
    private Position positionOffset;

    public MoveAction() {
        positionOffset = new Position();
    }

    public MoveAction(Position positionOffset) {
        this.positionOffset = positionOffset;
    }

    public void apply(Signal signal, StatefulObject object) {
        Position newPosition = object.getPosition().copy();
        newPosition.offset(positionOffset);
        object.getParentState().moveObject(object, newPosition);
    }

    @Override
    public MoveAction copy() {
        return new MoveAction(positionOffset.copy());
    }
}