package it.unibo.oop.relario.model.entities.npc;

import it.unibo.oop.relario.model.entities.LivingBeing;

/**
 * Interface representing a NPC in the game.
 */
public interface Npc extends LivingBeing {

    /**
     * Allowes the interaction with the NPC.
     * @return an InteractionOutput containing the outcome of the interaction
     */
    InteractionOutput interact();

    /**
     * Checks if the player has already interacted with the NPC.
     * @return a boolean representing if the player had already interacted with the NPC.
     */
    boolean hasInteracted();

    void confirmLootTaken();

}
