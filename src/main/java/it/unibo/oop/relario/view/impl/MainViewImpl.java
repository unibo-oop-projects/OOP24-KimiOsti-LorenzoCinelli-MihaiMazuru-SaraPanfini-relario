package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.utils.impl.GameKeyListener;
import it.unibo.oop.relario.utils.impl.GameState;
import it.unibo.oop.relario.view.api.MainView;

/**
 * Main container for View.
 */
public final class MainViewImpl implements MainView {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final MainController mainController;
    private final Map<JPanel, String> panels;
    private final GameKeyListener keyListener;
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
        this.panels = new HashMap<>();
        this.keyListener = new GameKeyListener(null);
        this.frameSetup();
        this.currentPanel = GameState.NONE.getState();
        this.previousPanel = GameState.NONE.getState();
        this.frame.add(mainPanel);
        this.frame.addKeyListener(keyListener);
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
        final JPanel startMenuView = new MenuView(this, 
        this.mainController.getMenuController().getStartMenuElements(), this.mainController);
        panels.put(startMenuView, GameState.MENU.getState());

        final JPanel inGameMenuView = new MenuView(this, 
        this.mainController.getMenuController().getInGameMenuElements(), this.mainController);
        panels.put(inGameMenuView, GameState.MENU_IN_GAME.getState());

        final JPanel gameView = new GameView(this.mainController);
        panels.put(gameView, GameState.GAME.getState());

        final InventoryViewImpl inventoryView = new InventoryViewImpl(this.mainController);
        panels.put(inventoryView, GameState.INVENTORY.getState());
        
        final JPanel combatView = new CombatView();
        panels.put(combatView, GameState.COMBAT.getState());

        this.panelsSetFocusable();
    }

    /**
     * Sets focusable any panel and adds it to the main panel.
     */
    private void panelsSetFocusable() {
        for (var p: panels.keySet()) {
            p.setFocusable(true);
            mainPanel.add(p, panels.get(p));
        }
    }

    @Override
    public void showPanel(final String panelName) {
        final CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        this.previousPanel = this.currentPanel;
        this.currentPanel = panelName;
        layout.show(mainPanel, this.currentPanel);

        this.getPanel(this.currentPanel).requestFocus();
    } 

    @Override
    public JPanel getPanel(final String name) {
        for (var p: panels.keySet()) {
            if (panels.get(p).equals(name)) {
                return p;
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
