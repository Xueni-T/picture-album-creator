package photoalbum.model;

/**
 * Abstract class for any type of shape.
 */
public abstract class Shape implements IShape {
  protected String name;
  private ShapeType type;
  protected double x;
  protected double y;
  protected Color color;

  /**
   * Constructs a shape with a given x-coordinate, y-coordinate, color,
   * name, and shape type.
   * @param name the name
   * @param type the shape type
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param color the color
   */
  public Shape(String name, ShapeType type, double x, double y, Color color) {
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.color = color;
    // if name is null or empty string, throw an exception
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name must not be null or empty.");
    }
  }

  /**
   * Gets the name of the shape.
   * @return the name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the type of the shape.
   * @return the type of the shape
   */
  @Override
  public ShapeType getShapeType() {
    return type;
  }

  /**
   * Gets the x-coordinate of the shape.
   * @return the x-coordinate
   */
  @Override
  public double getX() {
    return x;
  }

  /**
   * Gets the y-coordinate of the shape.
   * @return the y-coordinate
   */
  @Override
  public double getY() {
    return y;
  }

  /**
   * Moves the shape to a new location.
   * @param newX the new x-coordinate
   * @param newY the new y-coordinate
   */
  @Override
  public void moveTo(double newX, double newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Gets the color of the shape.
   * @return the color
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Changes the color of the shape.
   * @param newR the new red value
   * @param newG the new green value
   * @param newB the new blue value
   */
  @Override
  public void changeColor(double newR, double newG, double newB) {
    if (newR < 0 || newR > 255 || newG < 0 || newG > 255 || newB < 0 || newB > 255) {
      throw new IllegalArgumentException("Color values must be in the range [0, 255].");
    }
    color.setR(newR);
    color.setG(newG);
    color.setB(newB);
  }

  /**
   * Creates a copy of the shape.
   * @return the copy
   */
  @Override
  public abstract IShape clone();
}
