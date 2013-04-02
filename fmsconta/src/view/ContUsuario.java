package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
	private JPanel panelUsu;
	
	private JTable tablaDatos;
	
	private JLabel n0=new JLabel("Datos de usuario");
	
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
	
	
	/* *********************************************************
	 * Este metodo permite mostrar, modificar y borrar los datos
	 * de cada usuario operador
	 * El metodo recibe una matriz de datos de usuario
	 * y realiza las acciones pertinentes
	 ********************************************************* */
	
	
	public ContUsuario(String datosUser[], String datosEmp[], int CompHeight){	
		
		panelUsu=new JPanel();
		
		panelUsu.setLayout(new BoxLayout(panelUsu,BoxLayout.Y_AXIS));

		panelUsu.setBackground(colorfondo);
		      
		d1.setText(datosUser[2]); 	// nombre
		d2.setText(datosUser[3]); 	// login
		d3.setText(datosUser[4]); 	// password
		d4.setText(datosUser[5]);	// email
		d5.setText(datosUser[6]);	// categoria se transforma a texto
		if (d5.getText().equals("1")) {
			d5.setText("Manager");
		} else if (d5.getText().equals("2")) {
			d5.setText("Contable");
		} else {d5.setText("Auxiliar");}
		// sumatorio de empresas donde trabaja
		if (datosUser[8].equals("") || datosUser[8]==null) {
			d6.setText("1");
		} else if (datosUser[9].equals("") || datosUser[9]==null) {
			d6.setText("2");
		} else {d6.setText("3");}
		
		// datos de la empresa activa actual
		d7.setText(datosEmp[2]);
		d8.setText(datosEmp[3]);
		d9.setText(datosEmp[4]);
		d10.setText(datosEmp[6]);
		d11.setText(datosEmp[7]);
		
		
		// implementamos el modelo de la tabla
		CreaTabla modTabla=new CreaTabla();
		
		// creamos la tabla y la personalizamos
	    tablaDatos = new JTable(modTabla);
	    tablaDatos.setFont(fuente2);
	    tablaDatos.setBackground(colorfondo);
	    tablaDatos.setRowHeight(sizeLetras);
	    tablaDatos.doLayout();

	    tablaDatos.setFocusable(false);
	    tablaDatos.setShowGrid(false);
	    // asignamos un tamaño a la tabla
	    tablaDatos.setPreferredScrollableViewportSize(new Dimension(600, (sizeLetras*11)));
	    // la introducimos en un ScrollPane
	    JScrollPane scrollPane = new JScrollPane(tablaDatos);	
		
	    // ajustamos componentes
	    n0.setFont(fuente1);
	    
	    imprimir=new JButton("Imprimir");
	    
	    // agregamos componentes al panel
	    panelUsu.add(n0);
		panelUsu.add(scrollPane);
		panelUsu.add(imprimir);
		
		// visualizamos el panel
		panelUsu.setVisible(true);
		
		// añadimos los listener
		imprimir.addActionListener(this);
		
	} //fin del builder
	
	@Override
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
			panelUsu.setBackground(colorBlanco);
			listado.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			listado.add(panelUsu);
			listado.setVisible(true);
			
		}
		
	} // fin del actionPerformed
	
	
	public JPanel retorna() {
		return panelUsu;
	}


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

		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return info.length;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return nombreColum.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int nombreColum) {
			// TODO Auto-generated method stub
			return info[rowIndex][nombreColum];
		}

	}

}

