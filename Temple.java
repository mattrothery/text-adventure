/* This is the Temple class, which handles creation of a Temple object,
*  generation of Items and Dragons and placement of these randomly in Chambers.
*  There is also a getter method to return a Chamber at a given cooridnate in 
*  the temple.
*/

//Author:   Matthew Rothery
//Date:     10/12/2019
//Email:    M.Rothery@liverpool.ac.uk
//All Rights Reserved

//Import necessary list java classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

//Define Temple class
public class Temple
{
    //Set constants of the number of rows/cols of the temple
    public static final int TEMPLE_ROWS = 6;
    public static final int TEMPLE_COLS = 6;

    //Instantiate a temple object to store Chambers in a 2d array
    private Chamber[][] temple = new Chamber[TEMPLE_ROWS][TEMPLE_COLS];
    
    //Temple constructor method
    public Temple()
    {
        //For each row of the temple
        for (int i = 0; i < TEMPLE_ROWS; i++)
        {
            //And for each column of the temple
            for (int j = 0; j < TEMPLE_COLS; j++)
            {
                //Create a new Chamber at that coordinate
                temple[i][j] = new Chamber();                
            }
        }
    }

    //Define fillTemple method, taking a temple object as a parameter
    public void fillTemple(Temple t)
    {
        //Get the item object list and the list of item names
        LinkedList<Item> allItems = Item.getAllItems();
        ArrayList<String> itemNames = Item.getItemNames();

        //For each item name, create an item object and store in list
        for (int i = 0; i < itemNames.size(); i++)
        {
            Item item = new Item();
            allItems.add(item);
        }
        
        //Get the dragon object list and the list of dragon names
        LinkedList<Dragon> allDragons = Dragon.getAllDragons();
        ArrayList<String> dragonNames = Dragon.getDragonNames();
        
        //For each dragon name, create a dragon object and store in list
        for (int i = 0; i < dragonNames.size(); i++)
        {
            Dragon dragon = new Dragon();
            allDragons.add(dragon);
        }
        
        //Instantiate two list iterators on the item/dragon object lists
        ListIterator itemIter = allItems.listIterator();
        ListIterator dragonIter = allDragons.listIterator();

        //Start a do while loop
        do
        {
            //Generate a random (row,col) coordinate
            int row = (int) (Math.random() * Temple.TEMPLE_ROWS);
            int col = (int) (Math.random() * Temple.TEMPLE_COLS);

            //Retrieve the chamber at that coordinate
            Chamber c = t.getChamber(row, col);

            //If that chamber has no item or dragon in it
            if (c.getItem() == null && c.getDragon() == null)
            {
                //Then set the item and dragon at the iterator index to be 
                //located in that chamber
                c.setItem(allItems.get(itemIter.nextIndex()));
                c.setDragon(allDragons.get(dragonIter.nextIndex()));                
            }
            //Otherwise if the chamber is full, go back an item/dragon in list
            else
            {
                itemIter.previous();
                dragonIter.previous();
            }
            //Move to next item/dragon in list
            itemIter.next();
            dragonIter.next();
        }
        //Do this until there are no more items/dragons in the lists
        while (itemIter.hasNext() && dragonIter.hasNext());
    }

    //Define getter method to return the Chamber at a given row/col position
    public Chamber getChamber(int row, int col)
    {
        return temple[row][col];
    }
}