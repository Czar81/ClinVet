package Logica;

import Datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Veterinario extends Entidad {

    //Atributos
    private String apellidos;
    private String especialidad;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    //Metodos abstractos heredados de la clase Entidad
    public int contar() {
        int total = 0;
        Conexion con = new Conexion();
        String sql = "select count(*) from veterinario";
        try {
            PreparedStatement pst = con.conectar().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al contar: " + e.getMessage());
        }
        return total;
    }

    @Override
    public void listar(JTable tblVeterinario) {

        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("Nombre");
        table.addColumn("Apellidos");
        table.addColumn("Especialidad");
        table.setRowCount(contar());
        Conexion con = new Conexion();
        String sql = "select * from veterinario";
        int indice = 0;

        try {
            PreparedStatement pst = con.conectar().prepareStatement(sql);
            ResultSet tbl = pst.executeQuery();
            while (tbl.next()) {
                table.setValueAt(tbl.getInt("id"), indice, 0);
                table.setValueAt(tbl.getString("nombre"), indice, 1);
                table.setValueAt(tbl.getString("apellidos"), indice, 2);
                table.setValueAt(tbl.getString("especialidad"), indice, 3);
                indice++;
            }
            tblVeterinario.setModel(table);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
        }

    }

    @Override
    public void agregar() {

        Conexion con = new Conexion();
        String sql = "insert into veterinario(nombre,apellidos,especialidad) values (?,?,?) ";

        try {
            PreparedStatement pst = con.conectar().prepareStatement(sql);
            pst.setString(1, getNombre());
            pst.setString(2, getApellidos());
            pst.setString(3, getEspecialidad());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Veterinario agregado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
        }

    }

    @Override
    public void actualizar() {

        Conexion con = new Conexion();
        String sql = "update veterinario set nombre = ?, apellidos = ?, especialidad = ? where id = ?";

        try {
            PreparedStatement pst = con.conectar().prepareStatement(sql);
            pst.setString(1, getNombre());
            pst.setString(2, getApellidos());
            pst.setString(3, getEspecialidad());
            pst.setInt(4, getId());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Veterinario actualizado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }

    }

    @Override
    public void eliminar() {
        Conexion con = new Conexion();
        String sql = "delete from veterinario where id = ?";

        try {
            PreparedStatement pst = con.conectar().prepareStatement(sql);
            pst.setInt(1, getId());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Veterinario eliminado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar: " + e.getMessage());
        }

    }

}//Final clase Veterinario
