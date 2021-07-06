package it.unibo.pyxis.app;
import it.unibo.pyxis.model.level.Level;
import it.unibo.pyxis.model.level.iterator.LevelIterator;

import java.util.Iterator;

public class Main {
    public static void main(final String[] args) {
        // new GameLoopImpl().start();
        final Iterator<Level> levelIterator = new LevelIterator();
        final Level firstLevel = levelIterator.next();

        firstLevel.getArena().getBricks().forEach(b -> {
            System.out.println("Brick type: {" + b.getBrickType() + "} x:" + b.getPosition().getX() + " y: " + b.getPosition().getY());
        });

        firstLevel.getArena().getBalls().forEach(System.out::println);

        /*
            System.out.println(levelIterator.hasNext());

            final Level secondLevel = levelIterator.next();
            secondLevel.getArena().getBricks().forEach(b -> {
                System.out.println("Brick type: {" + b.getBrickType() + "} x:" + b.getPosition().getX() + " y: " + b.getPosition().getY());
            });
        */
    }
}
