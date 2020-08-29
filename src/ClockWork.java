import java.awt.event.*;
import java.text.*;
import javax.swing.Timer;

/**
 *
 * @author Linh
 */
public class ClockWork {
    //clock parameters
    int longArmIndex, shortArmIndex, secondsArmIndex;
    int radius, centerX, centerY;
    int[] x = new int[60];
    int[] y = new int[60];
    int[] xx = new int[60];
    int[] yy = new int[60];
    int tickTime;
    public boolean start = true;
    
    //functional and communication 
    Timer timer;
    ActionListener listener;
    ActionEvent event;
    
    public ClockWork() {
         event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,"Set the clock");

    }
    
    //getter for arm index
    public int[] getArmIndex() {
        int[] armIndex = {shortArmIndex, longArmIndex, secondsArmIndex};
        return armIndex;
    }
    
    //getter for tack coordinate
    public int[][] getTack() {
        int[][] tackCoordinates = {x, y, xx, yy};
        return tackCoordinates;
    }
    
    //setter for circle data
    public void setCircle(int rad, int x, int y) {
        radius = rad;
        centerX = x;
        centerY = y;
    }
    
    public void setTickTime (int tick) {
        tickTime = tick;
    }
    
    public void addActionListener(ActionListener watch) {
        listener = watch;
    }
    
    public void loadTacks() {
        for (int i = 0; i <60; i++) {
            x[i] = (int)(centerX + radius*Math.sin(i*Math.PI/30));
            y[i] = (int)(centerY - radius*Math.cos(i*Math.PI/30));
            if (i%5 == 0) {
                xx[i] = (int)(centerX + (radius + 17)*Math.sin(i*Math.PI/30));
                yy[i] = (int)(centerY - (radius + 17)*Math.cos(i*Math.PI/30));
            }
            else {
                xx[i] = (int)(centerX + (radius + 10)*Math.sin(i*Math.PI/30));
                yy[i] = (int)(centerY - (radius + 10)*Math.cos(i*Math.PI/30));                
            }
        }
    }
    
    public void makeTimer() {
        timer = new Timer(tickTime, new TimerListener());
        timer.start();
        
    }
    
    public void stop() {
        try { 
            timer.stop(); 
        }
        catch (NullPointerException e) {               
        }    }
    
    public void reset() {
        try { 
            timer.stop(); 
        }
        catch (NullPointerException e) {               
        }
        longArmIndex = 0;
        shortArmIndex = 0;
        secondsArmIndex = 0;
        connect();
    }
    
    private boolean timeFormat(String inp) {
        DateFormat df = new SimpleDateFormat("h:mm");
        
        try {
            df.parse(inp);
            return true;
        } 
        catch ( ParseException exc ) {          
        }
        
        return false;
    }
    
    public void setClock(String timeToSet) {
        boolean valid = timeFormat(timeToSet);
        if (valid == false) {
            return;
        }
        if (valid == true) {
            if (timeToSet.length() == 5) {
                int hour = Integer.parseInt(timeToSet.substring(0, 2));
                //shortArmIndex = hour%12*5;
                longArmIndex = Integer.parseInt(timeToSet.substring(3, 5));
                shortArmIndex = hour%12*5 + longArmIndex/12;
            }
            if (timeToSet.length() == 4) {
                int hour = Integer.parseInt(timeToSet.substring(0, 1));
                //shortArmIndex = hour%12*5;
                longArmIndex = Integer.parseInt(timeToSet.substring(2, 4));
                shortArmIndex = hour%12*5 + longArmIndex/12;                
            }
            secondsArmIndex = 0;
        }
        connect();
        
        
    }
    
    private void connect() {
        listener.actionPerformed(event);

    }
    
    private class TimerListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            
            if (secondsArmIndex > 59) {
                secondsArmIndex = secondsArmIndex%60; 
                longArmIndex ++;
            }
            if (longArmIndex > 59) {
                longArmIndex = longArmIndex%60;                 
            }
            if (start == false && longArmIndex%12 == 0 && secondsArmIndex == 0) {
                shortArmIndex ++;                
            }          
            if (shortArmIndex > 59) 
                shortArmIndex = shortArmIndex%60;
                      
            start = false;
            secondsArmIndex++;

            connect();
        }
    }
}
