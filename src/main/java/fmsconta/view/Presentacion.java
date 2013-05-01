package fmsconta.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Presentacion extends JFrame implements ActionListener, Settings{
	
	private JDialog pantallagen;
	
	private JLabel imagenCentral;
	private Image abc;	
	private JPanel south;
	private JButton botonSalir;

	private String userConta="";
	private String userPass="";
	private Identificacion nwUser;
	
	
	
	public Presentacion () {
		//builder
	}
		
	
	
	/* ********************************************************
	 * Este constructor es un inicializador de pantalla soporte
	 * para colocar sobre ella el JDialog de identificacion
	 * Recibe un JFrame principal y el titulo de la ventana 
	 ********************************************************* */
	
	public Presentacion (JFrame initWindow,String titulo) {
		
		// 		INICIALIZACION DE LA VENTANA PRINCIPAL
		pantallagen=new JDialog(initWindow,titulo,false);
		
		pantallagen.setSize(900,600);
		pantallagen.setLocationRelativeTo(initWindow);
		pantallagen.setResizable(true);
		pantallagen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		pantallagen.getContentPane().setBackground(colorfondo);
		
		pantallagen.setLayout(new BorderLayout());
		
		
		// creamos los componentes		
		
		// imagen central 
		abc=new ImageIcon(pathImageFiles+"fondo.jpg").getImage();
		ImageIcon imgPortada=new ImageIcon(abc.getScaledInstance(600,450,java.awt.Image.SCALE_SMOOTH));
		imagenCentral=new JLabel(imgPortada);	
		
		
        // boton en la parte inferior de la pantalla
		south=new JPanel();
		south.setLayout(new FlowLayout());   
		south.setBackground(colorfondo);
		botonSalir=new JButton("Salida");
        south.add(botonSalir);
        
        // añadimos los componentes a la pantalla central
		pantallagen.add(imagenCentral,BorderLayout.CENTER);
		pantallagen.add(south, BorderLayout.SOUTH);
			
		// mostramos la pantalla
		pantallagen.setVisible(true);
		
		// instanciamos y mostramos el jdialog de identificacion
		nwUser=new Identificacion();
		nwUser.identificacionPant(pantallagen);

		// implementamos el boton salida
		botonSalir.setActionCommand("Salida");
		botonSalir.addActionListener(this);
			
	} // fin de Presentacion
	


	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getActionCommand().equals("Salida")) {
			// si pulsa el boton salida reconfirma la accion
			int respuesta = JOptionPane.showConfirmDialog(null, 
					"¿Desea abandonar la aplicación?","Aviso importante", 
					JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			// yes_no option
			if (respuesta!=1) {
				// si pulsa abandonar
				System.exit(0);
			}
		}
		
	}
	
	
	public boolean idCorrect() {
		
		// llama al correspondiente metodo en identificacion
		return nwUser.idCorrect();
		
	}
	
	
	public String getUserConta() {
		userConta=nwUser.getUser();
		return userConta;
	}


	public void setUserConta(String userConta) {
		this.userConta = userConta;
	}


	public String getUserPass() {
		userPass=nwUser.getPassword();
		return userPass;
	}


	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	// metodo para hacer visible la pantalla de identificacion
	// instanciamos
	public void openPant() {
		this.nwUser.openPant();
		this.nwUser.setVisible(true);
	}
	
	// metodo para cerrar la pantalla de identificacion
	// instanciamos el metodo
	public void closePant() {
		this.nwUser.closePant();
		this.nwUser.setVisible(false);
		this.nwUser=null;
	}
}
