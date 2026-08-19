package org.esfe.vacunacion.modelos;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ViviendaResponsableId implements Serializable {

    private Long idVivienda;
    private Long idResponsable;

    public ViviendaResponsableId() {
    }

    public ViviendaResponsableId(Long idVivienda, Long idResponsable) {
        this.idVivienda = idVivienda;
        this.idResponsable = idResponsable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViviendaResponsableId that = (ViviendaResponsableId) o;
        return Objects.equals(idVivienda, that.idVivienda) &&
                Objects.equals(idResponsable, that.idResponsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVivienda, idResponsable);
    }
}