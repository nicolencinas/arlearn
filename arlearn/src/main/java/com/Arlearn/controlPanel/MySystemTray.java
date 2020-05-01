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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.net.URI;
import java.net.URL;


/**
 * @see https://www.jc-mouse.net/
 * @author mouse
 */
public class MySystemTray {
    
    private JFrame parent;
    private PopupMenu popup = new PopupMenu();
    private final Image image =new ImageIcon("C:/Users/nickito/git/arlearn/arlearn/src/main/resources/static/images/logo.png").getImage() ;
    private final TrayIcon trayIcon = new TrayIcon(image, "Panel de control(Online Hotel)", popup);    
    
    /**
     * Constructor de clase
     * @param frame
     */
    public MySystemTray( JFrame frame)
    {
    this.parent = frame;
    JButton eventos=new JButton("Gestión de eventos");
    frame.setLayout(null);
    eventos.setBounds(60, 10, 200, 20);
    frame.getContentPane().add(eventos);

    //comprueba si SystemTray es soportado en el sistema
    if (SystemTray.isSupported())
    {
      //obtiene instancia SystemTray
      SystemTray systemtray = SystemTray.getSystemTray();
      trayIcon.setImageAutoSize(true);
        
      //acciones del raton sobre el icono en la barra de tareas
        MouseListener mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent evt) {            

                //Si se presiona con el boton izquierdo en el icono
                //y la aplicacion esta minimizada se muestra una frase
                if( evt.getButton() == MouseEvent.BUTTON1 && parent.getExtendedState()==JFrame.ICONIFIED ){
                        MensajeTrayIcon("Steve Wozniak\n \"El Internet de las cosas es el futuro,\n todos los dispositivos conectados todo el tiempo\"", MessageType.WARNING);
                }            
            }

            @Override
            public void mouseEntered(MouseEvent evt) {/*nada x aqui circulen...*/}

            @Override
            public void mouseExited(MouseEvent evt) {/*nada x aqui circulen...*/}

            @Override
            public void mousePressed(MouseEvent evt) {/*nada x aqui circulen...*/}

            @Override
            public void mouseReleased(MouseEvent evt) {/*nada x aqui circulen...*/}
        };

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
        
        trayIcon.addMouseListener(mouseListener);

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

    eventos.addActionListener(new ActionListener()
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                URL url = new URL("http://127.0.0.1:8080/eventos");
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

    });

    }

    //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo)
    {
        trayIcon.displayMessage("Mi Aplicación dice:", texto, tipo);        
    }
    
    /**
     * clase interna que manejara una accion en segundo plano
     */

}