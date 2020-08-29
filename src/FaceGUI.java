
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;


/**
 *
 * @author Linh
 */
public class FaceGUI extends JFrame implements ActionListener {
    int radius, centerX, centerY;
    private ClockWork work = new ClockWork();
    private Face face;
    private final int HEIGHT = 600;
    private final int WIDTH = 700;

    public FaceGUI(ClockWork cw) {
        super();
        work = cw;
        work.addActionListener(this);
        buildClock();
    }

    public void buildClock(){
        Container pane = getContentPane();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.YELLOW);
        centerX = 350;
        centerY = 250;
        radius = 150;        
        work.setCircle(radius, centerX, centerY);
        work.loadTacks();
        face = new Face();
        add(face);
        pack();
        setVisible(true);
    }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        face.repaint();
    }
    
    private class Face extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int[] armIndex = work.getArmIndex();
            int[][] tackCoord = work.getTack();                       
            
            //draw circles
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, 10000, 10000);            
            g.setColor(Color.WHITE);
            g.fillOval(centerX - radius, centerY - radius, 2*radius, 2*radius);
            g.setColor(Color.CYAN);
            g.fillOval(centerX - radius + 15, centerY - radius + 15, 2*radius - 30, 2*radius - 30);
            g.setColor(Color.BLACK);
            g.drawOval(centerX - radius, centerY - radius, 2*radius, 2*radius);
            g.fillOval(centerX - 5, centerY - 5, 10, 10);
            
            //draw tacks
            for (int i = 0; i < 60; i++) {
                g.drawLine(tackCoord[0][i],tackCoord[1][i],tackCoord[2][i],tackCoord[3][i]);
            }
            
            //draw numbers
            g.setFont(new Font("Times", Font.PLAIN, 50));
            g.drawString("1", tackCoord[2][5] + 7, tackCoord[3][5] - 7);
            g.drawString("2", tackCoord[2][10] + 10, tackCoord[3][10]);
            g.drawString("3", tackCoord[2][15] + 10, tackCoord[3][15] + 20);
            g.drawString("4", tackCoord[2][20] + 10, tackCoord[3][20] + 30);
            g.drawString("5", tackCoord[2][25] + 5, tackCoord[3][25] + 40);
            g.drawString("6", tackCoord[2][30] - 10, tackCoord[3][30] + 45);
            g.drawString("7", tackCoord[2][35] - 25, tackCoord[3][35] + 45);
            g.drawString("8", tackCoord[2][40] - 35, tackCoord[3][40] + 30);
            g.drawString("9", tackCoord[2][45] - 45, tackCoord[3][45] + 20);
            g.drawString("10", tackCoord[2][50] - 60, tackCoord[3][50]);
            g.drawString("11", tackCoord[2][55] - 50, tackCoord[3][55] - 7);
            g.drawString("12", tackCoord[2][0] - 25, tackCoord[3][0] - 10);

            //draw arms
            
            try {
            g.setColor(Color.BLUE);             
            g.drawLine(centerX, centerY, (int) (tackCoord[0][work.shortArmIndex] - 0.3*radius*Math.sin(Math.PI*2*work.shortArmIndex/60)), (int) (tackCoord[1][work.shortArmIndex]  + 0.3*radius*Math.cos(Math.PI*2*work.shortArmIndex/60)));
            g.drawLine(centerX - 1, centerY - 1, (int) (tackCoord[0][work.shortArmIndex] - 0.3*radius*Math.sin(Math.PI*2*work.shortArmIndex/60))-1, (int) (tackCoord[1][work.shortArmIndex] + 0.3*radius*Math.cos(Math.PI*2*work.shortArmIndex/60))-1);
            g.drawLine(centerX + 1, centerY + 1, (int) (tackCoord[0][work.shortArmIndex] - 0.3*radius*Math.sin(Math.PI*2*work.shortArmIndex/60))+1, (int) (tackCoord[1][work.shortArmIndex] + 0.3*radius*Math.cos(Math.PI*2*work.shortArmIndex/60))+1);
            g.drawLine(centerX, centerY, (int) (tackCoord[0][work.longArmIndex] - 0.1*radius*Math.sin(Math.PI*2*work.longArmIndex/60)), (int) (tackCoord[1][work.longArmIndex] + 0.1*radius*Math.cos(Math.PI*2*work.longArmIndex/60)));
            g.setColor(Color.RED);
            g.drawLine(centerX, centerY, tackCoord[0][work.secondsArmIndex], tackCoord[1][work.secondsArmIndex]);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                
            }
            
        }
    }
    
}
