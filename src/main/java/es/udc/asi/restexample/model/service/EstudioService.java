package es.udc.asi.restexample.model.service;

import es.udc.asi.restexample.model.domain.Estudio;
import es.udc.asi.restexample.model.domain.Universidad;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.repository.EstudioDao;
import es.udc.asi.restexample.model.repository.UniversidadDao;
import es.udc.asi.restexample.model.service.dto.EstudioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EstudioService {
  @Autowired
  private EstudioDao estudioDao;

  @Autowired
  private UniversidadDao universidadDao;


  public List<EstudioDTO> findAllEstudios() {
    return estudioDao.findAll().stream().map(estudio -> new EstudioDTO(estudio)).collect(Collectors.toList());
  }

  @Transactional(readOnly = false)
  public EstudioDTO crearEstudio(EstudioDTO estudio) throws NotFoundException {
    Estudio e = new Estudio(estudio.getNombreEstudio());

    if (universidadDao.findById(estudio.getUniversidad().getIdUniversidad()) == null) {
      throw new NotFoundException(estudio.getUniversidad().getIdUniversidad().toString(), Universidad.class);
    }

    e.setUniversidad(universidadDao.findById(estudio.getUniversidad().getIdUniversidad()));

    estudioDao.create(e);
    return new EstudioDTO(e);
  }
}
