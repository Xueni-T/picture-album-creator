package photoalbum.model;

import java.util.List;

/**
 * Interface of a snapshot of the photo album.
 */
public interface ISnapshot {
  /**
   * Gets the snapshot ID.
   * @return the snapshot ID
   */
  String getSnapshotId();

  /**
   * Gets the timestamp.
   * @return the timestamp
   */
  String getTimestamp();

  /**
   * Gets the description of the snapshot.
   * @return the description
   */
  String getDescription();

  /**
   * Gets the list of shapes in the snapshot.
   * @return the list of shapes
   */
  List<IShape> getShapes();
}
