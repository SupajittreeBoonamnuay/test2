package sut.game01.core.Screen;

import org.jbox2d.dynamics.World;
import sut.game01.core.DebugDrawBox2D;

/**
 * Created by supercell on 2/5/14.
 */
public class GameScreen {
    public static float M_PER_PIXEL = 1 / 26.666667f;
    //size of world
    private static int width = 24;
    private static int height = 18;

    private World world;
    private DebugDrawBox2D debugDraw;
    private Boolean showDebugDraw = true;
}
