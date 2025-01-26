package it.unibo.oop.relario.model.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.MainController;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyFactoryImpl;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;

/**
 * Test class for {@link QuestManager}.
 */
class QuestManagerTest {

    private static final int ATTEMPTS = 100;
    private static final int ROOMS_NUMBER = 4;

    private final MainController controller;
    private final QuestManager manager;

    QuestManagerTest() {
        this.manager = new QuestManager();
        this.controller = new MainControllerImpl();
        this.controller.moveToNextRoom();
    }

    @Test
    void testQuestAssignment() {
        assertTrue(this.controller.getCurRoom().isPresent());
        this.controller.getCurRoom().get().addEntity(
            this.controller.getCurRoom().get().getExit(),
            new EnemyFactoryImpl().createEnemyByType(
                EnemyType.BOSS,
                this.controller.getCurRoom().get().getExit()
            )
        );

        for (int i = 1; i <= ATTEMPTS; i++) {
            this.manager.assignQuest(this.controller.getCurRoom().get(), i);
            if (i > 1 && i <= ROOMS_NUMBER) {
                this.checkGoodAssignment();
            } else {
                this.checkBadAssignment();
            }
        }
    }

    private void checkGoodAssignment() {
        assertTrue(this.controller.getCurRoom().get().getQuest().isPresent());
        assertFalse(this.controller.getCurRoom().get().getQuest().get().isCompleted(
            this.controller.getCurRoom().get()
        ));
    }

    private void checkBadAssignment() {
        assertTrue(this.controller.getCurRoom().get().getQuest().isEmpty());
    }
}
