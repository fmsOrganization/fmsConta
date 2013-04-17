package fmsconta.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class ContUsuario extends JFrame implements ActionListener{
	
	// implementa la imagen de portada con un fichero .jpg
	
	private JFrame ventanaListado;

	private Color colorfondo=new Color(220,220,220);
	private Color colorBlanco=new Color(255,255,255);
	private JTabbedPane panTab;
	private JPanel panelEmp;
	private JPanel panelEmp2;
	private JPanel panelEmp3;
	
	private JTable tablaDatos;
	
	private JLabel n0=new JLabel("Datos de usuario");
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
	private JLabel n1=new JLabel("Datos de usuario");
	private JLabel n3=new JLabel("Datos de usuario");
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

	private Font fuente1=new Font("",Font.BOLD,20);
	private Font fuente2=new Font("",Font.PLAIN,16);
	private int sizeLetras=24;
	
	private String columnA="cliente";
	
	private JButton imprimir;
	
	private String datosUsuario[];
	private String datosEmpresa[];
	
	
	/* *********************************************************
	 * Este metodo permite mostrar, modificar y borrar los datos
	 * de cada empresa registrada para contabilidad
	 * El metodo recibe una matriz de datos de empresa, su key
	 * de DDBB, el String nombre del usuario y true si es manager
	 * y realiza las acciones pertinentes
	 ********************************************************* */
		
	/* **************************************************************
	 * Este metodo permite mostrar, modificar y borrar los datos
	 * de cada usuario operador
	 * Ademas si eres usuario manager te permite crear otros usuarios
	 * El metodo recibe una matriz de datos de usuario
	 * y realiza las acciones pertinentes
	 **************************************************************** */
	
	
	public ContUsuario(String datosUser[], String datosEmp[]){	
	
		
		// asignamos a variables de clase los datos recibidos
		datosUsuario=datosUser;
		datosEmpresa=datosEmp;
	/*	
		keyEmpr=keyEmpresa;
		userCat =(int)Integer.parseInt(categoria);
		this.keyUser=keyUser;
	*/	
	
		// primero construimos el panel para consultar
		// *******************************************
		
		consultarPanel(datosUsuario,datosEmpresa);
		
		// segundo construimos el panel para modificar-borrar
		// **************************************************
		
		modificarPanel(datosUsuario,datosEmpresa);

		// tercero construimos el panel para creación nuevas empresas
		// **********************************************************
		creaPanel(datosUsuario,datosEmpresa);

		// y finalmente agregamos los paneles al tabbedPane
		panTab=panelTabulado(panelEmp,"Consultar",panelEmp2,"Modificar",panelEmp3,"Creación");
			
		// finalmente añadimos los listener
		imprimir.addActionListener(this);
		/*
		modificar.addActionListener(this);
		eliminar.addActionListener(this);
		crear.addActionListener(this);
		*/
		
		
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
		
	} // fin del actionPerformed
	
	
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
		d5.setText(datosUsu[6]);	// categoria
		d6.setText("1");			// nº de empresas
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
		
		
		// creacion del panel principal
		panelEmp2=new JPanel();
		panelEmp2.setLayout(new BoxLayout(panelEmp2,BoxLayout.Y_AXIS));
		panelEmp2.setAlignmentX(CENTER_ALIGNMENT);
		panelEmp2.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		JPanel panelEmpAux2 = new JPanel();
		panelEmpAux2.setLayout(new GridLayout(10,3));
		panelEmpAux2.setAlignmentY(CENTER_ALIGNMENT);
		panelEmpAux2.setFont(fuente2);
		panelEmpAux2.setBackground(colorfondo);
		/*      
		// datos de la empresa activa actual
		d1B.setText(datosEmp[2]);	// nombre empresa
		d2B.setText(datosEmp[3]);	// direccion
		d3B.setText(datosEmp[4]);	// localidad
		d4B.setText(datosEmp[5]);	// provincia
		d5B.setText(datosEmp[6]);	// codigo postal
		d6B.setText(datosEmp[7]);	// cif
		d7B.setText(datosEmp[8]);	// fecha inicio ejercicio
		d8B.setText(datosEmp[9]);	// fecha fin ejercicio
		d9B.setText(datosEmp[10]);	// activa
		if (d9B.getText().equals("0")) {
			d9B.setText("Inactiva");
		} else {d9B.setText("Activa");}
		d10B.setText(nameUsuario);		// nombre manager
		
		// definicion de celdas no editables
		d7B.setEditable(false);
		d8B.setEditable(false);
		d10B.setEditable(false);
		if (categoria!=1) {
			// los usuarios no manager tampoco puede modificar estas
			d1B.setEditable(false);
			d6B.setEditable(false);
			d9B.setEditable(false);
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
		if (categoria==1) {
			tabulacion=JLabel.LEFT;
		} else {
			tabulacion=JLabel.CENTER;
		}
		JLabel sp1=new JLabel("solo manager",iconoW,tabulacion);
		JLabel sp2=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp3=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp4=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp5=new JLabel("permitido",iconoE,JLabel.LEFT);
		JLabel sp6=new JLabel("solo manager",iconoW,tabulacion);
		JLabel sp7=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp8=new JLabel("no permitido",iconoD,JLabel.CENTER);
		JLabel sp9=new JLabel("solo manager",iconoW,tabulacion);
		JLabel sp10=new JLabel("no permitido",iconoD,JLabel.CENTER);
		
		// add de componentes al panel rejilla
		panelEmpAux2.add(l1B);
		panelEmpAux2.add(d1B);
		panelEmpAux2.add(sp1);
		panelEmpAux2.add(l2B);
		panelEmpAux2.add(d2B);
		panelEmpAux2.add(sp2);
		panelEmpAux2.add(l3B);
		panelEmpAux2.add(d3B);
		panelEmpAux2.add(sp3);
		panelEmpAux2.add(l4B);
		panelEmpAux2.add(d4B);
		panelEmpAux2.add(sp4);
		panelEmpAux2.add(l5B);
		panelEmpAux2.add(d5B);
		panelEmpAux2.add(sp5);
		panelEmpAux2.add(l6B);
		panelEmpAux2.add(d6B);
		panelEmpAux2.add(sp6);
		panelEmpAux2.add(l7B);
		panelEmpAux2.add(d7B);
		panelEmpAux2.add(sp7);
		panelEmpAux2.add(l8B);
		panelEmpAux2.add(d8B);
		panelEmpAux2.add(sp8);
		panelEmpAux2.add(l9B);
		panelEmpAux2.add(d9B);
		panelEmpAux2.add(sp9);
		panelEmpAux2.add(l10B);
		panelEmpAux2.add(d10B);
		panelEmpAux2.add(sp10);
		
		// separadores en panel principal
		JLabel espacio1=new JLabel(" ");
		JLabel espacio2=new JLabel(" ");
		
	    // ajustamos fuente del titulo
	    n1.setFont(fuente1);
	    
	    // creacion del panel de botones inferior
	    JPanel south2=new JPanel();
	    south2.setBackground(colorfondo);
	    modificar=new JButton("Modificar");
	    modificar.setToolTipText("guarda los datos actuales");
	    eliminar=new JButton("Eliminar");
	    eliminar.setToolTipText("borra definitivamente la empresa");
	    if (categoria!=1) {
	    	// si no eres manager no puede borrar la empresa
	    	eliminar.setEnabled(false);
	    	eliminar.setToolTipText("solo permitido al manager");
	    }
	    south2.add(modificar);
	    south2.add(eliminar);
	    
	    // agregamos componentes al panel principal
	    panelEmp2.add(n1);
	    panelEmp2.add(espacio1);
	    panelEmp2.add(panelEmpAux2);
	    panelEmp2.add(espacio2);
	    panelEmp2.add(south2);
		*/
		panelEmp2.add(n1);
		panelEmp2.add(panelEmpAux2);
	} // fin del metodo modificarPanel
	
	
	
	/* ************************************************************************
	 * Este metodo permite la consulta de la tabla datos empresa
	 * Ningun dato puede ser modificado
	 * Se reciben un string[] con datos de empresa, String nombre de usuario y
	 * un int con la categoria del usuario (solo cat=1 puede crear)
	 * Este metodo no tiene valor de retorno
	 ***************************************************************************/
	
	public void creaPanel (String datosUsu[], String datosEmp[]) {
		
		//datosEmpCreac[]=new String[12];
		
		// creacion del panel principal
		panelEmp3=new JPanel();
		panelEmp3.setLayout(new BoxLayout(panelEmp3,BoxLayout.Y_AXIS));
		panelEmp3.setAlignmentY(CENTER_ALIGNMENT);
		panelEmp3.setBackground(colorfondo);
		
		// creacion del panel rejilla datos
		JPanel panelEmpAux3 = new JPanel();
		panelEmpAux3.setLayout(new GridLayout(10,2));
		panelEmpAux3.setAlignmentY(CENTER_ALIGNMENT);
		panelEmpAux3.setFont(fuente2);
		panelEmpAux3.setBackground(colorfondo);
		/*      
		// datos de la empresa activa actual
		d1C.setText("");	// nombre empresa
		d2C.setText("");	// direccion
		d3C.setText("");	// localidad
		d4C.setText("");	// provincia
		d5C.setText("");	// codigo postal
		d6C.setText("");	// cif
		d7C.setText("");	// fecha inicio ejercicio
		d8C.setText("");	// fecha fin ejercicio
		d9C.setText("Inactiva");	// activa
		d10C.setText(nameUsuario);		// nombre manager
		d10C.setEnabled(false);
				
		abc=new ImageIcon(pathImageFiles+"warning.jpg").getImage();
		iconoW=new ImageIcon(abc.getScaledInstance(20,15,java.awt.Image.SCALE_SMOOTH));
		
		// definicion de comentarios edicion
		JLabel sc1=new JLabel("máx. longitud 40",iconoW,JLabel.LEFT);
		sc1.setToolTipText("Campo obligatorio");
		JLabel sc2=new JLabel("máx. longitud 50");
		JLabel sc3=new JLabel("máx. longitud 50");
		JLabel sc4=new JLabel("máx. longitud 20");
		JLabel sc5=new JLabel("longitud obligatoria 5",iconoW,JLabel.LEFT);
		sc5.setToolTipText("Campo obligatorio");
		JLabel sc6=new JLabel("longitud obligatoria 9",iconoW,JLabel.LEFT);
		sc6.setToolTipText("Campo obligatorio");
		JLabel sc7=new JLabel("longitud obligatoria 8",iconoW,JLabel.LEFT);
		sc7.setToolTipText("Campo obligatorio");
		JLabel sc8=new JLabel("longitud obligatoria 8",iconoW,JLabel.LEFT);
		sc8.setToolTipText("Campo obligatorio");
		JLabel sc9=new JLabel("1-Activa 0-Inactiva");
		JLabel sc10=new JLabel(" ");
		
		
		// add de componentes al panel rejilla
		panelEmpAux3.add(l1C);
		panelEmpAux3.add(d1C);
		panelEmpAux3.add(sc1);
		panelEmpAux3.add(l2C);
		panelEmpAux3.add(d2C);
		panelEmpAux3.add(sc2);
		panelEmpAux3.add(l3C);
		panelEmpAux3.add(d3C);
		panelEmpAux3.add(sc3);
		panelEmpAux3.add(l4C);
		panelEmpAux3.add(d4C);
		panelEmpAux3.add(sc4);
		panelEmpAux3.add(l5C);
		panelEmpAux3.add(d5C);
		panelEmpAux3.add(sc5);
		panelEmpAux3.add(l6C);
		panelEmpAux3.add(d6C);
		panelEmpAux3.add(sc6);
		panelEmpAux3.add(l7C);
		panelEmpAux3.add(d7C);
		panelEmpAux3.add(sc7);
		panelEmpAux3.add(l8C);
		panelEmpAux3.add(d8C);
		panelEmpAux3.add(sc8);
		panelEmpAux3.add(l9C);
		panelEmpAux3.add(d9C);
		panelEmpAux3.add(sc9);
		panelEmpAux3.add(l10C);
		panelEmpAux3.add(d10C);
		panelEmpAux3.add(sc10);
	
		// separadores en panel principal
		JLabel espacioY=new JLabel(" ");
		JLabel espacioZ=new JLabel(" ");
		
	    // cambiar el font al titulo
	    n3.setFont(fuente1);
	    
	    // creacion del panel inferior
	    JPanel south3=new JPanel();
	    south3.setBackground(colorfondo);
	    crear=new JButton("Crear empresa");
	    crear.setToolTipText("crea una empresa con los datos introducidos");
	    if (userCat!=1) {
	    	crear.setEnabled(false);
	    	crear.setToolTipText("Solo pueden crear empresas los managers");
	    }
	    south3.add(crear);
	    

	    // agregamos componentes al panel principal
	    panelEmp3.add(n3);
	    panelEmp3.add(espacioY);
	    panelEmp3.add(panelEmpAux3);
	    panelEmp3.add(espacioZ);
	    panelEmp3.add(south3);
		*/
		panelEmp3.add(n3);
		panelEmp3.add(panelEmpAux3);
	} // fin del metodo creaTabla
	
	
	
	/* ******************************************************************************
	 * Este metodo crea un tabbedpane de tres pestañas con la informacion recibida
	 * recibe tres JPanel con sus string titulos correspondientes
	 * devuelve un objeto tabbedpane
	 **************************************************************************** */
	
	public JTabbedPane panelTabulado(JPanel tab1,String title1,JPanel tab2,String title2,JPanel tab3,String title3) {
		
		setTitle("");
		JTabbedPane menu=new JTabbedPane();
		menu.setBackground(colorfondo);
		menu.addTab(title1,tab1);
		menu.addTab(title2,tab2);
		menu.addTab(title3,tab3);
		getContentPane().add(menu);
		return menu;
		
	} // fin del metodo panelTabulado
	
	
	
	
	
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

