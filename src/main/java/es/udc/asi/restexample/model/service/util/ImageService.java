package es.udc.asi.restexample.model.service.util;

import org.springframework.web.multipart.MultipartFile;

import es.udc.asi.restexample.model.exception.ModelException;
import es.udc.asi.restexample.model.service.dto.ImageDTO;

public interface ImageService {

  String saveImage(MultipartFile file, Long id) throws ModelException;

  ImageDTO getImage(String imagePath, Long id) throws ModelException;
}