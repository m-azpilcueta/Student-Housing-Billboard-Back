package es.udc.asi.restexample.model.service.util;

import org.springframework.web.multipart.MultipartFile;

import es.udc.asi.restexample.model.exception.ModelException;
import es.udc.asi.restexample.model.service.dto.ImagenDTO;

public interface ImageService {

  String saveImage(MultipartFile file) throws ModelException;

  ImagenDTO getImage(String imagePath) throws ModelException;
}
