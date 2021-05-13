/* This Dragon class is a subclass of the Character class. It contains the 
*  constructor method which names the Dragon and adds it to a list of all
*  Dragons. There are setter methods to change the name of the dragon and set
*  its status as being defeated, and getter methods to return a dragon's name
*  and defeated status.
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

//Define Dragon class which extends Character class
public class Dragon extends Character
{
    //Define variables to store name and defeat status of Dragon
    private String name;
    private boolean isDefeated = false;

    //Declare usedNames index integer as 0, and instantiate lists to store
    //all dragon objects and all dragon names
    private static int usedNames = 0;
    private static ArrayList<String> dragonNames = new ArrayList<String>();
    private static LinkedList<Dragon> allDragons = new LinkedList<Dragon>();

    //Dragon constructor method
    public Dragon()
    {
        //Call dragon name setter method, and add this dragon to list
        setName();
        allDragons.add(this);
    }

    //Define getter method to return the name of the dragon as a string
    public String getName()
    {
        return name;
    }

    //Define getter method to return the if a dragon is defeated as a boolean
    public Boolean getDefeatStatus()
    {
        return isDefeated;
    }

    //Define getter method to return the LinkedList of all dragon objects
    public static LinkedList<Dragon> getAllDragons()
    {
        return allDragons;
    }

    //Define getter method to return the ArrayList of all dragon names
    public static ArrayList<String> getDragonNames()
    {
        return dragonNames;
    }

    //Define setter method to set the name of the dragon
    public void setName()
    {
        //Set name of dragon to index of names list, then increment this index
        name = dragonNames.get(usedNames);
        usedNames++;
    }

    //Define setter to set dragon to be defeated
    public void setDefeatStatus()
    {
        isDefeated = true;
    }
}