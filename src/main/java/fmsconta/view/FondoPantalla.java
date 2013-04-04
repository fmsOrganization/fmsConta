package fmsconta.view;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class FondoPantalla extends JPanel {
	
	// implementa la imagen de portada con un fichero .jpg
	
	private String pathImageFiles="src/pictures/";
	private ImageIcon portada;
	
	public FondoPantalla(){
		portada=new ImageIcon(pathImageFiles+"portada.jpg");
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(portada.getImage(),0,0,400,300,this);
	}
}
