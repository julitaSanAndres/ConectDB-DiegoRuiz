<?php
 
class funciones_BD {
 
    private $db;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database

        $this->db = new DB_Connect();
        $this->db->connect();

    }
 
    // destructor
    function __destruct() {
 
    }
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($username, $password) {

    $result = mysql_query("INSERT INTO usuarios(username,passw) VALUES('$username', '$password')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }
 
 
     /**
     * Verificar si el usuario ya existe por el username
     */

    public function isuserexist($username) {

        $result = mysql_query("SELECT username from usuarios WHERE username = '$username'");

        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        if ($num_rows > 0) {

            // el usuario existe 

            return true;
        } else {
            // no existe
            return false;
        }
    }
 
   
	public function login($user,$passw){

	$result=mysql_query("SELECT COUNT(*) FROM administradores WHERE email='$user' AND password='$passw' "); 
	$count = mysql_fetch_row($result);

	/*como el usuario debe ser unico cuenta el numero de ocurrencias con esos datos*/


		if ($count[0]==0){

		return true;

		}else{

		return false;

		}
	}
	
	public function clientes(){
		$query_search = "select * from clientes order by dni";
		$query_exec = mysql_query($query_search) or die(mysql_error());
		$json = array();	
		if(mysql_num_rows($query_exec)){
			while($row=mysql_fetch_assoc($query_exec)){
			$json['clientes'][]=$row;
			}
		}
	echo json_encode($json);
	}
	
	public function empleados(){
		$query_search = "select * from empleados order by dni";
		$query_exec = mysql_query($query_search) or die(mysql_error());
		$json = array();	
		if(mysql_num_rows($query_exec)){
			while($row=mysql_fetch_assoc($query_exec)){
			$json['empleados'][]=$row;
			}
		}
	echo json_encode($json);
	}
  
}
 
?>
