package fmsconta.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ContaDAO {
	
	private static Connection conexionDB;
	
	public ContaDAO () {
		
		// builder

	}
    
    
    /*
     * Este metodo sirve para crear una conexion a la DB del usuario
     * No recibe argumentos
     * Si no hay problemas devuelve un objeto Connection
     * Si hay errores devuelve null
     */
    
    public Connection ConnectDB () {
        
        // CONECTOR CON LAS BASES DE DATOS
        // DEVUELVE UN OBJETO CONNECTION SI LO CONSIGUE Y NULL SI FALLA
       	
			/*	
					try {
						Class.forName("Driver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			*/
        
        try {
        	conexionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/db393934124","root", "0600105314");
        } catch (SQLException ex) {
            System.out.println("Error conectando a la base de datos");
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        	
          return conexionDB;
       } // fin del metodo connectDB *******************************************
    
    /* *********************************
     * Este metodo devuelve conexiones
     * está pensado para multihilo
    *********************************** */
	
    protected Connection consigueConexion() {
        // devuelve una conexion
    	// pensado para multihilo
        return conexionDB;
    }    
    
    
    /* **********************************************************************************
     * este metodo sirve para leer en las tablas correspondientes los datos solicitados
     * a un login y password determinado.
     * Recibe un nombre de usuario y la password en formato String
     * Devuelve un null si hay errores
     * Si el login-pasword son correctos, devuelve un String[15]
     ********************************************************************************** */
    
    
    public String[] idUserDB (String login, String password) {
        
    //  este metodo comprueba el login de un usuario y devuelve su clave de datos
    // antes de ejecutar este metodo, es preferible comprobar con idExist
    	
    // se crea una conexion con
    // se recibe el login y el password suministrado
    // devuelve el keyUser con los datos de usuario
    // o null si no encuentra al usuario
    
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// preparamos un String para recuperar todos los datos de usuario
    	String keyUser[]=new String[15];
    	
    	ResultSet rs=null; 
    	try {   
    		rs = st.executeQuery("SELECT * FROM c_usuario WHERE usuario='"+login+"' AND password='"+password+"' ");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    
    	try {
    		
    		while (rs.next()){ 
    			// lee todos los datos y los transforma a String
    			keyUser[0] = String.valueOf(rs.getInt(1));
    			keyUser[1] = rs.getString(2);
    			keyUser[2] = rs.getString(3);
    			keyUser[3] = rs.getString(4);
    			keyUser[4] = rs.getString(5);
    			keyUser[5] = rs.getString(6);
    			keyUser[6] = String.valueOf(rs.getInt(7));
    			keyUser[7] = rs.getString(8);
    			keyUser[8] = rs.getString(9);
    			keyUser[9] = rs.getString(10);
    			keyUser[10] = rs.getString(11);
    			keyUser[11] = String.valueOf(rs.getInt(12));
    			keyUser[12] = String.valueOf(rs.getInt(13));
    			keyUser[13] = rs.getString(14);
    			keyUser[14] = rs.getString(15);
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return keyUser;
    
    } // fin de idUserDB ***********************************

    
    /* ***************************************************************
     * este metodo sirve para comprobar en la tabla correspondiente 
     * si existe un login y password determinado.
     * Recibe un nombre de usuario y la password en formato String
     * Devuelve un boolean como respuesta
     * Encontrado = TRUE ; no encontrado = FALSE
    ****************************************************************** */
    
    
    public boolean idExist (String login, String password) {
        
        //  este metodo comprueba el login de un usuario y devuelve true o false
            
        // se recibe el login y el password suministrado
        // devuelve un boolean confirmando o denegando
        
    	// crea una conexion
    	Connection con=ConnectDB();
    	
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
              
        ResultSet rs=null;      	
        try {   
        	rs = st.executeQuery("SELECT * FROM c_usuario WHERE usuario='"+login+"' AND password='"+password+"' ");
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
        	 	
        boolean userRet=false;
        try {
        		// lee el primer dato, si existe
        		while (rs.next()){ 
        			// si el primer dato es distinto de null
        			// es que existe la coincidencia
        			if (!(rs.getString(1).equals(null))) {
        				userRet=true;
        			}
        		}
        	} catch (SQLException ex) {
        		// si hay algun error cerramos la conexion y return false
        		try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    		}
        	return false;
        } 
         
        // todo ha ido bien, cerramos la conexion y retornamos el String[]
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        return userRet;
        
    } // fin de idExist ***********************************    
    
    
   
    /* **********************************************************************************
     * este metodo sirve para leer en la tabla correspondiente los datos de la empresa
     * 
     * Recibe un el key de empresa en formato String
     * Devuelve un null si hay errores
     * Si el keyEmp es correcto, devuelve un String[12]
     ********************************************************************************** */
    
    
    public String[] idEmpDB (String keyEmp) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
          
    	// preparamos un String para recuperar todos los datos de usuario
    	String datosEmpr[]=new String[12];
    	
    	ResultSet rs=null; 
    	try {   
    		rs = st.executeQuery("SELECT * FROM c_empresas WHERE keyempresa='"+keyEmp+"'");
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 
       
    
    	try {
    		
    		while (rs.next()){ 
    			// lee todos los datos y los transforma a String
    			datosEmpr[0] = String.valueOf(rs.getInt(1));
    			datosEmpr[1] = rs.getString(2);
    			datosEmpr[2] = rs.getString(3);
    			datosEmpr[3] = rs.getString(4);
    			datosEmpr[4] = rs.getString(5);
    			datosEmpr[5] = rs.getString(6);
    			datosEmpr[6] = rs.getString(7);
    			datosEmpr[7] = rs.getString(8);
    			datosEmpr[8] = rs.getString(9);
    			datosEmpr[9] = rs.getString(10);
    			datosEmpr[10] =String.valueOf(rs.getInt(11));
    			datosEmpr[11] = rs.getString(12);
    		}
    		
    	} catch (SQLException ex) {
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return datosEmpr;
    
    } // fin de idEmpDB ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para grabar en la DDBB los datos de la empresa
     * 
     * Recibe el key de empresa en formato String,un String[] con los datos
     * y un muy importante String oper ("INSERT" o "UPDATE") segun corresponda
     * Devuelve un true o false si hay errores 
     ********************************************************************************** */
    
    
    public boolean grabaEmpDB (String keyEmp, String datosEmp[], String oper) {
           
    	// crea una conexion
    	Connection con=ConnectDB();
    	
    	Statement st=null;
    	try {
    		st = con.createStatement();
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return false;
    	}
    	
    	// recoge los valores a grabar o actualizar
      //  int id=(int)Integer.parseInt(datosEmp[0]); realmente es un autoincrement
        String key=datosEmp[1];
    	String nom=datosEmp[2];
    	String dir=datosEmp[3];
    	String loc=datosEmp[4];
    	String pro=datosEmp[5];
    	String cpo=datosEmp[6];
    	String cif=datosEmp[7];
    	String ini=datosEmp[8];
    	String fin=datosEmp[9];
    	int act=(int)Integer.parseInt(datosEmp[10]);
    	String man=datosEmp[11];
    	
    	int rs=0; 
    	try {   
    		if (oper.equals("UPDATE")) {
    			rs = st.executeUpdate("UPDATE c_empresas SET keyempresa='"+key+"', " +
    				"nombre='"+nom+"', direccion='"+dir+"', localidad='"+loc+"', provincia='"+pro+"'," +
    				" codpostal='"+cpo+"', cif='"+cif+"', fechainicio='"+ini+"',fechafin='"+fin+"'," +
    						" activa='"+act+"',keymanager='"+man+"' WHERE keyempresa='"+keyEmp+"' LIMIT 1");
    		} else if (oper.equals("INSERT")) {
    			rs = st.executeUpdate("INSERT c_empresas SET keyempresa='"+key+"', " +
        				"nombre='"+nom+"', direccion='"+dir+"', localidad='"+loc+"', provincia='"+pro+"'," +
        				" codpostal='"+cpo+"', cif='"+cif+"', fechainicio='"+ini+"',fechafin='"+fin+"'," +
        						" activa='"+act+"',keymanager='"+man+"'");
    		} else System.err.println("No se recibió orden de Update o Insert. Operación no efectuada ");
    			
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return false;
    	} 
       

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    
    } // fin de grabaEmpDB ***********************************
    
}
