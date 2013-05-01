package fmsconta.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class CompanyFiles implements SystemDates{
	
	private static Connection conexionDB;
	private String keyEmpresa;
	
	
	
	/* **************************************************************
	 * Constructor
	 * Recibe como parametro un String con la clave empresa
	 * con la que se desea operar (crear o eliminar ficheros
	 * Realiza la copia en variable privada que usaran los metodos
	 **************************************************************** */
	
	public CompanyFiles(String keyEmpr) {
		
		this.keyEmpresa=keyEmpr;
		
	}
	
	
	
    /* **************************************************************
     * Este metodo sirve para crear una conexion a la DDBB del usuario
     * No recibe argumentos
     * Si no hay problemas devuelve un objeto Connection
     * Si hay errores devuelve null
     ****************************************************************/
	
    private Connection ConnectDB () {
        
        // CONECTOR CON LAS BASES DE DATOS
        // DEVUELVE UN OBJETO CONNECTION SI LO CONSIGUE Y NULL SI FALLA
        
        try {
        	conexionDB = DriverManager.getConnection(nameDB,userDB, passDB);
        } catch (SQLException ex) {
            System.out.println("Error conectando a la base de datos");
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No conecta con Base de Datos: salga de la aplicación");
            return null;
        }
        	
          return conexionDB;
       } // fin del metodo connectDB *******************************************
    
    
    
	/* ***********************************************************************
	 * Este metodo se usa para crear los ficheros de una nueva empresa
	 * 
	 * Recibe como parametro String keyempr con la clave de la empresa
	 * Devuelve un boolean, siendo TRUE/FALSE ficheros creados o no
	 * 
	 * A) Crea una conexion
	 * B) Obtiene el año actual
	 * C) Fabrica los nombres de los ficheros a crear con el keyempr
	 *    Los ficheros son una combinacion con el keyempr son unicos por empresa
	 * D) Crea los ficheros con sus campos correspondientes
	 ************************************************************************ */
    
	public boolean creaFilesCompany() {
		
	    
		// crea una conexion
    	Connection con=ConnectDB();
    	
    	// crea un objeto createStatement
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        	} 
        
		// Obtiene el año actual
		Calendar dat=Calendar.getInstance();
		int dato=dat.get(Calendar.YEAR);
		String yearAct=String.valueOf(dato);  
		String yearAnt=String.valueOf(dato-1);
		
		//elabora los nombres de los ficheros
        int rs=0;
        String fich1="c_"+this.keyEmpresa+"pgc";
        String fich2="c_"+this.keyEmpresa+yearAct+"diario";
        String fich3="c_"+this.keyEmpresa+yearAnt+"diario";
        String fich4="c_"+this.keyEmpresa+"dtsfac";
        String fich5="c_"+this.keyEmpresa+yearAct+"ivaclie";
        String fich6="c_"+this.keyEmpresa+yearAct+"ivaprov";
        String fich7="c_"+this.keyEmpresa+"vtos";
        
        // crea los ficheros
        try {
        	// Fichero del PGC
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS "+fich1+" (cuenta VARCHAR( 8 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL," +
        			" nombre VARCHAR( 40 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"activa VARCHAR( 1 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"saldo DECIMAL( 10, 2 ) NOT NULL )");
        	// FICHERO DIARIO
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS  "+fich2+" (numasto INT NOT NULL, numapunte INT NOT NULL, " +
        			"fecha DATE NOT NULL, cuenta VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"documento VARCHAR( 15 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"concepto VARCHAR( 50 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"contrapartida VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"apunte VARCHAR( 1 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"importe DECIMAL( 10, 2 ) NOT NULL )");
        	//FICHERO DIARIO ANTERIOR
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS  "+fich3+" (numasto INT NOT NULL, numapunte INT NOT NULL, " +
        			"fecha DATE NOT NULL, cuenta VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"documento VARCHAR( 15 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"concepto VARCHAR( 50 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"contrapartida VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"apunte VARCHAR( 1 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"importe DECIMAL( 10, 2 ) NOT NULL )");
    		//FICHERO DATOS PROVEEDORES
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS "+fich4+" ( numero VARCHAR( 4 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"nombre VARCHAR( 40 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"tipo VARCHAR( 8 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"cuenta VARCHAR( 9 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"cif VARCHAR( 9 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"direccion VARCHAR( 50 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"localidad VARCHAR( 30 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"cpostal VARCHAR( 5 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        				"importe DECIMAL( 10, 2 ) NOT NULL )");
        	// FICHERO IVACLIENTES      	
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS "+fich5+" ( numero VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL," +
        			" fecha DATE NOT NULL, cliente VARCHAR( 4 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"nif VARCHAR( 30 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, base1 DECIMAL( 8, 2 ) NOT NULL, " +
        			"iva1 DECIMAL( 8, 2 ) NOT NULL, base2 DECIMAL( 8, 2 ) NOT NULL, iva2 DECIMAL( 8, 2 ) NOT NULL, " +
        			"base3 DECIMAL( 8, 2 ) NOT NULL, iva3 DECIMAL( 8, 2 ) NOT NULL, irpf DECIMAL( 8, 2 ) NOT NULL, " +
        			"total DECIMAL( 10, 2 ) NOT NULL, enlace INT NOT NULL )");
        	//FICHERO IVAPROVEEDORES
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS "+fich6+" ( numero VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"fecha DATE NOT NULL, proveed VARCHAR( 4 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"nif VARCHAR( 30 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, base0 DECIMAL( 8, 2 ) NOT NULL, " +
        			"base1 DECIMAL( 8, 2 ) NOT NULL, iva1 DECIMAL( 8, 2 ) NOT NULL, base2 DECIMAL( 8, 2 ) NOT NULL, " +
        			"iva2 DECIMAL( 8, 2 ) NOT NULL, base3 DECIMAL( 8, 2 ) NOT NULL, iva3 DECIMAL( 8, 2 ) NOT NULL, " +
        			"irpf DECIMAL( 8, 2 ) NOT NULL, total DECIMAL( 10, 2 ) NOT NULL, enlace INT NOT NULL )");
        	// FICHERO VENCIMIENTOS	 
        	rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS "+fich7+" ( codclie VARCHAR( 4 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"numfact VARCHAR( 10 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, fechafact DATE NOT NULL, " +
        			"tipo VARCHAR( 1 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, impvto DECIMAL( 10, 2 ) NOT NULL, " +
        			"fechavto DATE NOT NULL, sit VARCHAR( 1 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL, " +
        			"numapunte INT NOT NULL, banco VARCHAR( 9 ) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL)");
        	
        } catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        
         
        // todo ha ido bien, cerramos la conexion
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return false;
    			}
        return true;
		
	} // fin del metodo creaFilesCOmpany

	
	/* ***********************************************************************
	 * Este metodo se usa para borrar todos los ficheros de la empresa
	 * keyempr.
	 * Esta opcion no es reversible. Desaparece la informacion
	 * 
	 * No recibe parametros. Obtiene el keyempr del constructor
	 * Devuelve un boolean, siendo TRUE/FALSE sin problemas o no
	 * 
	 * A) Crea una conexion
	 * B) Obtiene el año actual
	 * C) Fabrica los nombres de los ficheros a borrar con el keyempr
	 * D) Borra primero los ficheros que siempre se crean por defecto
	 * E) Borra despues los ficheros de diarios de años anteriores
	 ************************************************************************ */
	
	public boolean deleteFilesCompany() {
		    
		// recoge la informacion del keyempr a borrar
		String Keyempr=this.keyEmpresa;
	
		// crea una conexion
    	Connection con=ConnectDB();
    	
    	// crea un objeto createStatement
        Statement st=null;
        try {
        	st = con.createStatement();
        	} catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		return false;
        	} 
        
		// Obtiene el año actual
		Calendar dat=Calendar.getInstance();
		int year=dat.get(Calendar.YEAR);
		String yearAct=String.valueOf(year);  
		String yearAnt=String.valueOf(year-1);
		
		//elabora los nombres de los ficheros
        int rs=0;
        String fich1="c_"+Keyempr+"pgc";
        String fich2="c_"+Keyempr+yearAct+"diario";
        String fich3="c_"+Keyempr+yearAnt+"diario";
        String fich4="c_"+Keyempr+"dtsfac";
        String fich5="c_"+Keyempr+yearAct+"ivaclie";
        String fich6="c_"+Keyempr+yearAct+"ivaprov";
        String fich7="c_"+Keyempr+"vtos";
        
        // crea los ficheros
        try {
        	// Ficheros minimos existentes para borrar
        	rs = st.executeUpdate("DROP TABLE IF EXISTS "+fich1+", "+fich2+", "+fich3+", "+fich4+"," +
        			""+fich5+", "+fich6+", "+fich7+"");
        	// Ficheros de diarios hasta 1+5 años atras es decir
        	// borraria hasta el diario del año actual - 6
        	String fichn;
        	for (int n=1; n<6;n++) {
        		yearAnt=String.valueOf(year-1-n);
        		fichn="c_"+Keyempr+yearAnt+"diario";
        		rs = st.executeUpdate("DROP TABLE IF EXISTS "+fichn+"");
        	}
        } catch (SQLException ex) {
        		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	return false;
        	} 
        
        // todo ha ido bien, cerramos la conexion
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return false;
    			}
        return true;
		
	} // fin del metodo deleteFilescompany
	
	
} // FIN DE LA CLASE COMPANYFILES *******************************************************
