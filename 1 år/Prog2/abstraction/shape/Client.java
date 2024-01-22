package inharitance.abstraction.shape;

import static edu.ntnu.idatt2001.utility.Out.out;

/**
 * @author rouhani
 *
 */
public class Client {

  public static void main(String[] args) {
    Shape[] shapes = { new Sqaure(5.2, 3.5), new Circle(2.1), new Triangle(5, 1.3) };

    out("\nUtskrift 1");
    out(shapes);

    out("\nUtskrift 2");
    for (Shape shape : shapes) {
      out("Areal av " + shape.getClass().getSimpleName() + " er "
          + String.format("%.2f", shape.calcArea()));
    }
  }
}
