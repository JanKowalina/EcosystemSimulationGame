package project2.animals;

import static java.lang.Thread.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import project2.Animal;
import project2.World;


public class Human extends Animal {
    
public Human(int x, int y, World world, boolean alive) {
    super(x, y, world, 'H', 4, alive, 5);
    fiveCounter = -5;
}



public void Action()
{
	System.out.println("Choose one from available options:");
	System.out.println("W, S, A, D keys - Human movement");
	System.out.println("N - new game, Q - quit, X - power up, E - export state of game");
    System.out.println("Z - to crate new Organism on the map\n");
	int whereToMove = -2, counter = 0;

	fiveCounter--;
	if (fiveCounter >= 0)
            strength--;

    world.SetKey('0');
	while (true) {
        try {
            sleep(40);
        } catch (InterruptedException ex) {
            System.out.println("Thread is interrupted");
        }
           
		if (world.GetKey() != '0') {
			switch (world.GetKey()) {
			case 'w': case 'W':
				if (y - 1 >= 0) {
					world.GetMap()[y][x] = null;
					y--;
					if (world.GetMap()[y][x] == null) {
						world.GetMap()[y][x] = this;
						whereToMove = -1;
					}
					else
						whereToMove = 0;
				}
				else {
					if (counter == 0) {
						counter++;
						System.out.println("\n");
					}
					System.out.println("You cannot cross the border. Try again.");
				}
				break;
			case 'd': case 'D':
				if (x + 1 < world.GetMapX()) {
					world.GetMap()[y][x] = null;
					x++;
					if (world.GetMap()[y][x] == null) {
						world.GetMap()[y][x] = this;
						whereToMove = -1;
					}
					else
						whereToMove = 1;
				}
				else {
					if (counter == 0) {
						counter++;
						System.out.println("\n");
					}
					System.out.println("You cannot cross the border. Try again.");
				}
				break;
			case 's': case 'S':
				if (y + 1 < world.GetMapY()) {
					world.GetMap()[y][x] = null;
					y++;
					if (world.GetMap()[y][x] == null) {
						world.GetMap()[y][x] = this;
						whereToMove = -1;
					}
					else
						whereToMove = 2;
				}
				else {
					if (counter == 0) {
						counter++;
						System.out.println("\n");
					}
					System.out.println("You cannot cross the border. Try again.");
				}
				break;
			case 'a': case 'A':
				if (x - 1 >= 0) {
					world.GetMap()[y][x] = null;
					x--;
					if (world.GetMap()[y][x] == null) {
						world.GetMap()[y][x] = this;
						whereToMove = -1;
					}
					else
						whereToMove = 3;
				}
				else {
					if (counter == 0) {
						counter++;
						System.out.println("\n");
					}
					System.out.println("You cannot cross the border. Try again.\n");
				}
				break;
			case 'q': case 'Q':
				world.SetCommand(0);
				whereToMove = -1;
				break;
			case 'n': case 'N':
				world.SetCommand(1);
				whereToMove = -1;
				break;
			case 'x': case 'X':
				if (fiveCounter <= -4) {
					fiveCounter = 5;
					strength += 5;
					whereToMove = -1;
				}
				else {
					System.out.println("Power cannot be activated right now, you need to wait at least " + (4 + fiveCounter) + " round");
					if (4 + fiveCounter != 1)
						System.out.println("s\n");
					System.out.println("Choose another option");
				}
				break;             
            case 'e': case 'E':
                world.SetCommand(2);
                whereToMove = -1;
                break;
            case 'z': case 'Z':
                System.out.println("Choose one from available organisms:");
                System.out.println("W, S, F, T, A, C, v, t, g, b, h");
                TakeKey();
                System.out.println("\nChoose empty cell");
                world.SetHelpKey(world.GetKey());
                world.SetCommand(9);
                while (true) {
                    try {
                        sleep(40);
                    } catch (InterruptedException ex) {
                        System.out.println("Thread is interrupted");
                    }
                    
                    if (world.GetCommand() == 10) {
                        world.SetCommand(3);
                        break;
                    }
                }
                whereToMove = -1;
                break;
			default:
				System.out.println("Something went wrong try again\n");
				break;
			}
		}
        world.SetKey('0');
                
		if (whereToMove != -2) {
			if (whereToMove != -1)
				Collision(whereToMove);
			break;
		}
	}
}
    


private void TakeKey() 
{
    while (true) {
        try {
            sleep(40);
        } catch (InterruptedException ex) {
            System.out.println("Thread is interrupted");
        }
        if (world.GetKey() == 'W' || world.GetKey() == 'S' || world.GetKey() == 'F' || world.GetKey() == 'T' || world.GetKey() == 'A'
            || world.GetKey() == 'v' || world.GetKey() == 't' || world.GetKey() == 'g' || world.GetKey() == 'b' || world.GetKey() == 'h') {
            break;
        }
        else if (!(world.GetKey() == 'Z' || world.GetKey() == 'z')) {
            System.out.println("Such organism does not exist.");
            world.SetKey('Z');
        }
    }
}

}
