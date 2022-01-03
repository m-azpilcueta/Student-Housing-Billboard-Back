package es.udc.asi.restexample.model.service;

import es.udc.asi.restexample.model.domain.Estudio;
import es.udc.asi.restexample.model.repository.EstudioDao;
import es.udc.asi.restexample.model.service.dto.EstudioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EstudioService {
  @Autowired
  private EstudioDao estudioDao;

  @Transactional(readOnly = false)
  public EstudioDTO crearEstudio(EstudioDTO estudio) {
    Estudio e = new Estudio(estudio);
    estudioDao.create(e);
    return new EstudioDTO(e);
  }
}
