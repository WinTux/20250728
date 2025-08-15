package com.pepe.proyectospringtool.Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EjemplosController {
	@PostMapping(path="/ejemplo/subida", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> archivoSubir(@RequestParam("archivito") MultipartFile arch) throws IOException{
		System.out.println("Llegó al método.");
		File elArchivo = new File("C:\\Users\\rusok\\Desktop\\"+arch.getOriginalFilename());//Obs
		FileOutputStream fos = new FileOutputStream(elArchivo);
		fos.write(arch.getBytes());
		return ResponseEntity.ok("El archivo se recibió correctamente");
	}
	@GetMapping(path="/ejemplo/bajada")
	public ResponseEntity<Object> descargarArchivo()  throws IOException{
		
		String nombreArch = "C:\\Users\\rusok\\Desktop\\mapache.webp";
		File archivo = new File(nombreArch);
		InputStreamResource recurso = new InputStreamResource(new FileInputStream(archivo));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		ResponseEntity<Object> re = ResponseEntity.ok()
				.headers(headers)
				.contentLength(archivo.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(recurso);
		return re;
	}
}
