package it.unibo.oop.relario.model.entities.npc;

import java.util.List;
import java.util.Random;

/**
 * This class generates random dialogues for NPCs.
 * It handles various categories of dialogues and each category contains a predefined set of strings.
 */
public final class DialoguesGenerator {

    private final List<String> defaultDialogues;
    private final List<String> lootDialogues;
    private final List<String> noLootDialogues;
    private final Random random;

    /**
     * Constructs a new dialogues' generator.
     */
    public DialoguesGenerator() {
        this.random = new Random();
        this.defaultDialogues = List.of("Oggi è una bella giornata!", "Non ho niente da dire",
            "Sono impegnato, non  mi disturbare", "Buona fortuna!");
        this.lootDialogues = List.of("Ho qualcosa che potrebbe esserti utile", "Ecco un regalo per te", 
            "Forse questo ti potrà servire", "Spero che questo ti aiuti");
        this.noLootDialogues = List.of("Non ho altro da darti", "Non posso più aiutarti", 
            "Mi dispiace, quello era tutto");
    }

    /**
     * Retrieves a random default dialogue, for not interactive NPCs.
     * @return a random default dialogue
     */
    public String getDefaultDialogue() {
        return this.defaultDialogues.get(random.nextInt(this.defaultDialogues.size()));
    }

    /**
     * Retrieves a random dialogue for NPCs with loot.
     * @return a random loot dialogue
     */
    public String getLootDialogue() {
        return this.lootDialogues.get(random.nextInt(this.lootDialogues.size()));
    }

    /**
     * Retrieves a random dialogue for NPCs that have already given a loot.
     * @return a random no loot dialogue
     */
    public String getNoLootDialogue() {
        return this.noLootDialogues.get(random.nextInt(this.noLootDialogues.size()));
    }

}
