package photoalbum.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a snapshot of the photo album.
 */
public class Snapshot implements ISnapshot {
  private String snapshotId;
  private String timestamp;
  private String description;
  private List<IShape> shapes;
  
  /**
   * Constructs a new snapshot with the given snapshot ID, timestamp, description, and shapes.
   * 
   * @param snapshotId the snapshot ID
   * @param timestamp the timestamp
   * @param description the description
   * @param shapes the shapes
   */
  public Snapshot(String snapshotId, String timestamp, String description, List<IShape> shapes) {
    this.snapshotId = snapshotId;
    this.timestamp = timestamp;
    this.description = description;
    this.shapes = new ArrayList<>(shapes);
    // copy the shapes to prevent modification of the original list
    deepCopy(shapes, this.shapes);
  }

  /**
   * Gets the snapshot ID in the format yyyy-MM-dd'T'HH:mm:ss.SSSSSS.
   * @return the snapshot ID
   */
  @Override
  public String getSnapshotId() {
    return snapshotId;
  }

  /**
   * Gets the timestamp.
   * @return the timestamp
   */
  @Override
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Gets the description of the snapshot.
   * @return the description
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Gets the list of shapes in the snapshot.
   * @return the list of shapes
   */
  @Override
  public List<IShape> getShapes() {
    return this.shapes;
  }

  /**
   * Makes a deep copy of the snapshot.
   * @param src the source snapshot
   * @param dst the destination snapshot
   */
  public static void deepCopy(List<IShape> src, List<IShape> dst) {
    dst.clear();
    for (IShape shape : src) {
      dst.add(shape.clone());
    }
  }

  /**
   * Makes a string representation of the snapshot.
   * @return a string representation of the snapshot
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    // Append snapshot ID, timestamp, and description
    sb.append("Snapshot ID: ").append(snapshotId).append("\n");
    sb.append("Timestamp: ").append(timestamp).append("\n");
    sb.append("Description: ").append(description).append("\n");
    sb.append("Shape Information:\n");
    // Append information of each shape
    for (IShape shape : shapes) {
      sb.append(shape.toString()).append("\n");
    }
    return sb.toString();
  }
}