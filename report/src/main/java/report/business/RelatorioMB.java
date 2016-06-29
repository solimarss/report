package report.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;



@ManagedBean
@SessionScoped
public class RelatorioMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RelatorioBC relatorioBC;

		
	
	public void gerarPdfWeb() throws JRException, IOException{
		System.out.println("executando método gerarPdfWeb()");
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		RelatorioUtil util = new RelatorioUtil();
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		
		util.geraPdf(relatorioBC.listAll(),"reports/report1.jasper", params, response.getOutputStream());
		
		context.renderResponse();
		context.responseComplete();
		
	}
	
	
	public void salvarPdfEmDisco() throws JRException, IOException{
		System.out.println("executando método salvarPdfEmDisco()");
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		RelatorioUtil util = new RelatorioUtil();

		File pdfFile = new File("/home/solimarss/testeB.pdf");
		
		util.geraPdf(relatorioBC.listAll(),"reports/report1.jasper", params, new FileOutputStream(pdfFile));
		
		System.out.println("Caminho do arquivo de saída: "+pdfFile.getAbsolutePath());
		
	}
	

}
