package project2;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import java.util.Collections;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import project2.animals.*;
import project2.plants.*;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class World extends JFrame implements KeyListener {
    
private int mapWidth, mapHeight;
private int command, roundNumber;
private char key, helpKey;

private Comentator comentator;
public Organism[][] organismsMap;
private JButton[][] buttonsMap;

private List<Organism> decreasingOrganisms = new LinkedList<>();
private List<Organism> allHogweeds = new LinkedList<>();
private List<Organism> deadHogweeds = new LinkedList<>();
private List<Organism> deadOrganisms = new LinkedList<>();
private List<Organism> createdOrganisms = new LinkedList<>();
private List<Organism> decreasingOrganismsBefore = new LinkedList<>();

private Container contents;


public void InitializeOrganisms() 
{
    AddCreated(5, 5, 'H');
    AddCreated(4, 3, 'W');
    AddCreated(4, 2, 'W');
    AddCreated(1, 4, 'S');
    AddCreated(2, 5, 'S');
    AddCreated(3, 1, 'F');
    AddCreated(2, 6, 'F');
    AddCreated(1, 1, 'T');
    AddCreated(2, 1, 'T');
    AddCreated(3, 5, 'A');
    AddCreated(3, 3, 'A');
    AddCreated(0, 0, 'C');
    AddCreated(4, 4, 'h');
    AddCreated(3, 4, 'h');
    AddCreated(0, 5, 'h');
    AddCreated(1, 4, 's');
    AddCreated(0, 3, 's');
    AddCreated(6, 0, 'g');
    AddCreated(6, 4, 'g');
    AddCreated(1, 6, 'b');
    AddCreated(4, 6, 'v');
    AddCreated(2, 2, 'v');
    
//    second default map
//    AddCreated(5, 5, 'H');
//    AddCreated(4, 3, 'W');
//    AddCreated(4, 2, 'W');
//    AddCreated(12, 9, 'W');
//    AddCreated(7, 4, 'S');
//    AddCreated(7, 5, 'S');
//    AddCreated(13, 1, 'F');
//    AddCreated(2, 6, 'F');
//    AddCreated(3, 6, 'F');
//    AddCreated(2, 2, 'T');
//    AddCreated(11, 1, 'T');
//    AddCreated(12, 1, 'T');
//    AddCreated(9, 7, 'A');
//    AddCreated(10, 8, 'A');
//    AddCreated(11, 0, 'C');
//    AddCreated(5, 8, 'C');
//    AddCreated(4, 4, 'h');
//    AddCreated(0, 1, 'h');
//    AddCreated(7, 4, 'h');
//    AddCreated(13, 4, 'h');
//    AddCreated(0, 8, 'h');
//    AddCreated(13, 4, 's');
//    AddCreated(11, 4, 's');
//    AddCreated(6, 0, 'g');
//    AddCreated(13, 0, 'g');
//    AddCreated(6, 6, 'g');
//    AddCreated(8, 4, 'g');
//    AddCreated(2, 4, 'g');
//    AddCreated(12, 7, 'b');
//    AddCreated(1, 9, 'b');
//    AddCreated(4, 9, 'v');
//    AddCreated(9, 2, 'v');
	
	MoveAndSort();
}



public void MoveAndSort()
{
	decreasingOrganisms.addAll(createdOrganisms);
	SortList();
  	createdOrganisms.clear();
}



private void SortList() 
{
	Collections.sort(decreasingOrganisms, (first, second) -> { 
			return (second.GetInitiative() - first.GetInitiative());
		});
}



private void CreateMapOfButtons() 
{
    buttonsMap = new JButton[mapHeight][mapWidth];
    
    contents = getContentPane();
    contents.removeAll();
    contents.setLayout(new GridLayout(mapHeight,mapWidth)); 

    for (int i = 0; i < mapHeight; i++) {
        for (int j = 0; j < mapWidth; j++) {
            buttonsMap[i][j] = new JButton();
            contents.add(buttonsMap[i][j]);
            buttonsMap[i][j].addActionListener(actionListener);
            buttonsMap[i][j].setFocusable(false);
        }
    }
}



ActionListener actionListener = new ActionListener() 
{
    public void actionPerformed(ActionEvent e) {
        if (command == 9) {
            Object source = e.getSource();
            for (int i = 0; i < mapHeight; i++) {
                for (int j = 0; j < mapWidth; j++) {
                    if (source == buttonsMap[i][j] && buttonsMap[i][j].getText() == "") {
                        buttonsMap[i][j].setText(Character.toString(helpKey));
                        
                        allHogweeds.clear();
                        deadHogweeds.clear();
                        deadOrganisms.clear();
                        createdOrganisms.clear();
                        comentator.GetCommunicates().clear();
                        
                        for (int k = 0; k < mapHeight; k++)
                            for (int l = 0; l < mapWidth; l++)
                                organismsMap[i][j] = null;
                        
                        AddCreated(j, i, helpKey);
                        InsertToDecreasingBefore();
                        RestoreOrganismsMap();
                        
                        command = 10;
                        break;
                    }
                }
            }    
        }
    } 
};



private void RestoreOrganismsMap() 
{
    decreasingOrganisms.clear();
    decreasingOrganisms.addAll(decreasingOrganismsBefore);
    
    for (int i = 0; i < decreasingOrganisms.size(); i++) {
        organismsMap[decreasingOrganisms.get(i).GetY()][decreasingOrganisms.get(i).GetX()] = decreasingOrganisms.get(i);
    }
}
    


private void InsertToDecreasingBefore() 
{
    for (int j = 0; j < decreasingOrganismsBefore.size(); j++) {
        if (createdOrganisms.get(0).GetInitiative() > decreasingOrganismsBefore.get(j).GetInitiative()) {
            decreasingOrganismsBefore.add(j, createdOrganisms.get(0));
            break;
        }
        else {
            if (j == decreasingOrganismsBefore.size() - 1) {
                decreasingOrganismsBefore.add(createdOrganisms.get(0));
                break;
            }
        }
    }
}



private void CreateBackgroundMapOfOrganisms() 
{
    organismsMap = new Organism[mapHeight][mapWidth];
    for (int i = 0; i < mapHeight; i++) {
        for (int j = 0; j < mapWidth; j++) {
            organismsMap[i][j] = null;
        }
    }
}



public World(Comentator c) 
{
    super("Jan Kowalina 184691");
    
    command = -1;
    roundNumber = 0;
    comentator = c;
    key = '0';
       
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);
    setLocationRelativeTo(null);
    addKeyListener(this);
    setVisible(true);
    setFocusTraversalKeysEnabled(false);
    
    System.out.println("Welcome in my Java game! Enjoy and have fun.\n");
    
    NewGame();
   
}



private void NewGame()
{
	int proper = 0;
	System.out.println("Type F to initialize game from file or to use default map.");
	System.out.println("Type R to generate random world.\n");

	while (true) {
		try {
            sleep(40);
        } catch (InterruptedException ex) {
            System.out.println("Thread is interrupted");
        }
		if (key != '0') {
			switch (key) {
			case 'r': case 'R': {
                key = '0';
				CreateRandomGame();
				proper = 1;
				break;
			}
			case 'f': case 'F': {
                key = '0';
				InitializationFromFile();
				proper = 1;
				break;
			}
			default: {
				System.out.println("Wrong command try again:");
				break;
			}
			}
            key = '0';
			if (proper == 1)
				break;
		}
	}
}



public void CreateRandomGame()
{
	ClearAllVariables();
	int organismNum, nx, ny;
    Scanner in = new Scanner(System.in);
	System.out.println("Give sizes of map, starting from width and number of organisms.\n");
	
    mapWidth = in.nextInt();
    mapHeight = in.nextInt();
    organismNum = in.nextInt();

	CreateMapOfButtons();
    CreateBackgroundMapOfOrganisms();

	nx = (int)(Math.random() * mapWidth);
	ny = (int)(Math.random() * mapHeight);
	AddCreated(nx, ny, 'H');

	if (mapWidth * mapHeight * 3 / 10 < organismNum)
		organismNum = mapWidth * mapHeight * 3 / 10;

	for (int i = 0; i < organismNum - 1; i++) {
		while (true) {
			nx = (int)(Math.random() * mapWidth);
	        ny = (int)(Math.random() * mapHeight);
			if (organismsMap[ny][nx] == null) {
				AddCreated(nx, ny, DrawAnimal());
				break;
			}
		}

	}
    
	MoveAndSort();
}



private void ClearAllVariables()
{
	roundNumber = 0;

	allHogweeds.clear();
	deadHogweeds.clear();
	decreasingOrganisms.clear();
    decreasingOrganismsBefore.clear();
	deadOrganisms.clear();
	createdOrganisms.clear();
	comentator.GetCommunicates().clear();
}



private void InitializationFromFile()
{
	System.out.println("Type D to initialize game from default text file.");
	System.out.println("O to initialize it from your own text file.");
    System.out.println("B to use default built in map.\n");
    
	File file = null;

	int proper = 0;
	while (true) {
		try {
            sleep(40);
        } catch (InterruptedException ex) {
            System.out.println("Thread is interrupted");
        }
		if (key != '0') {
			switch (key) {
			case 'd': case 'D': {
                file = new File("basic.txt");
				proper = 1;
				break;
			}
			case 'o': case 'O': {
				
				System.out.println("Give name of file (without extention .txt) from which you would like to load the game.");
				Scanner in = new Scanner(System.in);
                String s;
                s = in.nextLine();
				s += ".txt";
				System.out.println("\n");
				file = new File(s);
				if (!file.isFile() && !file.canRead()) {
					while (!file.isFile() && !file.canRead()) {
						System.out.println("Wrong file, try again");
						s = in.nextLine();
				        s += ".txt";
                        System.out.println("\n");
                        file = new File(s);
					}
				}
				proper = 1;
				break;
			}
            case 'b': case 'B': {
                ClearAllVariables();
                
                mapWidth = 8;
                mapHeight = 7;
                
                CreateMapOfButtons();
                CreateBackgroundMapOfOrganisms();
                InitializeOrganisms();
                decreasingOrganismsBefore.addAll(decreasingOrganisms);
                proper = 2;
            }
			default: {
				System.out.println("Wrong command try again:");
				break;
			}
			}
            key = '0';
			if (proper != 0)
			    break;
		}
	}
    
    if (proper == 1) {
        Scanner both = null;
        try {
            both = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }

        ClearAllVariables();

        mapWidth = Integer.parseInt(both.next());
        mapHeight = Integer.parseInt(both.next());
        roundNumber = Integer.parseInt(both.next());
        
        CreateMapOfButtons();
        CreateBackgroundMapOfOrganisms();

        String s;
        int x, y;
        char organismSymbol;
        while (both.hasNext()) {
            s = both.next();
            organismSymbol = s.charAt(0);
            x = Integer.parseInt(both.next());
            y = Integer.parseInt(both.next());
            AddCreated(x, y, organismSymbol);

            x = Integer.parseInt(both.next());
            createdOrganisms.get(createdOrganisms.size() - 1).SetStrength(x);
            if (organismSymbol == 'H') {
                x = Integer.parseInt(both.next());
                createdOrganisms.get(createdOrganisms.size() - 1).SetFiveCounter(x);
            }
        }
        MoveAndSort();
        decreasingOrganismsBefore.addAll(decreasingOrganisms);
        both.close();
    }

}



private void HumanDeath()
{
	System.out.println("Choose one from below options");
	System.out.println("N - new game, Q - quit\n");

	int proper = 0;
	while (true) {
		try {
            sleep(40);
        } catch (InterruptedException ex) {
            System.out.println("Thread is interrupted");
        }
		if (key != '0') {
			switch (key) {
			case 'n': case 'N':
				command = 1;
				proper = 1;
				break;
			case 'q': case 'Q':
				command = 0;
				proper = 1;
				break;
			default:
				System.out.println("Wrong command try again:");
				break;
			}
            key = '0';
			if (proper == 1)
				break;
		}
	}
}



public void Export()
{
	System.out.println("Type D to overwrite default text file with current state of game.");
    System.out.println("Type O to write to own file.\n");

	int proper = 0;
	while (true) {
		try {
            sleep(40);
        } catch (InterruptedException ex) {
            System.out.println("Thread is interrupted");
        }
		if (key != '0') {
			switch (key) {
			case 'd': case 'D': {
				proper = 1;
				break;
			}
			case 'o': case 'O': {
				proper = 2;
				break;
			}
			default: {
				System.out.println("Wrong command try again:");
				break;
			}
			}
            key = '0';
			if (proper != 0)
				break;
		}
	}

	File file = null;
	if (proper == 1) {
		file = new File("basic_text_file.txt");	
	}
	else if (proper == 2) {
		String s;
		System.out.println("Give name of file (without extention .txt) to which you would like to export current state of game.");
		Scanner in = new Scanner(System.in);
        s = in.nextLine();
		s += ".txt";
		System.out.println("\n");
        file = new File(s);
		if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

    try {
        
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(mapWidth + " " + mapHeight + " " + roundNumber + "\n");
        for (int i = 0; i < decreasingOrganisms.size(); i++) {
            bw.write(decreasingOrganisms.get(i).GetSymbol() + " " + decreasingOrganisms.get(i).GetX() + " " + decreasingOrganisms.get(i).GetY() + " " + decreasingOrganisms.get(i).GetStrength());
            if (decreasingOrganisms.get(i).GetSymbol() == 'H')
                bw.write(" " + decreasingOrganisms.get(i).GetFiveCounter());
            bw.write("\n");
        }
        bw.close();
        
    } catch(IOException e) {
        e.printStackTrace();
    }
}



private char DrawAnimal()
{
	int i = (int)(Math.random() * 11);
	switch (i) {
	case 0:
		return 'W';
	case 1:
		return 'S';
	case 2:
		return 'F';
	case 3:
		return 'T';
	case 4:
		return 'A';
	case 5:
		return 'C';
	case 6:
		return 'v';
	case 7:
		return 't';
	case 8:
		return 'g';
	case 9:
		return 'b';
	case 10:
		return 'h';
    default :
        return 'r';
	}
}



public void AddCreated(int nx, int ny, char symbol)
{
    if (0 <= nx && nx < mapWidth &&
            0 <= ny && ny < mapHeight) {
            if (organismsMap[ny][nx] == null) {
                    switch (symbol) {
                    case 'H':
                        createdOrganisms.add(new Human(nx, ny, this, true));
                        break;
                    case 'W':
                        createdOrganisms.add(new Wolf(nx, ny, this, true));
                        break;
			        case 'S':
                        createdOrganisms.add(new Sheep(nx, ny, this, true));
                        break;
			        case 'F':
                        createdOrganisms.add(new Fox(nx, ny, this, true));
                        break;
                    case 'T':
                        createdOrganisms.add(new Turtle(nx, ny, this, true));
                        break;
                    case 'A':
                        createdOrganisms.add(new Anthelope(nx, ny, this, true));
                        break;
                    case 'C':
                        createdOrganisms.add(new Cyber(nx, ny, this, true));
                        break;
                    case 'v':
                        createdOrganisms.add(new Grass(nx, ny, this, true));
                        break;
                    case 't':
                        createdOrganisms.add(new Thistle(nx, ny, this, true));
                        break;
                    case 'g':
                        createdOrganisms.add(new Guarana(nx, ny, this, true));
                        break;
                    case 'b':
                        createdOrganisms.add(new Belladonna(nx, ny, this, true));
                        break;
                    case 'h':
                        createdOrganisms.add(new Hogweed(nx, ny, this, true));
                        allHogweeds.add(createdOrganisms.get(createdOrganisms.size() - 1));
                        break;
                    }

                    organismsMap[ny][nx] = createdOrganisms.get(createdOrganisms.size() - 1);
            }
    }
}



@Override
public void keyTyped(KeyEvent e) 
{
}

@Override
public void keyPressed(KeyEvent e) 
{
}

@Override
public void keyReleased(KeyEvent e) 
{
    key = e.getKeyChar(); //e.getKeyText(e.getKeyCode()).charAt(0);//e.getKeyChar();
}



public void DrawWorld() 
{
    if (roundNumber == 0)
		System.out.println("Initially world looks like this:\n");
	else
		System.out.println("\n\nRound number: " + roundNumber + "\n");
	roundNumber++;
    
    for (int i = 0; i < mapHeight; i++) {
        for (int j = 0; j < mapWidth; j++) {
            buttonsMap[i][j].setText("");
            if (organismsMap[i][j] != null) {
                buttonsMap[i][j].setText(Character.toString(organismsMap[i][j].GetSymbol()));
            }
        }
    }
    
    comentator.PrintCommunicates();
}



public void AddCleanLists()
{
    decreasingOrganismsBefore.clear();
    decreasingOrganismsBefore.addAll(decreasingOrganisms);

	if (createdOrganisms.size() != 0) {
		for (int i = 0; i < createdOrganisms.size(); i++) {
			for (int j = 0; j < decreasingOrganisms.size(); j++) {
				if (createdOrganisms.get(i).GetInitiative() > decreasingOrganisms.get(j).GetInitiative()) {
					decreasingOrganisms.add(j, createdOrganisms.get(i));
					break;
				}
				else {
					if (j == decreasingOrganisms.size() - 1) {
						decreasingOrganisms.add(createdOrganisms.get(i));
                        break;
                    }
				}
			}
		}
	}

	if (deadHogweeds.size() != 0) { 
		for (int i = 0; i < deadHogweeds.size(); i++) {
			for (int j = 0; j < allHogweeds.size(); j++) {
				if (deadHogweeds.get(i).GetX() == allHogweeds.get(j).GetX()
                    && deadHogweeds.get(i).GetY() == allHogweeds.get(j).GetY()) {
                    
					allHogweeds.remove(j);
					break;
				}
		    }
	    }
    }
    
    if (deadOrganisms.size() != 0) { 
		for (int i = 0; i < deadOrganisms.size(); i++) {
			for (int j = 0; j < decreasingOrganisms.size(); j++) {
				if (deadOrganisms.get(i).GetX() == decreasingOrganisms.get(j).GetX() 
                    && deadOrganisms.get(i).GetY() == decreasingOrganisms.get(j).GetY()
                    && deadOrganisms.get(i).GetSymbol() == decreasingOrganisms.get(j).GetSymbol()) {
                    
					decreasingOrganisms.remove(j);
					break;
				}
			}
		}
	}

	createdOrganisms.clear();
	deadOrganisms.clear();
	deadHogweeds.clear();
}



public boolean MakeTurn() 
{   
    for (int i = 0; i < decreasingOrganisms.size(); i++) {
		if (decreasingOrganisms.get(i).GetAlive() == true) {
			decreasingOrganisms.get(i).Action();
			if (command != -1) {
				if (command == 4)
					HumanDeath();

				if (command == 0)
					return false;

				if (command == 1) {
					NewGame();
					command = -1;
					return true;
				}

                if (command == 2) {
					Export();
					command = -1;
					return true;
				}
                
                if (command == 3)
                    i = 0;
                              
				command = -1;
			}
		}
	}

	AddCleanLists();
	
	return true;
}



public List<Organism> GetCreatedOrganisms()
{
    return createdOrganisms;
}

public List<Organism> DeadHogweeds()
{
    return deadHogweeds;
}

public List<Organism> AllHogweeds()
{
    return allHogweeds;
}

public List<Organism> DeadList()
{
    return deadOrganisms;
}


public Comentator GetCommentator()
{
	return comentator;
}

public char GetKey()
{
    return key;
}

public int GetMapX()
{
    return mapWidth;
}


public int GetMapY()
{
    return mapHeight;
}


public int GetCommand()
{
    return command;
}


public Organism[][] GetMap() // ciekawe czy to bedzie funkcjonowalo
{
    return organismsMap;
}


public void SetKey(char k) 
{
    key = k;
}


public void SetHelpKey(char k) 
{
    helpKey = k;
}


public void SetCommand(int com)
{
    command = com;
}


public void SetRoundNumber(int num)
{
    roundNumber = num;
}


}
