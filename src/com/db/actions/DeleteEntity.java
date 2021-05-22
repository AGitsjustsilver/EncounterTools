package com.db.actions;

import com.db.Connector;
import com.ui.UI;

import java.sql.SQLException;

public class DeleteEntity extends Connector{

    private EntityActions reader;

    public DeleteEntity(Connector c){
        super(c);
        this.reader = new ReadEntity(c);
    }

    // Implemented Methods
    @Override
    public void adventuringGear() {
        super.adventuringGear();
        System.out.println("Displaying First.");
        this.reader.adventuringGear();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is the type?: ");
        String TYPE  = UI.userIn.nextLine();

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);
            this.cst.setString(2, TYPE);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void appearances() {
        super.appearances();
        System.out.println("Displaying First.");
        this.reader.appearances();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void armors() {
        super.armors();
        System.out.println("Displaying First.");
        this.reader.armors();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is the type?: ");
        String TYPE  = UI.userIn.nextLine();

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);
            this.cst.setString(2, TYPE);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void backgrounds() {
        super.backgrounds();
        System.out.println("Displaying First.");
        this.reader.backgrounds();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void bonds() {
        super.bonds();
        System.out.println("Displaying First.");
        this.reader.bonds();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void environmentalEffects() {
        super.environmentalEffects();
        System.out.println("Displaying First.");
        this.reader.environmentalEffects();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void flaws() {
        super.flaws();
        System.out.println("Displaying First.");
        this.reader.flaws();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void ideals() {
        super.ideals();
        System.out.println("Displaying First.");
        this.reader.ideals();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void itemEffects() {
        super.itemEffects();
        System.out.println("Displaying First.");
        this.reader.itemEffects();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void mannerisms() {
        super.mannerisms();
        System.out.println("Displaying First.");
        this.reader.mannerisms();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void monsters() {
        super.monsters();
        System.out.println("Displaying First.");
        this.reader.monsters();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void names() {
        super.names();
        System.out.println("Displaying First.");
        this.reader.names();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void traits() {
        super.traits();
        System.out.println("Displaying First.");
        this.reader.traits();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }

    @Override
    public void weapons() {
        super.weapons();
        System.out.println("Displaying First.");
        this.reader.weapons();

        System.out.print("\nWhat ID would you like to delete?: ");
        int ID = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nWhat is the type?: ");
        String TYPE  = UI.userIn.nextLine();

        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.deleteStatement);

            this.cst.setInt(1, ID);
            this.cst.setString(2, TYPE);

            this.cst.executeUpdate();
            System.out.println("DELETE SUCCESSFUL");
        } catch (SQLException ex){
            System.out.println("DELETE FAILED\n"+ex.getMessage());
        }
    }
}
