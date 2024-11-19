package it.unibo.oop.relario.model.map;

import java.util.Optional;

import it.unibo.oop.relario.model.entities.Entity;
import it.unibo.oop.relario.utils.api.Position;

public interface Room {
    
    Optional<Entity> getCellContent(Position position);

}
