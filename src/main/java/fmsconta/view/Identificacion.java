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
	private ContaDAO newUserConta;
	
	
	public Identificacion() {
		// builder
	}
	
	
	/* ****************************************************
	 * Este metodo constructor es un JDialog modal
	 * Suministra la clasica ventana de usuario y contraseña
	 * Recibe como argumento el JFrame principal
	 ****************************************************** */
	
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
		userName.setToolTipText("Longitud entre 5 y 10 caracteres");
		userPass=new JPasswordField(15);
		userPass.setToolTipText("Longitud entre 5 y 10 caracteres");
		
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
	
	
	
	/* *************************************************************************
	 * Este metodo es un metodo complementario de ayuda a la confeccion
	 * del gridbaglayout, ahorrando mucha linea y tal 
	 * Recibe un componente y los parametros del gridbaglayout
	 * Devuelve el componente con el setcontraints ya hecho
	 *************************************************************************** */
	
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

	
	
	/* **********************************************************************
	 * Este metodo implementa la accion de los botones entrar y salir
	 * de la ventana de identificacion
	 * 
	 * SALIR : cierra la aplicacion.
	 * 
	 * ENTRAR : Recibe el evento y lo controla. 
	 * Llama al metodo idCorrect para controlar el usuario y/o pass validos
	 * Llama al idExist para comprobar si existe el usuario/password
	 * Llama al lpdcgu para comprobar si había aceptado las LPD/CGU
	 * En caso de no haber aceptado, pregunta y graba la aceptacion
	 * En caso de no aceptar cierra la aplicacion
	 **********************************************************************/
	
	public void actionPerformed (ActionEvent e) {
		// leemos los eventos
		Object source=e.getSource();
		
		// ******************control sobre los botones
		
		if (source==salir) {
			// si pulsa salir
			System.exit(0);
		}
		
		if (source==entrar) {
			// comprueba la idoneidad del login/pass introducido
			if (idCorrect()){
				// comprueba la existencia del login/pass
				if (idExist()) {
					// comprueba si estaba aceptada la lpdcgu
					if (lpdcgu()){
						// todo se cumple - cierra la ventana
						idUser.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Debe aceptar las CGU y la LPD");
						if (aceptoLPD() && aceptoCGU()) {
							// Aceptar las condiciones
							grabaCGULPD();
							// despues de grabar la aceptacion cierra la ventana
							idUser.setVisible(false);							
						} else {
							// NO ACEPTA LAS CONDICIONES
							// SE CIERRA LA APLICACION
							JOptionPane.showMessageDialog(null, "No ha aceptado las condiciones," +
									" la aplicación se cerrará");
							System.exit(0);
						}
					}
					
				} else {
					// no existe y se muestra un mensaje
					JOptionPane.showMessageDialog(null, "Ese usuario o contraseña no existe");
				}
			} else {
				// no es correcto y se muestra un mensaje
				JOptionPane.showMessageDialog(null, "El usuario o contraseña es de longitud inadecuado");
			}
		}		

	}  // fin del actionPerformed

	
	
	/* *******************************************************************
	 * Este metodo comprueba en si el login y password suministrado
	 * cumple los requisitos basicos que son:
	 * campo no vacio, y longitud min 5 y max 10
	 * No recibe ningun argumento y retorna un true/false si cumple o no
	 ******************************************************************** */
	
	public boolean idCorrect() {
		
		String login=userName.getText();
		char passw[]=new char[10];
		passw=userPass.getPassword();
		
		// verifica si hay userName relleno
		if ((login.equals("") || login.equals(null))) {
			JOptionPane.showMessageDialog(null, "Usuario no rellenado");
			return false;
		}
		
		//  verifica si userName mide entre 5 y 10
		if ((login.length()<5 || login.length()>10)) {
			JOptionPane.showMessageDialog(null, "Longitud de usuario inadecuada");
			return false;
		}		
		
		// verifica si hay userPass relleno
		if ((passw.equals("") || passw.equals(null))) {
			JOptionPane.showMessageDialog(null, "Contraseña no rellenada");
			return false;
		}		
		
		// verifica si userPass mide entre 5 y 10
		if ((passw.length<5 || passw.length>10)) {
			JOptionPane.showMessageDialog(null, "Longitud de contraseña inadecuada");
			return false;
		}
		
		passw=null;
		// llegado aqui es que cumple los requisitos
		return true;
		
	} // fin del metodo idCorrect

	
	
	/* *******************************************************************
	 * Este metodo comprueba en la DDBB si existe el usuario identificado
	 * por el login y password introducido
	 * No recibe ningun argumento y retorna un true/false si existe o no
	 ******************************************************************** */
	
	public boolean idExist() {
		
		// se comprueba en DB si existe un user y password concreto
		// y devuelve true o false segun 
		
		// instanciamos el pool de conexiones ContaDAO
		this.newUserConta=new ContaDAO();
		
		boolean usExist=false;
		// comprobamos los datos del usuario
		// y recibimos true o false
		usExist=this.newUserConta.idExist(getUser(),getPassword());
		
		return usExist;
		
	} // fin del metodo idExist
	
	
	
	// get del user
	protected String getUser() {
		// retorna el login del usuario
		return userName.getText();
	} 

	// get del password
	protected String getPassword() {
		// retorna la password del usuario
		char tuPass[]=new char[10];
		tuPass=userPass.getPassword();
		String pass=String.valueOf(tuPass);
		tuPass=null;
		return pass;
	}
	
	
	
	/* *************************************************************
	 * Metodo que sirve para verificar si cumple la lpdcgu
	 * implementa un metodo existente en contaDAO
	 * retorna true si cumple y false si no cumple
	 ****************************************************************/
	
	protected boolean lpdcgu() {
		// retorna si cumple o no las CGULPD
		return this.newUserConta.cumpleCGULPD();
	}
	
	
	
	/* *************************************************************
	 * Metodo a implementar
	 * consiste en mostrar el texto de la LPD y que el cliente
	 * clicke una casilla y acepte o deniegue la LPD
	 * deberia retornar true si acepta y false si no acepta
	 ****************************************************************/
	
	protected boolean aceptoLPD() {
		
		JOptionPane.showMessageDialog(null, "Acepto la LPD");
		return true;
	}

	
	
	/* *************************************************************
	 * Metodo a implementar
	 * consiste en mostrar el texto de las CGU y que el cliente
	 * clicke una casilla y acepte o deniegue las CGU
	 * deberia retornar true si acepta y false si no acepta
	 ****************************************************************/
	
	protected boolean aceptoCGU() {
		
		JOptionPane.showMessageDialog(null, "Acepto las CGU");
		
		return true;
	}
	
	
	
	/* *************************************************************
	 * Metodo que realiza la grabacion de la aceptacion
	 * de la LPD y de la CGU
	 * 
	 * implementa un metodo de contaDAO que graba 'Y' en ambas
	 * no retorna nada
	 ****************************************************************/
	
	protected void grabaCGULPD(){
		// graba la aceptacion de la LPD y CGU
		this.newUserConta.grabaCGULPD(getUser(),getPassword());
	}
}
