package photoalbum.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads commands from a file and processes them.
 */
public class CommandFileReader {
  private PhotoalbumController controller;

  private int xmax;
  private int ymax;
  private String viewType;

  /**
   * Constructor for the CommandFileReader.
   * @param controller The photoalbum.controller to process the commands.
   */
  public CommandFileReader(PhotoalbumController controller) {
    this.controller = controller;
  }

  /**
   * Read the commands from the file and process them.
   * @param filename The name of the file to read the commands from.
   */
  public void readCommands(String filename) {
    try {
      // Read the commands from the file
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      // read the file line by line
      while (scanner.hasNextLine()) {
        String command = scanner.nextLine();
        // Process the command
        controller.processCommand(command);
      }
      // close the scanner
      scanner.close();
    } catch (FileNotFoundException e) {
      // Print an error message if the file is not found
      System.out.println("File not found.");
      e.printStackTrace();
    }
  }
}