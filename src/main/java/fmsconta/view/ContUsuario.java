package fmsconta.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import fmsconta.control.ContaDAO;
import javax.swing.JOptionPane;


public class ContUsuario extends JFrame implements ActionListener, ItemListener{
	
	// VARIABLES GENERALES
	
	private ContaDAO dao;
	
	private JFrame ventanaListado;

	private Color colorfondo=new Color(220,220,220);
	private Color colorBlanco=new Color(255,255,255);
	private JTabbedPane panTab;
	private JPanel panelEmp;
	
	private Font fuente1=new Font("",Font.BOLD,20);
	private Font fuente2=new Font("",Font.PLAIN,16);
	private int sizeLetras=24;
	private String pathImageFiles="src/main/java/fmsconta/pictures/";
	private Image abc;
	private Icon iconoW;
	private Icon iconoE;
	private Icon iconoD;
	
	private String datosUsuario[];		// datos del usuario que realiza la operativa
	private String datosEmpresa[];		// datos de la empresa activa del usuario operativo
	private int userCat;				// categoria del usuario 1=manager, etc..
	private String datosNuevoUsuario[];	   		// datos del nuevo usuario creado por el manager
	private String datosUsuarioAdmin[][];		// datos de los usuarios administrados por el manager
	private String datosEmprAdmin[][];			// datos de las empresas administradas por el manager
	
	// **** PANTALLA CONSULTAS
	
	private JTable tablaDatos;
	
	private JLabel n0=new JLabel("Consulta de datos de usuario");
	private JLabel l1=new JLabel("     Nombre del usuario");
	private JLabel l2=new JLabel("     Login");
	private JLabel l3=new JLabel("     Password");
	private JLabel l4=new JLabel("     Email");
	private JLabel l5=new JLabel("     Categoría");
	private JLabel l6=new JLabel("     Empresas activado");
	private JLabel l7=new JLabel("     Empresa actual");
	private JLabel l8=new JLabel("     Dirección");
	private JLabel l9=new JLabel("     Localidad");
	private JLabel l10=new JLabel("     Código Postal");
	private JLabel l11=new JLabel("     N.I.F. empresa");
	private JTextField d1=new JTextField();
	private JTextField d2=new JTextField();
	private JTextField d3=new JTextField();
	private JTextField d4=new JTextField();
	private JTextField d5=new JTextField();
	private JTextField d6=new JTextField();
	private JTextField d7=new JTextField();
	private JTextField d8=new JTextField();
	private JTextField d9=new JTextField();
	private JTextField d10=new JTextField();
	private JTextField d11=new JTextField();
	
	private JButton imprimir;
	
	// **** PANTALLA MODIFICACIONES
	
	private JPanel panelUsu2;
	private JPanel panelUsuAux2;
	private JPanel south2;
	private JLabel n1=new JLabel("Modificación de datos de usuario");
	private JLabel l1B=new JLabel("     Nombre del usuario");
	private JLabel l2B=new JLabel("     Login");
	private JLabel l3B=new JLabel("     Password");
	private JLabel l4B=new JLabel("     Email");
	private JLabel l5B=new JLabel("     Categoría");
	private JLabel l6B=new JLabel("     Empresas activado");
	private JLabel l7B=new JLabel("     Empresa actual");
	private JLabel l8B=new JLabel("     Dirección");
	private JLabel l9B=new JLabel("     Localidad");
	private JLabel l10B=new JLabel("     Código Postal");
	private JLabel l11B=new JLabel("     N.I.F. empresa");
	private JTextField d1B=new JTextField();
	private JTextField d2B=new JTextField();
	private JTextField d3B=new JTextField();
	private JTextField d4B=new JTextField();
	private JTextField d5B=new JTextField();
	private JTextField d6B=new JTextField();
	private JTextField d7B=new JTextField();
	private JTextField d8B=new JTextField();
	private JTextField d9B=new JTextField();
	private JTextField d10B=new JTextField();
	private JTextField d11B=new JTextField();
	
	private JButton modificar;
	
	// **** PANTALLA CREAR
	
	private JPanel panelUsu3;
	private JPanel panelUsuAux3;

	private JLabel n3=new JLabel("Creación de usuario");
	private JLabel l1C=new JLabel("     Nombre del usuario");
	private JLabel l2C=new JLabel("     Login");
	private JLabel l3C=new JLabel("     Password");
	private JLabel l4C=new JLabel("     Email");
	private JLabel l5C=new JLabel("     Categoría");
	private JLabel l6C=new JLabel("     Empresas activado");
	private JLabel l7C=new JLabel("     Empresa actual");
	private JLabel l8C=new JLabel("     Dirección");
	private JLabel l9C=new JLabel("     Localidad");
	private JLabel l10C=new JLabel("     Código Postal");
	private JLabel l11C=new JLabel("     N.I.F. empresa");
	private JTextField d1C=new JTextField();
	private JTextField d2C=new JTextField();
	private JTextField d3C=new JTextField();
	private JTextField d4C=new JTextField();
	private JComboBox d5C=new JComboBox();
	private JTextField d6C=new JTextField();
	private JTextField d7C=new JTextField();
	private JTextField d8C=new JTextField();
	private JTextField d9C=new JTextField();
	private JTextField d10C=new JTextField();
	private JTextField d11C=new JTextField();

	private String selectCateg="";
	
	private JButton crear;
	
	
	// **** PANTALLA MANTENIMIENTO
	
	private JPanel panelUsu4;
	private JPanel panelUsuAux4;

	private JLabel n4=new JLabel("Mantenimiento de usuarios",JLabel.CENTER);
	private JLabel l0D=new JLabel("     Usuarios administrados");
	private JLabel l1D=new JLabel("     Nombre usuario");
	private JLabel l2D=new JLabel("     Login");
	private JLabel l3D=new JLabel("     Password");
	private JLabel l4D=new JLabel("     Categoría");
	private JLabel l6D=new JLabel("     Situación actividad");
	private JLabel l7D=new JLabel("     Situacion empresa");
	private JLabel l8D=new JLabel("     Situacion empresa");
	private JLabel l9D=new JLabel("     Situacion empresa");
	private JComboBox d0D=new JComboBox();
	private JTextField d1D=new JTextField();
	private JTextField d2D=new JTextField();
	private JTextField d3D=new JTextField();
	private JComboBox d4D=new JComboBox();
	private JComboBox d6D=new JComboBox();
	private JCheckBox d7D=new JCheckBox();
	private JCheckBox d8D=new JCheckBox();
	private JCheckBox d9D=new JCheckBox();


	private JButton actualizar;
	private JButton eliminar;

	
	
	/* *********************************************************
	 * Este metodo permite mostrar, modificar y borrar los datos
	 * de cada empresa registrada para contabilidad
	 * El metodo recibe una matriz de datos de usuario,
	 * matriz de datos del usuario 
	 * y realiza las acciones pertinentes
	 ********************************************************* */
	
	public ContUsuario(String datosUser[], String datosEmp[]){	
	
		
		// asignamos a variables de clase los datos recibidos
		datosUsuario=datosUser;
		datosEmpresa=datosEmp;
		userCat =(int)Integer.parseInt(datosUsuario[6]);
	
		// primero construimos el panel para consultar
		// *******************************************
		
		consultarPanel(datosUsuario,datosEmpresa);
		
		// segundo construimos el panel para modificar-borrar
		// **************************************************
		
		modificarPanel(datosUsuario,datosEmpresa);

		// tercero construimos el panel para creación nuevas empresas
		// **********************************************************
		creaPanel(datosUsuario,datosEmpresa,userCat);
		
		// cuarto construimos el panel para mantenimiento usuarios
		// **********************************************************
		mantenUsuarios(datosUsuario,datosEmpresa,userCat);

		// y finalmente agregamos los paneles al tabbedPane
		if (userCat==1) { // si es manager
			panTab=panelTabulado(panelEmp,"Consultar",panelUsu2,"Modificar",
					panelUsu3,"Creación",panelUsu4,"Mantenimiento usuarios");
		} else 	panTab=panelTabulado2(panelEmp,"Consultar",panelUsu2,"Modificar"); // si no es manager
		
		// añadimos los itemlistener
		d7D.addItemListener(this);
		d8D.addItemListener(this);
		d9D.addItemListener(this);
		// finalmente añadimos los actionlistener
		imprimir.addActionListener(this);
		modificar.addActionListener(this);
		crear.addActionListener(this);
		eliminar.addActionListener(this);
		actualizar.addActionListener(this);
		d5C.addActionListener(this);
		d0D.addActionListener(this);
		d4D.addActionListener(this);
		d6D.addActionListener(this);	
		
	} //fin del builder
	
	
	public ContUsuario() {
		// builder
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object source=e.getSource();
		
		if (source==imprimir) {
			
			// abre una pantalla para imprimir en un JDialog
			
			JDialog listado=new JDialog(ventanaListado,"Listado de datos de usuario",false);
			listado.setSize(900,600);
			listado.setLocationRelativeTo(rootPane);
			listado.setResizable(true);
			listado.setBackground(colorBlanco);

			listado.getContentPane().setBackground(colorBlanco);
			tablaDatos.setBackground(colorBlanco);
			panelEmp.setBackground(colorBlanco);
			listado.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			listado.add(panelEmp);
			listado.setVisible(true);
			
		}
		
		
		if (source==modificar) {
			// añade los datos modificables de la pantalla visual 
			// a la tabla de datos y luego la graba en la DDBB
			if (aceptaModif()) {
				if (grabaModif(this.datosUsuario)) {
					JOptionPane.showMessageDialog(null, "Usuario MODIFICADO");
				} else {
					JOptionPane.showMessageDialog(null, "ERROR en modificación del usuario");
				}
			}
		}
		
		
		if (source==crear) {
			// añade los datos introducidos en la pantalla visual 
			// a la tabla de datos y luego la graba en la DDBB
			aceptaCreacion();
			// comprueba los datos y si la operativa es posible
			// devuelve true, en caso contrario false
			if(compruebaDatos()) {
				dao=new ContaDAO();
				if (dao.grabaUsuDB(datosNuevoUsuario, "INSERT", userCat)) {
					JOptionPane.showMessageDialog(null, "Usuario CREADO");
				} else JOptionPane.showMessageDialog(null, "ERROR en creación de usuario");
			}
		}
		
		if (source==d5C) {
			// creacion de usuarios:
			// Combobox de categoria -> contable o auxiliar
			this.selectCateg=d5C.getSelectedItem().toString();
			
		}
		
		/* **********************************************************
		 * Zona de lectura de los combos de mantenimiento de usuarios
		 *********************************************************** */
		
		if (source==d0D) {
			// mantenimiento de usuarios:
			// Combobox de usuarios managed
			String buscaUser=d0D.getSelectedItem().toString();
			// cambia los datos segun los correspondientes a la eleccion
			muestraAdministrado(buscaUser);
		}
		
		if (source==d4D) {
			// mantenimiento de usuarios:
			// Combobox de empresas del manager
			d4D.getSelectedItem().toString();
		}
		
		if (source==d6D) {
			// mantenimiento de usuarios:
			// Combobox de empresas del manager
			d6D.getSelectedItem().toString();
		}
		
		if (source==eliminar) {
			// coge el nombre seleccionado del panel 
			// pide una confirmacion y luego la borra en la DDBB
			int optionErase=JOptionPane.showConfirmDialog(null, "¿Desea eliminar definitivamente al usuario seleccionado?", "Borrado de usuario", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (optionErase==0) {
				// si acepta eliminar se coge el nombre del usuario
				// y ejecuta el metodo borraAdministrado
				if (borraAdministrado(d1D.getText())) {
					JOptionPane.showMessageDialog(null, "Usuario borrado");
				} else JOptionPane.showMessageDialog(null, "El usuario no ha podido ser borrado");
			}
		}
		
		if (source==actualizar) {
			// coge el nombre seleccionado del panel 
			String cambiaUser=d0D.getSelectedItem().toString();
			// y llama a la actualizacion
			if (actualizaAdministrado(cambiaUser)) {
				JOptionPane.showMessageDialog(null, "Usuario actualizado");
			} else JOptionPane.showMessageDialog(null, "El usuario no ha podido ser actualizado");
		}
		
	} // fin del actionPerformed
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		Object source=e.getItemSelectable();
		String empr="";
		
		if (source==d7D) {
			// mantenimiento de usuarios:
			// Combobox de empresas del manager
			if (e.getStateChange()==ItemEvent.DESELECTED) {
				l7D.setText("     Situacion empresa");
				// Comprueba cual de las empresa se ha dado de baja
				// y la elimina
				if (datosEmprAdmin[0][1].equals(datosNuevoUsuario[7])){
					datosNuevoUsuario[7]="";
				}
				if (datosEmprAdmin[0][1].equals(datosNuevoUsuario[8])){
					datosNuevoUsuario[8]="";
				}
				if (datosEmprAdmin[0][1].equals(datosNuevoUsuario[9])){
					datosNuevoUsuario[9]="";
				}
				// comprueba si la empresa seleccionada se ha dado de baja
				// y si es asi, la cambia por otra
				if (datosEmprAdmin[0][1].equals(datosNuevoUsuario[10])){
					if (!(datosNuevoUsuario[7].equals(""))) {
						datosNuevoUsuario[10]=datosNuevoUsuario[7];
					} else datosNuevoUsuario[10]=datosNuevoUsuario[8];
				}
			} else {
				l7D.setText("     Situacion empresa: ALTA");
				// Comprueba cual de las empresa se ha dado de alta
				// y la añade al array de usuario
				if (!(datosNuevoUsuario[7].equals(datosEmprAdmin[0][1]) || 
						datosNuevoUsuario[8].equals(datosEmprAdmin[0][1]) || 
						datosNuevoUsuario[9].equals(datosEmprAdmin[0][1]))) {
					// si no esta ya actualizada la empresa, procede a buscarle hueco
					if (datosNuevoUsuario[7].equals("")){
						datosNuevoUsuario[7]=datosEmprAdmin[0][1];
					} else	if (datosNuevoUsuario[8].equals("")){
						datosNuevoUsuario[8]=datosEmprAdmin[0][1];
					} else if (datosNuevoUsuario[9].equals("")){
						datosNuevoUsuario[9]=datosEmprAdmin[0][1];
					}
				}
			}
		}
		
		if (source==d8D) {
			// mantenimiento de usuarios:
			// Combobox de empresas del manager
			if (e.getStateChange()==ItemEvent.DESELECTED) {
				l8D.setText("     Situacion empresa");
				// Comprueba cual de las empresa se ha dado de baja
				// y la elimina
				if (datosEmprAdmin[1][1].equals(datosNuevoUsuario[7])){
					datosNuevoUsuario[7]="";
				}
				if (datosEmprAdmin[1][1].equals(datosNuevoUsuario[8])){
					datosNuevoUsuario[8]="";
				}
				if (datosEmprAdmin[1][1].equals(datosNuevoUsuario[9])){
					datosNuevoUsuario[9]="";
				}
				// comprueba si la empresa seleccionada se ha dado de baja
				// y si es asi, la cambia por otra
				if (datosEmprAdmin[1][1].equals(datosNuevoUsuario[10])){
					if (!(datosNuevoUsuario[7].equals(""))) {
						datosNuevoUsuario[10]=datosNuevoUsuario[7];
					} else datosNuevoUsuario[10]=datosNuevoUsuario[8];
				}
			} else {
				l8D.setText("     Situacion empresa: ALTA");

				// Comprueba cual de las empresa se ha dado de alta
				// y la añade al array de usuario
				if (!(datosNuevoUsuario[7].equals(datosEmprAdmin[1][1]) || 
						datosNuevoUsuario[8].equals(datosEmprAdmin[1][1]) || 
						datosNuevoUsuario[9].equals(datosEmprAdmin[1][1]))) {
					// si no esta ya actualizada la empresa, procede a buscarle hueco
					if (datosNuevoUsuario[7].equals("")){
						datosNuevoUsuario[7]=datosEmprAdmin[1][1];
					} else	if (datosNuevoUsuario[8].equals("")){
						datosNuevoUsuario[8]=datosEmprAdmin[1][1];
					} else if (datosNuevoUsuario[9].equals("")){
						datosNuevoUsuario[9]=datosEmprAdmin[1][1];
					}
				}
			}
		}
		
		if (source==d9D) {
			// mantenimiento de usuarios:
			// Combobox de empresas del manager
			if (e.getStateChange()==ItemEvent.DESELECTED) {
				l9D.setText("     Situacion empresa");
				// Comprueba cual de las empresa se ha dado de baja
				// y la elimina
				if (datosEmprAdmin[2][1].equals(datosNuevoUsuario[7])){
					datosNuevoUsuario[7]="";
				}
				if (datosEmprAdmin[2][1].equals(datosNuevoUsuario[8])){
					datosNuevoUsuario[8]="";
				}
				if (datosEmprAdmin[2][1].equals(datosNuevoUsuario[9])){
					datosNuevoUsuario[9]="";
				}
				// comprueba si la empresa seleccionada se ha dado de baja
				// y si es asi, la cambia por otra
				if (datosEmprAdmin[2][1].equals(datosNuevoUsuario[10])){
					if (!(datosNuevoUsuario[7].equals(""))) {
						datosNuevoUsuario[10]=datosNuevoUsuario[7];
					} else datosNuevoUsuario[10]=datosNuevoUsuario[8];
				}
			} else {
				l9D.setText("     Situacion empresa: ALTA");
				// Comprueba cual de las empresa se ha dado de alta
				// y la añade al array de usuario
				if (!(datosNuevoUsuario[7].equals(datosEmprAdmin[2][1]) || 
						datosNuevoUsuario[8].equals(datosEmprAdmin[2][1]) || 
						datosNuevoUsuario[9].equals(datosEmprAdmin[2][1]))) {
					// si no esta ya actualizada la empresa, procede a buscarle hueco	
					if (datosNuevoUsuario[7].equals("")){
						datosNuevoUsuario[7]=datosEmprAdmin[2][1];
					} else if (datosNuevoUsuario[8].equals("")){
						datosNuevoUsuario[8]=datosEmprAdmin[2][1];
					} else	if (datosNuevoUsuario[9].equals("")){
						datosNuevoUsuario[9]=datosEmprAdmin[2][1];
					}
				}
			}
		}
	}
	
	
	public JTabbedPane retorna() {
		return panTab;
	}


	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, y un String con el manager
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	public void consultarPanel (String datosUsu[], String datosEmp[]) {
		
		
		int categoria=this.userCat;
		
		// creacion del panel principal
		panelEmp=new JPanel();
		panelEmp.setLayout(new BoxLayout(panelEmp,BoxLayout.Y_AXIS));
		panelEmp.setAlignmentY(CENTER_ALIGNMENT);
		panelEmp.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		JPanel panelEmpAux = new JPanel();
		panelEmpAux.setLayout(new GridLayout(11,2));
		panelEmpAux.setAlignmentY(CENTER_ALIGNMENT);
		panelEmpAux.setFont(fuente2);
		panelEmpAux.setBackground(colorfondo);
		      
		// datos de la empresa activa actual
		d1.setText(datosUsu[2]);	// nombre usuario
		d2.setText(datosUsu[3]);	// login
		d3.setText(datosUsu[4]);	// password
		d4.setText(datosUsu[5]);	// email
		// categoria
		if (categoria==1) {
			d5.setText("Manager");
		} else if (categoria==2) {
			d5.setText("Contable");
		} else d5.setText("Auxiliar");
		// nº de empresas
		int numemp=1;
		if (!(datosUsu[8].equals("")|| datosUsu[8]==null)) numemp++;
		if (!(datosUsu[9].equals("")|| datosUsu[9]==null)) numemp++;
		d6.setText(String.valueOf(numemp));
		d7.setText(datosEmp[2]);	// Empresa activa
		d8.setText(datosEmp[3]);	// direccion
		d9.setText(datosEmp[4]);	// localidad
		d10.setText(datosEmp[6]);	// Cod. Postal
		d11.setText(datosEmp[7]);	// CIF
		
		// definicion de celdas no editables
		d1.setEditable(false);
		d2.setEditable(false);
		d3.setEditable(false);
		d4.setEditable(false);
		d5.setEditable(false);
		d6.setEditable(false);
		d7.setEditable(false);
		d8.setEditable(false);
		d9.setEditable(false);
		d10.setEditable(false);
		d11.setEditable(false);
		
		// add de componentes al panel rejilla
		panelEmpAux.add(l1);
		panelEmpAux.add(d1);
		panelEmpAux.add(l2);
		panelEmpAux.add(d2);
		panelEmpAux.add(l3);
		panelEmpAux.add(d3);
		panelEmpAux.add(l4);
		panelEmpAux.add(d4);
		panelEmpAux.add(l5);
		panelEmpAux.add(d5);
		panelEmpAux.add(l6);
		panelEmpAux.add(d6);
		panelEmpAux.add(l7);
		panelEmpAux.add(d7);
		panelEmpAux.add(l8);
		panelEmpAux.add(d8);
		panelEmpAux.add(l9);
		panelEmpAux.add(d9);
		panelEmpAux.add(l10);
		panelEmpAux.add(d10);
		panelEmpAux.add(l11);
		panelEmpAux.add(d11);
	
		// separadores en panel principal
		JLabel espacioA=new JLabel(" ");
		JLabel espacioB=new JLabel(" ");
		
	    // cambiar el font al titulo
	    n0.setFont(fuente1);
	    
	    // creacion del panel inferior
	    JPanel south=new JPanel();
	    south.setBackground(colorfondo);
	    imprimir=new JButton("Imprimir");
	    imprimir.setToolTipText("abre una pantalla aparte");
	    south.add(imprimir);
	    
	    panelEmpAux.setVisible(true);
	    // agregamos componentes al panel principal
	    panelEmp.add(n0);
	    panelEmp.add(espacioA);
	    panelEmp.add(panelEmpAux);
	    panelEmp.add(espacioB);
	    panelEmp.add(south);
		
		
	} // fin del metodo consultarPanel
	
	
	
	/* *************************************************************************
	 * Este metodo permite la modificacion de la tabla datos empresa
	 * Algunos datos no pueden ser modificados y son solo informativos
	 * Otros datos solo pueden ser modificados por el manager de la empresa
	 * Finalmente, otros datos son modificables por todos
	 * Se reciben un string[] con datos de empresa, String con el manager
	 * y un boolean si el usuario es el manager
	 * Este metodo no retorna, modifica directamente las variables de clase
	 ************************************************************************** */
	
	public void modificarPanel (String datosUsu[], String datosEmp[]) {
		
		int categoria=this.userCat;
		
		// creacion del panel principal
		panelUsu2=new JPanel();
		panelUsu2.setLayout(new BoxLayout(panelUsu2,BoxLayout.Y_AXIS));
		panelUsu2.setAlignmentX(CENTER_ALIGNMENT);
		panelUsu2.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		panelUsuAux2 = new JPanel();
		panelUsuAux2.setLayout(new GridLayout(11,3));
		panelUsuAux2.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux2.setFont(fuente2);
		panelUsuAux2.setBackground(colorfondo);
		
		// datos del usuario activo actual
		d1B.setText(datosUsu[2]);	// nombre usuario
		d2B.setText(datosUsu[3]);	// login
		d3B.setText(datosUsu[4]);	// password
		d4B.setText(datosUsu[5]);	// email
		// categoria
		if (categoria==1) {
			d5B.setText("Manager");
		} else if (categoria==2) {
			d5B.setText("Contable");
		} else d5B.setText("Auxiliar");
		// nº de empresas
		int numemp=1;
		if (!(datosUsu[8].equals("")|| datosUsu[8]==null)) numemp++;
		if (!(datosUsu[9].equals("")|| datosUsu[9]==null)) numemp++;
		d6B.setText(String.valueOf(numemp));
		d7B.setText(datosEmp[2]);	// Empresa activa
		d8B.setText(datosEmp[3]);	// direccion
		d9B.setText(datosEmp[4]);	// localidad
		d10B.setText(datosEmp[6]);	// Cod. Postal
		d11B.setText(datosEmp[7]);	// CIF
				
		// definicion de celdas no editables
		d1B.setEditable(false);
		d2B.setEditable(true);
		d3B.setEditable(true);
		d4B.setEditable(true);
		d5B.setEditable(false);
		d6B.setEditable(false);
		d7B.setEditable(false);
		d8B.setEditable(false);
		d9B.setEditable(false);
		d10B.setEditable(false);
		d11B.setEditable(false);
		if (categoria==1) {
			// los usuarios manager pueden modificar estas
			d1B.setEditable(true);
		}
				
		// elaboracion de iconos
		abc=new ImageIcon(pathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"enable.jpg").getImage();
		iconoE=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		abc=new ImageIcon(pathImageFiles+"disable.jpg").getImage();
		iconoD=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		// definicion de comentarios edicion
		int tabulacion;
				
		// si categoria es manager
		if (categoria==1) {
			tabulacion=JLabel.LEFT;
		} else {
			tabulacion=JLabel.CENTER;
		}
				
		JLabel sp1=new JLabel(" Longitud entre 3 y 25",iconoW,tabulacion);
		sp1.setToolTipText("Campo obligatorio");
		JLabel sp2=new JLabel(" Longitud entre 5 y 10",iconoE,JLabel.LEFT);
		sp2.setToolTipText("Campo obligatorio");
		JLabel sp3=new JLabel(" Longitud entre 5 y 10",iconoE,JLabel.LEFT);
		sp3.setToolTipText("Campo obligatorio");
		JLabel sp4=new JLabel("permitido",iconoE,JLabel.LEFT);
		sp4.setToolTipText("Campo obligatorio");
		JLabel sp5=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp6=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp7=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp8=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp9=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp10=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp11=new JLabel("no permitido",iconoD,JLabel.CENTER);
				
				// add de componentes al panel rejilla
				panelUsuAux2.add(l1B);
				panelUsuAux2.add(d1B);
				panelUsuAux2.add(sp1);
				panelUsuAux2.add(l2B);
				panelUsuAux2.add(d2B);
				panelUsuAux2.add(sp2);
				panelUsuAux2.add(l3B);
				panelUsuAux2.add(d3B);
				panelUsuAux2.add(sp3);
				panelUsuAux2.add(l4B);
				panelUsuAux2.add(d4B);
				panelUsuAux2.add(sp4);
				panelUsuAux2.add(l5B);
				panelUsuAux2.add(d5B);
				panelUsuAux2.add(sp5);
				panelUsuAux2.add(l6B);
				panelUsuAux2.add(d6B);
				panelUsuAux2.add(sp6);
				panelUsuAux2.add(l7B);
				panelUsuAux2.add(d7B);
				panelUsuAux2.add(sp7);
				panelUsuAux2.add(l8B);
				panelUsuAux2.add(d8B);
				panelUsuAux2.add(sp8);
				panelUsuAux2.add(l9B);
				panelUsuAux2.add(d9B);
				panelUsuAux2.add(sp9);
				panelUsuAux2.add(l10B);
				panelUsuAux2.add(d10B);
				panelUsuAux2.add(sp10);
				panelUsuAux2.add(l11B);
				panelUsuAux2.add(d11B);
				panelUsuAux2.add(sp11);
			
		// separadores en panel principal
		JLabel espacioA=new JLabel(" ");
		JLabel espacioB=new JLabel(" ");
		
		// cambiar el font al titulo
		n1.setFont(fuente1);
		
			    
	    // creacion del panel de botones inferior
	    south2=new JPanel();
	    south2.setBackground(colorfondo);
	    modificar=new JButton("Modificar");
	    modificar.setToolTipText("guarda los datos actuales");

	    south2.add(modificar);
	    
	    // agregamos componentes al panel principal
	    panelUsu2.add(n1);
	    panelUsu2.add(espacioA);
	    panelUsu2.add(panelUsuAux2);
	    panelUsu2.add(espacioB);
	    panelUsu2.add(south2);
		
	} // fin del metodo modificarPanel
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, String nombre de usuario y
	 * un int con la categoria del usuario (solo cat=1 puede actualizar)
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	public void creaPanel (String datosUsu[], String datosEmp[], int userCat) {
		
		
		// creacion del panel principal
		panelUsu3=new JPanel();
		panelUsu3.setLayout(new BoxLayout(panelUsu3,BoxLayout.Y_AXIS));
		panelUsu3.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu3.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		panelUsuAux4 = new JPanel();
		panelUsuAux4.setLayout(new GridLayout(10,3));
		panelUsuAux4.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux4.setFont(fuente2);
		panelUsuAux4.setBackground(colorfondo);
		    

		// datos del usuario activo actual
		d1C.setText("");	// nombre usuario
		d2C.setText("");	// login
		d3C.setText("");	// password
		d4C.setText("");	// email
		// categoria
		d5C.addItem("Elegir");
		d5C.addItem("Contable");
		d5C.addItem("Auxiliar");
		
		// d6C.setText("");	// nº de empresas - NO SE MUESTRA EN CREACION
		d7C.setText(datosEmp[2]);	// Empresa activa
		d8C.setText(datosEmp[3]);	// direccion
		d9C.setText(datosEmp[4]);	// localidad
		d10C.setText(datosEmp[6]);	// Cod. Postal
		d11C.setText(datosEmp[7]);	// CIF
				
		d7C.setEditable(false);
		d8C.setEditable(false);
		d9C.setEditable(false);
		d10C.setEditable(false);
		d11C.setEditable(false);
		  
		
		abc=new ImageIcon(pathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		
		// definicion de comentarios edicion
		JLabel sc1=new JLabel(" Longitud entre 3 y 25",iconoW,JLabel.LEFT);
		sc1.setToolTipText("Campo obligatorio");
		JLabel sc2=new JLabel(" Longitud entre 5 y 10",iconoW,JLabel.LEFT);
		sc2.setToolTipText("Campo obligatorio");
		JLabel sc3=new JLabel(" Longitud entre 5 y 10",iconoW,JLabel.LEFT);
		sc3.setToolTipText("Campo obligatorio");
		JLabel sc4=new JLabel(" máx. longitud 50");
		JLabel sc5=new JLabel(" Contable o Auxiliar");
		sc5.setToolTipText("Campo obligatorio");
		// JLabel sc6=new JLabel(" INFO "); - NO SE MUESTRA EN CREACION
		JLabel sc7=new JLabel(" INFO ");
		JLabel sc8=new JLabel(" INFO ");
		JLabel sc9=new JLabel(" INFO ");
		JLabel sc10=new JLabel(" INFO ");
		JLabel sc11=new JLabel(" INFO ");
		
		
		// add de componentes al panel rejilla
		panelUsuAux4.add(l1C);
		panelUsuAux4.add(d1C);
		panelUsuAux4.add(sc1);
		panelUsuAux4.add(l2C);
		panelUsuAux4.add(d2C);
		panelUsuAux4.add(sc2);
		panelUsuAux4.add(l3C);
		panelUsuAux4.add(d3C);
		panelUsuAux4.add(sc3);
		panelUsuAux4.add(l4C);
		panelUsuAux4.add(d4C);
		panelUsuAux4.add(sc4);
		panelUsuAux4.add(l5C);
		panelUsuAux4.add(d5C);
		panelUsuAux4.add(sc5);
		//panelUsuAux4.add(l6C); 	- NO SE MUESTRA EN CREACION
		//panelUsuAux4.add(d6C);	- NO SE MUESTRA EN CREACION
		//panelUsuAux4.add(sc6);	- NO SE MUESTRA EN CREACION
		panelUsuAux4.add(l7C);
		panelUsuAux4.add(d7C);
		panelUsuAux4.add(sc7);
		panelUsuAux4.add(l8C);
		panelUsuAux4.add(d8C);
		panelUsuAux4.add(sc8);
		panelUsuAux4.add(l9C);
		panelUsuAux4.add(d9C);
		panelUsuAux4.add(sc9);
		panelUsuAux4.add(l10C);
		panelUsuAux4.add(d10C);
		panelUsuAux4.add(sc10);
		panelUsuAux4.add(l11C);
		panelUsuAux4.add(d11C);
		panelUsuAux4.add(sc11);
		
		// separadores en panel principal
		JLabel espacioY=new JLabel(" ");
		JLabel espacioZ=new JLabel(" ");
		
	    // cambiar el font al titulo
	    n3.setFont(fuente1);
	    
	    // creacion del panel inferior
	    JPanel south3=new JPanel();
	    south3.setBackground(colorfondo);
	    crear=new JButton("crear usuario");
	    crear.setToolTipText("crea un usuario con los datos introducidos");
	    if (userCat!=1) {
	    	crear.setEnabled(false);
	    	crear.setToolTipText("Solo pueden crear usuarios los managers");
	    }
	    south3.add(crear);
	    

	    // agregamos componentes al panel principal
	    panelUsu3.add(n3);
	    panelUsu3.add(espacioY);
	    panelUsu3.add(panelUsuAux4);
	    panelUsu3.add(espacioZ);
	    panelUsu3.add(south3);

	
	} // fin del metodo creaTabla
	
	
	
	/* ************************************************************************
	 * Este metodo permite el mantenimiento de los usuarios creados por manager
	 * 
	 * Se reciben un string[] con datos de empresa, String nombre de usuario y
	 * un int con la categoria del usuario (solo cat=1 puede actualizar)
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	public void mantenUsuarios (String datosUsu[], String datosEmp[], int userCat) {
		
		
		// creacion del panel principal
		panelUsu4=new JPanel();
		panelUsu4.setLayout(new BoxLayout(panelUsu4,BoxLayout.Y_AXIS));
		panelUsu4.setAlignmentY(CENTER_ALIGNMENT);
		panelUsu4.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		panelUsuAux4 = new JPanel();
		panelUsuAux4.setLayout(new GridLayout(9,3));
		panelUsuAux4.setAlignmentY(CENTER_ALIGNMENT);
		panelUsuAux4.setFont(fuente2);
		panelUsuAux4.setBackground(colorfondo);
		    

		// para crear el panel, primero busca las empresas del manager
		
		datosEmprAdmin=buscaEmpresasManager(datosUsu[1]);
		
		if (!(datosEmprAdmin==null || datosEmprAdmin[0][2]==null)) {
			d7D.setText(datosEmprAdmin[0][2]);
		}
		if (!(datosEmprAdmin==null || datosEmprAdmin[1][2]==null)) {
			d8D.setText(datosEmprAdmin[1][2]);
		}
		if (!(datosEmprAdmin==null || datosEmprAdmin[2][2]==null)) {
			d9D.setText(datosEmprAdmin[2][2]);
		}
	
		
		// usuarios administrados 
		datosUsuarioAdmin=new String[9][16];
		// obtenemos la info de la DDBB
		datosUsuarioAdmin=buscaUsuariosManager(datosUsu[1]);
		// es necesario suministrar la empresa para el combobox
		int j=0;
		while (datosUsuarioAdmin!=null && datosUsuarioAdmin[j][1]!="" && datosUsuarioAdmin[j][1]!=null) {
			// guarda el additem de la empresa seleccionado en cada usuario administrado
			datosUsuarioAdmin[j][15]="1"; //asignamos por defecto empresa additem1
			if (datosUsuarioAdmin[j][10].equals(datosEmprAdmin[1][1])) datosUsuarioAdmin[j][15]="2";
			if (datosUsuarioAdmin[j][10].equals(datosEmprAdmin[2][1])) datosUsuarioAdmin[j][15]="3";
			j++;
			if (j==6) break; // fin de bucle, por si acaso
		}
		
		// añadimos al combobox
		d0D.addItem("Elija usuario");
		if ( datosUsuarioAdmin!=null && datosUsuarioAdmin[0][2]!="") d0D.addItem(datosUsuarioAdmin[0][2]);
		if ( datosUsuarioAdmin!=null && datosUsuarioAdmin[1][2]!="" && datosUsuarioAdmin[1][2]!=null
				&& !datosUsuarioAdmin[1][2].equals(datosUsuarioAdmin[0][2])) d0D.addItem(datosUsuarioAdmin[1][2]);
		if ( datosUsuarioAdmin!=null && datosUsuarioAdmin[2][2]!="" && datosUsuarioAdmin[2][2]!=null
				&& !datosUsuarioAdmin[2][2].equals(datosUsuarioAdmin[0][2]) 
				&& !datosUsuarioAdmin[2][2].equals(datosUsuarioAdmin[1][2])) d0D.addItem(datosUsuarioAdmin[2][2]);
		if ( datosUsuarioAdmin!=null && datosUsuarioAdmin[3][2]!="" && datosUsuarioAdmin[3][2]!=null
				&& !datosUsuarioAdmin[3][2].equals(datosUsuarioAdmin[0][2])
				&& !datosUsuarioAdmin[3][2].equals(datosUsuarioAdmin[1][2])
				&& !datosUsuarioAdmin[3][2].equals(datosUsuarioAdmin[2][2])) d0D.addItem(datosUsuarioAdmin[3][2]);
		if ( datosUsuarioAdmin!=null && datosUsuarioAdmin[4][2]!="" && datosUsuarioAdmin[4][2]!=null
				&& !datosUsuarioAdmin[4][2].equals(datosUsuarioAdmin[0][2])
				&& !datosUsuarioAdmin[4][2].equals(datosUsuarioAdmin[1][2])
				&& !datosUsuarioAdmin[4][2].equals(datosUsuarioAdmin[2][2])
				&& !datosUsuarioAdmin[4][2].equals(datosUsuarioAdmin[3][2])) d0D.addItem(datosUsuarioAdmin[4][2]);
		if ( datosUsuarioAdmin!=null && datosUsuarioAdmin[5][2]!="" && datosUsuarioAdmin[5][2]!=null
				&& !datosUsuarioAdmin[5][2].equals(datosUsuarioAdmin[0][2])
				&& !datosUsuarioAdmin[5][2].equals(datosUsuarioAdmin[1][2])
				&& !datosUsuarioAdmin[5][2].equals(datosUsuarioAdmin[2][2])
				&& !datosUsuarioAdmin[5][2].equals(datosUsuarioAdmin[3][2])
				&& !datosUsuarioAdmin[5][2].equals(datosUsuarioAdmin[4][2])) d0D.addItem(datosUsuarioAdmin[5][2]);
		
		d1D.setText("");	// nombre
		d2D.setText("");	// login
		d3D.setText("");	// password
		// categoria
		d4D.addItem("Elija categoría");
		d4D.addItem("Contable");
		d4D.addItem("Auxiliar");
		
		// situación de actividad
		d6D.addItem("Situación");
		d6D.addItem("Activo");
		d6D.addItem("Desactivado");
			
		abc=new ImageIcon(pathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		
		// definicion de comentarios edicion
		JLabel sc0=new JLabel(" Seleccione administrado");
		JLabel sc1=new JLabel(" Longitud entre 3 y 25",iconoW,JLabel.LEFT);
		sc1.setToolTipText("Campo obligatorio");
		JLabel sc2=new JLabel(" Longitud entre 5 y 10",iconoW,JLabel.LEFT);
		sc2.setToolTipText("Campo obligatorio");
		JLabel sc3=new JLabel(" Longitud entre 5 y 10",iconoW,JLabel.LEFT);
		sc3.setToolTipText("Campo obligatorio");
		JLabel sc4=new JLabel(" Contable o Auxiliar");
		sc4.setToolTipText("Campo obligatorio");
		JLabel sc6=new JLabel(" Activo o Desactivado");
		sc6.setToolTipText("Campo obligatorio");
		JLabel sc7=new JLabel(" Activar como usuario");
		JLabel sc8=new JLabel(" Activar como usuario");
		JLabel sc9=new JLabel(" Activar como usuario");

		
		// add de componentes al panel rejilla
		panelUsuAux4.add(l0D);
		panelUsuAux4.add(d0D);
		panelUsuAux4.add(sc0);
		panelUsuAux4.add(l1D);
		panelUsuAux4.add(d1D);
		panelUsuAux4.add(sc1);
		panelUsuAux4.add(l2D);
		panelUsuAux4.add(d2D);
		panelUsuAux4.add(sc2);
		panelUsuAux4.add(l3D);
		panelUsuAux4.add(d3D);
		panelUsuAux4.add(sc3);
		panelUsuAux4.add(l4D);
		panelUsuAux4.add(d4D);
		panelUsuAux4.add(sc4);
		panelUsuAux4.add(l6D);
		panelUsuAux4.add(d6D);
		panelUsuAux4.add(sc6);
		if (!(datosEmprAdmin==null || datosEmprAdmin[0][2]==null)) {
			panelUsuAux4.add(l7D);
			panelUsuAux4.add(d7D);
			panelUsuAux4.add(sc7);
		}
		if (!(datosEmprAdmin==null || datosEmprAdmin[1][2]==null)) {
			panelUsuAux4.add(l8D);
			panelUsuAux4.add(d8D);
			panelUsuAux4.add(sc8);
		}
		if (!(datosEmprAdmin==null || datosEmprAdmin[2][2]==null)) {
			panelUsuAux4.add(l9D);
			panelUsuAux4.add(d9D);
			panelUsuAux4.add(sc9);
		}

		// separadores en panel principal
		JLabel espacioX=new JLabel(" ");
		JLabel espacioW=new JLabel(" ");
		
	    // cambiar el font al titulo
	    n4.setFont(fuente1);
	    
	    // creacion del panel inferior
	    JPanel south4=new JPanel();
	    south4.setBackground(colorfondo);
	    actualizar=new JButton("actualizar usuario");
	    actualizar.setToolTipText("actualiza los datos registrados");
	    eliminar=new JButton("eliminar usuario");
	    eliminar.setToolTipText("elimina definitivamente el usuario");
	    if (userCat!=1) {
	    	actualizar.setEnabled(false);
	    	actualizar.setToolTipText("Solo pueden actualizar usuarios los managers");
	    }
	    south4.add(actualizar);
	    south4.add(eliminar);
	    

	    // agregamos componentes al panel principal
	    panelUsu4.add(n4);
	    panelUsu4.add(espacioX);
	    panelUsu4.add(panelUsuAux4);
	    panelUsu4.add(espacioW);
	    panelUsu4.add(south4);

	
	} // fin del metodo mantenUsuarios
	
	
	
	
	/* ******************************************************************************
	 * Este metodo crea un tabbedpane de cuatro pestañas con la informacion recibida
	 * recibe cuatro JPanel con sus string titulos correspondientes
	 * devuelve un objeto tabbedpane - es la version para managers
	 * 
	 * La version panelTabulado2 hace lo mismo pero solo con dos pestañas
	 * es una version para usuarios no manager
	 **************************************************************************** */


	public JTabbedPane panelTabulado(JPanel tab1,String title1,JPanel tab2,String title2,
			JPanel tab3,String title3,JPanel tab4,String title4) {
		
		setTitle("");
		JTabbedPane menu=new JTabbedPane();
		menu.setBackground(colorfondo);
		menu.addTab(title1,tab1);
		menu.addTab(title2,tab2);
		menu.addTab(title3,tab3);
		menu.addTab(title4,tab4);
		getContentPane().add(menu);
		return menu;
		
	} // fin del metodo panelTabulado
	
	
	public JTabbedPane panelTabulado2(JPanel tab1,String title1,JPanel tab2,String title2) {
		
		setTitle("");
		JTabbedPane menu=new JTabbedPane();
		menu.setBackground(colorfondo);
		menu.addTab(title1,tab1);
		menu.addTab(title2,tab2);
		getContentPane().add(menu);
		return menu;
		
	} // fin del metodo panelTabulado2
	
	
	
	/* ************************************************************************
	 * Este metodo realiza la modificacion de la tabla datos usuario
	 * Actuando sobre el string[] con los datos de usuario
	 * Este metodo no tiene parametros
	 * Retorna true si todo va bien o false si hay algun error
	 ************************************************************************** */
	
	private boolean aceptaModif() {
		
		d2B.setBackground(Color.WHITE);
		d3B.setBackground(Color.WHITE);
		d4B.setBackground(Color.WHITE);
		
		// datos de la empresa actualizados a las casillas editables
		
		datosUsuario[2]=d1B.getText();		// nombre usuario
		
		// ************** login ******************* 
		datosUsuario[3]=d2B.getText();			
		if(datosUsuario[3].contains(" ")) {
			JOptionPane.showMessageDialog(null, "Login no debe contener espacios");
			d2B.setBackground(Color.ORANGE);
			return false;
		}
		if ((datosUsuario[3].equals("") || datosUsuario[3].equals(null))) {
			JOptionPane.showMessageDialog(null, "Login no rellenado");
			d2B.setBackground(Color.ORANGE);
			return false;
		}
		if (datosUsuario[3].length()<5 || datosUsuario[3].length()>10) {
			JOptionPane.showMessageDialog(null, "el login tiene longitud inadecuada" );
			d2B.setBackground(Color.ORANGE);
			return false;
		}
		// ************** password ******************* 
		datosUsuario[4]=d3B.getText();			
		if(datosUsuario[4].contains(" ")) {
			JOptionPane.showMessageDialog(null, "Contraseña no debe contener espacios");
			d3B.setBackground(Color.ORANGE);
			return false;
		}
		if ((datosUsuario[4].equals("") || datosUsuario[4].equals(null))) {
			JOptionPane.showMessageDialog(null, "contraseña no rellenada");
			d3B.setBackground(Color.ORANGE);
			return false;
		}
		if (datosUsuario[4].length()<5 || datosUsuario[4].length()>10) {
			JOptionPane.showMessageDialog(null, "la contraseña tiene longitud inadecuada" );
			d3B.setBackground(Color.ORANGE);
			return false;
		}
		// ************** email ******************* 
		datosUsuario[5]=d4B.getText();			
		if (datosUsuario[5].length()>50) {
			JOptionPane.showMessageDialog(null, "el email tiene tiene longitud inadecuada" );
			d4B.setBackground(Color.ORANGE);
			return false;
		}
		
		return true;
		
	} // fin del metodo aceptaModif
	
	
	
	/* ************************************************************************
	 * Este metodo graba la modificacion de la tabla usuarios en la DDBB
	 * 
	 * Este metodo recibe el fichero de usuario como parametro
	 * Devuelve valor de retorno true/false segun haya ido la grabacion
	 ************************************************************************** */

	private boolean grabaModif(String modifUsuario[]) {
		
		// Instanciamos el metodo DAO para efectuar la grabacion
		
		dao=new ContaDAO();
		
		// graba los datos residentes en datosUsuario en la DDBB
		// si falla retorna false
		
		return dao.grabaUsuDB (modifUsuario, "UPDATE", userCat);
		
	} // fin del metodo grabaModif
	
	
	
	/* ************************************************************************
	 * Este metodo realiza la creacion de la tabla datos usuario
	 * Creando un nuevo string[] con los datos del usuario
	 * 
	 * Recoge algunos datos del formulario de creacion
	 * Hereda otros datos desde el manager que los crea
	 * Otros datos se crean como default tal cual
	 * 
	 * Este metodo no recibe parametros
	 * No devuelve nada
	 ************************************************************************** */
	
	private void aceptaCreacion() {
		
		datosNuevoUsuario=new String[15];
		datosNuevoUsuario[0]="";					// ES UN AUTOINCREMENT DDBB
		datosNuevoUsuario[1]="";					// keyUser se crea en compruebaDatos()
		datosNuevoUsuario[2]=d1C.getText();			// nombre usuario
		datosNuevoUsuario[3]=d2C.getText();			// login
		datosNuevoUsuario[4]=d3C.getText();			// password
		datosNuevoUsuario[5]=d4C.getText();			// email
		datosNuevoUsuario[6]=this.selectCateg;		// categoria: HEREDADA
		datosNuevoUsuario[7]=this.datosEmpresa[1];	// emp1: HEREDADA
		datosNuevoUsuario[8]="";						// emp2
		datosNuevoUsuario[9]="";						// emp3
		datosNuevoUsuario[10]=this.datosEmpresa[1];	// empselec: HEREDADA
		datosNuevoUsuario[11]=this.datosUsuario[11];	// yearselec: HEREDADA
		datosNuevoUsuario[12]="1";					// activo
		datosNuevoUsuario[13]="N";					// lpg
		datosNuevoUsuario[14]="N";					// cgu
			
		
	} // fin del metodo aceptaCreacion
	
	
	
	/* ************************************************************************
	 * Este metodo realiza la comprobacion y ajuste en su caso de los datos
	 * del usuario a grabar en DDBB
	 * 
	 * Comprueba si el idUser no esta duplicado, y si lo esta lo corrige
	 * Si el login/password esta duplicado, avisa y devuelve false
	 * Si algun dato falta, avisa y devuelve false
	 * Si algun dato falla por longitud, avisa y devuelve false
	 * 
	 * Este metodo no recibe parametros
	 * Devuelve un true si es posible la grabacion, y false en caso contrario
	 ************************************************************************** */
	
	private boolean compruebaDatos() {
		
		// antes de nada, comprueba cuantos usuarios hay creados en esa empresa
		// si hay tres usuarios, ya no pueden crearse mas
		
		if (numUsuariosAutorizados(this.datosEmpresa[1])>2) {
			JOptionPane.showMessageDialog(null, "No es posible crear más usuarios\nEn esta empresa ya hay 3 usuarios creados");
			return false;
		}
		
		// Inicia la revision de los datos introducidos y si se ajustan al perfil
		
		// reseteamos los posible resaltes backgrounds de errores anteriores
		d1C.setBackground(Color.WHITE);
		d2C.setBackground(Color.WHITE);
		d3C.setBackground(Color.WHITE);
		d4C.setBackground(Color.WHITE);

		// ************** nombre usuario ******************* 
		datosNuevoUsuario[2]=d1C.getText();			
		if ((datosNuevoUsuario[2].equals("") || datosNuevoUsuario[2].equals(null))) {
			JOptionPane.showMessageDialog(null, "nombre de usuario no rellenado");
			d1C.setBackground(Color.ORANGE);
			return false;
		}
		if (datosNuevoUsuario[2].length()<3 || datosNuevoUsuario[2].length()>25) {
			JOptionPane.showMessageDialog(null, "el nombre tiene longitud inadecuada" );
			d1C.setBackground(Color.ORANGE);
			return false;
		}
		// ************** login ******************* 
		datosNuevoUsuario[3]=d2C.getText();			
		if(datosNuevoUsuario[3].contains(" ")) {
			JOptionPane.showMessageDialog(null, "Login no debe contener espacios");
			d2C.setBackground(Color.ORANGE);
			return false;
		}
		if ((datosNuevoUsuario[3].equals("") || datosNuevoUsuario[3].equals(null))) {
			JOptionPane.showMessageDialog(null, "Login no rellenado");
			d2C.setBackground(Color.ORANGE);
			return false;
		}
		if (datosNuevoUsuario[3].length()<5 || datosNuevoUsuario[3].length()>10) {
			JOptionPane.showMessageDialog(null, "el login tiene longitud inadecuada" );
			d2C.setBackground(Color.ORANGE);
			return false;
		}
		// ************** password ******************* 
		datosNuevoUsuario[4]=d3C.getText();			
		if(datosNuevoUsuario[4].contains(" ")) {
			JOptionPane.showMessageDialog(null, "Contraseña no debe contener espacios");
			d3C.setBackground(Color.ORANGE);
			return false;
		}
		if ((datosNuevoUsuario[4].equals("") || datosNuevoUsuario[4].equals(null))) {
			JOptionPane.showMessageDialog(null, "contraseña no rellenada");
			d3C.setBackground(Color.ORANGE);
			return false;
		}
		if (datosNuevoUsuario[4].length()<5 || datosNuevoUsuario[4].length()>10) {
			JOptionPane.showMessageDialog(null, "la contraseña tiene longitud inadecuada" );
			d3C.setBackground(Color.ORANGE);
			return false;
		}
		// ************** email ******************* 
		datosNuevoUsuario[5]=d4C.getText();			
		if (datosNuevoUsuario[5].length()>50) {
			JOptionPane.showMessageDialog(null, "el email tiene tiene longitud inadecuada" );
			d4C.setBackground(Color.ORANGE);
			return false;
		}
		// ************** categoria ******************* 
		if (this.selectCateg=="Contable") {
			datosNuevoUsuario[6]="2"; 					// Contable
		} else datosNuevoUsuario[6]="3"; 				// Auxiliar
		
		// ************** keyUser ******************* 	
		// instancia un objeto de comunicaciones
		dao=new ContaDAO();
		// el keyUser se construye utilizando fragmentos del nombre - password - login - aleatorio
		datosNuevoUsuario[1]=d1C.getText().substring(0, 2)+d3C.getText().substring(0, 3)+d2C.getText().substring(0, 3)+String.valueOf(10+(int)(Math.floor((Math.random()*88)+1)));;
		// elimina los espacios por A
		datosNuevoUsuario[1]=datosNuevoUsuario[1].replace(' ', 'A');
		// comprueba y corrige longitud del idUser
		// añadiendo los digitos que falten a 10 de un aleatorio
		while (datosNuevoUsuario[1].length()<10) {
			String aleatorio=String.valueOf(1000000000+(int)(Math.floor((Math.random()*999999999)+1)));
			int dif=(10-datosNuevoUsuario[1].length());
			datosNuevoUsuario[1]=datosNuevoUsuario[1].concat(aleatorio.substring(0, dif));
		}
		int n=0;
		// comprueba que no existe el user, u si existe lo modifica por rotacion
		while (dao.idKeyExist(datosNuevoUsuario[1])) {
			// este bucle realiza una rotacion de letras
			// empezando por la ultima hasta encontrar una no repe 
			// ejem: Doc99Fdoca => aDoc99Fdoc => caDoc99Fdo
			datosNuevoUsuario[1]=datosNuevoUsuario[1].substring(9)+datosNuevoUsuario[1].substring(0,8);
			if (n++==9) {
				// si la rotacion no funciona, generamos un aleatorio
				if (datosNuevoUsuario[1].length()<10) {
					String aleatorio=String.valueOf(1000000000+(int)((Math.random()*999999999)+1));
					int dif=(10-datosNuevoUsuario[1].length());
					datosNuevoUsuario[1]=datosNuevoUsuario[1].concat(aleatorio.substring(0, dif));
				}
			}
		}
		
		datosNuevoUsuario[7]=this.datosEmpresa[1];		// emp1: HEREDADA
		datosNuevoUsuario[8]="";						// emp2
		datosNuevoUsuario[9]="";						// emp3
		datosNuevoUsuario[10]=this.datosEmpresa[1];		// empselec: HEREDADA
		datosNuevoUsuario[11]=this.datosUsuario[11];	// yearselec: HEREDADA
		datosNuevoUsuario[12]="1";						// activo
		datosNuevoUsuario[13]="N";						// lpg
		datosNuevoUsuario[14]="N";						// cgu
			
		return true;
		
	} // fin del metodo compruebaDatos
	
	
	
	/* **********************************************************
	 * Este metodo instancia una conexion dao
	 * y busca las empresas que tiene el manager en DDBB
	 * Recibe como parametro el keyuser del manager
	 * Devuelve un null si hay algun error o no tiene empresas
	 * Devuelve un Array con los datos de las empresas si O.K.
	 *********************************************************** */
	
	private String[][] buscaEmpresasManager(String usuManager) {
		// TODO Auto-generated method stub
		
		// instancia una conexion
		dao=new ContaDAO();
		
		return dao.buscaEmpresasUsuDB(usuManager);
		
	}  // fin del metodo buscaempresasmanager
	
	
	
	/* **********************************************************
	 * Este metodo instancia una conexion dao
	 * y busca los usuarios que gestiona el manager en DDBB
	 * Recibe como parametro el keyuser del manager
	 * Devuelve un null si hay algun error o no tiene empresas
	 * Devuelve un Array con los datos de los usuarios si O.K.
	 *********************************************************** */
	
	private String[][] buscaUsuariosManager(String usuManager) {
		// TODO Auto-generated method stub
		
		// instancia una conexion
		dao=new ContaDAO();
		
		return dao.buscaUsuariosManagerDB(usuManager);
		
	}  // fin del metodo buscausuariosmanager
	
	
	
	/* ****************************************************************
	 * Este metodo busca cuanto usuarios tiene una empresa determinada
	 *
	 * Recibe como parametro el key de la empresa
	 * Devuelve un -1 si hay algun error
	 * Devuelve un INT con el numero de usuarios de esa empresa
	 *********************************************************** */
	
	private int numUsuariosAutorizados(String empresa) {
		// TODO Auto-generated method stub
		
		// instancia una conexion
		dao=new ContaDAO();
		
		return dao.numUserAutorizados(empresa);
		
	}  // fin del metodo buscausuariosmanager
	
	
	
	/* ****************************************************************************
	 * Este metodo busca la informacion de un usuario administrado
	 * en el menu de mantenimiento de usuarios de un manager
	 *
	 * Recibe como parametro el nombre del administrado
	 * No retorna ningun dato
	 * 
	 * PROCEDIMIENTO:
	 * A) Recorre el array de datos de usuarios administrados comparando
	 * con nombres de administrados para localizarlo
	 * B) Una vez localizado, toma los valores del array de usuario y los
	 * va mostrando en la pantalla
	 * C) Los tres primeros campos son de texto y los muestra .setText
	 * D) El siguiente campo es un Jcombobox y selecciona el index pertinente
	 * E) El/Los siguientes campos son Checkbox, y es algo mas complejo:
	 * debe localizar en la matriz datosEmprAdmin los nombres de las empresas
	 * administradas, comprobar si el usuario pertenece a ellas, y si es
	 * asi, entonces poner el checkbox en true para que aparezca clickado
	 * F) El ultimo campo es un JCombobox y se selecciona el index correspondiente
	 * *************************************************************************** */
	
	private void muestraAdministrado(String administrado) {
		// TODO Auto-generated method stub
		int n=0;
		while (n<6 && datosUsuarioAdmin[n][2]!=null) {
			if (administrado.equals(datosUsuarioAdmin[n][2])) {
				// copias la informacion del seleccionado
				datosNuevoUsuario=datosUsuarioAdmin[n];
				// mostramos los datos del array sin mas
				d1D.setText(datosUsuarioAdmin[n][2]);
				d2D.setText(datosUsuarioAdmin[n][3]);
				d3D.setText(datosUsuarioAdmin[n][4]);
				// aqui hay dos valores posibles 2 - Contable y 3 -Auxiliar
				// que se corresponden con index 1 e index 2
				if (datosUsuarioAdmin[n][6].equals("2")) {
					d4D.setSelectedIndex(1);
				} else d4D.setSelectedIndex(2);
				
				// se comparan las tres empresas administradas una por una con los datos
				// de los usuarios, para comprobar si el usuario esta dado de alta en alguna
				// y si es asi, se da la casilla del checkbox como true
				if (datosEmprAdmin[0][1]!=null && datosEmprAdmin[0][1].equals(datosUsuarioAdmin[n][7])) {
					d7D.setSelected(true);
				}
				if (datosEmprAdmin[0][1]!=null && datosEmprAdmin[0][1].equals(datosUsuarioAdmin[n][8])) {
					d7D.setSelected(true);
				}
				if (datosEmprAdmin[0][1]!=null && datosEmprAdmin[0][1].equals(datosUsuarioAdmin[n][9])) {
					d7D.setSelected(true);
				}
				
				if (datosEmprAdmin[1][1]!=null && datosEmprAdmin[1][1].equals(datosUsuarioAdmin[n][7])) {
					d8D.setSelected(true);
				}
				if (datosEmprAdmin[1][1]!=null && datosEmprAdmin[1][1].equals(datosUsuarioAdmin[n][8])) {
					d8D.setSelected(true);
				}
				if (datosEmprAdmin[1][1]!=null && datosEmprAdmin[1][1].equals(datosUsuarioAdmin[n][9])) {
					d8D.setSelected(true);
				}
				
				if (datosEmprAdmin[2][1]!=null && datosEmprAdmin[2][1].equals(datosUsuarioAdmin[n][7])) {
					d9D.setSelected(true);
				}
				if (datosEmprAdmin[2][1]!=null && datosEmprAdmin[2][1].equals(datosUsuarioAdmin[n][8])) {
					d9D.setSelected(true);
				}
				if (datosEmprAdmin[2][1]!=null && datosEmprAdmin[2][1].equals(datosUsuarioAdmin[n][9])) {
					d9D.setSelected(true);
				}

				// aqui se selecciona activo o desactivado, cuyo valores son 
				// 1 Activado y 0 Desactivado
				// se corresponden con los index 1 e index 2
				if (datosUsuarioAdmin[n][12].equals("1")) {
					d6D.setSelectedIndex(1);
				} else d6D.setSelectedIndex(2);
				break;
			}
			n++;
		}
		
	}  // fin del metodo muestraAdministrado

	
		
	/* **********************************************************************
	 * Este metodo elimina la informacion de un usuario administrado
	 * en el menu de mantenimiento de usuarios de un manager
	 *
	 * Recibe como parametro el nombre del administrado
	 * Retorna true o false segun el resultado de la grabacion
	 * 
	 * PROCEDIMIENTO:
	 * A) Busca en el array datosUsuarioAdmin un nombre que coincida
	 * con el seleccionado en el Jcombobox
	 * B) Cuando coincide, coge el keyUser almacenado en [1]
	 * C) Instancia el dao, e invoca el metodo grabaUsuDB con el keyUser
	 * D) En realidad no se le borra, sino que se le marca como borrado
	 *    por cuestiones de conservar informacion de operaciones realizadas
	 * E) Retorna un TRUE si borrado OK y FALSE en otro caso
	 ********************************************************************* */
	
	private boolean borraAdministrado(String administrado) {
		// TODO Auto-generated method stub
		int rememberWho=0;
		int n=0;
		// busca en el array el usuario y su userkey
		while (n<6 && datosUsuarioAdmin[n][2]!="") {
			if (administrado.equals(datosUsuarioAdmin[n][2])) {
				rememberWho=n;
				break;
			}
			n++;
		}
		
		// encontrado el usuario, se le marca para borrado poniendo en blanco
		// todas sus variables excepto keyUser y nombre.
		// traspasando a una matriz nueva
		datosNuevoUsuario=new String[15];
		datosNuevoUsuario[0]=datosUsuarioAdmin[rememberWho][0];
		datosNuevoUsuario[1]=datosUsuarioAdmin[rememberWho][1];
		datosNuevoUsuario[2]=datosUsuarioAdmin[rememberWho][2];
		datosNuevoUsuario[3]="";
		datosNuevoUsuario[4]="";
		datosNuevoUsuario[5]="";
		datosNuevoUsuario[6]="";
		datosNuevoUsuario[7]="";
		datosNuevoUsuario[8]="";
		datosNuevoUsuario[9]="";
		datosNuevoUsuario[10]="";
		datosNuevoUsuario[11]="";
		datosNuevoUsuario[12]="";
		datosNuevoUsuario[13]="";
		datosNuevoUsuario[14]="";
		
		// instancia una conexion
		dao=new ContaDAO();
		// llama al metodo de borrado
		return dao.grabaUsuDB(datosNuevoUsuario, "UPDATE", userCat);
		
	}  // fin del metodo borraAdministrado
	
	
	
	/* **********************************************************************
	 * Este metodo actualiza la informacion de un usuario administrado
	 * en el menu de mantenimiento de usuarios de un manager
	 *
	 * Recibe como parametro el nombre del administrado
	 * No retorna true o false según el resultado de la grabacion
	 * 
	 * PROCEDIMIENTO:
	 * A) Busca en el array datosUsuarioAdmin un nombre que coincida
	 * con el seleccionado en el Jcombobox
	 * B) Cuando coincide, coge el keyUser almacenado en [1]
	 * C) Actualiza los datos modificados en el menu y lo incorpora al array
	 * D) Instancia el dao, e invoca el metodo grabaUsuDB con el keyUser
	 * E) Retorna un TRUE si borrado OK y FALSE en otro caso
	 ********************************************************************* */
	
	private boolean actualizaAdministrado(String administrado) {
		
		d1D.setBackground(Color.WHITE);
		d2D.setBackground(Color.WHITE);
		d3D.setBackground(Color.WHITE);
		
		// TODO Auto-generated method stub
		int rememberWho=0;
		int n=0;
		// busca en el array el usuario y su userkey
		while (n<6 && datosUsuarioAdmin[n][2]!="") {
			if (administrado.equals(datosUsuarioAdmin[n][2])) {
				rememberWho=n;
				break;
			}
			n++;
		}
		
		// encontrado el usuario, se cogen las variables del formulario
		// se tratan y se incorporan al array
		datosNuevoUsuario=new String[15];
		datosNuevoUsuario[0]=datosUsuarioAdmin[rememberWho][0];
		datosNuevoUsuario[1]=datosUsuarioAdmin[rememberWho][1];
		
		
		// ************** nombre usuario ******************* 
				datosNuevoUsuario[2]=d1D.getText();			
				if ((datosNuevoUsuario[2].equals("") || datosNuevoUsuario[2].equals(null))) {
					JOptionPane.showMessageDialog(null, "nombre de usuario no rellenado");
					d1D.setBackground(Color.ORANGE);
					return false;
				}
				if (datosNuevoUsuario[2].length()<3 || datosNuevoUsuario[2].length()>25) {
					JOptionPane.showMessageDialog(null, "el nombre tiene longitud inadecuada" );
					d1D.setBackground(Color.ORANGE);
					return false;
				}
				// ************** login ******************* 
				datosNuevoUsuario[3]=d2D.getText();			
				if(datosNuevoUsuario[3].contains(" ")) {
					JOptionPane.showMessageDialog(null, "Login no debe contener espacios");
					d2D.setBackground(Color.ORANGE);
					return false;
				}
				if ((datosNuevoUsuario[3].equals("") || datosNuevoUsuario[3].equals(null))) {
					JOptionPane.showMessageDialog(null, "Login no rellenado");
					d2D.setBackground(Color.ORANGE);
					return false;
				}
				if (datosNuevoUsuario[3].length()<5 || datosNuevoUsuario[3].length()>10) {
					JOptionPane.showMessageDialog(null, "el login tiene longitud inadecuada" );
					d2D.setBackground(Color.ORANGE);
					return false;
				}
				// ************** password ******************* 
				datosNuevoUsuario[4]=d3D.getText();			
				if(datosNuevoUsuario[4].contains(" ")) {
					JOptionPane.showMessageDialog(null, "Contraseña no debe contener espacios");
					d3D.setBackground(Color.ORANGE);
					return false;
				}
				if ((datosNuevoUsuario[4].equals("") || datosNuevoUsuario[4].equals(null))) {
					JOptionPane.showMessageDialog(null, "contraseña no rellenada");
					d3D.setBackground(Color.ORANGE);
					return false;
				}
				if (datosNuevoUsuario[4].length()<5 || datosNuevoUsuario[4].length()>10) {
					JOptionPane.showMessageDialog(null, "la contraseña tiene longitud inadecuada" );
					d3D.setBackground(Color.ORANGE);
					return false;
				}
		
		datosNuevoUsuario[5]=datosUsuarioAdmin[rememberWho][5];	
		if (d4D.getSelectedItem().toString().equals("Contable")) {
			datosNuevoUsuario[6]="2";
		} else datosNuevoUsuario[6]="3";
		
		datosNuevoUsuario[7]=datosUsuarioAdmin[rememberWho][7];
		datosNuevoUsuario[8]=datosUsuarioAdmin[rememberWho][8];
		datosNuevoUsuario[9]=datosUsuarioAdmin[rememberWho][9];
		if (datosNuevoUsuario[7].equals("")) {
			datosNuevoUsuario[7]=datosNuevoUsuario[8];
			datosNuevoUsuario[8]="";
		}
		if (datosNuevoUsuario[8].equals("")) {
			datosNuevoUsuario[8]=datosNuevoUsuario[9];
			datosNuevoUsuario[9]="";
		}

		datosNuevoUsuario[10]=datosUsuarioAdmin[rememberWho][10];
		datosNuevoUsuario[11]=datosUsuarioAdmin[rememberWho][11];
		if (d6D.getSelectedItem().toString().equals("Desactivado")) {
			datosNuevoUsuario[12]="0";
		} else datosNuevoUsuario[12]="1";
		datosNuevoUsuario[13]=datosUsuarioAdmin[rememberWho][13];
		datosNuevoUsuario[14]=datosUsuarioAdmin[rememberWho][14];
		
		
		// instancia una conexion
		dao=new ContaDAO();
		// llama al metodo de borrado
		return dao.grabaUsuDB(datosNuevoUsuario, "UPDATE", userCat);
		
	}  // fin del metodo actualizaAdministrado
	
	
	public class CreaTabla extends AbstractTableModel {
		
		
		String nombreColum[]={"cliente","Datos"};
		
		Object info[][]= {
				{"Nombre usuario",d1.getText()},
				{"Usuario",d2.getText()},
				{"Password",d3.getText()},
				{"email",d4.getText()},
				{"Categoría",d5.getText()},
				{"Empresas activas ",d6.getText()},
				{"Empresa",d7.getText()},
				{"Dirección",d8.getText()},
				{"Localidad",d9.getText()},
				{"Cod.Postal",d10.getText()},
				{"N.I.F.",d11.getText()}
		};

		
		public int getRowCount() {
			// TODO Auto-generated method stub
			return info.length;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return nombreColum.length;
		}

		public Object getValueAt(int rowIndex, int nombreColum) {
			// TODO Auto-generated method stub
			return info[rowIndex][nombreColum];
		}

	}




}