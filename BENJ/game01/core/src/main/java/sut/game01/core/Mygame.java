package sut.game01.core;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import playn.core.Game;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.PlayN;
import playn.core.util.Clock;
import sut.game01.core.Screen.Homescreen;
import sut.game01.core.Screen.TestScreen;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;


public class Mygame extends Game.Default {
  private ScreenStack ss = new ScreenStack();
  private Clock.Source clock = new Clock.Source(33);


  public Mygame() {
    super(33);
  }

  @Override
  public void init() {
     final Screen home = new Homescreen(ss);
     final Screen tests = new TestScreen(ss);
      ss.push(home);
     PlayN.keyboard().setListener(new Keyboard.Adapter() {
         @Override
         public void onKeyDown(Keyboard.Event event) {
             if (event.key() == Key.RIGHT) {
                 ss.push(tests);
             } else if (event.key() == Key.LEFT) {
                 ss.popTo(home);
             }
         }
         }

         );
     }

      @Override
  public void update(int delta) {
    ss.update(delta);
  }

  @Override
  public void paint(float alpha) {
   clock.paint(alpha);
   ss.paint(clock);
  }

}
