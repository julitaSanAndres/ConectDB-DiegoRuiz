package com.diego.chiefmanagement;



import com.diego.chiefmanagement.model.Cliente;
import com.diego.chiefmanagement.model.Empleado;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Object> {
	private int viewResourceId; // the id  layout of row
	public MyAdapter(Activity context, int textViewResourceId, Object[] c) {
		super(context, textViewResourceId, c);

		this.viewResourceId = textViewResourceId;

	}
	// ************ OPTIMIZER *********************

		/**
		 * This method is executed for each row
		 * 
		 * @param position
		 *            Number of the row 0,1,2
		 * @param converView
		 *            is the View of row
		 * @param parent
		 *            is the group of elements
		 */
		 @Override
		public View getView(int position, View convertView, ViewGroup parent) {
	
			View item = convertView; // el View de una fila, puede haberse creado antes o no
			ViewHolder holder; // recipiente de Views
	
			if (convertView == null) {
				// Creamos un elemento inflater
				LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();
				
				// Creamos un objeto View que sea el tvDniado de inflar el layout de nuestra fila
						
			item = inflater.inflate(viewResourceId, null); //si sale
		//	item = inflater.inflate(viewResourceId, parent); no sale
			
				
				// Creamos un recipiente de Views
				holder = new ViewHolder();
				//Inicializamos los elementos del holder
				holder.tvDni = (TextView) item.findViewById(R.id.tv_dni);
				holder.tvNombre = (TextView) item.findViewById(R.id.tv_nombre);
				item.setTag(holder);
			} else {
	
				holder = (ViewHolder) item.getTag();
			}
			//Asignamos datos a cada elemento del holder
			Resources r = getContext().getResources();
			Cliente cliente = null;
			Empleado empleado = null;
			//Si es una instacia de Cliente nuestro objeto item
			if(this.getItem(position) instanceof Cliente){
				cliente = (Cliente) this.getItem(position);
				if(cliente != null){
					holder.tvDni.setText(r.getString(R.string.tx_dni)+" "+cliente.getDni());
					holder.tvNombre.setText(r.getString(R.string.tx_nombre)+" "+cliente.getNombre());
				}
				//Si es una instacia de Empleado nuestro objeto item	
			}else if(this.getItem(position) instanceof Empleado){
				empleado = (Empleado) this.getItem(position);
				if(empleado != null){
					holder.tvDni.setText(r.getString(R.string.tx_dni)+" "+empleado.getDni());
					holder.tvNombre.setText(r.getString(R.string.tx_nombre)+" "+empleado.getNombre());
				}
			}
			return (item);
		}
	
		/**
		 * Is similar to the Model for Data. 
		 * En este caso la class View Holder contiene el control View de nuestra fila Layout
		 * Recordamos que si otro otros componentes se pondrán aquí
		 * @author Diego
		 * 
		 */
		static class ViewHolder {
			protected TextView tvNombre;
			protected TextView tvDni;
		}
}
