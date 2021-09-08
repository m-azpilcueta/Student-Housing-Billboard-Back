package es.udc.asi.restexample.model.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.domain.Tag;
import es.udc.asi.restexample.model.repository.TagDao;
import es.udc.asi.restexample.model.service.dto.TagDTO;

@Service
@Transactional(readOnly = true)
public class TagService {

  @Autowired
  private TagDao tagDAO;

  public List<TagDTO> findAll() {
    return tagDAO.findAll().stream().sorted(Comparator.comparing(Tag::getName)).map(TagDTO::new)
        .collect(Collectors.toList());
  }
}
