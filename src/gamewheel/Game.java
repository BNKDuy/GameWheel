/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewheel;

import java.util.ArrayList;

public class Game
{
  public static void play(GameWheel g)
  {
    ArrayList<Slice> arr = new ArrayList<Slice>();
    Slice result;
    int prize = 0;
    String color = "";
    boolean sameColor = true;
    for (int i = 0; i < 3; i++)
    {
      result = g.spinWheel();
      arr.add(result);
      
      if (i == 0) color = result.getColor();
      else 
      {
        if (!color.equals(result.getColor())) sameColor = false;
      }
      
      prize += result.getPrizeAmount();
    }
    
    if (sameColor) prize *= 2;
    
    System.out.println("Total prize money: $" + prize + "\n");
    
    for (int i = 0; i < 3; i++)
    {
      System.out.println("Spin " + (i + 1) + " - " + arr.get(i) );
    }
    
    if (sameColor)
    {
      System.out.print("Three " + color + "s = double your money!");
    }
  }
  
}

