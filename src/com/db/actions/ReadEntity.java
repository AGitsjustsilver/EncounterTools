package com.db.actions;

import com.db.Connector;
import com.db.Entities;
import com.ui.UI;

import java.sql.SQLException;

public class ReadEntity extends Connector {
    public ReadEntity(Connector c){
        super(c);
    }

    // ENCOUNTER

    public void genEncounter(String difficulty){
        System.out.print("\nHow many Players?: ");
        int numPlayers = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nAverage Player Level?(Whole Number): ");
        int apl = Integer.parseInt(UI.userIn.nextLine());
        String q = "";
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL GENENCOUNTER(?, ?, ?)}");

            this.cst.setString(1, difficulty);
            this.cst.setInt(2, numPlayers);
            this.cst.setInt(3, apl);

            this.cst.execute();

            String patternT;
            String patternD;
            String title;
            String lb;

            // Monsters
            this.rs = this.cst.getResultSet();
            patternT =   "| %8s | %-30s | %-10s | %-2s | %-4s | %-9s | %-9s | %-8s |";
            patternD = "| %8.2f | %-30s | %-10s | %-2d | %-4d | %-9s | %-9B | %-8d |";
            title = String.format(patternT, "CR","Name","Size","AC","HP","Alignment","Legendary","XP");
            lb = Entities.getLineBreak(title);

            System.out.println(lb);
            System.out.println("MONSTERS");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);
            
            while(this.rs.next()){
                float cr = this.rs.getFloat(1);
                String name = this.rs.getString(2);
                String size = this.rs.getString(3);
                int ac = this.rs.getInt(4);
                int hp = this.rs.getInt(5);
                String alignment = this.rs.getString(6);
                boolean legendary = this.rs.getBoolean(7);
                int xp = this.rs.getInt(8);
                System.out.println(String.format(patternT, cr,name,size,ac,hp,alignment,legendary,xp));
            }
            System.out.println(lb);
            
            // Loot
            // GEAR
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();
            patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %-50s |";
            patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %-50s |";
            title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)", "Description");
            lb = Entities.getLineBreak(title);

            System.out.println(lb);
            System.out.println("GEAR");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,desc));
            }

            System.out.println(lb);
            // ARMORS
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();
            patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %2s | %2s | %5s | %2s | %-50s |";
            patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %2d | %2d | %5B | %2d | %-50s |";
            title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)", "Base AC",
                    "AC Max Bonus", "Stealth Disadvantage", "STR req", "Description");
            lb = Entities.getLineBreak(title);

            System.out.println(lb);
            System.out.println("ARMOR");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);


            this.rs = this.cst.getResultSet();

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                int ac = this.rs.getInt(7);
                int acb = this.rs.getInt(8);
                boolean stealth = this.rs.getBoolean(9);
                int strreq = this.rs.getInt(10);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,ac,acb,stealth,strreq,desc));
            }
            System.out.println(lb);
            // WEAPONS
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();
            patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %-5s | %-8s | %-50s | %-50s |";
            patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %-5s | %-8s | %-50s | %-50s |";
            title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)","Damage","Damage Type","Properties", "Description");
            lb = Entities.getLineBreak(title);

            System.out.println(lb);
            System.out.println("WEAPONS");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                String damage = this.rs.getString(7);
                String damage_type = this.rs.getString(8);
                String prop = this.rs.getString(9);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,damage,damage_type,prop,desc));
            }

            System.out.println(lb);

            // Env Effects
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();
            patternT = "| %-20s | %11s | %-10s | %s | %s | %s | %s | %-5s | %-50s |";
            patternD = "| %-20s | %11s | %-10s | %d | %d | %d | %d | %-5s | %-50s |";
            title = String.format(patternT, "Name", "Environment", "Danger", "Min Save", "Max Save",
                    "Min Attack Bonus", "Max Attack Bonus", "Suggested Damage Dice", "Description");
            lb = Entities.getLineBreak(title);

            System.out.println(lb);
            System.out.println("ENVIRONMENT EFFECTS");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            while(rs.next()) {
                String name = this.rs.getString(1);
                String description = this.rs.getString(2);
                String environment = this.rs.getString(3);
                String danger = this.rs.getString(4);
                int minsave = this.rs.getInt(5);
                int maxsave = this.rs.getInt(6);
                int minattackbonus = this.rs.getInt(7);
                int maxattackbonus = this.rs.getInt(8);
                String suggesteddamagedice = this.rs.getString(9);
                System.out.println(String.format(patternD, name, environment, danger, minsave, maxsave,
                        minattackbonus, maxattackbonus, suggesteddamagedice, description));
            }
            System.out.println(lb);


        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    // NPC

    public void genCharacter(){
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL GENCHARACTER()}");
            this.cst.execute();
            String patern = "| %s = %s |\n" +
                    "| %s = %s |\n" +
                    "| %s = %s |\n" +
                    "| %s = %s |\n" +
                    "| %s = %s |\n" +
                    "| %s = %s |\n";
            this.rs = this.cst.getResultSet();
            if(this.rs.next()) {
                String name = this.rs.getString(1),
                        gender = this.rs.getString(2),
                        race = this.rs.getString(3),
                        alignment = this.rs.getString(4),
                        background = this.rs.getString(5),
                        backgroundDescription = this.rs.getString(6);
                switch (gender){
                    case "M":
                        gender = "Male";
                        break;
                    case "F":
                        gender = "Female";
                        break;
                    case "U":
                        gender = "Non-Binary";
                        break;
                }
                System.out.print(String.format(patern,
                        "Name",name, "Gender", gender, "Race", race, "Alignment", alignment, "Background", background,"Background description", backgroundDescription));
            }
            this.genCharacteristics();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void randomNameList(){
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL NAMELIST()}");
            this.cst.execute();

            String pattern = "| %-25s | %-25s | %-6s | %-10s |\n";
            String title = String.format(pattern, "First Name", "Last Name", "Gender", "Race");
            String lb = Entities.getLineBreak(title);

            System.out.print(title);
            System.out.println(lb);

            this.rs = this.cst.getResultSet();

            while (this.rs.next()) {
                String fName = this.rs.getString(1),
                        lName = this.rs.getString(2),
                        gender = this.rs.getString(3),
                        race = this.rs.getString(4);
                System.out.print(String.format(pattern, fName,lName,gender,race));
            }
            System.out.println(lb);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void genCharacteristics(){
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL GENCHARACTERISTICS()}");
            this.cst.execute();
            String patern = "| %s = %s |\n" +
                            "| %s = %s |\n" +
                            "| %s = %s |\n" +
                            "| %s = %s |\n" +
                            "| %s = %s |\n" +
                            "| %s = %s |\n";
            this.rs = this.cst.getResultSet();
            if(this.rs.next()) {
                String trait = this.rs.getString(1),
                        ideal = this.rs.getString(2),
                        bond = this.rs.getString(3),
                        flaw = this.rs.getString(4),
                        appearance = this.rs.getString(5),
                        mannerism = this.rs.getString(6);
                System.out.print(String.format(patern,
                        "Trait",trait, "Ideal", ideal, "Bond", bond, "Flaw", flaw, "Appearance", appearance,"Mannerism", mannerism));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    // LOOT

    public void randomShop(){
        try{
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL RANDOMSHOP()}");
            this.cst.execute();

            String patternT;
            String patternD;
            String title;
            String lb;

            // WEAPONS
            this.rs = this.cst.getResultSet();

            patternT = "| %-20s | %-20s | %-8s | %9s | %-5s | %-8s | %-50s |";
            patternD = "| %-20s | %-20s | %8.1f | %-9d | %-5s | %-8s | %-50s |";
            title = String.format(patternT, "Name", "TYPE", "Weight (lb)", "Cost(cp)","Damage","Damage Type","Properties");
            lb = Entities.getLineBreak(title);

            System.out.println();
            System.out.println(lb);
            System.out.println("WEAPONS");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            this.rs = this.cst.getResultSet();

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                float weight = this.rs.getFloat(3);
                int cost = this.rs.getInt(4);
                String damage = this.rs.getString(5);
                String damage_type = this.rs.getString(6);
                String prop = this.rs.getString(7);
                System.out.println(String.format(patternD, n1, n2,weight,cost,damage,damage_type,prop));
            }

            System.out.println(lb);

            // ARMOR
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();

            patternT =   "| %-25s | %-20s | %8s | %-9s | %-2s | %-12s | %-20s | %-15s |";
            patternD = "| %-25s | %-20s | %8.1f | %-9d | %-2d | %-12d | %-20B | %-15d |";
            title = String.format(patternT, "Name", "TYPE", "Weight (lb)", "Cost(cp)","AC","AC Max Bonus","Stealth Disadvantage", "STR requirement");
            lb = Entities.getLineBreak(title);

            System.out.println();
            System.out.println(lb);
            System.out.println("ARMOR");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            this.rs = this.cst.getResultSet();

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                float weight = this.rs.getFloat(6);
                int cost = this.rs.getInt(3);
                int ac = this.rs.getInt(4);
                int acb = this.rs.getInt(5);
                boolean stdis = this.rs.getBoolean(6);
                int strreq = this.rs.getInt(7);
                System.out.println(String.format(patternD, n1, n2,weight,cost,ac,acb,stdis,strreq));
            }

            System.out.println(lb);

            // ADVENTURE GEAR
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();

            patternT = "| %-30s | %-20s | %-8s | %-9s |";
            patternD = "| %-30s | %-20s | %-8.1f | %-9d |";
            title = String.format(patternT, "Name", "Type", "Weight (lb)", "Cost(cp)");
            lb = Entities.getLineBreak(title);

            System.out.println();
            System.out.println(lb);
            System.out.println("ADVENTURE GEAR");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                float weight = this.rs.getFloat(3);
                int cost = this.rs.getInt(4);
                System.out.println(String.format(patternD, n1, n2,weight,cost));
            }

            System.out.println(lb);


            // MAGIC WEAPON
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();

            patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %-5s | %-8s | %-50s | %-50s |";
            patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %-5s | %-8s | %-50s | %-50s |";
            title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)","Damage","Damage Type","Properties", "Description");
            lb = Entities.getLineBreak(title);

            System.out.println();
            System.out.println(lb);
            System.out.println("MAGIC WEAPON");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            this.rs = this.cst.getResultSet();

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                String damage = this.rs.getString(7);
                String damage_type = this.rs.getString(8);
                String prop = this.rs.getString(9);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,damage,damage_type,prop,desc));
            }

            System.out.println(lb);

            // MAGIC ARMOR
            this.cst.getMoreResults();
            this.rs = this.cst.getResultSet();

            patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %2s | %2s | %5s | %2s | %-50s |";
            patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %2d | %2d | %5B | %2d | %-50s |";
            title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)", "Base AC",
                    "AC Max Bonus", "Stealth Disadvantage", "STR req", "Description");
            lb = Entities.getLineBreak(title);

            System.out.println();
            System.out.println(lb);
            System.out.println("MAGIC ARMOR");
            System.out.println(lb);
            System.out.println(title);
            System.out.println(lb);

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                int ac = this.rs.getInt(7);
                int acb = this.rs.getInt(8);
                boolean stealth = this.rs.getBoolean(9);
                int strreq = this.rs.getInt(10);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,ac,acb,stealth,strreq,desc));
            }
            System.out.println(lb);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void genAdventureGear(){
        System.out.print("How many pieces of magic gear to generate?: ");
        int amount = Integer.parseInt(UI.userIn.nextLine());
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL GENADVENTUREGEAR(?)}");
            this.cst.setInt(1,amount);
            this.cst.execute();

            this.rs = this.cst.getResultSet();

            String patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %-50s |";
            String patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %-50s |";
            String title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)", "Description");
            String lb = Entities.getLineBreak(title);

            System.out.println(title);
            System.out.println(lb);

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,desc));
            }

            System.out.println(lb);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void genWeapons(){
        System.out.print("How many pieces of magic weapons to generate?: ");
        int amount = Integer.parseInt(UI.userIn.nextLine());
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL GENWEAPONS(?)}");
            this.cst.setInt(1,amount);
            this.cst.execute();

            String patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %-5s | %-8s | %-50s | %-50s |";
            String patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %-5s | %-8s | %-50s | %-50s |";
            String title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)","Damage","Damage Type","Properties", "Description");
            String lb = Entities.getLineBreak(title);

            System.out.println(title);
            System.out.println(lb);

            this.rs = this.cst.getResultSet();

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                String damage = this.rs.getString(7);
                String damage_type = this.rs.getString(8);
                String prop = this.rs.getString(9);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,damage,damage_type,prop,desc));
            }

            System.out.println(lb);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void genArmors(){
        System.out.print("How many pieces of magic armor to generate?: ");
        int amount = Integer.parseInt(UI.userIn.nextLine());
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL GENARMORS(?)}");
            this.cst.setInt(1,amount);
            this.cst.execute();

            String patternT = "| %-50s | %-50s | %-8s | %5s | %8s | %2s | %2s | %5s | %2s | %-50s |";
            String patternD = "| %-50s | %-50s | %-8d | %5B | %8.1f | %2d | %2d | %5B | %2d | %-50s |";
            String title = String.format(patternT, "Name 1", "Name 2", "Cost(cp)", "Attunement?", "Weight (lb)", "Base AC",
                    "AC Max Bonus", "Stealth Disadvantage", "STR req", "Description");
            String lb = Entities.getLineBreak(title);

            System.out.println(title);
            System.out.println(lb);


            this.rs = this.cst.getResultSet();

            while(rs.next()) {
                String n1 = this.rs.getString(1);
                String n2 = this.rs.getString(2);
                int cost = this.rs.getInt(3);
                boolean attunement = this.rs.getBoolean(4);
                String desc = this.rs.getString(5);
                float weight = this.rs.getFloat(6);
                int ac = this.rs.getInt(7);
                int acb = this.rs.getInt(8);
                boolean stealth = this.rs.getBoolean(9);
                int strreq = this.rs.getInt(10);
                System.out.println(String.format(patternD, n1, n2,cost,attunement,weight,ac,acb,stealth,strreq,desc));
            }
            System.out.println(lb);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    // MONSTER LIST

    public void monsterList(String difficulty){
        System.out.print("\nHow many Players?: ");
        int numPlayers = Integer.parseInt(UI.userIn.nextLine());
        System.out.print("\nAverage Player Level?(Whole Number): ");
        int apl = Integer.parseInt(UI.userIn.nextLine());
        String q = "";
        try {
            this.con = this.getConnection();
            this.cst = this.con.prepareCall("{CALL MONSTERLIST(?, ?, ?)}");

            this.cst.setInt(1, numPlayers);
            this.cst.setInt(2, apl);
            this.cst.setString(3, difficulty);

            this.rs = this.cst.executeQuery();

            String patternT = "| %8s | %-30s | %-10s | %-9s | %-4s | %-2s | %-8s | %-11s | %-9s | %10s | %3s |";
            String patternD = "| %8.2f | %-30s | %-10s | %-9s | %-4d | %-2d | %-8d | %-11s | %-9B | %10s | %3d |";
            String title = String.format(patternT, "CR","Name","Size","Alignment","HP","AC","XP","Environment","Legendary","Book","Page");
            String lb = Entities.getLineBreak(title);

            System.out.println(title);
            System.out.println(lb);

            while(this.rs.next()){
                float cr = this.rs.getFloat(1);
                String name = this.rs.getString(2);
                String size = this.rs.getString(3);
                String alignment = this.rs.getString(4);
                int hp = this.rs.getInt(5);
                int ac = this.rs.getInt(6);
                int xp = this.rs.getInt(7);
                String env = this.rs.getString(8);
                boolean legendary = this.rs.getBoolean(9);
                String book = this.rs.getString(10);
                int page = this.rs.getInt(11);
                System.out.println(String.format(patternD, cr,name,size,alignment,hp,ac,xp,env,legendary,book,page));
            }
            System.out.println(lb);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void adventuringGear() {
        super.adventuringGear();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-30s | %-20s | %5s | %8.2s | %-10s | %5s |\n","GEAR_ID","NAME","TYPE","COST","WEIGHT","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                    this.rs.getInt("GEAR_ID"),
                    this.rs.getString("NAME"),
                    this.rs.getString("TYPE"),
                    this.rs.getInt("COST"),
                    this.rs.getFloat("WEIGHT"),
                    this.rs.getString("BOOK"),
                    this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void appearances() {
        super.appearances();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-60s | %-10s | %5s |\n","APPEARANCE_ID","APPEARANCE_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                    this.rs.getInt("APPEARANCE_ID"),
                    this.rs.getString("APPEARANCE_DESC"),
                    this.rs.getString("BOOK"),
                    this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void armors() {
        super.armors();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-25s | %-20s | %5s | %5s | %5s | %8.2s | %s | %5s | %-10s | %5s |\n","ARMOR_ID","NAME","TYPE","COST","BASE_AC","AC_MAX_BONUS","WEIGHT","STEALTH","STR_REQ","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                    this.rs.getInt("ARMOR_ID"),
                    this.rs.getString("NAME"),
                    this.rs.getString("TYPE"),
                    this.rs.getInt("COST"),
                    this.rs.getInt("BASE_AC"),
                    this.rs.getInt("AC_MAX_BONUS"),
                    this.rs.getFloat("WEIGHT"),
                    this.rs.getBoolean("STEALTH"),
                    this.rs.getInt("STR_REQ"),
                    this.rs.getString("BOOK"),
                    this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void backgrounds() {
        super.backgrounds();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-20s | %-50s | %-10s | %5s |\n","BACKGROUND_ID","BACKGROUND_NAME","BACKGROUND_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                    this.rs.getInt("BACKGROUND_ID"),
                    this.rs.getString("BACKGROUND_NAME"),
                    this.rs.getString("BACKGROUND_DESC"),
                    this.rs.getString("BOOK"),
                    this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void bonds() {
        super.bonds();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-50s | %-10s | %5s |\n","BOND_ID","BOND_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("BOND_ID"),
                        this.rs.getString("BOND_DESC"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void environmentalEffects() {
        super.environmentalEffects();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-20s | %-50s | %-11s | %-10s | %5s | %5s |\n","ENVIRON_EFFECT_ID","NAME","EFFECT_DESC","ENVIRONMENT","BOOK","PAGE","DIFFICULTY_ID"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("ENVIRON_EFFECT_ID"),
                        this.rs.getString("NAME"),
                        this.rs.getString("EFFECT_DESC"),
                        this.rs.getString("ENVIRONMENT"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE"),
                        this.rs.getInt("DIFFICULTY_ID")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void flaws() {
        super.flaws();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-50s | %-10s | %5s |\n","FLAW_ID","FLAW_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("FLAW_ID"),
                        this.rs.getString("FLAW_DESC"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void ideals() {
        super.ideals();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-50s | %-10s | %5s |\n","IDEAL_ID","IDEAL_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("IDEAL_ID"),
                        this.rs.getString("IDEAL_DESC"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void itemEffects() {
        super.itemEffects();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-20s | %-25s | %-1s | %s | %-50s | %-10s | %5s |\n","EFFECT_ID","F_NAME","L_NAME","TYPE","ATTUNEMENT_REQ","DESCRIPTION","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("EFFECT_ID"),
                        this.rs.getString("F_NAME"),
                        this.rs.getString("L_NAME"),
                        this.rs.getString("TYPE"),
                        this.rs.getBoolean("ATTUNEMENT_REQ"),
                        this.rs.getString("DESCRIPTION"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void mannerisms() {
        super.mannerisms();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-50s | %-10s | %5s |\n","MANNERISM_ID","MANNERISM_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("MANNERISM_ID"),
                        this.rs.getString("MANNERISM_DESC"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void monsters() {
        super.monsters();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-30s | %-10s | %-10s | %-2s | %5s | %5s | %5s | %-11s | %s | %-10s | %5s |\n","MONSTER_ID","NAME","SIZE","TYPE","ALIGNMENT","AC","HP","CR","ENVIRONMENT","IS_LEGENDARY","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("MONSTER_ID"),
                        this.rs.getString("NAME"),
                        this.rs.getString("SIZE"),
                        this.rs.getString("TYPE"),
                        this.rs.getString("ALIGNMENT"),
                        this.rs.getInt("AC"),
                        this.rs.getInt("HP"),
                        this.rs.getInt("CR"),
                        this.rs.getString("ENVIRONMENT"),
                        this.rs.getBoolean("IS_LEGENDARY"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void names() {
        super.names();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-20s | %-1s | %-10s | %-10s | %5s |\n","NAME_ID","NAME","TYPE","RACE","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("NAME_ID"),
                        this.rs.getString("NAME"),
                        this.rs.getString("TYPE"),
                        this.rs.getString("RACE"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void traits() {
        super.traits();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-50s | %-10s | %5s |\n","TRAIT_ID","TRAIT_DESC","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("TRAIT_ID"),
                        this.rs.getString("TRAIT_DESC"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void weapons() {
        super.weapons();
        this.getConnection();
        try {
            this.cst = this.con.prepareStatement(entity.readStatement);
            this.rs = this.cst.executeQuery();
            boolean flag = false;
            String title = String.format("| %5s | %-20s | %-20s | %5s | %-5s | %-8s | %8.2s | %-50s | %-10s | %5s |\n","WEAPON_ID","NAME","TYPE","COST","DAMAGE_DICE","DAMAGE_TYPE","WEIGHT","PROPERTIES","BOOK","PAGE"),
                lb = Entities.getLineBreak(title);
            System.out.print(title);
            System.out.println(lb);
            while(!flag && rs.next()){
                System.out.printf(entity.pattern,
                        this.rs.getInt("WEAPON_ID"),
                        this.rs.getString("NAME"),
                        this.rs.getString("TYPE"),
                        this.rs.getInt("COST"),
                        this.rs.getString("DAMAGE_DICE"),
                        this.rs.getString("DAMAGE_TYPE"),
                        this.rs.getFloat("WEIGHT"),
                        this.rs.getString("PROPERTIES"),
                        this.rs.getString("BOOK"),
                        this.rs.getInt("PAGE")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }


}
