package fmsconta.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.SwingConstants;


public class ShowInfoMayor extends JFrame implements ActionListener, Settings {
	
	private JFrame auxWindow;
	private JScrollPane panelScroll;
	private JPanel listadoDatos;
	private JPanel listadoMayor;
	private JLabel title1;
	private JLabel title2;
	private JLabel title3;
	private String datos2[][];
	
	private JButton impress;

	public ShowInfoMayor() {
	// builder
	}
		
		
	public ShowInfoMayor(String datos[][],JLabel title1,JLabel title2,JLabel title3){
			
		this.title1=title1;
		this.title2=title2;
		this.title3=title3;
		
			datos2=datos;
		
			// creamos primero el marco de la ventana auxiliar
			auxWindow=new JFrame("Listado de cuentas de Mayor");
			auxWindow.setMinimumSize(new Dimension(700,500));
			auxWindow.setPreferredSize(new Dimension(1000,700));
			auxWindow.setMaximumSize(new Dimension(1000,700));
			auxWindow.setResizable(false);
			auxWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			auxWindow.getContentPane().setBackground(colorBlanco);
			
			panelScroll=new JScrollPane();
			panelScroll.setPreferredSize(new Dimension(1000,500));
			panelScroll.setMaximumSize(new Dimension(1000,700));
			
			listadoMayor=new JPanel();
			listadoMayor.setLayout(new BoxLayout(listadoMayor,BoxLayout.Y_AXIS));
			listadoMayor.setAlignmentX(LEFT_ALIGNMENT);
			listadoMayor.setBackground(colorBlanco);
			
			// titulo
			JPanel north1=new JPanel();
			north1.setLayout(new BoxLayout(north1,BoxLayout.Y_AXIS));
			north1.setAlignmentX(LEFT_ALIGNMENT);
			north1.setBackground(colorBlanco);	
		    this.title1.setFont(fuente1);
		    this.title2.setFont(fuente2);
		    this.title3.setFont(fuente2);
		    this.title1.setHorizontalAlignment(SwingConstants.LEFT);
		    this.title1.setAlignmentX(LEFT_ALIGNMENT);
		    north1.setAlignmentX((float)1);
			north1.add(this.title1);
			north1.add(this.title2);
			north1.add(this.title3);
			
			
			// centro
			JPanel center1=new JPanel();
			center1.setLayout(new BoxLayout(center1,BoxLayout.Y_AXIS));
			center1.setBackground(colorBlanco);
		    // creacion del panel de botones inferior
		    JPanel south1=new JPanel();
		    south1.setBackground(colorBlanco);
		    impress=new JButton("Imprimir");
		    impress.setToolTipText("Abre una pantalla");
		    south1.add(impress);
			
			
			// mostraremos la informacion en JLabels dinamicos
			JLabel[][] lab=new JLabel[datos2.length][11];
								
			listadoDatos=new JPanel();
			listadoDatos.setLayout(new GridBagLayout());
			listadoDatos.setBackground(colorBlanco);
			GridBagConstraints constraints;
			Insets alrededor=new Insets(5,5,5,5);
				
					
			for (int i=0;i<datos2.length;i++) {
				constraints=new GridBagConstraints();
				constraints.gridy=i;
				constraints.weightx=1;
				constraints.weighty=1.5;
				constraints.insets=alrededor;
				constraints.fill=GridBagConstraints.NONE;
				constraints.anchor=GridBagConstraints.WEST;

				for (int j=0;j<10;j++) {
					// creamos la etiqueta y le agregamos el dato
					lab[i][j]=new JLabel();
					lab[i][j].setHorizontalAlignment(SwingConstants.LEFT);
					lab[i][j].setFont(fuente4);
					lab[i][j].setText(datos2[i][j]);
					if (j==7) {
						constraints.anchor=GridBagConstraints.EAST;
					}
					constraints.gridx=j;
					listadoDatos.add(lab[i][j],constraints);
				}
						
			}
					
			center1.add(listadoDatos);
					
			listadoMayor.add(north1);
			listadoMayor.add(center1);
			listadoMayor.add(south1);
					
			listadoMayor.setVisible(true);
			panelScroll.setViewportView(listadoMayor);
			panelScroll.getViewport().setView(listadoMayor);
			auxWindow.add(panelScroll);
			auxWindow.setVisible(true);
			
			impress.addActionListener(this);
			
	}  // fin del constructor showinfo


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
		
	
} // *************** FIN DE LA CLASE SHOWINFO
