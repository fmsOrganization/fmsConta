package fmsconta.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
;

public class ContaFileDAO implements FileSystemDates {

	private String lpd="Y";
	private String cgu="Y";



	public ContaFileDAO () {
		//constructor
	}

	
	
	  

      
  
    
    
    
    /* **********************************************************************************
     * este metodo sirve para leer en el fichero usuarios los datos solicitados
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
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
    	// crea el string[] de retorno y un auxiliar de lectura
    	String datosUser[]=new String[15];
    	String datosLectura[]=new String[15];
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileUsers);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return null;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	datosUser=null;
	    	datosLectura=null;
	    	
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		// si login y password coinciden dejamos de leer
	    		if (datosLectura[3].equals(login) && datosLectura[4].equals(password)){
	    			datosUser=datosLectura;
	    			break;
	    		} 
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return null;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
				return null;
			}
		}
    	
    	return datosUser;
    
    } // fin de idUserDB ***********************************

    
    
    /* *******************************************************************
     * Este metodo convierte una lectura de un fichero en un array
     * 
     * Recibe como parametros un String con la linea de lectura
     * devuelve un array[] con los datos
     *****************************************************************/
    
    private String[] leeDatos(String data) {
		    	
    	String lectura[];
    	
    	lectura=data.split(";");
    	
		return lectura;
	} // fin del metodo leeDatos
    
    

	/* ***************************************************************
     * este metodo sirve para comprobar en el fichero de usuarios 
     * si existe un login y password determinado.
     *
     * Recibe un nombre de usuario y la password en formato String
     * Devuelve un boolean como respuesta
     * Encontrado = TRUE ; no encontrado = FALSE
    ****************************************************************** */
    
    public boolean idExist (String login, String password) {
        
        //  este metodo comprueba el login de un usuario y devuelve true o false
            
        // se recibe el login y el password suministrado
        // devuelve un boolean confirmando o denegando
        Boolean userRet=false;

    	// crea una conexion
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
    	// crea el string[] auxiliar de lectura
    	String datosLectura[]=new String[15];
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileUsers);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return false;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	datosLectura=null;
	    	
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		// si login y password coinciden dejamos de leer
	    		if (datosLectura[3].equals(login) && datosLectura[4].equals(password)){
	    			userRet=true;
	    			break;
	    		} 
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return false;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
				return false;
			}
		}

        return userRet;
        
    } // fin de idExist ***********************************    
    
    
    
    /* ***************************************************************
     * este metodo sirve para comprobar en DDBB 
     * si existe un keyUser determinado
     * Recibe un String con el keyUser
     * 
     * Devuelve un boolean como respuesta
     * Encontrado = TRUE ; no encontrado = FALSE
    ****************************************************************** */
    
    public boolean idKeyExist (String keyUser) {
        
        //  este metodo comprueba el key y devuelve true o false
            

        Boolean userRet=false;

    	// crea una conexion
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
    	// crea el string[] auxiliar de lectura
    	String datosLectura[]=new String[15];
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileUsers);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return false;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	datosLectura=null;
	    	
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		// si el keyUser coincide dejamos de leer
	    		if (datosLectura[1].equals(keyUser)){
	    			userRet=true;
	    			break;
	    		} 
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return false;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
				return false;
			}
		}

        return userRet;    	
        
    } // fin de idKeyExist ***********************************    
    
    
    
    /* **********************************************************************************
     * este metodo sirve para leer en la tabla correspondiente los datos de la empresa
     * 
     * Recibe un el key de empresa en formato String
     * Devuelve un null si hay errores
     * Si el keyEmp es correcto, devuelve un String[12]
     ********************************************************************************** */
    
    public String[] idEmpDB (String keyEmp) {
           
    	String datosLectura[];
    	String datosUser[];
    	
    	// crea una conexion con el fichero de empresas
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
          
    	try {
			fichero=new File(PathDataFiles+FileCompanys);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error al abrir el fichero");
			e.printStackTrace();
			return null;
		}
    	
    	try {
			lector=new FileReader(fichero);
			buff=new BufferedReader(lector);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error leyendo fichero");
			e.printStackTrace();
			return null;
		}
    	
    	
    	// comenzamos la lectura linea a linea
    	String data=null;
    	datosUser=null;
    	datosLectura=null;
    	
    	try {
			while ((data=buff.readLine())!=null) {
				// convertimos la lectura en array
				datosLectura=leeDatos(data);
				// si login y password coinciden dejamos de leer
				if (datosLectura[1].equals(keyEmp)){
					datosUser=datosLectura;
					break;
				} 
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error leyendo el buffer del fichero");
			e.printStackTrace();
			return null;
		} finally {
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando fichero");
				e.printStackTrace();
			}
		}

    	return datosUser;
    
    } // fin de idEmpDB ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para buscar en la DDBB los datos de las empresas de un manager
     * 
     * Recibe un key de user en formato String
     * Devuelve un null si hay errores
     * Si el keyUser es correcto, devuelve un String[n][12] siendo n el num.de empresas
     ********************************************************************************** */
    
    public String[][] buscaEmpresasUsuDB (String keyUser) {
   
        String datosLectura[];
    	String datosUser[][];
    	
    	// crea una conexion con el fichero de empresas
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
          
    	try {
			fichero=new File(PathDataFiles+FileCompanys);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error al abrir el fichero");
			e.printStackTrace();
			return null;
		}
    	
    	try {
			lector=new FileReader(fichero);
			buff=new BufferedReader(lector);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error leyendo fichero");
			e.printStackTrace();
			return null;
		}
    	
    	
    	// comenzamos la lectura linea a linea
    	String data=null;
    	datosLectura=new String[12];
    	ArrayList<ArrayList<String>> arrayUser=new ArrayList<ArrayList<String>>();
    	//datosUser=new String[3][12];
    	
    	//int k=0;
    	try {
			while ((data=buff.readLine())!=null) {
				// convertimos la lectura en array
				datosLectura=leeDatos(data);
				// si coincide con el keyUser grabamos los datos del usuario
				// en el arrayList
				if (datosLectura[11].equals(keyUser)){
					ArrayList<String> arrayAux=new ArrayList<String>();
					for (int n=0;n<12;n++) {
						arrayAux.add(datosLectura[n]);
					}
					arrayUser.add(arrayAux);
					//System.arraycopy(datosLectura, 0, datosUser[k], 0, datosLectura.length);
					//k++;
				} 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error leyendo el buffer del fichero");
			e.printStackTrace();
			return null;
		} finally {
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando fichero");
				e.printStackTrace();
			}
		}
    	// creamos la matriz de retorno y copiamos datos
    	datosUser=new String [arrayUser.size()][12];
    	// obtenida la informacion, pasamos el arraylist
    	// a una matriz para return de los datos
    	for (int i=0;i<arrayUser.size();i++) {
    		datosUser[i]=arrayUser.get(i).toArray(new String[12]);
    	}  	
    	
    	return datosUser;
      
    
    } // fin de BuscaEmpresasUsuDB ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para buscar en la DDBB los usuarios gestionados por un manager
     * 
     * Recibe un key de user en formato String
     * Instancia el metodo buscaEmpresasUsuDB para encontrar las empresas
     * y luego busca en los usuarios en la DDBB
     * 
     * Devuelve un null si hay errores
     * Si el keyUser es correcto, devuelve un String[n][15] siendo n el num.de usuarios
     * 
     * PROCEDIMIENTO
     * A) Obtenemos un array de las empresas del manager, instanciando el metodo
     *    buscaEmpresasUsuDB
     * B) Creamos una conexion al fichero de usuarios
     * C) Leemos usuario a usuario
     * D) En cada usuario hacemos un array instanciando leedatos()
     * E) Comprobamos si en el array del usuario está registrada alguna empresa mediante
     *    un bucle y si lo esta lo seleccionamos y lo grabamos en arrayList
     * F) Finalizada la lectura de usuarios, pasamos el arrayList al array de retorno
     ********************************************************************************** */
    
    public String[][] buscaUsuariosManagerDB (String keyUser) {
         
    	// busca las empresas del manager
    	String empMan[][]=buscaEmpresasUsuDB(keyUser);
    	// si no hay empresas no hay usuarios gestionados: salimos con null
    	if (empMan==null) return null;
    	
    	// crea una conexion
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
          
    	// preparamos un String para recuperar todos los datos de usuario
    	// considerando la longitud de la matriz empMan (n empresas managed)
    	// String datosUser[][]=new String[empMan.length][16];
    	
    	// abrimos fichero, lector y buffer
    	try {
			fichero=new File(PathDataFiles+FileUsers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error al abrir el fichero");
			e.printStackTrace();
			return null;
		}
    	try {
			lector=new FileReader(fichero);
			buff=new BufferedReader(lector);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error leyendo fichero");
			e.printStackTrace();
			return null;
		}


    	String data=null;	// lectura de linea
    	String datosLectura[]=new String[15];	// array lectura datos usuarios
    	ArrayList <ArrayList<String>> userSelec=new ArrayList <ArrayList<String>>();
    	
    	// bloque de lectura de datos
    	try {
			while ((data=buff.readLine())!=null) {
				// convertimos la lectura en array
				datosLectura=leeDatos(data);
				// cogemos una por una las empresas del manager y...
				for (int k=0;k<empMan.length;k++) {
					// si alguna de las empresas coincide entonces hay que
					// seleccionar al usuario SI NO ES EL MANAGER
					if (((datosLectura[7].equals(empMan[k][1]))||
							(datosLectura[8].equals(empMan[k][1]))||
							(datosLectura[9].equals(empMan[k][1]))) && (!(empMan[k][11].equals(keyUser))) ){
						ArrayList<String> lect=new ArrayList<String>();
						for (int n=0;n<15;n++) {
							lect.add(datosLectura[n]);
						}
						userSelec.add(lect);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error leyendo el buffer del fichero");
			e.printStackTrace();
			return null;
		} finally {
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando fichero");
				e.printStackTrace();
			}
		}
    	
    	// creamos el array de retorno de datos
    	String datosUser[][]=new String[userSelec.size()][15];
    	// copiamos los datos del arraylist al array
    	for (int n=0;n<userSelec.size();n++){
    		datosUser[n]=userSelec.get(n).toArray(new String[15]);
    	}
    	
    	return datosUser;
    
    } // fin de BuscaUsuariosManagerDB ***********************************
    
    
    
    /* **********************************************************************************
     * Este metodo sirve para grabar en el fichero los datos de la empresa
     * 
     * Recibe el key de empresa en formato String,un String[] con los datos
     * y un String oper ("INSERT" o "UPDATE") que se mantiene por compatibilidad con DDBB
     * pero no tiene utilidad
     * 
     * Devuelve un true o false si hay errores 
     ********************************************************************************** */
    
    public boolean grabaEmpDB (String keyEmp, String datosEmp[], String oper) {
           
    	// crea una conexion al fichero de empresas
    	File fichero=null;
    	FileWriter grabador=null;
    	BufferedWriter buff=null;
    	
    	try {
			fichero=new File(PathDataFiles+FileCompanys);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Error en nombre de fichero");
			e1.printStackTrace();
			return false;
		}
    	
    	try {
			grabador=new FileWriter(fichero);
			buff=new BufferedWriter(grabador);
		} catch (IOException e1) {
			System.err.println("Error al abrir canal de grabación");
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
    	
    	// recoge los valores a grabar o actualizar
        String id=datosEmp[0];
        String key=datosEmp[1];
    	String nom=datosEmp[2];
    	String dir=datosEmp[3];
    	String loc=datosEmp[4];
    	String pro=datosEmp[5];
    	String cpo=datosEmp[6];
    	String cif=datosEmp[7];
    	String ini=datosEmp[8];
    	String fin=datosEmp[9];
    	String act=datosEmp[10];
    	String man=datosEmp[11];
    	
    	// se compone un String para grabar de una vez todos los campos
    	// separados por punto y coma
    	String rs;
    	rs = id+";"+key+";"+nom+";"+dir+";"+loc+";"+pro+";"+cpo+";"+cif+";"+ini+";"+fin+";"+act+";"+man;
    		
    	try {
			buff.write(rs);
			// se inserta una marca de linea para separar cada grabacion
			buff.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("Error al grabar los datos de empresa");
			e1.printStackTrace();
			return false;
		} finally {
			try {
				buff.flush();
				buff.close();
				grabador.close();
			} catch (IOException e) {
				System.err.println("Error en el proceso de cerrar grabación");	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    	// si todo ha ido bien retornamos true
    	return true;
    
    }  // fin de grabaEmpDB ***********************************
    
    
    
    /* ********************************************************************
     * este metodo sirve para grabar en la tabla del usuario manager
     * la empresa que acaba de crear
     * 
     * Recibe como argumentos el keyUser y el KeyEmpr y la posicion a grabar
     * Devuelve TRUE o FALSE como resultado de la operacion
     * 
     * PROCEDIMIENTO:
     * A) Abrimos el fichero a modificar
     * B) Comenzamos la lectura del fichero linea a linea, y vamos almacenando
     *    la informacion en un ArrayList
     * C) Cuando llegamos al usuario keyUser, modificamos la informacion de
     *    empresa, grabandola en el sitio adecuado [8]o[9]
     * D) Terminamos de leer el fichero y de almacenar en ArrayList
     * E) Abrimos el fichero para escritura
     * F) Grabamos el contenido del ArrayList
     ********************************************************************** */

    
    public boolean grabaEmpresaUsu (String keyUser, String keyEmpr, int position) {     
                

    	// crea una conexion
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
    	// crea el string[] auxiliar de lectura
    	String datosLectura[]=new String[15];
    	// crea el arrayList acumulador de la informacion del fichero
    	ArrayList<ArrayList<String>> fichUsu= new ArrayList<ArrayList<String>>();
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileUsers);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return false;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	datosLectura=null;
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		// si el keyUser coincide dejamos de leer
	    		if (datosLectura[1].equals(keyUser)){
	    			// modificacion del dato
	    			//  grabamos el dato emp en el array
	    			if (position==8) {
	    				datosLectura[8]=keyEmpr;
	    			} else {
	    				datosLectura[9]=keyEmpr;
	    			}
	    		}
	    		// pasamos la info al array de grabacion
	    		ArrayList<String> fichAcum= new ArrayList<String>();
	    		for (int n=0;n<datosLectura.length;n++){
	    			fichAcum.add(datosLectura[n]);
	    		}
	    		fichUsu.add(fichAcum);
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return false;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
			}
		}

    	// **** ENTRAMOS EN LA FASE DE GRABACION
    	
    	// crea una conexion
    	FileWriter grabador=null;
    	BufferedWriter rebuff=null;

    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileUsers);

	    	try {
	    		// abrimos lecturas
				grabador=new FileWriter(fichero);
				rebuff=new BufferedWriter(grabador);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error abriendo canal de grabación de fichero");
				e1.printStackTrace();
				return false;
			}
	    	
	    	// comenzamos la grabacion linea a linea
	    	for (int n=0;n<fichUsu.size();n++) {
	    		// pasamos la info al array de grabacion
	    		// grabamos convirtiendo el array en String
	    		try {
					rebuff.write(fichUsu.get(n).toArray(new String[15]).toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("Error copiando información al fichero");
					e.printStackTrace();
					rebuff.close();
					grabador.close();
					return false;
				}
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return false;
			
		} finally {
			
			try {
				rebuff.flush();
				rebuff.close();
				grabador.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
			}
		}
    	
    	
        return true; 
    
    }  // fin de grabaEmpresaUsu ***********************************
    
    
    
    /* **********************************************************************************
     * este metodo sirve para grabar en la DDBB la aceptación de la LPD y de las CGU
     * 
     * Recibe como argumento el login y el password y no devuelve nada 
     ********************************************************************************** */
    
    public void grabaCGULPD (String login, String pass) {
   /*        
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
    	}
    	
    	// recoge los valores a grabar o actualizar
    	String lpd="Y";
    	String cgu="Y";
    	int rs=0;
    	
    	try { 
    		rs = st.executeUpdate("UPDATE c_usuario SET lpd='"+lpd+"', " +
    			"cgu='"+cgu+"' WHERE usuario='"+login+"' AND password='"+pass+"' LIMIT 1");
    			
    	} catch (SQLException ex) {
    		Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
    		// si hay algun error cerramos la conexion y return null
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} 

    	// si todo ha ido bien
    	// cerramos la conexion y retornamos el String[]
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    */
    }  // fin de grabaCGULPD ***********************************
     
   
    
    /* *************************************************************
     * Este metodo comprueba si la lpd y las cgu estaban aceptadas
     * ambas han obtenido el valor en idExist()
     * retorna true si cumplen y false si no cumplen 
     *************************************************************** */
    
    public boolean cumpleCGULPD() {
    	
    	if ((this.cgu.equals("Y"))&&(this.lpd.equals("Y"))) {
    		return true;
    	}
    	
    	return false;
    }
    
   
    
    /* **********************************************************************************
     * Este metodo sirve para grabar en el fichero los datos del usuario
     * 
     * Recibe como parametros un array con los datos de usuario
     * un String oper ("INSERT" o "UPDATE") que se mantiene por compatibilidad con DDBB
     * pero no tiene utilidad
     * la categoria del usuario  siendo 1=manager, etc
     * 
     * Devuelve un true o false si hay errores 
     ********************************************************************************** */
    
    public boolean grabaUsuDB (String datosUsu[], String oper, int categoria) {
        
    	// crea una conexion al fichero de usuarios
    	File fichero=null;
    	FileWriter grabador=null;
    	BufferedWriter buff=null;
    	
    	try {
			fichero=new File(PathDataFiles+FileUsers);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Error en nombre de fichero");
			e1.printStackTrace();
			return false;
		}
    	
    	try {
			grabador=new FileWriter(fichero,true);
			buff=new BufferedWriter(grabador);
		} catch (IOException e1) {
			System.err.println("Error al abrir canal de grabación");
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

    	// recoge los valores a grabar o actualizar
        String id="1"; 
        String key=datosUsu[1];
      	String nom=datosUsu[2];
      	String log=datosUsu[3];
      	String pas=datosUsu[4];
      	String ema=datosUsu[5];
      	String cat=datosUsu[6];
      	String em1=datosUsu[7];
      	String em2=datosUsu[8];
      	String em3=datosUsu[9];
      	String sel=datosUsu[10];
      	String fec=datosUsu[11];
      	String act=datosUsu[12];
      	String lpd=datosUsu[13];
      	String cgu=datosUsu[14];
    	
    	// se compone un String para grabar de una vez todos los campos
    	// separados por punto y coma
    	String rs;
    	rs = id+";"+key+";"+nom+";"+log+";"+pas+";"+ema+";"+cat+";"+em1+";"+em2+";"+em3+
    			";"+sel+";"+fec+";"+act+";"+lpd+";"+cgu;
    		
    	try {
			buff.write(rs);
			// se inserta una marca de linea para separar cada grabacion
			buff.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("Error al grabar los datos de usuario");
			e1.printStackTrace();
			return false;
		} finally {
			try {
				buff.flush();
				buff.close();
				grabador.close();
			} catch (IOException e) {
				System.err.println("Error en el proceso de cerrar grabación");	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    	// si todo ha ido bien retornamos true
    	return true;
    
    }  // fin de grabaUsuDB ***********************************
    
    
    
    /* ***************************************************************
     * Este metodo sirve para comprobar en la tabla correspondiente 
     * cuantos usuarios tiene una empresa
     * 
     * Recibe un parametro String con el keyEmpresa a buscar 
     * Devuelve un INT como respuesta del numero de usuarios
     * Si hay algun error devuelve -1
    ****************************************************************** */
    
    public int numUserAutorizados(String keyEmpresa) {
    	
    	// crea una conexion con el fichero de datos
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
    	// crea el int de retorno y un auxiliar de lectura
    	int numberUsers=0;
    	String datosLectura[];
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileUsers);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return -1;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	datosLectura=null;
	    	
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		// si login y password coinciden dejamos de leer
	    		if ((datosLectura[7].equals(keyEmpresa))||(datosLectura[8].equals(keyEmpresa))||(datosLectura[9].equals(keyEmpresa))){
	    			numberUsers++;
	    		} 
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return -1;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
				return -1;
			}
		}
    	
    	return numberUsers;
    
    } // fin de numUserAutorizados *********************************** 
    
    
    
    /* ************************************************************************************
     * este metodo devuelve un string de datos con los nombres de las empresas del usuario
     * 
     * Recibe un array con los datos del usuario
     * Devuelve un null si hay errores
     * Si el keyEmp es correcto, devuelve un String[3] con nombres
     ********************************************************************************** */
    
    public String[] showNamesCompDB (String user[]) {
           
    	// crea una conexion
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
          
    	// cogemos los keyEmpr del array del usuario
    	String emp1=user[7];
    	String emp2=user[8];
    	String emp3=user[9];
    	
    	ArrayList<String>nombres=new ArrayList<String>();
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+FileCompanys);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return null;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	String datosLectura[]=null;
	    	
	    	
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		// si el key de empresa es igual a algun keyempr del usuario
	    		if ((datosLectura[1].equals(emp1))||(datosLectura[1].equals(emp2))||(datosLectura[1].equals(emp3))){
	    			// grabamos el nombre en el arrayList
	    			nombres.add(datosLectura[2]);
	    		} 
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return null;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
				return null;
			}
		}
    	
    	// preparamos un String para devolver la informacion solicitada
    	String nameEmpr[]=new String[nombres.size()];
    	for (int n=0;n<nombres.size();n++){
    		nameEmpr[n]=nombres.get(n);
    	}
    	
    	return nameEmpr;
    
    } // fin de showNamesCompDB ***********************************
    
    
    
    /* ***************************************************************
     * Este metodo sirve para eliminar en DDBB una empresa determinada 
     *
     * A) Borra la empresa del fichero de empresas
     * B) Borra la keyEmpresa de los usuarios del fichero de usuarios
     * C) A implementar: el borrado de los ficheros de la empresa
     * 
     * Recibe un String con el keyEmpresa de la empresa a borrar
     * Devuelve un boolean como respuesta
     * borrado = TRUE ; no borrado = FALSE
    ****************************************************************** */
  
    public boolean eraseCompany (String keyEmp) {
    	 /*    
        //  este metodo comprueba el key y devuelve true o false
            
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
              
        int rs=0;      	
        // Primero borramos la empresa del fichero de empresas
        try {   
        	rs = st.executeUpdate("DELETE FROM c_empresas WHERE keyempresa='"+keyEmp+"' LIMIT 1");
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
        
        // segundo, borramos las referencias a esa empresa del fichero de usuarios
        // es un proceso en cuatro pasos
        
        // primero borramos la aparicion en ultima posicion de empresa
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET emp3='' WHERE emp3='"+keyEmp+"' LIMIT 3");
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
        // luego borramos la aparicion en segunda posicion de empresa
        // para no dejar huecos, grabamos la tercera posicion en la segunda y borramos la tercera
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET emp2=emp3, emp3='' WHERE emp2='"+keyEmp+"' LIMIT 3");
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
      
        // siguiente, borramos la aparicion en primera posicion de empresa
        // para no dejar huecos, desplazamos la 3ª posicion en la 2ª y la 2ª en la 1ª
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET emp1=emp2, emp2=emp3, emp3='' WHERE emp1='"+keyEmp+"' LIMIT 3");
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
        
        // por ultimo, llega lo dificil: borrar la empselec
        // para ello se le añade la primera empresa como seleccionada
        try {   
        	rs = st.executeUpdate("UPDATE c_usuario SET empselec=emp1 WHERE empselec='"+keyEmp+"' LIMIT 3");
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
        
        
        
        try {
    			con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return false;
    			}
    			
    			*/
        return true;
        
    } // fin de eraseCompany ***********************************
    
    
    
    /* **************************************************************************************
     * Metodo que lee los datos del diario para conformar el mayor solicitado
     * 
     * Recibe como parametros el key de la empresa, la cuenta inicial y la cuenta final
     * a listar, y el intervalo de fecha inicial y final
     * 
     * Devuelve un Array[n][10] con un listado ya confeccionado con cabeceras y sumatorios
     * 
     * PROCEDIMIENTO:
     * A) Con los datos recibidos se componen los datos de la busqueda a realizar
     *    se compone el fichero del diario de la empresa correspondiente, se preparan los
     *    filtros para la busqueda y se hace un SELECT en la DDBB
     * B) Se preparan las cabeceras y los finales que se imprimiran con cada cuenta
     * C) Se leen y componen en el formato adecuado de salida los distintos movimientos
     *    de cada cuenta. Hay que tener muy en cuenta el caso de el cambio de cuenta para
     *    el tema de acumulados y cabeceras 
     * D) Se transforma el ArrayList en un array[n][10] para devolver la informacion
     * 
     **************************************************************************************** */
    
    public String[][] leeMayor(String keyEmp,String cuentaIni,String cuentaFin,String fechaIni,String fechaFin) {   	
    	
    	// creamos el arrayList bidimensional 	
    	ArrayList<ArrayList<String>> datosLeidos=new ArrayList<ArrayList<String>>();
    	// leemos las variables filtro - modificamos la fecha a formato date
    	String fec1=fechaIni.substring(6)+fechaIni.substring(2, 6)+fechaIni.substring(0, 2);
    	String fec2=fechaFin.substring(6)+fechaFin.substring(2, 6)+fechaFin.substring(0, 2);
    	int cta1=(int)Integer.parseInt(cuentaIni);
    	int cta2=(int)Integer.parseInt(cuentaFin);
    	
    	// primero componemos dinamicamente el nombre del fichero diario
    	// del cual leeremos los datos
    	String anno=fec1.substring(2, 4);
    	String nomFichero="c_"+keyEmp+anno+"diario";
    	
    	// para obtener el nombre de las cuentas contables
    	// instanciamos el metodo getNamesCtas
    	// y recibimos un array con los numeros y nombres de cuentas
    	String namesCta[][]=getNamesCtas(keyEmp,cuentaIni,cuentaFin);
    	
    	if (namesCta==null) {
    		System.err.println("NO HAY NOMBRES PARA LEER");
    	}
    	   	
    	// crea una conexion con el fichero de diario empresa
    	File fichero=null;
    	FileReader lector=null;
    	BufferedReader buff=null;
    	
    	ArrayList<ArrayList<String>>asientos=new ArrayList<ArrayList<String>>();
    	
    	// abrimos una conexion con el fichero
    	try {
			fichero=new File(PathDataFiles+nomFichero);

	    	try {
	    		// abrimos lecturas
				lector=new FileReader(fichero);
				buff=new BufferedReader(lector);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error leyendo fichero");
				e1.printStackTrace();
				return null;
			}
	    	
	    	// comenzamos la lectura linea a linea
	    	String data=null;
	    	String datosLectura[]=null;
	    	int c1;
	    	
	    	while ((data=buff.readLine())!=null) {
	    		// convertimos la lectura en array
	    		datosLectura=leeDatos(data);
	    		c1=(int)Integer.parseInt(datosLectura[3]);
	    		// comparamos si los numeros de cuenta y el rango de fechas 
	    		// son los buscados
	    		if ((c1>=cta1)||(c1<=cta2)||(datosLectura[2].equals(fec1))||(datosLectura[2].equals(fec2))) {
	    			// grabamos el asiento en un arrayList
	    			ArrayList<String>asiento=new ArrayList<String>();
	    			for (int i=0;i<datosLectura.length;i++) {
	    				asiento.add(datosLectura[i]);
	    			}
	    			// grabamos el asiento en el arrayList de asientos
	    			asientos.add(asiento);
	    		} 
	    	}
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Fichero no encontrado");
			e1.printStackTrace();
			return null;
			
		} finally {
			
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error cerrando el fichero");
				e.printStackTrace();
				return null;
			}
		}
    	
    	String mayor[][]=new String[asientos.size()][10];
    	for (int k=0;k<asientos.size();k++){
    		mayor[k]=asientos.get(k).toArray(new String[10]);
    	}
    	
    	// creamos un objeto OrdenaAsientos y le pasamos
    	// la matriz que contiene los apuntes contables
    	OrdenaAsientos ordena=new OrdenaAsientos(mayor);
    	// ejecutamos el metodo de reordenacion
    	// nos devuelve un listado ordenado por cuentas y fechas
    	mayor=ordena.reordenaMayor();
    	
    	// crea los distintos string[] para cada cuenta
    	// toda cuenta auxiliar tiene dos lineas de cabecera
    	// n lineas de datos
    	// y finalmente tres lineas de cierre de cuenta
    	String cabecera1[]=new String[10];
    	String cabecera2[]=new String[10];
    	String datos[]=new String[10];
    	String context[]=new String[10];
    	String sumas1[]=new String[10];
    	String sumas2[]=new String[10];
    	
    	// dando formato a datos prefijados de los String
    	cabecera1[0]="Mayor de la cuenta: ";
    	cabecera1[2]="Desde: ";
    	cabecera1[3]=fec1;
    	cabecera1[4]="Hasta: ";
    	cabecera1[5]=fec2;
    	    	
    	cabecera2[0]="";
    	cabecera2[1]="Asiento";
    	cabecera2[2]="Apunte";
    	cabecera2[3]="Fecha";
    	cabecera2[4]="Documento";
    	cabecera2[5]="Concepto";
    	cabecera2[6]="Contrapartida";
    	cabecera2[7]="Debe";
    	cabecera2[8]="Haber";
    	cabecera2[9]="Saldo";
    	
    	context[6]="____________";
    	context[7]="____________";
    	context[8]="____________";
    	sumas1[5]="Sumas........... ";
    	sumas2[6]="Saldo Final..... ";
    	sumas2[7]="................ ";
    	sumas2[8]="................ ";
    	
    	
    	int n=0;	// MOVIMIENTOS LEIDOS
    	int c=0;	// NOMBRE DE CUENTAS LEIDOS
    	
    	
    		String cuentaOld=""; 			// guarda la cuenta leida ultima
    		float sumasdebe=0;				// aqui acumula sumas al debe
    		float sumashaber=0;				// aqui acumula sumas al haber
    		float acumula=0;				// aqui acumula los saldos
    		
    		
    		// graba la primera cabecera y empieza
    		datosLeidos.add(new ArrayList<String>());
    		datosLeidos.get(n).add(cabecera1[0]);
    		datosLeidos.get(n).add(cabecera1[1]);
    		datosLeidos.get(n).add(cabecera1[2]);
    		datosLeidos.get(n).add(cabecera1[3]);
    		datosLeidos.get(n).add(cabecera1[4]);
    		datosLeidos.get(n).add(cabecera1[5]);
    		datosLeidos.get(n).add(cabecera1[6]);
    		datosLeidos.get(n).add(cabecera1[7]);
    		datosLeidos.get(n).add(cabecera1[8]);
    		datosLeidos.get(n).add(cabecera1[9]);
    		
    		n++;
    		datosLeidos.add(new ArrayList<String>());
    		// se le asigna automaticamente el primer nombre
    		// solo fallaria si la cuenta no estuviese creada
    		// cosa que no puede ser porque tiene movimientos
    		cabecera2[0]=namesCta[0][1];
    		datosLeidos.get(n).add(cabecera2[0]);
    		datosLeidos.get(n).add(cabecera2[1]);
    		datosLeidos.get(n).add(cabecera2[2]);
    		datosLeidos.get(n).add(cabecera2[3]);
    		datosLeidos.get(n).add(cabecera2[4]);
    		datosLeidos.get(n).add(cabecera2[5]);
    		datosLeidos.get(n).add(cabecera2[6]);
    		datosLeidos.get(n).add(cabecera2[7]);
    		datosLeidos.get(n).add(cabecera2[8]);
    		datosLeidos.get(n).add(cabecera2[9]);
    		
			for (int k=0;k<mayor.length;k++) {
				
				//******** AQUI SE LEE UN MOVIMIENTO Y ALMACENA EN MATRIZ
				datos[0]=mayor[k][0];
				datos[1]=mayor[k][1];
				datos[2]=mayor[k][2];
				datos[3]=mayor[k][4];
				datos[4]=mayor[k][5];
				datos[5]=mayor[k][6];
				datos[9]=mayor[k][3];
				// si fuera el primer movimiento debe cambiar
				// la variable de control cuentaOld
				if (cuentaOld.isEmpty()) cuentaOld=datos[9];
				
				
				if ((cuentaOld.equals(datos[9]))){ 
					// si el movimiento leido es de la misma cuenta que el anterior movimiento
					// leido, entonces lo integra en la misma cuenta
					
					// acumulacion de saldos
					if (mayor[k][7].equals("1")){
						// es un movimiento al debe
						datos[6]=dosdecimales(mayor[k][8]);
						datos[7]=dosdecimales("0");
						// lo suma al saldo
						sumasdebe+=(float)Float.parseFloat(mayor[k][8]);
						acumula+=(float)Float.parseFloat(mayor[k][8]);
					} else {
						// es un movimiento al haber
						datos[6]=dosdecimales("0");
						datos[7]=dosdecimales(mayor[k][8]);
						// lo resta del saldo
						sumashaber+=(float)Float.parseFloat(mayor[k][8]);
						acumula-=(float)Float.parseFloat(mayor[k][8]);
					}
					datos[8]=dosdecimales(String.valueOf(acumula));
				
				// ******* AQUI SE AÑADE AL ARRAY LIST CADA MOVIMIENTO
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(datos[9]);
					datosLeidos.get(n).add(datos[0]);
					datosLeidos.get(n).add(datos[1]);
					datosLeidos.get(n).add(datos[2]);
					datosLeidos.get(n).add(datos[3]);
					datosLeidos.get(n).add(datos[4]);
	    			datosLeidos.get(n).add(datos[5]);
	    			datosLeidos.get(n).add(datos[6]);
	    			datosLeidos.get(n).add(datos[7]);
	    			datosLeidos.get(n).add(datos[8]);
				} else {
				
					// si el movimiento no coincide con la cuenta del movimiento anterior
					// se cambia el control (cuentaOld), se cierra la cuenta y se abre
					// una cabecera nueva
						
					// cambia el cuentaOld
					cuentaOld=datos[9];
						
					// cuando cambia de cuenta guarda los acumulados
					sumas1[6]=dosdecimales(String.valueOf(sumasdebe));
					sumas1[7]=dosdecimales(String.valueOf(sumashaber));
					sumas1[8]="";
					sumasdebe=0;
					sumashaber=0;
					sumas2[8]=dosdecimales(String.valueOf(acumula));
					acumula=0;
					
					// acumulacion de los nuevos saldos
					if (mayor[k][7].equals("1")){
						// es un movimiento al debe
						datos[6]=dosdecimales(mayor[k][8]);
						datos[7]=dosdecimales("0");
						// lo suma al saldo
						sumasdebe=(float)Float.parseFloat(mayor[k][8]);
						acumula=(float)Float.parseFloat(mayor[k][8]);
					} else {
						// es un movimiento al haber
						datos[6]=dosdecimales("0");
						datos[7]=dosdecimales(mayor[k][8]);
						// lo resta del saldo
						sumashaber=(float)Float.parseFloat(mayor[k][8]);
						acumula=-(float)Float.parseFloat(mayor[k][8]);
					}
					datos[8]=dosdecimales(String.valueOf(acumula));
					
					// añade los acumulados al array
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(context[9]);
		    		datosLeidos.get(n).add(context[0]);
		    		datosLeidos.get(n).add(context[1]);
		    		datosLeidos.get(n).add(context[2]);
		    		datosLeidos.get(n).add(context[3]);
		    		datosLeidos.get(n).add(context[4]);
		    		datosLeidos.get(n).add(context[5]);
		    		datosLeidos.get(n).add(context[6]);
		    		datosLeidos.get(n).add(context[7]);
		    		datosLeidos.get(n).add(context[8]);
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(sumas1[9]);
		    		datosLeidos.get(n).add(sumas1[0]);
		    		datosLeidos.get(n).add(sumas1[1]);
		    		datosLeidos.get(n).add(sumas1[2]);
		    		datosLeidos.get(n).add(sumas1[3]);
		    		datosLeidos.get(n).add(sumas1[4]);
		    		datosLeidos.get(n).add(sumas1[5]);
		    		datosLeidos.get(n).add(sumas1[6]);
		    		datosLeidos.get(n).add(sumas1[7]);
		    		datosLeidos.get(n).add(sumas1[8]);
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(sumas2[9]);
		    		datosLeidos.get(n).add(sumas2[0]);
		    		datosLeidos.get(n).add(sumas2[1]);
		    		datosLeidos.get(n).add(sumas2[2]);
		    		datosLeidos.get(n).add(sumas2[3]);
		    		datosLeidos.get(n).add(sumas2[4]);
		    		datosLeidos.get(n).add(sumas2[5]);
		    		datosLeidos.get(n).add(sumas2[6]);
		    		datosLeidos.get(n).add(sumas2[7]);
		    		datosLeidos.get(n).add(sumas2[8]);
					// monta la siguiente cabecera
		    		n++;
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera1[0]);
		    		datosLeidos.get(n).add(cabecera1[1]);
		    		datosLeidos.get(n).add(cabecera1[2]);
		    		datosLeidos.get(n).add(cabecera1[3]);
		    		datosLeidos.get(n).add(cabecera1[4]);
		    		datosLeidos.get(n).add(cabecera1[5]);
		    		datosLeidos.get(n).add(cabecera1[6]);
		    		datosLeidos.get(n).add(cabecera1[7]);
		    		datosLeidos.get(n).add(cabecera1[8]);
		    		datosLeidos.get(n).add(cabecera1[9]);
		    		n++;
		    		
		    		// si el encontramos el numero de la cuenta en el 
		    		// array de nombres, le ponemos nombre a la cuenta
		    		c=0;
		    		while ( c < namesCta.length) {
			    		if (namesCta[c][0].equals(datos[9])) {
			    			cabecera2[0]=namesCta[c][1];
			    			break;
			    		}
		    			c++;
		    		}

		    		
		    		datosLeidos.add(new ArrayList<String>());
		    		datosLeidos.get(n).add(cabecera2[0]);
		    		datosLeidos.get(n).add(cabecera2[1]);
		    		datosLeidos.get(n).add(cabecera2[2]);
		    		datosLeidos.get(n).add(cabecera2[3]);
		    		datosLeidos.get(n).add(cabecera2[4]);
		    		datosLeidos.get(n).add(cabecera2[5]);
		    		datosLeidos.get(n).add(cabecera2[6]);
		    		datosLeidos.get(n).add(cabecera2[7]);
		    		datosLeidos.get(n).add(cabecera2[8]);
		    		datosLeidos.get(n).add(cabecera2[9]);
					n++;
					datosLeidos.add(new ArrayList<String>());
					datosLeidos.get(n).add(datos[9]);
		    		datosLeidos.get(n).add(datos[0]);
		    		datosLeidos.get(n).add(datos[1]);
		    		datosLeidos.get(n).add(datos[2]);
		    		datosLeidos.get(n).add(datos[3]);
		    		datosLeidos.get(n).add(datos[4]);
		    		datosLeidos.get(n).add(datos[5]);
		    		datosLeidos.get(n).add(datos[6]);
		    		datosLeidos.get(n).add(datos[7]);
		    		datosLeidos.get(n).add(datos[8]);

				}
				
				
			} // fin del FOR
			
			// hay que grabar la ultima cuenta
			// cuando cambia de cuenta guarda los acumulados
			sumas1[6]=dosdecimales(String.valueOf(sumasdebe));
			sumas1[7]=dosdecimales(String.valueOf(sumashaber));
			sumas1[8]="";
			sumasdebe=0;
			sumashaber=0;
			sumas2[8]=dosdecimales(String.valueOf(acumula));
			acumula=0;
			// añade los acumulados al array final
			datosLeidos.add(new ArrayList<String>());
			datosLeidos.get(n).add(context[9]);
    		datosLeidos.get(n).add(context[0]);
    		datosLeidos.get(n).add(context[1]);
    		datosLeidos.get(n).add(context[2]);
    		datosLeidos.get(n).add(context[3]);
    		datosLeidos.get(n).add(context[4]);
    		datosLeidos.get(n).add(context[5]);
    		datosLeidos.get(n).add(context[6]);
    		datosLeidos.get(n).add(context[7]);
    		datosLeidos.get(n).add(context[8]);
			n++;
			datosLeidos.add(new ArrayList<String>());
			datosLeidos.get(n).add(sumas1[9]);
    		datosLeidos.get(n).add(sumas1[0]);
    		datosLeidos.get(n).add(sumas1[1]);
    		datosLeidos.get(n).add(sumas1[2]);
    		datosLeidos.get(n).add(sumas1[3]);
    		datosLeidos.get(n).add(sumas1[4]);
    		datosLeidos.get(n).add(sumas1[5]);
    		datosLeidos.get(n).add(sumas1[6]);
    		datosLeidos.get(n).add(sumas1[7]);
    		datosLeidos.get(n).add(sumas1[8]);
			n++;
			datosLeidos.add(new ArrayList<String>());
			datosLeidos.get(n).add(sumas2[9]);
    		datosLeidos.get(n).add(sumas2[0]);
    		datosLeidos.get(n).add(sumas2[1]);
    		datosLeidos.get(n).add(sumas2[2]);
    		datosLeidos.get(n).add(sumas2[3]);
    		datosLeidos.get(n).add(sumas2[4]);
    		datosLeidos.get(n).add(sumas2[5]);
    		datosLeidos.get(n).add(sumas2[6]);
    		datosLeidos.get(n).add(sumas2[7]);
    		datosLeidos.get(n).add(sumas2[8]);
			 
    	// en el final, se transforma el arrayList en un array[n][10]
    	String lectura[][]=new String[datosLeidos.size()][10];
    	
    	for (int i=0;i<datosLeidos.size();i++) {
    		lectura[i]=datosLeidos.get(i).toArray(new String[10]);
    	}
    	// se devuelve el array con la informacion a listar
    	return lectura;
    	
    } // *************** fin del metodo leeMayor



    /* ************************************************************************
     * Este metodo devuelve un array con los numeros y nombres de las cuentas
     * 
     *  Recibe como parametro el keyEmpr de la empresa a buscar, y los numeros
     *  de cuenta inicial y final para buscar sus nombres
     *  
     *  Devuelve un array [n][2] con los nombres de las cuentas existentes
     ************************************************************************* */
    

    private String[][] getNamesCtas(String keyEmpr,String cta1, String cta2) {
	// TODO Auto-generated method stub
	
    	// primero componemos dinamicamente el nombre del fichero diario
    	// del cual leeremos los datos
    	String buscoFichero="c_"+keyEmpr+"pgc";

    	// pasamos a int los limites de las cuentas a buscar
    	int limit1=(int)Integer.parseInt(cta1);
    	int limit2=(int)Integer.parseInt(cta2);
    	
    	File fichero;
    	FileReader lector;
    	BufferedReader buff;
    	
    	// abrimos el fichero
    	try {
			fichero=new File(PathDataFiles+buscoFichero);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.err.println("Error al abrir el fichero");
			e1.printStackTrace();
			return null;
		}
    	// leemos fichero y almacenamos en buffer
    	try {
			lector=new FileReader(fichero);
			buff=new BufferedReader(lector);
		} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
			System.err.println("Error al leer el fichero");
			e1.printStackTrace();
			return null;
		}
    	
    	//leemos linea por linea para encontrar la informacion buscada
    	String data;
    	String auxLectura[];
       	  	
    	// creamos el arraylist para guardar los valores leidos
    	ArrayList<ArrayList<String>> lista=new ArrayList<ArrayList<String>>(); 
    	int k=0;
    	
    	try {
			while ((data=buff.readLine())!=null) {
				// convertimos cada dato leido en un array
				auxLectura=data.split(";");
				// si la cuenta se encuentra entre las buscadas
				if (((int)Integer.parseInt(auxLectura[0])>=limit1) && 
						((int)Integer.parseInt(auxLectura[0])<=limit2)) {
					// se añade al arrayList
					lista.add(new ArrayList<String>());
					lista.get(k).add(auxLectura[0]);
					lista.get(k).add(auxLectura[1]);
					k++;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.err.println("Error de dato recibido");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error de lectura datos de buffer");
			e.printStackTrace();
			return null;
		} finally {
			try {
				buff.close();
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error de cierre de fichero");
				e.printStackTrace();
			}
		}

	
    	// obtenida la informacion, pasamos el arraylist
    	// a una matriz para return de los datos
    	String names[][]=new String[lista.size()][2];
    	for (int i=0;i<lista.size();i++) {
    		names[i]=lista.get(i).toArray(new String[2]);
    	}
	
    	return names;
	
    } // ******** fin del metodo getnamectas
  
    
    
    /* **********************************************************************
     * Este metodo transforma los numeros en numero formato decimal español
     * con dos decimales (p.ej 3.1 -> 3,10  o  25.317 -> 25,32)
     * 
     * Recibe un parametro String con el numero a transformar
     * Devuelve un String con el numero ya transformado
     * 
     * PROCEDIMIENTO:
     * 
     ************************************************************************* */
    
    private String dosdecimales(String cantidad) {
    	/*
    	int p1=Math.round((float)Float.parseFloat(cantidad)*100);
    	float p2=(float)p1/100;
    	System.err.println("Numeros: "+p1+" * "+p2);
    	*/
    	String nuevoNumero="0";
    	
    	// primero comprobamos si tiene el separador de decimal
    	if (cantidad.contains(".")) {
    		int unaMas=0;
    		// localizamos el punto decimal
    		int puntoDec=cantidad.lastIndexOf(".");
    		// y obtenemos dos cadenas, la parte entera y la parte decimal
    		String cadena1=cantidad.substring(0, puntoDec);
    		String cadena2=cantidad.substring(puntoDec+1);
    		// la parte decimal hay que dejarla en dos decimales
    		// primero el caso de más de tres decimales
    		if (cadena2.length()>2) {
    			// cogemos el 3ºdecimal para redondeo
    			int tercerDec=(int)Integer.parseInt(cadena2.substring(2, 3));
    			int segundoDec=(int)Integer.parseInt(cadena2.substring(1, 2));
    			int primerDec=(int)Integer.parseInt(cadena2.substring(0, 1));
    			// si el tercer decimal es mayor que 5 redondeamos hacia arriba
    			if (tercerDec>=5) segundoDec+=1;
    			// si el segundo decimal es diez (era 9 + 1 de redondeo)
    			// hay que ponerlo a 0 y llevarse uno
    			if (segundoDec==10) {
    				segundoDec=0;
    				primerDec+=1;
    			}
    			// si el primer decimal es diez (era 9 + 1 de redondeo)
    			// hay que ponerlo a 0 y llevarse uno
    			if (primerDec==10) {
    				primerDec=0;
    				unaMas=1;
    			}
    		
    			cadena2=","+String.valueOf(primerDec)+String.valueOf(segundoDec);
    		}
    		
    		// en el caso de que haya solo un decimal
    		if (cadena2.length()==1) {
    			
    			cadena2=","+cadena2+"0";
    			
    		}
    		
    		// en el caso de dos decimales solo hay que poner la coma
    		if (cadena2.length()==2) {
    			
    			cadena2=","+cadena2;
    			
    		}
    		
    		// ahora toca la parte entera
    		// primero comprobamos si nos hemos llevado una por el redondeo
    		if (unaMas==1) {
    			int entera=(int)Integer.parseInt(cadena1);
    			entera+=1;
    			cadena1=String.valueOf(entera);
    		}
    		
    		// componemos el numero uniendo la parte entera y la decimal
    		nuevoNumero=cadena1+cadena2;
    		
    	}  else {
    		
    		// si no tiene separador decimal es tan facil como añadir
    		// la coma decimal y dos ceros
    		
    		nuevoNumero=cantidad+",00";
    		
    	}
    			
    	return nuevoNumero;
    	
    } // fin del metodo dosdecimales
  
    
	public String[][] leeDiario (String keyEmpresa,String nombreCompany,String fechaInicial,String fechaFinal,String lineas) {
		// void
		return null;
	}
    
    
    
    /* ************************************************************************
     *  CLASE INTERNA ORDENAASIENTOS
     *  
     *  Esta clase se diseña para implementar comparator
     *  
     *  El objetivo de esta clase es recibir un String[][] con movimientos
     *  contables y reordenarlos según los distintos metodos
     *  
     *  EL metodo reordenaMayor realiza una ordenacion por cuenta y fecha de la
     *  matriz recibida
     *  
     *  El metodo reordenaAsientos realiza una ordenacion por numero de asiento
     *  y fecha de la matriz recibida
     *  
     ************************************************************************** */
    
    
    
	public class OrdenaAsientos implements Comparator{
		
		private String asientos[][];
		
		
		public OrdenaAsientos(String movimientos[][]) {
			
			this.asientos=movimientos;
			
		} // fin del constructor
		
		
		
		/* *******************************************************************
		 *  Este metodo reordena el mayor por cuenta y fecha
		 *  
		 *  No recibe parametros, utiliza la variable String[][] private
		 *  de clase para operar con ella
		 *  Invoca el metodo compare con 3(cuenta) y 2(fecha)
		 *  Devuelve la matriz ordenada
		 ********************************************************************/
		
		public String[][] reordenaMayor() {
			
			String buff[];
			for (int i=1;i<asientos.length;i++) {
				for (int j=0;j<i;j++){
					if (compare(asientos[j],asientos[j+1],3,2)>0){
						buff=asientos[j];
						asientos[j]=asientos[j+1];
						asientos[j+1]=buff;
					}
				}
			}			
			
			return asientos;
			
		} // fin del metodo reordenaMayor

		

		/* *******************************************************************
		 *  Este metodo reordena el diario por cuenta y fecha
		 *  
		 *  No recibe parametros, utiliza la variable String[][] private
		 *  de clase para operar con ella
		 *  Invoca el metodo compare con 0(asiento) y 2(fecha)
		 *  Devuelve la matriz ordenada
		 ********************************************************************/
		
		public String[][] reordenaAsientos() {
			
			String buff[];
			for (int i=1;i<asientos.length;i++) {
				for (int j=0;j<i;j++){
					if (compare(asientos[j],asientos[j+1],0,2)>0){
						buff=asientos[j];
						asientos[j]=asientos[j+1];
						asientos[j+1]=buff;
					}
				}
			}			
			
			return asientos;
			
		} // fin del metodo reordenaAsientos
		
		
		
		/* ***************************************************************************
		 * El metodo compare va a comparar dos int que vienen envueltos
		 * en un String[].
		 * Si son iguales compara dos Strings
		 * 
		 * Se utiliza para ordenar mayores (por cuenta y fecha)
		 * y diarios (por asiento y fecha)
		 * 
		 * Recibe como parametros los dos String[] a comparar,
		 * int n1 es la posicion del int a comparar y n2 la del String a comparar
		 * 
		 * Devuelve el 0=igual -1=menor 1=mayor y por lo tanto reordena
		 ************************************************************************ **/
		
		public int compare(String o1[], String o2[], int n1, int n2) {
			// compara dos integers y si lo son
			// compara dos strings
			int cta1=(int)Integer.parseInt(o1[n1]);
			int cta2=(int)Integer.parseInt(o2[n1]);
			
			if (cta1==cta2) {
				if (o1[n2].equals(o2[n2])) {
					// si son igual cuenta y fecha
					return 0;
				} else if (0<(o1[n2].compareTo(o2[n2]))) {
					// es fecha posterior, cambiar
					return 1;
				} return -1; // es fecha anterior
			}
			
			if (cta1>cta2) {
				// es una cuenta mayor, cambiar
				return 1;
			} else return -1; //es una cuenta menor
			
		} // fin del metodo compare Strings

		
		
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	} // ******************* fin de la clase interna ordenaAsientos
	
	
	
}   // ********************** fin de class ContaFileDAO
