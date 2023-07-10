package project2.animals;

import project2.Animal;
import project2.World;


public class Anthelope extends Animal{
    
private int numberOfMoves;
    

public void Action()
{
	numberOfMoves = 0;

	while (numberOfMoves < 2) {
		int whereToMove = RandomMovement();
		Move(whereToMove);
		numberOfMoves++;
	}
}



public void Collision(int whereToMove)
{
	if (GetSymbol() == world.GetMap()[y][x].GetSymbol()) { // breading part
		ReturnToOrginalPosition(whereToMove);

		int[] whereToCreate = RandomFreeTile(whereToMove); 
		if (whereToCreate != null) {
			world.GetCommentator().FightCommunicat(whereToCreate[0], whereToCreate[1], GetSymbol(), GetSymbol(), 0);
			world.AddCreated(whereToCreate[0], whereToCreate[1], GetSymbol()); 
		}
	}
	else {
		if ((int)(Math.random() * 2) == 1) {
			world.GetCommentator().FightCommunicat(x, y, 'A', world.GetMap()[y][x].GetSymbol(), 0);
			ReturnToOrginalPosition(whereToMove);
		}
		else {
			if (strength >= world.GetMap()[y][x].GetStrength()) {
				if (world.GetMap()[y][x].GetSymbol() == 'g')
					strength += 3;

				if (world.GetMap()[y][x].GetSymbol() == 'H') {
					world.GetCommentator().FatalCommunicat(x, y, GetSymbol());
					world.SetCommand(4);
				}
				else {
					world.GetCommentator().FightCommunicat(x, y, GetSymbol(), world.GetMap()[y][x].GetSymbol(), 2);
					world.GetMap()[y][x].SetAlive(false);
					world.DeadList().add(world.GetMap()[y][x]);
					world.GetMap()[y][x] = this;
				}
			}
			else {
				world.GetCommentator().FightCommunicat(x, y, GetSymbol(), world.GetMap()[y][x].GetSymbol(), 1);
				this.SetAlive(false);
				world.DeadList().add(this);	
				numberOfMoves++;
			}
		}
	}
}


public Anthelope(int x, int y, World world, boolean alive)	
{
    super(x, y, world, 'A', 1, alive, 2);
}

}
