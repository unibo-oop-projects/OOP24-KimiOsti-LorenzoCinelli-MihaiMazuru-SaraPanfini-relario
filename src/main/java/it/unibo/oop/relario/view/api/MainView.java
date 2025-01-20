package it.unibo.oop.relario.view.api;

import javax.swing.JPanel;

import it.unibo.oop.relario.utils.impl.GameState;

/**
 * Interface for the main container for View.
 */
public interface MainView {
    /**
     * Creates all the game panels and adds them to the main panel.
     */
    void panelsSetup();

    /**
     * Display a game panel.
     * @param panelName is the name of the game phase.
     */
    void showPanel(GameState panelName);

    /**
     * Retrieves the specified JPanel.
     * @param name is corrisponding name of the panel.
     * @return the requested panel or null.
     */
    JPanel getPanel(GameState name);

    /**
     * Display the previous game panel.
     */
    void showPreviousPanel();

    /**
     * Retrieves the current panel.
     * @return the current panel.
     */
    GameState getCurrentPanel();

}
