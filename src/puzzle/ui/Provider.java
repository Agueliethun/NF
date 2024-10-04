package puzzle.ui;

import puzzle.util.Copyable;

public abstract class Provider <A, T> implements Copyable<Provider<A, T>> {
    public abstract T get(A args);
}
