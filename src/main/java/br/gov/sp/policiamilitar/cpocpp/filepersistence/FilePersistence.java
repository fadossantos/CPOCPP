package br.gov.sp.policiamilitar.cpocpp.filepersistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FilePersistence {

	private String caminhoArquivo = "c:\\arquivos\\cpocpp";

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public void salvaArquivo(MultipartFile mpFile, String diretorio, String nomeArquivo) throws IOException {
		Files.createDirectories(Paths.get(caminhoArquivo + File.separator + diretorio));
		Files.write(Paths.get(caminhoArquivo + File.separator + diretorio + File.separator + nomeArquivo),
				mpFile.getBytes());
	}

	public File obtemArquivo(String caminhoArquivo) {
		return new File(caminhoArquivo);
	}

	public void deletaArquivo(String diretorio, String nomeArquivo) throws IOException {
		Files.delete(Paths.get(caminhoArquivo + File.separator + diretorio + File.separator + nomeArquivo));
	}

	public FilePersistence() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	public void deletaDiretorio(String diretorio) {
		File dir = new File(caminhoArquivo + File.separator + diretorio);
		deleteDir(dir);		
	}

}
