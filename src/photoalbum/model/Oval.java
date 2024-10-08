package photoalbum.model;

/**
 * Represents a shape of oval.
 */
public class Oval extends Shape {
  private double Xradius;
  private double Yradius;

  /**
   * Constructs an oval with name, shape type,
   * given x-coordinate, y-coordinate, X-radius, Y-radius, and color.
   * @param name the name
   * @param type the shape type
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param Xradius the X-radius
   * @param Yradius the Y-radius
   * @param color the color
   */
  public Oval(String name, ShapeType type,
      double x, double y, double Xradius, double Yradius, Color color) {
    super(name, ShapeType.OVAL, x, y, color);
    this.Xradius = Xradius;
    this.Yradius = Yradius;
    // if X-radius or Y-radius is negative or 0, throw an exception
    if (Xradius <= 0 || Yradius <= 0) {
      throw new IllegalArgumentException("X-radius and Y-radius must be positive.");
    }
  }

  /**
   * Gets the X-radius of the oval.
   * @return the X-radius
   */
  public double getXradius() {
    return Xradius;
  }

  /**
   * Gets the Y-radius of the oval.
   * @return the Y-radius
   */
  public double getYradius() {
    return Yradius;
  }

  /**
   * Resizes X-radius.
   * @param newXradius the new X-radius
   */
  public void resizeXradius(double newXradius) {
    if (newXradius <= 0) {
      throw new IllegalArgumentException("X-radius must be positive.");
    }
    this.Xradius = newXradius;
  }

  /**
   * Resizes Y-radius.
   * @param newYradius the new Y-radius
   */
  public void resizeYradius(double newYradius) {
    if (newYradius <= 0) {
      throw new IllegalArgumentException("Y-radius must be positive.");
    }
    this.Yradius = newYradius;
  }

  /**
   * Make a string representation of oval.
   * @return a string representation of oval
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nType: oval\nCenter: (%.1f, %.1f),"
      + " X radius: %.1f, Y radius: %.1f, Color: (%.1f, %.1f, %.1f)\n",
      name, x, y, Xradius, Yradius, color.getR(), color.getG(), color.getB());
  }

  /**
   * Makes a copy of the oval.
   * @return a copy of the oval
   */
  @Override
  public IShape clone() {
    Color colorCopy = color.clone();
    Oval ovalCopy = new Oval(this.name, ShapeType.OVAL, this.x, this.y,
        this.Xradius, this.Yradius, colorCopy);
    return ovalCopy;
  }
}