package inharitance.abstraction.triangle;


/**
 * Triangle.
 *
 * @author "Majid Rouhani"
 *
 */
public abstract class Triangle {
  protected final double side1;
  protected final double side2;
  protected final double side3;

  /**
   * Triangle constructor.
   *
   * @param side1 - length of side 1
   * @param side2 - length of side 2
   * @param side3 - length of side 3
   */
  public Triangle(double side1, double side2, double side3) {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }

  public abstract double calcArea();

  public double calcCircumference() {
    return this.side1 + this.side2 + this.side3;
  }

  @Override
  public String toString() {
    return "Trekant [side1=" + side1 + ", side2=" + side2 + ", side3=" + side3 + "]";
  }
}
