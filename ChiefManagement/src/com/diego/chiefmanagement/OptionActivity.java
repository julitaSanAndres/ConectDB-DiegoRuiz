package com.diego.chiefmanagement;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends ActionBarActivity {
	private Button btClientes;
	private Button btEmpleados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		btClientes = (Button) findViewById(R.id.bt_clientes);
		btEmpleados = (Button) findViewById(R.id.bt_empleados);
	}
	
	/**
	 * Metodo producido al pulsar btClientes
	 * @param v es el evento producido
	 */
	public void clientes(View v){
		Intent intent = new Intent(OptionActivity.this,ListActivity.class);
		//Paso este string para no tener que hacer 2 ListActiviy *Mas info ListActiviy.java
		intent.putExtra("clientes","clientes");
		startActivity(intent);
	}
	
	/**
	 * Metodo producido al pulsar btEmpleados
	 * @param v es el evento producido
	 */
	public void empleados(View v){
		Intent intent = new Intent(OptionActivity.this,ListActivity.class);
		//Paso este string para no tener que hacer 2 ListActiviy *Mas info ListActiviy.java
		intent.putExtra("empleados","empleados");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option, menu);
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
