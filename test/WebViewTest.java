import static org.junit.Assert.assertEquals;


import java.util.List;

import org.junit.Test;
import photoalbum.model.Color;
import photoalbum.model.IPhotoalbum;
import photoalbum.model.ISnapshot;
import photoalbum.model.PhotoalbumModel;
import photoalbum.model.ShapeType;
import photoalbum.views.WebView;

/**
 * A class to test the web view.
 */
public class WebViewTest {

  private IPhotoalbum model = PhotoalbumModel.getInstance();
  private WebView view = new WebView();
  private String snapshotId;

  /**
   * Test the generateHtml method with a snapshot list.
   */
  @Test
  public void testGenerateHtml() {
    model.createRectangle("Rectangle1", ShapeType.RECTANGLE, 10, 10, 20, 20, new Color(1, 0, 0));
    model.createOval("Oval1", ShapeType.OVAL, 10, 10, 20, 20, new Color(0, 1, 0));
    model.takeSnapshot("Snapshot1");
    List<ISnapshot> snapshots = model.getSnapshots();
    snapshotId = snapshots.get(0).getSnapshotId();
    view.display(800, 800);
    String htmlContent = view.getHtmlContent();
    String expected = "<!DOCTYPE html>"
      + "<html>"
      + "<head>"
      + "<title>Shapes Photo Album</title>"
      + "</head>"
      + "<body>"
      + "<div style=\"background-color:powderblue;\">"
      + "<h2>" + snapshotId + "</h2>"
      + "<p>Description: Snapshot1</p>"
      + "<svg width=\"800\" height=\"800\">"
      + "<rect x=\"10.0\" y=\"10.0\" width=\"20.0\" height=\"20.0\" style=\"fill:rgb(1,0,0)\" />"
      + "<ellipse cx=\"30.0\" cy=\"30.0\" rx=\"20.0\" ry=\"20.0\" style=\"fill:rgb(0,1,0)\" />"
      + "</svg>"
      + "</div>"
      + "</body>"
      + "</html>";
    assertEquals(expected, htmlContent);
  }

  /**
   * Test the generateHtml method with empty snapshot list.
   */
  @Test
  public void testGenerateHtmlEmpty() {
    model.takeSnapshot("");
    List<ISnapshot> snapshots = model.getSnapshots();
    model.clearSnapshots();
    view.display(800, 800);
    String htmlContent = view.getHtmlContent();
    String expected = "<!DOCTYPE html>"
      + "<html>" 
      + "<head>" 
      + "<title>Shapes Photo Album</title>" 
      + "</head>" 
      + "<body>" 
      + "</body>" 
      + "</html>";
    assertEquals(expected, htmlContent);
  }
}
