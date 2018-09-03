package mx.com.amx.wsb.yog.backoffice.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import mx.com.amx.wsb.yog.backoffice.dto.ParametrosDTO;






public class PropertiesUtils {

	// LOG
	private static Logger logger = Logger.getLogger(PropertiesUtils.class);

	/**
	 * Metodo encargado de generar el DTO con la informacion del properties.
	 * 
	 * @return ParametrosDTO
	 * @throws ProcesoWorkflowException
	 */
	public static  ParametrosDTO obtenerPropiedades() throws Exception 
	{	
		logger.debug("Inicia obtenerPropiedades en PropertiesUtils");
		ParametrosDTO parametrosDTO = new ParametrosDTO();		 
		try {	    		
			//Properties war
			Properties propsWAR = new Properties();
			propsWAR.load(PropertiesUtils.class.getResourceAsStream( "/general.properties" ));
			String ambiente = propsWAR.getProperty("ambiente");
			String rutaProperties = propsWAR.getProperty("ambiente.resources.properties".replace("ambiente", ambiente));
			
			//Properties server
			Properties propsServer = new Properties();		
			propsServer.load(new FileInputStream(new File(rutaProperties)));
			
			
			parametrosDTO.setAmbiente(ambiente);
			parametrosDTO.setUrl(propsServer.getProperty(ambiente+".wsd.url"));
			parametrosDTO.setDominio(propsServer.getProperty(ambiente+".dominio"));
			parametrosDTO.setBackofficeController(propsServer.getProperty("controller.backoffice"));
			parametrosDTO.setEventoController(propsServer.getProperty("controller.evento"));
			
						
		} catch (Exception ex) {
			parametrosDTO = new ParametrosDTO();
			logger.error("No se encontro el Archivo de propiedades: ", ex);			
			throw new Exception(ex.getMessage());
		}
		return parametrosDTO;
    }

}// FIN CLASE
