package photoalbum.views;

import photoalbum.model.IPhotoalbum;
import photoalbum.model.ISnapshot;
import photoalbum.model.PhotoalbumModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A class to draw a frame for the graphical view.
 */
public class GraphicalViewFrame extends JFrame {
  private IPhotoalbum model;
  private List<ISnapshot> snapshots;
  private List<String> snapshotIDs;
  private Map<String, ISnapshot> snapshotMap;

  private int xmax;
  private int ymax;
 
  private GraphicalViewPanel currentP;
  private int currentSnapshotIndex;

  private JPanel snapshotP;
  private JPanel buttonsP;

  private JButton prev;
  private JButton next;
  private JButton select;
  private JButton quit;

  /**
   * Constructs a new graphical view frame.
   * @param xmax The x size of the bounds of the "view window"
   * @param ymax The y size of the bounds of the "view window"
   */
  public GraphicalViewFrame(int xmax, int ymax) {
    super();
    this.model = PhotoalbumModel.getInstance();
    this.snapshots = model.getSnapshots();

    this.xmax = xmax;
    this.ymax = ymax;

    // Check if the snapshots list is empty
    if (snapshots.isEmpty()) {
      System.out.println("There is No Snapshot!");
      return;  // Exit the constructor if the list is empty
    }
    
    this.snapshotIDs = model.getSnapshotIDs();
    this.snapshotMap = new HashMap<>();

    this.snapshotP = new JPanel();
    this.buttonsP = new JPanel();

    // Initialize the view
    setTitle("CS5004 Shapes Photo Album Viewer");
    setSize(xmax, ymax);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setVisible(true);

    // Create all the components and add them to the frame
    createButtonsPanel();
    initSnapshotPanel();
    loadSnapshots();
  }

  /**
   * Initialize the snapshot panel.
   */
  private void initSnapshotPanel() {
    snapshotP.setLayout(new BorderLayout()); // Set the layout of the panel
    snapshotP.setPreferredSize(new Dimension(xmax, ymax)); // Set the size of the panel
    add(snapshotP, BorderLayout.CENTER); // Add the panel to the frame
  }

  /**
   * Create the buttons panel.
   */
  private void createButtonsPanel() {
    buttonsP.setLayout(new FlowLayout()); // Set the layout of the panel

    // Create buttons
    prev = new JButton("<< Prev <<");
    next = new JButton(">> Next >>");
    select = new JButton("^^ Select ^^");
    quit = new JButton("xx Quit xx");

    // Add action listeners and use method references
    prev.addActionListener(e -> navigate(-1)); 
    select.addActionListener(this::selectSnapshot);
    next.addActionListener(e -> navigate(1));
    quit.addActionListener(e -> quit());

    // Add buttons to the panel
    buttonsP.add(prev);
    buttonsP.add(next);
    buttonsP.add(select);
    buttonsP.add(quit);
    add(buttonsP, BorderLayout.SOUTH); // Add the buttons panel to the frame
  }

  /**
   * Navigate to the previous or next snapshot.
   * @param direction The direction to navigate in.
   */
  private void navigate(int direction) {
    int newIndex = currentSnapshotIndex + direction; // Calculate the new index
    // Check if the new index is out of bounds
    if (newIndex < 0 || newIndex >= snapshots.size()) {
      JOptionPane.showMessageDialog(
              this, "No more snapshots in this direction.", "Warning", JOptionPane.WARNING_MESSAGE);
      return;
    }
    currentSnapshotIndex = newIndex; // Update the current index
    displaySnapshot(currentSnapshotIndex); // Display the new snapshot
  }

  /**
   * Select a snapshot to display.
   * @param e The action event.
   */
  private void selectSnapshot(ActionEvent e) {
    // Get the snapshot IDs
    String[] ids = snapshots.stream().map(ISnapshot::getSnapshotId).toArray(String[]::new);
    // Display a dialog to select a snapshot
    String selectedId = (String) JOptionPane.showInputDialog(
            this, "Select a Snapshot ID:", "Select Snapshot",
            JOptionPane.QUESTION_MESSAGE, null, ids, ids[0]);
    // Check if a snapshot was selected
    if (selectedId != null) {
      // Find the index of the selected snapshot
      for (int i = 0; i < snapshots.size(); i++) {
        // Check if the snapshot ID matches
        if (snapshots.get(i).getSnapshotId().equals(selectedId)) {
          currentSnapshotIndex = i; // Update the current index
          displaySnapshot(currentSnapshotIndex); // Display the selected snapshot
          break;
        }
      }
    }
  }

  /**
   * Quit the application.
   */
  private void quit() {
    // Quit the application
    System.exit(0);
  }

  /**
   * Load the snapshots.
   */
  private void loadSnapshots() {
    if (snapshots.isEmpty()) { // Check if the snapshots list is empty
      JOptionPane.showMessageDialog(null,
            "No Snapshot in the List!",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
      return;
    }
    snapshotP.setLayout(new BorderLayout()); // Set the layout of the panel

    // Initialize the snapshot IDs list and the snapshot map for fast access
    for (ISnapshot snapshot : snapshots) {
      String snapshotId = snapshot.getSnapshotId();
      snapshotIDs.add(snapshotId); // Add the snapshot ID to the list
      snapshotMap.put(snapshotId, snapshot); // Add the snapshot with the ID to the map
    }
    currentSnapshotIndex = 0; // Set the current index to 0
    displaySnapshot(currentSnapshotIndex); // Display first snapshot and update the current index
  }

  /**
   * Display a snapshot by ID.
   * @param id The ID of the snapshot to display.
   */
  private void displaySnapshot(String id) {
    // Get the snapshot by ID
    ISnapshot snapshot = snapshotMap.get(id);
    if (snapshot == null) {
      return;
    }
    // Check if there is a current panel
    if (currentP != null) {
      currentP.setVisible(false);
    }
    // Create a new current panel and add it to the snapshot panel
    currentP = new GraphicalViewPanel(snapshot, xmax, ymax);
    snapshotP.add(currentP, BorderLayout.CENTER);
    // Update the current panel and make it visible
    currentP.setVisible(true);
  }

  /**
   * Display a snapshot by index.
   * @param idIndex The index of the snapshot to display.
   */
  private void displaySnapshot(int idIndex) {
    // Check if the index is out of bounds
    if (idIndex < 0 || idIndex >= snapshotIDs.size()) {
      return;
    }
    // Get the snapshot ID by index and display the snapshot
    String snapshotId = snapshotIDs.get(idIndex);
    displaySnapshot(snapshotId);
  }
}