package project2;

import java.lang.*;


public class Animal extends Organism {
    
public Animal(int x, int y, World world, char symbol, int initiative, boolean alive, int strength)

{
    super(x, y, world, symbol, initiative, alive, strength);
}


protected int RandomMovement()
{
    int[] neighbours = new int[4];
    int freeNeighbours = 0;

    if (y - 1 < 0)
        neighbours[0] = 1;
    else {
        neighbours[0] = 0;
        freeNeighbours++;
    }

    if (x + 1 >= world.GetMapX())
        neighbours[1] = 1;
    else {
        neighbours[1] = 0;
        freeNeighbours++;
    }

    if (y + 1 >= world.GetMapY())
        neighbours[2] = 1;
    else {
        neighbours[2] = 0;
        freeNeighbours++;
    }

    if (x - 1 < 0)
        neighbours[3] = 1;
    else {
        neighbours[3] = 0;
        freeNeighbours++;
    }

    int j = (int) (Math.random() * freeNeighbours);
    int whereToMove = -1;
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
    return whereToMove;
}



protected void Move(int whereToMove)
{
    if (whereToMove == 0) {
        world.GetMap()[y][x] = null;
        y--;
    }
    else if (whereToMove == 1) {
        world.GetMap()[y][x] = null;
        x++;
    }
    else if (whereToMove == 2) {
        world.GetMap()[y][x] = null;
        y++;
    }
    else if (whereToMove == 3) {
        world.GetMap()[y][x] = null;
        x--;
    }

    if (world.GetMap()[y][x] == null)
        world.GetMap()[y][x] = this;
    else
        Collision(whereToMove);
}


@Override
public void Action()
{
    int whereToMove = RandomMovement();
    Move(whereToMove);
}



protected void ReturnToOrginalPosition(int whereToMove)
{
    if (whereToMove == 0) {
        y++;
        world.GetMap()[y][x] = this;
    }
    else if (whereToMove == 1) {
        x--;
        world.GetMap()[y][x] = this;
    }
    else if (whereToMove == 2) {
        y--;
        world.GetMap()[y][x] = this;
    }
    else if (whereToMove == 3) {
        x++;
        world.GetMap()[y][x] = this;
    }
}


@Override
public void Collision(int whereToMove)
{ 
    if (GetSymbol() == world.GetMap()[y][x].GetSymbol()) {

        ReturnToOrginalPosition(whereToMove);

        int[] whereToCreate = RandomFreeTile(whereToMove);
        if (whereToCreate != null) {
            world.GetCommentator().FightCommunicat(whereToCreate[0], whereToCreate[1], GetSymbol(), GetSymbol(), 0);
            world.AddCreated(whereToCreate[0], whereToCreate[1], GetSymbol());
        }
    }
    else {
        int escape = 2;
        if (world.GetMap()[y][x].GetSymbol() == 'A')
            escape = (int) (Math.random() * 2);

        if (world.GetMap()[y][x].GetSymbol() == 'T' && GetStrength() < 5) {
            world.GetCommentator().FightCommunicat(x, y, GetSymbol(), 'T', 0);
            ReturnToOrginalPosition(whereToMove);
        }
        else if (escape == 1) {
            world.GetCommentator().FightCommunicat(x, y, GetSymbol(), 'A', 0);

            if (whereToMove == 0) {
                world.GetMap()[y][x].SetY(y + 1);
                world.GetMap()[y + 1][x] = world.GetMap()[y][x];
                world.GetMap()[y][x] = this;
            }
            else if (whereToMove == 1) {
                world.GetMap()[y][x].SetX(x - 1);
                world.GetMap()[y][x - 1] = world.GetMap()[y][x];
                world.GetMap()[y][x] = this;
            }
            else if (whereToMove == 2) {
                world.GetMap()[y][x].SetY(y - 1);
                world.GetMap()[y - 1][x] = world.GetMap()[y][x];
                world.GetMap()[y][x] = this;
            }
            else if (whereToMove == 3) {
                world.GetMap()[y][x].SetX(x + 1);
                world.GetMap()[y][x + 1] = world.GetMap()[y][x];
                world.GetMap()[y][x] = this;
            }
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
                    if (world.GetMap()[y][x].GetSymbol() == 'h')
                        world.DeadHogweeds().add(world.GetMap()[y][x]);

                    world.GetCommentator().FightCommunicat(x, y, GetSymbol(), world.GetMap()[y][x].GetSymbol(), 2);
                    world.GetMap()[y][x].SetAlive(false);
                    world.DeadList().add(world.GetMap()[y][x]);
                    world.GetMap()[y][x] = this;
                }
            }
            else {
                if (GetSymbol() == 'H') {
                    world.GetCommentator().FatalCommunicat(x, y, world.GetMap()[y][x].GetSymbol());
                    world.SetCommand(4);
                }
                else {
                    world.GetCommentator().FightCommunicat(x, y, GetSymbol(), world.GetMap()[y][x].GetSymbol(), 1);
                    this.SetAlive(false);
                    world.DeadList().add(this);
                }
            }
        }
    }
}

}

