package photoalbum.views;

/**
 * The view interface for both the graphical and web views.
 */
public interface IView {
  /**
   * Display the view.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  void display(int xmax, int ymax); // Display the web view
}
