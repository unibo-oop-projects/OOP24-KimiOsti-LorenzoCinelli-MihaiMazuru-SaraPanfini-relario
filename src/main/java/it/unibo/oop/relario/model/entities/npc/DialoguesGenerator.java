package it.unibo.oop.relario.model.entities.npc;

import java.util.List;
import java.util.Random;

public final class DialoguesGenerator {

    private final List<String> defaultDialogues;
    private final List<String> lootDialogues;
    private final List<String> noLootDialogues;
    private final Random random;

    public DialoguesGenerator() {
        this.random = new Random();
        this.defaultDialogues = List.of("Oggi è una bella giornata!", "Non ho niente da dire",
            "Sono impegnato, non  mi disturbare", "Buona fortuna!");
        this.lootDialogues = List.of("Ho qualcosa che potrebbe esserti utile", "Ecco un regalo per te", 
            "Forse questo ti potrà servire", "Spero che questo ti aiuti");
        this.noLootDialogues = List.of("Non ho altro da darti", "Non posso più aiutarti", 
            "Mi dispiace, quello era tutto");
    }

    public String getDefaultDialogue() {
        return this.defaultDialogues.get(random.nextInt(this.defaultDialogues.size()));
    }

    public String getLootDialogue() {
        return this.lootDialogues.get(random.nextInt(this.lootDialogues.size()));
    }

    public String getNoLootDialogue() {
        return this.noLootDialogues.get(random.nextInt(this.noLootDialogues.size()));
    }

}
