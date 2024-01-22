package inharitance.abstraction.shape;

/**
 * @author rouhani
 *
 */
public class Triangle extends Shape {
  private final double grunnlinje;
  private final double hoyde;

  /**
   * @param grunnlinje
   * @param hoyde
   */
  public Triangle(double grunnlinje, double hoyde) {
    this.grunnlinje = grunnlinje;
    this.hoyde = hoyde;
  }

  public double getGrunnlinje() {
    return grunnlinje;
  }

  public double getHoyde() {
    return hoyde;
  }

  @Override
  public double calcArea() {
    return (grunnlinje * hoyde) / 2;
  }

  @Override
  public String toString() {
    return "Trekant [grunnlinje=" + grunnlinje + ", hoyde=" + hoyde + ", areal="
        + String.format("%.2f", calcArea()) + "]";
  }
}
