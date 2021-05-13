/* This class is the Superclass of Hero and Dragon, as they both share the 
*  firepower variable, position variables and have the same firepower range.
*  The constructor method is here along with the method to fight Heros/Dragons.
*/

//Author:   Matthew Rothery
//Date:     10/12/2019
//Email:    M.Rothery@liverpool.ac.uk
//All Rights Reserved

//Import Random class used for random firepower generation
import java.util.Random;

//Define the Character class
public class Character
{
    //Protected variables to be inherited by subclasses
    protected int firepower;
    protected int rowPosition;
    protected int colPosition;

    //Constants of maximum/minimum firepower
    protected final int MAX_FP = 10;
    protected final int MIN_FP = 5;
    
    //Character constructor method 
    public Character()
    {
        //Generate a random firepower in the range of min - max firepower
        Random rng = new Random();
        firepower = rng.nextInt((MAX_FP - MIN_FP) + 1) + MIN_FP;
    }

    //Define fight method, taking a Hero & Dragon object as parameters
    public int fight(Hero hero, Dragon dragon)
    {
        //Tell user that fight is starting, and get firepower of Hero/Dragon
        System.out.println("Preparing to fight..");
        int h = hero.getFirepower();
        int d = dragon.getFirepower();

        //Define winner variable. Method returns an int, stored here
        int winner;

        //If the firepower of Hero > Dragon
        if (h > d)
        {
            //Increase Hero's firepower, set Dragon to be defeated, return 1
            hero.incFirepower();
            dragon.setDefeatStatus();
            winner = 1;
        } 
        //Otherwise if firepower of Dragon > Hero
        else if (d > h)
        {
            //Decrease Hero's firepower, return 0
            hero.decFirepower();
            winner = 0;
        }
        //Otherwise a draw
        else
        {
            //Set winner to be a random int between 0 and 1
            winner = (int) (Math.random() * 2);
            //If int is 1, Hero wins, so increase firepower/set Dragon defeat
            if (winner == 1)
            {
                hero.incFirepower();
                dragon.setDefeatStatus();
            }
            //Otherwise int is 0, Hero lost, so decrease firepower
            else
            {
                hero.decFirepower();
            }
        }
        //Return winner int
        return winner;
    }

    //Define getter to return firepower int
    public int getFirepower()
    {
        return firepower;
    }

    //Define getter to return row position as an int
    public int getRow()
    {
        return rowPosition;
    }

    //Define getter to return column position as an int
    public int getCol()
    {
        return colPosition;
    }
}