package jp.co.systena.tigerscave.RpgGame.application.model;

import java.util.List;
import org.springframework.ui.ModelMap;

public abstract class Human extends Character{

    public abstract int kaihuku(ModelMap modelMap, List<String> messageList);
}
