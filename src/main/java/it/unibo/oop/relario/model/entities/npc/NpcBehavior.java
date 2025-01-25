package it.unibo.oop.relario.model.entities.npc;

/**
 * Interface for the game's NPC behavior.
 */
public interface NpcBehavior {

    /**
     * Getter for the NPC's dialogue.
     * @param hasLoot represents if the NPC carries a loot.
     * @return the NPC's preset dialogue.
     */
    String getDialogue(boolean hasLoot);

}
