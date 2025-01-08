package it.unibo.oop.relario.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.relario.controller.api.InventoryController;
import it.unibo.oop.relario.controller.impl.InventoryControllerImpl;
import it.unibo.oop.relario.controller.impl.MainControllerImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test class for the {@link InventoryControllerImpl} class.
 */
final class InventoryControllerTest {
    
    InventoryController inventory;

    /**
     * Sets up the testing.
     */
    @BeforeEach
    void setUp() {
        inventory = new InventoryControllerImpl(new MainControllerImpl(), null);
    }

    /**
     * 
     */
    @Test
    void testGetItemsNames() {

    }

    /**
     * 
     */
    @Test
    void testGetItemFullDescription() {

    }

    /**
     * 
     */
    @Test
    void testUseItem() {

    }

    /**
     * 
     */
    @Test
    void testDiscardItem() {

    }

    /**
     * 
     */
    @Test
    void testRegress() {

    }

}
