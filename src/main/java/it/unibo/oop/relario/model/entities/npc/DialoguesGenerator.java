package it.unibo.oop.relario.model.entities.npc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class generates random dialogues for NPCs.
 * It handles various categories of dialogues and each category contains a predefined set of strings.
 */
public final class DialoguesGenerator {

    private final Map<DialogueType, List<String>> dialogues = new EnumMap<>(DialogueType.class);
    private final Random random;

    /**
     * Constructs a new dialogues' generator.
     */
    public DialoguesGenerator() {
        this.random = new Random();
        this.dialogues.put(DialogueType.DEFAULT, new ArrayList<>(Arrays.asList(
            "<html>Oggi &egrave una bella giornata!<html>", "Sono impegnato, non mi disturbare.", 
            "<html>Hai tutto ci&ograve che ti serve per superare questa sfida.<html>", 
            "<html>Non sei il primo a passare di qui, chiss&agrave dove sono finiti gli altri...<html>", 
            "<html>Questo castello era diverso un tempo, ma ora &egrave solo ombre e polvere.<html>",
            "A volte guardo il soffitto e mi chiedo... chi lo ha pulito l'ultima volta?",
            "Sai cosa manca qui? Un bel divano. E una pizza.", "Ogni angolo di questo castello nasconde un segreto.",
            "<html>La mia collezione di tazze da t&egrave &egrave la pi&ugrave grande del regno.<html>", 
            "<html>La cucina del castello &egrave sempre piena di piatti misteriosi...<html>")));
        this.dialogues.put(DialogueType.LOOT, new ArrayList<>(Arrays.asList(
            "Ho qualcosa per te, credo che potrebbe esserti utile.", 
            "<html>Prendi questo, ti aiuter&agrave in un momento difficile.<html>", 
            "Spero che in futuro questo ti possa servire.", "Ecco un regalo per te.",
            "<html>Non so perch&eacute, ma sono certo che avrai bisogno di questo.<html>")));
        this.dialogues.put(DialogueType.NO_LOOT, new ArrayList<>(Arrays.asList(
            "Mi dispiace, ma non ho altro da darti.", "Non posso aiutarti oltre.", 
            "<html>Scusa, ma ti ho gi&agrave dato ci&ograve che avevo.<html>",
            "<html>Non ho pi&ugrave niente da offrirti, buona fortuna!<html>",
            "<html>Ho gi&agrave fatto la mia parte, adesso tocca a te.<html>")));
    }

    /**
     * Retrieves a random default dialogue, for not interactive NPCs.
     * @param dialogueType is the type of dialogue.
     * @return a random default dialogue
     */
    public String getDialogue(final DialogueType dialogueType) {
        final List<String> matchingDialogues = this.dialogues.getOrDefault(dialogueType, List.of("Nessun dialogo disponibile"));
        return matchingDialogues.get(random.nextInt(matchingDialogues.size()));
    }

}
