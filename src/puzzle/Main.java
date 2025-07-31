package puzzle;

import puzzle.model.*;
import puzzle.model.Accessor.*;
import puzzle.model.Action.*;
import puzzle.model.Action.Signal.Provider.DynamicSignalProvider;
import puzzle.model.Action.StateModifier.AccessorModifier;
import puzzle.model.Action.StateModifier.StateModifier;
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

        int size = 7;

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
        player.getState().put("tool", "none");
        addControlInteraction(player);
        addObject(p, player, new Position(1, 1));

        StatefulObject door = new StatefulObject();
        door.getState().put("display", "D");
        door.getState().put("collision", true);
        door.getState().put("locked", false);

        Interaction openDoor = new Interaction();
        openDoor.getSignalRules().add(new Rule(Rule.Comparison.EQ, new PropertyAccessor<>("type"), new ConstantAccessor<>("interact")));
        openDoor.getStateRules().add(new Rule(Rule.Comparison.EQ, new PropertyAccessor<>("locked"), new ConstantAccessor<>(false)));

        List<StateModifier> modifiers = new ArrayList<>();
        modifiers.add(new AccessorModifier("collision", new NotAccessor(new PropertyAccessor<>("object", "collision"))));
        modifiers.add(new AccessorModifier("display", new IfAccessor<>(
                new PropertyAccessor<>("object", "collision"),
                new ConstantAccessor<>("D"),
                new ConstantAccessor<>("|")
        )));
        StateChangeAction action = new StateChangeAction(modifiers);
        openDoor.getActions().add(action);
        door.getInteractions().add(openDoor);
        addObject(p, door, new Position(3, 3));

        //Interaction pushInteraction = new Interaction();
        //pushInteraction.getSignalRules().add(new Rule(Rule.Comparison.GTE, "force", "UP"));

        p.calculateStateMap();

        new InputInterface(p);
    }

    private static void addControlInteraction(StatefulObject obj) {
        Rule oneRule = new Rule(Rule.Comparison.NE, new PropertyAccessor<>("inputName"), new ConstantAccessor<>("ONE"));
        Rule twoRule = new Rule(Rule.Comparison.NE, new PropertyAccessor<>("inputName"), new ConstantAccessor<>("TWO"));

        Interaction control = new Interaction();
        control.getGeneralRules().add(new Rule(Rule.Comparison.EQ,
                new PropertyAccessor<>("signal", "inputName"),
                new PropertyAccessor<>("object", "direction")));
        control.getActions().add(new DirectionMoveAction());
        control.getSignalRules().add(oneRule.copy());
        control.getSignalRules().add(twoRule.copy());
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getGeneralRules().add(new Rule(Rule.Comparison.NE,
                new PropertyAccessor<>("signal", "inputName"),
                new PropertyAccessor<>("object", "direction")));
        control.getActions().add(new StateChangeAction(List.of(new AccessorModifier("direction",
                new PropertyAccessor<>("signal", "inputName")))));
        control.getSignalRules().add(oneRule.copy());
        control.getSignalRules().add(twoRule.copy());
        obj.getInteractions().add(control);

        control = new Interaction();
        control.getSignalRules().add(new Rule(Rule.Comparison.EQ, new PropertyAccessor<>("inputName"), new ConstantAccessor<>("ONE")));
        control.getStateRules().add(new Rule(Rule.Comparison.EQ, new PropertyAccessor<>("tool"), new ConstantAccessor<>("none")));

        SignalAction action = new SignalAction();
        DynamicSignalProvider provider = new DynamicSignalProvider();
        provider.getNewData().put("type", new ConstantAccessor<>("interact"));
        action.getSignalProviders().add(provider);
        action.setPositionAccessor(new PositionFromDirection(new PropertyAccessor<>("object", "direction")));
        control.getActions().add(action);
        obj.getInteractions().add(control);
    }

    private static void addObject(Puzzle puzzle, StatefulObject object, Position position) {
        object.setPosition(position);
        puzzle.addObject(object);
    }
}