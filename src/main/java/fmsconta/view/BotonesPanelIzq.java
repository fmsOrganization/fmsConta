package fmsconta.view;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;



public class BotonesPanelIzq extends JFrame{
	
	//*********** ESTA CLASE CREA LOS BOTONES DEL PANEL LATERAL IZQUIERDO
	// CREANDO LOS ESCUCHADORES Y MANEJADORES DE LOS EVENTOS
	
	protected JButton usuarios;
	protected JButton empresa;
	protected JButton faqs;
	protected JButton boe;
	protected JButton copseg;
	protected JButton salida;
	
	private String pathImageFiles="src/main/java/fmsconta/pictures/";
	private Image abc;

	
	
	public BotonesPanelIzq(){
	    // CREAMOS LOS BOTONES LATERALES DEL PANEL IZQUIERDO
			
			// LES ASIGNAMOS UNA IMAGEN, Y LA DIMENSIONAMOS
		abc=new ImageIcon(pathImageFiles+"botonusuarios.jpg").getImage();
		ImageIcon botonusu=new ImageIcon(abc.getScaledInstance(100,75,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"botonempresas.jpg").getImage();		
		ImageIcon botonemp=new ImageIcon(abc.getScaledInstance(100,75,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"botonfaqs.jpg").getImage();
		ImageIcon botonfaq=new ImageIcon(abc.getScaledInstance(100,75,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"botonboe.jpg").getImage();
		ImageIcon botonboe=new ImageIcon(abc.getScaledInstance(100,75,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"botoncopia.jpg").getImage();
		ImageIcon botonseg=new ImageIcon(abc.getScaledInstance(100,75,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"botonsalida.jpg").getImage();
		ImageIcon botonsal=new ImageIcon(abc.getScaledInstance(100,75,java.awt.Image.SCALE_SMOOTH));
			
			// CREACION DE LOS BOTONES
		usuarios=new JButton(botonusu);
		empresa=new JButton(botonemp);
		faqs=new JButton(botonfaq);
		boe=new JButton(botonboe);
		copseg=new JButton(botonseg);
		salida=new JButton(botonsal);
		

	}


}