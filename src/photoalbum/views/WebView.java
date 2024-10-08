package photoalbum.views;

import photoalbum.model.IPhotoalbum;
import photoalbum.model.IShape;
import photoalbum.model.ISnapshot;
import photoalbum.model.PhotoalbumModel;
import photoalbum.model.ShapeType;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * A class to display the photo album in a web view.
 */
public class WebView implements IView {
  private static IView INSTANCE;
  private IPhotoalbum model;
  private String htmlContent;

  private int xmax;
  private int ymax;

  /**
   * Constructor for the web view.
   */
  public WebView() {
    this.model = PhotoalbumModel.getInstance(); // Singleton to ensure only one instance of the model
  }

  /**
   * Get the instance of the web view.
   * @return The instance of the web view.
   */
  public static IView getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new WebView();
    }
    return INSTANCE;
  }

  /**
   * Display the web view.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  @Override
  public void display(int xmax, int ymax) {
    this.xmax = xmax;
    this.ymax = ymax;
    List<ISnapshot> snapshots = model.getSnapshots(); // Get the snapshots from the model
    generateHtml(snapshots); // Generate the HTML content
    try {
      // Create a 
      File file = new File("photoalbum.html");
      // Write the HTML content to the file
      try (PrintWriter writer = new PrintWriter(file)) {
        writer.println(htmlContent);
      }
      // Output the file path to the console
      System.out.println("HTML file path: " + file.getAbsolutePath());
      // Open the HTML file in the default web browser
      Desktop.getDesktop().browse(file.toURI());
    } catch (IOException e) {
      // Handle the exception
      System.err.println("Error displaying HTML content: " + e.getMessage());
    }
  }

  /**
   * Generate the HTML content for the web view.
   * @param snapshots The snapshots to display.
   */
  public void generateHtml(List<ISnapshot> snapshots) {
    StringBuilder htmlBuilder = new StringBuilder(); // Create string builder for the HTML content
    // Generate the HTML content
    htmlBuilder.append("<!DOCTYPE html>");
    htmlBuilder.append("<html>");
    htmlBuilder.append("<head>");
    htmlBuilder.append("<title>Shapes Photo Album</title>");
    htmlBuilder.append("</head>");
    htmlBuilder.append("<body>");

    // Generate the HTML content for each snapshot
    for (ISnapshot snapshot : snapshots) {
      // Set the background color of the snapshot
      htmlBuilder.append("<div style=\"background-color:powderblue;\">");
      // Set the snapshot ID and description
      htmlBuilder.append("<h2>").append(snapshot.getSnapshotId()).append("</h2>");
      htmlBuilder.append("<p>Description: ").append(snapshot.getDescription()).append("</p>");
      // set the SVG tag
      htmlBuilder.append("<svg width=\"800\" height=\"800\">");
      // Generate the SVG content for the snapshot
      String snapshotSvg = generateSvg(snapshot);
      htmlBuilder.append(snapshotSvg);
      // Close the SVG tag
      htmlBuilder.append("</svg>");
      htmlBuilder.append("</div>");
    }
    // Close the body and HTML tags
    htmlBuilder.append("</body>");
    htmlBuilder.append("</html>");
    // Set the HTML content to the generated content
    htmlContent = htmlBuilder.toString();
  }

  /**
   * Generate the SVG content for a snapshot.
   * @param snapshot The snapshot to generate the SVG content for.
   * @return The SVG content for the snapshot.
   */
  private String generateSvg(ISnapshot snapshot) {
    StringBuilder svg = new StringBuilder(); // Create a string builder for the SVG content
    // Generate the SVG content for each shape in the snapshot
    for (IShape shape : snapshot.getShapes()) {
      // Generate SVG based on the type of the shape
      if (shape.getShapeType() == ShapeType.RECTANGLE) { // If the shape is a rectangle
        svg.append("<rect x=\"").append(shape.getX())
           .append("\" y=\"").append(shape.getY())
           .append("\" width=\"").append(((photoalbum.model.Rectangle) shape).getWidth())
           .append("\" height=\"").append(((photoalbum.model.Rectangle) shape).getHeight())
           .append("\" style=\"fill:rgb(")
           .append((int) (shape.getColor().getR())).append(",")
           .append((int) (shape.getColor().getG())).append(",")
           .append((int) (shape.getColor().getB())).append(")\" />");
      } else if (shape.getShapeType() == ShapeType.OVAL) { // If the shape is an oval
        svg.append("<ellipse cx=\"").append(shape.getX()
                          + ((photoalbum.model.Oval) shape).getXradius())
             .append("\" cy=\"").append(shape.getY() + ((photoalbum.model.Oval) shape).getYradius())
             .append("\" rx=\"").append(((photoalbum.model.Oval) shape).getXradius())
             .append("\" ry=\"").append(((photoalbum.model.Oval) shape).getYradius())
             .append("\" style=\"fill:rgb(")
             .append((int) (shape.getColor().getR())).append(",")
             .append((int) (shape.getColor().getG())).append(",")
             .append((int) (shape.getColor().getB())).append(")\" />");
      }
    }
    return svg.toString(); // Return the SVG content
  }

  /**
   * Get the HTML content.
   * @return The HTML content.
   */
  public String getHtmlContent() {
    return this.htmlContent;
  }
}
