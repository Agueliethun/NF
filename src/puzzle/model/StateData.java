package puzzle.model;

import puzzle.util.Copyable;

import java.util.HashMap;
import java.util.Map;
import java.lang.Comparable;

public class StateData implements Copyable<StateData>, Comparable<StateData> {
	private Map<String, Comparable> data;

	public StateData() {
		data = new HashMap<>();
	}

	public StateData(Map<String, Comparable> data) {
		this.data = data;
	}

	public Map<String, Comparable> getData() {
		return data;
	}

	public void put(String key, Comparable value) {
		data.put(key, value);
	}

	public <T> T get(String key) {
		if (data.containsKey(key)) {
			return (T) data.get(key);
		}
		return null;
	}

	@Override
	public StateData copy() {
		Map<String, Comparable> copy = new HashMap<>();
		for (Map.Entry<String, Comparable> entry : data.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}
		return new StateData(copy);
	}

	@Override
	public int hashCode() {
		int hashcode = 0;
		for (Map.Entry<String, Comparable> entry : data.entrySet()) {
			hashcode = 7 * hashcode + entry.hashCode();
		}
		return hashcode;
	}

	@Override
	public int compareTo(StateData o) {
		return 0;
	}
}