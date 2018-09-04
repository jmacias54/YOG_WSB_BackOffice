/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsb.yog.backoffice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import mx.com.amx.wsb.yog.backoffice.model.Evento;
import mx.com.amx.wsb.yog.backoffice.ws.EventoCallWS;


/**
 * @author  Jesus Armando Macias Benitez
 *
 */


@Controller
@RequestMapping("eventoController")
public class EventoController {
	
	private static Logger LOG = Logger.getLogger(EventoController.class);
	
	@Autowired private EventoCallWS eventoCallWS;
	
	
	@RequestMapping( value = "/" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public Evento index() throws RestClientException {
		LOG.debug("--- index  [ EventoDAO ] ---");
		try {						
			return new Evento();
		} catch (Exception e ){
			LOG.error("Exception index  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}
	
	
	@RequestMapping( value = "/insert" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public int insert(@RequestBody Evento evento) throws RestClientException {
		LOG.debug("--- insert  [ EventoDAO ] ---");
		LOG.debug("--- evento: " + evento.toString());
		try {						
			return eventoCallWS.insert(evento);
		} catch (Exception e ){
			LOG.error("Exception insert  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}
	
	@RequestMapping( value = "/delete" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public int delete(@RequestBody Evento evento) throws RestClientException {
		LOG.debug("--- delete  [ EventoDAO ] ---");
		LOG.debug("--- evento: " + evento.toString());
		try {						
			return eventoCallWS.delete(evento.getIdEvento());
		} catch (Exception e ){
			LOG.error("Exception delete  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}
	
	@RequestMapping( value = "/update" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public int update(@RequestBody Evento evento ) throws RestClientException {
		LOG.debug("--- update  [ EventoDAO ] ---");
		LOG.debug("--- idEvento: " + evento.getIdEvento());
		try {						
			return eventoCallWS.update( evento);
		} catch (Exception e ){
			LOG.error("Exception update  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}
	
	@RequestMapping( value = "/findById/{idEvento}/" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public Evento findById(@PathVariable String idEvento ) throws RestClientException {
		LOG.debug("--- findById  [ EventoDAO ] ---");
		LOG.debug("--- idEvento: " + idEvento);
		try {						
			return eventoCallWS.findById(idEvento);
		} catch (Exception e ){
			LOG.error("Exception findById  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}
	
	@RequestMapping( value = "/findByNombre/{nombre}/" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public List<Evento>  findByNombre(@PathVariable String nombre ) throws RestClientException {
		LOG.debug("--- findByNombre  [ EventoDAO ] ---");
		LOG.debug("--- nombre: " + nombre);
		try {						
			return eventoCallWS.findByNombre(nombre);
		} catch (Exception e ){
			LOG.error("Exception findByNombre  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}
	
	@RequestMapping( value = "/findByFecha/{fecha}/" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public List<Evento>  findByFecha(@PathVariable String fecha ) throws RestClientException {
		LOG.debug("--- findByFecha  [ EventoDAO ] ---");
		LOG.debug("--- fecha: " + fecha);
		try {						
			return eventoCallWS.findByFecha(fecha);
		} catch (Exception e ){
			LOG.error("Exception findByFecha  [ EventoController  ] ",e);
			throw new RestClientException(e.getMessage());
		}	 	
		
	}

}
