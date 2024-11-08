package org.example.modelo.example.modelo;

import java.util.List;

public interface InterfaceDAO<T> {

    int insertar(T object);
    int actualizar(T object);
    int eliminar(T object);
    T buscarPorId(String id);
    List<T> obtenerTodos();
    List<T> obtenerPorNombre(String nombre);
    int obtenerConteo();
}
