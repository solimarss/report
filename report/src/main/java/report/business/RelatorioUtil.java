package report.business;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioUtil {

	private String reportFilePath;
	private Map<String, Object> params;
	private String outputFile;

	private boolean isReportGenerated;

	public RelatorioUtil(String reportFilePath, Map<String, Object> params, String outputFile) {
		this.reportFilePath = reportFilePath;
		this.params = params;
		this.outputFile = outputFile;
		this.params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	public <T> boolean execute(List<T> list) {
		try {

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource(reportFilePath));
			JasperPrint impressao = JasperFillManager.fillReport(report, params, ds);
			this.isReportGenerated = impressao.getPages().size() > 0;
			

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + this.outputFile + "\"");
			JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

			//context.getApplication().getStateManager().saveView(context);
			context.renderResponse();
			context.responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isReportGenerated;
	}


}
