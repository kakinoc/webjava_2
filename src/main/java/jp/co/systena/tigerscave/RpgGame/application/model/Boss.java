package jp.co.systena.tigerscave.RpgGame.application.model;

import java.util.List;
import org.springframework.ui.ModelMap;

public class Boss extends Enemy {

  private int ap = 50;
  private int hp = 100;
  private int speed = 10;

  //Getter
  public int getAp() { return ap; }
  public int getHp() { return hp; }
  public int getSpeed() { return speed; }

  //Setter
  public void setAp(int ap) { this.ap = ap; }
  public void setHp(int hp) { this.hp = hp; }
  public void setSpeed(int speed) { this.speed = speed; }

  @Override
  public int sentou(int hp, String name, ModelMap modelMap, List<String> messageList) {

    if(this.hp <= 0) {

    }else {

      messageList.add("ボスは" + name + "に必殺技を使った！");
      if((hp - this.ap) > 0) {

        hp = hp - this.ap;
      }else if(hp < this.ap) {

        hp = 0;
        messageList.add("ボスは" + name + "を倒した！");
      }else {

        messageList.add("しかし" + name + "はすでに死んでいる！");
      }

      modelMap.addAttribute("messageList", messageList);
    }

    return hp;
  }
}
