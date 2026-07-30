package com.example.demo.service.impl;

import com.example.demo.service.ArchivoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    @Override
    public File crearArchivo(MultipartFile archivo) throws Exception {

        if(archivo == null || archivo.isEmpty()){
            return null;
        }

        File file = File.createTempFile("upload-", archivo.getOriginalFilename());

        archivo.transferTo(file);
        return file;
    }

    @Override
    public void eliminarArchivo(File archivo){
        if(archivo != null && archivo.exists()){
            archivo.delete();
        }
    }

}