USE ENCOUNTERTOOLS;

-- region DROPS
DROP TRIGGER IF EXISTS AWARDXP;
DROP PROCEDURE IF EXISTS MONSTERSINTHRESH;
DROP FUNCTION IF EXISTS CALCTHRESH;
DROP PROCEDURE IF EXISTS GENENCOUNTER;
DROP PROCEDURE IF EXISTS MONSTERLIST;
DROP PROCEDURE IF EXISTS GENCHARACTERISTICS;
DROP PROCEDURE IF EXISTS GENCHARACTER;
DROP PROCEDURE IF EXISTS NAMELIST;
DROP PROCEDURE IF EXISTS RANDOMSHOP;
DROP PROCEDURE IF EXISTS GENADVENTUREGEAR;
DROP PROCEDURE IF EXISTS GENARMORS;
DROP PROCEDURE IF EXISTS GENWEAPONS;
-- endregion

-- region VIEWS

/**
    VIEW: FULL_NAMES
    RATIONAL: HAVING A FULL NAME WAS PRETTY USEFUL WHEN CREATING OTHER
    DATABASE OBJECTS
 */
CREATE OR REPLACE VIEW FULL_NAMES AS
SELECT
   FNAMES.NAME_ID AS 'F_NAME_ID',
   LNAMES.NAME_ID AS 'L_NAME_ID'
FROM (SELECT NAME_ID, RACE FROM NAMES WHERE TYPE != 'L') FNAMES
JOIN (SELECT NAME_ID, RACE FROM NAMES WHERE TYPE = 'L') LNAMES
WHERE LNAMES.RACE = FNAMES.RACE;

/**
    VIEW: CHARACTERISTICS
    RATIONAL: CHARACTERISTICS ARE THE FLESH TO THE BONES OF AND NPC.
    WITHOUT THEM, NPCS WOULD FEEL EMPTY.
    THIS VIEW REPRESENTS THE COMBINATION OF MULTIPLE ENTITIES
    STORED IN A VIEW TO PRESERVE STORAGE.
 */
CREATE OR REPLACE VIEW CHARACTERISTICS AS
SELECT
   TRAITS.TRAIT_ID,
   IDEALS.IDEAL_ID,
   BONDS.BOND_ID,
   FLAWS.FLAW_ID,
   APPEARANCES.APPEARANCE_ID,
   MANNERISMS.MANNERISM_ID
FROM TRAITS
JOIN IDEALS
JOIN BONDS
JOIN FLAWS
JOIN APPEARANCES
JOIN MANNERISMS;

/**
    VIEW: NPCS
    RATIONAL: NPCS IS THE BAREBONES INFORMATION FOR AN NPC.
    THEY HAVE ENOUGH INFORMATION TO LET THE DM NOT PANIC WHEN ASKED
    FOR A RANDOM NPC.
    SIMILAR IDEA WITH CHARACTERISTICS, WOULD BE HUGE IF STORED.
 */
CREATE OR REPLACE VIEW NPCS AS
SELECT
   F_NAME_ID,
   L_NAME_ID,
   ALIGNMENTS.ALIGNMENT_ID,
   BACKGROUNDS.BACKGROUND_ID
FROM BACKGROUNDS
JOIN FULL_NAMES
JOIN ALIGNMENTS;

/**
    VIEW: MAGIC_GEAR
    RATIONAL: THESE ARE MAGICAL TRINKETS THE MORE EFFECTS ARE ADDED
    THE MORE THIS 'TABLE' GROWS
 */
CREATE OR REPLACE VIEW MAGIC_GEAR AS
SELECT
    GEAR_ID,
    EFFECT_ID,
    CONCAT_WS(' ',F_NAME, NAME) AS 'NAME1',
    CONCAT_WS(' ',NAME, L_NAME) AS 'NAME2',
    COST * 25 AS 'COST',
    ATTUNEMENT_REQ,
    DESCRIPTION,
    WEIGHT
FROM ADVENTURING_GEAR
JOIN ITEM_EFFECTS;

/**
    VIEW: MAGIC_ARMORS
    RATIONAL: SIMILAR TO MAGIC_GEAR. ORIGINALLY I THOUGHT
 */
CREATE OR REPLACE VIEW MAGIC_ARMORS AS
SELECT
    ARMOR_ID,
    ARMOR_EFFECTS.EFFECT_ID,
    CONCAT_WS(' ',F_NAME, NAME) AS 'NAME1',
    CONCAT_WS(' ',NAME, L_NAME) AS 'NAME2',
    COST * 50 AS 'COST',
    ATTUNEMENT_REQ,
    DESCRIPTION,
    WEIGHT,
    BASE_AC,
    AC_MAX_BONUS,
    STEALTH,
    STR_REQ
FROM ARMORS
JOIN (SELECT * FROM ITEM_EFFECTS WHERE TYPE IN ('A', 'B')) ARMOR_EFFECTS;

/**
    VIEW: MAGIC_WEAPONS
    RATIONAL: SIMILAR TO MAGIC_GEAR AND MAGIC_ARMORS.
 */
CREATE OR REPLACE VIEW MAGIC_WEAPONS AS
SELECT
    WEAPON_ID,
    WEAPON_EFFECTS.EFFECT_ID,
    CONCAT_WS(' ',F_NAME, NAME) AS 'NAME1',
    CONCAT_WS(' ',NAME, L_NAME) AS 'NAME2',
    COST * 75 AS 'COST',
    ATTUNEMENT_REQ,
    DESCRIPTION,
    WEIGHT,
    DAMAGE_DICE,
    DAMAGE_TYPE,
    PROPERTIES
FROM WEAPONS
JOIN (SELECT * FROM ITEM_EFFECTS WHERE TYPE IN ('W', 'B')) WEAPON_EFFECTS;



-- endregion

-- region TRIGGERS

/**
    TRIGGER: AWARDXP
    RATIONAL: THERE WAS ONE PIECE MISSING TO THE MONSTERS AN THAT WAS
    XP. ITS IMPORTANT WHEN IT COMES TO CALCULATING HOW HARD AN ENCOUNTER IS.
    ALSO PLAYERS LIKE TO KNOW HOW MUCH XP THEY ARE GETTING TO THEIR NEXT
    LEVEL UP.
 */
DELIMITER //
CREATE TRIGGER AWARDXP
BEFORE INSERT ON MONSTERS
FOR EACH ROW
BEGIN
    CASE
        WHEN NEW.CR = 0 THEN SET NEW.XP = 10;
        WHEN NEW.CR = 0.13 THEN SET NEW.XP = 25;
        WHEN NEW.CR = 0.25 THEN SET NEW.XP = 50;
        WHEN NEW.CR = 0.5 THEN SET NEW.XP = 100;
        WHEN NEW.CR = 1 THEN SET NEW.XP = 200;
        WHEN NEW.CR = 2 THEN SET NEW.XP = 450;
        WHEN NEW.CR = 3 THEN SET NEW.XP = 700;
        WHEN NEW.CR = 4 THEN SET NEW.XP = 1100;
        WHEN NEW.CR = 5 THEN SET NEW.XP = 1800;
        WHEN NEW.CR = 6 THEN SET NEW.XP = 2300;
        WHEN NEW.CR = 7 THEN SET NEW.XP = 2900;
        WHEN NEW.CR = 8 THEN SET NEW.XP = 3900;
        WHEN NEW.CR = 9 THEN SET NEW.XP = 5000;
        WHEN NEW.CR = 10 THEN SET NEW.XP = 5900;
        WHEN NEW.CR = 11 THEN SET NEW.XP = 7200;
        WHEN NEW.CR = 12 THEN SET NEW.XP = 8400;
        WHEN NEW.CR = 13 THEN SET NEW.XP = 10000;
        WHEN NEW.CR = 14 THEN SET NEW.XP = 11500;
        WHEN NEW.CR = 15 THEN SET NEW.XP = 13000;
        WHEN NEW.CR = 16 THEN SET NEW.XP = 15000;
        WHEN NEW.CR = 17 THEN SET NEW.XP = 18000;
        WHEN NEW.CR = 18 THEN SET NEW.XP = 20000;
        WHEN NEW.CR = 19 THEN SET NEW.XP = 22000;
        WHEN NEW.CR = 20 THEN SET NEW.XP = 25000;
        WHEN NEW.CR = 21 THEN SET NEW.XP = 33000;
        WHEN NEW.CR = 22 THEN SET NEW.XP = 41000;
        WHEN NEW.CR = 23 THEN SET NEW.XP = 50000;
        WHEN NEW.CR = 24 THEN SET NEW.XP = 62000;
        WHEN NEW.CR = 30 THEN SET NEW.XP = 155000;
        ELSE SET NEW.XP = 0;
    END CASE;
END //

-- endregion

-- region PROCEDURES

/**
    PROCEDURE: MONSTERSINTHRESH
    RATIONAL: helpful for creating an encounter where you need monsters within the xp
    threshold and a specific environment
 */
CREATE PROCEDURE  MONSTERSINTHRESH(IN THRESH INT, IN ENV VARCHAR(11))
BEGIN
    SELECT CR,NAME, SIZE, AC, HP, ALIGNMENT, IS_LEGENDARY, XP
    FROM monsters WHERE (XP  <= THRESH AND XP>0) AND ENVIRONMENT = ENV;
END //

/**
    FUNCTION: CALCTHRESH
    RATIONAL: There is a certain amount of xp a DM has to calculate for an encounter
    The recommended amount of xp an encounter should be  based the number of players
    and the player level (normally its an average but keeping simple due to the fact
    that normally the party are all the same level)
 */
CREATE FUNCTION CALCTHRESH ( NUMPATRY INT, APL INT, DIF VARCHAR(6)) RETURNS INT
deterministic READS SQL DATA
begin
    set @amount = (SELECT IF(DIF='Easy', EASY_XP,IF(DIF = 'Medium', MEDIUM_XP,IF(DIF = 'Hard', HARD_XP,DEADLY_XP))) FROM XP_THRESHOLDS WHERE LEVEL = APL);
    RETURN (NUMPATRY*@amount);
end //

/**
  PROCEDURE: GENADVENTUREGEAR
  RATIONAL: Generates magical adventuring gear taking advantage of
  magic gear view
 */
CREATE PROCEDURE GENADVENTUREGEAR(IN AMOUNT INT)
BEGIN
    SELECT
        NAME1,
        NAME2,
        COST,
        ATTUNEMENT_REQ,
        DESCRIPTION,
        WEIGHT
    FROM MAGIC_GEAR
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(GEAR_ID) FROM ADVENTURING_GEAR)) AS ID) AS N1
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(EFFECT_ID) FROM ITEM_EFFECTS)) AS ID) AS N2
    WHERE GEAR_ID >= N1.ID AND EFFECT_ID >= N2.ID ORDER BY RAND() LIMIT AMOUNT;
END //

/**
  PROCEDURE: GENARMORS
  RATIONAL: similar to GENADVENTUREGEAR and GENWEAPONS
 */
CREATE PROCEDURE GENARMORS(IN AMOUNT INT)
BEGIN
    SELECT
        NAME1,
        NAME2,
        COST,
        ATTUNEMENT_REQ,
        DESCRIPTION,
        WEIGHT,
        BASE_AC,
        AC_MAX_BONUS,
        STEALTH,
        STR_REQ
    FROM MAGIC_ARMORS
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(ARMOR_ID) FROM ARMORS)) AS ID) AS N1
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(EFFECT_ID) FROM (SELECT EFFECT_ID FROM ITEM_EFFECTS WHERE TYPE IN ('A', 'B'))E)) AS ID) AS N2
    WHERE ARMOR_ID >= N1.ID AND EFFECT_ID >= N2.ID ORDER BY RAND() LIMIT AMOUNT;
END //

/**
  PROCEDURE: GENWEAPONS
  RATIONAL: similar to GENADVENTUREGEAR and GENARMORS
 */
CREATE PROCEDURE GENWEAPONS(IN AMOUNT INT)
BEGIN
    SELECT
         NAME1,
         NAME2,
         COST,
         ATTUNEMENT_REQ,
         DESCRIPTION,
         WEIGHT,
         DAMAGE_DICE,
         DAMAGE_TYPE,
         PROPERTIES
    FROM MAGIC_WEAPONS
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(WEAPON_ID) FROM WEAPONS)) AS ID) AS N1
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(EFFECT_ID) FROM (SELECT EFFECT_ID FROM ITEM_EFFECTS WHERE TYPE IN ('W', 'B'))E)) AS ID) AS N2
    WHERE WEAPON_ID >= N1.ID AND EFFECT_ID >= N2.ID ORDER BY RAND() LIMIT AMOUNT;
END //

/**
    PROCEDURE: GENENCOUNTER
    RATIONAL: the quintessential piece of the tool. Creates and encounter based on
    a difficulty, number of party members, and the int average party level.
    Create means:
        - Monsters
            - Are within an experience threshold calculated for difficulty
        - Loot
            - items that are magical in nature
        - Environmental Effects
    Always chooses a random Environment.
 */
CREATE PROCEDURE GENENCOUNTER(IN DIF VARCHAR(6), IN NUMPARTY INT, IN APL INT)
BEGIN
    DECLARE THRESHOLD INT;
    DECLARE d SMALLINT;
    DECLARE numEEffects SMALLINT;
    DECLARE env VARCHAR(11);
    DECLARE numgear TINYINT;
    DECLARE numarmor TINYINT;
    DECLARE numweap TINYINT;

    SET d = 0;
    SET numEEffects = 0;
    CASE
        WHEN DIF = 'Easy' THEN
            SET d = 1, numEEffects = 1, numgear = 2,numarmor = 1, numweap = 1;
        WHEN DIF = 'Medium' THEN
            SET d = 1, numEEffects = 2, numgear = 4,numarmor = 2, numweap = 2;
        WHEN DIF = 'Hard' THEN
            SET d = 2, numEEffects = 3, numgear = 6,numarmor = 3, numweap = 3;
        WHEN DIF = 'Deadly' THEN
            SET d = 3, numEEffects = 4, numgear = 8,numarmor = 6, numweap = 6;
        ELSE
            SET d = 1, numEEffects = 1, numgear = numarmor = numweap =0;
    END CASE;
    # select a random environment
    SET env = (select distinct ENVIRONMENT from environmental_effects order by rand() limit 1);

    # monsters
    SET THRESHOLD = CALCTHRESH(NUMPARTY,APL,DIF);
    CALL MONSTERSINTHRESH(THRESHOLD, env);

    # loot
    CALL GENADVENTUREGEAR(numgear);
    CALL GENARMORS(numarmor);
    CALL GENWEAPONS(numweap);

    # environment effects difficulty
    SELECT
        NAME,EFFECT_DESC,ENVIRONMENT,DANGER,MIN_SAVE,MAX_SAVE,MIN_ATTACK_BONUS,MAX_ATTACK_BONUS,
        IF(APL<=4, DIF_FROM_1_4, IF(APL<=10,DIF_FROM_5_10,IF(APL <= 16,DIF_FROM_11_16,DIF_FROM_17_20)))
        AS 'SUGGESTED DAMAGE DICE'
    FROM ENVIRONMENTAL_EFFECTS
    JOIN DIFFICULTY USING(DIFFICULTY_ID)
    WHERE DIFFICULTY_ID = d and ENVIRONMENT = env
    ORDER BY RAND()
    LIMIT numEEffects;
END //

/**
    PROCEDURE: MONSTERLIST
    RATIONAL: a set of 25 monsters that a party could fight given a difficulty
    regardless of the environment
 */
CREATE PROCEDURE MONSTERLIST(IN NUMPARTY INT, IN APL INT, IN DIF VARCHAR(6))
BEGIN
    set @thresh = CALCTHRESH(NUMPARTY,APL,DIF);
    SELECT CR, NAME, SIZE, ALIGNMENT, HP, AC, XP, ENVIRONMENT, IS_LEGENDARY, BOOK, PAGE
    FROM MONSTERS WHERE XP <= @thresh order by rand() limit 25;
END//

/**
    PROCEDURE: GENCHARACTERISTICS
    RATIONAL: CHARACTERISTICS IS ONE PART OF THE NPC'S THAT GIVE THEM MORE LIFE
    THIS PROCEDURE IS USED TO RETRIEVE THE INFORMATION FROM THE VIEW AND SHOWN TO THE
    DM THOUGH THE UI.
 */
CREATE PROCEDURE GENCHARACTERISTICS()
BEGIN
    SELECT
        TRAIT_DESC AS 'TRAIT',
        IDEAL_DESC AS 'IDEAL',
        BOND_DESC AS 'BOND',
        FLAW_DESC AS 'FLAW',
        APPEARANCE_DESC AS 'APPEARANCE',
        MANNERISM_DESC AS 'MANNERISM'
    FROM CHARACTERISTICS
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(TRAIT_ID) FROM TRAITS))AS ID) RANDOM_TRAIT
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(IDEAL_ID) FROM IDEALS))AS ID) RANDOM_IDEAL
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(BOND_ID) FROM BONDS))AS ID) RANDOM_BOND
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(FLAW_ID) FROM FLAWS))AS ID) RANDOM_FLAW
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(APPEARANCE_ID) FROM APPEARANCES))AS ID) RANDOM_APPEARANCE
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(MANNERISM_ID) FROM MANNERISMS))AS ID) RANDOM_MANNERISM
    JOIN TRAITS USING(TRAIT_ID)
    JOIN IDEALS USING(IDEAL_ID)
    JOIN BONDS USING(BOND_ID)
    JOIN FLAWS USING(FLAW_ID)
    JOIN APPEARANCES USING(APPEARANCE_ID)
    JOIN MANNERISMS USING(MANNERISM_ID)
    WHERE
        TRAIT_ID >= RANDOM_TRAIT.ID AND
        IDEAL_ID >= RANDOM_IDEAL.ID AND
        BOND_ID >= RANDOM_BOND.ID AND
        FLAW_ID >= RANDOM_FLAW.ID AND
        APPEARANCE_ID >= RANDOM_APPEARANCE.ID AND
        MANNERISM_ID >= RANDOM_MANNERISM.ID
    LIMIT 1;
END //


/**
    PROCEDURE: GENCHARACTER
    RATIONAL: SOMETIMES A DM NEEDS A RANDOM CHARACTER, OR A PLAYER CHARACTER DIES.
    THIS PROVIDES USERS A RANDOM CHARACTER WITH CHARACTERISTICS SO THAT USERS CAN
    GET AN IDEA OF WHAT CHARACTER TO MAKE NEXT.
 */
CREATE PROCEDURE GENCHARACTER()
BEGIN
    SELECT
           CONCAT_WS(' ', F.NAME, L.NAME) AS 'NAME',
           F.TYPE AS 'GENDER',
           L.RACE,
           ALIGNMENT,
           BACKGROUND_NAME,
           BACKGROUND_DESC
    FROM NPCS
    JOIN NAMES F ON F.NAME_ID = F_NAME_ID
    JOIN NAMES L ON L.NAME_ID = L_NAME_ID
    JOIN ALIGNMENTS USING(ALIGNMENT_ID)
    JOIN BACKGROUNDS USING(BACKGROUND_ID)
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(NAME_ID) FROM NAMES WHERE TYPE != 'L')) AS ID) RANDOM_NAME_F
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(NAME_ID) FROM NAMES WHERE TYPE = 'L')) AS ID) RANDOM_NAME_L
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(ALIGNMENT_ID) FROM ALIGNMENTS)) AS ID) RANDOM_ALIGNMENT
    JOIN (SELECT CEIL(RAND() * (SELECT MAX(BACKGROUND_ID) FROM BACKGROUNDS)) AS ID) RANDOM_BACKGROUND
    WHERE
        F_NAME_ID >= RANDOM_NAME_F.ID AND
        L_NAME_ID >= RANDOM_NAME_L.ID AND
        ALIGNMENT_ID >= RANDOM_ALIGNMENT.ID AND
        BACKGROUND_ID >= RANDOM_BACKGROUND.ID
    LIMIT 1;
    CALL GENCHARACTERISTICS();
END //


/**
    PROCEDURE: NAMELIST
    RATIONAL: AN ITEM OF IMPORTANCE TO DUNGEON MASTERS EVERYWHERE
    HAVING A NAME FOR ANY RANDOMLY MENTIONED NPC. PLAYERS WILL
    ALWAYS ASK FOR SOME RANDOM BACKGROUND CHARACTER'S NAME REGARDLESS
    OR THE IMPORTANCE.
 */
CREATE PROCEDURE NAMELIST()
BEGIN
    SELECT
           F.NAME AS 'FIRST NAME',
           L.NAME AS 'LAST NAME',
           F.TYPE AS 'GENDER',
           F.RACE
    FROM FULL_NAMES
    JOIN NAMES F ON F.NAME_ID = F_NAME_ID
    JOIN NAMES L ON L.NAME_ID = L_NAME_ID
    ORDER BY RAND()
    LIMIT 10;
END //

/**
  PROCEDURE: RANDOMSHOP
  RATIONAL: Generates a random shop inventory
 */
CREATE PROCEDURE RANDOMSHOP()
BEGIN
    # RANDOMLY GETS 4 WEAPONS
    SELECT NAME, TYPE, WEIGHT, COST, DAMAGE_DICE, DAMAGE_TYPE, PROPERTIES
    FROM WEAPONS ORDER BY RAND() LIMIT 4;

    # RANDOMLY GETS 2 ARMORS
    SELECT NAME, TYPE, WEIGHT, COST, BASE_AC, AC_MAX_BONUS, STEALTH, STR_REQ
    FROM ARMORS ORDER BY RAND() LIMIT 2;

    # RANDOMLY GETS 8 ADVENTURE GEAR
    SELECT NAME, TYPE, WEIGHT, COST
    FROM ADVENTURING_GEAR ORDER BY RAND() LIMIT 8;

    # GETS 1 RANDOM MAGICAL WEAPON
    CALL GENWEAPONS(1);
    # GETS 1 RANDOM MAGICAL ARMOR
    CALL GENARMORS(1);
END//

DELIMITER ;

-- endregion