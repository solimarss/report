package report.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class RelatorioBC implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Inject
	private RelatorioDAO relatorioDAO;
	
	public List<Carro> listAll() {
		return relatorioDAO.listAll();
	}

}
