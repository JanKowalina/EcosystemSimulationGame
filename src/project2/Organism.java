package project2;

import java.lang.Math;         


public abstract class Organism {
    
private char symbol;
private int initiative;	
private boolean alive;	
public World world; // to chyba nie jest aktywne

protected int x, y, fiveCounter, strength;

public Organism(int x, int y, World world, char symbol, int initiative, boolean alive, int strength) 
{
    this.x = x; 
    this.y = y;
    this.world = world;
    this.symbol = symbol;
    this.initiative = initiative;
    this.alive = alive; 
    this.strength = strength;
}

abstract public void Action(); // tych dwoch chyba nie ma sensu tu oznaczac skoro abstract powinno zalatwic psrawe
abstract public void Collision(int whereToMove); //public i abstarct chyba moga byc zaminione iejcami

public char GetSymbol()
{
    return symbol;
}


public int GetInitiative()
{
    return initiative;
}


public int GetX()
{
    return x;
}


public int GetY()
{
    return y;
}


public boolean GetAlive()
{
    return alive;
}


public int GetStrength()
{
    return strength;
}


public int GetFiveCounter()
{
    return fiveCounter;
}



public void SetFiveCounter(int five)
{
    fiveCounter = five;
}


public void SetStrength(int stren)
{
    strength = stren;
}


public void SetAlive(boolean ali)
{
    alive = ali;
}


public void SetX(int nx)
{
        x = nx;
}


public void SetY(int ny)
{
        y = ny;
}



protected int[] RandomFreeTile(int whereToMove)
{
    int hx = x, hy = y;
    int[] neighbours = new int[4];
    int freeNeighbours = 0;
    int[] whereToCreate = new int[2];

    if (whereToMove != -1) { // there is -1 because plants are also using this function
        if (whereToMove == 0) // nire pamietam jak to bylo z tymi opartorami porownania, ale nie jestem pewien czy aby nie bylo jakis roznic
            hy = y - 1;
        else if (whereToMove == 1)
            hx = x + 1;
        else if (whereToMove == 2)
            hy = y + 1;
        else if (whereToMove == 3)
            hx = x - 1;
    }

    if (hy - 1 < 0)
        neighbours[0] = 1;
    else if (world.GetMap()[hy - 1][hx] == null) { //nie wiem czy to GetMap dziala skoro nie podświtlone
        neighbours[0] = 0;
        freeNeighbours++;
    }
    else
        neighbours[0] = 1;

    if (hx + 1 >= world.GetMapX())
        neighbours[1] = 1;
    else if (world.GetMap()[hy][hx + 1] == null) {
        neighbours[1] = 0;
        freeNeighbours++;
    }
    else
        neighbours[1] = 1;

    if (hy + 1 >= world.GetMapY())
        neighbours[2] = 1;
    else if (world.GetMap()[hy + 1][hx] == null) {
        neighbours[2] = 0;
        freeNeighbours++;
    }
    else
        neighbours[2] = 1;

    if (hx - 1 < 0)
        neighbours[3] = 1;
    else if (world.GetMap()[hy][hx - 1] == null) {
        neighbours[3] = 0;
        freeNeighbours++;
    }
    else
        neighbours[3] = 1;

    if (freeNeighbours == 0)//in case all are occupied
        return null; // zmienilem z NULL taka intuicja
    else {
        int j = (int) (Math.random() * freeNeighbours);
        int whichWayCreate = -1;
        for (int i = 0; i < 4; i++) {
            if (neighbours[i] == 0) {
                if (j == 0) {
                    whichWayCreate = i;
                    break;
                }
                else
                    j--;
            }
        }

        if (whichWayCreate == 0) // pytanie czy z tym bedzie sie kompilowalo, czy juz lepiej zainicjalizowac na -1 na samym poczatku zeby miec to z glowy
            hy--;
        else if (whichWayCreate == 1)
            hx++;
        else if (whichWayCreate == 2)
            hy++;
        else if (whichWayCreate == 3)
            hx--;

        whereToCreate[0] = hx;
        whereToCreate[1] = hy;

        return whereToCreate;
    }
}
    
}
