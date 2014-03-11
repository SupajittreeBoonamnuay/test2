package sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.Screen.TestScreen;
import sut.game01.core.Screen.abScreen;


public class Kirito {

    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;




    public enum State{
        R_IDLE,R_RUN,R_ATK,R_SATK,R_DIE,L_IDLE,L_RUN,L_ATK,L_SATK,L_DIE
    };
    private State state = State.R_IDLE;
    private int e= 0;
    private  int offset =0;
    private int t = 0;


    public void atk() {
        if(state==state.R_IDLE){state = State.R_ATK;}
        else if(state==state.L_IDLE){state = State.L_ATK;}
    }

    public void satk() {
        if(state==state.R_IDLE){state = State.R_SATK;}
        else if(state==state.L_IDLE){state = State.L_SATK;}
    }

    public void l_run() {
        state = State.L_RUN;
    }

    public void r_run() {
        state = State.R_RUN;
    }

    //==============================================================================================================================================================
    public Kirito(final float x_px, final float y_px){
        this.sprite = SpriteLoader.getSprite("images/kirito.json");
        sprite.addCallback(new Callback<Sprite>(){
            @Override
            public void onSuccess(Sprite result){
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width() / 2f, sprite.height() / 2f);
                sprite.layer().setTranslation(x_px, y_px);

                hasLoaded = true;

                /*sprite.layer().addListener(new Pointer.Adapter() {
                    @Override
                    public void onPointerEnd(Pointer.Event event) {
                                    state = State.IDLE;
                                    spriteIndex = -1;
                                    t=1000;
                                    e=0;


                    }
                });*/
            }
            @Override
            public void onFailure(Throwable cause){
                PlayN.log().error("Error loading image!",cause);
            }
        });

    }
    //==============================================================================================================================================================
    public Layer layer(){
        return sprite.layer();
    }
    // @Override
    public void update(int delta) {
        if(!hasLoaded) return ;
        e+= delta;


        if(e > 150){
            t += delta;
            //System.out.println(t);
            if(t==300){
                if((state==state.R_RUN)||(state==state.R_ATK)||(state==state.R_SATK)){
                    state=state.R_IDLE;
                    t=0;
                }else if((state==state.L_RUN)||(state==state.L_ATK)||(state==state.L_SATK)) {
                    state=state.L_IDLE;
                    t=0;
                }

            }
            switch(state) {
                case R_IDLE: offset = 0;
                    break;
                case R_RUN: offset = 4;
                    if(spriteIndex==7){
                        state=State.R_IDLE;
                    }
                    break;
                case R_ATK: offset = 8;
                    if(spriteIndex==11){
                        state=State.R_IDLE;
                    }
                    break;
                case R_SATK: offset = 12;
                    if(spriteIndex==15){
                        state=State.R_IDLE;
                    }
                    break;
                case R_DIE: offset = 16;
                    if(spriteIndex==19){
                        state=State.R_IDLE;
                    }
                    break;
                case L_IDLE: offset = 20;
                    break;
                case L_RUN: offset = 24;
                    if(spriteIndex==27){
                        state=State.L_IDLE;
                    }
                    break;
                case L_ATK: offset = 28;
                    if(spriteIndex==31){
                        state=State.L_IDLE;
                    }
                    break;
                case L_SATK: offset = 32;
                    if(spriteIndex==35){
                        state=State.L_IDLE;
                    }
                    break;
                case L_DIE: offset = 36;
                    if(spriteIndex==39){
                        state=State.L_IDLE;
                    }
                    break;
            }

                spriteIndex = offset + ((spriteIndex + 1)%4);
                sprite.setSprite(spriteIndex);
                e =0;
        }

    }





    public void paint(Clock clock) {
        if(!hasLoaded) return;

    }


}
