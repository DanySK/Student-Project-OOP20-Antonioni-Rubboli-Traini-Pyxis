package it.unibo.pyxis.model.hitbox;

import it.unibo.pyxis.model.util.Coord;
import it.unibo.pyxis.model.util.CoordImpl;
import it.unibo.pyxis.model.util.Dimension;
import it.unibo.pyxis.model.util.DimensionImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HitboxTest {

    @Test
    void testCollidingWithPoint() {
        final Coord coord = new CoordImpl(10,10);
        final Dimension dimension = new DimensionImpl(3,5);
        final Hitbox rectBox = new RectHitbox(coord, dimension);

        assertTrue(rectBox.isCollidingWithPoint(new CoordImpl(11, 8)));
        assertTrue(rectBox.isCollidingWithPoint(new CoordImpl(11.5, 7.5)));
        assertFalse(rectBox.isCollidingWithPoint(new CoordImpl(11.6, 7.5)));
        assertFalse(rectBox.isCollidingWithPoint(new CoordImpl(11, 7)));

        final Hitbox circleBox = new CircleHitbox(coord, 5.9);

        assertTrue(circleBox.isCollidingWithPoint(new CoordImpl(11, 8)));
        assertTrue(circleBox.isCollidingWithPoint(new CoordImpl(11.5, 7.5)));
        assertFalse(circleBox.isCollidingWithPoint(new CoordImpl(11.6, 7.5)));
        assertFalse(circleBox.isCollidingWithPoint(new CoordImpl(10, 7)));
    }

    @Test
    void testCollidingWithOtherHB() {

        final Coord coord1 = new CoordImpl(10,10);
        final Coord coord2 = new CoordImpl(13, 11);
        final Coord coord3 = new CoordImpl(14, 11);

        final Dimension dimension1 = new DimensionImpl(3,5);
        final Dimension dimension2 = new DimensionImpl(2,1);

        final Hitbox rectBox = new RectHitbox(coord1, dimension1);
        final Hitbox circleBoxToHit = new CircleHitbox(coord2, 6.0);
        final Hitbox circleBoxToMiss = new CircleHitbox(coord3, 4.0);

        assertTrue(rectBox.isCollidingWithOtherHB(circleBoxToHit));
        assertFalse(rectBox.isCollidingWithOtherHB(circleBoxToMiss));

        final Hitbox circleBox = new CircleHitbox(coord1, 6.0);
        final Hitbox rectBoxToHit = new RectHitbox(coord2, dimension2);
        final Hitbox rectBoxToMiss = new RectHitbox(coord3, dimension2);

        assertTrue(circleBox.isCollidingWithOtherHB(rectBoxToHit));
        assertFalse(circleBox.isCollidingWithOtherHB(rectBoxToMiss));
    }

    @Test
    void testCollidingWithSameHB() {

        final Coord coord1 = new CoordImpl(10,10);
        final Coord coord2 = new CoordImpl(10, 11);
        final Coord coord3 = new CoordImpl(10, 1);

        final Dimension dimension1 = new DimensionImpl(3,5);
        final Dimension dimension2 = new DimensionImpl(2,1);

        final Hitbox rectBox = new RectHitbox(coord1, dimension1);
        final Hitbox rectBoxToHit = new RectHitbox(coord2, dimension2);
        final Hitbox rectBoxToMiss = new RectHitbox(coord3, dimension2);

        assertTrue(rectBox.isCollidingWithSameHB(rectBoxToHit));
        assertFalse(rectBox.isCollidingWithSameHB(rectBoxToMiss));

        final Hitbox circleBox = new CircleHitbox(coord1, 6.0);
        final Hitbox circleBoxToHit = new CircleHitbox(coord2, 4.0);
        final Hitbox circleBoxToMiss = new CircleHitbox(coord3, 4.0);

        assertTrue(circleBox.isCollidingWithSameHB(circleBoxToHit));
        assertFalse(circleBox.isCollidingWithSameHB(circleBoxToMiss));
    }

    @Test
    void testCollidingEdgeWithOtherHB() {
        final Coord coord1 = new CoordImpl(10,10);
        final Coord coord2 = new CoordImpl(13, 11);
        final Coord coord3 = new CoordImpl(12, 13);
        final Coord coord4 = new CoordImpl(9.5, 8);
        final Coord coord5 = new CoordImpl(8.5, 13.9);

        final Dimension dimension1 = new DimensionImpl(3,5);

        final Hitbox rectBox = new RectHitbox(coord1, dimension1);
        final Hitbox circleBoxToHit = new CircleHitbox(coord2, 6.0);

        Optional<HitEdge> result = rectBox.collidingEdgeWithOtherHB(circleBoxToHit);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.VERTICAL, result.get());

        final Hitbox circleBoxToHit2 = new CircleHitbox(coord3, 2.0);

        result = rectBox.collidingEdgeWithOtherHB(circleBoxToHit2);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.CORNER, result.get());

        final Hitbox circleBoxToHit3 = new CircleHitbox(coord3, 1.3);

        result = rectBox.collidingEdgeWithOtherHB(circleBoxToHit3);
        assertTrue(result.isEmpty());

        final Hitbox circleBoxToHit4 = new CircleHitbox(coord4, 10.0);

        result = rectBox.collidingEdgeWithOtherHB(circleBoxToHit4);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.VERTICAL, result.get());

        final Hitbox circleBoxToHit5 = new CircleHitbox(coord5, 10.0);

        result = rectBox.collidingEdgeWithOtherHB(circleBoxToHit5);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.HORIZONTAL, result.get());
    }

    @Test
    void testCollidingEdgeWithBorder() {

        final Coord borderCoord = new CoordImpl(10, 15);
        final Coord coord1 = new CoordImpl(18, 3);
        final Coord coord2 = new CoordImpl(17.2, 2.8);
        final Coord coord3 = new CoordImpl(3, 3);
        final Coord coord4 = new CoordImpl(6, 5);
        final Coord coord5 = new CoordImpl(6, 25);

        final Coord coord6 = new CoordImpl(13, 7);
        final Coord coord7 = new CoordImpl(13, 19);

        final Dimension borderDimension = new DimensionImpl(20, 30);
        final Dimension dimension3 = new DimensionImpl(4, 6);
        final Dimension dimension4 = new DimensionImpl(12, 10);
        final Dimension dimension5 = new DimensionImpl(12, 9.9);

        final Dimension dimension7 = new DimensionImpl(12, 10);

        final Hitbox border = new RectHitbox(borderCoord, borderDimension);

        final Hitbox circleHBToHitRight = new CircleHitbox(coord1, 4.0);
        final Hitbox circleHBToHitCorner = new CircleHitbox(coord2, 6.0);
        final Hitbox rectHBToHitUp = new RectHitbox(coord3, dimension3);
        final Hitbox rectHBToHitCorner = new RectHitbox(coord4, dimension4);
        final Hitbox rectHBToHitLeft = new RectHitbox(coord5, dimension5);

        final Hitbox circleHBToMiss = new CircleHitbox(coord6, 12.0);
        final Hitbox rectHBToMiss = new RectHitbox(coord7, dimension7);


        Optional<HitEdge> result = circleHBToHitRight.collidingEdgeWithBorder(border);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.VERTICAL, result.get());

        result = circleHBToHitCorner.collidingEdgeWithBorder(border);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.CORNER, result.get());

        result = rectHBToHitUp.collidingEdgeWithBorder(border);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.HORIZONTAL, result.get());

        result = rectHBToHitCorner.collidingEdgeWithBorder(border);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.CORNER, result.get());

        result = rectHBToHitLeft.collidingEdgeWithBorder(border);
        assertTrue(result.isPresent());
        assertEquals(HitEdge.VERTICAL, result.get());

        result = circleHBToMiss.collidingEdgeWithBorder(border);
        assertFalse(result.isPresent());

        result = rectHBToMiss.collidingEdgeWithBorder(border);
        assertFalse(result.isPresent());
    }

    @Test
    void testCollidingWithLowerBorder() {
        final Coord borderCoord = new CoordImpl(10, 15);
        final Coord coord1 = new CoordImpl(12, 29.1);
        final Coord coord2 = new CoordImpl(9, 27);
        final Coord coord3 = new CoordImpl(14, 25);

        final Coord coord4 = new CoordImpl(5, 26);

        final Dimension borderDimension = new DimensionImpl(20, 30);
        final Dimension dimension2 = new DimensionImpl(5, 6);

        final Dimension dimension4 = new DimensionImpl(10, 7.9);

        final Hitbox border = new RectHitbox(borderCoord, borderDimension);

        final Hitbox circleHBToHit = new CircleHitbox(coord1, 2.0);
        final Hitbox rectHBToHit = new RectHitbox(coord2, dimension2);

        final Hitbox circleHBToMiss = new CircleHitbox(coord3, 6.0);
        final Hitbox rectHBToMiss = new RectHitbox(coord4, dimension4);

        assertTrue(circleHBToHit.isCollidingWithLowerBorder(border));
        assertTrue(rectHBToHit.isCollidingWithLowerBorder(border));
        assertFalse(circleHBToMiss.isCollidingWithLowerBorder(border));
        assertFalse(rectHBToMiss.isCollidingWithLowerBorder(border));
    }
}