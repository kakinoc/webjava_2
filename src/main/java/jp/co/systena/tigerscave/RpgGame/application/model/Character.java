package jp.co.systena.tigerscave.RpgGame.application.model;

import java.util.List;
import org.springframework.ui.ModelMap;

public abstract class Character  {

  int ap;
  int hp;
  int speed;

  public int getAp() {
    return ap;
  }

  public void setAp(int ap) {
    this.ap = ap;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public abstract int sentou(int hp, String name, ModelMap modelMap, List<String> messages);

}
