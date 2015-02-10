package com.diego.chiefmanagement.model;

public class Empleado {
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private int telefono;
	private double salario;
	
	/**
	 * Constructo de la clase Empleado
	 * @param dni es el dni del empleado 
	 * @param nombre es el nombre del empleado
	 * @param apellido1 es el primer apellido del empleado 
	 * @param apellido2 es el segundo aepllido del empleado
	 * @param telefono es el telefono del empleado
	 * @param direccion es la direccion del empleado 
	 * @param salario es el salario del empleado
	 */
	public Empleado(String dni, String nombre, String apellido1,
			String apellido2, String direccion, int telefono, double salario) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.telefono = telefono;
		this.salario = salario;
	}
	
	/**
	 * Constructor por defecto de la clase empleado
	 */
	public Empleado() {
	}

	/**
	 * Metodo para obtener el dni del ciente 
	 * @return el dni del cliente 
	 */
	public String getDni() {
		return dni;
	}


	/**
	 * Metodo para fijar o actuazliar el dni del empleado 
	 * @param dni es el dni del empleado a fijar o actualizar 
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Metodo para obtener el nombre del empleado 
	 * @return el nombre del empleado 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para fijar o actualizar el nombre del empleado 
	 * @param nombre es el nombre a actualziar o fijar 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Metodo para obtener el primer apellido del empleado
	 * @return el primer apellido del empleado
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Metodo para fijar o actuazliar el primer apellido del empleado 
	 * @param apellido1 es el apellido a fijar o actualziar 
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * Metodo para obtejer el segundo apellido del empleado 
	 * @return el segundo apellido del empleado 
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Metodo para fijar o acualizar el segundo apellido del empleado
	 * @param apellido2 es el apellido a fijar o actuazizar 
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * Metodo para otener la direccion del empleado
	 * @return la direccion del empleado 
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo para fijar o actualziar la direccion del empleado 
	 * @param direccion es la direccion a fijar o actualizar 
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Metodo para obtener el telefono del empleado
	 * @return el telefono del empleado
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Metodo para fijar o actualizar el telefono del empleado
	 * @param telefono es el telefono a fijar o actualizar 
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo para obtener el salario del empleado 
	 * @return el salario del empleado
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * Metodo para fijar o actualziar el salario del empleado
	 * @param salario es el salario a actualizar o fijar 
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	
}
