package fmsconta.control;
import java.awt.*;

import javax.swing.*;
import fmsconta.view.*;

public class Fmsconta1 extends JFrame {

	
	public Fmsconta1() {
		// builder
	}


	public static void main(String[] args) {
		
		final String version="fmsConta 2.0 Beta";
		String nameUser;
		String passUser;
		
		// CONFIGURACION ASPECTO DE PANELES PLATAFORMA MEZCLA JAVA
		try {
			UIManager.setLookAndFeel(
			UIManager.getCrossPlatformLookAndFeelClassName()
			);
		} catch (Exception e) {
			System.out.println("No se puede cambiar el aspecto de la aplicación: "+e.getMessage());
			e.printStackTrace();
		}
		
		// ARRANCAMOS LA APLICACION
		
		JFrame initWindow=new JFrame();	
		initWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		
		// Ventana de presentacion e identificacion
		Presentacion enterSandMan=new Presentacion(initWindow,version);
		enterSandMan.setVisible(true);
		
		// Abre la pantalla de identificacion
		enterSandMan.openPant();
		
		// si la identificacion es correcta
		// y existe el usuario abre la pantalla 
		
		if (enterSandMan.idCorrect()) {
			// recupera nombre y pass de usuario
			nameUser=enterSandMan.getUserConta();
			passUser=enterSandMan.getUserPass();
			
			// cierra el marco de la pantalla de identificacion
			enterSandMan.closePant();
			// cierra el marco de la pantalla de presentacion
			enterSandMan.setVisible(false);
			initWindow.setVisible(false);
			
			// accede a la pantalla principal
			// la operativa contable se realiza aqui
			initWindow.setLocation(0, 0);
			PantallaPrincipal ventanaMain=new PantallaPrincipal(initWindow,version,nameUser,passUser);
			
			ventanaMain.controlCenter();
		}

						
	}

}