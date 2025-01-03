package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Main container for View.
 */
public final class MainViewImpl implements MainView {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final MainController mainController;
    private String previousPanel;
    private String currentPanel;

    /**
     * Inizializes the frame of the main view.
     * @param mainController is the controller container instance used to access controllers.
     */
    public MainViewImpl(final MainController mainController) {
        this.mainController = mainController;
        this.mainPanel = new JPanel(new CardLayout());
        this.frame = new JFrame();
        this.frameSetup();
        this.currentPanel = GameState.NONE;
        this.previousPanel = GameState.NONE;
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }

    private void frameSetup() {
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setLayout(new BorderLayout());
        this.frame.setLocationByPlatform(true);
        this.frame.setFocusable(true);
    }

    @Override
    public void panelsSetup() {
        mainPanel.add(new MainMenuView(this, this.mainController.getMainMenuController().getStartMenuElements(), this.mainController), GameState.MENU);
        mainPanel.add(new GameView(this.mainController), GameState.GAME);
        mainPanel.add(new InventoryView(this.mainController), GameState.INVENTORY);
        mainPanel.add(new CombatView(), GameState.COMBAT);
    }

    @Override
    public void showPanel(final String panelName) {
        final CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        this.previousPanel = this.currentPanel;
        this.currentPanel = panelName;
        layout.show(mainPanel, this.currentPanel);
    } 

    @Override
    public JPanel getPanel(final String name) {
        for (var comp : mainPanel.getComponents()) {
            if (comp.getName() != null && comp.getName().equals(name)) {
                return (JPanel) comp;
            }
        }
        return null;
    }

    @Override
    public void showPreviousPanel() {
        showPanel(this.previousPanel);
    }

    @Override
    public String getCurrentPanel() {
        return this.currentPanel;
    }

}
