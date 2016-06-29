package report.business;

import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioUtil {

	public <T> void geraPdf(List<T> dados, String reportFilePath, Map<String, Object> params, OutputStream saida) {

		try {
			params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

			// compila jrxml em memoria
			// JasperReport report = JasperCompileManager.compileReport(jrxml);

			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource(reportFilePath));
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
			JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);

			JasperExportManager.exportReportToPdfStream(print, saida);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar relatório", e);
		}
	}

	/*
	 * public enum FileType {
	 * 
	 * PDF("pdf"), HTML("html"), XLS("xls"), ODS("ods");
	 * 
	 * private String label;
	 * 
	 * private FileType(String label) { this.label = label; }
	 * 
	 * public String toString() { return label; } }
	 */

}
