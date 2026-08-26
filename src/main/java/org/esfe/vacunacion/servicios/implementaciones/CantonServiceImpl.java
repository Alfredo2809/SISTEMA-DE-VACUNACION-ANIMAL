package org.esfe.vacunacion.servicios.implementaciones;
import org.esfe.vacunacion.modelos.Canton;
import org.esfe.vacunacion.repositorios.CantonRepository;
import org.esfe.vacunacion.repositorios.MunicipioRepository;
import org.esfe.vacunacion.servicios.interfaces.ICantonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CantonServiceImpl implements ICantonService {

    @Autowired
    private CantonRepository cantonRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public Canton guardar(Canton canton) {
        if (existeMunicipio(canton.getMunicipio().getIdMunicipio())) {
            return cantonRepository.save(canton);
        }
        throw new RuntimeException("El municipio no existe");
    }

    @Override
    public List<Canton> obtenerTodos() {
        return cantonRepository.findAll();
    }

    @Override
    public Optional<Canton> obtenerPorId(Long id) {
        return cantonRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        cantonRepository.deleteById(id);
    }

    @Override
    public boolean existeMunicipio(Long idMunicipio) {
        return municipioRepository.existsById(idMunicipio);
    }
}