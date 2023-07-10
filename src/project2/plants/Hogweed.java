package project2.plants;

import project2.Plant;
import project2.World;


public class Hogweed extends Plant {
    
public Hogweed(int x, int y, World world, boolean alive)
{
    super(x, y, world, 'h', 0, alive, 10);
}


public void Action()
{
	if ((int)(Math.random() * 10) < probabilityParameter) {
		int[] whereToCreate = RandomFreeTile(-1);// signal to not change position
		if (whereToCreate != null) {
			world.GetCommentator().SowCommunicat(whereToCreate[0], whereToCreate[1], GetSymbol());
			world.AddCreated(whereToCreate[0], whereToCreate[1], GetSymbol());
		}
	}

	char c = 'n';

	if (y - 1 >= 0 && world.GetMap()[y - 1][x] != null) {

		c = world.GetMap()[y - 1][x].GetSymbol();
		if ((c == 'W' || c == 'S' || c == 'F' || c == 'T' || c == 'A' || c == 'H')
			&& world.GetMap()[y - 1][x].GetStrength() <= GetStrength()) {

			if (c == 'H') {
				world.SetCommand(4);
				world.GetCommentator().FatalCommunicat(x, y - 1, 'h');
			}
			else {
				world.GetCommentator().FightCommunicat(x, y - 1, GetSymbol(), c, 2);
				world.DeadList().add(world.GetMap()[y - 1][x]);
				world.GetMap()[y - 1][x] = null;
			}
		}
	}

	if (x + 1 < world.GetMapX() && world.GetMap()[y][x + 1] != null) {

		c = world.GetMap()[y][x + 1].GetSymbol();
		if ((c == 'W' || c == 'S' || c == 'F' || c == 'T' || c == 'A' || c == 'H')
			&& world.GetMap()[y][x + 1].GetStrength() <= GetStrength()) {

			if (c == 'H') {
				world.SetCommand(4);
				world.GetCommentator().FatalCommunicat(x + 1, y, 'h');
			}
			else {
				world.GetCommentator().FightCommunicat(x + 1, y, GetSymbol(), c, 2);
				world.DeadList().add(world.GetMap()[y][x + 1]);
				world.GetMap()[y][x + 1] = null;
			}
		}
	}

	if (y + 1 < world.GetMapY() && world.GetMap()[y + 1][x] != null) {

		c = world.GetMap()[y + 1][x].GetSymbol();
		if ((c == 'W' || c == 'S' || c == 'F' || c == 'T' || c == 'A' || c == 'H')
			&& world.GetMap()[y + 1][x].GetStrength() <= GetStrength()) {

			if (c == 'H') {
				world.SetCommand(4);
				world.GetCommentator().FatalCommunicat(x, y + 1, 'h');
			}
			else {
				world.GetCommentator().FightCommunicat(x, y + 1, GetSymbol(), c, 2);
				world.DeadList().add(world.GetMap()[y + 1][x]);
				world.GetMap()[y + 1][x] = null;
			}
		}
	}

	if (x - 1 >= 0 && world.GetMap()[y][x - 1] != null) {

		c = world.GetMap()[y][x - 1].GetSymbol();
		if ((c == 'W' || c == 'S' || c == 'F' || c == 'T' || c == 'A' || c == 'H')
			&& world.GetMap()[y][x - 1].GetStrength() <= GetStrength()) {

			if (c == 'H') {
				world.SetCommand(4);
				world.GetCommentator().FatalCommunicat(x - 1, y, 'h');
			}
			else {
				world.GetCommentator().FightCommunicat(x - 1, y, GetSymbol(), c, 2);
				world.DeadList().add(world.GetMap()[y][x - 1]);
				world.GetMap()[y][x - 1] = null;
			}
		}
	}
}
    
}
