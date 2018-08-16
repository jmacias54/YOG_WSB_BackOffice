/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsb.yog.backoffice.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.wsb.yog.backoffice.bo.exception.BOException;
import mx.com.amx.wsb.yog.backoffice.dto.MagazineDTO;
import mx.com.amx.wsb.yog.backoffice.model.Nota;
import mx.com.amx.wsb.yog.backoffice.model.NotaMagazine;
import mx.com.amx.wsb.yog.backoffice.ws.BackOfficeCallWS;



/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class BackOfficeBO {

private static Logger LOG = Logger.getLogger(BackOfficeBO.class);
	

	
	@Autowired
	private BackOfficeCallWS backOfficeCallWS;
	
	
	
	public boolean saveNotes(MagazineDTO magazineDTO) throws BOException {
		LOG.debug(" ---  saveNotes en  [ BackOffcieBO  ] --- ");
		LOG.debug("magazineDTO: "+magazineDTO);
		try {
			ArrayList<Nota> listNotas= (ArrayList<Nota>) magazineDTO.getListNotas();
			LOG.debug("size lista: "+listNotas.size());

			if(listNotas!=null && listNotas.size()>0){
				
				String idMagazine=listNotas.get(0).getFcNombreMagazine();				
				LOG.debug("idMagazine: "+idMagazine);
				
				//Borras las notas actuales del magazine
				int resultado_borrado = backOfficeCallWS._deleteAllNoticias(idMagazine);
				LOG.debug("resultado_borrado: "+resultado_borrado);
				
					for (int i = 0; i < listNotas.size(); i++) {
						
						//Verificamos si tiene URL Externa
						String urlExterna="";
						LOG.debug("listNotas.get(i).getFcNombre(): "+listNotas.get(i).getFcNombre());
						if(listNotas.get(i).getFcNombre().toLowerCase().contains("http://") ||listNotas.get(i).getFcNombre().toLowerCase().contains("https://")){
							urlExterna=listNotas.get(i).getFcNombre();
						}else
							urlExterna="";
						
						//Obtenemos el patrocineo
						int patrocinado=listNotas.get(i).getFiBanPatrocinio() == null || listNotas.get(i).getFiBanPatrocinio().equals("")?0:Integer.parseInt(listNotas.get(i).getFiBanPatrocinio());
						LOG.debug("patrocinado: "+patrocinado);
						
						//Guardamos las nuevas notas del magazine
						NotaMagazine notaMagazine = new NotaMagazine();
						notaMagazine.setIdContenido(listNotas.get(i).getFcIdContenido());
						notaMagazine.setIdMagazine(idMagazine);
						notaMagazine.setOrden(i);
						notaMagazine.setUrlExterna(urlExterna);
						backOfficeCallWS._insertNotaMagazine(notaMagazine);
					
				}				
			}
			return true;		
		}catch (Exception e) {
			LOG.error("Exception en saveNotes   [ BackOffcieBO  ]:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
	
	/**
	 * Metodo
	 * @return List<CategoriaDTO>
	 * @throws BOException
	 * */
	public Integer getTotalNotasByCategoria(String fcIdCategoria) throws BOException 
	{
		LOG.debug("Inicia getTotalNotasByCategoria[BO]");
		LOG.debug("fcIdCategoria: "+fcIdCategoria);
		try {							
			return backOfficeCallWS._getTotalNotasByCategoria(fcIdCategoria);			
		}catch (Exception e) {
			LOG.error("Exception en getNotasByCategoria:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
	
	
	
	
	
	/**
	 * Metodo
	 * @return List<CategoriaDTO>
	 * @throws BOException
	 * 
	 * */
	public List<Nota> getNotasByCategoria(String fcIdCategoria, int pagina) throws BOException 
	{
		LOG.debug("Inicia getNotasByCategoria en BackOfficeBO");
		LOG.debug("pagina: "+pagina);
		try {							
			Nota[] arrayNota = backOfficeCallWS._getNotasByCategoria(fcIdCategoria, String.valueOf(pagina), "10");
			return Arrays.asList(arrayNota);
		} catch (Exception e) {
			LOG.error("Exception en getNotasByCategoria:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
	
	
	/**
	 * Metodo
	 * @return List<CategoriaDTO>
	 * @throws BOException
	 * */
	public Integer getTotalNotasByTag(String fcIdTag) throws BOException 
	{
		LOG.debug("Inicia getTotalNotasByTag[BO]");
		LOG.debug("fcIdTag: "+fcIdTag);
		try {							
			return backOfficeCallWS._getTotalNotasByTag(fcIdTag);			
		}catch (Exception e) {
			LOG.error("Exception en getTotalNotasByTag:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
	
	
	public List <Nota> getNotesPublished(String idMagazine) throws BOException 
	{
		LOG.debug("Inicia getNotesPublished en BackOfficeBO");
		LOG.debug("idMagazine: "+idMagazine);
		try {			
			List<Nota> listNotaResponse = new ArrayList<Nota>();
			
			Nota[] arrayNota = backOfficeCallWS._getAllIdsNotesPublished(idMagazine);				
			for (Nota Nota : arrayNota) {
				Nota notaResponse = new Nota();
				notaResponse = backOfficeCallWS._getNotaMagazine(Nota.getFcIdContenido(), idMagazine);
				listNotaResponse.add(notaResponse);
			}
			LOG.debug("size: "+listNotaResponse.size());
			return listNotaResponse;
		} catch (Exception e) {
			LOG.error("Exception en getNotesPublished:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
	
	

	/**
	 * Metodo
	 * @return List<CategoriaDTO>
	 * @throws BOException
	 * 
	 * */
	public List<Nota> getNotasByTag(String fcIdTag, int pagina) throws BOException 
	{
		LOG.debug("Inicia getNotasByTag en BackOfficeBO");
		LOG.debug("pagina: "+pagina);
		try {							
			Nota[] arrayNota = backOfficeCallWS._getNotasByTag(fcIdTag, String.valueOf(pagina), "10");
			return Arrays.asList(arrayNota);
		}catch (Exception e) {
			LOG.error("Exception en getNotasByTag:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
	
	
	

	public Nota getNotasByFcNombre(String fcNombre) throws BOException 
	{
		LOG.debug("=======================================================");	
		LOG.debug("Inicia getNotasByFcNombre en BackOfficeBO");
		LOG.debug("fcNombre"+fcNombre);
		LOG.debug("=======================================================");	
		try {							
			Nota nota  = backOfficeCallWS._getNotasByFcNombre(fcNombre);
			return nota ;
		}catch (Exception e) {
			LOG.error("Exception en getNotasByFcNombre:",e);
			throw new BOException(e.getMessage());
		}		
	}
	
}
