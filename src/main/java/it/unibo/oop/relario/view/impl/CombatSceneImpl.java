package it.unibo.oop.relario.view.impl;

import javax.swing.JPanel;

import it.unibo.oop.relario.controller.api.CombatController;
import it.unibo.oop.relario.view.api.CombatScene;

/**
 * Implementation for the central scene of combat environments.
 */
public class CombatSceneImpl extends JPanel implements CombatScene {

    private final CombatController controller;
    private final JPanel enemyInfo;
    private final JPanel enemyImage;
    private final JPanel playerInfo;

    public CombatSceneImpl(final CombatController controller) {
        this.controller = controller;

        this.enemyInfo = new JPanel();
        this.enemyImage = new JPanel();
        this.playerInfo = new JPanel();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
