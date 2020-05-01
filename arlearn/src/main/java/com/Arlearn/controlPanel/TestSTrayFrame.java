package com.Arlearn.controlPanel;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * @see https://www.jc-mouse.net/
 * @author mouse
 */



public class TestSTrayFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static TestSTrayFrame INSTANCE;

    public static TestSTrayFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TestSTrayFrame();
			return new TestSTrayFrame();
		} else
			return INSTANCE;
	}
    
    MySystemTray mySystemTray = new MySystemTray( this );
    
    public TestSTrayFrame(){
        initComponents();
        TestSTrayFrame.this.setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        setTitle("Online Hotel (Panel Control)");
        setSize(new Dimension(400,300));
        setPreferredSize(new Dimension(400,300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        
    }
    
     public  void panelControl(String args[]){
        EventQueue.invokeLater(() -> {
            new TestSTrayFrame().setVisible(true);
        });
    }
}//end class