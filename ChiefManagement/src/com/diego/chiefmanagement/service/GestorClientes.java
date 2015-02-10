package com.diego.chiefmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.diego.chiefmanagement.model.Cliente;

public class GestorClientes {
	private List<Cliente> clientes;
	
	/**
	 * Constructor de la calse GestorClientes
	 */
	public GestorClientes() {
		clientes = new ArrayList<Cliente>();
	}
	
	/**
	 * Metodo para obtener un Cliente 
	 * @param posicion es la poscion del objeto Cliente 
	 * @return un objeto Cliente
	 */
	public Cliente getCliente(int posicion){
		return clientes.get(posicion);
	}
	
	/**
	 * Metodo para obtener todos los cliente en un array de Object
	 * @return un array de Object con los clientes
	 */
	public Object[] getClientes() {
		return clientes.toArray(new Object[clientes.size()]);
	}
	
	/**
	 * Metodo para añadir un  cliente 
	 * @param cliente es el cliente a añadir
	 */
	public void addCliente(Cliente cliente){
		clientes.add(cliente);
	}
	
	/**
	 * Metodo para obtener un array de String con los nombre de los clientes 
	 * @return un array de String con los nombres de los clientes
	 */
	public String[] nombresClie(){
		String[] nombres = new String[clientes.size()];
		for(int i=0;i<clientes.size();i++){
			nombres[i] = clientes.get(i).getNombre();
		}
		return nombres;
	}
}
