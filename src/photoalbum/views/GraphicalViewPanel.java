package photoalbum.views;

import photoalbum.model.IShape;
import photoalbum.model.ISnapshot;
import photoalbum.model.ShapeType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class to draw a panel for the graphical view.
 */
public class GraphicalViewPanel extends JPanel {
  private final ISnapshot snapshot;

  private int xmax;
  private int ymax;

  /**
   * Constructs a new graphical view panel.
   * @param snapshot The snapshot to display
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  public GraphicalViewPanel(ISnapshot snapshot, int xmax, int ymax) {
    this.snapshot = snapshot;
    this.xmax = xmax;
    this.ymax = ymax;
    setLayout(new BorderLayout()); // Set the layout for this panel
    createDrawPanel(); // Create the panel
  }

  /**
   * Create the draw panel.
   */
  private void createDrawPanel() {
    // Create a panel for snapshot label and set its layout
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    addSnapshotLabel(labelPanel, snapshot);

    // Create a panel for drawing shapes
    JPanel p = new JPanel(new BorderLayout()) {
      // Paint the shapes in the snapshot on the panel
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the shapes in the snapshot
        for (IShape shape : snapshot.getShapes()) {
          // set the color
          int red = (int) (shape.getColor().getR());
          int green = (int) (shape.getColor().getG());
          int blue = (int) (shape.getColor().getB());
          g.setColor(new Color(red, green, blue));
          // get coordinates
          int x = (int) shape.getX();
          int y = (int) shape.getY();
          // if the shape is a rectangle
          if (shape.getShapeType() == ShapeType.RECTANGLE) {
            int width = (int) ((photoalbum.model.Rectangle) shape).getWidth();
            int height = (int) ((photoalbum.model.Rectangle) shape).getHeight();
            g.fillRect(x, y, width, height); // fill the rectangle
          } else if (shape.getShapeType() == ShapeType.OVAL) {
            // if the shape is an oval
            int xRadius = (int) ((photoalbum.model.Oval) shape).getXradius();
            int yRadius = (int) ((photoalbum.model.Oval) shape).getYradius();
            g.fillOval(x, y, xRadius, yRadius); // fill the oval
          }
        }
      }
    };
    // Set the size and background color of the panel
    p.setPreferredSize(new Dimension(xmax, ymax));
    p.setBackground(Color.DARK_GRAY);
    p.setVisible(true); // Set the panel to be visible
    add(labelPanel, BorderLayout.NORTH); // add the label panel to this panel
    add(p, BorderLayout.CENTER); // add the drawing panel to this panel
  }

  /**
   * Add a label for the snapshot to the panel.
   * @param panel The panel to add the label to
   * @param snapshot The snapshot to add the label for
   */
  private void addSnapshotLabel(JPanel panel, ISnapshot snapshot) {
    // Create a JLabel for the snapshot ID and description
    JLabel label = new JLabel();
    // Set the text of the label
    label.setText("<html>" + snapshot.getSnapshotId()
            + "<br>" + snapshot.getDescription() + "</html>");
    label.setForeground(Color.BLACK); // Set the color of the label
    panel.add(label, BorderLayout.NORTH); // Add the label to the panel
  }
}
