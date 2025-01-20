package it.unibo.oop.relario.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
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
    private final Map<GameState, JPanel> panels = new EnumMap<>(GameState.class);
    private final Deque<GameState> stack = new ArrayDeque<>();
    private GameState currentPanel;

    /**
     * Inizializes the frame of the main view.
     * @param mainController is the controller container instance used to access controllers.
     */
    public MainViewImpl(final MainController mainController) {
        this.mainController = mainController;
        this.mainPanel = new JPanel(new CardLayout());
        this.frame = new JFrame();
        this.frameSetup();
        this.currentPanel = GameState.MENU;
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
        final JPanel combatView = new CombatView(this.mainController.getCombatController());

        panels.put(GameState.MENU, startMenuView);
        panels.put(GameState.MENU_IN_GAME, inGameMenuView);
        panels.put(GameState.GAME, gameView);
        panels.put(GameState.INVENTORY, inventoryView);
        panels.put(GameState.COMBAT, combatView);

        this.panelsSetFocusable();
    }

    @Override
    public void showPanel(final GameState panelName) {
        final CardLayout layout = (CardLayout) this.mainPanel.getLayout();
        this.currentPanel = panelName;
        layout.show(mainPanel, this.currentPanel.getState());

        stack.push(panelName);
        this.getPanel(this.currentPanel).requestFocus();
    } 

    @Override
    public JPanel getPanel(final GameState name) {
        return panels.get(name);
    }

    @Override
    public void showPreviousPanel() {
        stack.pop();
        showPanel(stack.pop());
    }

    @Override
    public GameState getCurrentPanel() {
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
        for (final var p: panels.values()) {
            p.setFocusable(true);
            final String s = panels.entrySet().stream()
                .filter(e -> e.getValue().equals(p))
                .map(Map.Entry::getKey)
                .findFirst()
                .get()
                .getState();
            mainPanel.add(p, s);
        }
    }

}
