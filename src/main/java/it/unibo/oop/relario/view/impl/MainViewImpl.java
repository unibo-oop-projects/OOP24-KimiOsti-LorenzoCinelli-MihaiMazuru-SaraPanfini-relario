package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Main container for View.
 */
public final class MainViewImpl implements MainView {

    private final JFrame frame;
    private final JPanel mainPanel;

    /**
     * Inizializes the frame of the main view.
     * @param controller The Controller container instance used to access Controllers.
     */
    public MainViewImpl(final MainController mainController) {
        final MainController controller = mainController;
        this.frame = new JFrame();
        this.frameSetup();
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(new MainMenuView(controller), "Menu");
        mainPanel.add(new GameView(), "Game");
        mainPanel.add(new InventoryView(), "Inventory");
        mainPanel.add(new CombatView(), "Combat");
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }

    @Override
    public void showPanel(final String panelName) {
        CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    } 

    private void frameSetup() {
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setLayout(new BorderLayout());
        this.frame.setLocationByPlatform(true);
        this.frame.setFocusable(true);
    }
}
