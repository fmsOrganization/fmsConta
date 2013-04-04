package fmsconta.view;

import java.awt.Component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import fmsconta.control.ContaDAO;

public class Identificacion extends JFrame implements ActionListener{
	
	// FORMATO DE LA VENTANA
	private JDialog idUser;
	private GridBagLayout granLayout=new GridBagLayout();
	// componentes de la ventana identificacion
	private JButton entrar;
	private JButton salir;
	private JTextField userName;
	private JPasswordField userPass;
	private JLabel texto1;
	private JLabel userNameL;
	private JLabel userPassL;
	
	
	public Identificacion() {
		// builder
	}
	
	
	public Identificacion (JDialog mainWindow) {
		
		// PREPARACION DE LA VENTANA
		// al ser de tipo identificacion es un dialog modal
	
		idUser=new JDialog(mainWindow, "Identificación",true);		
		 
		// tamaño de ventana, localizacion centro screen, no resizable y cerrar 
		idUser.setSize(350, 275);
		idUser.setLocationRelativeTo (null);
		idUser.setResizable(false);
		idUser.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
		// PREPARACION DEL FORMATO
		idUser.setLayout(granLayout); 
		
		// ETIQUETAS DE LA VENTANA
		texto1= new JLabel("  Introduzca su nombre de usuario y password",SwingConstants.LEFT);
		userNameL= new JLabel("Usuario : ",SwingConstants.LEFT);
		userPassL= new JLabel("Password: ",SwingConstants.LEFT);
		entrar= new JButton("Entrar");
		salir= new JButton("Salir");
		userName=new JTextField(15);
		userPass=new JPasswordField(15);
		
		// añadimos los componentes
		Component comp;
		comp=addComponentes(texto1,0,0,4,1,300,50,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER);
		idUser.add(comp);
		comp=addComponentes(userNameL,0,1,2,1,80,50,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		idUser.add(comp);
		comp=addComponentes(userName,2,1,2,1,30,50,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		idUser.add(comp);
		comp=addComponentes(userPassL,0,2,2,1,80,50,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		idUser.add(comp);
		comp=addComponentes(userPass,2,2,3,1,30,50,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		idUser.add(comp);
		comp=addComponentes(entrar,1,3,1,1,60,100,GridBagConstraints.NONE,GridBagConstraints.EAST);
		idUser.add(comp);
		comp=addComponentes(salir,2,3,1,1,60,100,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		idUser.add(comp);
		
		// añadimos listener de los eventos
		this.entrar.addActionListener(this);
		this.salir.addActionListener(this);

		// mostramos la ventana de identificacion
		idUser.setVisible(true);
		
	} // fin del builder identificacion
	
	
	public Component addComponentes (Component componente, int gridx, int gridy, int gridwidth, 
			int gridheight, int weightx, int weighty, int fill, int anchor) {
		
		// este metodo ayuda a ajustar el gridbag ahorrando escritura de codigo
		GridBagConstraints colocador=new GridBagConstraints();
		colocador.gridx=gridx;
		colocador.gridy=gridy;
		colocador.gridwidth=gridwidth;
		colocador.gridheight=gridheight;
		colocador.weightx=weightx;
		colocador.weighty=weighty;
		colocador.fill=fill;
		colocador.anchor=anchor;
		granLayout.setConstraints(componente,colocador);
		return componente;
		
	}  // fin del metodo addcomponentes

	
	public void actionPerformed (ActionEvent e) {
		// leemos los eventos
		Object source=e.getSource();
		
		// control sobre los botones
		if (source==salir) {
			// si pulsa salir
			System.exit(0);
		}
		
		if (source==entrar) {
			// si pulsa entrar
			if (!(userName.getText().equals("") || userName.getText().equals(null))) {
				// introducido un user y un pass 
				// verifica si coincide user y pass
				// y cierra la ventana
				if (idCorrect()) {
					idUser.setVisible(false);
				} else {
					// no es correcto y se muestra un mensaje
					JOptionPane.showMessageDialog(null, "Usuario o contraseña no válidos");
				}
				
			}
		}		

	}  // fin del actionPerformed

	
	public boolean idCorrect() {
		
		// se comprueba en DB si existe un user y password concreto
		// y devuelve true o false segun 
		
		// instanciamos el pool de conexiones ContaDAO
		ContaDAO newUserConta=new ContaDAO();
		
		boolean usExist=false;
		// comprobamos los datos del usuario
		// y recibimos true o false
		usExist=newUserConta.idExist(getUser(),getPassword());
		
		return usExist;
	}
	
		
	public String getUser() {
		// retorna el usuario
		return userName.getText();
	}

	public String getPassword() {
		// retorna la password
		char tuPass[]=new char[10];
		tuPass=userPass.getPassword();
		return String.valueOf(tuPass);
	}
}
