package photoalbum.model;

import java.util.List;

/**
 * Interface of a photo album.
 */
public interface IPhotoalbum {
  /**
   * Creates a new rectangle and adds it to the photo album.
   * @param name the name of the rectangle
   * @param type the type of the shape
   * @param x the x-coordinate of the rectangle
   * @param y the y-coordinate of the rectangle
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @param color the color of the rectangle
   */
  void createRectangle(String name, ShapeType type, double x, double y,
      double width, double height, Color color);

  /**
   * Creates a new oval and adds it to the photo album.
   * @param name the name of the oval
   * @param type the type of the shape
   * @param x the x-coordinate of the oval
   * @param y the y-coordinate of the oval
   * @param xRadius the x-radius of the oval
   * @param yRadius the y-radius of the oval
   * @param color the color of the oval
   */
  void createOval(String name, ShapeType type, double x, double y,
      double xRadius, double yRadius, Color color);

  /**
   * Removes a shape from the photo album.
   * @param shapeName the name of the shape to remove
   */
  void removeShape(String shapeName);

  /**
   * Clears all shapes from the photo album.
   */
  void clearShapes();

  /**
   * Clears all snapshots from the photo album.
   */
  void clearSnapshots();

  /**
   * Resets the photo album to its initial state.
   */
  void reset();

  /**
   * Moves a shape to a new location.
   * @param name the name of the shape
   * @param newX the new x-coordinate
   * @param newY the new y-coordinate
   */
  void moveShape(String name, double newX, double newY);

  /**
   * Resizes a rectangle to a new size.
   * @param name the name of the rectangle
   * @param newWidth the new width
   * @param newHeight the new height
   */
  void resizeRectangle(String name, double newWidth, double newHeight);

  /**
   * Resizes an oval to a new size.
   * @param name the name of the oval
   * @param Xradius the new x-radius
   * @param Yradius the new y-radius
   */
  void resizeOval(String name, double Xradius, double Yradius);

  /**
   * Changes the color of a shape.
   * @param name the name of the shape
   * @param newR the new red value
   * @param newG the new green value
   * @param newB the new blue value
   */
  void changeShapeColor(String name, double newR, double newG, double newB);

  /**
   * Takes a snapshot of the current state of the photo album.
   * @param description the description of the snapshot
   */
  void takeSnapshot(String description);

  /**
   * Gets the snapshot IDs in the photo album.
   * @return the snapshot IDs
   */
  List<String> getSnapshotIDs();

  /**
   * Gets the snapshots in the photo album.
   * @return the snapshots
   */
  List<ISnapshot> getSnapshots();

  /**
   * Gets the shapes in the photo album.
   * @return the shapes
   */
  List<IShape> getShapes();

  /**
   * Gets the shape in the photo album.
   * @return the shape
   */
  public IShape getShape(String name);
}
