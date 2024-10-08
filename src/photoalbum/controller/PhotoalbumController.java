package photoalbum.controller;

import photoalbum.model.Color;
import photoalbum.model.IPhotoalbum;
import photoalbum.model.IShape;
import photoalbum.model.PhotoalbumModel;
import photoalbum.model.ShapeType;
import photoalbum.views.GraphicalView;
import photoalbum.views.IView;
import photoalbum.views.WebView;

import java.io.IOException;

/**
 * The controller for the photoalbum.
 */
public class PhotoalbumController implements IPhotoalbumController {
  private static IPhotoalbumController INSTANCE;
  private IPhotoalbum model;
  private IView view;
  private CommandFileReader commandReader;

  private int xmax;
  private int ymax;
  private String viewType;

  /**
   * Constructor for the photoalbum controller.
   */
  public PhotoalbumController() {
    model = PhotoalbumModel.getInstance(); // Singleton to ensure only one instance of the model
    commandReader = new CommandFileReader(this); // Dependency Injection
  }

  /**
   * Get the instance of the photoalbum controller.
   * @return The instance of the photoalbum controller.
   */
  public static IPhotoalbumController getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PhotoalbumController();
    }
    return INSTANCE;
  }

  /**
   * Run the photoalbum controller.
   * @param filename The name of the file to read the commands from.
   * @param viewType The type of view to display.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  @Override
  public void run(String filename, String viewType, int xmax, int ymax) throws IOException {
    // Read the commands from the file
    commandReader.readCommands(filename);
    // Determine and initialize the appropriate view
    switch (viewType.toLowerCase()) {
      case "graphical":
        goGraph(xmax, ymax);
        break;
      case "web":
        goWeb(xmax, ymax);
        break;
      default:
        // If the view type is not recognized, throw an exception
        throw new IllegalArgumentException("Unknown view type: " + viewType);
    }
  }

  /**
   * Display the graphical view.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  private void goGraph(int xmax, int ymax) {
    IView view = GraphicalView.getInstance(); // Singleton to ensure only one instance of the view
    view.display(xmax, ymax);
  }

  /**
   * Display the web view.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  private void goWeb(int xmax, int ymax) {
    IView view = WebView.getInstance(); // Singleton to ensure only one instance of the view
    view.display(xmax, ymax);
  }

  /**
   * Process a command.
   * @param command The command to process.
   */
  public void processCommand(String command) {
    command = command.trim(); // Remove leading and trailing whitespace
    String[] tokens = command.split("\\s+"); // Split the command into tokens
    // Determine the command and process it
    switch (tokens[0].toLowerCase()) {
      case "shape":
        handleShape(tokens);
        break;
      case "move":
        handleMove(tokens);
        break;
      case "color":
        handleColor(tokens);
        break;
      case "resize":
        handleResize(tokens);
        break;
      case "remove":
        handleRemove(tokens);
        break;
      case "snapshot":
        handleSnapshot(tokens);
        break;
      default:
        // If the command is not recognized, print an error message
        System.err.println("Unknown command: " + tokens[0]);
    }
  }

  /**
   * Handle the add shape command.
   * @param tokens The tokens from the command.
   */
  private void handleShape(String[] tokens) {
    try {
      String name = tokens[1]; // Get the name of the shape
      // Get the type of the shape
      ShapeType type = ShapeType.valueOf(tokens[2].toUpperCase());
  
      // if the shape is rectangle
      if (type.equals(ShapeType.RECTANGLE)) {
        double x = Double.parseDouble(tokens[3]);
        double y = Double.parseDouble(tokens[4]);
        double width = Double.parseDouble(tokens[5]);
        double height = Double.parseDouble(tokens[6]);
        double red = Double.parseDouble(tokens[7]);
        double green = Double.parseDouble(tokens[8]);
        double blue = Double.parseDouble(tokens[9]);
        // create the rectangle to add to the model
        model.createRectangle(name, type, x, y, width, height, new Color(red, green, blue));
      } else if (type.equals(ShapeType.OVAL)) { // if the shape is oval
        double x = Double.parseDouble(tokens[3]);
        double y = Double.parseDouble(tokens[4]);
        double xRadius = Double.parseDouble(tokens[5]);
        double yRadius = Double.parseDouble(tokens[6]);
        double red = Double.parseDouble(tokens[7]);
        double green = Double.parseDouble(tokens[8]);
        double blue = Double.parseDouble(tokens[9]);
        // create the oval to add to the model
        model.createOval(name, type, x, y, xRadius, yRadius, new Color(red, green, blue));
      } else { // if the shape is not recognized, throw an exception
        throw new IllegalArgumentException("Unknown shape type: " + type);
      }
    } catch (NumberFormatException e) { // if the number format is not correct, print an error
      e.printStackTrace();
    }
  }

  /**
   * Handle the move shape command.
   * @param tokens The tokens from the command.
   */
  private void handleMove(String[] tokens) {
    try {
      String name = tokens[1]; // Get the name of the shape
      // Get the x and y coordinates to move the shape to
      double x = Double.parseDouble(tokens[2]);
      double y = Double.parseDouble(tokens[3]);
      model.moveShape(name, x, y); // Move the shape
    } catch (NumberFormatException e) {
      e.printStackTrace(); // If the number format is not correct, print an error
    }
  }

  /**
   * Handle the change color command.
   * @param tokens The tokens from the command.
   */
  private void handleColor(String[] tokens) {
    String name = tokens[1]; // Get the name of the shape
    // Get the red, green, and blue values to change the color to
    double red = Double.parseDouble(tokens[2]);
    double green = Double.parseDouble(tokens[3]);
    double blue = Double.parseDouble(tokens[4]);
    model.changeShapeColor(name, red, green, blue); // Change the color of the shape
  }

  /**
   * Handle the resize shape command.
   * @param tokens The tokens from the command.
   */
  private void handleResize(String[] tokens) {
    String name = tokens[1]; // Get the name of the shape
    // use name to find if the shape is a rectangle or oval
    IShape shape = model.getShape(name);
    // if the shape is a rectangle, get the width and height to resize the rectangle
    if (shape.getShapeType().equals(ShapeType.RECTANGLE)) {
      double width = Double.parseDouble(tokens[2]);
      double height = Double.parseDouble(tokens[3]);
      model.resizeRectangle(name, width, height);
    } else if (shape.getShapeType().equals(ShapeType.OVAL)) {
      // if the shape is an oval, get the x and y radius to resize the oval
      double xRadius = Double.parseDouble(tokens[2]);
      double yRadius = Double.parseDouble(tokens[3]);
      model.resizeOval(name, xRadius, yRadius);
    }
  }

  /**
   * Handle the remove shape command.
   * @param tokens The tokens from the command.
   */
  private void handleRemove(String[] tokens) {
    String name = tokens[1];
    model.removeShape(name); // Remove the shape
  }

  /**
   * Handle the take snapshot command.
   * @param tokens The tokens from the command.
   */
  private void handleSnapshot(String[] tokens) {
    String description = ""; // Get the description of the snapshot
    if (tokens.length > 1) { // if the description is not empty, get the description
      description = String.join(" ", tokens[1], tokens[tokens.length - 1]);
    }
    model.takeSnapshot(description); // Take the snapshot of the photo album
  }
}

