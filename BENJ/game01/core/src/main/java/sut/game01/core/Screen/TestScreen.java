package sut.game01.core.Screen;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import react.UnitSlot;
import sprite.Sprite;
import sprite.Zealot;
import sut.game01.core.DebugDrawBox2D;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.Root;

import static playn.core.PlayN.graphics;
import static tripleplay.ui.SimpleStyles.newSheet;
import static tripleplay.ui.layout.AxisLayout.vertical;


public class TestScreen extends UIScreen {
//==============================================================================================================================================================
    public static float M_PER_PIXEL = 1 / 26.666667f;
    //size of world
    private static int width = 24;
    private static int height = 18;

    private World world;
    private DebugDrawBox2D debugDraw;
    private Boolean showDebugDraw = true;
    private Zealot z;
//==============================================================================================================================================================

    private final ScreenStack ss;
    private Root root;
   //private Zealot z = new Zealot(world,150f,400f);

    public TestScreen(ScreenStack ss) {
        this.ss = ss;
    }
//==============================================================================================================================================================
    @Override
    public void wasAdded(){
        super.wasAdded();

        Vec2 gravity = new Vec2(0.0f,2f);
        world = new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
//==============================================================================================================================================================
        Image bttImage = PlayN.assets().getImage("images/sd_gundam.png");
        Image bgImage = PlayN.assets().getImage("images/bg1.png");
        bgImage.addCallback(new Callback<Image>(){
            @Override
            public void onSuccess(Image result){
            }
            @Override
            public void onFailure(Throwable cause){
            }
        });
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        ImageLayer bttLayer = graphics().createImageLayer(bttImage);
        //layer.add(bgLayer);
       // layer.add(bttLayer);
        graphics().rootLayer().add(bttLayer);
        z = new Zealot(this.world,150f,400f);
        this.layer.add(z.layer());



//==============================================================================================================================================================
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
            }
            @Override
            public void endContact(Contact contact) {
            }
            @Override
            public void preSolve(Contact contact, Manifold manifold) {
            }
            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {
            }
        });

            if(showDebugDraw) {
               CanvasImage image = graphics().createImage(
                       (int) (width / TestScreen.M_PER_PIXEL),
                       (int) (height / TestScreen.M_PER_PIXEL));
                layer.add(graphics().createImageLayer(image));
                debugDraw = new DebugDrawBox2D();
                debugDraw.setCanvas(image);
                debugDraw.setFlipY(false);
                debugDraw.setStrokeAlpha(150);
                debugDraw.setFillAlpha(75);
                debugDraw.setStrokeWidth(2.0f);
                debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
                debugDraw.setCamera(0, 0, 1f / TestScreen.M_PER_PIXEL);
                world.setDebugDraw(debugDraw);
            }

            Body ground = world.createBody(new BodyDef());
            PolygonShape groundShape = new PolygonShape();
            groundShape.setAsEdge(new Vec2(2f,height-2f),new Vec2(width-2f,height-2));
            ground.createFixture(groundShape,0.0f);

        Body left = world.createBody(new BodyDef());
        PolygonShape leftShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f, 2f),new Vec2(2f,height-1));
        ground.createFixture(groundShape,0.0f);

        Body right = world.createBody(new BodyDef());
        PolygonShape rightShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(width-2f, 2f),new Vec2(width-2f,height-1));
        ground.createFixture(groundShape,0.0f);

        Body top = world.createBody(new BodyDef());
        PolygonShape topShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f,2f),new Vec2(width-2f,2f));
        ground.createFixture(groundShape,0.0f);
            createBox();




//==============================================================================================================================================================
    }
//==============================================================================================================================================================
    /* @Override
    public void wasShown() {

        super.wasShown();
        root = iface.createRoot(vertical().gap(100),newSheet(), layer);
        root.setSize(width(), height());
        root.add(new Label("Event Driven Programming").addStyles(Style.FONT.is(Homescreen.TITLE_FONT)));
        root.add(new Button("กลับเถอะ").onClick(new UnitSlot(){
            public void onEmit(){
                ss.remove(ss.top());
            }
        }));
        bttLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                body.applyLinearImpulse(new Vec2(0f, 110f), body.getPosition());


            }
        });
     }*/
//==============================================================================================================================================================
private void createBox(){
    BodyDef bodyDef1 = new BodyDef();
    bodyDef1.type = BodyType.DYNAMIC;
    bodyDef1.position = new Vec2(500,500);
    final Body body1 = world.createBody(bodyDef1);

    BodyDef bodyDef2 = new BodyDef();
    bodyDef2.type = BodyType.DYNAMIC;
    bodyDef2.position = new Vec2(0,0);
    final Body body2 = world.createBody(bodyDef2);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(2f, 1f);
    FixtureDef fd1  = new FixtureDef();
    fd1.shape = shape;
    fd1.density = 1f;
    fd1.friction = 1f;
    fd1.restitution = 1f;
    FixtureDef fd2  = new FixtureDef();
    fd2.shape = shape;
    fd2.density = 1f;
    fd2.friction = 1f;
    fd2.restitution = 1f;
    body1.createFixture(fd1);
    body1.setLinearDamping(0.5f);
    body1.setTransform(new Vec2(10f,10f),0);
    body2.createFixture(fd2);
    body2.setLinearDamping(0.5f);
    body2.setTransform(new Vec2(20f,10f),0);

    z.layer().addListener(new Pointer.Adapter(){
        @Override
        public void onPointerEnd(Pointer.Event event) {
            super.onPointerEnd(event);
            body1.applyLinearImpulse(new Vec2(0f,100f),body1.getPosition());

        }
    });
}
//==============================================================================================================================================================


    @Override
    public void update(int delta) {
        z.update(delta);
        world.step(0.033f,10,10);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
           z.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
    }


}