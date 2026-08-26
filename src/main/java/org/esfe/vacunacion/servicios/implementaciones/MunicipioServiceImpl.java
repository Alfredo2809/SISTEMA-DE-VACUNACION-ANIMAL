package org.esfe.vacunacion.servicios.implementaciones;
import org.esfe.vacunacion.modelos.Municipio;
import org.esfe.vacunacion.repositorios.MunicipioRepository;
import org.esfe.vacunacion.servicios.interfaces.IMunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipioServiceImpl implements IMunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public Municipio guardar(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    @Override
    public List<Municipio> obtenerTodos() {
        return municipioRepository.findAll();
    }

    @Override
    public Optional<Municipio> obtenerPorId(Long id) {
        return municipioRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        municipioRepository.deleteById(id);
    }
}
