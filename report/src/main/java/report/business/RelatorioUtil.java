package report.business;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioUtil{

	

	public <T> void geraPdf(List<T> dados, String reportFilePath, Map<String, Object> parametros, OutputStream saida) {

		try {

			// compila jrxml em memoria
			//JasperReport jasper = JasperCompileManager.compileReport(jrxml);

			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource(reportFilePath));
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);
			JasperPrint print = JasperFillManager.fillReport(report, parametros, ds);
		
			

			// exporta 			
			JasperExportManager.exportReportToPdfStream(print, saida);


		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar relatório", e);
		}
	}

}
