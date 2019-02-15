package jp.co.systena.tigerscave.RpgGame.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.systena.tigerscave.RpgGame.application.model.Boss;
import jp.co.systena.tigerscave.RpgGame.application.model.Mob;
import jp.co.systena.tigerscave.RpgGame.application.model.Robber;
import jp.co.systena.tigerscave.RpgGame.application.model.Warrior;

@Controller
public class RpgController {

  List<String> messageList = new ArrayList<String>();

  Warrior warrior = new Warrior();
  Robber robber = new Robber();
  Boss boss = new Boss();
  Mob mob = new Mob();

  @RequestMapping("/start") // URLとのマッピング

  public String index(ModelMap modelMap) {

    Random rand = new Random();
    int humanNum1 = rand.nextInt(2);
    int humanNum2 = rand.nextInt(2);
    int enemyNum1 = rand.nextInt(2);
    int enemyNum2 = rand.nextInt(2);
    int kaihukuNum = rand.nextInt(3);

    int humanFlg = 0;
    int enemyFlg = 0;

    //ヒューマンチームの攻撃
    switch(humanNum1) {

      //戦士の攻撃
      case 0:
          if(kaihukuNum == 0 && !(warrior.getHp() == 100)) {
            warrior.setHp(warrior.kaihuku(modelMap, messageList));
          }

          //ボスへの攻撃
          if(humanNum2 == 2) {

            boss.setHp(warrior.sentou(boss.getHp(), "ボス", modelMap, messageList));

          //モブへの攻撃
          }else{

            mob.setHp(warrior.sentou(mob.getHp(), "モブ", modelMap, messageList));
          }

        if(warrior.getHp() <= 0) {
          warrior.setAp(0);
        }

        break;

      //盗賊の攻撃
      case 1:
        if(kaihukuNum == 0 && !(robber.getHp() == 100)) {
          robber.setHp(robber.kaihuku(modelMap, messageList));
        }

        //ボスへの攻撃
        if(humanNum2 == 2) {

          boss.setHp(robber.sentou(boss.getHp(), "ボス", modelMap, messageList));

        //モブへの攻撃
        }else{

          mob.setHp(robber.sentou(mob.getHp(), "モブ", modelMap, messageList));
        }

        if(robber.getHp() <= 0) {
          robber.setAp(0);
        }
        break;
    }

    //エネミーチームの攻撃
    switch(enemyNum1) {
      //ボスの攻撃
      case 0:
        //戦士への攻撃
        if(enemyNum2 == 0) {

          warrior.setHp(boss.sentou(warrior.getHp(), "戦士", modelMap, messageList));

        //盗賊への攻撃
        }else{

          robber.setHp(boss.sentou(robber.getHp(), "盗賊", modelMap, messageList));
        }
        break;

      //モブの攻撃
      case 1:
        //戦士への攻撃
        if(enemyNum2 == 1) {

          warrior.setHp(mob.sentou(warrior.getHp(), "戦士", modelMap, messageList));

        //盗賊への攻撃
        }else{

          robber.setHp(mob.sentou(robber.getHp(), "盗賊", modelMap, messageList));
        }
        break;
    }

    if(boss.getHp() <= 0) {
      boss.setAp(0);
    }
    if(mob.getHp() <= 0) {
      mob.setAp(0);
    }

    int warriorHp = warrior.getHp();
    int robberHp = robber.getHp();
    int bossHp = boss.getHp();
    int mobHp = mob.getHp();

    if(warriorHp <= 0 && robberHp <= 0) {
      humanFlg = 1;
    }

    if(bossHp <= 0 && mobHp <= 0) {
      enemyFlg = 1;
    }

    if(humanFlg == 1) {

      modelMap.addAttribute("messageList", "エネミーチームの勝利！");
    }else if(enemyFlg == 1) {

      modelMap.addAttribute("messageList", "ヒューマンチームの勝利！");
    }

    modelMap.addAttribute("warriorHp", warriorHp);
    modelMap.addAttribute("robberHp", robberHp);
    modelMap.addAttribute("bossHp", bossHp);
    modelMap.addAttribute("mobHp", mobHp);

    return "start";
  }
}