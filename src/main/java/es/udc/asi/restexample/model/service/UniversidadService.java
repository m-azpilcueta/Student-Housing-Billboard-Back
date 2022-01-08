package es.udc.asi.restexample.model.service;

import es.udc.asi.restexample.model.domain.Universidad;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.repository.EstudioDao;
import es.udc.asi.restexample.model.repository.UniversidadDao;
import es.udc.asi.restexample.model.service.dto.EstudioDTO;
import es.udc.asi.restexample.model.service.dto.UniversidadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UniversidadService {
  @Autowired
  private UniversidadDao universidadDao;

  @Autowired
  private EstudioDao estudioDao;

  public List<UniversidadDTO> findAllUniversidades() {
    return universidadDao.findAll().stream().map(universidad -> new UniversidadDTO(universidad)).collect(Collectors.toList());
  }

  public List<EstudioDTO> findAllByUniversidad(Long id) throws NotFoundException {
    Universidad universidad = universidadDao.findById(id);
    if (universidad == null){
      throw new NotFoundException(id.toString(), Universidad.class);
    }
    return estudioDao.findAllByUniversidad(id).stream().map(estudio -> new EstudioDTO(estudio)).collect(Collectors.toList());

  }
}
