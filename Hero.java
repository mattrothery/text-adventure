/* This Hero class is a subclass of the Character class. It contains the 
*  constructor method which places the Hero in a random position in the Temple,
*  and overwrites the firepower to start at the minimum. Methods to move the 
*  player, take items, alter firepower of the Hero and to change/check the 
*  status of the game are found in this class
*/

//Author:   Matthew Rothery
//Date:     10/12/2019
//Email:    M.Rothery@liverpool.ac.uk
//All Rights Reserved

//Import LinkedList class and ListIterator class
import java.util.LinkedList;
import java.util.ListIterator;

//Define Hero class which extends Character class
public class Hero extends Character 
{
    //Define variable of pouch to store items, and game status variable
    private LinkedList<Item> pouch;
    private boolean gameComplete = false;

    //Hero constructor method
    public Hero()
    {
        //Set the Hero's firepower to be minimum. This is a design choice to 
        //overwrite the random firepower generation, as that can lead to a less
        //challenging game if it is generated to start with the maximum.
        firepower = MIN_FP;

        //Instantiate pouch LinkedList and generate a random starting position
        pouch = new LinkedList<Item>();         
        rowPosition = (int) (Math.random() * Temple.TEMPLE_ROWS);
        colPosition = (int) (Math.random() * Temple.TEMPLE_COLS);
    }

    //Define method to increase firepower of Hero
    public void incFirepower()
    {
        //As long as the Hero has a firepower below maximum
        if (firepower < MAX_FP)
        {
            //Then increment by 1
            firepower++;
        }
        //Otherwise tell player that it is already at the maximum
        else
        {
            System.out.println("You are at the maximum firepower!");
        }
    }

    //Define method to decrease firepower of Hero
    public void decFirepower()
    {
        //As long as the Hero has a firepower above minimum
        if (firepower > MIN_FP)
        {
            //Decrement by 1
            firepower--;
        }
        //Otherwise tell player that it is already at the minimum
        else
        {
            System.out.println("You are at the minimum firepower!");
        }
    }

    //Define rest method
    public void rest()
    {
        //As long as the Hero has a firepower below maximum
        if (firepower < MAX_FP)
        {
            //Call increase firepower method and tell user the current firepower
            incFirepower();
            System.out.println("Resting... Your firepower is now " + getFirepower());
        }
        //Otherwise tell player that it is already at the maximum
        else
        {
            System.out.println("You are already at the maximum firepower.");
        }
    }

    //Define showInventory method
    public void showInventory()
    {
        //Tell user that items in pouch will be listed
        System.out.println("Here are the items in your pouch:");

        //Set list iterator object using the pouch
        ListIterator listIter = pouch.listIterator();

        //Initialise a total value of 0
        int totalValue = 0;

        //If the pouch is empty, tell player
        if (pouch.size() == 0)
        {
            System.out.println("You have not found any items yet.");
        }
        //Otherwise iterate through pouch
        else
        {
            //Initialise a counter to be displayed next to each item
            int count = 1;

            //While there is items left in the pouch
            while (listIter.hasNext())
            {
                //Get the item at that location in the pouch
                Item i = pouch.get(listIter.nextIndex());

                //Print its name and value, after the count of items
                System.out.println(count + ": " + i.getName() + " (value " + i.getValue() + ")");
                
                //Increment count of items and add the items value to total
                count++;
                totalValue += i.getValue();

                //Move to next item
                listIter.next();
            }
        }
        //Print out the total value of all items in the pouch
        System.out.println("Total value of all items is " + totalValue);
    }

    //Define the takeItem method which takes an item object as a parameter
    public void takeItem(Item i) 
    {
        //If pouch is empty, add item
        if (pouch.size() == 0) 
        {
            pouch.add(i);
        } 
        //If the value of the first item is greater than new items value
        else if (pouch.get(0).getValue() > i.getValue()) 
        {
            //Add new item to first in pouch
            pouch.addFirst(i);
        } 
        //If the value of the last item (size - 1) is less than new items value
        else if (pouch.get(pouch.size() - 1).getValue() < i.getValue()) 
        {
            //Add new item to last in pouch
            pouch.addLast(i);
        } 
        //Otherwise, iterate through each item in pouch
        else
        {
            int j = 0;
            //While current items value is less than new items value
            while (pouch.get(j).getValue() < i.getValue())
            {
                //Increment - move to next item
                j++;
            }
            //When value is not less than new items value, add new item here
            pouch.add(j, i);
        }

        //Set the item to be 'taken'
        i.setTakenStatus();

        //Prompt user that item has been taken
        System.out.println("OK, item taken.");
    }

    //Define method to print players current position in the temple
    public void printPosition()
    {
        System.out.println("\nYou are in chamber (" + rowPosition + ", " + colPosition + ")");
    }

    //Define method to move player north by decrementing row position int
    public void goNorth()
    {
        rowPosition--;
    }
    //Define method to move player east by incrementing col position int
    public void goEast()
    {
        colPosition++;
    }
    //Define method to move player south by incrementing row position int
    public void goSouth()
    {
        rowPosition++;
    }
    //Define method to move player west by decrementing col position int
    public void goWest()
    {
        colPosition--;
    }

    //Define method to check if the game is complete
    public void isGameComplete()
    {
        //If the size of the pouch is equal to the number of all item names
        if (pouch.size() == Item.getItemNames().size())
        {
            //Set status of game to be complete, tell player and print inventory
            gameComplete = true;
            System.out.println("\nYou have completed the game!");
            showInventory();
        }
    }

    //Define getter to return status of game as a boolean
    public boolean getGameStatus()
    {
        return gameComplete;
    }
    
    //Define setter to set the status of the game to be completed
    public void quitGame()
    {
        gameComplete = true;
    }
}