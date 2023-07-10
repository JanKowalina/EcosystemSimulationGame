package project2.animals;

import project2.Animal;
import project2.World;


public class Fox extends Animal{
    
public void Action()
{
	int hx = x, hy = y;
	int[] neighbours = new int[4];
	int freeNeighbours = 0;
	int whereToMove = -1;

	if (hy - 1 < 0)
		neighbours[0] = 1;
	else {
		if (world.GetMap()[y - 1][x] != null) {
			if (strength < world.GetMap()[y - 1][x].GetStrength())
				neighbours[0] = 1;
			else {
				neighbours[0] = 0;
				freeNeighbours++;
			}
		}
		else {
			neighbours[0] = 0;
			freeNeighbours++;
		}
	}

	if (hx + 1 >= world.GetMapX())
		neighbours[1] = 1;
	else {
		if (world.GetMap()[y][x + 1] != null) {
			if (strength < world.GetMap()[y][x + 1].GetStrength())
				neighbours[1] = 1;
			else {
				neighbours[1] = 0;
				freeNeighbours++;
			}
		}
		else {
			neighbours[1] = 0;
			freeNeighbours++;
		}
	}

	if (hy + 1 >= world.GetMapY())
		neighbours[2] = 1;
	else {
		if (world.GetMap()[y + 1][x] != null) {
			if (strength < world.GetMap()[y + 1][x].GetStrength())
				neighbours[2] = 1;
			else {
				neighbours[2] = 0;
				freeNeighbours++;
			}
		}
		else {
			neighbours[2] = 0;
			freeNeighbours++;
		}
	}

	if (hx - 1 < 0)
		neighbours[3] = 1;
	else {
		if (world.GetMap()[y][x - 1] != null) {
			if (strength < world.GetMap()[y][x - 1].GetStrength())
				neighbours[3] = 1;
			else {
				neighbours[3] = 0;
				freeNeighbours++;
			}
		}
		else {
			neighbours[3] = 0;
			freeNeighbours++;
		}
	}

	if (freeNeighbours != 0) {
		int j = (int)(Math.random() * freeNeighbours);
		for (int i = 0; i < 4; i++) {
			if (neighbours[i] == 0) {
				if (j == 0) {
					whereToMove = i;
					break;
				}
				else
					j--;
			}
		}

		Move(whereToMove);
	}

	
}



public Fox(int x, int y, World world, boolean alive)
{
    super(x, y, world, 'F', 7, alive, 3);
}    
    
}
