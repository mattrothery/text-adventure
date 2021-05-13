/* This Item class contains the constructor used to create Item objects, name
*  them and set their value to be a random integer between 1 and 20. There are
*  getter methods to return information on the Item, and setter methods to set 
*  the name of an item and change its status to be taken.
*/

//Author:   Matthew Rothery
//Date:     10/12/2019
//Email:    M.Rothery@liverpool.ac.uk
//All Rights Reserved

//Import necessary java classes
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;

//Define Item class
public class Item
{
    //Define variables to store name, value and taken status of Item
    private String name;
    private int value;
    private boolean isTaken = false;

    //Declare usedNames index integer as 0, and instantiate lists to store
    //all item objects and all item names
    private static int usedNames = 0;
    private static ArrayList<String> itemNames = new ArrayList<String>();
    private static LinkedList<Item> allItems = new LinkedList<Item>();

    //Item constructor method
    public Item()
    {
        //Generate a random item value between 1 and 20, set its name 
        //and store Item in list
        value = (int) ((Math.random() * 20) + 1);
        setName();
        allItems.add(this);
    }

    //Define getter to return value of item as an int
    public int getValue()
    {
        return value;
    }

    //Define getter method to return name of item as a string
    public String getName()
    {
        return name;
    }

    //Define getter method to return taken status of Item as a boolean
    public boolean getTakenStatus()
    {
        return isTaken;
    }

    //Define getter method to return list of all item objects
    public static LinkedList<Item> getAllItems()
    {
        return allItems;
    }

    //Define getter method to return list of all item names
    public static ArrayList<String> getItemNames()
    {
        return itemNames;
    }

    //Define setter method to set Item name
    public void setName()
    {
        //Set name of item to index of names list, then increment this index
        name = itemNames.get(usedNames);
        usedNames++;
    }

    //Define setter method to set the status of an item to be taken
    public void setTakenStatus()
    {
        isTaken = true;
    }    
}