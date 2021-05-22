package com.db;

public enum Entities {

    ADVENTURING_GEAR(
  "ADVENTURING_GEAR",
    2,
       "| %7d | %-30s | %-20s | %5d | %8.2f | %-10s | %5d |\n",
    "GEAR_ID","NAME","TYPE","COST","WEIGHT","BOOK","PAGE"
    ),
    APPEARANCES(
  "APPEARANCES",
    1,
       "| %13d | %-60s | %-10s | %5d |\n",
    "APPEARANCE_ID","APPEARANCE_DESC","BOOK","PAGE"
    ),
    ARMORS(
  "ARMORS",
    2,
       "| %8d | %-25s | %-20s | %5d | %5d | %5d | %8.2f | %B | %5d | %-10s | %5d |\n",
    "ARMOR_ID","NAME","TYPE","COST","BASE_AC","AC_MAX_BONUS","WEIGHT","STEALTH","STR_REQ","BOOK","PAGE"
    ),
    BACKGROUNDS(
  "BACKGROUNDS",
    1,
       "| %13d | %-20s | %-50s | %-10s | %5d |\n",
    "BACKGROUND_ID","BACKGROUND_NAME","BACKGROUND_DESC","BOOK","PAGE"
    ),
    BONDS(
  "BONDS",
    1,
       "| %7d | %-50s | %-10s | %5d |\n",
    "BOND_ID","BOND_DESC","BOOK","PAGE"
    ),
    ENVIRONMENTAL_EFFECTS(
  "ENVIRONMENTAL_EFFECTS",
    1,
       "| %17d | %-20s | %-50s | %-11s | %-10s | %5d | %5d |\n",
    "ENVIRON_EFFECT_ID","NAME","EFFECT_DESC","ENVIRONMENT","BOOK","PAGE","DIFFICULTY_ID"
    ),
    FLAWS(
  "FLAWS",
    1,
       "| %7d | %-50s | %-10s | %5d |\n",
    "FLAW_ID","FLAW_DESC","BOOK","PAGE"
    ),
    IDEALS(
  "IDEALS",
    1,
       "| %8d | %-50s | %-10s | %5d |\n",
    "IDEAL_ID","IDEAL_DESC","BOOK","PAGE"
    ),
    ITEM_EFFECTS(
  "ITEM_EFFECTS",
    1,
       "| %9d | %-20s | %-25s | %-1s | %B | %-50s | %-10s | %5d |\n",
    "EFFECT_ID","F_NAME","L_NAME","TYPE","ATTUNEMENT_REQ","DESCRIPTION","BOOK","PAGE"
    ),
    MANNERISMS(
  "MANNERISMS",
    1,
       "| %12d | %-50s | %-10s | %5d |\n",
    "MANNERISM_ID","MANNERISM_DESC","BOOK","PAGE"
    ),
    MONSTERS(
  "MONSTERS",
    1,
       "| %10d | %-30s | %-10s | %-10s | %-2s | %5d | %5d | %5d | %-11s | %B | %-10s | %5d |\n",
    "MONSTER_ID","NAME","SIZE","TYPE","ALIGNMENT","AC","HP","CR","ENVIRONMENT","IS_LEGENDARY","BOOK","PAGE"
    ),
    NAMES(
  "NAMES",
    1,
       "| %7d | %-20s | %-1s | %-10s | %-10s | %5d |\n",
    "NAME_ID","NAME","TYPE","RACE","BOOK","PAGE"
    ),
    TRAITS(
  "TRAITS",
    1,
       "| %8d | %-50s | %-10s | %5d |\n",
    "TRAIT_ID","TRAIT_DESC","BOOK","PAGE"
    ),
    WEAPONS(
  "WEAPONS",
    2,
       "| %9d | %-20s | %-20s | %5d | %-5s | %-8s | %8.2f | %-50s | %-10s | %5d |\n",
    "WEAPON_ID","NAME","TYPE","COST","DAMAGE_DICE","DAMAGE_TYPE","WEIGHT","PROPERTIES","BOOK","PAGE"
    );

    // Utilities
    private StringBuilder sb;

    // Fields
    public String dbTableName;
    private final int numberOfPrimaryKeys;
    public String[] entityFields;

    // Outputs
    public String pattern;
    public String createStatement;
    public String readStatement;
    public String updateStatement;
    public String deleteStatement;

    Entities(String tableName, int numpk, String pat, String ... fields){
        this.dbTableName = tableName;
        this.numberOfPrimaryKeys = numpk;
        this.entityFields = fields;
        this.pattern = pat;
        this.createStatement = createStatement();
        this.readStatement = readStatement();
        this.updateStatement = updateStatement();
        this.deleteStatement = deleteStatement();
    }

    private String createStatement(){
        sb = new StringBuilder();
        // "INSERT INTO TABLE (FIELDS,...) VALUE (?,...)"
        //  INSERT INTO TABLE (
        sb.append("INSERT INTO ").append(this.dbTableName).append(" (");
        // FIELDS,...)
        for (int i = 1; i < this.entityFields.length-1; i++) {
            sb.append(this.entityFields[i]).append(',');
        }
        sb.append(this.entityFields[this.entityFields.length-1]);
        sb.append(')');
        // VALUE (
        sb.append("VALUE (");
        // ?,...)
        for (int i = 1; i < this.entityFields.length-1; i++) {
            sb.append("?, ");
        }
        sb.append("?)");
        return sb.toString();
    }
    private String readStatement(){
        sb = new StringBuilder();
        // "SELECT FIELD,... FROM TABLE"
        sb.append("SELECT * FROM ").append(dbTableName);
        return sb.toString();
    }
    private String updateStatement(){
        sb = new StringBuilder();
        // "UPDATE TABLE SET FIELD = ?,... WHERE PK = ?[AND PK2 = ?]"
        sb.append("UPDATE ");
        sb.append(dbTableName);
        sb.append(" SET ");
        String eq = " = ?";
        if(this.numberOfPrimaryKeys > 1){
            sb.append(this.entityFields[1]).append(eq).append(',');
            for (int i = 3; i < this.entityFields.length-1; i++) {
                sb.append(this.entityFields[i]).append(eq).append(", ");
            }
        }else{
            for (int i = 1; i < this.entityFields.length-1; i++) {
                sb.append(this.entityFields[i]).append(eq).append(", ");
            }
        }
        sb.append(this.entityFields[this.entityFields.length-1]).append(eq);
        sb.append(" WHERE ");
        sb.append(entityFields[0]).append(eq);
        if (this.numberOfPrimaryKeys > 1) {
            sb.append(" AND ");
            sb.append(entityFields[2]).append(eq);
        }
        return sb.toString();
    }
    private String deleteStatement(){
        sb = new StringBuilder();
        // "DELETE FROM TABLE WHERE PK = ?[AND PK2 = ?]"
        sb.append("DELETE FROM ");
        sb.append(dbTableName);
        // WHERE PK = ?[AND PK2 = ?]"
        String eq = " = ?";
        sb.append(" WHERE ");
        sb.append(entityFields[0]).append(eq);
        if (this.numberOfPrimaryKeys > 1) {
            sb.append(" AND ");
            sb.append(entityFields[2]).append(eq);
        }
        return sb.toString();
    }

    public static String getLineBreak(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append('=');
        }
        return sb.toString();
    }

}
