/* This class contains the constructor method to import the Chambers file, 
*  methods to set the Dragon/Item found in that Chamber, and the method to print
*  a description of the current Chamber.
*/

//Author:   Matthew Rothery
//Date:     10/12/2019
//Email:    M.Rothery@liverpool.ac.uk
//All Rights Reserved

//Import necessary java classes
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Define Chamber class
public class Chamber
{
    //Declare class variable to store Chamber layout, dragon and item
    private char[] layout = new char[4];
    private Dragon dragon;
    private Item item;

    //Declare list to store all chamber layouts which are imported
    //Also declare the current chamber counter
    private static ArrayList<String> allChambers = new ArrayList<String>();
    private static int currentChamber = 0;

    //Chamber constructor method
    public Chamber()
    {
        //Define scanner object to import from Chamebr file
        Scanner input;

        //Try/Catch loop to handle Chambers import
        try
        {
            //Set new scanner object containing the file to import
            input = new Scanner(new File("chambers.txt"));           
        }
        //If file not found, print an error
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot find chambers file");
            return;
        }
        
        //While there are still lines in the input file
        while (input.hasNextLine())
        {
            //Add these lines to the list of all Chamber layouts
            allChambers.add(input.nextLine());
        }

        //Assign the Chambers layout to be the index of the list, 
        //then advance the index by 1 for the next Chamber to be created
        layout = allChambers.get(currentChamber).toCharArray();
        currentChamber++;

        //Close input stream
        input.close();
    }

    //Define describeChamber method
    public void describeChamber()
    {
        //Set dragon/item to be the one found in this chamber
        dragon = this.getDragon();
        item = this.getItem();

        //If there is a door in the North position, tell player
        if (layout[0] == '1')
        {
            System.out.println("There is a door going north");
        }
        //If there is a door in the East position, tell player
        if (layout[1] == '1')
        {
            System.out.println("There is a door going east");
        }
        //If there is a door in the South position, tell player
        if (layout[2] == '1')
        {
            System.out.println("There is a door going south");
        }
        //If there is a door in the West position, tell player
        if (layout[3] == '1')
        {
            System.out.println("There is a door going west");
        }  
        //If there is a dragon and it has not been defeated
        if (dragon != null && dragon.getDefeatStatus() != true)
        {
            //Print the name of the dragon to player
            System.out.println("The dragon " + dragon.getName() + " is here!");
        }
        //If there is an item and it has not been taken
        if (item != null && item.getTakenStatus() != true)
        {
            //Print the name of the item and it's value to the player
            System.out.println("There is a " + item.getName() + " here (value " + item.getValue() + ")");
        }
    }

    //Define getter to return Dragon object
    public Dragon getDragon()
    {
        return dragon;
    }

    //Define getter to return Item object
    public Item getItem()
    {
        return item;
    }

    //Define getter to return layout char array
    public char[] getLayout()
    {
        return layout;
    }

    //Define setter to set the item found in the chamber
    public void setItem(Item i)
    {
        item = i;
    }

    //Define setter to set the dragon found in the chamber
    public void setDragon(Dragon d)
    {
        dragon = d;
    }
}