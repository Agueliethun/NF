package puzzle.model.Accessor;

import puzzle.model.StateData;

import java.util.ArrayList;
import java.util.List;

public class PropertyAccessor <T extends Comparable<T>> extends Accessor<T> {

    private List<String> properties;

    public PropertyAccessor(String property) {
        this.properties = new ArrayList<>();
        properties.add(property);
    }

    public PropertyAccessor(String first, String property) {
        this.properties = new ArrayList<>();
        properties.add(first);
        properties.add(property);
    }

    public PropertyAccessor(List<String> properties) {
        this.properties = properties;
    }

    @Override
    public T get(StateData d) {
        StateData data = d;

        for (String property : properties) {
            if (!(data.get(property) instanceof StateData)) {
                return data.get(property);
            }
            data = data.get(property);
        }

        return null;
    }

    @Override
    public PropertyAccessor<T> copy() {
        List<String> newProps = new ArrayList<>(properties);
        return new PropertyAccessor<T>(newProps);
    }
}
