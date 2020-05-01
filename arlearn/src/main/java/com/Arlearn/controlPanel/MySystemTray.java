package com.Arlearn.controlPanel;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.awt.Desktop;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.net.URL;


/**
 * @see https://www.jc-mouse.net/
 * @author mouse
 */
public class MySystemTray {
    
    private JFrame parent;
    private PopupMenu popup = new PopupMenu();
    private final ImageIcon icon =new ImageIcon("C:/Users/nickito/git/arlearn/arlearn/src/main/resources/static/images/logo.png");

    private final ImageIcon logoIm =new ImageIcon("C:/Users/nickito/git/arlearn/arlearn/src/main/resources/static/images/logoOH.png");
    private final Icon logo=new ImageIcon(logoIm.getImage().getScaledInstance(125,125, Image.SCALE_DEFAULT));


    private final Image image =icon.getImage() ;
    private final TrayIcon trayIcon = new TrayIcon(image, "Panel de control(Online Hotel)", popup);    
    
    /**
     * Constructor de clase
     * @param frame
     */
    public MySystemTray( JFrame frame)
    {
    this.parent = frame;
    frame.setLayout(null);


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

    frame.getContentPane().add(eventos);
    frame.getContentPane().add(recepcion);
    frame.getContentPane().add(restaurant);
    frame.getContentPane().add(reportes);
    frame.getContentPane().add(home);



    if (SystemTray.isSupported())
    {
      SystemTray systemtray = SystemTray.getSystemTray();
      trayIcon.setImageAutoSize(true);

        ActionListener exitListener = (ActionEvent e) -> {
            System.exit(0);
        };
        
        ActionListener restoreListener = (ActionEvent e) -> 
        {
            if(parent.getExtendedState()== JFrame.ICONIFIED){
                parent.setVisible(true);
                parent.setExtendedState( JFrame.NORMAL );
                parent.repaint();                
            }            
        };
    
        MenuItem exitAppItem = new MenuItem("Salir");
        exitAppItem.addActionListener(exitListener);
        popup.add(exitAppItem);

        MenuItem restoreAppItem = new MenuItem("Restaurar");
        restoreAppItem.addActionListener(restoreListener);
        popup.add(restoreAppItem);
    
        try 
        {
            systemtray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println( "Error:" + e.getMessage() );
        }
    } else {
        System.err.println( "Error: SystemTray no es soportado" );
        return;
    }
    parent.addWindowListener(new WindowAdapter(){
        @Override
        public void windowIconified(WindowEvent e){
           parent.setVisible(false);
        
        }
    });

    eventos.addActionListener(a -> gotoURL("http://127.0.0.1:8080/eventos",a));
    recepcion.addActionListener(b -> gotoURL("http://127.0.0.1:8080/recepcion",b));
    restaurant.addActionListener(c -> gotoURL("http://127.0.0.1:8080/restaurant",c));
    home.addActionListener(d -> gotoURL("http://127.0.0.1:8080/homepage", d));
    reportes.addActionListener(e -> gotoURL("http://127.0.0.1:8080/reportes", e));

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