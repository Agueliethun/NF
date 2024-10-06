package puzzle.model.Accessor;

import puzzle.model.Position;
import puzzle.model.StateData;

public class PositionFromDirection extends Accessor<Position> {

    private Accessor<String> dirAccessor;

    public PositionFromDirection(Accessor<String> dirAccessor) {
        this.dirAccessor = dirAccessor;
    }

    @Override
    public Position get(StateData data) {
        String dir = dirAccessor.get(data);

        switch (dir) {
            case "UP":
                return new Position(0, -1);
            case "LEFT":
                return new Position(-1, 0);
            case "RIGHT":
                return new Position(1, 0);
            case "DOWN":
                return new Position(0, 1);
            default:
                return new Position(0, 0);
        }
    }

    @Override
    public PositionFromDirection copy() {
        return new PositionFromDirection(dirAccessor.copy());
    }
}
