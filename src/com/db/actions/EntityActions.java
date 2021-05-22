package com.db.actions;

import com.db.Entities;
import com.ui.UI;

public abstract class EntityActions {

    protected Entities entity = null;


    public void adventuringGear(){
        this.entity = Entities.ADVENTURING_GEAR;
    }
    public void appearances(){
        this.entity = Entities.APPEARANCES;
    }
    public void armors(){
        this.entity = Entities.ARMORS;
    }
    public void backgrounds(){
        this.entity = Entities.BACKGROUNDS;
    }
    public void bonds(){
        this.entity = Entities.BONDS;
    }
    public void environmentalEffects(){
        this.entity = Entities.ENVIRONMENTAL_EFFECTS;
    }
    public void flaws(){
        this.entity = Entities.FLAWS;
    }
    public void ideals(){
        this.entity = Entities.IDEALS;
    }
    public void itemEffects(){
        this.entity = Entities.ITEM_EFFECTS;
    }
    public void mannerisms(){
        this.entity = Entities.MANNERISMS;
    }
    public void monsters(){
        this.entity = Entities.MONSTERS;
    }
    public void names(){
        this.entity = Entities.NAMES;
    }
    public void traits(){
        this.entity = Entities.TRAITS;
    }
    public void weapons(){
        this.entity = Entities.WEAPONS;
    }

    public boolean cont(){
        System.out.print("15 more lines?(true or false): ");
        String r = UI.userIn.nextLine();
        return !Boolean.parseBoolean(r);
    }
}
