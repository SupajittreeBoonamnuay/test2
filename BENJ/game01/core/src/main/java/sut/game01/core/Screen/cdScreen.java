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
import sprite.Kirito;
import sprite.Shana;
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


public class cdScreen extends UIScreen {
    //==============================================================================================================================================================
    public static float M_PER_PIXEL = 1 / 26.666667f;
    //size of world
    private static int width = 24;
    private static int height = 18;

    private Shana s;
//==============================================================================================================================================================

    private final ScreenStack ss;
    private Root root;
    //private Zealot z = new Zealot(world,150f,400f);

    public cdScreen(ScreenStack ss) {
        this.ss = ss;
    }
    //==============================================================================================================================================================
    @Override
    public void wasAdded(){
        super.wasAdded();

//==============================================================================================================================================================
        //Image bttImage = PlayN.assets().getImage("images/inside_castle.png");
        Image bgImage = PlayN.assets().getImage("images/bg.png");
        bgImage.addCallback(new Callback<Image>(){
            @Override
            public void onSuccess(Image result){
            }
            @Override
            public void onFailure(Throwable cause){
            }
        });
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        //ImageLayer bttLayer = graphics().createImageLayer(bttImage);
        layer.add(bgLayer);
        // layer.add(bttLayer);
        s = new Shana(150f,300f);
        this.layer.add(s.layer());

        Image atkImage = PlayN.assets().getImage("images/3.png");
        ImageLayer atkLayer = graphics().createImageLayer(atkImage);
        atkLayer.setSize(60f,60f);
        atkLayer.setTranslation(550f,350f);
        layer.add(atkLayer);
        atkLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                s.atk();

            }
        });


        Image satkImage = PlayN.assets().getImage("images/1.png");
        ImageLayer satkLayer = graphics().createImageLayer(satkImage);
        satkLayer.setSize(60f,60f);
        satkLayer.setTranslation(480f,400f);
        layer.add(satkLayer);
        satkLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                s.satk();

            }
        });


        Image leftImage = PlayN.assets().getImage("images/left-arrow.png");
        ImageLayer leftLayer = graphics().createImageLayer(leftImage);
        leftLayer.setSize(60f,60f);
        leftLayer.setTranslation(50f,380f);
        layer.add(leftLayer);
        leftLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                s.l_run();

            }
        });

        Image rightImage = PlayN.assets().getImage("images/right-arrow.png");
        ImageLayer rightLayer = graphics().createImageLayer(rightImage);
        rightLayer.setSize(60f,60f);
        rightLayer.setTranslation(120f,380f);
        layer.add(rightLayer);
        rightLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                s.r_run();

            }
        });


        Image backImage = PlayN.assets().getImage("images/back-button.png");
        ImageLayer backLayer = graphics().createImageLayer(backImage);
        backLayer.setSize(60f,60f);
        backLayer.setTranslation(30f,30f);
        layer.add(backLayer);
        backLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());

            }
        });




//==============================================================================================================================================================
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


        /*k.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);

            }
        });*/

    }
//==============================================================================================================================================================


    @Override
    public void update(int delta) {
        super.update(delta);
        s.update(delta);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        s.paint(clock);
    }


}

