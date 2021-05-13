/* This TempleUser class contains the main method, which allows the user to play
*  the Temple of Wishes game. This class imports the dragon and items files, and 
*  handles all the user input of commands. Commands can be input until the user 
*  quits, or until the game is complete (all items taken/all dragons defeated).
*/

//Author:   Matthew Rothery
//Date:     10/12/2019
//Email:    M.Rothery@liverpool.ac.uk
//All Rights Reserved

//Import necessary java classes
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//Define TempleUser class
public class TempleUser
{
    //Define main method
    public static void main(String[] args)
    {
        //Tell player that a game is starting
        System.out.println("Starting the game...");

        //Instantiate a temple object
        Temple temple = new Temple();
        
        //Set up a scanner to take input of dragon file
        Scanner fileImport;
        try
        {
            fileImport = new Scanner(new File("dragons.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Cannot find dragons file");
            return;
        }

        //Get the list of all dragon names
        ArrayList<String> dragonNames = new ArrayList<String>();
        dragonNames = Dragon.getDragonNames();

        //While there are still names in the input file, add these to list
        while (fileImport.hasNextLine())
        {
            dragonNames.add(fileImport.nextLine());
        }

        //Set up a scanner to take input of item file
        try
        {
            fileImport = new Scanner(new File("items.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Cannot find items file");
            return;
        }

        //Get the list of all item names
        ArrayList<String> itemNames = new ArrayList<String>();

        itemNames = Item.getItemNames();
        //While there are still names in the input file, add these to list
        while (fileImport.hasNextLine())
        {
            itemNames.add(fileImport.nextLine());
        }
        //Close input stream for files
        fileImport.close();

        //Call method to fill temple object with items/dragons
        temple.fillTemple(temple);

        //Instantiate a new Hero object
        Hero hero = new Hero();

        //Tell the user the position of their player
        hero.printPosition();

        //Get the chamber that the player is currently in, describe its layout
        Chamber currentChamber = temple.getChamber(hero.getRow(), hero.getCol());
        char[] currentLayout = currentChamber.getLayout();
        currentChamber.describeChamber();

        //Create a new scanner object to take players commands input
        Scanner in = new Scanner(System.in);

        //Repeat until the game is set to completed
        while (!hero.getGameStatus())
        {
            //Ask the user to input a command, store command as a char
            System.out.print("\nWhat would you like to do? ");
            char decision = in.next().charAt(0);

            //Get the dragon/item at the current Chamber
            Dragon currentDragon = currentChamber.getDragon();
            Item currentItem = currentChamber.getItem();
            
            //Switch statement to handle users command input
            switch(decision)
            {
                //If user tries to go north
                case 'n': case 'N':
                    //Check if there is a door in the north wall
                    if (currentLayout[0] == '1')
                    {
                        //If there is, go north and print new Hero position
                        hero.goNorth();
                        hero.printPosition();

                        //Describe the new chamber
                        currentChamber = temple.getChamber(hero.getRow(), hero.getCol());
                        currentChamber.describeChamber();
                        currentLayout = currentChamber.getLayout();
                    }
                    //Otherwise tell user that they can't go that way
                    else
                    {
                        System.out.println("You cannot go that way!");
                    }             
                    break;
                //If user tries to go east
                case 'e': case 'E':
                    //Check if there is a door in the east wall
                    if (currentLayout[1] == '1')
                    {
                        //If there is, go east and print new Hero position
                        hero.goEast();
                        hero.printPosition();

                        //Describe the new chamber
                        currentChamber = temple.getChamber(hero.getRow(), hero.getCol());
                        currentChamber.describeChamber();
                        currentLayout = currentChamber.getLayout();
                    }
                    //Otherwise tell user that they can't go that way
                    else
                    {
                        System.out.println("You cannot go that way!");
                    }  
                    break;
                //If user tries to go south
                case 's': case 'S':
                    if (currentLayout[2] == '1')
                    {
                        //If there is, go south and print new Hero position
                        hero.goSouth();
                        hero.printPosition();

                        //Describe the new chamber
                        currentChamber = temple.getChamber(hero.getRow(), hero.getCol());
                        currentChamber.describeChamber();
                        currentLayout = currentChamber.getLayout();
                    }
                    //Otherwise tell user that they can't go that way
                    else
                    {
                        System.out.println("You cannot go that way!");
                    } 
                    break;
                //If user tries to go west
                case 'w': case 'W':
                    if (currentLayout[3] == '1')
                    {
                        //If there is, go west and print new Hero position
                        hero.goWest();
                        hero.printPosition();

                        //Describe the new chamber
                        currentChamber = temple.getChamber(hero.getRow(), hero.getCol());
                        currentChamber.describeChamber();
                        currentLayout = currentChamber.getLayout();
                    }
                    //Otherwise tell user that they can't go that way
                    else
                    {
                        System.out.println("You cannot go that way!");
                    } 
                    break;
                //If the user tries to fight the dragon in that chamber
                case 'f': case 'F':
                    //Check if the dragon is already defeated
                    if (currentDragon.getDefeatStatus() == true)
                    {
                        System.out.println("There is no dragon here");
                    }
                    //If it isnt, call the fight method
                    else if (currentDragon.getDefeatStatus() == false)
                    {
                        //If a 1 is returned, Hero wins, tell user new firepower
                        if (hero.fight(hero, currentDragon) == 1)
                        {
                            System.out.println("You have won the battle! Your firepower is now " + hero.getFirepower());
                        }
                        //Otherwise Dragon wins, tell user new firepower
                        else
                        {
                            System.out.println("You have lost the battle! Your firepower is now " + hero.getFirepower() + ". Come back when you are stronger.");
                        }
                    }
                    break;
                //If the user tries to view their inventory
                case 'i': case 'I':
                    //Call showInventory method
                    hero.showInventory();
                    break;
                //If the user tries to pick up an item
                case 'p': case 'P':
                    //Check if the item has already been picked up
                    if (currentItem.getTakenStatus() == true)
                    {
                        System.out.println("There is no item here");
                    }
                    //If it hasnt, try to pick up
                    else if (currentItem.getTakenStatus() == false)
                    {
                        //If the dragon guarding it has been defeated
                        if (currentDragon.getDefeatStatus())
                        {
                            //Then take the item
                            hero.takeItem(currentItem);
                        }
                        //Otherwise tell player to defeat dragon first
                        else
                        {
                            System.out.println("You must first defeat the dragon!");
                        }
                    }
                    break;
                //If the user tries to rest
                case 'r': case 'R':
                    //Call the rest method
                    hero.rest();
                    break;
                //If the user tries to quit the game
                case 'q': case 'Q':
                    //Tell them they are quitting, and call the quitGame method
                    System.out.println("Quitting..");
                    hero.quitGame();
                    break;
                //DEFAULT CASE: handles incorrect input
                default:
                    System.out.println("Invalid command");
            }
            //After user has input a command, check if game is now completed
            hero.isGameComplete();
        }
        //Close user input stream
        in.close();
    }
}