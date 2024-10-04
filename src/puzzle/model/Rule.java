package puzzle.model;

import puzzle.util.Copyable;

import java.util.Map;

import java.lang.Comparable;

public class Rule implements Copyable<Rule> {
	public enum Comparison {
		EQ,
		NE,
		GT,
		LT,
		GTE,
		LTE
	}

	private Comparison comparison;
	private String propertyName;
	private Comparable target;

	public Rule() {
		comparison = Comparison.EQ;
		propertyName = "";
		target = false;
	}

	public Rule(Comparison comparison, String propertyName, Comparable target) {
		this.comparison = comparison;
		this.propertyName = propertyName;
		this.target = target;
	}

	public boolean fulfilledBy(StateData stateData) {
		Map<String, Comparable> data = stateData.getData();
		if (data.containsKey(propertyName)) {
			Comparable value = data.get(propertyName);

			if (value == null) {
				return false;
			}

			int comparisonValue = target.compareTo(value);

			switch (comparison) {
				case EQ:
					return comparisonValue == 0;
				case NE:
					return comparisonValue != 0;
				case GT:
					return comparisonValue > 0;
				case LT:
					return comparisonValue < 0;
				case GTE:
					return comparisonValue >= 0;
				case LTE:
					return comparisonValue <= 0;
			}
		}

		return false;
	}

	@Override
	public Rule copy() {
		return new Rule(comparison, propertyName, target);
	}
}