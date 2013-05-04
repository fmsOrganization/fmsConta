package fmsconta.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import fmsconta.control.ContaDAO;

public class CambioEmpresa implements ActionListener, Settings {
	
	
	// botones y controles
	public JButton modificar;
	
	private String datosUsuario[];	// matriz con los datos del usuario
	private String datosEmp[];		// matriz con los datos de la empresa
	private String creaEmp[];		// matriz para creacion datos empresa
	private String keyEmpr;			// key de la empresa actual
	private int userCat;			// categoria que tiene el usuario 
	private String keyUser;			// key del usuario
	private ContaDAO dao;			// instanciar objeto tipo conexiones empresa actual
	private ContaDAO daoN;			// instanciar objeto tipo conexiones empresa nueva
	
	private String nameEmprs[][];
	private String nameCompany="";
	private String yearCompany="";
	
	private JPanel panelEmpresa;
	
	private JLabel n2=new JLabel("Cambio de empresa de trabajo");
	
	private JLabel l1B=new JLabel("     Empresa");
	private JLabel l2B=new JLabel("     Año");
	private JLabel l3B=new JLabel("     Nombre de la empresa  ");
	private JLabel l4B=new JLabel("     N.I.F.");
	private JLabel l5B=new JLabel("     Activa");
	private JLabel l6B=new JLabel("     Usuario");
	
	private JComboBox d1B=new JComboBox();
	private JComboBox d2B=new JComboBox();
	private JTextField d3B=new JTextField();
	private JTextField d4B=new JTextField();
	private JTextField d5B=new JTextField();
	private JTextField d6B=new JTextField();



	public CambioEmpresa() {
		
	}
	
	/* *******************************************************************
	 * En la clase CambioEmpresa se efectuará el cambio de empresa por
	 * parte del usuario, asi como el cambio del ejercicio de trabajo
	 ******************************************************************** */
	
	public CambioEmpresa(String datosEmpmain[], String datosUsuario[]){
		
		// asignamos a variables de clase los datos recibidos
		this.datosUsuario=datosUsuario;
		datosEmp=datosEmpmain;
		keyEmpr=datosEmpmain[1];
		userCat =(int)Integer.parseInt(datosUsuario[6]);
		this.keyUser=datosUsuario[1];
		
		// construimos el panel para cambiar de empresa
		// **************************************************
		modificarPanel(datosEmp,datosUsuario[2],userCat);
		
		// añadimos los actionlistener
		d1B.addActionListener(this);
		d2B.addActionListener(this);
		modificar.addActionListener(this);
		
	} // fin del builder

	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==d1B) {
			// cambio de empresas:
			// modificacion de la vista al cambiar el combobox
			d1B.getSelectedItem().toString();
			if (d1B.getSelectedItem().toString().equals(nameEmprs[0][2])){
				d3B.setText(nameEmprs[0][2]); 	// nombre empresa
				d4B.setText(nameEmprs[0][7]);	// nif			
				// actividad
				if (nameEmprs[0][10].equals("0")) {
					d5B.setText("Inactiva");
				} else d5B.setText("Activa");
				
			} else 	if (d1B.getSelectedItem().toString().equals(nameEmprs[1][2])){
				d3B.setText(nameEmprs[1][2]); 	// nombre empresa
				d4B.setText(nameEmprs[1][7]);	// nif			
				// actividad
				if (nameEmprs[1][10].equals("0")) {
					d5B.setText("Inactiva");
				} else d5B.setText("Activa");
				
			} else 	if (d1B.getSelectedItem().toString().equals(nameEmprs[2][2])){
				d3B.setText(nameEmprs[2][2]); 	// nombre empresa
				d4B.setText(nameEmprs[2][7]);	// nif			
				// actividad
				if (nameEmprs[2][10].equals("0")) {
					d5B.setText("Inactiva");
				} else d5B.setText("Activa");
				
			}
		}
		
		if (source==d2B) {
			d2B.getSelectedItem().toString();
		}
		
		
		if (source==modificar) {
			// al pulsar modificar hay que cambiar en el fichero de usuario
			// tanto el año seleccionado como la compañia seleccionada
			if (nameEmprs[0][2].equals(d3B.getText())) {
				datosUsuario[10]=nameEmprs[0][1];
			} else if (nameEmprs[1][2].equals(d3B.getText())) {
				datosUsuario[10]=nameEmprs[1][1];
			} else if (nameEmprs[2][2].equals(d3B.getText())) {
				datosUsuario[10]=nameEmprs[2][1];
			}
			
			datosUsuario[11]=d2B.getSelectedItem().toString();
			// se graban los cambios en DDBB
			if (grabaModif(this.datosUsuario)) {
				JOptionPane.showMessageDialog(null, "Cambio de empresa efectuado");
			} else {
				JOptionPane.showMessageDialog(null, "ERROR en cambio de empresa");
			}
			// se ajustan las variables a devolver mediante getter a pantallaprincipal
			nameCompany=d3B.getText();
			
			yearCompany=d2B.getSelectedItem().toString();

		}
		
	}
	
	
	
	/* ****************************************************************
	 * el metodo retorna es el que devuelve a la pantalla principal
	 * el tabbedPane fabricado y listo para ser visualizado
	 * no recibe argumentos, y devuelve un jtabbedPane
	 ************************************************************** */

	public JPanel retorna() {
		return panelEmpresa;
	}
	
	
	
	/* *************************************************************************
	 * Este metodo permite la modificacion de la tabla datos empresa
	 * Algunos datos no pueden ser modificados y son solo informativos
	 * Otros datos solo pueden ser modificados por el manager de la empresa
	 * Finalmente, otros datos son modificables por todos
	 * Se reciben un string[] con datos de empresa, String con el manager
	 * y un boolean si el usuario es el manager
	 * Este metodo no retorna, modifica directamente las variables de clase
	 ************************************************************************** */
	
	public void modificarPanel (String datosEmp[], String nameUsuario, int categoria) {
		
		// creacion del panel principal
		panelEmpresa=new JPanel();
		panelEmpresa.setLayout(new BoxLayout(panelEmpresa,BoxLayout.Y_AXIS));
		//panelEmpresa.setAlignmentX(CENTER_ALIGNMENT);
		panelEmpresa.setBackground(ColorFondo);
		
		// titulo
		JPanel north2=new JPanel();
		north2.setLayout(new FlowLayout());
		north2.setBackground(ColorFondo);
	    // cambiar el font al titulo
	    n2.setFont(Fuente1);
		north2.add(n2);
		
		// creacion del panel rejilla datos
		JPanel panelEmpAux = new JPanel();
		panelEmpAux.setLayout(new GridLayout(6,2));
		//panelEmpAux.setAlignmentY(CENTER_ALIGNMENT);
		panelEmpAux.setFont(Fuente2);
		panelEmpAux.setBackground(ColorFondo);
		      
		// empresas
		nameEmprs=new String[3][12];
		nameEmprs[0]=buscaDatosEmpresas(datosUsuario[7]);
		nameEmprs[1]=buscaDatosEmpresas(datosUsuario[8]);
		nameEmprs[2]=buscaDatosEmpresas(datosUsuario[9]);
		for (int i=0;i<nameEmprs.length;i++) {
			d1B.addItem(nameEmprs[i][2]);
			if (datosEmp[2].equals(nameEmprs[i][2])) {
				nameCompany=nameEmprs[i][2]; // asignamos el nombre de la compañia seleccionada
				d1B.setSelectedIndex(i);
			}
		}
		
		// año	
		// obtengo el año actual
		Calendar dat=Calendar.getInstance();
		int dato=dat.get(Calendar.YEAR);
		for (int n=0;n<6;n++) {
			d2B.addItem(String.valueOf(dato-n));
			if (this.datosUsuario[11].equals(String.valueOf(dato-n))) {
				yearCompany=String.valueOf(dato-n); // asignamos el nombre del año seleccionado
				d2B.setSelectedIndex(n);
			}
		}
		
		// datos de la empresa activa actual
		d3B.setText(datosEmp[2]);	// nombre empresa
		d4B.setText(datosEmp[7]);	// nif		
		// actividad
		if (datosEmp[10].equals("0")) {
			d5B.setText("Inactiva");
		} else d5B.setText("Activa");
		d6B.setText(nameUsuario);		// nombre manager
		
		// definicion de celdas no editables
		d3B.setEditable(false);
		d4B.setEditable(false);
		d5B.setEditable(false);
		d6B.setEditable(false);
		


		
		// add de componentes al panel rejilla
		panelEmpAux.add(l1B);
		panelEmpAux.add(d1B);
		
		panelEmpAux.add(l2B);
		panelEmpAux.add(d2B);
	
		panelEmpAux.add(l3B);
		panelEmpAux.add(d3B);
		
		panelEmpAux.add(l4B);
		panelEmpAux.add(d4B);
		
		panelEmpAux.add(l5B);
		panelEmpAux.add(d5B);
		
		panelEmpAux.add(l6B);
		panelEmpAux.add(d6B);

		
		// separadores en panel principal
		JLabel espacio1=new JLabel(" ");
		JLabel espacio2=new JLabel(" ");
	    
	    // creacion del panel de botones inferior
	    JPanel south2=new JPanel();
	    south2.setBackground(ColorFondo);
	    modificar=new JButton("Modificar");
	    modificar.setToolTipText("guarda los datos actuales");
	    south2.add(modificar);
	    
	    // agregamos componentes al panel principal
	    panelEmpresa.add(north2);
	    panelEmpresa.add(espacio1);
	    panelEmpresa.add(panelEmpAux);
	    panelEmpresa.add(espacio2);
	    panelEmpresa.add(south2);
		
		
	} // fin del metodo modificarPanel
	
	
	
	/* ****************************************************************
	 * Este metodo busca los nombres de las empresas en la DDBB
	 *
	 * Recibe como parametro el array del usuario
	 * Devuelve un null si hay algun error
	 * Devuelve un array[3] con los nombre de las empresas del usuario
	 **************************************************************** */
	
	private String[] buscaDatosEmpresas(String keyEmpresa) {
		// TODO Auto-generated method stub
		
		// instancia una conexion
		dao=new ContaDAO();
		
		return dao.idEmpDB(keyEmpresa);
		
	}  // fin del metodo buscaNombresEmpresas
	
	
	
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
	
	
	public String getCompany() {
		return nameCompany;
	}
	
	public String getYear() {
		return yearCompany;
	}
	
}
