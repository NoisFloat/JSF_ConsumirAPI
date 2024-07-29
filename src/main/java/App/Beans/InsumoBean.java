package App.Beans;

import App.Models.InsumosMedicos;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class InsumoBean {

    private List<InsumosMedicos> insumosList;
    private InsumosMedicos newInsumo;
    private InsumosMedicos selectedInsumo;

    @PostConstruct
    public void init() {
        insumosList = new ArrayList<>();
        newInsumo = new InsumosMedicos();
        selectedInsumo = new InsumosMedicos();
        fetchAllInsumos();
    }

    public List<InsumosMedicos> getInsumosList() {
        return insumosList;
    }

    public InsumosMedicos getNewInsumo() {
        return newInsumo;
    }

    public void setNewInsumo(InsumosMedicos newInsumo) {
        this.newInsumo = newInsumo;
    }

    public InsumosMedicos getSelectedInsumo() {
        return selectedInsumo;
    }

    public void setSelectedInsumo(InsumosMedicos selectedInsumo) {
        this.selectedInsumo = selectedInsumo;
    }

    public void fetchAllInsumos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/DWF404T_Guevara_Noel_Waldemar_Abner-1.0-SNAPSHOT/api/insumos");

        try {
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == 200) {
                insumosList = response.readEntity(new GenericType<List<InsumosMedicos>>() {});
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos cargados exitosamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al cargar los datos."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e.getMessage()));
        } finally {
            client.close();
        }
    }

    public void createInsumo() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/DWF404T_Guevara_Noel_Waldemar_Abner-1.0-SNAPSHOT/api/insumos");

        try {
            Form form = new Form();
            form.param("nombre", newInsumo.getNombre());
            form.param("descripcion", newInsumo.getDescripcion());
            form.param("precio", String.valueOf(newInsumo.getPrecio()));

            Response response = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));

            if (response.getStatus() == 201) {
                fetchAllInsumos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Insumo creado exitosamente."));
                newInsumo = new InsumosMedicos();  // Reset newInsumo after creation
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al crear el insumo."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e.getMessage()));
        } finally {
            client.close();
        }
    }

    public void updateInsumo() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/DWF404T_Guevara_Noel_Waldemar_Abner-1.0-SNAPSHOT/api/insumos/" + selectedInsumo.getId());

        try {
            Form form = new Form();
            form.param("nombre", selectedInsumo.getNombre());
            form.param("descripcion", selectedInsumo.getDescripcion());
            form.param("precio", String.valueOf(selectedInsumo.getPrecio()));

            Response response = target.request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));

            if (response.getStatus() == 200) {
                fetchAllInsumos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Insumo actualizado exitosamente."));
                selectedInsumo = new InsumosMedicos();  // Reset selectedInsumo after update
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al actualizar el insumo."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e.getMessage()));
        } finally {
            client.close();
        }
    }


    public void deleteInsumo(InsumosMedicos insumo) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/DWF404T_Guevara_Noel_Waldemar_Abner-1.0-SNAPSHOT/api/insumos/" + insumo.getId());

        try {
            Response response = target.request(MediaType.APPLICATION_JSON).delete();

            if (response.getStatus() == 200) {
                insumosList.remove(insumo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Insumo eliminado exitosamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar el insumo."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e.getMessage()));
        } finally {
            client.close();
        }
    }

    public void selectInsumo(InsumosMedicos insumo) {
        this.selectedInsumo = insumo;
    }
}
