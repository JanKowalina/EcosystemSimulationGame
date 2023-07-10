package project2.plants;

import project2.Plant;
import project2.World;


public class Thistle extends Plant {
    
public Thistle(int x, int y, World world, boolean alive)
{
    super(x, y, world, 't', 0, alive, 0);
}


public void Action()
{
	int i = 0;
	while (i++ < 3) {
		if ((int)(Math.random() * 10) < probabilityParameter) {
			int[] whereToCreate = RandomFreeTile(-1);
			if (whereToCreate != null) {
				world.GetCommentator().SowCommunicat(whereToCreate[0], whereToCreate[1], GetSymbol());
				world.AddCreated(whereToCreate[0], whereToCreate[1], GetSymbol());
			}
		}
	}
}
    
}
