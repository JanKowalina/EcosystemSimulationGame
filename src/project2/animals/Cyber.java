package project2.animals;

import project2.Animal;
import project2.World;


public class Cyber extends Animal {

    public Cyber(int x, int y, World world, boolean alive) {
        super(x, y, world, 'C', 4, alive, 11);
    }
    
private int CyberMovement()
{
	int fx = x, fy = y, sum = world.GetMapX() + world.GetMapY();

	if (world.AllHogweeds().size() != 0) {
		for (int i = 0; i <  world.AllHogweeds().size(); i++) {
			if (Math.abs(x - world.AllHogweeds().get(i).GetX()) + Math.abs(y - world.AllHogweeds().get(i).GetY()) < sum) {
				fx = world.AllHogweeds().get(i).GetX();
                fy = world.AllHogweeds().get(i).GetY();
				sum = Math.abs(x - world.AllHogweeds().get(i).GetX()) + Math.abs(y - world.AllHogweeds().get(i).GetY());
			}
		}

		if (x != fx) {
			if (fx > x)
				return 1;
			else
				return 3;
		}
		else {
			if (fy > y)
				return 2;
			else
				return 0;
		}
	}
	else
		return RandomMovement();
}


public void Action()
{
	int whereToMove = CyberMovement();
	Move(whereToMove);
}    
    
}
