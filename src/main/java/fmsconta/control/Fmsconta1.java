package fmsconta.control;
import java.awt.*;

import javax.swing.*;

import fmsconta.view.PantallaPrincipal;
import fmsconta.view.Presentacion;

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
		
		JFrame initWindow=new JFrame(version);	
		initWindow.setExtendedState(Frame.MAXIMIZED_BOTH);

		// Ventana de presentacion e identificacion
		Presentacion enterSandMan=new Presentacion(initWindow,version);
		
		// si la identificacion es correcta
		// y existe el usuario abre la pantalla principal
		// y cierra la intro
		
		if (enterSandMan.idCorrect()) {
			// cierra intro
			initWindow.setVisible(false);
			// recupera nombre y pass de usuario
			nameUser=enterSandMan.getUserConta();
			passUser=enterSandMan.getUserPass();
			// accede a la pantalla principal
			// la operativa contable se realiza aqui
			PantallaPrincipal ventana=new PantallaPrincipal(initWindow,version,nameUser,passUser);
		}

						
	}

}