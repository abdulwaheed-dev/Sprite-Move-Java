import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.*;    // Using Swing's components and containers
/**
 * Custom Graphics Example: Using key/button to move a object left or right.
 * The moving object (sprite) is defined in its own class, with its own
 * operations and can paint itself.
 */

class Sprite {
	   // Variables (package access)
	   int x, y, x1, y1, width, height; // Use an rectangle for illustration
	   Color color = Color.RED; // Color of the object
	 
	   // Constructor
	   public Sprite(int x, int y, int x1, int y1, int width, int height, Color color) {
	      this.x = x;
	      this.y = y;
	      this.x1 = x1;
	      this.y1 = y1;
	      this.width = width;
	      this.height = height;
	      this.color = color;
	   }
	 
	   // Paint itself given the Graphics context
	   public void paint(Graphics g) {
	      g.setColor(color);
	     // g.fillRect(x1, y1, height, width); // Fill a rectangle
	      g.fillRect(x1, y1, width, height); // Fill a rectangle
	      g.fillOval(x, y, 30, 30);
	      
	   }
	}

public class SpriteMoving extends JFrame {
   // Define constants for the various dimensions
   public static final int CANVAS_WIDTH = 400;
   public static final int CANVAS_HEIGHT = 140;
   public static final Color CANVAS_BG_COLOR = new Color(47, 48, 52);
 
   private DrawCanvas canvas; // the custom drawing canvas (an inner class extends JPanel)
   private Sprite sprite;     // the moving object
 
   // Constructor to set up the GUI components and event handlers
   public SpriteMoving() {
      // Construct a sprite given x, y, width, height, color
      sprite = new Sprite(CANVAS_WIDTH / 2 - 5, CANVAS_HEIGHT / 2 - 40,
    		  CANVAS_WIDTH / 2 - 5 - 5, CANVAS_HEIGHT / 2 - 1,
            10, 80, Color.RED);
 
      // Set up a panel for the buttons
      JPanel btnPanel = new JPanel(new FlowLayout());
      JButton btnLeft = new JButton("Move Left ");
      btnPanel.add(btnLeft);
      btnLeft.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            moveLeft();
            requestFocus(); // change the focus to JFrame to receive KeyEvent
         }
      });
      JButton btnRight = new JButton("Move Right");
      btnPanel.add(btnRight);
      btnRight.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            moveRight();
            requestFocus(); // change the focus to JFrame to receive KeyEvent
         }
      });
      JButton btnUp = new JButton("Move Up ");
      btnPanel.add(btnUp);
      btnUp.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            moveUp();
            requestFocus(); // change the focus to JFrame to receive KeyEvent
         }
      });
      JButton btnDown = new JButton("Move Down ");
      btnPanel.add(btnDown);
      btnDown.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            moveDown();
            requestFocus(); // change the focus to JFrame to receive KeyEvent
         }
      });
 
      // Set up the custom drawing canvas (JPanel)
      canvas = new DrawCanvas();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Add both panels to this JFrame
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(btnPanel, BorderLayout.SOUTH);
 
      // "super" JFrame fires KeyEvent
      addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent evt) {
            switch(evt.getKeyCode()) {
               case KeyEvent.VK_LEFT:  
            	   moveLeft();  
            	   break;
               case KeyEvent.VK_RIGHT: moveRight(); break;
            }
         }
      });
      
      addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent evt) {
             switch(evt.getKeyCode()) {
                case KeyEvent.VK_UP:  moveUp();  break;
                case KeyEvent.VK_DOWN: moveDown(); break;
             }
          }
       });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Move a Sprite");
      pack();            // pack all the components in the JFrame
      setVisible(true);  // show it
      requestFocus();    // "super" JFrame requests focus to receive KeyEvent
   }
 
   // Helper method to move the sprite Up
   private void moveUp() {
	      // Save the current dimensions for repaint to erase the sprite
	      int savedY = sprite.y;
	      // update spritez
	      sprite.y -= 10;
	      //sprite.x1 -= 10;
	      // Repaint only the affected areas, not the entire JFrame, for efficiency
	      //canvas.repaint(savedX, sprite.y, sprite.width, sprite.height); // Clear old area to background
	      //canvas.repaint(sprite.x, sprite.y, sprite.width, sprite.height); // Paint new location
	      canvas.repaint();
	   }
   
   // Helper method to move the sprite Down
   private void moveDown() {
	      // Save the current dimensions for repaint to erase the sprite
	      int savedY = sprite.y;
	      // update spritez
	      sprite.y += 10;
	      //sprite.x1 -= 10;
	      // Repaint only the affected areas, not the entire JFrame, for efficiency
	      //canvas.repaint(savedX, sprite.y, sprite.width, sprite.height); // Clear old area to background
	      //canvas.repaint(sprite.x, sprite.y, sprite.width, sprite.height); // Paint new location
	      canvas.repaint();
	   }
   
   private void moveLeft() {
      // Save the current dimensions for repaint to erase the sprite
	  int savedX = sprite.x;
	  
	  if((sprite.x1 != sprite.x) && (sprite.y < sprite.y1 || (sprite.y) > (sprite.y1+sprite.height) )) 
	  	{sprite.x -= 10;}
	  
      // update spritez
      //sprite.x1 -= 10;
      // Repaint only the affected areas, not the entire JFrame, for efficiency
      //canvas.repaint(savedX, sprite.y, sprite.width, sprite.height); // Clear old area to background
      //canvas.repaint(sprite.x, sprite.y, sprite.width, sprite.height); // Paint new location
      canvas.repaint();
   }
 
   // Helper method to move the sprite right
   private void moveRight() {
      // Save the current dimensions for repaint to erase the sprite
      int savedX = sprite.x;
      // update sprite
      sprite.x += 10;
      //sprite.x1 += 10;
      // Repaint only the affected areas, not the entire JFrame, for efficiency
      //canvas.repaint(savedX, sprite.y, sprite.width, sprite.height); // Clear old area to background
      //canvas.repaint(sprite.x, sprite.y, sprite.width, sprite.height); // Paint at new location
      canvas.repaint();
      
   }
 
   // Define inner class DrawCanvas, which is a JPanel used for custom drawing
   class DrawCanvas extends JPanel {
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         setBackground(CANVAS_BG_COLOR);
         sprite.paint(g);  // the sprite paints itself
      }
   }
 
   // The entry main() method
   public static void main(String[] args) {
      // Run GUI construction on the Event-Dispatching Thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new SpriteMoving(); // Let the constructor do the job
         }
      });
   }
}