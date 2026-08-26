package org.esfe.vacunacion.servicios.implementaciones;
import org.esfe.vacunacion.modelos.Municipio;
import org.esfe.vacunacion.repositorios.MunicipioRepository;
import org.esfe.vacunacion.repositorios.DepartamentoRepository;
import org.esfe.vacunacion.servicios.interfaces.IMunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipioServiceImpl implements IMunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public Municipio guardar(Municipio municipio) {
        if (existeDepartamento(municipio.getDepartamento().getIdDepartamento())) {
            return municipioRepository.save(municipio);
        }
        throw new RuntimeException("El departamento no existe");
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

    @Override
    public boolean existeDepartamento(Long idDepartamento) {
        return departamentoRepository.existsById(idDepartamento);
    }
}