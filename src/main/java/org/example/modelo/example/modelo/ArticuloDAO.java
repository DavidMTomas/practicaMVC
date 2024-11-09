package org.example.modelo.example.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO implements InterfaceDAO<Articulo> {
    private Connection conexion = null;

    public ArticuloDAO() {
        conexion = GestionBD.getConexion();
    }


    @Override
    public int insertar(Articulo object) {
        //

        int filas = 0;
        String sql = "Insert into articulos values(?,?,?,?,?,?)";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

            pst.setString(1, object.getId());
            pst.setString(2, object.getNombre());
            pst.setFloat(3, object.getPrecio());
            pst.setInt(4, object.getStock());
            pst.setString(5, object.getImagen());
            pst.setString(6, object.getDescripcion());

            filas = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error articulos insertar: " + e.getMessage());
        }

        return filas;
    }

    @Override
    public int actualizar(Articulo object) {
        int filas = 0;
        String sql2 = "Update articulos values(?,?,?,?,?,?)";
        String sql = "Update articulos SET nombre=?, " +
                "precio=?, stock=?, imagen=?, descripcion=? " +
                "WHERE id=?";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

            pst.setString(1, object.getNombre());
            pst.setFloat(2, object.getPrecio());
            pst.setInt(3, object.getStock());
            pst.setString(4, object.getImagen());
            pst.setString(5, object.getDescripcion());
            pst.setString(6, object.getId());

            filas = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filas;
    }

    @Override
    public int eliminar(Articulo object) {
        int filas = 0;
        String sql = "Delete from articulos where id=?";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, object.getId());

            filas = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filas;
    }

    @Override
    public Articulo buscarPorId(String id) {
        String sql = "select * from articulos where id=?";
        Articulo articulo = null;
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                articulo = new Articulo();
                articulo.setId(rs.getString("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setPrecio(rs.getFloat("precio"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setImagen(rs.getString("imagen"));
                articulo.setDescripcion(rs.getString("descripcion"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articulo;

    }

    @Override
    public List obtenerTodos() {
        List<Articulo> articulos = new ArrayList<Articulo>();
        String sql = "select * from articulos";

        try (PreparedStatement pst = conexion.prepareCall(sql)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getString("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setPrecio(rs.getFloat("precio"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setImagen(rs.getString("imagen"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return articulos;
    }

    @Override
    public List obtenerPorNombre(String nombre) {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "select * from articulos where nombre like ?";


        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

            pst.setString(1, "%" + nombre + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getString("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setPrecio(rs.getFloat("precio"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setImagen(rs.getString("imagen"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articulos;
    }

    @Override
    public int obtenerConteo() {
        int filas = 0;
        String sql = "select count(*) from articulos";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.executeQuery();
            ResultSet rs = pst.getResultSet();
            while (rs.next()) {
                filas = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filas;
    }
}
