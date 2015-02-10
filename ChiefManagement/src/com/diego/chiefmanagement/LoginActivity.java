package com.diego.chiefmanagement;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.diego.chiefmanagement.service.Httppostaux;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private EditText edEmail;
	private EditText edPass;
	private Button btStart;
	//private final String IP_SERVER = "192.168.1.100:8080";//ip local de tu equipo y el puerto de apertura del servidor apache
	public static final String IP_SERVER = "10.0.2.2:8080";//ip local del sdk de android y el puerto de apertura del servidor apache
	private final String URL_CONECT = "http://" + IP_SERVER+ "/ChiefManagement/acces.php";//direccion donde esta el scrip php
	private Httppostaux post;
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// Inicializo los atributos
		edEmail = (EditText) findViewById(R.id.ed_email);
		edPass = (EditText) findViewById(R.id.ed_password);
		btStart = (Button) findViewById(R.id.bt_iniciar);
		post = new Httppostaux();
	}

	/**
	 * Evento producido al pulsar el boton login
	 * @param v es el evento producido
	 */
	public void login(View v) {
		// Extreamos datos de los EditText
		String usuario = edEmail.getText().toString();
		String passw = edPass.getText().toString();

		// verificamos si estan en blanco
		if (checklogindata(usuario, passw) == true) {

			// si pasamos esa validacion ejecutamos el asynctask pasando el
			// usuario y clave como parametros

			new Login().execute(usuario, passw);

		} else {
			Toast.makeText(LoginActivity.this, getResources().getString(R.string.tx_error_vacio), Toast.LENGTH_LONG).show();
		}
	}

	/*
	 * Valida el estado del logueo solamente necesita como parametros el usuario
	 * y password
	 */
	public boolean loginstatus(String email, String password) {
		int logstatus = -1;

		
//		  Creamos un ArrayList del tipo nombre valor para agregar los datos
//		  recibidos por los parametros anteriores y enviarlo mediante POST a
//		  nuestro sistema para relizar la validacion
		 
		ArrayList<NameValuePair> postparameterssend = new ArrayList<NameValuePair>();

		postparameterssend.add(new BasicNameValuePair("usuario", email));
		postparameterssend.add(new BasicNameValuePair("password", password));

		// realizamos una peticion y como respuesta obtenes un array JSON
		JSONArray jdata = post.getserverdataArray(postparameterssend, URL_CONECT);

		
//		  como estamos trabajando de manera local el ida y vuelta sera casi
//		  inmediato para darle un poco realismo decimos que el proceso se pare
//		  por unos segundos para poder observar el progressdialog la podemos
//		  eliminar si queremos
	 
		SystemClock.sleep(950);

		// si lo que obtuvimos no es null
		if (jdata != null && jdata.length() > 0) {

			JSONObject json_data; // creamos un objeto JSON
			try {
				json_data = jdata.getJSONObject(0); // leemos el primer segmento
													// en nuestro caso el unico
				logstatus = json_data.getInt("logstatus");// accedemos al valor
				Log.e("loginstatus", "logstatus= " + logstatus);// muestro por
																// log que
																// obtuvimos
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// validamos el valor obtenido
			if (logstatus == 0) {// [{"logstatus":"0"}]
				Log.e("loginstatus ", "invalido");
				return false;
			} else {// [{"logstatus":"1"}]
				Log.e("loginstatus ", "valido");
				return true;
			}

		} else { // json obtenido invalido verificar parte WEB.
			Log.e("JSON  ", "ERROR");
			return false;
		}

	}

	/**
	 * Para comprobar que los campos no estan en blanco
	 * @param email es el email del administrador
	 * @param password es la contraseña del administrador
	 * @return true si son validos o false si estan vacios 
	 */
	private boolean checklogindata(String email, String password) {
		boolean check=true;
		if (email.equals("") || password.equals("")) {
			Log.e("Login ui", "checklogindata email or pass error");
			return false;

		} 
		return check;

	}
	
	/**
	 * Redefinimos el metodo onStop para que cuando nos logueemos no podamos volver a hacerlo
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * CLASE Login
	 * 
	 * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos
	 * y obtenemos los datos podria hacerse lo mismo sin usar esto pero si el
	 * tiempo de respuesta es demasiado lo que podria ocurrir si la conexion es
	 * lenta o el servidor tarda en responder la aplicacion sera inestable.
	 * ademas observariamos el mensaje de que la app no responde.
	 */
	class Login extends AsyncTask<String, String, String> {

		private String email;
		private String pass;
		

		/**
		 * Metodo que se ejecuta antes de la acción principal 
		 */
		protected void onPreExecute() {
			// para el progress dialog
			 pDialog = new ProgressDialog(LoginActivity.this);
			 pDialog.setMessage("Autenticando....");
			 pDialog.setIndeterminate(false);
			 pDialog.setCancelable(false);
			 pDialog.show();
		}

		/**
		 * Acción principal 
		 */
		protected String doInBackground(String... params) {
			// obtnemos email y pass
			email = params[0];
			pass = params[1];

			// enviamos y recibimos y analizamos los datos en segundo plano.
			if (loginstatus(email, pass) == true) {
				return "ok"; // login valido
			} else {
				return "err"; // login invalido
			}

		}

		/**
		 * Metodo que se ejecuta despues de la acción principal
		 */
		protected void onPostExecute(String result) {

			 pDialog.dismiss();//ocultamos progess dialog.
			 Log.e("onPostExecute=",""+result);
			
			 if (result.equals("ok")){
			//Lanzamos otra activity donde tendremos las opciones de elegir clientes y empleados
			 Intent intent=new Intent(LoginActivity.this,OptionActivity.class);
			 startActivity(intent);
			
			 }else{	
				//Mostramos mensage de error 
				Toast.makeText(LoginActivity.this, getResources().getString(R.string.tx_toast_error), Toast.LENGTH_LONG).show();
			 }

		}

	}
}
