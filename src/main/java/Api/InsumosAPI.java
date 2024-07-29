package Api;

import App.DAO.InsumosDAO;
import App.Models.InsumosMedicos;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

@Path("insumos")
public class InsumosAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumos() throws SQLException {
        InsumosDAO fuenteDeDatos = new InsumosDAO();

        ArrayList<InsumosMedicos> listaInsumosMedicos= fuenteDeDatos.getAllInsumos();
        if(listaInsumosMedicos.isEmpty()){
            return Response.status(404).entity("No hay Insumos Medicos en el Sistema").build();

        }
        return Response.ok(listaInsumosMedicos).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumo(@PathParam("id") int id) throws SQLException {
        InsumosDAO fuenteDeDatos = new InsumosDAO();
        InsumosMedicos insumo = fuenteDeDatos.getInsumoById(id);
        if(insumo.getNombre() == null){
            return Response.status(404).entity("Insumo no Encontrado").build();
        }
        return Response.ok(insumo).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertConcepto(@FormParam("nombre") String name,
                                   @FormParam("descripcion") String descripcion,
                                   @FormParam("precio") double precio) throws SQLException {

        // Validaciones
        name = name.trim();
        descripcion = descripcion.trim();

        if (precio <= 0.0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El precio debe ser mayor a 0.0")
                    .build();
        }

        if (name.length() <= 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El nombre debe tener más de 2 caracteres")
                    .build();
        }

        if (descripcion.length() <= 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La descripción debe tener más de 2 caracteres")
                    .build();
        }

        // Asignaciones
        InsumosDAO fuenteDeDatos = new InsumosDAO();
        InsumosMedicos insumo = new InsumosMedicos();
        insumo.setNombre(name);
        insumo.setDescripcion(descripcion);
        insumo.setPrecio(precio);

        //Uso del DAO
        String resultado = fuenteDeDatos.insertInsumo(insumo);
        if(Objects.equals(resultado, "Insumo insertado exitosamente")){
            // Suponiendo que la inserción fue exitosa

            return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*").entity("Recurso creado").build();
        }

        // Suponiendo que la inserción fue exitosa
        return Response.status(Response.Status.BAD_REQUEST)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Hubo un error en insertar el Insumo")
                .build();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) //Esto es para recibir los datos de esta manera = application/x-www-form-urlencoded

    public Response updateConcepto(@PathParam("id") int id,
                                   @FormParam("nombre") String nombre,
                                   @FormParam("descripcion") String descripcion,
                                   @FormParam("precio") Double precio) throws SQLException {

        // Verificaciones nulas
        if (nombre == null || descripcion == null || precio == 0) {
            System.out.println("-------------------ERROR DE NULOS RECIBIDOS -----------------------");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Todos los campos (nombre, descripcion, precio) son obligatorios")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        // Validaciones
        nombre = nombre.trim();
        descripcion = descripcion.trim();

        if (precio <= 0.0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El precio debe ser mayor a 0.0")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        if (nombre.length() <= 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El nombre debe tener más de 2 caracteres")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        if (descripcion.length() <= 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La descripción debe tener más de 2 caracteres")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }

        // Asignaciones
        InsumosDAO fuenteDeDatos = new InsumosDAO();
        InsumosMedicos insumo = new InsumosMedicos();
        insumo.setId(id);
        insumo.setNombre(nombre);
        insumo.setDescripcion(descripcion);
        insumo.setPrecio(precio);

        // Uso del DAO para la actualización
        String resultado = fuenteDeDatos.updateInsumo(insumo);
        if (Objects.equals(resultado, "Insumo actualizado exitosamente")) {
            return Response.status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Recurso actualizado")
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Hubo un error en actualizar el Insumo")
                .build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteConcepto(@PathParam("id") int id) throws SQLException {
        InsumosDAO fuenteDeDatos = new InsumosDAO();

        // Uso del DAO para la eliminación
        String resultado = fuenteDeDatos.deleteInsumo(id);
        if (Objects.equals(resultado, "Insumo eliminado exitosamente")) {
            return Response.status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Recurso eliminado")
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Hubo un error en eliminar el Insumo")
                .build();
    }


}
