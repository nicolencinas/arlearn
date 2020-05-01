package com.Arlearn.controlPanel;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.net.URI;
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
    private final Icon logo=new ImageIcon(logoIm.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));


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
    home.setBounds(150, 10, 110, 110);
    home.setToolTipText("HomePage");
    home.setIcon(logo);
    home.setOpaque(true);
    
    
    JButton eventos=new JButton("Gestión salon eventos");
    eventos.setBounds(100, 120, 200, 20);

    JButton recepcion=new JButton("Gestión de habitaciones");
    recepcion.setBounds(100, 150, 200, 20);

    JButton restaurant=new JButton("Gestión salon restaurant");
    restaurant.setBounds(100, 180, 200, 20);

    JButton reportes=new JButton("Gestión de reportes");
    reportes.setBounds(100, 210, 200, 20);

    frame.getContentPane().add(eventos);
    frame.getContentPane().add(recepcion);
    frame.getContentPane().add(restaurant);
    frame.getContentPane().add(reportes);
    frame.getContentPane().add(home);



    //comprueba si SystemTray es soportado en el sistema
    if (SystemTray.isSupported())
    {
      //obtiene instancia SystemTray
      SystemTray systemtray = SystemTray.getSystemTray();
      trayIcon.setImageAutoSize(true);
        
      

        /* ----------------- ACCIONES DEL MENU POPUP --------------------- */
        //Salir de aplicacion
        ActionListener exitListener = (ActionEvent e) -> {
            System.exit(0);
        };
        
        //Restaurar aplicacion
        ActionListener restoreListener = (ActionEvent e) -> {
            //si esta minimizado restaura JFrame
            if(parent.getExtendedState()== JFrame.ICONIFIED){
                parent.setVisible(true);
                parent.setExtendedState( JFrame.NORMAL );
                parent.repaint();                
            }            
        };
    
        //Se crean los Items del menu PopUp y se añaden
        MenuItem exitAppItem = new MenuItem("Salir");
        exitAppItem.addActionListener(exitListener);
        popup.add(exitAppItem);

        MenuItem restoreAppItem = new MenuItem("Restaurar");
        restoreAppItem.addActionListener(restoreListener);
        popup.add(restoreAppItem);
        
        /* ----------------- ACCIONES DEL MENU POPUP : END ---------------- */
        
      

        //Añade el TrayIcon al SystemTray
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

    //Cuando se minimiza JFrame, se oculta para que no aparesca en la barra de tareas
    parent.addWindowListener(new WindowAdapter(){
        @Override
        public void windowIconified(WindowEvent e){
           parent.setVisible(false);//Se oculta JFrame
           //Se inicia una tarea cuando se minimiza           
        
        }
    });

    eventos.addActionListener(a -> gotoURL("http://127.0.0.1:8080/eventos",a));
    recepcion.addActionListener(b -> gotoURL("http://127.0.0.1:8080/habitacion",b));
    restaurant.addActionListener(c -> gotoURL("http://127.0.0.1:8080/restaurant",c));
    home.addActionListener(d -> gotoURL("http://127.0.0.1:8080/greeting", d));
    reportes.addActionListener(e -> gotoURL("http://127.0.0.1:8080/reportes", e));

    
    // {

    //     @Override
    //     public void actionPerformed(ActionEvent e)
    //     {
    //         try {
    //             URL url = new URL("http://127.0.0.1:8080/eventos");
    //             try {
    //                 Desktop.getDesktop().browse(url.toURI());
    //             } catch (IOException ex) {
    //                 ex.printStackTrace();
    //             } catch (URISyntaxException ex) {
    //                 ex.printStackTrace();
    //             }
    //         } catch (MalformedURLException e1) {
    //             e1.printStackTrace();
    //         }

    //     }

    // });

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