package photoalbum.model;

/**
 * Represents a shape of rectangle.
 */
public class Rectangle extends Shape {
  private double width;
  private double height;

  /**
   * Constructs a rectangle with name, shape type,
   * x-coordinate, y-coordinate, width, height, and color.
   * @param name the name
   * @param type the shape type
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param width the width
   * @param height the height
   * @param color the color
   */
  public Rectangle(String name, ShapeType type,
      double x, double y, double width, double height, Color color) {
    super(name, ShapeType.RECTANGLE, x, y, color);
    this.width = width;
    this.height = height;
    // if width or height is negative or 0, throw an exception
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
  }

  /**
   * Gets the width of the rectangle.
   * @return the width
   */
  public double getWidth() {
    return width;
  }

  /**
   * Gets the height of the rectangle.
   * @return the height
   */
  public double getHeight() {
    return height;
  }

  /**
   * Resizes width.
   * @param newWidth the new width
   */
  public void resizeWidth(double newWidth) {
    if (newWidth <= 0) {
      throw new IllegalArgumentException("Width must be positive.");   
    }
    this.width = newWidth;
  }

  /**
   * Resizes height.
   * @param newHeight the new height
   */
  public void resizeHeight(double newHeight) {
    if (newHeight <= 0) {
      throw new IllegalArgumentException("Height must be positive.");
    }
    this.height = newHeight;
  }

  /**
   * Make a string representation of rectangle.
   * @return a string representation of rectangle
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nType: rectangle\nMin corner: (%.1f, %.1f),"
      + " Width: %.1f, Height: %.1f, Color: (%.1f, %.1f, %.1f)\n",
      name, x, y, width, height, color.getR(), color.getG(), color.getB());
  }

  /**
   * Copies the rectangle.
   * @return a copy of the rectangle
   */
  @Override
  public IShape clone() {
    Color colorCopy = color.clone();
    Rectangle rectangleCopy = new Rectangle(this.name, ShapeType.RECTANGLE,
        this.x, this.y, this.width, this.height, colorCopy);
    return rectangleCopy;
  }
}
