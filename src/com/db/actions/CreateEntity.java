package com.db.actions;

import com.db.Connector;
import com.ui.UI;

import java.sql.SQLException;

public class CreateEntity extends Connector{

    public CreateEntity(Connector c){
        super(c);
    }
    // Implemented Methods

    @Override
    public void adventuringGear() {
        super.adventuringGear();
        // ADVENTURING_GEAR
        // TYPE VARCHAR(20), NAME VARCHAR(30), BOOK VARCHAR(10)
        System.out.print("\nWhat type is it?: ");
        String TYPE = UI.userIn.nextLine();
        System.out.print("\nWhat is its name?: ");
        String NAME = UI.userIn.nextLine();
        System.out.print("\nWhat is the cost in copper?: ");
        int COST = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its weight?(number): ");
        float WEIGHT = Float.parseFloat(UI.userIn.nextLine());
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString(1,NAME);
            this.cst.setString(2,TYPE);
            this.cst.setInt(3,COST);
            this.cst.setFloat(4,WEIGHT);
            this.cst.setString(5,BOOK);
            this.cst.setInt(6,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void appearances() {
        super.appearances();

        // APPEARANCES
        // APPEARANCE_DESC VARCHAR(60), BOOK VARCHAR(10)
        System.out.print("\nType out the description: ");
        String APPEARANCE_DESC  = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK   = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,APPEARANCE_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void armors() {
        super.armors();

        // ARMORS
        // TYPE VARCHAR(20), NAME VARCHAR(25), BOOK VARCHAR(10)
        System.out.print("\nWhat type is it?(light, medium, heavy, neither): ");
        String TYPE = UI.userIn.nextLine();
        if(TYPE.equalsIgnoreCase("neither")) TYPE = "";
        System.out.print("\nWhat is its name?: ");
        String NAME = UI.userIn.nextLine();
        System.out.print("\nWhat is the cost in copper?: ");
        int COST = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its weight?(number): ");
        float WEIGHT = Float.parseFloat(UI.userIn.nextLine());
        System.out.print("\nWhat is its base armor class?(): ");
        int BASE_AC = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its max bonus?(): ");
        int AC_MAX_BONUS = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nDoes it have stealth disadvantage?(): ");
        boolean STEALTH = Boolean.parseBoolean(UI.userIn.nextLine());
        System.out.print("\nWhat is its strength requirement?(): ");
        int STR_REQ = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,NAME);
            this.cst.setString( 2,TYPE);
            this.cst.setInt( 3,COST);
            this.cst.setFloat( 4,WEIGHT);
            this.cst.setInt( 5,BASE_AC);
            this.cst.setInt( 6,AC_MAX_BONUS);
            this.cst.setBoolean( 7,STEALTH);
            this.cst.setInt( 8,STR_REQ);
            this.cst.setString( 9,BOOK);
            this.cst.setInt( 10,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void backgrounds() {
        super.backgrounds();

        // BACKGROUNDS
        // BACKGROUND_NAME VARCHAR(20), BACKGROUND_DESC VARCHAR(650), BOOK VARCHAR(10)
        System.out.print("\nWhat is the background's name?: ");
        String BACKGROUND_NAME  = UI.userIn.nextLine();
        System.out.print("\nType out the description: ");
        String BACKGROUND_DESC  = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK   = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,BACKGROUND_NAME);
            this.cst.setString( 2,BACKGROUND_DESC);
            this.cst.setString( 3,BOOK);
            this.cst.setInt( 4,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void bonds() {
        super.bonds();

        // BONDS
        // BOND_DESC VARCHAR(130), BOOK VARCHAR(10)
        System.out.print("\nType out the description: ");
        String BOND_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,BOND_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void environmentalEffects() {
        super.environmentalEffects();

        // ENVIRONMENTAL_EFFECTS
        // NAME VARCHAR(20) , EFFECT_DESC VARCHAR(2000) , ENVIRONMENT VARCHAR(11) , BOOK VARCHAR(10)
        System.out.print("\nWhat is its name?: ");
        String NAME   = UI.userIn.nextLine();
        System.out.print("\nType out the description: ");
        String EFFECT_DESC   = UI.userIn.nextLine();
        System.out.print("\nWhat environments can this effect take place in?(space separate please): ");
        String ENVIRONMENT   = UI.userIn.nextLine();
        String[] env = ENVIRONMENT.split(" ");
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());

        for (String e :env) {
            this.getConnection();
            try {
                this.cst = this.con.prepareStatement(entity.createStatement);

                this.cst.setString(1, NAME);
                this.cst.setString(2, EFFECT_DESC);
                this.cst.setString(3, e);
                this.cst.setString(4, BOOK);
                this.cst.setInt(5, PAGE);

                cst.executeUpdate();
                System.out.println("\nSUBMITTED!");
            } catch (SQLException ex) {
                System.out.println("\nFAILED TO SUBMIT!");
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void flaws() {
        super.flaws();

        // FLAWS
        // FLAW_DESC VARCHAR(120), BOOK VARCHAR(10)
        System.out.print("\nType out the description: ");
        String FLAW_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,FLAW_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void ideals() {
        super.ideals();

        // IDEALS
        // BOOK VARCHAR(10)
        System.out.print("\nType out the description: ");
        String IDEAL_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,IDEAL_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void itemEffects() {
        super.itemEffects();

        // ITEM_EFFECTS
        // F_NAME VARCHAR(20), L_NAME VARCHAR(25), TYPE VARCHAR(1) DESCRIPTION VARCHAR(475) , BOOK VARCHAR(10)
        System.out.print("\nWhat is the effect's prefix?: ");
        String F_NAME   = UI.userIn.nextLine();
        System.out.print("\nWhat is the effect's suffix?: ");
        String L_NAME   = UI.userIn.nextLine();
        System.out.print("\nWhat type is it?(W, A, or B): ");
        String TYPE    = UI.userIn.nextLine();
        System.out.print("\nDoes it require attunement?(true or false): ");
        boolean ATTUNEMENT_REQ  = Boolean.parseBoolean(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String DESCRIPTION   = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,F_NAME);
            this.cst.setString( 2,L_NAME);
            this.cst.setString( 3,TYPE);
            this.cst.setBoolean( 4,ATTUNEMENT_REQ);
            this.cst.setString( 5,DESCRIPTION);
            this.cst.setString( 6,BOOK);
            this.cst.setInt( 7,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mannerisms() {
        super.mannerisms();

        // MANNERISMS
        // MANNERISM_DESC VARCHAR(150), BOOK VARCHAR(10)
        System.out.print("\nType out the description: ");
        String MANNERISM_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,MANNERISM_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void monsters() {
        super.monsters();

        // MONSTERS
        // NAME VARCHAR(30), SIZE VARCHAR(10), TYPE VARCHAR(11), ALIGNMENT VARCHAR(2), ENVIRONMENT VARCHAR(11), BOOK VARCHAR(10)
        System.out.print("\nWhat is its name?: ");
        String NAME = UI.userIn.nextLine();
        System.out.print("\nWhat size is it?: ");
        String SIZE = UI.userIn.nextLine();
        System.out.print("\nWhat type is it?: ");
        String TYPE = UI.userIn.nextLine();
        System.out.print("\nWhat is it's alignment?(2 characters max): ");
        String ALIGNMENT = UI.userIn.nextLine();
        System.out.print("\nWhat is its armor class?: ");
        int AC = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its hit points?: ");
        int HP = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its challenge rating?(decimal to hundreds place): ");
        float CR = Float.parseFloat(UI.userIn.nextLine());
        System.out.print("\nWhat environments can this monster be in?(space separate please): ");
        String ENVIRONMENT = UI.userIn.nextLine();
        String[] env = ENVIRONMENT.split(" ");
        System.out.print("\nIs it Legendary?:(True or False) ");
        boolean IS_LEGENDARY = Boolean.parseBoolean(UI.userIn.nextLine());
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        for(String en: env) {
            this.getConnection();
            try {
                this.cst = this.con.prepareStatement(entity.createStatement);

                this.cst.setString( 1,NAME);
                this.cst.setString( 2,SIZE);
                this.cst.setString( 3,TYPE);
                this.cst.setString( 4,ALIGNMENT);
                this.cst.setInt( 5,AC);
                this.cst.setInt( 6,HP);
                this.cst.setFloat( 7,CR);
                this.cst.setString( 8,en);
                this.cst.setBoolean( 9,IS_LEGENDARY);
                this.cst.setString( 10,BOOK);
                this.cst.setInt( 11,PAGE);

                cst.executeUpdate();
                System.out.println("\nSUBMITTED!");
            } catch (SQLException e) {
                System.out.println("\nFAILED TO SUBMIT!");
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void names() {
        super.names();

        // NAMES
        // NAME VARCHAR(20), TYPE VARCHAR(1), RACE VARCHAR(10), BOOK VARCHAR(10)
        System.out.print("\nWhat is its name?: ");
        String NAME = UI.userIn.nextLine();
        System.out.print("\nWhat type is it?(M,F,U, or L): ");
        String TYPE = UI.userIn.nextLine();
        System.out.print("\nWhat race does this name apply to?: ");
        String RACE = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,NAME);
            this.cst.setString( 2,TYPE);
            this.cst.setString( 3,RACE);
            this.cst.setString( 4,BOOK);
            this.cst.setInt( 5,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void traits() {
        super.traits();

        // TRAITS
        // TRAIT_DESC VARCHAR(140), BOOK VARCHAR(10)
        System.out.print("\nType out the description: ");
        String TRAIT_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,TRAIT_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void weapons() {
        super.weapons();

        // WEAPONS
        // TYPE VARCHAR(20) , NAME VARCHAR(20), DAMAGE_DICE VARCHAR(5) , DAMAGE_TYPE VARCHAR(8) , PROPERTIES VARCHAR(100) , BOOK VARCHAR(10)
        System.out.print("\nWhat type is it?: ");
        String TYPE  = UI.userIn.nextLine();
        System.out.print("\nWhat is its name?: ");
        String NAME   = UI.userIn.nextLine();
        System.out.print("\nWhat is the cost in copper?: ");
        int COST   = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its weight?(decimal): ");
        float WEIGHT   = Float.parseFloat(UI.userIn.nextLine());
        System.out.print("\nWhat is the damage dice?(ex. 1d6): ");
        String DAMAGE_DICE   = UI.userIn.nextLine();
        System.out.print("\nWhat is its damage type?:(ex. Piercing");
        String DAMAGE_TYPE   = UI.userIn.nextLine();
        System.out.print("\nType out any properties this weapon has: ");
        String PROPERTIES   = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.createStatement);

            this.cst.setString( 1,NAME);
            this.cst.setString( 2,TYPE);
            this.cst.setInt( 3,COST);
            this.cst.setFloat( 4,WEIGHT);
            this.cst.setString( 5,DAMAGE_DICE);
            this.cst.setString( 6,DAMAGE_TYPE);
            this.cst.setString( 7,PROPERTIES);
            this.cst.setString( 8,BOOK);
            this.cst.setInt( 9,PAGE);

            cst.executeUpdate();
            System.out.println("\nSUBMITTED!");
        }catch (SQLException e){
            System.out.println("\nFAILED TO SUBMIT!");
            System.out.println(e.getMessage());
        }
    }
}
