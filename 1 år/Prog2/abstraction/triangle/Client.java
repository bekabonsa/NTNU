package inharitance.abstraction.triangle;

import static edu.ntnu.idatt2001.utility.Out.out;

import java.util.ArrayList;

/**
 * Client program for demonstrating multiple inharitance.
 *
 * @author Majid Rouhani
 *
 */
public class Client {

  /**
   * main method for testing.
   *
   * @param args - not in use
   */
  public static void main(String[] args) {

    ArrayList<Triangle> trekanter = new ArrayList<Triangle>();

    trekanter.add(new IsoscelesTriangle(10, 15));
    trekanter.add(new EquilateralTriangle(5));
    trekanter.add(new RightTriangle(7, 11));

    System.out.println(trekanter);
  }
}
