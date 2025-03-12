//NOT YET FINAL

package oop.classes.empselfservice;

import java.io.ByteArrayInputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import oop.classes.actors.Accounting;
import oop.classes.calculations.PayrollSummary;
import oop.classes.actors.Employee;

/**
 * Payslip class inherits payroll details from PayrollSummary and provides a method to display employee payslip.
 * Payslip uses HTML format and saved as PDF (will automatically save on "Downloads" folder).
 * @author Admin
 */
public class Payslip extends PayrollSummary {
    
    private YearMonth payrollMonth;
    public Payslip(Employee employee) {
        super(employee); // Call the PayrollSummary constructor with Employee object
        this.payrollMonth = YearMonth.now(); // Default to current month
    }

    // Method to format numbers in PHP or Pesos
    private String formatAsPhp(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("tl", "PH")); // "tl" is Tagalog
        return format.format(amount);
    }

    // Method to print Payslip
    public void printPayslip(int month, int year) throws IOException, DocumentException {
        // Month and year formatting
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        LocalDate payslipDate = LocalDate.of(year, month, 1);
        String period = payslipDate.format(monthYearFormatter);

        // Breaks down the HTML layout of the payslip details
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n");
        htmlContent.append("<html lang=\"en\">\n");
        htmlContent.append("<head>\n");
        htmlContent.append("    <meta charset=\"UTF-8\"/>\n");
        htmlContent.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n");
        htmlContent.append("    <title>Payslip</title>\n");
        htmlContent.append("    <style>\n");
        htmlContent.append("        body {\n");
        htmlContent.append("            font-family: Arial, sans-serif;\n");
        htmlContent.append("            background-color: #EEEEEE;\n");
        htmlContent.append("            margin: 0;\n");
        htmlContent.append("            padding: 0;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        .container {\n");
        htmlContent.append("            max-width: 100%;\n");
        htmlContent.append("            margin: 0;\n");
        htmlContent.append("            background-color: white;\n");
        htmlContent.append("            padding: 20px;\n");
        htmlContent.append("            border: 1px solid #DC5F00;\n");
        htmlContent.append("            border-radius: 5px;\n");
        htmlContent.append("            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n");
        htmlContent.append("        }\n");
        htmlContent.append("        .header {\n");
        htmlContent.append("            background-color: #CF0A0A;\n");
        htmlContent.append("            color: white;\n");
        htmlContent.append("            padding: 10px;\n");
        htmlContent.append("            text-align: center;\n");
        htmlContent.append("            border-radius: 5px 5px 0 0;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        .title {\n");
        htmlContent.append("            color: #000000;\n");
        htmlContent.append("            text-align: center;\n");
        htmlContent.append("            margin-bottom: 20px;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        .section {\n");
        htmlContent.append("            margin-bottom: 20px;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        .section-title {\n");
        htmlContent.append("            color: #DC5F00;\n");
        htmlContent.append("            margin-bottom: 10px;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        table {\n");
        htmlContent.append("            width: 100%;\n");
        htmlContent.append("            border-collapse: collapse;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        th, td {\n");
        htmlContent.append("            border: 1px solid #DC5F00;\n");
        htmlContent.append("            padding: 5px;\n");
        htmlContent.append("            text-align: left;\n");
        htmlContent.append("        }\n");
        htmlContent.append("        th {\n");
        htmlContent.append("            background-color: #CF0A0A;\n");
        htmlContent.append("            color: white;\n");
        htmlContent.append("        }\n");
        htmlContent.append("    </style>\n");
        htmlContent.append("</head>\n");
        htmlContent.append("<body>\n");
        htmlContent.append("<div class=\"container\">\n");
        htmlContent.append("    <div class=\"header\">\n");
        htmlContent.append("        <h2>MotorPH - Country's Top Motorcycle Online Dealership</h2>\n");
        htmlContent.append("    </div>\n");
        htmlContent.append("    <div class=\"title\">\n");
        htmlContent.append("        <h2>Payslip Information</h2>\n");
        htmlContent.append("    </div>\n");
        htmlContent.append("    <div class=\"section\">\n");
        htmlContent.append("        <h3 class=\"section-title\">Employee Details</h3>\n");
        htmlContent.append("        <table>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <th>Detail</th>\n");
        htmlContent.append("                <th>Amount</th>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Employee ID</td>\n");
        htmlContent.append("                <td>").append(getEmployeeId()).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Employee Name</td>\n");
        htmlContent.append("                <td>").append(getEmployeeName()).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Pay Period</td>\n");
        htmlContent.append("                <td>").append(period).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("        </table>\n");
        htmlContent.append("    </div>\n");
        htmlContent.append("    <div class=\"section\">\n");
        htmlContent.append("        <h3 class=\"section-title\">Salary Breakdown</h3>\n");
        htmlContent.append("        <table>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <th>Detail</th>\n");
        htmlContent.append("                <th>Amount</th>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Monthly Gross Pay</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getGrossMonthlySalary())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Net Monthly Pay</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getNetMonthlyPay())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("        </table>\n");
        htmlContent.append("    </div>\n");
        htmlContent.append("    <div class=\"section\">\n");
        htmlContent.append("        <h3 class=\"section-title\">Deductions Breakdown</h3>\n");
        htmlContent.append("        <table>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <th>Deduction</th>\n");
        htmlContent.append("                <th>Amount</th>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>SSS</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getSssDeduction())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>PhilHealth</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getPhilHealthDeduction())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Pag-IBIG</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getPagIbigDeduction())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Withholding Tax</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getWithholdingTax())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("            <tr>\n");
        htmlContent.append("                <td>Total Deductions</td>\n");
        htmlContent.append("                <td>").append(formatAsPhp(getTotalDeductions())).append("</td>\n");
        htmlContent.append("            </tr>\n");
        htmlContent.append("        </table>\n");
        htmlContent.append("    </div>\n");
        htmlContent.append("</div>\n");
        htmlContent.append("</body>\n");
        htmlContent.append("</html>\n");

            // Payslip Path ; will automatically set the file name to "Payslip_employee ID_pay period"
            String downloadsFolder = System.getProperty("user.home") + "/Downloads";
            String fileNamePDF = "Payslip_" + getEmployeeId() + "_" + period.replace(" ", "_") + ".pdf";

            File directory = new File(downloadsFolder);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File pdfFile = new File(downloadsFolder, fileNamePDF);
            convertToPdf(htmlContent.toString(), pdfFile.getAbsolutePath());

        }

        // Getter for payroll month
        @Override
        public YearMonth getPayrollMonth() {
            return this.payrollMonth;
        }

        // Setter for payroll month
        public void setPayrollMonth(YearMonth payrollMonth) {
            this.payrollMonth = payrollMonth;
            super.setPayrollMonth(payrollMonth); // Also update the parent class field
        }
    // Helper method that converts HTML to PDF using iText (imported jar)
    private void convertToPdf(String htmlContent, String fileNamePDF) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileNamePDF));
            document.open();

            // CSS - how the HTML should look like/styled in the PDF
            CSSResolver cssResolver = new StyleAttrCSSResolver();
            CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(
                    "body { font-family: Arial, sans-serif; } ".getBytes()));
            cssResolver.addCss(cssFile);

            // HTML worker - prepares the HTML to PDF conversion
            XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
            CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

            // Pipelines - establishes data flow for transforming HTML and CSS into PDF
            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

            // XML Worker - parses HTML and processes it through pipelines
            XMLWorker worker = new XMLWorker(css, true);
            XMLParser p = new XMLParser(worker);
            p.parse(new StringReader(htmlContent));

        } catch (IOException e) {
            System.err.println("IO Exception during PDF conversion: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw the exception to be handled upstream
        } catch (DocumentException e) {
            System.err.println("Document Exception during PDF conversion: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw the exception to be handled upstream
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}