
import javax.swing.JList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Linh
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClockWork cw = new ClockWork();
        ControlDevices control = new ControlDevices(cw);
        FaceGUI face = new FaceGUI(cw);
        
    }
    
}
