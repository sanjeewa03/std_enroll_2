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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Subject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author sanjeewa_s
 */
public class PDFDownloader extends ActionSupport {
     private String FILE_TYPE = "application/pdf";
    public PDFDownloader() {
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

      byte[] byteStream;
      byteStream = JasperRunManager.runReportToPdf(jasperReport,reportParameters, reportSource);

      outputStream = ServletActionContext.getResponse().getOutputStream();
      ServletActionContext.getResponse().setHeader("Content-Disposition", "inline; filename=" + "senarathnaSanjeewa.pdf");
      ServletActionContext.getResponse().setContentType(FILE_TYPE);
      ServletActionContext.getResponse().setContentLength(byteStream.length);
      outputStream.write(byteStream, 0, byteStream.length);

         return null;
    }
}
