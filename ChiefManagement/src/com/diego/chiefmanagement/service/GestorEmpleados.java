package com.diego.chiefmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.diego.chiefmanagement.model.Empleado;

public class GestorEmpleados {
	private List<Empleado> empleados;
	
	/**
	 * Constructor por defecto de la clase GestorEmpleados
	 */
	public GestorEmpleados() {
		empleados = new ArrayList<Empleado>();
	}
	
	/**
	 * Metodo para obtener un Empleado
	 * @param posicion es la poscion del Empleado
	 * @return un objeto Empleado
	 */
	public Empleado getEmpleado(int posicion){
		return empleados.get(posicion);
	}
	
	/**
	 * Metodo para obtener un array de Object con todos los empleados 
	 * @return un array de Object con todos los empleados
	 */
	public Object[] getEmpleados(){
		return empleados.toArray(new Object[empleados.size()]);
	}
	
	/**
	 * Metodo para añadir un emplelado
	 * @param empleado es el empleado a añadir
	 */
	public void addEmple(Empleado empleado){
		empleados.add(empleado);
	}
	
	/**
	 * Metodo para obtener un array de String con todos los nombres de los empleados 
	 * @return un array de String con los nombres de los empleados
	 */
	public String[] nombresEmple(){
		String[] nombres = new String[empleados.size()];
		for(int i=0;i<empleados.size();i++){
			nombres[i] = empleados.get(i).getNombre();
		}
		return nombres;
	}
}
