package sut.game01.core;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.util.Callback;
import static playn.core.PlayN.*;

public class Pea {
  public static String IMAGE = "images/sd_gundam.png";
  private ImageLayer layer;
  private int elapsed;
  private final float angVel = (tick() % 10 - 5) / 1000f;

  public Pea(final GroupLayer peaLayer, final float x, final float y) {
    Image image = assets().getImage(IMAGE);
    layer = graphics().createImageLayer(image);

    // Add a callback for when the image loads.
    // This is necessary because we can't use the width/height (to center the
    // image) until after the image has been loaded
    image.addCallback(new Callback<Image>() {
      @Override
      public void onSuccess(Image image) {
        layer.setOrigin(image.width() / 2f, image.height() / 2f);
        layer.setTranslation(x, y);
        peaLayer.add(layer);
      }

      @Override
      public void onFailure(Throwable err) {
        log().error("Error loading image!", err);
      }
    });
  }
private int x=0,y=0;
  public void update(int delta) {
    elapsed += delta;
     float now = elapsed + delta * Mygame.UPDATE_RATE;
    if(delta%2==0){
      x=x+10;
      y=y+10;
      layer.setTranslation(x,y);
      
    }else{
      x=x+10;
      y=y+10;
      layer.setTranslation(x,y);
    }
  }

  public void paint(float alpha) {
   
    
    
  }
}

