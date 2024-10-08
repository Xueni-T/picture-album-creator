package photoalbum.controller;

import java.io.IOException;

/**
 * The interface for the photoalbum controller.
 */
public interface IPhotoalbumController {
  /**
   * Run the controller.
   * @param filename The name of the file to read the commands from.
   * @param viewType The type of view to display.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  void run(String filename, String viewType, int xmax, int ymax) throws IOException;
}