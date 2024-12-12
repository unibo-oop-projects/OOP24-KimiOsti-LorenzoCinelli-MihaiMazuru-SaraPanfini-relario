package it.unibo.oop.relario.view.api;

/**
 * Interface for the main container for View.
 */
public interface MainView {

    /**
     * Display a game panel.
     * @param panelName is the name of the game phase.
     */
    void showPanel(String panelName);

    /**
     * Display the previous game panel.
     */
    void showPreviousPanel();

    /**
     * Retrieves the current panel.
     * @return the current panel.
     */
    String getCurrentPanel();
}
