package com.tienda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TiendaApplication {

	private static final Logger logger = LogManager.getLogger(TiendaApplication.class);

	public static void main(String[] args) {

		String logDirectoryPath = "logs"; // Puedes ajustar la ruta según tu configuración
		String logFilePath = "logs/logs.log";

		Path logDirectory = Paths.get(logDirectoryPath);
		Path logFile = Paths.get(logFilePath);

		try {
			// Verificar y crear el directorio si no existe
			if (Files.notExists(logDirectory)) {
				Files.createDirectories(logDirectory);
			}

			// Verificar y crear el archivo si no existe
			if (Files.notExists(logFile)) {
				Files.createFile(logFile);
			}
		} catch (IOException e) {
			// Manejar cualquier excepción que pueda ocurrir al crear el directorio o el
			// archivo
			e.printStackTrace();
		}

		logger.info("Iniciando logs");

		SpringApplication.run(TiendaApplication.class, args);
	}

}
