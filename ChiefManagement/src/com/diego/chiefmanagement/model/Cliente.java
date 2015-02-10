package com.diego.chiefmanagement.model;

public class Cliente {
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private int telefono;

	/**
	 * Constructo de la clase Cliente
	 * @param dni es el dni del cliente 
	 * @param nombre es el nombre del cliente 
	 * @param apellido1 es el primer apellido del cliente 
	 * @param apellido2 es el segundo aepllido del cliente 
	 * @param direccion es la direccion del cliente 
	 * @param telefono es el telefono del cliente
	 */
	public Cliente(String dni, String nombre, String apellido1,
			String apellido2, String direccion, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	/**
	 * Constructor por defecto de la clase cliente 
	 */
	public Cliente() {
		
	}

	/**
	 * Metodo para obtener el dni del ciente 
	 * @return el dni del cliente 
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Metodo para fijar o actuazliar el dni del cliente 
	 * @param dni es el dni del cliente a fijar o actualizar 
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Metodo para obtener el nombre del cliente 
	 * @return el nombre del cliente 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para fijar o actualizar el nombre del cliente 
	 * @param nombre es el nombre a actualziar o fijar 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para obtener el primer apellido del cliente 
	 * @return el primer apellido del cliente 
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Metodo para fijar o actuazliar el primer apellido del cliente 
	 * @param apellido1 es el apellido a fijar o actualziar 
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * Metodo para obtejer el segundo apellido del cliente 
	 * @return el segundo apellido del cliente 
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Metodo para fijar o acualizar el segundo apellido del cliente 
	 * @param apellido2 es el apellido a fijar o actuazizar 
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * Metodo para otener la direccion del cliente 
	 * @return la direccion del cliente 
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo para fijar o actualziar la direccion del cliente 
	 * @param direccion es la direccion a fijar o actualizar 
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo para obtener el telefono del cliente 
	 * @return el telefono del cliente 
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Metodo para fijar o actualizar el telefono del cliente 
	 * @param telefono es el telefono a fijar o actualizar 
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
}
