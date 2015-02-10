package com.diego.chiefmanagement.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/*CLASE AUXILIAR PARA EL ENVIO DE PETICIONES A NUESTRO SISTEMA
 * Y MANEJO DE RESPUESTA.*/
public class Httppostaux {

	InputStream is = null;
	String result = "";
	
	/**
	 * Metodo para obtener un JSONArray mediante una peticion webservice
	 * @param parameters son los paramentros de la peticion si los hay sino solo le pasamos un ArrayList inicializado pero sin datos
	 * @param urlwebserver es la direccion donde tendra que conectarse para obtener los datos de la base de datos
	 * @return un objeto JSONAarry si hay datos o null si no hay datos
	 */
	public JSONArray getserverdataArray(ArrayList<NameValuePair> parameters,
			String urlwebserver) {

		// conecta via http y envia un post.
		httppostconnect(parameters, urlwebserver);

		if (is != null) {// si obtuvo una respuesta

			getpostresponse();

			return getjsonArray();

		} else {

			return null;

		}

	}

	/**
	 * Metodo para obtener un objeto JSONObject mediante una peticion a un webservice
	 * @param parameters son los paramentros de la peticion si los hay sino solo le pasamos un ArrayList inicializado pero sin datos
	 * @param urlwebserver urlwebserver es la direccion donde tendra que conectarse para obtener los datos de la base de datos
	 * @return un objeto JSONObject si hay datos o null sino hay datos
	 */
	public JSONObject getserverdata(ArrayList<NameValuePair> parameters,
			String urlwebserver) {

		// conecta via http y envia un post.
		httppostconnect(parameters, urlwebserver);

		if (is != null) {// si obtuvo una respuesta

			getpostresponse();

			return getjsonObject();

		} else {

			return null;

		}

	}

	/**
	 * Metodo para realizar las peticiones http
	 * @param parameters son los paramentros de la peticion si los hay sino solo le pasamos un ArrayList inicializado pero sin datos
	 * @param urlwebserver urlwebserver es la direccion donde tendra que conectarse para obtener los datos de la base de datos
	 */
	private void httppostconnect(ArrayList<NameValuePair> parametros,
			String urlwebserver) {

		//
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(urlwebserver);
			httppost.setEntity(new UrlEncodedFormEntity(parametros));
			// ejecuto peticion enviando datos por POST
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

	}

	/**
	 * Metodo para convertir la respuesta a la peticion http en String
	 */
	public void getpostresponse() {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();
			Log.e("getpostresponse", " result= " + sb.toString());
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
	}

	/**
	 * Metodo para pasar los datos obtenidos de un String de la peticion http a un objeto JSONObject
	 * @return un objeto JSONObject o null sino se a podido parsear
	 */
	public JSONObject getjsonObject() {
		try {
			JSONObject jObject = new JSONObject(result);

			return jObject;
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
			return null;
		}

	}
	
	/**
	 * Metodo para pasar los datos obtenidos de un String de la peticion http a un objeto JSONOArray
	 * @return un objeto JSONArray o null sino se a podido parsear
	 */
	public JSONArray getjsonArray(){
		try {
			JSONArray jArray = new JSONArray(result);

			return jArray;
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
			return null;
		}

	}

}
