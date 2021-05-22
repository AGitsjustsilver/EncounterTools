package com.db.actions;

import com.db.Connector;
import com.ui.UI;

import java.sql.SQLException;

public class UpdateEntity extends Connector{

    private EntityActions reader;

    public UpdateEntity(Connector c){
        super(c);
        this.reader = new ReadEntity(c);
    }

    // Implemented Methods
    @Override
    public void adventuringGear() {
        super.adventuringGear();
        System.out.println("Displaying First.");
        this.reader.adventuringGear();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString(1,NAME);
            this.cst.setString(2,TYPE);
            this.cst.setInt(3,COST);
            this.cst.setFloat(4,WEIGHT);
            this.cst.setString(5,BOOK);
            this.cst.setInt(6,PAGE);
            this.cst.setInt(7,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void appearances() {
        super.appearances();
        System.out.println("Displaying First.");
        this.reader.appearances();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String APPEARANCE_DESC  = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK   = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,APPEARANCE_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);
            this.cst.setInt( 4,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void armors() {
        super.armors();
        System.out.println("Displaying First.");
        this.reader.armors();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,NAME);
            this.cst.setInt( 2,COST);
            this.cst.setFloat( 3,WEIGHT);
            this.cst.setInt( 4,BASE_AC);
            this.cst.setInt( 5,AC_MAX_BONUS);
            this.cst.setBoolean( 6,STEALTH);
            this.cst.setInt( 7,STR_REQ);
            this.cst.setString( 8,BOOK);
            this.cst.setInt( 9,PAGE);
            this.cst.setInt( 10,ID);
            this.cst.setString( 11,TYPE);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void backgrounds() {
        super.backgrounds();
        System.out.println("Displaying First.");
        this.reader.backgrounds();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,BACKGROUND_NAME);
            this.cst.setString( 2,BACKGROUND_DESC);
            this.cst.setString( 3,BOOK);
            this.cst.setInt( 4,PAGE);
            this.cst.setInt( 5,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void bonds() {
        super.bonds();
        System.out.println("Displaying First.");
        this.reader.bonds();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String BOND_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,BOND_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);
            this.cst.setInt( 4,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void environmentalEffects() {
        super.environmentalEffects();
        System.out.println("Displaying First.");
        this.reader.environmentalEffects();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is its name?: ");
        String NAME   = UI.userIn.nextLine();
        System.out.print("\nType out the description: ");
        String EFFECT_DESC   = UI.userIn.nextLine();
        System.out.print("\nWhat environment can this effect take place in?: ");
        String ENVIRONMENT   = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE = Integer.parseInt(UI.userIn.nextLine());


        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

                this.cst.setString(1, NAME);
                this.cst.setString(2, EFFECT_DESC);
                this.cst.setString(3, ENVIRONMENT);
                this.cst.setString(4, BOOK);
                this.cst.setInt(5, PAGE);
                this.cst.setInt(6, ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void flaws() {
        super.flaws();
        System.out.println("Displaying First.");
        this.reader.flaws();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String FLAW_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,FLAW_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);
            this.cst.setInt( 4,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ideals() {
        super.ideals();
        System.out.println("Displaying First.");
        this.reader.ideals();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String IDEAL_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,IDEAL_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);
            this.cst.setInt( 4,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void itemEffects() {
        super.itemEffects();
        System.out.println("Displaying First.");
        this.reader.itemEffects();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,F_NAME);
            this.cst.setString( 2,L_NAME);
            this.cst.setString( 3,TYPE);
            this.cst.setBoolean( 4,ATTUNEMENT_REQ);
            this.cst.setString( 5,DESCRIPTION);
            this.cst.setString( 6,BOOK);
            this.cst.setInt( 7,PAGE);
            this.cst.setInt( 8,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mannerisms() {
        super.mannerisms();
        System.out.println("Displaying First.");
        this.reader.mannerisms();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String MANNERISM_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,MANNERISM_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);
            this.cst.setInt( 4,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void monsters() {
        super.monsters();
        System.out.println("Displaying First.");
        this.reader.monsters();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
        System.out.print("\nWhat environment can this monster be in?(space separate please): ");
        String ENVIRONMENT = UI.userIn.nextLine();
        System.out.print("\nIs it Legendary?:(True or False) ");
        boolean IS_LEGENDARY = Boolean.parseBoolean(UI.userIn.nextLine());
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

                this.cst.setString( 1,NAME);
                this.cst.setString( 2,SIZE);
                this.cst.setString( 3,TYPE);
                this.cst.setString( 4,ALIGNMENT);
                this.cst.setInt( 5,AC);
                this.cst.setInt( 6,HP);
                this.cst.setFloat( 7,CR);
                this.cst.setString( 8,ENVIRONMENT);
                this.cst.setBoolean( 9,IS_LEGENDARY);
                this.cst.setString( 10,BOOK);
                this.cst.setInt( 11,PAGE);
                this.cst.setInt( 12,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void names() {
        super.names();
        System.out.println("Displaying First.");
        this.reader.names();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,NAME);
            this.cst.setString( 2,TYPE);
            this.cst.setString( 3,RACE);
            this.cst.setString( 4,BOOK);
            this.cst.setInt( 5,PAGE);
            this.cst.setInt( 6,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void traits() {
        super.traits();
        System.out.println("Displaying First.");
        this.reader.traits();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nType out the description: ");
        String TRAIT_DESC = UI.userIn.nextLine();
        System.out.print("\nWhich Book?(acronym form): ");
        String BOOK  = UI.userIn.nextLine();
        System.out.print("\nWhat page?(If no page put 0): ");
        int PAGE= Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,TRAIT_DESC);
            this.cst.setString( 2,BOOK);
            this.cst.setInt( 3,PAGE);
            this.cst.setInt( 4,ID);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void weapons() {
        super.weapons();
        System.out.println("Displaying First.");
        this.reader.weapons();

        System.out.print("\nWhat ID would you like to edit?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
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
            this.cst = this.con.prepareStatement(entity.updateStatement);

            this.cst.setString( 1,NAME);
            this.cst.setInt( 2,COST);
            this.cst.setFloat( 3,WEIGHT);
            this.cst.setString( 4,DAMAGE_DICE);
            this.cst.setString( 5,DAMAGE_TYPE);
            this.cst.setString( 6,PROPERTIES);
            this.cst.setString( 7,BOOK);
            this.cst.setInt( 8,PAGE);
            this.cst.setInt( 9,ID);
            this.cst.setString( 10,TYPE);

            this.cst.executeUpdate();
            System.out.println("UPDATE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("UPDATE FAILED");
            System.out.println(ex.getMessage());
        }
    }
}
