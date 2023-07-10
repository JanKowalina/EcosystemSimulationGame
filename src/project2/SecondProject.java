package project2;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SecondProject {

    
    public static void main(String[] args) {
        
        Comentator comentator = new Comentator();
        World world = new World(comentator);
        

        world.DrawWorld();
        while (world.MakeTurn()) {
            world.DrawWorld();
        }
        
        System.exit(0);
    }
}