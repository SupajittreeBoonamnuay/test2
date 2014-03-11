package sut.game01.core.Screen;


import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sprite.Kirito;
import sprite.Shana;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Style;

import static playn.core.PlayN.graphics;
import static tripleplay.ui.SimpleStyles.newSheet;
import static tripleplay.ui.layout.AxisLayout.vertical;


public class CharacterScreen extends UIScreen {


    private final ScreenStack ss;
    private Root root;

    private Kirito a;
    private Shana b;

    public CharacterScreen(ScreenStack ss){
        this.ss=ss;
    }

    @Override
    public void wasAdded(){
        super.wasAdded();
        Image bgImage = PlayN.assets().getImage("images/ChooseCharacter.png");
        bgImage.addCallback(new Callback<Image>(){
            @Override
            public void onSuccess(Image result){

            }
            @Override
            public void onFailure(Throwable cause){

            }
        });
        ImageLayer bgLayer = PlayN.graphics().createImageLayer(bgImage);
        layer.add(bgLayer);

        a = new Kirito(300f,200f);
        a.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                Screen ab = new abScreen(ss);
                ss.push(ab);
            }
        });
        this.layer.add(a.layer());

        b = new Shana(400f,380f);
        b.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                Screen cd = new cdScreen(ss);
                ss.push(cd);
            }
        });
        this.layer.add(b.layer());

    }

    @Override
    public void wasShown() {

        super.wasShown();

        /*root.add(new Button("จิ้มๆ").onClick(new UnitSlot(){
            public void onEmit(){
                ss.push(new TestScreen(ss));
            }
        }));*/

        /*Image startImage = PlayN.assets().getImage("images/start.png");
        ImageLayer startLayer = graphics().createImageLayer(startImage);
        startLayer.setSize(100f,100f);
        startLayer.setTranslation(270f,300f);

        startLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                Screen ab = new abScreen(ss);
                ss.push(ab);
            }
        });
        layer.add(startLayer);*/
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        a.update(delta);
        b.update(delta);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        a.paint(clock);
        b.paint(clock);
    }

}