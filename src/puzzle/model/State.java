package puzzle.model;

import puzzle.util.Copyable;
import puzzle.util.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State implements Copyable<State> {

    private Map<Position, List<StatefulObject>> objects;

    public State() {
        objects = new HashMap<>();
    }

    public State(Map<Position, List<StatefulObject>> objects) {
        this.objects = objects;
    }

    public void moveObject(StatefulObject object, Position newPos) {
        if (objects.containsKey(newPos)) {
            for (StatefulObject obj : objects.get(newPos)) {
                Boolean collision = obj.getState().get("collision");
                if (collision != null && collision) {
                    return;
                }
            }
        }

        objects.getOrDefault(object.getPosition(), new ArrayList<>()).remove(object);
        object.setPosition(newPos);
        List<StatefulObject> newObjects = objects.getOrDefault(newPos, new ArrayList<>());
        newObjects.add(object);
        objects.put(newPos, newObjects);
    }

    public Map<Position, List<StatefulObject>> getObjects() {
        return objects;
    }

    public void setObjects(Map<Position, List<StatefulObject>> objects) {
        this.objects = objects;
    }

    public State copy() {
        Map<Position, List<StatefulObject>> copy = new HashMap<>();
        for (Map.Entry<Position, List<StatefulObject>> entry : objects.entrySet()) {
            copy.put(entry.getKey().copy(), ObjectUtil.copyList(entry.getValue()));
        }

        State newState = new State(copy);
        copy.values().forEach(l -> l.forEach(o -> o.setParentState(newState)));
        return newState;
    }

    public List<StatefulObject> getAllObjects() {
        List<StatefulObject> ret = new ArrayList<>();
        objects.values().forEach(ret::addAll);
        return ret;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (StatefulObject object : getAllObjects()) {
            hashCode = hashCode * 17 + object.getPosition().hashCode();
            hashCode = hashCode * 7 + object.getState().hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            return obj.hashCode() == hashCode();
        }
        return false;
    }
}