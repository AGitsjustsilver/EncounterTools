package com.ui;

public class CmdMenuUi {
    private boolean mainUi;
    private String tagline;
    private String [] options;
    private int numChoices;
    private int choice;

    /**
     * Creates a 5 Length command line Menu UI
     * that is not the main menu
     */
    public CmdMenuUi(){
        this(false, "", new String[5]);
    }

    /**
     * Create a non main command line menu
     * @param tag - String Tagline
     * @param opt - String[] Entries to choose from
     */
    public CmdMenuUi(String tag, String[] opt){
        this(false, tag, opt);
    }

    /**
     * Creates a command line menu
     * @param main - boolean true if its a main, false if non main
     * @param tag - String Tagline
     * @param opt - String[] Entries to choose from
     */
    public CmdMenuUi(boolean main, String tag, String[] opt){
        this.mainUi = main;
        this.tagline = tag;
//        this.choice = opt.length; // initially the last choice for looping purposes
        this.numChoices = opt.length+1;
        this.options = new String[numChoices];
        for (int i = 0; i < opt.length; i++) {
            this.options[i] = (i+1) + ": " + opt[i] + "\n";
        }
        this.options[this.numChoices-1] = (this.numChoices) + ": " + ((this.mainUi)? "Exit" : "Return") + "\n";
    }

    public int getIntChoice(){
        return this.choice;
    }

    public boolean makeChoice(){
        this.menu();
        System.out.print("Enter the number you need: ");
        this.choice = Integer.parseInt(UI.userIn.nextLine());
        while (!validate(this.choice)){
            System.out.print("Invalid Choice\nEnter the number you need: ");
            this.choice = Integer.parseInt(UI.userIn.nextLine());
        }
        return this.choice != this.numChoices;
    }

    private boolean validate(int c){
        return (c > 0 && c < this.numChoices+1);
    }

    public void menu() {
        StringBuilder result = new StringBuilder();
        result.append(tagline).append('\n');
        for (String o :
                this.options){
            result.append(o);
        }
        System.out.println(result.toString());
    }
}
