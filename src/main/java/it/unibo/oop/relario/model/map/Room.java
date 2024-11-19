package it.unibo.oop.relario.model.map;

import it.unibo.oop.relario.utils.api.Position;

public interface Room {
    
    Entity getCellContent(Position position);

}
