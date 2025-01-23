package it.unibo.oop.relario.model.entities.npc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class generates random dialogues for NPCs.
 * It handles various categories of dialogues and each category contains a predefined set of strings.
 */
public final class DialoguesGenerator {

    private final Map<DialogueType, List<String>> dialogues = new HashMap<>();
    private final Random random;

    /**
     * Constructs a new dialogues' generator.
     */
    public DialoguesGenerator() {
        this.random = new Random();
        this.dialogues.put(DialogueType.DEFAULT, new ArrayList<>(Arrays.asList
        ("Oggi è una bella giornata!", "Non ho niente da dire", "Sono impegnato, non mi disturbare")));
        this.dialogues.put(DialogueType.LOOT, new ArrayList<>(Arrays.asList
        ("Ho qualcosa che potrebbe esserti utile", "Forse questo ti potrà servire", 
        "Spero che questo ti aiuti", "Ecco un regalo per te")));
        this.dialogues.put(DialogueType.NO_LOOT, new ArrayList<>(Arrays.asList
        ("Non ho altro da darti", "Non posso aiutarti oltre", "Mi dispiace, quello era tutto")));
    }

    /**
     * Retrieves a random default dialogue, for not interactive NPCs.
     * @return a random default dialogue
     */
    public String getDialogue(final DialogueType dialogueType) {
        List<String> matchingDialogues = this.dialogues.get(dialogueType);
        if (matchingDialogues.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return matchingDialogues.get(random.nextInt(matchingDialogues.size()));
    }

}
