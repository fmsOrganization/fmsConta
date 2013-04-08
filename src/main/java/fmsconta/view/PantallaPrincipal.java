package fmsconta.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fmsconta.control.ContaDAO;





public class PantallaPrincipal extends JFrame implements ActionListener {
	
	// panel principal y variables diversas
	private JDialog pantallaMain;
	private Color colorfondo=new Color(220,220,220);
	// path de los jpg
	private String pathImageFiles="src/main/java/fmsconta/pictures/";
	
	// panel superior title y sus componentes 
	private JPanel panelIco;
	private FlowLayout horizontal0;
	private Image abc;
	private JLabel iconoPortada;
	// panel datos informativos y sus componentes
	private JPanel panelTitle;
	private GridLayout horizontal1;
	private Font fuente;
	private JLabel nombreEmpresa;
	private JLabel nombreUsuario;
	// panel de los combos y sus componentes
	private JPanel panelCombos;
	private BoxLayout horizontal2;
	// panel de la izquierda y sus componentes
	private JPanel panelIzq;
	private BoxLayout vertical;
	private BotonesPanelIzq botonesFijos;
	private Dimension dimens;
	// panel central y sus componentes
	private JPanel panelCen;
	private JPanel panelAuxCen;
	private BoxLayout horizontal6;
	private Font fuente2;
	private JLabel fotoFondo;
	// panel de abajo y sus componentes
	private JPanel panelInf;
	private BoxLayout horizontal3;
	private String textoLeyenda;
	private JLabel leyenda;
	
	// matrices de retorno de DDBB
	private String datosUser[]=new String[15];
	private String datosEmpr[]=new String[12];
	// variables de id y DDBB
	private String company="";			// nombre de la compañia (info pantalla)
	private String year="";				// año de operaciones (info pantalla)
	private String nameUser="";			// nombre del usuario (info pantalla)
	private String keyUser="";			// key del user para DDBB
	private String keyEmpr="";			// key de la empresa para DDBB
	private boolean isManager=false;	// es manager (true) o usuario (false)
	
	
	
	/* *****************************************************************************
	 *  Este metodo builder muestra la pantalla principal
	 *  Asimismo lee en la DDBB los datos del usuario y los datos de la empresa
	 *  actual en la que esta trabajando.
	 *  Deja informacion del keyUser y keyEmpr (keys de sus respectivos ficheros)
	 *  Recibe un Frame principal, el titulo de la pantalla,
	 *  el nombre y el password
	 ******************************************************************************** */
	
	public PantallaPrincipal (JFrame mainWindow, String titulo, String loginUser, String passUser){
		
			
			
			// instanciamos el pool de conexiones ContaDAO
			ContaDAO newUserConta=new ContaDAO();

			// leemos los datos del usuario segun identificacion
			datosUser=newUserConta.idUserDB(loginUser, passUser);
			
			// si no recibimos un null es que la lectura es correcta
			// los datos vienen filtrados desde identificacion
			// por lo que el error seria de lectura
			if (datosUser!=null) {
				// lee datos de la empresa seleccionada
				datosEmpr=newUserConta.idEmpDB(datosUser[10]);
				if (datosEmpr==null) {
					// si no existe empresa creada PROVISIONALMENTE se sale
					System.err.println("Error, no existe la empresa");
					JOptionPane.showMessageDialog(null, "No existe empresa creada");
					System.exit(0);
				} else {
					this.nameUser=datosUser[2];	// nombre del user (info)
					company=datosEmpr[2];	// compañia de trabajo (info)
					year=datosUser[11];		// año de trabajo (info)
					keyUser=datosUser[1]; 	// key del user
					keyEmpr=datosEmpr[1]; 	// key de la empresa
					if (datosEmpr[11].equals(datosUser[1])) isManager=true;	//si es el manager true
				}

			} else {
				// si se produce un error leyendo el usuario finaliza la app
				System.err.println("Error, no existe el user");
				JOptionPane.showMessageDialog(null, "Error leyendo el usuario, no puede continuar");
				System.exit(0);
			}
			
		// ***********************************************
		// ***********************************************
		
		// **** CREAMOS LAS CONDICIONES DE LA VENTANA PRINCIPAL
		
		pantallaMain=new JDialog(mainWindow,titulo,false);
		pantallaMain.setSize(900,600);
		pantallaMain.setLocationRelativeTo(mainWindow);
		pantallaMain.setResizable(true);
		pantallaMain.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pantallaMain.getContentPane().setBackground(colorfondo);
				
		// ******************************CREAMOS EL PANEL GENERAL
		// **** EN ESTE PANEL GENERAL VAN MONTADOS EN FORMA DE GRIDBAGLAYOUT TODOS LOS
		// **** COMPONENTES, QUE SON DISTINTOS TIPOS DE DISTRIBUCION DE PANELES
				
		pantallaMain.getContentPane().setLayout(new GridBagLayout());

		Insets alrededor=new Insets(10,10,10,10);

			// ****************************** CREAMOS EL PANEL ICONO
		panelIco=new JPanel();
		 	// IMAGEN DE PORTADA
		horizontal0=new FlowLayout();
		abc=new ImageIcon(pathImageFiles+"portada.jpg").getImage();
		ImageIcon portada=new ImageIcon(abc.getScaledInstance(125,100,java.awt.Image.SCALE_SMOOTH));
		iconoPortada=new JLabel("",portada,SwingConstants.LEFT);
		panelIco.add(iconoPortada);
		panelIco.setBackground(colorfondo);
			// CREAMOS LA CONFIGURACION DE PANTALLA
		GridBagConstraints constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.weightx=1;
		constraints.weighty=1.5;
		constraints.insets=alrededor;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.WEST;
		pantallaMain.getContentPane().add(panelIco, constraints);
		
			// ****************************** CREAMOS EL PANEL TITLE
		panelTitle=new JPanel();
		horizontal1=new GridLayout(1,2,10,5);
		panelTitle.setLayout(horizontal1);
		nombreEmpresa=new JLabel(company+" - "+year,SwingConstants.CENTER);
		nombreUsuario=new JLabel("usuario: "+nameUser,SwingConstants.CENTER);
			// CREAMOS LOS COLORES DE FONDO Y DE LOS TIPOS DE LETRA
		panelTitle.setBackground(colorfondo);
		panelTitle.setForeground(colorfondo);
		fuente=new Font("",Font.BOLD,24);
		nombreEmpresa.setFont(fuente);
		fuente=new Font("",Font.BOLD,20);
		nombreUsuario.setFont(fuente);
		nombreEmpresa.setForeground(Color.WHITE);
		nombreUsuario.setForeground(Color.WHITE);
			// AÑADIMOS LOS GRAFICOS AL TITLE
		panelTitle.add(nombreEmpresa);
		panelTitle.add(nombreUsuario);
			// CREAMOS LA CONFIGURACION DE PANTALLA
		constraints=new GridBagConstraints();
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.weightx=2;
		constraints.weighty=1.5;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.WEST;
		pantallaMain.getContentPane().add(panelTitle, constraints);
		
		    // ****************************** CREAMOS EL PANEL DE COMBOS
		panelCombos=new JPanel();
		horizontal2=new BoxLayout(panelCombos,BoxLayout.X_AXIS);
		panelCombos.setLayout(horizontal2);
		panelCombos.setBackground(colorfondo);
		    // ELEMENTOS DEL MENU	
		CombosSuperior combos=new CombosSuperior();
			// AÑADIMOS LOS COMBOS AL PANEL SUPERIOR
		panelCombos.add(combos.combo1);
		panelCombos.add(combos.combo2);
		panelCombos.add(combos.combo3);
		panelCombos.add(combos.combo4);
		panelCombos.add(combos.combo5);
			// CREAMOS LA CONFIGURACION DE PANTALLA
		constraints=new GridBagConstraints();
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.weightx=2;
		constraints.weighty=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.WEST;
		pantallaMain.getContentPane().add(panelCombos, constraints);
		
			// ******************************* CREAMOS EL PANEL IZQUIERDO
		panelIzq=new JPanel();
		vertical=new BoxLayout(panelIzq,BoxLayout.Y_AXIS);
		dimens=new Dimension(100,510);
		panelIzq.setPreferredSize(dimens);
		panelIzq.setLayout(vertical);
		panelIzq.setBackground(colorfondo);
		     // CREAMOS LOS BOTONES LATERALES LLAMAMOS A LA CLASE QUE LOS CREA
		botonesFijos=new BotonesPanelIzq();
		     // AÑADIMOS LOS BOTONES AL PANEL IZQUIERDO		
		panelIzq.add(botonesFijos.usuarios);
		panelIzq.add(botonesFijos.empresa);
		panelIzq.add(botonesFijos.faqs);
		panelIzq.add(botonesFijos.boe);
		panelIzq.add(botonesFijos.copseg);
		panelIzq.add(botonesFijos.salida);
			// CREAMOS LA CONFIGURACION DE PANTALLA
		constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.weightx=1;
		constraints.weighty=9;
		constraints.insets=alrededor;
		constraints.fill=GridBagConstraints.VERTICAL;
		constraints.anchor=GridBagConstraints.WEST;
		pantallaMain.getContentPane().add(panelIzq, constraints);
		
			// ************************* CREAMOS EL PANEL CENTRAL
			// el panel central es un JPanel anidado en un JPanel
			// para poder borrar el contenedor interior sin afectar
			// al contenedor exterior
		
		panelCen=new JPanel();
		panelAuxCen=new JPanel();
		
		horizontal6=new BoxLayout(panelAuxCen,BoxLayout.Y_AXIS);
		
		//panelAuxCen.setLayout(horizontal6);
		// CREAMOS LOS COLORES DE FONDO Y DE LOS TIPOS DE LETRA
		fuente2=new Font("",Font.BOLD,16);
		panelCen.setBackground(colorfondo);
		panelAuxCen.setBackground(colorfondo);
		
		    // CREAMOS EL GRAFICO
		abc=new ImageIcon(pathImageFiles+"fondo.jpg").getImage();
		ImageIcon fondo=new ImageIcon(abc.getScaledInstance(600,400,java.awt.Image.SCALE_SMOOTH));
		fotoFondo=new JLabel("",fondo,SwingConstants.CENTER);
			// AÑADIMOS EL GRAFICO
		panelAuxCen.add(fotoFondo);
		panelCen.add(panelAuxCen);
		// CREAMOS LA CONFIGURACION DE PANTALLA
		constraints=new GridBagConstraints();
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.weightx=2;
		constraints.weighty=9;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		pantallaMain.getContentPane().add(panelCen, constraints);
		
		// ************************* CREAMOS EL PANEL INFERIOR
		panelInf=new JPanel();
		horizontal3=new BoxLayout(panelInf,BoxLayout.Y_AXIS);
		panelInf.setLayout(horizontal3);
		panelInf.setBackground(colorfondo);
		     // CREAMOS EL TEXTO INFERIOR
		textoLeyenda="Este programa es propiedad de fms. " +
				"Los usuarios autorizados solamente tienen derechos de uso. " +
				"Prohibida su reproducción sin autorización expresa del propietario.";
		leyenda=new JLabel(textoLeyenda,SwingConstants.CENTER);
		     // AÑADIMOS EL TEXTO AL PANEL INFERIOR
		panelInf.add(leyenda);
		// CREAMOS LA CONFIGURACION DE PANTALLA
		constraints=new GridBagConstraints();
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.weightx=2;
		constraints.weighty=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.EAST;
		pantallaMain.getContentPane().add(panelInf, constraints);
		
			// MOSTRAMOS EL PANEL GENERAL
		pantallaMain.pack();
		pantallaMain.setVisible(true);	


		// CREAMOS LOS LISTENER DE LOS BOTONES
		
		botonesFijos.usuarios.addActionListener(this);
		botonesFijos.empresa.addActionListener(this);
		botonesFijos.faqs.addActionListener(this);
		botonesFijos.boe.addActionListener(this);
		botonesFijos.copseg.addActionListener(this);
		botonesFijos.salida.addActionListener(this);

	} //fin del builder
	
	
	
	/* ***************************************************************
	 * El metodo controla todos los eventos de la aplicacion principal
	 * NO CONTROLA los eventos de las distintas pantallas mostradas
	***************************************************************** */

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		int resp_exit=1;
		
		if (source == botonesFijos.usuarios) {
			panelCen.setVisible(false);	
			panelCen.remove(panelAuxCen);
			panelCen.validate();
			panelAuxCen=new JPanel();
			panelAuxCen.setBackground(colorfondo);
			ContUsuario hola=new ContUsuario(datosUser,datosEmpr,panelCen.getHeight());
			panelAuxCen.add(hola.retorna());
			panelCen.add(panelAuxCen);
			panelCen.setVisible(true);
		}
		
		// si pulsa el boton de empresa
		if (source == botonesFijos.empresa) {
			// borramos el panel central
			panelCen.setVisible(false);	
			panelCen.remove(panelAuxCen);
			panelCen.validate();
			// creamos un nuevo panel central
			panelAuxCen=new JPanel();
			panelAuxCen.setBackground(colorfondo);
			// invocamos y mostramos el nuevo panel central
			ContEmpresa hola=new ContEmpresa(datosEmpr,keyEmpr,nameUser,isManager,datosUser[6],keyUser);
			panelAuxCen.add(hola.retorna());
			panelCen.add(panelAuxCen);
			panelCen.setVisible(true);

		}
		
		if (source == botonesFijos.faqs) {
			panelCen.setVisible(false);	
			JOptionPane.showMessageDialog(null, "faqs pulsada");
		}
		if (source == botonesFijos.boe) {
			panelCen.setVisible(true);	
			JOptionPane.showMessageDialog(null, "BOE pulsada");
		}
		if (source == botonesFijos.copseg) {
			panelCen.setVisible(false);	
			JOptionPane.showMessageDialog(null, "Copia seguridad pulsada");
		}
		if (source == botonesFijos.salida) {
			panelCen.setVisible(false);	
			resp_exit=JOptionPane.showConfirmDialog(null, "¿Desea abandonar la aplicación?","",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE);
			if (resp_exit==0) {
				System.exit(0);
			}
		}
	}

}
