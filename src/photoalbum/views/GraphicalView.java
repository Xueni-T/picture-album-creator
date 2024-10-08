package photoalbum.views;

import photoalbum.model.IPhotoalbum;
import photoalbum.model.PhotoalbumModel;

/**
 * The graphical view for the photo album.
 */
public class GraphicalView implements IView {
  private static IView INSTANCE;
  private IPhotoalbum model;

  private int xmax;
  private int ymax;

  /**
   * Constructor for the graphical view.
   */
  public GraphicalView() {
    this.xmax = xmax;
    this.ymax = ymax;
    this.model = PhotoalbumModel.getInstance(); // Singleton to ensure only one instance of the model
  }

  /**
   * Get the instance of the graphical view.
   * @return The instance of the graphical view.
   */
  public static IView getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new GraphicalView();
    }
    return INSTANCE;
  }

  /**
   * Display the graphical view.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  @Override
  public void display(int xmax, int ymax) {
    this.xmax = xmax;
    this.ymax = ymax;
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      // Display the graphical view in a new frame
      @Override
      public void run() {
        GraphicalViewFrame frame = new GraphicalViewFrame(xmax, ymax);
        frame.setVisible(true); // Make the frame visible
      }
    });
  }
}
