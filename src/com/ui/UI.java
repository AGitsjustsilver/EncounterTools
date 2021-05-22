package com.ui;

import com.db.Connector;
import com.db.actions.*;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    public static final Scanner userIn = new Scanner(System.in);

    private Connector connection;

    private CreateEntity create = null;
    private ReadEntity read = null;
    private UpdateEntity update = null;
    private DeleteEntity delete = null;

    private CmdMenuUi
            mainMenu,
            genMenu,
            createmenu,
            readmenu,
            deletemenu,
            updatemenu,
            again;


    public UI(){
        this.connection = new Connector();
        mainMenu = new CmdMenuUi(
                true,
                "Welcome to Dungeon Master's Encounter Tools\nGeneral Menu",
                    new String[]{
                            "Generate Encounters",
                            "Create Data",
                            "Read Data",
                            "Update Data",
                            "Delete Data",
                    }
        );

        genMenu = new CmdMenuUi(
                "Generators",
                new String[]{
                        "Generate Easy Encounter",
                        "Generate Easy Monsters",
                        "Generate Medium Encounter",
                        "Generate Medium Monsters",
                        "Generate Hard Encounter",
                        "Generate Hard Monsters",
                        "Generate Deadly Encounter",
                        "Generate Deadly Monsters",
                        "Generate Full Character",
                        "Generate Names",
                        "Generate Characteristics",
                        "Generate Random Shop Inventory",
                        "Generate Adventuring Gear",
                        "Generate Weapons",
                        "Generate Armors"
                }
        );

        String[] entities =  {
                "Adventuring Gear",
                "Appearances",
                "Armors",
                "Backgrounds",
                "Bonds",
                "Environmental Effects",
                "Flaws",
                "Ideals",
                "Item Effects",
                "Mannerisms",
                "Monsters",
                "Names",
                "Traits",
                "Weapons"
        };

        createmenu = new CmdMenuUi("Create Entries",entities);

        readmenu = new CmdMenuUi("Read Entries",entities);

        deletemenu = new CmdMenuUi("Delete Entries", entities);

        updatemenu = new CmdMenuUi("Update Entries", entities);

        again = new CmdMenuUi("", new String[]{"Again"});

        this.run();
    }

    private void run(){
        int choice = -1;
        while(choice != 6){
            UI.cls();
            switch (choice) {
                case -1: // DB Login
                    boolean isLoggedIn = this.logIn();
                    while(!isLoggedIn){
                        isLoggedIn = this.logIn();
                    }
                    System.out.println("CONNECTED!");
                    // Proper login means choice = 0
                case 0: // Welcome and menu
                    UI.cls();
                    mainMenu.makeChoice();
                    choice = mainMenu.getIntChoice();
                    break;
                case 1: // Encounter
                    read = new ReadEntity(this.connection);
                    encounterInterface();
                    choice = 0;
                    break;
                case 2: // create
                    this.create = new CreateEntity(this.connection);
                    createEntityMenu();
                    choice = 0;
                    break;
                case 3: // update
                    this.read = new ReadEntity(this.connection);
                    readEntityMenu();
                    choice = 0;
                    break;
                case 4: // update
                    this.update = new UpdateEntity(this.connection);
                    updateEntityMenu();
                    choice = 0;
                    break;
                case 5: // delete
                    this.delete = new DeleteEntity(this.connection);
                    deleteEntityMenu();
                    choice = 0;
                    break;
            }
        }
        this.connection.logOut();
        System.out.println("Goodbye & Good Luck!");
    }

    // TODO - REMOVE LOGIN CREDENTIALS WHEN DONE
    private boolean logIn(){
        String u, p;
        System.out.println("Log in");
        System.out.print("Username: ");
        u = UI.userIn.nextLine();
        System.out.print("Password: ");
        p = UI.userIn.nextLine();
        return this.connection.logIn(u,p);
    }

    private void encounterInterface(){
        UI.cls();
        while (genMenu.makeChoice()) {
            do {
                switch (genMenu.getIntChoice()) {
                    case 1: // Generate Easy Encounter
                        this.read.genEncounter("Easy");
                        break;
                    case 2: // Generate Easy Monsters
                        this.read.monsterList("Easy");
                        break;
                    case 3: // Generate Medium Encounter
                        this.read.genEncounter("Medium");
                        break;
                    case 4: // Generate Medium Monsters
                        this.read.monsterList("Medium");
                        break;
                    case 5: // Generate Hard Encounter
                        this.read.genEncounter("Hard");
                        break;
                    case 6: // Generate Hard Monsters
                        this.read.monsterList("Hard");
                        break;
                    case 7: // Generate Deadly Encounter
                        this.read.genEncounter("Deadly");
                        break;
                    case 8: // Generate Deadly Monsters
                        this.read.monsterList("Deadly");
                        break;
                    case 9: // Generate Full Character
                        this.read.genCharacter();
                        break;
                    case 10: // Generate Names
                        this.read.randomNameList();
                        break;
                    case 11: // Generate Characteristics
                        this.read.genCharacteristics();
                        break;
                    case 12: // Generate Random Shop Inventory
                        this.read.randomShop();
                        break;
                    case 13: // Generate Adventuring Gear
                        this.read.genAdventureGear();
                        break;
                    case 14: // Generate Weapons
                        this.read.genWeapons();
                        break;
                    case 15: // Generate Armors
                        this.read.genArmors();
                        break;
                }
            } while (again.makeChoice());
        }
    }

    private void createEntityMenu() {
        UI.cls();
        while(createmenu.makeChoice()) {
            do {
                switch(createmenu.getIntChoice()){
                    case 1: // adventuringGear
                        this.create.adventuringGear();
                        break;
                    case 2: // appearances
                        this.create.appearances();
                        break;
                    case 3: // armors
                        this.create.armors();
                        break;
                    case 4: // backgrounds
                        this.create.backgrounds();
                        break;
                    case 5: // bonds
                        this.create.bonds();
                        break;
                    case 6: // environmentalEffects
                        this.create.environmentalEffects();
                        break;
                    case 7: // flaws
                        this.create.flaws();
                        break;
                    case 8: // ideals
                        this.create.ideals();
                        break;
                    case 9: // itemEffects
                        this.create.itemEffects();
                        break;
                    case 10: // mannerisms
                        this.create.mannerisms();
                        break;
                    case 11: // monsters
                        this.create.monsters();
                        break;
                    case 12: // names
                        this.create.names();
                        break;
                    case 13: // traits
                        this.create.traits();
                        break;
                    case 14: // weapons
                        this.create.weapons();
                        break;
                    default:
                }
            } while(again.makeChoice());
        }
    }

    private void readEntityMenu() {
        UI.cls();
        while(readmenu.makeChoice()) {
            do {
                switch(readmenu.getIntChoice()){
                    case 1: // adventuringGear
                        this.read.adventuringGear();
                        break;
                    case 2: // appearances
                        this.read.appearances();
                        break;
                    case 3: // armors
                        this.read.armors();
                        break;
                    case 4: // backgrounds
                        this.read.backgrounds();
                        break;
                    case 5: // bonds
                        this.read.bonds();
                        break;
                    case 6: // environmentalEffects
                        this.read.environmentalEffects();
                        break;
                    case 7: // flaws
                        this.read.flaws();
                        break;
                    case 8: // ideals
                        this.read.ideals();
                        break;
                    case 9: // itemEffects
                        this.read.itemEffects();
                        break;
                    case 10: // mannerisms
                        this.read.mannerisms();
                        break;
                    case 11: // monsters
                        this.read.monsters();
                        break;
                    case 12: // names
                        this.read.names();
                        break;
                    case 13: // traits
                        this.read.traits();
                        break;
                    case 14: // weapons
                        this.read.weapons();
                        break;
                    default:
                }
            } while(again.makeChoice());
        }
    }

    private void updateEntityMenu() {
        UI.cls();
        while(updatemenu.makeChoice()) {
            do {
                switch(updatemenu.getIntChoice()) {
                    case 1: // adventuringGear
                        this.update.adventuringGear();
                        break;
                    case 2: // appearances
                        this.update.appearances();
                        break;
                    case 3: // armors
                        this.update.armors();
                        break;
                    case 4: // backgrounds
                        this.update.backgrounds();
                        break;
                    case 5: // bonds
                        this.update.bonds();
                        break;
                    case 6: // environmentalEffects
                        this.update.environmentalEffects();
                        break;
                    case 7: // flaws
                        this.update.flaws();
                        break;
                    case 8: // ideals
                        this.update.ideals();
                        break;
                    case 9: // itemEffects
                        this.update.itemEffects();
                        break;
                    case 10: // mannerisms
                        this.update.mannerisms();
                        break;
                    case 11: // monsters
                        this.update.monsters();
                        break;
                    case 12: // names
                        this.update.names();
                        break;
                    case 13: // traits
                        this.update.traits();
                        break;
                    case 14: // weapons
                        this.update.weapons();
                        break;
                    default:
                }
            } while(again.makeChoice());
        }
    }

    private void deleteEntityMenu() {
        UI.cls();
        while (deletemenu.makeChoice()) {
            do {
                switch(deletemenu.getIntChoice()){
                    case 1: // adventuringGear
                        this.delete.adventuringGear();
                        break;
                    case 2: // appearances
                        this.delete.appearances();
                        break;
                    case 3: // armors
                        this.delete.armors();
                        break;
                    case 4: // backgrounds
                        this.delete.backgrounds();
                        break;
                    case 5: // bonds
                        this.delete.bonds();
                        break;
                    case 6: // environmentalEffects
                        this.delete.environmentalEffects();
                        break;
                    case 7: // flaws
                        this.delete.flaws();
                        break;
                    case 8: // ideals
                        this.delete.ideals();
                        break;
                    case 9: // itemEffects
                        this.delete.itemEffects();
                        break;
                    case 10: // mannerisms
                        this.delete.mannerisms();
                        break;
                    case 11: // monsters
                        this.delete.monsters();
                        break;
                    case 12: // names
                        this.delete.names();
                        break;
                    case 13: // traits
                        this.delete.traits();
                        break;
                    case 14: // weapons
                        this.delete.weapons();
                        break;
                    default:
                }
            } while (again.makeChoice()) ;
        }
    }


    private static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

}
