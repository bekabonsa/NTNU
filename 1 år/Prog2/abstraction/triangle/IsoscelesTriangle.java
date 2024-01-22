package inharitance.abstraction.triangle;

/**
 * IsoscelesTriangle class overriding calcArea-method.
 *
 * @author "Majid Rouhani"
 *
 */
public class IsoscelesTriangle extends Triangle {
  public IsoscelesTriangle(double g, double s) {
    super(g, s, s);
  }

  @Override
  public double calcArea() {
    double h = Math.sqrt(Math.pow(this.side2, 2) - Math.pow(this.side1, 2) / 4);
    return Math.round(h * this.side1 / 4);
  }

  @Override
  public String toString() {
    return "LikebenetTrekant [g=" + this.side1 + ", s=" + this.side2 + ", getAreal()=" + calcArea()
        + ", getOmkrets()=" + calcCircumference() + "]";
  }
}
