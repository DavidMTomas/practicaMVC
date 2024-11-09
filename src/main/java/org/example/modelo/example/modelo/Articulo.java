package org.example.modelo.example.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {
    private String id;
    private String nombre;
    private Float precio;
    private int stock;
    private String imagen;
    private String descripcion;


//public Articulo() {
//}
//
//
//public Articulo(String id, String nombre, Float precio, int stock, String imagen, String descripcion) {
//    this.id = id;
//    this.nombre = nombre;
//    this.precio = precio;
//    this.stock = stock;
//    this.imagen = imagen;
//    this.descripcion = descripcion;
//}
//
//public String getId() {
//    return id;
//}
//
//public void setId(String id) {
//    this.id = id;
//}
//
//public String getNombre() {
//    return nombre;
//}
//
//public void setNombre(String nombre) {
//    this.nombre = nombre;
//}
//
//public Float getPrecio() {
//    return precio;
//}
//
//public void setPrecio(Float precio) {
//    this.precio = precio;
//}

//public int getStock() {
//    return stock;
//}
//
//public void setStock(int stock) {
//    this.stock = stock;
//}
//
//public String getImagen() {
//    return imagen;
//}
//
//public void setImagen(String imagen) {
//    this.imagen = imagen;
//}
//
//public String getDescripcion() {
//    return descripcion;
//}
//
//public void setDescripcion(String descripcion) {
//    this.descripcion = descripcion;
//}
//
}
