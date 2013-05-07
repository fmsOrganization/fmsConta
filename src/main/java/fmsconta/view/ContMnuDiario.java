package fmsconta.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuDiario extends JFrame implements ActionListener, Settings {

	private static final long serialVersionUID = 1L;
	private JPanel menuDiario;
	private JPanel auxMenu;
	private JPanel north;
	private JPanel south;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;
	private JComboBox r4;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private JLabel space3=new JLabel(" ");
	private JLabel space4=new JLabel(" ");
	private JButton listar;
	private JButton cancelar;
	
	private ContListadoDiario listaDiario;
	
	
	
	/* ****************************************************************************
	 * 
	 * Este metodo constructor implementa el menu de impresion del Diario contable
	 * 
	 * ************************************************************************** */
	
	public ContMnuDiario() {
		
		
		menuDiario=new JPanel();
		menuDiario.setBackground(ColorFondo);
		menuDiario.setPreferredSize(new Dimension(400,300));
		menuDiario.setLayout(new BoxLayout(menuDiario,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Listado del Libro Diario");
		title1.setFont(Fuente1);
		title1.setAlignmentX((float)0.5);
		north.add(title1);
		
		auxMenu=new JPanel();
		auxMenu.setBackground(ColorFondo);
		auxMenu.setLayout(new GridLayout(6,2));
		JLabel n1=new JLabel("Desde fecha");
		n1.setFont(Fuente3);
		JLabel n2=new JLabel("Hasta fecha");
		n2.setFont(Fuente3);
		JLabel n3=new JLabel("Líneas por hoja");
		n3.setFont(Fuente3);
		JLabel n4=new JLabel("Diario Oficial");
		n4.setFont(Fuente3);
		
		r1=new JTextField("01-01-2012");
		r2=new JTextField("31-12-2012");
		r3=new JTextField("29");
		r4=new JComboBox();
		r4.addItem("NO");
		r4.addItem("SI");
		
		
		
		auxMenu.add(n1);
		auxMenu.add(r1);
		auxMenu.add(n2);
		auxMenu.add(r2);
		auxMenu.add(n3);
		auxMenu.add(r3);
		auxMenu.add(n4);
		auxMenu.add(r4);
		
		south=new JPanel();
		south.setBackground(ColorFondo);
		south.setLayout(new FlowLayout());
		listar=new JButton("Listar Diario");
		listar.setToolTipText("Lista el Diario con los parámetros seleccionados");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		cancelar.setToolTipText("Borra la pantalla");
		south.add(cancelar);
		
		menuDiario.add(space1);
		menuDiario.add(space2);
		menuDiario.add(north);
		menuDiario.add(space3);
		menuDiario.add(auxMenu);
		menuDiario.add(space4);
		menuDiario.add(south);
		
		menuDiario.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuDiario;
	}

	
	
	/* ******************************************************************
	 * Este metodo implementa el actionPerformed
	 ******************************************************************* */

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==listar) {
			menuDiario.setVisible(false);
			listaDiario=new ContListadoDiario("EC001","Ecovitalia",this.r1.getText(),this.r2.getText(),this.r3.getText(),r4.getActionCommand());
			menuDiario.remove(space1);
			menuDiario.remove(space2);
			menuDiario.remove(north);
			menuDiario.remove(space3);
			menuDiario.remove(auxMenu);
			menuDiario.remove(space4);
			menuDiario.remove(south);
			menuDiario.remove(south);
			
			menuDiario.add(listaDiario.retorna());
			menuDiario.setPreferredSize(new Dimension(850,(listaDiario.sizeComponent*20)+125));
			menuDiario.setAlignmentY(TOP_ALIGNMENT);
			menuDiario.setVisible(true);
		}
		
		if (source==cancelar) {
			menuDiario.setVisible(false);
		}
		
	}   // fin del actionPerformed
	

} // ******************************** fin de la class ContMnuDiario 
