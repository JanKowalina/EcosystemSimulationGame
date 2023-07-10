package project2.animals;

import project2.Animal;
import project2.World;


public class Turtle extends Animal {
    
public void Action()
{
	if ((int)(Math.random() * 4) == 0) {
		int whereToMove = RandomMovement();
		Move(whereToMove);
	}
}


public Turtle(int x, int y, World world, boolean alive)
{
    super(x, y, world, 'T', 1, alive, 2);
}
    
}
