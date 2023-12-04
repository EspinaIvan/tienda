package com.tienda.servicios;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class SubirArchivos {

	public static void guardarArchivo (String rutaArchivo, String nombreArchivo,
            MultipartFile multipartFile) throws IOException {
		
        Path subirRuta = Paths.get(rutaArchivo);

        if (!Files.exists(subirRuta)) {
            Files.createDirectories(subirRuta);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
        	
            Path filePath = subirRuta.resolve(nombreArchivo);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            
        } catch (IOException ioe) {
        	
            throw new IOException("No se puede guardar el archivo: " + nombreArchivo, ioe);
        }
    }
	
	public static String obtenerExtension(String nombreArchivo) {
		
	    int lastDotIndex = nombreArchivo.lastIndexOf(".");
	    
	    if (lastDotIndex != -1) {
	    	
	        return nombreArchivo.substring(lastDotIndex);
	    }
	    
	    return "";
	}

}

