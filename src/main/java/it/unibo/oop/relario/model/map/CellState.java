package it.unibo.oop.relario.model.map;

/**
 * Enumeration representing the state of a cell.
 */
public enum CellState {
    /** The cell is occupied. */
    OCCUPIED,

    /** The cell is restricted. */
    RESTRICTED,

    /** The cell is empty and is a perimeter cell. */
    PERIMETER_EMPTY,

    /** The cell is empty and is an inner cell. */
    INNER_EMPTY;
}
