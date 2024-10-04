package puzzle.model.Action.Signal.Propagation;

import puzzle.model.Position;

public class SimplePropagation extends Propagation {

    private Position position;

    public SimplePropagation() {
        position = new Position();
    }

    public SimplePropagation(Position position) {
        this.position = position;
    }

    @Override
    public Position propagate(Position oldPos) {
        return position;
    }

    @Override
    public SimplePropagation copy() {
        return new SimplePropagation(position.copy());
    }
}