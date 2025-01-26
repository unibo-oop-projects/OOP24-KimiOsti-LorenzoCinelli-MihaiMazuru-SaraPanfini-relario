package it.unibo.oop.relario.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.relario.model.GameEntityType;
import it.unibo.oop.relario.model.entities.enemies.EnemyType;
import it.unibo.oop.relario.model.inventory.InventoryItemType;
import it.unibo.oop.relario.model.quest.QuestFactory;
import it.unibo.oop.relario.model.quest.QuestFactoryImpl;
import it.unibo.oop.relario.model.quest.QuestType;
import it.unibo.oop.relario.utils.impl.Pair;

/**
 * Implementation for the quest manager.
 */
public class QuestManager {

    /** Index of the first room. */
    public static final int FIRST_ROOM = 1;

    /** Index of the second room. */
    public static final int SECOND_ROOM = 2;

    /** Index of the third room. */
    public static final int THIRD_ROOM = 3;

    /** Index of the fourth room. */
    public static final int FOURTH_ROOM = 4;

    /** Index of the fifth room. */
    public static final int FIFTH_ROOM = 5;

    private final QuestFactory questFactory = new QuestFactoryImpl();
    private final Map<Integer, Pair<QuestType, Optional<GameEntityType>>> roomQuests = new HashMap<>();
    private final Map<Integer, String> questDescriptions = new HashMap<>();

    /**
     * Instantiates a quest manager.
     */
    public QuestManager() {
        this.roomQuests.put(FIRST_ROOM, new Pair<>(QuestType.NO_QUEST, Optional.empty()));
        this.roomQuests.put(SECOND_ROOM, new Pair<>(QuestType.COLLECTION_QUEST, Optional.of(InventoryItemType.KEY)));
        this.questDescriptions.put(SECOND_ROOM, """
                <html>La chiave per aprire la porta &egrave scomparsa!<br>
                Sono sicuro che &egrave da qualche parte qui vicino.<br>
                Ti prego, puoi aiutarmi a trovarla?<html>
                """);
        this.roomQuests.put(THIRD_ROOM, new Pair<>(QuestType.NPC_INTERACT_QUEST, Optional.empty()));
        this.questDescriptions.put(THIRD_ROOM, """
                <html>Per proseguire parla con tutti coloro che incontri nella stanza.<br>
                Se sarai fortunato, alcuni potrebbero anche donarti oggetti che ti saranno<br>
                utili durante la tua impresa. Ma fai attenzione a chi scegli di avvicinare!<html>
                """);
        this.roomQuests.put(FOURTH_ROOM, new Pair<>(QuestType.DEFEAT_ENEMY_QUEST, Optional.of(EnemyType.BOSS)));
        this.questDescriptions.put(FOURTH_ROOM, """
                <html>Complimenti, ma l'ultima sfida che ti aspetta &egrave la pi&ugrave difficile.<br>
                Dovrai affrontare il re in un combattimento decisivo, e solo se riuscirai a<br>
                sconfiggerlo potrai reclamare il trono e diventare il nuovo sovrano del regno!<html>
                """);
    }

    /**
     * Assigns a quest to a given room.
     * @param room the room to which the quest is assigned.
     * @param indexRoom the room's index.
     */
    public void assignQuest(final Room room, final int indexRoom) {
        final Pair<QuestType, Optional<GameEntityType>> quest = this.roomQuests.get(indexRoom);
        room.setQuest(quest.getX().equals(QuestType.NO_QUEST) ? Optional.empty() 
        : Optional.of(this.questFactory.createQuestByType(questDescriptions.get(indexRoom), quest.getX(), quest.getY())));
    }

}
