/*Author: Eric Simich
  E-mail: ericsimich@gmail.com
  
  Worst case, this programs runs @ O(N)
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main 
{
    //container for all the integers, in file
    private static String[] data;
    //_intTmp is a container for the reversal of an integer
    private static int _intTmp, count;
    //MAX is the number of allowable iterations allowed before 
    //it is decided that an integer does not have a palindrome
    private static final int MAX=100;

    //Main default constructor
    public Main(final String[] input)
    {
        //check for null data values & init
        if(!input.equals(null))
        { Main.data=input; Main.count=0; Main._intTmp=0; }
        else 
        System.err.println("Data Null");
    }//Main::Main end
    
    public final void run()
    {
        int _palindrome;
        for(int i=0; i<data.length;i++)
        {
            //add each integer in data to its reversal
            //add() calls rev()
            _palindrome=add(Integer.parseInt(data[i]));

            //if count has not reached MAX, output the 
            //palindrome; otherwise, there isn't one.
            if(!(_palindrome==0) && !(count==MAX))
            {
            System.out.println(count+ " "+ _palindrome);
            count=0;
            }
            else
            {
            System.out.println(count+ " "+ 0);
            count=0;
            }//else:end
        }//for:end
    }//Main::run end

    //adds the i'th integer in data to its reversed integer:
    //worst case is O(N)
    private final int add(int x) //T(N)=n+(1/n)+1
    {
        //if x is 0, then don't do anymore computation
        if(x==0)
            return x;
        //if x equals its reversal, the palindrome has been found
        if(x==rev(x))
            return x;
       
        //keep adding and reversing
        else
        {
            //if MAX has been reached, STOP
             if (count==MAX)
             return 0;
            
             //increment the count
            count++;
            _intTmp=rev(x); //get the reversal
            
            //System.out.println(_intTmp);
            
            return add(x + _intTmp); //recursive call: keep adding
        }//else:end
    } //Main::add end
    
    //reverse the integer: worst case is O(N)
    private final int rev(int orig) 
    {
        int reversed=0;
        while(orig>0)
        {
            //adds the last digit from orig to reversed
            reversed=reversed*10+orig%10; 
            orig=orig/10; //gets rid of the last digit    
        }
        return reversed; //return the reversed integer  
    }//Main::final end

    public static void main(String[] args) throws FileNotFoundException 
    {
            if(args.length>0) //check the number of arguments 
            {

                //final long startTime = System.nanoTime(); 
                final Scanner cin = new Scanner(new File(args[0]));
                final StringBuilder numbers = new StringBuilder();
                final String separator=" ";
                final Main revAdd;

                //read the data from file
                while(cin.hasNext()) { numbers.append(cin.next()); numbers.append(separator);}

                //call Main's default constructor to init class variables
                revAdd=new Main(numbers.toString().split(separator));
                revAdd.run(); //perform computation

                //final long endTime = System.nanoTime()-startTime;
                //System.out.println("\n"+ endTime);

                cin.close(); //close input stream
                System.exit(0); //exit program without incident
            } //if:end
        System.exit(0); //if no specified arguments, exit the program cleanly
    }//main()::end
} //Main{}::end
