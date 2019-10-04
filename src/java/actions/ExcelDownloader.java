/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import beanFactories.SubjectBeanFactory;
import com.opensymphony.xwork2.ActionSupport;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Subject;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author sanjeewa_s
 */
public class ExcelDownloader extends ActionSupport {
    private String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public ExcelDownloader() {
    }
    
    public String execute() throws Exception {
        System.out.println("inside PDFDownloader");
        List<Subject> postData = new ArrayList<>();
        String reportPath;
        OutputStream outputStream;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        String logoFilePath;
        JasperPrint jasperPrint = null;
        Map reportParameters;
        SubjectBeanFactory ps = new SubjectBeanFactory();

          //reportPath = request.getServletContext().getRealPath("/reports") + "\\report1.jrxml";
          reportPath = "C:\\Users\\sanjeewa_s\\Documents\\NetBeansProjects\\StudentEnrollement2\\web\\reports\\subject.jrxml";
          logoFilePath = "C:\\Users\\sanjeewa_s\\Documents\\NetBeansProjects\\StudentEnrollement2\\web\\reports\\index1.jpg";

          reportParameters = new HashMap();
          reportParameters.put("paramLogFilePath", "Posts");

          jasperDesign = JRXmlLoader.load(reportPath);
          jasperReport = JasperCompileManager.compileReport(jasperDesign);

          postData = SubjectBeanFactory.getSubjectBeans();
          reportSource = new JRBeanCollectionDataSource(postData);

          jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters,reportSource );
         

          outputStream = ServletActionContext.getResponse().getOutputStream();
          ServletActionContext.getResponse().setHeader("Content-Disposition", "inline; filename=" + "senarathnaSanjeewa.xlsx");
          ServletActionContext.getResponse().setContentType(FILE_TYPE);
          ServletActionContext.getResponse().setContentLength(4960);
          JRXlsxExporter exporterXLS = new JRXlsxExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            //exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "C:/javareportCall/report.xlsx");
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            //exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "senarathnaSanjeewa.xlsx");
            //exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
            exporterXLS.exportReport();            

             return null;
        }
    
    
}
