package fmsconta.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuMayor extends JFrame implements ActionListener, Settings {
	
	private static final long serialVersionUID = 1L;
	private JPanel menuMayor;
	private JPanel auxMenu;
	private JPanel north;
	private JPanel south;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;
	private JTextField r4;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private JLabel space3=new JLabel(" ");
	private JLabel space4=new JLabel(" ");
	private JButton listar;
	private JButton cancelar;
	
	private ContListadoMayor listaMayor;
	
	
	
	/* ****************************************************************************
	 * 
	 * Este metodo constructor implementa el menu de impresion del Mayor contable
	 * 
	 * ************************************************************************** */
	
	public ContMnuMayor() {
		
		
		menuMayor=new JPanel();
		menuMayor.setBackground(ColorFondo);
		menuMayor.setPreferredSize(new Dimension(400,300));
		menuMayor.setLayout(new BoxLayout(menuMayor,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Listado del Mayor");
		title1.setFont(Fuente1);
		title1.setAlignmentX((float)0.5);
		north.add(title1);
		
		auxMenu=new JPanel();
		auxMenu.setBackground(ColorFondo);
		auxMenu.setLayout(new GridLayout(4,2));
		JLabel n1=new JLabel("Desde cuenta");
		n1.setFont(Fuente3);
		JLabel n2=new JLabel("Hasta cuenta");
		n2.setFont(Fuente3);
		JLabel n3=new JLabel("Desde fecha");
		n3.setFont(Fuente3);
		JLabel n4=new JLabel("Hasta fecha");
		n4.setFont(Fuente3);
		
		r1=new JTextField("10000000");
		r2=new JTextField("79999999");
		r3=new JTextField("01-01-2012");
		r4=new JTextField("31-12-2012");
		
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
		listar=new JButton("Listar Mayor");
		listar.setToolTipText("Lista el Mayor con los parámetros seleccionados");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		cancelar.setToolTipText("Borra la pantalla");
		south.add(cancelar);
		
		menuMayor.add(space1);
		menuMayor.add(space2);
		menuMayor.add(north);
		menuMayor.add(space3);
		menuMayor.add(auxMenu);
		menuMayor.add(space4);
		menuMayor.add(south);
		
		menuMayor.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuMayor;
	}

	
	
	/* ******************************************************************
	 * Este metodo implementa el actionPerformed
	 ******************************************************************* */

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==listar) {
			
			if (compruebaErrores()) {
				
				menuMayor.setVisible(false);
				listaMayor=new ContListadoMayor("EC001","Ecovitalia",this.r1.getText(),this.r2.getText(),this.r3.getText(),this.r4.getText());
				menuMayor.remove(space1);
				menuMayor.remove(space2);
				menuMayor.remove(north);
				menuMayor.remove(space3);
				menuMayor.remove(auxMenu);
				menuMayor.remove(space4);
				menuMayor.remove(south);
				menuMayor.remove(south);
				
				menuMayor.add(listaMayor.retorna());
				menuMayor.setPreferredSize(new Dimension(850,(listaMayor.sizeComponent*20)+125));
				menuMayor.setAlignmentY(TOP_ALIGNMENT);
				menuMayor.setVisible(true);
			}
		}
		
		
		if (source==cancelar) {
			menuMayor.setVisible(false);
		}
		
	} // fin del actionListener
	
	
	
	/* ****************************************************************************
	 * Este metodo realiza la comprobacion de errores en introduccion formulario
	 * 
	 * No recibe parametros
	 * Devuelve TRUE/FALSE segun este correcto o incorrecto el formulario
	 ***************************************************************************** */
	
	private boolean compruebaErrores() {
		
		// Revision de errores
		
		r1.setBackground(Color.WHITE);
		r2.setBackground(Color.WHITE);
		r3.setBackground(Color.WHITE);
		r4.setBackground(Color.WHITE);

		
		if (r1.getText().length()!=LongAuxiliares) {
			JOptionPane.showMessageDialog(null, "Longitud de cuenta inadecuada");
			r1.setBackground(Color.ORANGE);
			return false;
		} 
		if (r2.getText().length()!=LongAuxiliares) {
			JOptionPane.showMessageDialog(null, "Longitud de cuenta inadecuada");
			r2.setBackground(Color.ORANGE);
			return false;
		}
		if (r3.getText().length()!=10) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r3.setBackground(Color.ORANGE);
			return false;
		}
		if (!compruebaFecha(r3.getText())) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r3.setBackground(Color.ORANGE);
			return false;
		}
		if (r4.getText().length()!=10) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r4.setBackground(Color.ORANGE);
			return false;
		}
		if (!compruebaFecha(r4.getText())) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r4.setBackground(Color.ORANGE);
			return false;
		}
		
		
		return true;
		
	} // fin del metodo compruebaErrores
	
	
	
	/* ******************************************************************
	 * Este metodo comprueba la fecha introducida y su correccion
	 * 
	 * Solo comprueba la concordancia dia y mes. No comprueba el año
	 * Tampoco comprueba los años bisiestos
	 * 
	 * Recibe como argumento la fecha en formato String
	 * Devuelve TRUE/FALSE según sea correcta o incorrecta la fecha
	 **************************************************************** */
	
	private boolean compruebaFecha(String fecha) {
		
		
		String dig1=fecha.substring(0,1);
		String dig2=fecha.substring(1,2);
		String dig3=fecha.substring(3,4);
		String dig4=fecha.substring(4,5);
		
		if (!(dig1.equals("0") || dig1.equals("1") || dig1.equals("2") || dig1.equals("3"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 1");
			return false;
		}
		if (!(dig2.equals("0") || dig2.equals("1") || dig2.equals("2") || dig2.equals("3") ||
				dig2.equals("4") || dig2.equals("5") || dig2.equals("6") || dig2.equals("7") ||
				dig2.equals("8") || dig2.equals("9"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 2");
			return false;
		}
		if (!(dig3.equals("0") || dig3.equals("1"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 3");
			return false;
		}
		if (!(dig4.equals("0") || dig4.equals("1") || dig4.equals("2") || dig4.equals("3") ||
				dig4.equals("4") || dig4.equals("5") || dig4.equals("6") || dig4.equals("7") ||
				dig4.equals("8") || dig4.equals("9"))) {
			JOptionPane.showMessageDialog(null, "Formato fecha inicial: DD-MM 4");
			return false;
		}
		
		// comprobacion de correccion de fechas
		int dia=(int)Integer.parseInt(fecha.substring(0,2));
		int mes=(int)Integer.parseInt(fecha.substring(3,5));
		
		if (dia<1 || dia>31) {
			JOptionPane.showMessageDialog(null, "El día "+dia+" no es correcto");
			return false;
		}
		
		if (mes<1 || mes>12) {
			JOptionPane.showMessageDialog(null, "El mes "+mes+" no es correcto");
			return false;
		}
		
		if (mes==2 && dia>29) {
			JOptionPane.showMessageDialog(null, "La fecha "+dia+"-"+mes+" no es correcta");
			return false;
		}
		
		if ((mes==4 || mes==6 || mes==9 || mes==11 ) && dia>30) {
			JOptionPane.showMessageDialog(null, "La fecha "+dia+"-"+mes+" no es correcta");
			return false;
		}
		
		// llegado aqui, la fecha es correcta
		return true;
		
	} // fin del metodo compruebaFecha 
	
}   // ***************** fin de la class ContMnuMayor
