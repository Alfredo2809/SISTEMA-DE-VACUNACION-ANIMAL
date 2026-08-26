package org.esfe.vacunacion.servicios.implementaciones;
import org.esfe.vacunacion.modelos.Colonia;
import org.esfe.vacunacion.repositorios.ColoniaRepository;
import org.esfe.vacunacion.repositorios.CantonRepository;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ColoniaServiceImpl implements IColoniaService {

    @Autowired
    private ColoniaRepository coloniaRepository;

    @Autowired
    private CantonRepository cantonRepository;

    @Override
    public Colonia guardar(Colonia colonia) {
        if (existeCanton(colonia.getCanton().getIdCanton())) {
            return coloniaRepository.save(colonia);
        }
        throw new RuntimeException("El cantón no existe");
    }

    @Override
    public List<Colonia> obtenerTodos() {
        return coloniaRepository.findAll();
    }

    @Override
    public Optional<Colonia> obtenerPorId(Long id) {
        return coloniaRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        coloniaRepository.deleteById(id);
    }

    @Override
    public boolean existeCanton(Long idCanton) {
        return cantonRepository.existsById(idCanton);
    }
}