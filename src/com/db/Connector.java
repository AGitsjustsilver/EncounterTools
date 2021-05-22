package com.db;

import com.db.actions.EntityActions;

import java.sql.*;

public class Connector extends EntityActions {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/encountertools";
    private String user;
    private String pass;

    protected Connection con;
    protected PreparedStatement cst;
    protected ResultSet rs;

    public Connector(){
        this.user = null;
        this.pass = null;
        this.con = null;
        this.cst = null;
        this.rs = null;
    }

    public Connector(String username, String password){
        this();
        this.logIn(username, password);
    }

    public Connector(Connector c){
        this(c.user, c.pass);
    }

    public boolean logIn(String username, String password){
        this.user = username;
        this.pass = password;
        this.con = this.getConnection();
        if (this.con != null) {
            return true;
        }
        return false;
    }

    public void logOut(){
        this.user = null;
        this.pass = null;
        try {
            if(this.con != null)
                this.con.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    protected Connection getConnection(){
        try{
            if(this.con == null || this.con.isClosed()) {
                this.con = DriverManager.getConnection(DB_URL,this.user,this.pass);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            this.logOut();
        }
        return this.con;
    }


}
