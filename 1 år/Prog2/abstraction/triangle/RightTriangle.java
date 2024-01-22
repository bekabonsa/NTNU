package inharitance.abstraction.triangle;

/**
 * RightTriangle class overriding calcArea-method.
 *
 * @author "Majid Rouhani"
 *
 */
public class RightTriangle extends Triangle {

  public RightTriangle(double g, double h) {
    super(g, h, Math.sqrt(Math.pow(g, 2) + Math.pow(h, 2)));
  }

  @Override
  public double calcArea() {
    return Math.round(this.side1 * this.side2 / 2);
  }

  @Override
  public String toString() {
    return "RettvinkletTrekant [g=" + this.side1 + ", h=" + this.side2 + ", getAreal()="
        + calcArea() + ", getOmkrets()=" + calcCircumference() + "]";
  }
}
