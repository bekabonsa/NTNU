package inharitance.abstraction.shape;

/**
 * @author rouhani
 *
 */
public class Sqaure extends Shape {
  private final double hoyde;
  private final double bredde;

  /**
   * @param hoyde
   * @param bredde
   */
  public Sqaure(double hoyde, double bredde) {
    this.hoyde = hoyde;
    this.bredde = bredde;
  }

  public double getHoyde() {
    return hoyde;
  }

  public double getBredde() {
    return bredde;
  }

  /** Calculate area.
   *
   *  {@inheritDoc}
   */
  @Override
  public double calcArea() {
    return hoyde * bredde;
  }

  @Override
  public String toString() {
    return "Kvadrat [hoyde=" + hoyde + ", bredde=" + bredde + ", areal="
        + String.format("%.2f", calcArea()) + "]";
  }

}
