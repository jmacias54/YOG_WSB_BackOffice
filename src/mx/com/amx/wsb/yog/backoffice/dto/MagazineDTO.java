/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsb.yog.backoffice.dto;

import java.util.List;

import mx.com.amx.wsb.yog.backoffice.model.Nota;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class MagazineDTO {
	private List<Nota> listNotas;

	/**
	 * @return the listNotas
	 */
	public List<Nota> getListNotas() {
		return listNotas;
	}

	/**
	 * @param listNotas the listNotas to set
	 */
	public void setListNotas(List<Nota> listNotas) {
		this.listNotas = listNotas;
	}

}
