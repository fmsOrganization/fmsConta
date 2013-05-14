package fmsconta.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContMnuResultados implements ActionListener, Settings {

	
	private static final long serialVersionUID = 1L;
	private JPanel menuResultados;
	private JPanel auxMenu;
	private JPanel north;
	private JPanel south;
	private JTextField r1;
	private JTextField r2;
	private JLabel space1=new JLabel(" ");
	private JLabel space2=new JLabel(" ");
	private JLabel space3=new JLabel(" ");
	private JLabel space4=new JLabel(" ");
	private JButton listar;
	private JButton cancelar;
	
	//private ContListadoRsdos listaResultados;
	
	
	
	/* ****************************************************************************
	 * 
	 * Este metodo constructor implementa el menu de impresion de
	 * la Cuenta de Resultados
	 * 
	 * ************************************************************************** */
	
	public ContMnuResultados() {
		
		
		menuResultados=new JPanel();
		menuResultados.setBackground(ColorFondo);
		menuResultados.setPreferredSize(new Dimension(400,300));
		menuResultados.setLayout(new BoxLayout(menuResultados,BoxLayout.Y_AXIS));
		
		north=new JPanel();
		north.setBackground(ColorFondo);
		north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
		JLabel title1=new JLabel("Cuenta de Resultados");
		title1.setFont(Fuente1);
		title1.setAlignmentX((float)0.5);
		north.add(title1);
		
		auxMenu=new JPanel();
		auxMenu.setBackground(ColorFondo);
		auxMenu.setLayout(new GridLayout(4,2));
		JLabel n1=new JLabel("Desde fecha");
		n1.setFont(Fuente3);
		JLabel n2=new JLabel("Hasta fecha");
		n2.setFont(Fuente3);
		JLabel n3=new JLabel(" ");
		JLabel n4=new JLabel(" ");
		JLabel n5=new JLabel(" ");
		JLabel n6=new JLabel(" ");

		r1=new JTextField("01-01-2012");
		r2=new JTextField("31-12-2012");
		
		auxMenu.add(n3);
		auxMenu.add(n4);
		auxMenu.add(n1);
		auxMenu.add(r1);

		auxMenu.add(n2);
		auxMenu.add(r2);
		auxMenu.add(n5);
		auxMenu.add(n6);

		south=new JPanel();
		south.setBackground(ColorFondo);
		south.setLayout(new FlowLayout());
		listar=new JButton("Listar Resultados");
		listar.setToolTipText("Muestra la cuenta de Resultados entre las fechas seleccionadas");
		south.add(listar);
		cancelar=new JButton("Cancelar");
		cancelar.setToolTipText("Borra la pantalla");
		south.add(cancelar);
		
		menuResultados.add(space1);
		menuResultados.add(space2);
		menuResultados.add(north);
		menuResultados.add(space3);
		menuResultados.add(auxMenu);
		menuResultados.add(space4);
		menuResultados.add(south);
		
		menuResultados.setVisible(true);
		
		listar.addActionListener(this);
		cancelar.addActionListener(this);
		
	}
	
	
	
	/* ******************************************************************
	 * Este metodo simplemente retorna el JPanel a la pantalla principal
	 ******************************************************************* */
	
	public JPanel retorna() {
		return menuResultados;
	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==listar) {
						
			if (compruebaErrores()) {
				// no habiendo errores
				/*
				menuResultados.setVisible(false);
				listaResultados=new ContListadoSyS("EC001","Ecovitalia",this.r1.getText(),this.r2.getText(),this.r3.getText(),this.r4.getText());
				menuResultados.remove(space1);
				menuResultados.remove(space2);
				menuResultados.remove(north);
				menuResultados.remove(space3);
				menuResultados.remove(auxMenu);
				menuResultados.remove(space4);
				menuResultados.remove(south);
				menuResultados.remove(south);

				menuResultados.add(listaResultados.retorna());
				menuResultados.setPreferredSize(new Dimension(850,(listaResultados.sizeComponent*20)+125));
				menuResultados.setAlignmentY(TOP_ALIGNMENT);
				menuResultados.setVisible(true);
				*/
				
			}

		}
		
		if (source==cancelar) {
			menuResultados.setVisible(false);
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

		
		if (r1.getText().length()!=10) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r1.setBackground(Color.ORANGE);
			return false;
		}
		if (!compruebaFecha(r1.getText())) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r1.setBackground(Color.ORANGE);
			return false;
		}
		if (r2.getText().length()!=10) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r2.setBackground(Color.ORANGE);
			return false;
		}
		if (!compruebaFecha(r2.getText())) {
			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: xx-xx-xxxx");
			r2.setBackground(Color.ORANGE);
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
	
} // ***************************** fin de la class ContMnuResultados
