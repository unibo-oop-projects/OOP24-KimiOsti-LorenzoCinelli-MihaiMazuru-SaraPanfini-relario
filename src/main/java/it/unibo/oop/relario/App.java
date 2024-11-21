package it.unibo.oop.relario;

import it.unibo.oop.relario.view.impl.ViewFactoryImpl;

/**
 * The main class of the application, which starts its execution.
 */
public final class App {
    private App() {
        throw new UnsupportedOperationException();
    }

    /**
     * The main method of the application, from which execution starts.
     * @param args are ignored.
     */
    public static void main(final String[] args) {
        new ViewFactoryImpl().createMainView();
    }
}
