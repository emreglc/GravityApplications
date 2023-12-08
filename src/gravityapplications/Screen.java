package gravityapplications;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class Screen extends JFrame{

    public Screen(String title) throws HeadlessException {
      
        super(title);
        Panel panel = new Panel();
        setResizable(false);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        getContentPane().setPreferredSize(new Dimension(800,600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
