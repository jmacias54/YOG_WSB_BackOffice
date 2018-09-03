/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsb.yog.backoffice.ws;

import java.util.Arrays;
import java.util.List;
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
import mx.com.amx.wsb.yog.backoffice.model.Evento;
import mx.com.amx.wsb.yog.backoffice.utils.PropertiesUtils;
import mx.com.amx.wsb.yog.backoffice.ws.exception.CallWSException;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class EventoCallWS {
	
	private static Logger LOG = Logger.getLogger(EventoCallWS.class);

	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public EventoCallWS  () {
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
			LOG.error("[ [ EventoCallWS ]  ---     [ EventoCallWS ] :   [ EventoCallWS ] :init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}

	}
	
	
	

	public int insert(Evento evento) throws CallWSException {
		LOG.debug("--- insert  [ EventoCallWS ] ---");
		LOG.debug("--- evento: " + evento.toString());
		
		try {					
			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl() + parametrosDTO.getEventoController() +"insert";
			HttpEntity<Evento> entity = new HttpEntity<Evento>(evento);
			
			LOG.debug("--- URL_WS: " + URL_WS.toString());
			return restTemplate.postForObject(URL_WS, entity, Integer.class);		
					
		} catch (Exception e ){
			LOG.error("Exception insert  [ EventoCallWS  ] ",e);
			throw new CallWSException(e.getMessage());
		}	 	
		
	}
	
	
	public int update( Evento evento) throws CallWSException {
		LOG.debug("--- update  [ EventoCallWS ] ---");
		LOG.debug("--- evento: " + evento.toString());
		try {		
			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl() + parametrosDTO.getEventoController() +"update";
			HttpEntity<Evento> entity = new HttpEntity<Evento>(evento);
			LOG.debug("--- URL_WS: " + URL_WS.toString());
			return restTemplate.postForObject(URL_WS, entity, Integer.class);
					
		} catch (Exception e ){
			LOG.error("Exception update  [ EventoCallWS  ] ",e);
			throw new CallWSException(e.getMessage());
		}	 	
		
	}
	
	
	public int delete( String idEvento ) throws CallWSException {
		LOG.debug("--- delete  [ EventoCallWS ] ---");
		LOG.debug("--- idEvento: " + idEvento);
		try {		
			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl() + parametrosDTO.getEventoController() +"delete";
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idEvento", idEvento);
			LOG.debug("--- URL_WS: " + URL_WS.toString());
			return restTemplate.postForObject(URL_WS, parts, Integer.class);
					
					
		} catch (Exception e ){
			LOG.error("Exception delete  [ EventoCallWS  ] ",e);
			throw new CallWSException(e.getMessage());
		}	 	
		
	}
	
	
	public Evento findById( String idEvento ) throws CallWSException {
		LOG.debug("--- findById  [ EventoCallWS ] ---");
		LOG.debug("--- idEvento: " + idEvento);
		try {	
			
			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl() + parametrosDTO.getEventoController() +"findById";
			
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idEvento", idEvento);
			LOG.debug("--- URL_WS: " + URL_WS.toString());
			return restTemplate.postForObject(URL_WS, parts, Evento.class);
					
					
		} catch (Exception e ){
			LOG.error("Exception findById  [ EventoCallWS  ] ",e);
			throw new CallWSException(e.getMessage());
		}	 	
		
	}
	
	
	public List<Evento>  findByNombre( String nombre ) throws CallWSException {
		LOG.debug("--- findByNombre  [ EventoCallWS ] ---");
		LOG.debug("--- nombre: " + nombre);
		try {					
			
			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl() + parametrosDTO.getEventoController() +"findByNombre";
					
					MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("nombre", nombre);
			LOG.debug("--- URL_WS: " + URL_WS.toString());
			return Arrays.asList(restTemplate.postForObject(URL_WS, parts, Evento[].class));		
					
					
		} catch (Exception e ){
			LOG.error("Exception findByNombre  [ EventoCallWS  ] ",e);
			throw new CallWSException(e.getMessage());
		}	 	
		
	}
	
	
	public List<Evento>  findByFecha( String fecha ) throws CallWSException {
		LOG.debug("--- findByFecha  [ EventoCallWS ] ---");
		LOG.debug("--- fecha: " + fecha);
		try {			
			
			
			ParametrosDTO parametrosDTO = PropertiesUtils.obtenerPropiedades();	
			String URL_WS=parametrosDTO.getUrl() + parametrosDTO.getEventoController() +"findByFecha";
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("fecha", fecha);
			LOG.debug("--- URL_WS: " + URL_WS.toString());
			return Arrays.asList(restTemplate.postForObject(URL_WS, parts, Evento[].class));
					
					
					
		} catch (Exception e ){
			LOG.error("Exception findByFecha  [ EventoCallWS  ] ",e);
			throw new CallWSException(e.getMessage());
		}	 	
		
	}

}
