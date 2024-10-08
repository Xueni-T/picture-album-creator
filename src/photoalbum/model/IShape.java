package photoalbum.model;

/**
 * Interface for any type of shape.
 */
public interface IShape {
  /**
   * Gets the name of the shape.
   * @return the name
   */
  String getName();

  /**
   * Gets the type of the shape.
   * @return the type of the shape
   */
  ShapeType getShapeType();

  /**
   * Gets the x-coordinate of the shape.
   * @return the x-coordinate
   */
  double getX();

  /**
   * Gets the y-coordinate of the shape.
   * @return the y-coordinate
   */
  double getY();

  /**
   * Moves the shape to a new location.
   * @param newX the new x-coordinate
   * @param newY the new y-coordinate
   */
  void moveTo(double newX, double newY);

  /**
   * Gets the color of the shape.
   * @return the color
   */
  Color getColor();

  /**
   * Changes the color of the shape.
   * @param r the red value
   * @param b the blue value
   * @param g the green value
   */
  void changeColor(double r, double g, double b);

  /**
   * Creates a copy of the shape.
   * @return the copy
   */
  IShape clone();
}
