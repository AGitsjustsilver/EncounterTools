package com.ui;

import java.awt.*;
import java.io.Console;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "EncounterTools-UI.jar";
        String[] wind = new String[]{"cmd", "/c","start","cmd", "/k", "java -jar \""+ fileName + "\""};
        String[] mac = new String[]{"/bin/bash", "-c", "java", "-jar", fileName };
        Console console = System.console();
        if (console == null && !GraphicsEnvironment.isHeadless()){
            Runtime.getRuntime().exec((System.getProperty("os.name").contains("Windows"))? wind : mac);
        }else {
            UI ui = new UI();
            System.out.println("\nProgram has exited.\nType 'exit' to close the console.");
        }
        System.out.println(System.getProperty("os.name").contains("Windows"));
    }

}
