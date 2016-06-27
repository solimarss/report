package report.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class RelatorioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Carro> listAll() {
		List<Carro> carros = new ArrayList<Carro>();

		carros.add(new Carro(1l, "Gol", "Volkswagem"));
		carros.add(new Carro(2l, "Fit", "Honda"));
		carros.add(new Carro(3l, "Palio", "Fiat"));
		carros.add(new Carro(4l, "Sandero", "Renault"));
		carros.add(new Carro(5l, "Frontier", "Nissan"));
		return carros;

	}

}
