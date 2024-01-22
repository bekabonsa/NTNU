package inharitance.abstraction.shape;

/**
 * @author rouhani
 *
 */
public class Circle extends Shape {
  private final double radius;

  /**
   * @param r
   */
  public Circle(double r) {
    this.radius = r;
  }

  public double getRadius() {
    return radius;
  }

  @Override
  public double calcArea() {
    return Math.PI * Math.sqrt(radius);
  }

  @Override
  public String toString() {
    return "Sirkel [radius=" + radius + ", areal=" + String.format("%.2f", calcArea()) + "]";
  }
}
