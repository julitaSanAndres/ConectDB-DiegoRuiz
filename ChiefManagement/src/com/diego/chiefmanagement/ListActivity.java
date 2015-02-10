package com.diego.chiefmanagement;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.diego.chiefmanagement.model.Cliente;
import com.diego.chiefmanagement.model.Empleado;
import com.diego.chiefmanagement.service.GestorClientes;
import com.diego.chiefmanagement.service.GestorEmpleados;
import com.diego.chiefmanagement.service.Httppostaux;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends ActionBarActivity {
	//Url para el scrip de la lista de clientes
	private final String URL_CONECT_CLIE = "http://" +LoginActivity.IP_SERVER+ "/ChiefManagement/showcustomers.php";
	//Url para la scrip lista de empleados
	private final String URL_CONECT_EMPLE = "http://" +LoginActivity.IP_SERVER+ "/ChiefManagement/showemployers.php";
	private EditText edBus;
	private ListView listClient;
	private Httppostaux post;
	private GestorClientes gestorClie;
	private GestorEmpleados gestorEmple;
	private ProgressDialog pDialog;
	private ArrayList<Empleado> array_sort_emple;
	private ArrayList<Cliente> array_sort_clie;
	private String[] values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		//Inicializo los atributos
		edBus = (EditText) findViewById(R.id.ed_bus_clie);
		listClient = (ListView) findViewById(R.id.list_clie);
		post = new Httppostaux();
		//Cojo los extras enviados al lanzar el intent
		Bundle bundle = getIntent().getExtras();
		//Si he pulsado el boton clientes
		if(bundle.containsKey("clientes")){
			gestorClie = new GestorClientes();
			array_sort_clie = new ArrayList<Cliente>();
			//Lanzamos la clase de apoyo para obtener datos de la base de datos mediante webservice
			new ShowClientes().execute();
			//Evento que cambia el texo del EditText
			edBus.addTextChangedListener(new TextWatcher() {
				/**
				 * Cada vez que cambia el texto del EditText, se lanza este evento
				 */
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
					// TODO Auto-generated method stub
					int textlength = edBus.getText().length();
					// limpiamos el array
					array_sort_clie.clear();
					// Buscamos las coincidencias de la cadena de búsqueda con las
					// del adaptador
					for (int i = 0; i < values.length; i++) {
						if (textlength <= values[i].length()) {
							if (edBus
									.getText()
									.toString()
									.equalsIgnoreCase(
											(String) values[i].subSequence(0,
													textlength))) {
								array_sort_clie.add(gestorClie.getCliente(i));
							}
						}
					}

					// Actualizamos el adapteer
					MyAdapter adapter = new MyAdapter(ListActivity.this,
							R.layout.adapter_list_clie, array_sort_clie
									.toArray(new Object[array_sort_clie.size()]));
					listClient.setAdapter(adapter);
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub

				}

			});
		//Si he pulsado el boton empleados	
		}else if(bundle.containsKey("empleados")){
			gestorEmple = new GestorEmpleados();
			array_sort_emple = new ArrayList<Empleado>();
			values = gestorEmple.nombresEmple();
			//Lanzamos la clase de apoyo para obtener datos de la base de datos mediante webservice
			new ShowEmpleados().execute();
			edBus.addTextChangedListener(new TextWatcher() {
				/**
				 * Cada vez que cambia el texto del EditText, se lanza este evento
				 */
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
					// TODO Auto-generated method stub
					int textlength = edBus.getText().length();
					// limpiamos el array
					array_sort_emple.clear();
					// Buscamos las coincidencias de la cadena de búsqueda con las
					// del adaptador
					for (int i = 0; i < values.length; i++) {
						if (textlength <= values[i].length()) {
							if (edBus
									.getText()
									.toString()
									.equalsIgnoreCase(
											(String) values[i].subSequence(0,
													textlength))) {
								array_sort_emple.add(gestorEmple.getEmpleado(i));
							}
						}
					}

					// Actualizamos el adapteer
					MyAdapter adapter = new MyAdapter(ListActivity.this,
							R.layout.adapter_list_clie, array_sort_emple
									.toArray(new Object[array_sort_emple.size()]));
					listClient.setAdapter(adapter);
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub

				}

			});
		}
		
	}
	/**
	 * Metodo para otenter los datos de los clientes del webservice
	 * @return true si se han podido obtener los datos o false sino se han podido obtener los datos
	 */
	public boolean showClientes(){
		
//		  Creamos un ArrayList del tipo nombre valor para agregar los datos
//		  pero en este caso no hay solo lo inicializamos  y lo enviamos mediante POST a
//		  nuestro sistema para relizar la validacion
		
		ArrayList<NameValuePair> postparameterssend = new ArrayList<NameValuePair>();
		// realizamos una peticion y como respuesta obtenes un objeto JSON
		JSONObject jobject = post.getserverdata(postparameterssend, URL_CONECT_CLIE);
		//Para pasar el JSONObject a un array con los datos de la base de datos
		JSONArray jdata = jobject.optJSONArray("clientes");

		
//		  como estamos trabajando de manera local el ida y vuelta sera casi
//		  inmediato para darle un poco realismo decimos que el proceso se pare
//		  por unos segundos para poder observar el progressdialog la podemos
//		  eliminar si queremos
		 
		SystemClock.sleep(950);

		// si lo que obtuvimos no es null
		if (jdata != null && jdata.length() > 0) {
			try {
				for (int i = 0; i < jdata.length(); i++) {
					Cliente cliente = new Cliente();
					//Cojemos el elemento del array de JSON
	                JSONObject jsonArrayChild = jdata.getJSONObject(i);
	                //Cojemos los datos de objeto JSONObject mediante el get y el nombre de la columna de la base de datos
		            cliente.setDni(jsonArrayChild.getString("dni"));
		            cliente.setNombre(jsonArrayChild.getString("nombre"));
		            cliente.setApellido1(jsonArrayChild.getString("primerApellido"));
		            cliente.setApellido2(jsonArrayChild.getString("segundoApellido"));
		            cliente.setTelefono(Integer.parseInt(jsonArrayChild.getString("telefono")));
		            cliente.setDireccion(jsonArrayChild.getString("direccion"));
		            //Añadimos a nuestro gestor un cliente con los datos de la base de datos
		            gestorClie.addCliente(cliente);
	            }
				return true;
				
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
		} else { // json obtenido invalido verificar parte WEB.
			Log.e("JSON  ", "ERROR");
			return false;
		}
	}
	
	/*
	 * CLASE ShowClientes
	 * 
	 * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos
	 * y obtenemos los datos podria hacerse lo mismo sin usar esto pero si el
	 * tiempo de respuesta es demasiado lo que podria ocurrir si la conexion es
	 * lenta o el servidor tarda en responder la aplicacion sera inestable.
	 * ademas observariamos el mensaje de que la app no responde.
	 */
	class ShowClientes extends AsyncTask<String, String, String> {

		/**
		 * Metodo que se ejecuta antes de la acción principal 
		 */
		protected void onPreExecute() {
			// para el progress dialog
			 pDialog = new ProgressDialog(ListActivity.this);
			 pDialog.setMessage("Autenticando....");
			 pDialog.setIndeterminate(false);
			 pDialog.setCancelable(false);
			 pDialog.show();
		}

		/**
		 * Acción principal 
		 */
		protected String doInBackground(String... params) {
			// enviamos y recibimos y analizamos los datos en segundo plano.
			if (showClientes() == true) {
				return "ok"; // valido
			} else {
				return "err"; // invalido
			}

		}

		/**
		 * Metodo que se ejecuta despues de la acción principal
		 */
		protected void onPostExecute(String result) {

			 pDialog.dismiss();//ocultamos progess dialog.
			 Log.e("onPostExecute=",""+result);
			
			 if (result.equals("ok")){
				 //Cojemos los nombres de los clientes
				 values = gestorClie.nombresClie();
				 //Generamos y fijamos el adapter para nuestro ListView
				 MyAdapter adapter = new MyAdapter(ListActivity.this, R.layout.adapter_list_clie, gestorClie.getClientes());
				 listClient.setAdapter(adapter);
			 }else{	
				//Mostramos mensage de error 
				 Log.e("onPostExecute=",""+result);
			 }

		}

	}
	
	/**
	 * Metodo para otenter los datos de los empleados del webservice
	 * @return true si se han podido obtener los datos o false sino se han podido obtener los datos
	 */
	public boolean showEmpleados(){
//		  Creamos un ArrayList del tipo nombre valor para agregar los datos
//		  pero en este caso no hay solo lo inicializamos  y lo enviamos mediante POST a
//		  nuestro sistema para relizar la validacion
		ArrayList<NameValuePair> postparameterssend = new ArrayList<NameValuePair>();
		// realizamos una peticion y como respuesta obtenes un objeto JSON
		JSONObject jobject = post.getserverdata(postparameterssend, URL_CONECT_EMPLE);
		//Para pasar el JSONObject a un array con los datos de la base de datos
		JSONArray jdata = jobject.optJSONArray("empleados");

		
//		  como estamos trabajando de manera local el ida y vuelta sera casi
//		  inmediato para darle un poco realismo decimos que el proceso se pare
//		  por unos segundos para poder observar el progressdialog la podemos
//		  eliminar si queremos
		
		SystemClock.sleep(950);

		// si lo que obtuvimos no es null
		if (jdata != null && jdata.length() > 0) {
			try {
				for (int i = 0; i < jdata.length(); i++) {
					Empleado empleado = new Empleado();
					//Cojemos el elemento del array de JSON
	                JSONObject jsonArrayChild = jdata.getJSONObject(i);
	              //Cojemos los datos de objeto JSONObject mediante el get y el nombre de la columna de la base de datos
	                empleado.setDni(jsonArrayChild.getString("dni"));
	                empleado.setNombre(jsonArrayChild.getString("nombre"));
	                empleado.setApellido1(jsonArrayChild.getString("primerApellido"));
	                empleado.setApellido2(jsonArrayChild.getString("segundoApellido"));
	                empleado.setTelefono(Integer.parseInt(jsonArrayChild.getString("telefono")));
	                empleado.setDireccion(jsonArrayChild.getString("direccion"));
	                empleado.setSalario(Double.parseDouble(jsonArrayChild.getString("salario")));
	              //Añadimos a nuestro gestor un empleado con los datos de la base de datos
		            gestorEmple.addEmple(empleado);
	            }
				return true;
				
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
		} else { // json obtenido invalido verificar parte WEB.
			Log.e("JSON  ", "ERROR");
			return false;
		}
	}
	
	
	/*
	 * CLASE ShowEmpleados
	 * 
	 * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos
	 * y obtenemos los datos podria hacerse lo mismo sin usar esto pero si el
	 * tiempo de respuesta es demasiado lo que podria ocurrir si la conexion es
	 * lenta o el servidor tarda en responder la aplicacion sera inestable.
	 * ademas observariamos el mensaje de que la app no responde.
	 */
	class ShowEmpleados extends AsyncTask<String, String, String> {
		
		/**
		 * Metodo que se ejecuta antes de la acción principal 
		 */
		protected void onPreExecute() {
			// para el progress dialog
			 pDialog = new ProgressDialog(ListActivity.this);
			 pDialog.setMessage("Autenticando....");
			 pDialog.setIndeterminate(false);
			 pDialog.setCancelable(false);
			 pDialog.show();
		}

		/**
		 * Acción principal 
		 */
		protected String doInBackground(String... params) {
			// enviamos y recibimos y analizamos los datos en segundo plano.
			if (showEmpleados() == true) {
				return "ok"; //  valido
			} else {
				return "err"; // invalido
			}

		}

		/**
		 * Metodo que se ejecuta despues de la acción principal
		 */
		protected void onPostExecute(String result) {

			 pDialog.dismiss();//ocultamos progess dialog.
			 Log.e("onPostExecute=",""+result);
			
			 if (result.equals("ok")){
				 //Obtenemos los nomrbes de los empleados
				 values = gestorEmple.nombresEmple();
				 //Generamos y fijamos el adapter para nuestro ListView
				 MyAdapter adapter = new MyAdapter(ListActivity.this, R.layout.adapter_list_clie, gestorEmple.getEmpleados());
				 listClient.setAdapter(adapter);
			 }else{	
				//Mostramos mensage de error 
				 Log.e("onPostExecute=",""+result);
			 }

		}

	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client, menu);
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
}
