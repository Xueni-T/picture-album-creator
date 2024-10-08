# Shapes Photo Album #

This system allows to create and manipulate shapes, take snapshots of the state of the shapes, and print out the snapshots.
Then, generate and display the photo album snapshots in both HTML and graphical view.

1. # Model Structure #

1.1 Interface: IPhotoAlbum
    Represents the contract for a photo album that can manipulate shapes and take snapshots.
             
1.2 Concrete Class: PhotoalbumModel
    Implements the IPhotoalbum interface to provide functionality for managing shapes and snapshots.
    It has methods to add and manipulate shapes, take snapshots, and print out the snapshots.

1.3 Aggregation
  1.3.1 Shape
        Represents a 2D shape in the photo album.
    1.3.1.1 Interface: IShape
            Defines the contract for a shape in the photo album.
    1.3.1.2 Abstract Class: Shape
            Provides a base implementation for common shape functionalities.
            It has properties for the name, type, coordinates, dimensions, and color of the shape.
    1.3.1.3 Concrete Class: Oval, Rectangle
            The Rectangle and Oval classes extend the Shape class to represent specific types of shapes.
            They have additional properties for their dimensions
  1.3.2 Snapshot
        Represents a snapshot of the photo album's state at a specific point in time.
    1.3.2.1 Interface: ISnapshot
            Defines the contract for a snapshot in the photo album.
    1.3.2.2 Concrete Class: Snapshot
            Implements the ISnapshot interface to represent a snapshot.
            The purpose of this class is to capture and store the state of the shapes at a specific point in time.
            It has properties for the ID, timestamp, description, and list of shapes in the snapshot.

1.4 Design Considerations
  1.4.1 The model supports various kinds of 2D shapes. 
        In consideration of more potential shapes have various number of dimensions, such as
        oval(x radius, y raduis), circle(raduis), triangle(side1, side2, side3),
        they may cause different number of parameters of each shape object. Thus, the manipulation of dimensions of shapes 
        such as width, height, x radius, and y raduis are not generally implemented in one resize function, but in their own class
        of shape. This shape-specific dimensions manipulation make the model ease of extension.
        As the photo album application evolves to include more complex shapes.
  
  1.4.2 The Model allows to create and manipulate (e.g. move, resize, change color, remove) the attributes of each shape
        (e.g. move, resize, change color, remove) by searching their names.

  1.4.3 The Model could take a Snapshot of all the shapes with their attributes of itself and the information
        of taken time. The snapshots are also efficient to retrieve and clear.

1.5 Changes from the past
  1.5.1 Changed the value restrictions of color red, green, and blue from [0, 1] to [0, 255], which can better fit the 
     range of RGB values to show on the view.
  1.5.2 Added some getter functions for shapes, snapshots, and snapshot IDs in the model, which can be used in controller
     and views.

2. # Controller Structure #

2.1 Interface: IPhotoalbumController
    Defines the contract for a controller in the photo album.
2.2 Concrete class: PhotoalbumController
    This class is responsible for interpreting formatted input from users, validating and processeing commands,
    then directing actions to the model and views, and coordinating communication between the model and the view. 
    It also provides error handling and feedback to users.
    The run() funciton in the controller mainly controls to read the users' commands and reference to an View,
    which can be either a `GraphicalView` or a `WebView`, and display each view.
2.3 I/O: CommandFileReader
    A file reader to read the input file into lines from users and convey to controller to interpret.

3. # Views Structure #

3.1 Interface: IView
    Defines the contract for WebView and GraphicalView in the photo album.
3.2 Concrete Class: WebView and GraphicalView
    3.2.1 WebView
          This Class creates web-based viewing of each snapshot by generating HTML files with SVG content.
    3.2.2 GraphicalView
          GraphicalView presents shapes and snapshots in a photoalbum using Swing components.
          3.2.3 Aggregation
                GraphicalViewFrame: extends JFrame, represents the main graphical window with buttons (Prev, Select, Next, Quit)
                to navigate each snapshot in the photoalbum.
                GraphicalViewPanel: extends JPanel, displays snapshots and shapes.

4. # PhotoAlbumMain #
   The `main` method accepts command line arguments that specify the input file, output file, view type, and the dimensions of the canvas.
   The command line arguments are parsed in a loop. The `-in` argument specifies the input file, `-out` specifies the output file,
   and `-view` or `-v` specifies the view type. After parsing the command line arguments, the `main` method creates an instance of
   the `PhotoalbumController` and calls its `run` method to start the application.