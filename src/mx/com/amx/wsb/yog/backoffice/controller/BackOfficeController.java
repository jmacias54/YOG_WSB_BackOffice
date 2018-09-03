/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsb.yog.backoffice.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.wsb.yog.backoffice.bo.BackOfficeBO;
import mx.com.amx.wsb.yog.backoffice.controller.exception.ControllerException;
import mx.com.amx.wsb.yog.backoffice.dto.MagazineDTO;
import mx.com.amx.wsb.yog.backoffice.model.Nota;


/**
 * @author  Jesus Armando Macias Benitez
 *
 */


@Controller
@RequestMapping("backOfficeController")
public class BackOfficeController {
	
	
	private static Logger LOG = Logger.getLogger(BackOfficeController.class);	
	@Autowired
	private BackOfficeBO backOfficeBO;
	/**
	 * Metodo 
	 * @param MagazineDTO
	 * @return boolean
	 * @throws ControllerException
	 * */
	@RequestMapping( value = "saveNotes" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public boolean saveNotes (@RequestBody MagazineDTO magazineDTO, HttpServletResponse  response ) throws ControllerException {
		LOG.debug(" ====================================================================== ");	
		LOG.info(" ----   Inicia saveNotes    [  BackOfficeController  ]  ----  ");
		LOG.info("magazineDTO: "+magazineDTO);		
		LOG.debug(" ====================================================================== ");	
		
		
		try {
			return backOfficeBO.saveNotes(magazineDTO);
		} catch ( Exception e ){
			LOG.error("Exception saveNotes   [  BackOfficeController  ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	

	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return List<Nota>
	 * @throws ControllerException
	 * */
	@RequestMapping( value = "getNotasByCategoria/{fcIdCategoria}/{pagina}" , method=RequestMethod.GET , headers="Accept=application/json" )
	@ResponseBody
	public  List<Nota> getNotasByCategoria(@PathVariable String fcIdCategoria, @PathVariable Integer pagina, HttpServletResponse  response ) throws ControllerException {
		LOG.debug(" ====================================================================== ");	
		LOG.info(" ----   Inicia getNotasByCategoria    [  BackOfficeController  ]  ----  ");
		LOG.info("fcIdCategoria: "+fcIdCategoria);
		LOG.info("pagina: "+pagina);
		LOG.debug(" ====================================================================== ");	
		
		try {
			Integer numeroPaginas = 0;
			Integer total = 0;
			if (pagina == 0){
				total = backOfficeBO.getTotalNotasByCategoria(fcIdCategoria);
				numeroPaginas = (int)( Math.ceil( (double) total / (double) 10 )   )   ;
				response.setHeader("total", String.valueOf( numeroPaginas ) );
				pagina = 1;
			}	
			return backOfficeBO.getNotasByCategoria(fcIdCategoria, pagina);
		}  catch ( Exception e ){
			LOG.error("Exception getNotasByCategoria   [  BackOfficeController  ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return List<Nota>
	 * @throws ControllerException
	 * */
	@RequestMapping( value = "getNotasByDeporte/{fcIdDeporte}/{pagina}" , method=RequestMethod.GET , headers="Accept=application/json" )
	@ResponseBody
	public  List<Nota> getNotasByDeporte(@PathVariable String fcIdDeporte, @PathVariable Integer pagina, HttpServletResponse  response ) throws ControllerException {
		LOG.debug(" ====================================================================== ");	
		LOG.info(" ----   Inicia getNotasByDeporte    [  BackOfficeController  ]  ----  ");
		LOG.info("fcIdDeporte: "+fcIdDeporte);
		LOG.info("pagina: "+pagina);
		LOG.debug(" ====================================================================== ");	
		
		try {
			Integer numeroPaginas = 0;
			Integer total = 0;
			if (pagina == 0){
				total = backOfficeBO.getTotalNotasByDeporte(fcIdDeporte);
				numeroPaginas = (int)( Math.ceil( (double) total / (double) 10 )   )   ;
				response.setHeader("total", String.valueOf( numeroPaginas ) );
				pagina = 1;
			}	
			return backOfficeBO.getNotasByDeporte(fcIdDeporte, pagina);
		}  catch ( Exception e ){
			LOG.error("Exception getNotasByDeporte   [  BackOfficeController  ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return List<Nota>
	 * @throws ControllerException
	 * */
	@RequestMapping( value = "/getNotasByFcNombre/{fcNombre}/" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public  Nota getNotasByFcNombre(@PathVariable String fcNombre) throws ControllerException {
		LOG.debug(" ====================================================================== ");	
		LOG.info(" --- Inicia getNotasByFcNombre    [  BackOfficeController  ] --- ");
		LOG.info("fcNombre: "+fcNombre);
		LOG.debug(" ====================================================================== ");			
		try {
			
			return backOfficeBO.getNotasByFcNombre(fcNombre);
			
			
		} catch ( Exception e ){
			LOG.error("Exception getNotasByFcNombre     [  BackOfficeController  ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	
	
	
	/**
	 * Metodo 
	 * @param idMagazine
	 * @return List<Nota>
	 * @throws ControllerException
	 * */
	@RequestMapping( value = "getNotasByTag/{fcIdTag}/{pagina}" , method=RequestMethod.GET , headers="Accept=application/json" )
	@ResponseBody
	public  List<Nota> getNotasByTag(@PathVariable String fcIdTag, @PathVariable Integer pagina, HttpServletResponse  response ) throws ControllerException {
		LOG.debug(" ====================================================================== ");	
		LOG.info(" ----   Inicia getNotasByTag    [  BackOfficeController  ]  ----  ");
		LOG.info("fcIdTag: "+fcIdTag);
		LOG.info("pagina: "+pagina);
		LOG.debug(" ====================================================================== ");	
		
		try {
			Integer numeroPaginas = 0;
			Integer total = 0;
			if (pagina == 0){
				total = backOfficeBO.getTotalNotasByTag(fcIdTag);
				numeroPaginas = (int)( Math.ceil( (double) total / (double) 10 )   )   ;
				response.setHeader("total", String.valueOf( numeroPaginas ) );
				pagina = 1;
			}	
			return backOfficeBO.getNotasByTag(fcIdTag, pagina);
		}  catch ( Exception e ){
			LOG.error("Exception getNotasByTag   [  BackOfficeController  ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	
	

	/**
	 * Metodo 
	 * @param idMagazine
	 * @return List<Nota>
	 * @throws ControllerException
	 * */
	@RequestMapping( value = "getNotesPublished" , method=RequestMethod.POST , headers="Accept=application/json" )
	@ResponseBody
	public  List<Nota> getNotesPublished(@RequestParam String idMagazine, HttpServletResponse  response ) throws ControllerException {
		
		LOG.debug(" ====================================================================== ");	
		LOG.info(" ----   Inicia getNotesPublished    [  BackOfficeController  ]  ----  ");
		LOG.info("idMagazine: "+idMagazine);	
		LOG.debug(" ====================================================================== ");	
		
		
		try {
			return backOfficeBO.getNotesPublished(idMagazine);
		}  catch ( Exception e ){
			LOG.error("Exception getNotesPublished   [  BackOfficeController  ] ",e);
			throw new ControllerException(e.getMessage());
		}	 			
	}
	
	

}
