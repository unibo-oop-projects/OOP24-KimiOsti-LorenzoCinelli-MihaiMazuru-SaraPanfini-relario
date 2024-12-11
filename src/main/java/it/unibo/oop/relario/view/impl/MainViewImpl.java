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
    private String previousPanel;
    private String currentPanel;

    /**
     * Inizializes the frame of the main view.
     * @param controller The Controller container instance used to access Controllers.
     */
    public MainViewImpl(final MainController mainController) {
        final MainController controller = mainController;
        this.frame = new JFrame();
        this.frameSetup();
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new MainMenuView(controller), GameState.MENU);
        mainPanel.add(new GameView(), GameState.GAME);
        mainPanel.add(new InventoryView(), GameState.INVENTORY);
        mainPanel.add(new CombatView(), GameState.COMBAT);
        this.currentPanel = GameState.NONE;
        this.previousPanel = GameState.NONE;
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }

    public void showPreviousPanel() {
        showPanel(this.previousPanel);
    }

    @Override
    public void showPanel(final String panelName) {
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        this.previousPanel = this.currentPanel;
        this.currentPanel = panelName;
        layout.show(mainPanel, this.currentPanel);
    } 

    private void frameSetup() {
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setLayout(new BorderLayout());
        this.frame.setLocationByPlatform(true);
        this.frame.setFocusable(true);
    }
}
