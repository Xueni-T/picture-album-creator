import photoalbum.controller.IPhotoalbumController;
import photoalbum.controller.PhotoalbumController;

import java.io.IOException;

/**
 * The main class for the photo album.
 */
public class PhotoAlbumMain {
  /**
   * The main method for the photo album.
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    // Set the default values
    String inputFile = null;
    String outputFile = null;
    String viewType = null;
    int xmax = 1000;
    int ymax = 1000;

    // Parse the command line arguments
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          inputFile = args[++i];
          break;
        case "-out":
          outputFile = args[++i];
          break;
        case "-view":
        case "-v":
          viewType = args[++i];
          break;
        default:
          if (xmax == 1000) {
            xmax = Integer.parseInt(args[i]);
          } else if (ymax == 1000) {
            ymax = Integer.parseInt(args[i]);
          }
          break;
      }
    }

    // Check for mandatory arguments
    if (inputFile == null || viewType == null) {
      System.out.println("Input file and view type are mandatory.");
      return;
    }

    // Check for web view without output file
    if ("web".equals(viewType) && outputFile == null) {
      System.out.println("Output file is mandatory for web view.");
      return;
    }

    // Create the model and controller
    IPhotoalbumController controller = PhotoalbumController.getInstance();

    // Run the controller with the input file
    try {
      controller.run(inputFile, viewType, xmax, ymax);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}