package puzzle.model.Action.Signal.Provider;

import puzzle.model.Action.Signal.Signal;

public class SimpleSignalProvider extends SignalProvider {
    private Signal signal;

    public SimpleSignalProvider() {
        signal = new Signal();
    }

    public SimpleSignalProvider(Signal signal) {
        this.signal = signal;
    }

    public Signal getSignal(Signal oldSignal) {
        return signal;
    }

    @Override
    public SimpleSignalProvider copy() {
        return new SimpleSignalProvider(signal.copy());
    }
}