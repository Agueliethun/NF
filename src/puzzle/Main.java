package puzzle;

import puzzle.model.*;
import puzzle.model.Action.Action;
import puzzle.model.Action.MoveAction;
import puzzle.model.Action.Signal.Propagation.SimplePropagation;
import puzzle.model.Action.Signal.Provider.SignalProvider;
import puzzle.model.Action.Signal.Provider.SimpleSignalProvider;
import puzzle.model.Action.Signal.Signal;
import puzzle.model.Action.SignalAction;
import puzzle.model.Action.StateChangeAction;
import puzzle.model.Action.StateModifier.SimpleStateModifier;
import puzzle.ui.InputInterface;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Puzzle p = new Puzzle();

        StatefulObject wall = new StatefulObject();
        wall.getState().put("display", "||");
        wall.getState().put("collision", true);

        StatefulObject floor = new StatefulObject();
        floor.getState().put("display", "x");

        int size = 5;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (r == 0 || c == 0 || r == size-1 || c == size-1) {
                    addObject(p, wall.copy(), new Position(c, r));
                } else {
                    addObject(p, floor.copy(), new Position(c, r));
                }
            }
        }

        StatefulObject goal = new StatefulObject();
        goal.getState().put("display", "O");
        goal.getState().put("objectType", "goal");
        goal.getState().put("pushed", false);
        addObject(p, goal, new Position(size-2, size-2));

        StatefulObject player = new StatefulObject();
        player.getState().put("display", "P");
        player.getState().put("direction", "DOWN");
        addControlInteraction(player);
        addObject(p, player, new Position(1, 1));

        p.calculateStateMap();

        new InputInterface(p);
    }

    private static void addControlInteraction(StatefulObject obj) {
        Interaction control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "UP"));
        control.getStateRules().add(new Rule(Rule.Comparison.EQ, "direction", "UP"));
        control.getActions().add(new MoveAction(new Position(0, -1)));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "DOWN"));
        control.getStateRules().add(new Rule(Rule.Comparison.EQ, "direction", "DOWN"));
        control.getActions().add(new MoveAction(new Position(0, 1)));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "LEFT"));
        control.getStateRules().add(new Rule(Rule.Comparison.EQ, "direction", "LEFT"));
        control.getActions().add(new MoveAction(new Position(-1, 0)));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "RIGHT"));
        control.getStateRules().add(new Rule(Rule.Comparison.EQ, "direction", "RIGHT"));
        control.getActions().add(new MoveAction(new Position(1, 0)));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "UP"));
        control.getStateRules().add(new Rule(Rule.Comparison.NE, "direction", "UP"));
        control.getActions().add(new StateChangeAction(List.of(new SimpleStateModifier("direction", "UP"))));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "DOWN"));
        control.getStateRules().add(new Rule(Rule.Comparison.NE, "direction", "DOWN"));
        control.getActions().add(new StateChangeAction(List.of(new SimpleStateModifier("direction", "DOWN"))));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "LEFT"));
        control.getStateRules().add(new Rule(Rule.Comparison.NE, "direction", "LEFT"));
        control.getActions().add(new StateChangeAction(List.of(new SimpleStateModifier("direction", "LEFT"))));
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, "inputName", "RIGHT"));
        control.getStateRules().add(new Rule(Rule.Comparison.NE, "direction", "RIGHT"));
        control.getActions().add(new StateChangeAction(List.of(new SimpleStateModifier("direction", "RIGHT"))));
        obj.getInteractions().add(control);
    }

    private static void addObject(Puzzle puzzle, StatefulObject object, Position position) {
        object.setPosition(position);
        puzzle.addObject(object);
    }
}