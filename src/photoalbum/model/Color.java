package photoalbum.model;

/**
 * Represents a color.
 */
public class Color {
  private double r;
  private double g;
  private double b;

  /**
   * Constructs a color with red, green, and blue values.
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   */
  public Color(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
    // if red, green, or blue is not in the range [0, 255], throw an exception
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Color values must be in the range [0, 255].");
    }
  }

  /**
   * Gets the red value of the color.
   * @return the red value
   */
  public double getR() {
    return this.r;
  }

  /**
   * Gets the green value of the color.
   * @return the green value
   */
  public double getG() {
    return this.g;
  }

  /**
   * Gets the blue value of the color.
   * @return the blue value
   */
  public double getB() {
    return this.b;
  }

  /**
   * Sets the red value of the color.
   * @param r the red value
   */
  public void setR(double r) {
    this.r = r;
  }

  /**
   * Sets the green value of the color.
   * @param g the green value
   */
  public void setG(double g) {
    this.g = g;
  }

  /**
   * Sets the blue value of the color.
   * @param b the blue value
   */
  public void setB(double b) {
    this.b = b;
  }

  /**
   * Clone the color.
   * @return the clone
   */
  public Color clone() {
    return new Color(this.r, this.g, this.b);
  }
}