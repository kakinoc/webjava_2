package jp.co.systena.tigerscave.RpgGame.application.model;

import java.util.List;
import org.springframework.ui.ModelMap;

public class Robber extends Human {

  private int ap = 30;
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

    int flg = 0;
    int oldHp = this.hp;

    if(this.hp <= 0) {

    }else {

      messageList.add("盗賊は" + name + "の体力を盗んだ！");
      if((hp - this.ap) > 0) {

        flg = 1;
        hp = hp - this.ap;
        this.hp = this.hp + this.ap;
      }else if(hp < this.ap) {

        flg = 1;
        hp = 0;
        this.hp = this.hp + hp;
        messageList.add(name + "を倒した！");
      }else {

        messageList.add("しかし" + name + "はすでに死んでいる！");
      }

      if(this.hp > 100) {

        this.hp = 100;
      }
      if(flg == 1) {

        messageList.add("盗賊は体力を" + (this.hp - oldHp) + "回復した！");
      }

      modelMap.addAttribute("messageList", messageList);
    }

    return hp;
  }

  @Override
  public int kaihuku(ModelMap modelMap, List<String> messageList) {

    messageList.add("盗賊は体力を25回復した！");
    modelMap.addAttribute("messageList", messageList);
    if(this.hp + 25 > 100) {

      this.hp = 100;
    }else {

      this.hp = this.hp + 25;
    }

    return this.hp;
  }
}