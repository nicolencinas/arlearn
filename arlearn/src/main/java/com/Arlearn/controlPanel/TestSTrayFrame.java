package com.Arlearn.controlPanel;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * @see https://www.jc-mouse.net/
 * @author mouse
 */



public class TestSTrayFrame extends JFrame 
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SystemTray systemtray;
    private TrayIcon trayIcon;
        
    public TestSTrayFrame()
    {
        
		initComponents();
        TestSTrayFrame.this.setLocationRelativeTo(null);
    }
    
    private void initComponents()
    {
        setTitle("Online Hotel (Panel Control)");
        setSize(new Dimension(400,300));
        setPreferredSize(new Dimension(400,300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        setSize(310, 600);   
        setResizable(false);  
        setLayout(null);
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowIconified(WindowEvent e)
            {
               setVisible(false);
            
            }
        });

        
        
        PopupMenu popup = new PopupMenu();
        final ImageIcon icon =new ImageIcon("C:/Users/nickito/git/arlearn/arlearn/src/main/resources/static/images/logo.png");

        final ImageIcon logoIm =new ImageIcon("C:/Users/nickito/git/arlearn/arlearn/src/main/resources/static/images/logoOH.png");
        final Icon logo=new ImageIcon(logoIm.getImage().getScaledInstance(125,125, Image.SCALE_DEFAULT));


        final Image image =icon.getImage() ;
        trayIcon = new TrayIcon(image, "Panel de control(Online Hotel)", popup);  
       
        trayIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) 
				{
					if(getExtendedState()== JFrame.ICONIFIED){
		                setVisible(true);
		                setExtendedState( JFrame.NORMAL );
		               repaint();                
		            }    
					else if(getExtendedState()== JFrame.NORMAL){
		                setVisible(true);
		                setExtendedState( JFrame.ICONIFIED);
		               repaint();                
		            }     
				  }
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
        	
        });

        JButton home=new JButton();
        home.setBounds(85, 10, 130, 130);
        home.setToolTipText("HomePage");
        home.setIcon(logo);
        home.setOpaque(true);
        
        
        JButton eventos=new JButton("Gestión salon eventos");
        eventos.setBounds(50, 160, 200, 20);

        JButton recepcion=new JButton("Gestión recepcion");
        recepcion.setBounds(50, 190, 200, 20);

        JButton restaurant=new JButton("Gestión salon restaurant");
        restaurant.setBounds(50, 220, 200, 20);

        JButton reportes=new JButton("Gestión de reportes");
        reportes.setBounds(50, 250, 200, 20);

        getContentPane().add(eventos);
        getContentPane().add(recepcion);
        getContentPane().add(restaurant);
        getContentPane().add(reportes);
        getContentPane().add(home);
        
        eventos.addActionListener(a -> gotoURL("http://127.0.0.1:8080/eventos",a));
        recepcion.addActionListener(b -> gotoURL("http://127.0.0.1:8080/recepcion",b));
        restaurant.addActionListener(c -> gotoURL("http://127.0.0.1:8080/restaurant",c));
        home.addActionListener(d -> gotoURL("http://127.0.0.1:8080/homepage", d));
        reportes.addActionListener(e -> gotoURL("http://127.0.0.1:8080/reportes", e));
        
        ActionListener exitListener = (ActionEvent e) -> 
        {
            System.exit(0);
        };
        
        ActionListener restoreListener = (ActionEvent e) -> 
        {
            if(getExtendedState()== JFrame.ICONIFIED){
                setVisible(true);
                setExtendedState( JFrame.NORMAL );
               repaint();                
            }            
        };
        
        MenuItem exitAppItem = new MenuItem("Salir");
        exitAppItem.addActionListener(exitListener);
        popup.add(exitAppItem);

        MenuItem restoreAppItem = new MenuItem("Restaurar");
        restoreAppItem.addActionListener(restoreListener);
        popup.add(restoreAppItem);
        
        
        if (SystemTray.isSupported())
        {
          systemtray= SystemTray.getSystemTray();
          trayIcon.setImageAutoSize(true);

            try 
            {
            	systemtray.add(trayIcon);
            } catch (Exception e) {
                System.err.println( "Error:" + e.getMessage() );
            }
        } else {
            System.err.println( "Error: SystemTray no es soportado" );
           
        }
        
       
    }
    
     public  void panelControl(String args[])
     {
        setVisible(true);
       
    }
     
     public void gotoURL(String ur,ActionEvent e) 
     {
         try {
             URL url = new URL(ur);
             try {
                 Desktop.getDesktop().browse(url.toURI());
             } catch (IOException ex) {
                 ex.printStackTrace();
             } catch (URISyntaxException ex) {
                 ex.printStackTrace();
             }
         } catch (MalformedURLException e1) {
             e1.printStackTrace();
         }
     }
}