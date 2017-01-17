package br.gov.sp.policiamilitar.cpocpp.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.gov.sp.policiamilitar.cpocpp.business.entities.DocumentoRelacionado;
import br.gov.sp.policiamilitar.cpocpp.business.services.DocumentoRelacionadoService;
import br.gov.sp.policiamilitar.cpocpp.filepersistence.FilePersistence;


@Controller
public class DocumentoRelacionadoController {


    @Autowired
    private DocumentoRelacionadoService documentoRelacionadoService;
      
    @Autowired 
    private FilePersistence filePersistence;
    
    public DocumentoRelacionadoController() {
        super();
    }

   
    @RequestMapping("/downloadDocumentoRelacionado/{id}")
    public void downloadDocumentoRelacionado(@PathVariable Long id, HttpServletResponse response) throws IOException {
    	
    	DocumentoRelacionado docRel = this.documentoRelacionadoService.findByOne(id);
    	if(docRel != null)
    	{    	
    	 response.setContentType("application/pdf");
         
         /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
             while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
         response.setHeader("Content-Disposition", String.format("inline; filename=\"" + docRel.getNomeDocumentoRelacionado() + ".pdf" + "\""));

         /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
         //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
          
         response.setContentLength((int)filePersistence.obtemArquivo(docRel.getCaminhoArquivoSalvo()).length());
  
         InputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(docRel.getCaminhoArquivoSalvo())));
  
         //Copy bytes from source to destination(outputstream in this example), closes both streams.
         try {
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    }      
    
  
}
