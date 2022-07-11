package me.adrea.lifesteal.settings;

import me.adrea.lifesteal.settings.modules.ServerStartCountdown;
import me.adrea.lifesteal.settings.modules.eliminations.Elimination;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public List<Setting> classes = new ArrayList<>();

    public void initialize() {
       classes.add(new Elimination());
       classes.add(new ServerStartCountdown());

    }

}
