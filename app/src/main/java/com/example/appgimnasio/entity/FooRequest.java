package com.example.appgimnasio.entity;

public class FooRequest {
    final String nombre;
    final String fechaNacimiento;
    final String tipo;
    final String fechaCompra;
    final String genero;
    final String raza;
    final String precioCompra;
    public FooRequest(String nombre,
                      String fechaNacimiento,
                      String tipo, String fechaCompra, String genero, String raza, String precioCompra) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.fechaCompra = fechaCompra;
        this.genero = genero;
        this.raza = raza;
        this.precioCompra = precioCompra;
    }
}
