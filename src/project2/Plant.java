package project2;


public class Plant extends Organism {
    
protected int probabilityParameter;    
    
public Plant(int x, int y, World world, char symbol, int initiative, boolean alive, int strength)	
{
    super(x, y, world, symbol, initiative, alive, strength);
    probabilityParameter = 1;
}


@Override
public void Action()
{
	if ((int)(Math.random() * 10) < probabilityParameter) {
		int[] whereToCreate = RandomFreeTile(-1);
		if (whereToCreate != null) {
			world.GetCommentator().SowCommunicat(whereToCreate[0], whereToCreate[1], GetSymbol());
			world.AddCreated(whereToCreate[0], whereToCreate[1], GetSymbol());
		}
	}
}

@Override
public void Collision(int whereToMove)
{

}
    
}
