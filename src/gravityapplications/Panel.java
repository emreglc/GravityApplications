package gravityapplications;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener, ActionListener {
    
    private float ballX = 100;
    private float ballY = 550;
    private float vY = 0;
    private float vX = 0;
    private double angle;
    private float g = -(float) 0.025;
    private float a = g;
    private float h;
    private float hMax = 0;
    private long startTime;
    private long endTime;
    private long t;
    Timer timer = new Timer(10, this);

    public Panel() {
        
        JButton okButton = new JButton("OK");
		JLabel l1 = new JLabel("Velocity:");
		JLabel l2 = new JLabel("Angle:");
        JTextField tf1 = new JTextField();
        JTextField tf2 = new JTextField();

        timer.addActionListener(this);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = tf1.getText();
                String text2 = tf2.getText();
                if (!text1.isEmpty() && !timer.isRunning()){
                    angle = Math.toRadians(Float.parseFloat(text2));                    
                    vY = (float) (Float.parseFloat(text1)*Math.sin(angle)/10.0);
                    vX = (float) (Float.parseFloat(text1)*Math.cos(angle)/10.0);
                    System.out.println(vX);
                }
                hMax = 0;
                timer.start();
                t = 0;
                startTime = System.nanoTime();
                
            }
        });
        
        requestFocus();
        addKeyListener(this);
        setFocusable(true);
        tf1.addActionListener(this);
        tf1.setBounds(300,100,100,50);
        tf1.setPreferredSize(new Dimension(200,20));
        tf2.addActionListener(this);
        tf2.setBounds(300,200,100,50);
        tf2.setPreferredSize(new Dimension(200,20));
		add(l1);
        add(tf1);
		add(l2);
        add(tf2);
        add(okButton);
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        h = 550-ballY;
        
        
        if (ballX <= 0) {
            ballX = 0;
            vX = -vX;
        }
        
        else if (ballX >= 750) {
            ballX = 750;
            vX = -vX;
        }
        
        if (ballY <= 0) {
            ballY = 0;
            vY = -vY;
        }
        
        if (ballY+50 < 600 || vY>0) {
            ballY -= vY;
            ballX += vX;
            vY += a;        
            repaint();   
        }else{
            ballY = 550;
            repaint();
            timer.stop();
            
            endTime = System.nanoTime();
            t = (long) ((endTime - startTime)/1000000.0);
            System.out.println(hMax);
            System.out.println("elapsed time: "+t); 
        }
        
        if (h > hMax) {
            hMax = h;
        }
 
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.fillOval((int) ballX, (int) ballY, 50, 50);
    }
    
    
    
    
}
