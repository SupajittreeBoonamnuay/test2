package sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.Screen.TestScreen;


public class Zealot {

    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;


    public enum State{
        IDLE,DIED
    };
    private State state = State.IDLE;
    private int e= 0;
    private  int offset =0;
    private int t = 1000;


//==============================================================================================================================================================
    public Zealot(final World world ,final float x_px, final float y_px){
        this.sprite = SpriteLoader.getSprite("images/zealot.json");
        sprite.addCallback(new Callback<Sprite>(){
                @Override
                public void onSuccess(Sprite result){
                    sprite.setSprite(spriteIndex);
                    sprite.layer().setOrigin(sprite.width() / 2f, sprite.height() / 2f);
                    sprite.layer().setTranslation(x_px, y_px);

                    body = initPhysicsBody(world, TestScreen.M_PER_PIXEL *x_px,TestScreen.M_PER_PIXEL*y_px);
                    hasLoaded = true;

                    sprite.layer().addListener(new Pointer.Adapter() {
                                @Override
                                public void onPointerEnd(Pointer.Event event) {
                                    /*state = State.IDLE;
                                    spriteIndex = -1;
                                    t=1000;
                                    e=0;*/


                                }
                            });
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
       if(this.state == State.DIED){
            sprite.setSprite(7);
        }

        if(e > 150){
            //t = t-50;
            //System.out.println(t);
            if(t==0){state=state.DIED;}
            switch(state) {
                case IDLE: offset = 0;
                    break;
                case DIED: offset = 4;
                    break;
            }
    if(spriteIndex==7){spriteIndex=spriteIndex+0;}else{
            spriteIndex = offset + ((spriteIndex + 1)%4);
            sprite.setSprite(spriteIndex);
            e =0;}
        }

    }
    private Body body;
    private Body initPhysicsBody(World world,float x,float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        body = world.createBody(bodyDef);
        //EdgeShape shape = new EdgeShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(56 * TestScreen.M_PER_PIXEL /2, sprite.layer().height() * TestScreen.M_PER_PIXEL /2);
        FixtureDef fd  = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(10f,15f),0);


        return body;
    }
    public void paint(Clock clock) {
        if(!hasLoaded) return;
        sprite.layer().setTranslation(body.getPosition().x / TestScreen.M_PER_PIXEL - 10, body.getPosition().y / TestScreen.M_PER_PIXEL);

    }


}
