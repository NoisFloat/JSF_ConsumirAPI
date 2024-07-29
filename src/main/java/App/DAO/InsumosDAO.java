package App.DAO;

import App.Models.ConexionToDB;
import App.Models.InsumosMedicos;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsumosDAO extends ConexionToDB {

    public ArrayList<InsumosMedicos> getAllInsumos() throws SQLException {
        conectar();
        ArrayList<InsumosMedicos> insumos = new ArrayList<>();
        String sql = "SELECT * FROM insumos_medicos";
        st = conn.createStatement(); //Creo una consola con el contenido de la consulta SQL
        rs = st.executeQuery(sql); //Guardo
        while (rs.next()) {
            InsumosMedicos insumo = new InsumosMedicos();
            insumo.setId(rs.getInt("PK_insumo"));
            insumo.setNombre(rs.getString("nombre_insumo"));
            insumo.setDescripcion(rs.getString("descripcion"));
            insumo.setPrecio(rs.getDouble("precio"));
            insumos.add(insumo);
        }
        desconectar();
        return insumos;
    }
    public InsumosMedicos getInsumoById(int id) throws SQLException {
        conectar();
        String sql = "SELECT * FROM insumos_medicos WHERE PK_insumo=?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        rs = pst.executeQuery();
        InsumosMedicos insumo = new InsumosMedicos();
        while (rs.next()) {
            insumo.setId(rs.getInt("PK_insumo"));
            insumo.setNombre(rs.getString("nombre_insumo"));
            insumo.setDescripcion(rs.getString("descripcion"));
            insumo.setPrecio(rs.getDouble("precio"));
        }
        desconectar();
        return insumo;
    }

    public String insertInsumo(InsumosMedicos insumo) throws SQLException {
        conectar();

        // Verificar si el insumo ya existe
        String sqlSelect = "SELECT * FROM insumos_medicos WHERE PK_insumo = ?";
        pst = conn.prepareStatement(sqlSelect);
        pst.setInt(1, insumo.getId());
        rs = pst.executeQuery();

        if (rs.next()) {
            return "El insumo ya existe en la base de datos";
        }

        // Insertar el nuevo insumo
        String sqlInsert = "INSERT INTO insumos_medicos (PK_insumo, nombre_insumo, descripcion, precio) VALUES (?, ?, ?, ?)";
        pst = conn.prepareStatement(sqlInsert);
        pst.setInt(1, insumo.getId());
        pst.setString(2, insumo.getNombre());
        pst.setString(3, insumo.getDescripcion());
        pst.setDouble(4, insumo.getPrecio());

        int resultado = pst.executeUpdate();

        if (resultado > 0) {
            return "Insumo insertado exitosamente";
        } else {
            return "Hubo un error en la inserción del insumo";
        }
    }

    public String updateInsumo(InsumosMedicos insumo) throws SQLException {
        conectar();
        String sql ="UPDATE insumos_medicos SET nombre_insumo = ?, descripcion = ?, precio = ? WHERE PK_insumo = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, insumo.getNombre());
        pst.setString(2, insumo.getDescripcion());
        pst.setDouble(3, insumo.getPrecio());
        pst.setInt(4, insumo.getId());
        int Resultado = pst.executeUpdate();
        desconectar();

        if (Resultado > 0) {
            return "Insumo actualizado exitosamente";
        }

        return "Hubo un error en la actualizacion del Insumo";
    }

    public String deleteInsumo(int id) throws SQLException {
        conectar();
        String sql = "DELETE FROM insumos_medicos WHERE PK_insumo = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        int Resultado = pst.executeUpdate();
        desconectar();

        if (Resultado > 0) {
            return "Insumo eliminado exitosamente";
        }

        return "Hubo un error en la actualizacion del Insumo";
    }
}
