/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewheel;
import java.util.ArrayList;

public class GameWheel
{
  private ArrayList<Slice> slices; // List of slices making up the wheel
  private int currentPos;   // Position of currently selected slice on wheel


  /* Returns string representation of GameWheel with each numbered slice
   * on a new line
   */
  public String toString()
  {
    String out = "";
    for (int i = 0; i < slices.size(); i++)
    {
      out+= i + " - " + slices.get(i) + "\n";
    }
    
    return out;
  }


  /* Randomizes the positions of the slices that are in the wheel, but without
   * changing the pattern of the colors
   */
  public void scramble()
  {
    ArrayList<Slice> black = new ArrayList<Slice>();
    ArrayList<Slice> blue = new ArrayList<Slice>();
    ArrayList<Slice> red = new ArrayList<Slice>();
    
    for (int i = 0; i < slices.size(); i++)
    {
      if ((i % 5) == 0) 
      {
        black.add((int) (Math.random()*(black.size() + 1)),slices.get(i));
      }
      else if ((i % 2) == 0)
      {
        blue.add((int) (Math.random()*(blue.size() + 1)),slices.get(i));
      }
      
      else 
      {
        red.add((int) (Math.random()*(red.size() + 1)),slices.get(i));
      }
    }
    
    combine(black, blue, red);
  }
  
  /* Sorts the positions of the slices that are in the wheel by prize amount,
   * but without changing the pattern of the colors.
   */
  public void sort()
  {
    ArrayList<Slice> black = new ArrayList<Slice>();
    ArrayList<Slice> blue = new ArrayList<Slice>();
    ArrayList<Slice> red = new ArrayList<Slice>();
    
    
    for (int i = 0; i < slices.size(); i++)
    {
      if ((i % 5) == 0) 
      {
        black.add(slices.get(i));
      }
      else if ((i % 2) == 0) 
      {
        blue.add(slices.get(i));
      }
      else 
      {
        red.add(slices.get(i));
      }
      
      
    }
    arrSort(black);
    arrSort(blue);
    arrSort(red);
    
    combine(black, blue, red);
  }
  
  public void arrSort(ArrayList<Slice> list)
  {
    int minIndex; 
    Slice temp;
    
    for (int i = 0; i < list.size() -1; i++)
    {
      minIndex = i;
      for (int j = i + 1; j < list.size(); j++)
      {
        if (list.get(minIndex).getPrizeAmount() > list.get(j).getPrizeAmount())
        {
          minIndex = j;
        }
      }
      
      temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
    
  }
  
  public void combine(ArrayList<Slice> black, ArrayList<Slice> blue, ArrayList<Slice> red)
  {
    for (int t = 0; t < slices.size(); t++)
    {
      if ((t % 5) == 0) slices.set(t, black.remove(0));
      else if ((t % 2) == 0) slices.set(t, blue.remove(0));
      else slices.set(t, red.remove(0));
    }
  }
  
  /* COMPLETED METHODS - YOU DO NOT NEED TO CHANGE THESE */

  /* Creates a wheel with 20 preset slices
   */
  public GameWheel()
  {
    this(getStandardPrizes());
  }

  /* Creates a wheel with 20 slices, using values from array parameter
   */
  public GameWheel(int[] prizes)
  {
    currentPos = 0;
    slices = new ArrayList<Slice>();
    for(int i = 0; i < 20; i++){
      int pa = 0;
      String col = "blue";
      if(i < prizes.length)
        pa = prizes[i];
      if (i%5 == 0)
        col = "black";
      else if (i%2 == 1)
        col = "red";
      slices.add(new Slice(col, pa));
    }
  }

  /* Spins the wheel by so that a different slice is selected. Returns that
   * slice (Note: the 10 slices following the current slice are more likely to
   * be returned than the other 10).
   */
  public Slice spinWheel()
  {
    //spin power between range of 1-50 (inclusive)
    int power = (int)(Math.random()*50 + 1);
    int newPos = (currentPos + power) % slices.size();
    currentPos = newPos;
    return slices.get(currentPos);
  }

  public Slice getSlice(int i){
    int sliceNum = i;
    if(i < 0 || i > 19)
      sliceNum = 0;
    return slices.get(sliceNum);
  }

  // Makes an array with a standard list of prizes
  private static int[] getStandardPrizes()
  {
    int[] arr = new int[20];
    for (int i=0; i < 20; i++)
    {
      if (i%5 == 0)
        arr[i] = i*1000;
      else if (i%2 == 1)
        arr[i] = i*100;
      else
        arr[i] = i*200;
    }
    return arr;
  }
}
