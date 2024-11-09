package org.example.modelo.example.modelo;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PracticaDAO implements InterfaceDAO<Articulo> {
    private Connection conexion = null;

    public PracticaDAO() {
        conexion = GestionBD.getConexion();
    }


    @Override
    public int insertar(Articulo articulo) {

        String sql = "Insert into articulos values(?,?,?,?,?,?)";
        return enviarDatos(sql, articulo);
    }

    @Override
    public int actualizar(Articulo articulo) {

        String sql = "UPDATE articulos SET WHERE id=?, nombre=?, precio=?, stock=?, imagen=?, descripcion=?";
        return enviarDatos(sql, articulo);

    }


    @Override
    public int eliminar(Articulo articulo) {
        int filas = 0;
        String sql = "DELETE from articulos where id=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, articulo.getId());
            filas = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filas;
    }

    @Override
    public Articulo buscarPorId(String id) {
        int filas = 0;
        Articulo articulo = new Articulo();
        String sql = "SELECT * from articulos where id=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet registros = ps.executeQuery();

            while (registros.next()) {
                articulo.setId(registros.getString("id"));
                articulo.setNombre(registros.getString("nombre"));
                articulo.setDescripcion(registros.getString("descripcion"));
                articulo.setImagen(registros.getString("imagen"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return articulo;
    }

    @Override
    public List<Articulo> obtenerTodos() {

        String sql = "SELECT * from articulos ";
        return recibirListado(sql);
    }


    @Override
    public List<Articulo> obtenerPorNombre(String nombre) {

        String sql = "SELECT * from articulos where nombre like ?";
        return recibirListado(sql);
    }

    @Override
    public int obtenerConteo() {
        return 0;
    }


    private int enviarDatos(String sql, Articulo articulo) {
        int filas = 0;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, articulo.getId());
            ps.setString(2, articulo.getNombre());
            ps.setFloat(3, articulo.getPrecio());
            ps.setInt(4, articulo.getStock());
            ps.setString(5, articulo.getImagen());
            ps.setString(6, articulo.getDescripcion());

            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filas;
    }

    private List<Articulo> recibirListado(String sql) {
        List<Articulo> articulos = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet registros = ps.executeQuery();
            while (registros.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(registros.getString("id"));
                articulo.setNombre(registros.getString("nombre"));
                articulo.setDescripcion(registros.getString("descripcion"));
                articulo.setImagen(registros.getString("imagen"));
                articulos.add(articulo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articulos;
    }
}
