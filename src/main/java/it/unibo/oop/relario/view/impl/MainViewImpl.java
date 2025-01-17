package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
    private final Map<JPanel, GameState> panels;
    private Deque<String> stack = new ArrayDeque<>();
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
        this.frameSetup();
        this.currentPanel = GameState.MENU.getState();
        this.frame.add(mainPanel);
        this.frame.setVisible(true);
    }

    @Override
    public void panelsSetup() {
        final JPanel startMenuView = new MenuView(this, 
        this.mainController.getMenuController().getStartMenuElements(), this.mainController);
        final JPanel inGameMenuView = new MenuView(this, 
        this.mainController.getMenuController().getInGameMenuElements(), this.mainController);
        final JPanel gameView = new GameView(this.mainController);
        final JPanel inventoryView = new InventoryViewImpl(this.mainController);
        final JPanel combatView = new CombatView();
        
        panels.put(startMenuView, GameState.MENU);
        panels.put(inGameMenuView, GameState.MENU_IN_GAME);
        panels.put(gameView, GameState.GAME);
        panels.put(inventoryView, GameState.INVENTORY);
        panels.put(combatView, GameState.COMBAT);

        this.panelsSetFocusable();
    }

    @Override
    public void showPanel(final String panelName) {
        final CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        this.currentPanel = panelName;
        layout.show(mainPanel, this.currentPanel);

        stack.push(panelName);
        this.getPanel(this.currentPanel).requestFocus();
    } 

    @Override
    public JPanel getPanel(final String name) {
        return panels.entrySet().stream()
            .filter(e -> e.getValue().getState().equals(name))
            .map(Map.Entry::getKey)
            .findFirst()
            .get();
    }

    @Override
    public void showPreviousPanel() {
        stack.pop();
        showPanel(stack.pop());
    }

    @Override
    public String getCurrentPanel() {
        return this.currentPanel;
    }

    private void frameSetup() {
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setLayout(new BorderLayout());
        this.frame.setLocationByPlatform(true);
        this.frame.setFocusable(true);
    }

    /**
     * Sets focusable any panel and adds it to the main panel.
     */
    private void panelsSetFocusable() {
        for (var p: panels.keySet()) {
            p.setFocusable(true);
            mainPanel.add(p, panels.get(p).getState());
        }
    }

}
