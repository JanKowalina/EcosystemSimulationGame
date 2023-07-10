package project2;

import java.util.LinkedList;
import java.util.List;


public class Comentator {
    
private List<String> allCommunicates = new LinkedList<>();
    
public void FatalCommunicat(int x, int y, char symbol)
{
	System.out.println("GAME OVER\n");
	System.out.println("Human was killed by " + LongForm(symbol) + " during fight at position (" + (x + 1) + "," + (y + 1) + ")\n");
}



public void SowCommunicat(int x, int y, char symbol)
{
	String com = "New " + LongForm(symbol) + " was sowed at position (" + (x + 1) + "," + (y + 1) + ")";
	allCommunicates.add(com);
}



public void FightCommunicat(int x, int y, char attacerSymbol, char defenderSymbol, int whoIsDead)
{
	String com;
	if (attacerSymbol == defenderSymbol) {
		com = "New " + LongForm(attacerSymbol) + " was created at position (" + (x + 1) + "," + (y + 1) + ")";
	}
	else if (whoIsDead == 0) {
		if (defenderSymbol == 'T') {
			com = LongForm(defenderSymbol) + " reflected attac of " + LongForm(attacerSymbol) + " at position (" + (x + 1) + "," + (y + 1) + ")";
		}
		else {
			if (attacerSymbol == 'A') {
				com = LongForm(attacerSymbol) + " resigned from fight with " + LongForm(defenderSymbol) + " at position (" + (x + 1) + "," + (y + 1) + ")";
			}
			else {
				com = LongForm(defenderSymbol) + " escaped from attac of  " + LongForm(attacerSymbol) + " at position (" + (x + 1) + "," + (y + 1) + ")";
			}
		}
	}
	else {
		if (whoIsDead == 2) {
			char a = attacerSymbol;
			if (a == 'h')
				com = LongForm(a) + " killed its neighbour " + LongForm(defenderSymbol) + " at position (" + (x + 1) + "," + (y + 1) + ")";
			else if (a == 'C' && defenderSymbol == 'h')
				com = LongForm(defenderSymbol) + " was exterminated by its natural enemy " + LongForm(a) + " at position (" + (x + 1) + "," + (y + 1) + ")";
			else if (defenderSymbol == 'g')
				com = "Attacer " + LongForm(a) + " ate " + LongForm(defenderSymbol) + " and gained 3 points of strength at position (" + (x + 1) + "," + (y + 1) + ")";
			else
				com = "Attacer " + LongForm(a) + " won fight with " + LongForm(defenderSymbol) + " at position (" + (x + 1) + "," + (y + 1) + ")";
			}
		else {
			com = "Defender " + LongForm(defenderSymbol) + " won fight with " + LongForm(attacerSymbol) + " at position (" + (x + 1) + "," + (y + 1) + ")";
		}
	}
	allCommunicates.add(com);
}



public void PrintCommunicates()
{
	if (!allCommunicates.isEmpty()) {
	System.out.println("Fights, breeding and sow: \n");
	for (int i = 0; i < allCommunicates.size(); i++)
		System.out.println(allCommunicates.get(i)); 
	}
	allCommunicates.clear();
}



private String LongForm(char c)
{
	switch (c) {
	case 'W':
		return "Wolf";
	case 'S':
		return "Sheep";
	case 'F':
		return "Fox";
	case 'T':
		return "Turtle";
	case 'A':
		return "Anthelope";
	case 'H':
		return "Human";
	case 'C':
		return "Cyber Sheep";
	case 'v':
		return "Grass";
	case 't':
		return "Sow Thistle";
	case 'g':
		return "Guarana";
	case 'b':
		return "Belladonna";
	case 'h':
		return "Sosnowsky's Hogweed";
    default :
        return "Ther is no such Organism";
	}
}


public List<String> GetCommunicates()
{
	return allCommunicates;
}


public Comentator()
{
}

}
