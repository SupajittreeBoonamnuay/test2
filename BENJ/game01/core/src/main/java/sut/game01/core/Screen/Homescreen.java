package sut.game01.core.Screen;

import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import react.UnitSlot;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.Root;
import static playn.core.PlayN.graphics;
import static tripleplay.ui.SimpleStyles.newSheet;
import static tripleplay.ui.layout.AxisLayout.vertical;

public class Homescreen extends UIScreen{

    public static final Font TITLE_FONT =
            graphics().createFont(
              "FRENCH SCRIPT MT",
               Font.Style.BOLD,50
            );

private final ScreenStack ss;
private Root root;

    public Homescreen(ScreenStack ss){
        this.ss=ss;
    }

    @Override
    public void wasAdded(){
        super.wasAdded();
        Image bgImage = PlayN.assets().getImage("images/castle.png");
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

    }

    @Override
    public void wasShown() {

        super.wasShown();
        root = iface.createRoot(
                vertical().gap(15),
                newSheet(), layer);
        root.setSize(width(), height());
        root.add(new Label("Save the King")
            .addStyles(Style.FONT.is(Homescreen.TITLE_FONT)).addStyles(Style.COLOR.is(0xFFFFFFFF)));

        /*root.add(new Button("จิ้มๆ").onClick(new UnitSlot(){
            public void onEmit(){
                ss.push(new TestScreen(ss));
            }
        }));*/

        Image startImage = PlayN.assets().getImage("images/start.png");
        ImageLayer startLayer = graphics().createImageLayer(startImage);
        startLayer.setSize(100f,100f);
        startLayer.setTranslation(270f,300f);

        startLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                Screen choosechar = new CharacterScreen(ss);
                ss.push(choosechar);
            }
        });
        layer.add(startLayer);
    }

    @Override
    public void update(int delta) {
        super.update(delta);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);

    }
}
