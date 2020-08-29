
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



/**
 *
 * @author Linh
 */
public class ControlDevices extends JFrame implements ActionListener {
    private ClockWork work = new ClockWork();
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
        //J Components
    private JSlider slider;
    private JPanel panelCenter, panelEast, panelC1, panelC2, panelC3, panelC4;
    private JButton btnRun, btnStop, btnReset, btnSet;
    private JTextField text;
    private JLabel lblStart, lblStop, lblReset, lblEnter, lblSelect;
    
    public ControlDevices(ClockWork cw) {
        super();
        work = cw;       
        Container pane = getContentPane();
        setTitle("Control Of Clockwork");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        setBackground(Color.DARK_GRAY);
        slider = new JSlider(JSlider.VERTICAL, 0, 1000, 1000);
        makeSliders(slider);
        work.setTickTime(slider.getValue());
        buildControlGUI();
    }
    
    public void makeSliders(JSlider sld) {
        sld.setBackground(Color.CYAN);
        sld.setPaintLabels(true);
        sld.setPaintTicks(true);
        sld.setMajorTickSpacing(100);
        sld.setMinorTickSpacing(10);
        sld.addChangeListener(new SliderListener());
    }
    
    public void resetSlider(JSlider sld) {
        sld.setValue(sld.getMaximum());
        sld.setEnabled(true);
    }
    
    public void buildControlGUI() {
        //instantiate
        //labels
        lblStart = new JLabel("Click to start the clock");
        lblStop = new JLabel("Click to stop the clock");
        lblReset = new JLabel("Click to reset the clock");
        lblEnter = new JLabel(" Enter time, use format 15:42 ");
        lblEnter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lblSelect = new JLabel("Select tick time");
        //buttons
        btnRun = new JButton("Run the Clock");
        btnRun.addActionListener(this);
        btnStop = new JButton("Stop the Clock");
        btnStop.addActionListener(this);
        btnReset = new JButton("Reset the Clock");
        btnReset.addActionListener(this);
        btnSet = new JButton("Set the Clock");
        btnSet.addActionListener(this);
                
        //panels
        panelCenter = new JPanel(new GridLayout(4,1));
        panelEast = new JPanel();
        panelC1 = new JPanel(); 
        panelC2 = new JPanel();
        panelC3 = new JPanel();
        panelC4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //text fields
        text = new JTextField(10);
        text.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                
        //add components into panels
        panelC1.add(btnRun);
        panelC1.add(lblStart);
        
        panelC2.add(btnStop);
        panelC2.add(lblStop);
        
        panelC3.add(btnReset);
        panelC3.add(lblReset);
        
        panelC4.add(lblEnter);
        panelC4.add(text);
        panelC4.add(btnSet);
        panelC4.setBackground(Color.PINK);

        panelCenter.add(panelC1);
        panelCenter.add(panelC2);
        panelCenter.add(panelC3);
        panelCenter.add(panelC4);
        
        slider.setPreferredSize(new Dimension(100,333));
        panelEast.add(slider);
        panelEast.add(lblSelect);
            
        add(panelCenter, BorderLayout.CENTER);
        add(panelEast, BorderLayout.EAST);
        
        pack();
        setVisible(true);
    }
          
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRun) {
            work.makeTimer();
            btnRun.setEnabled(false);
        }
        else if (e.getSource() == btnStop) {
            work.stop();
            btnRun.setEnabled(true);

        }
        else if (e.getSource() == btnReset) {
            resetSlider(slider);
            work.reset();
            btnRun.setEnabled(true);
            work.start = true;    
            
        }
        else if (e.getSource() == btnSet) {
            work.setClock(text.getText());
            text.setText("");

        }
        
        //System.out.println(e.getActionCommand());

    }
    
    private class SliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent event){
        
            work.setTickTime(slider.getValue());
        }
    }
}
