package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ArchivoService {


    File crearArchivo(MultipartFile archivo) throws Exception;
    void eliminarArchivo(File archivo);

}