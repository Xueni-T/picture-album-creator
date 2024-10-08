package photoalbum.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the photoalbum.model for a photo album.
 */
public class PhotoalbumModel implements IPhotoalbum {
  private static IPhotoalbum INSTANCE = new PhotoalbumModel();
  private List<IShape> shapes; // List of shapes in the photo album
  private List<ISnapshot> snapshots; // List of snapshots in the photo album
  private List<String> snapshotIDs; // List of IDs of snapshots in the photo album
  private Set<String> shapeNames; // Set of names of shapes in the photo album

  /**
   * Constructs a new photo album.
   */
  public PhotoalbumModel() {
    this.shapes = new ArrayList<>();
    this.snapshots = new ArrayList<>();
    this.snapshotIDs = new ArrayList<>();
    this.shapeNames = new HashSet<>();
  }
  
  /**
   * Gets the instance of the photo album.
   * @return the photo album instance
   */
  public static IPhotoalbum getInstance() {
    return INSTANCE;
  }

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
  public void createRectangle(String name, ShapeType type, double x, double y,
      double width, double height, Color color) {
    if (shapeNames.contains(name)) {
      throw new IllegalArgumentException("A shape with the name " + name + " already exists");
    }
    IShape rectangle = new Rectangle(name, ShapeType.RECTANGLE, x, y, width, height, color);
    shapes.add(rectangle);
    shapeNames.add(name);
  }

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
  public void createOval(String name, ShapeType type, double x, double y,
      double xRadius, double yRadius, Color color) {
    if (shapeNames.contains(name)) {
      throw new IllegalArgumentException("A shape with the name " + name + " already exists");
    }
    IShape oval = new Oval(name, ShapeType.OVAL, x, y, xRadius, yRadius, color);
    shapes.add(oval);
    shapeNames.add(name);
  }

  /**
   * Removes a shape from the photo album.
   * @param shapeName the name of the shape to remove
   */
  @Override
  public void removeShape(String shapeName) {
    for (IShape shape : shapes) {
      // if the name of the shape matches the given name, remove the shape
      if (shape.getName().equals(shapeName)) {
        shapes.remove(shape);
        shapeNames.remove(shapeName);
        break;
      }
    }
  }

  /**
   * Clears all shapes from the photo album.
   */
  @Override
  public void clearShapes() {
    shapes.clear();
  }

  /**
   * Clears all snapshots from the photo album.
   */
  @Override
  public void clearSnapshots() {
    snapshots.clear();
  }

  /**
   * Resets the photo album by clearing all shapes and snapshots.
   */
  @Override
  public void reset() {
    shapes.clear();
    snapshots.clear();
  }

  /**
   * Moves a shape to a new position.
   * @param shapeName the name of the shape to move
   * @param newX the new x-coordinate
   * @param newY the new y-coordinate
   */
  @Override
  public void moveShape(String shapeName, double newX, double newY) {
    for (IShape shape : shapes) {
      // if the name of the shape matches the given name, move the shape
      if (shape.getName().equals(shapeName)) {
        shape.moveTo(newX, newY);
        break;
      }
    }
  }

  /**
   * Changes the color of a shape.
   * @param shapeName the name of the shape to change color
   * @param newR the new red value
   * @param newG the new green value
   * @param newB the new blue value
   */
  @Override
  public void changeShapeColor(String shapeName, double newR, double newG, double newB) {
    for (IShape shape : shapes) {
      // if the name of the shape matches the given name, change the color
      if (shape.getName().equals(shapeName)) {
        shape.changeColor(newR, newG, newB);
        break;
      }
    }
  }

  /**
   * Resizes a rectangle to a new width and height.
   * @param shapeName the name of the rectangle
   * @param newWidth the new width
   * @param newHeight the new height
   */
  @Override
  public void resizeRectangle(String shapeName, double newWidth, double newHeight) {
    for (IShape shape : shapes) {
      // if the name of the shape matches the given name, resize the rectangle
      if (shape.getName().equals(shapeName)) {
        ((Rectangle) shape).resizeWidth(newWidth);
        ((Rectangle) shape).resizeHeight(newHeight);
        break;
      }
    }
  }

  /**
   * Resizes an oval to a new x-radius and y-radius.
   * @param shapeName the name of the oval
   * @param newXRadius the new x-radius
   * @param newYRadius the new y-radius
   */
  @Override
  public void resizeOval(String shapeName, double newXRadius, double newYRadius) {
    for (IShape shape : shapes) {
      // if the name of the shape matches the given name, resize the oval
      if (shape.getName().equals(shapeName)) {
        ((Oval) shape).resizeXradius(newXRadius);
        ((Oval) shape).resizeYradius(newYRadius);
        break;
      }
    }
  }

  /**
   * Takes a snapshot of the current state of the photo album.
   * @param description the description of the snapshot
   */
  @Override
  public void takeSnapshot(String description) {
    List<IShape> snapshotShapes = new ArrayList<>(shapes);
    LocalDateTime timestamp = LocalDateTime.now();
    String newSnapshotId = timestamp.toString();
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedTimestamp = timestamp.format(outputFormatter);
    Snapshot newSnapshot = new Snapshot(newSnapshotId, formattedTimestamp,
            description, snapshotShapes);
    snapshots.add(newSnapshot);
    snapshotIDs.add(newSnapshotId);
  }


  /**
   * Gets the IDs of snapshots in the photo album.
   * @return the list of snapshot IDs
   */
  @Override
  public List<String> getSnapshotIDs() {
    return snapshotIDs;
  }
  
  /**
   * Gets the snapshots in the photo album.
   * @return the list of snapshots
   */
  @Override
  public List<ISnapshot> getSnapshots() {
    return snapshots;
  }
  
  /**
   * Gets the shapes in the photo album.
   * @return the list of shapes
   */
  @Override
  public List<IShape> getShapes() {
    return shapes;
  }

  /**
   * Gets the shape in the photo album.
   * @return the shape
   */
  @Override
  public IShape getShape(String name) {
    if (shapeNames.contains(name)) {
      for (IShape shape : shapes) {
        if (shape.getName().equals(name)) {
          return shape;
        }
      }
    }
    return null;
  }
}
