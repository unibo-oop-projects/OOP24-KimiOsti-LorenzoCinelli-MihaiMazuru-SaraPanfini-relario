package it.unibo.oop.relario.model.entities.npc;

import it.unibo.oop.relario.model.entities.LivingBeing;
import it.unibo.oop.relario.utils.api.Position;

public interface Npc extends LivingBeing {

    InteractionOutput interact();
    
    Position getPosition();

}
