package puzzle.model;

import puzzle.model.Accessor.Accessor;
import puzzle.model.Accessor.PropertyAccessor;
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
	private Accessor propA;
	private Accessor propB;

	public Rule() {
		comparison = Comparison.EQ;
	}

	public Rule(Comparison c, Accessor a, Accessor b) {
		comparison = c;
		propA = a;
		propB = b;
	}

	public boolean fulfilledBy(StateData stateData) {
		Comparable a = propA.get(stateData);
		Comparable b = propB.get(stateData);

		if (a == null || b == null) {
			return false;
		}

		int comparisonValue = a.compareTo(b);
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

		return false;
	}

	@Override
	public Rule copy() {
		Rule c = new Rule();
		c.comparison = comparison;
		c.propA = propA.copy();
		c.propB = propB.copy();
		return c;
	}
}