/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsb.yog.backoffice.ws;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import mx.com.amx.wsb.yog.backoffice.dto.ParametrosDTO;
import mx.com.amx.wsb.yog.backoffice.model.Nota;
import mx.com.amx.wsb.yog.backoffice.model.NotaMagazine;
import mx.com.amx.wsb.yog.backoffice.utils.PropertiesUtils;
import mx.com.amx.wsb.yog.backoffice.ws.exception.CallWSException;


/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class BackOfficeCallWS  {
	
	private static Logger LOG = Logger.getLogger(BackOfficeCallWS.class);

	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public BackOfficeCallWS  () {
		super();
		restTemplate = new RestTemplate();
		ClientHttpRequestFactory factory = restTemplate.getRequestFactory();

		if (factory instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) factory).setConnectTimeout(15 * 1000);
			((SimpleClientHttpRequestFactory) factory).setReadTimeout(15 * 1000);
			System.out.println("   --- lizando rest template 1");
		} else if (factory instanceof HttpComponentsClientHttpRequestFactory) {
			((HttpComponentsClientHttpRequestFactory) factory).setReadTimeout(15 * 1000);
			((HttpComponentsClientHttpRequestFactory) factory).setConnectTimeout(15 * 1000);
			System.out.println("   --- lizando rest template 2");
		}

		restTemplate.setRequestFactory(factory);
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			props.load(this.getClass().getResourceAsStream("/general.properties"));
		} catch (Exception e) {
			LOG.error("[ [ BackOfficeCallWS ]  ---     [ BackOfficeCallWS ] :   [ BackOfficeCallWS ] :init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}

	}
	
	
	/**
	 * Metodo 
	 * @param idContenido
	 * @param idMagazine
	 * @return Nota
	 * @throws CallWSException
	 * */
	public Nota _getNotaMagazine(String idContenido, String idMagazine) throws CallWSException
	{		
		LOG.debug("   ---  _getNotaMagazine en [ [ BackOfficeCallWS ]  ---  ]");
		LOG.debug("idContenidos   [ BackOfficeCallWS ] : "+idContenido);
		LOG.debug("idMagazine   [ BackOfficeCallWS ] : "+idMagazine);		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"getNotaMagazine";			
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);			
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idContenido", idContenido);
			parts.add("idMagazine", idMagazine);
			return restTemplate.postForObject(URL_WS, parts, Nota.class);			
		} catch(Exception e) {
			LOG.error("Exception en _getNotaMagazine   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return Nota
	 * @throws CallWSException
	 * */
	public Nota[] _getAllIdsNotesPublished(String idMagazine) throws CallWSException
	{		
		LOG.debug("   ---  _getAllIdsNotesPublished en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idMagazine   [ BackOfficeCallWS ] : "+idMagazine);		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"getAllIdsNotesPublished";			
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);			
			HttpEntity<String> entity = new HttpEntity<String>(idMagazine);			
			return restTemplate.postForObject(URL_WS, entity, Nota[].class);			
		} catch(Exception e) {
			LOG.error("Exception en _getAllIdsNotesPublished   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo que llamaba al WS de Datos para obtener todas las secciones 
	 * @return CategoriaDTO[]
	 * @throws CallWSException
	 * */
	public Integer _getTotalNotasByTag(String idTag) throws CallWSException
	{		
		LOG.debug("   ---  _getTotalNotasByCategoria en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idTag   [ BackOfficeCallWS ] : "+idTag);
		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=  parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"getTotalNotasByTag";
			HttpEntity<String> entity = new HttpEntity<String>(idTag);
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);								
			return restTemplate.postForObject(URL_WS, entity, Integer.class);			
		} catch(Exception e) {
			LOG.error("Exception en _getTotalNotasByTag   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo que llamaba al WS de Datos para obtener todas las secciones 
	 * @return CategoriaDTO[]
	 * @throws CallWSException
	 * */
	public Nota[] _getNotasByCategoria(String idCategoria, String pagina, String resultadosPorPagina) throws CallWSException
	{		
		LOG.debug("   ---  _getNotasByCategoria en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idCategoria   [ BackOfficeCallWS ] : "+idCategoria);
		LOG.debug("pagina   [ BackOfficeCallWS ] : "+pagina);
		LOG.debug("resultadosPorPagina   [ BackOfficeCallWS ] : "+resultadosPorPagina);
		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"getNotasByCategoria";			
			
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idCategoria", idCategoria);
			parts.add("pagina", pagina);
			parts.add("resultadosPorPagina", resultadosPorPagina);			
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);								
			return restTemplate.postForObject(URL_WS, parts, Nota[].class);			
		} catch(Exception e) {
			LOG.error("Exception en _getNotasByCategoria   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo que llamaba al WS de Datos para obtener todas las secciones 
	 * @return CategoriaDTO[]
	 * @throws CallWSException
	 * */
	public Integer _getTotalNotasByCategoria(String idCategoria) throws CallWSException
	{		
		LOG.debug("   ---  _getTotalNotasByCategoria en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idCategoria   [ BackOfficeCallWS ] : "+idCategoria);
		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+ "getTotalNotasByCategoria";
			HttpEntity<String> entity = new HttpEntity<String>(idCategoria);

			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);								
			return restTemplate.postForObject(URL_WS, entity, Integer.class);			
		} catch(Exception e) {
			LOG.error("Exception en _getTotalNotasByCategoria   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return Nota
	 * @throws CallWSException
	 * */
	public int _insertNotaMagazine(NotaMagazine notaMagazineDTO) throws CallWSException
	{		
		LOG.debug("   ---  _insertNotaMagazine en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idContenido   [ BackOfficeCallWS ] : "+notaMagazineDTO.getIdContenido());
		LOG.debug("idMagazine   [ BackOfficeCallWS ] : "+notaMagazineDTO.getIdMagazine());
		LOG.debug("orden   [ BackOfficeCallWS ] : "+notaMagazineDTO.getOrden());
		LOG.debug("urlExterna   [ BackOfficeCallWS ] : "+notaMagazineDTO.getUrlExterna());
		try {
						
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"insertNotaMagazine";			
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);			
			HttpEntity<NotaMagazine> entity = new HttpEntity<NotaMagazine>(notaMagazineDTO);			
			return restTemplate.postForObject(URL_WS, entity, Integer.class);			
		} catch(Exception e) {
			LOG.error("Exception en _insertNotaMagazine  [ BackOfficeCallWS ]    [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return Nota
	 * @throws CallWSException
	 * */
	public int _deleteAllNoticias(String idMagazine) throws CallWSException
	{		
		LOG.debug("   ---  _deleteAllNoticias en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idMagazine   [ BackOfficeCallWS ] : "+idMagazine);		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"deleteAllNoticias";			
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);			
			HttpEntity<String> entity = new HttpEntity<String>(idMagazine);			
			return restTemplate.postForObject(URL_WS, entity, Integer.class);			
		} catch(Exception e) {
			LOG.error("Exception en _deleteAllNoticias [ BackOfficeCallWS ]    [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}


	
	

	/**
	 * Metodo que llamaba al WS de Datos para obtener todas las secciones 
	 * @return CategoriaDTO[]
	 * @throws CallWSException
	 * */
	public Nota[] _getNotasByTag(String idTag, String pagina, String resultadosPorPagina) throws CallWSException
	{		
		LOG.debug("   ---  _getNotasByTag en [ BackOfficeCallWS ]  --- ");
		LOG.debug("idTag   [ BackOfficeCallWS ] : "+idTag);
		LOG.debug("pagina   [ BackOfficeCallWS ] : "+pagina);
		LOG.debug("resultadosPorPagina   [ BackOfficeCallWS ] : "+resultadosPorPagina);
		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS= parametrosDTO.getUrl() + parametrosDTO.getBackofficeController()+ "getNotasByTag";			
			
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idTag", idTag);
			parts.add("pagina", pagina);
			parts.add("resultadosPorPagina", resultadosPorPagina);			
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);								
			return restTemplate.postForObject(URL_WS, parts, Nota[].class);			
		} catch(Exception e) {
			LOG.error("Exception en _getNotasByTag   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo que llamaba al WS de Datos para obtener todas las notas por el fcNombre 
	 * @return Nota[]
	 * @throws CallWSException
	 * */
	public Nota   _getNotasByFcNombre(String fcNombre) throws CallWSException
	{		
		LOG.debug("   ---  _getNotasByFcNombre en [ BackOfficeCallWS ]  --- ");
		LOG.debug("fcNombre   [ BackOfficeCallWS ] : "+fcNombre);
		
		
		try {			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl()+parametrosDTO.getBackofficeController()+"getNotasByFcNombre" ;			
			
		
			HttpEntity<String> entity = new HttpEntity<String>(fcNombre);
			LOG.debug("URL_WS   [ BackOfficeCallWS ] : "+URL_WS);								
			
			
			
			Nota nota  =  restTemplate.postForObject(URL_WS, entity, Nota.class);	
			return nota;
			
		} catch(Exception e) {
			LOG.error("Exception en _getNotasByFcNombre   [ BackOfficeCallWS ] : ",e);
			throw new CallWSException(e.getMessage());
		}
	
	}
	
}
