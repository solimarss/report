package report.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;



@ManagedBean
@SessionScoped
public class RelatorioMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RelatorioBC relatorioBC;

	public void gerReport() throws JRException, IOException{
		System.out.println("Foi");
		//https://github.com/algaworks/curso-javaee-primefaces/tree/master/ChamandoRelatorioPaginaJSF
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		RelatorioUtil util = new RelatorioUtil("reports/report1.jasper", params, "saida.pdf");
		
		System.out.println("Realtorio gerado: "+util.execute(relatorioBC.listAll()));
		
		
		
	}
	

}
