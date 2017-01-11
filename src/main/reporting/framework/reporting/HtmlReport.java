package reporting.framework.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import reporting.framework.utilities.FrameworkException;
import reporting.framework.utilities.Util;

public class HtmlReport implements ReportType{
		
	private String _testLogPath;

    private String _resultSummaryPath;

    private ReportSettings _reportSettings;

    private ReportTheme _reportTheme;

    private Boolean _isTestLogHeaderTableCreated = false;

    private Boolean _isTestLogMainTableCreated = false;

    private Boolean _isResultSummaryHeaderTableCreated = false;

    private Boolean _isResultSummaryMainTableCreated = false;

    private String _currentSection = "";

    private String _currentSubSection = "";

    private int _currentContentNumber = 1;

    public HtmlReport(ReportSettings reportSettings, ReportTheme reportTheme)
    {
        this._reportSettings = reportSettings;
        this._reportTheme = reportTheme;
        String reportPath = reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results" + Util.GetFileSeparator() + reportSettings.getReportName() +".html";
        this._testLogPath = reportPath;
        //reportPath = reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results" + Util.GetFileSeparator() + "Summary.html";
        this._resultSummaryPath = reportPath;
    }
    
    @Override
    public void AddResultSummaryFooter(String totalExecutionTime, int nTestsPassed, int nTestsFailed) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	FileWriter file = new FileWriter(test, true);
        	BufferedWriter streamWriter = new BufferedWriter(file);
            String objArray = "\t\t\t </tbody> \n\t\t </table> \n\n\t\t <table id='footer'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <tfoot> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4'>Total Duration: "+ totalExecutionTime+ "</th> \n\t\t\t\t </tr> \n\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <td class='pass'>&nbsp;Tests passed</td> \n\t\t\t\t\t <td class='pass'>&nbsp;: "+ nTestsPassed+ "</td> \n\t\t\t\t\t <td class='fail'>&nbsp;Tests failed</td> \n\t\t\t\t\t <td class='fail'>&nbsp;: "+ nTestsFailed+ "</td> \n\t\t\t\t </tr> \n\t\t\t </tfoot> \n\t\t </table> \n\t </body> \n</html>";
            streamWriter.write(objArray);
            file.close();
            streamWriter.close();
        }
        catch (IOException oException)
        {
        	System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding footer to HTML result summary");
        }
    }
    
    @Override
    public void AddResultSummaryHeading(String heading) throws FrameworkException
    {
        if (!this._isResultSummaryHeaderTableCreated)
        {
            this.CreateResultSummaryHeaderTable();
            this._isResultSummaryHeaderTableCreated = true;
        }
        try
        {
        	File test = new File(this._resultSummaryPath);
        	FileWriter file = new FileWriter(test, true);
        	BufferedWriter streamWriter = new BufferedWriter(file);
        	String html = "\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4' style='font-family:sans-serif; font-size:1.4em;'> \n\t\t\t\t\t\t "+ heading.toUpperCase()+ " \n\t\t\t\t\t </th> \n\t\t\t\t </tr> \n";
            streamWriter.write(html);
            file.close();
            streamWriter.close();
        }
        catch (IOException oException)
        {
        	System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding heading to HTML result summary");
        }
    }

    @Override
    public void AddResultSummarySubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	FileWriter file = new FileWriter(test, true);
        	BufferedWriter streamWriter = new BufferedWriter(file);
            String strArrays = "\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <th>&nbsp;"+ subHeading1.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th>&nbsp;"+ subHeading2.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th>&nbsp;"+ subHeading3.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th>&nbsp;"+ subHeading4.replace(" ", "&nbsp;") + "</th> \n\t\t\t\t </tr> \n";
            streamWriter.write(strArrays);
            file.close();
            streamWriter.close();
        }
        catch (IOException oException)
        {
        	System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding sub-heading to HTML result summary");
        }
    }

    @Override
    public void AddResultSummaryTableHeadings() throws FrameworkException
    {
        if (!this._isResultSummaryMainTableCreated)
        {
            this.CreateResultSummaryMainTable();
            this._isResultSummaryMainTableCreated = true;
        }
        try
        {
        	File test = new File(this._resultSummaryPath);
        	FileWriter file = new FileWriter(test, true);
        	BufferedWriter streamWriter = new BufferedWriter(file);
        	String html = "\t\t\t <thead> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th>Test_Scenario</th> \n\t\t\t\t\t <th>Test_Case</th> \n\t\t\t\t\t <th>Test_Description</th> \n\t\t\t\t\t <th>Execution_Time</th> \n\t\t\t\t\t <th>Test_Status</th> \n\t\t\t\t </tr> \n\t\t\t </thead> \n\n";
            streamWriter.write(html);
            file.close();
            streamWriter.close();
        }
        catch (IOException oException)
        {
        	System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding main table headings to HTML result summary");
        }
    }

    @Override
    public void AddTestLogFooter(String executionTime, int nStepsPassed, int nStepsFailed) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
            String objArray = "\t\t\t </tbody> \n\t\t </table> \n\n\t\t <table id='footer'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <tfoot> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4'>Execution Duration: "+ executionTime+ "</th> \n\t\t\t\t </tr> \n\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <td class='pass'>&nbsp;Steps passed</td> \n\t\t\t\t\t <td class='pass'>&nbsp;: "+ nStepsPassed+ "</td> \n\t\t\t\t\t <td class='fail'>&nbsp;Steps failed</td> \n\t\t\t\t\t <td class='fail'>&nbsp;: "+ nStepsFailed+ "</td> \n\t\t\t\t </tr> \n\t\t\t </tfoot> \n\t\t </table> \n\t </body> \n</html>";
            streamWriter.write(objArray);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding footer to HTML test log");
        }
    }

    @Override
    public void AddTestLogHeading(String heading) throws FrameworkException
    {
        if (!this._isTestLogHeaderTableCreated)
        {
            this.CreateTestLogHeaderTable();
            this._isTestLogHeaderTableCreated = true;
        }
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String html = "\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4' style='font-family:sans-serif; font-size:1.4em;'> \n\t\t\t\t\t\t "+ heading+ " \n\t\t\t\t\t </th> \n\t\t\t\t </tr> \n";
            streamWriter.write(html);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding heading to HTML test log");
        }
    }

    @Override
    public void AddTestLogSection(String section) throws FrameworkException
    {
        String str = "";
        if (!this._currentSection.equals(""))
        {
            str = "\t\t\t </tbody>";
        }
        //this._currentSection = Regex.Replace(section, "[^a-zA-Z0-9]", "");
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
            String str1 = str;
            String strArrays = str1+ "\t\t\t <tbody> \n\t\t\t\t <tr class='section'> \n\t\t\t\t\t <td colspan='5' onclick=\"toggleMenu('"+ this._currentSection+ "')\">+ "+ section+ "</td> \n\t\t\t\t </tr> \n\t\t\t </tbody> \n\t\t\t <tbody id='"+ this._currentSection+ "' style='display:table-row-group'> \n" ;
            str = strArrays;
            streamWriter.write(str);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding section to HTML test log");
        }
    }

    @Override
    public void AddTestLogSubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
            String strArrays = "\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <th>&nbsp;"+ subHeading1.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th>&nbsp;"+ subHeading2.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th>&nbsp;"+ subHeading3.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th>&nbsp;"+ subHeading4.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t </tr> \n";
            streamWriter.write(strArrays);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding sub-heading to HTML test log");
        }
    }

    @Override
    public void AddTestLogSubSection(String subSection) throws FrameworkException
    {
        //this._currentSubSection = Regex.Replace(subSection, "[^a-zA-Z0-9]", "");
        this._currentContentNumber = 1;
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
            String strArrays = "\t\t\t\t <tr class='subheading subsection'> \n\t\t\t\t\t <td colspan='5' onclick=\"toggleSubMenu('"+ this._currentSection+ this._currentSubSection+ "')\">&nbsp;+ "+ subSection+ "</td> \n\t\t\t\t </tr> \n" ;
            streamWriter.write(strArrays);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding sub-section to HTML test log");
        }
    }

    @Override
    public void AddTestLogTableHeadings() throws FrameworkException
    {
        if (!this._isTestLogMainTableCreated)
        {
            this.CreateTestLogMainTable();
            this._isTestLogMainTableCreated = true;
        }
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String html = "\t\t\t <thead> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th>Step_No</th> \n\t\t\t\t\t <th>Step_Name</th> \n\t\t\t\t\t <th>Description</th> \n\t\t\t\t\t <th>Status</th> \n\t\t\t\t\t <th>Step_Time</th> \n\t\t\t\t </tr> \n\t\t\t </thead> \n\n";
            streamWriter.write(html);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding main table headings to HTML test log");
        }
    }

    private void CreateResultSummaryHeaderTable() throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String html = "\t <body> \n\t\t <table id='header'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <thead> \n";
            streamWriter.write(html);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding header table to HTML result summary");
        }
    }

    private void CreateResultSummaryMainTable() throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String html = "\t\t\t </thead> \n\t\t </table> \n\n\t\t <table id='main'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 10%' /> \n\t\t\t\t <col style='width: 10%' /> \n\t\t\t\t <col style='width: 50%' /> \n\t\t\t\t <col style='width: 20%' /> \n\t\t\t\t <col style='width: 10%' /> \n\t\t\t </colgroup> \n\n";
            streamWriter.write(html);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding main table to HTML result summary");
        }
    }

    private void CreateTestLogHeaderTable() throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String html = "\t <body> \n\t\t <table id='header'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <thead> \n";
            streamWriter.write(html);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding header table to HTML test log");
        }
    }

    private void CreateTestLogMainTable() throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String html = "\t\t\t </thead> \n\t\t </table> \n\n\t\t <table id='main'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 8%' /> \n\t\t\t\t <col style='width: 12%' /> \n\t\t\t\t <col style='width: 62%' /> \n\t\t\t\t <col style='width: 8%' /> \n\t\t\t\t <col style='width: 10%' /> \n\t\t\t </colgroup> \n\n";
            streamWriter.write(html);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while adding main table to HTML test log");
        }
    }

    private String GetJavascriptFunctions()
    {
        return "\t\t <script> \n\t\t\t function toggleMenu(objID) { \n\t\t\t\t if (!document.getElementById) return; \n\t\t\t\t var ob = document.getElementById(objID).style; \n\t\t\t\t if(ob.display === 'none') { \n\t\t\t\t\t try { \n\t\t\t\t\t\t ob.display='table-row-group'; \n\t\t\t\t\t } catch(ex) { \n\t\t\t\t\t\t ob.display='block'; \n\t\t\t\t\t } \n\t\t\t\t } \n\t\t\t\t else { \n\t\t\t\t\t ob.display='none'; \n\t\t\t\t } \n\t\t\t } \n\t\t\t function toggleSubMenu(objId) { \n\t\t\t\t for(i=1; i<10000; i++) { \n\t\t\t\t\t var ob = document.getElementById(objId.concat(i)); \n\t\t\t\t\t if(ob === null) { \n\t\t\t\t\t\t break; \n\t\t\t\t\t } \n\t\t\t\t\t if(ob.style.display === 'none') { \n\t\t\t\t\t\t try { \n\t\t\t\t\t\t\t ob.style.display='table-row'; \n\t\t\t\t\t\t } catch(ex) { \n\t\t\t\t\t\t\t ob.style.display='block'; \n\t\t\t\t\t\t } \n\t\t\t\t\t } \n\t\t\t\t\t else { \n\t\t\t\t\t\t ob.style.display='none'; \n\t\t\t\t\t } \n\t\t\t\t } \n\t\t\t } \n\t\t </script> \n";
    }

    private String GetThemeCss()
    {
        String contentForeColor = "\t\t <style type='text/css'> \n\t\t\t body { \n\t\t\t\t background-color: "+ this._reportTheme.getContentForeColor()+ "; \n\t\t\t\t font-family: sans-serif; \n\t\t\t\t text-align: center; \n\t\t\t width: 97%} \n\n\t\t\t small { \n\t\t\t\t font-size: 0.7em; \n\t\t\t } \n\n\t\t\t table { \n\t\t\t\t border: 2px solid #868686; \n\t\t\t\t border-collapse: collapse; \n\t\t\t\t border-spacing: 0px; \n\t\t\t\t width: 1200px; \n\t\t\t\t margin-left: auto; \n\t\t\t\t margin-right: auto; \n\t\t\t } \n\n\t\t\t tr.heading { \n\t\t\t\t background-color: "+ this._reportTheme.getHeadingBackColor()+ "; \n\t\t\t\t color: "+ this._reportTheme.getHeadingForeColor()+ "; \n\t\t\t\t font-size: 1.4em; \n\t\t\t\t font-weight: bold; \n\t\t\t } \n\n\t\t\t tr.subheading { \n\t\t\t\t background-color: "+ this._reportTheme.getHeadingForeColor()+ "; \n\t\t\t\t color: "+ this._reportTheme.getHeadingBackColor()+ "; \n\t\t\t\t font-weight: bold; \n\t\t\t\t font-size: 1em; \n\t\t\t\t text-align: justify; \n\t\t\t } \n\n\t\t\t tr.section { \n\t\t\t\t background-color: "+ this._reportTheme.getSectionBackColor()+ "; \n\t\t\t\t color: "+ this._reportTheme.getSectionForeColor()+ "; \n\t\t\t\t cursor: pointer; \n\t\t\t\t font-weight: bold; \n\t\t\t\t font-size: 0.9em; \n\t\t\t\t text-align: justify; \n\t\t\t } \n\n\t\t\t tr.subsection { \n\t\t\t\t cursor: pointer; \n\t\t\t } \n\n\t\t\t tr.content { \n\t\t\t\t background-color: "+ this._reportTheme.getContentBackColor()+ "; \n\t\t\t\t color: "+ this._reportTheme.getContentForeColor()+ "; \n\t\t\t\t font-size: 0.9em; \n\t\t\t\t display: table-row; \n\t\t\t } \n\n\t\t\t td, th { \n\t\t\t\t padding: 8px; \n\t\t\t\t text-align: inherit\\0/; \n\t\t\t } \n\n\t\t\t td.justified { \n\t\t\t\t text-align: justify; \n\t\t\t } \n\n\t\t\t td.pass { \n\t\t\t\t font-weight: bold; \n\t\t\t\t color: green; \n\t\t\t } \n\n\t\t\t td.fail { \n\t\t\t\t font-weight: bold; \n\t\t\t\t color: red; \n\t\t\t } \n\n\t\t\t td.done, td.screenshot { \n\t\t\t\t font-weight: bold; \n\t\t\t\t color: black; \n\t\t\t } \n\n\t\t\t td.debug { \n\t\t\t\t font-weight: bold; \n\t\t\t\t color: blue; \n\t\t\t } \n\n\t\t\t td.warning { \n\t\t\t\t font-weight: bold; \n\t\t\t\t color: orange; \n\t\t\t } \n\t\t </style> \n\n";
        return contentForeColor;
    }
    
    @Override
    public void InitializeResultSummary() throws IOException, FrameworkException
    {
    	BufferedWriter streamWriter;
        try
        {
        	File file = new File(this._resultSummaryPath);
        	file.setWritable(true);
            if (!file.exists())
            {
                file.createNewFile();
            }
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while creating HTML result summary file");
        }
        try
        {
        	File test = new File(this._resultSummaryPath);
            streamWriter = new BufferedWriter(new FileWriter(test, true));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println(fileNotFoundException.getStackTrace());
            throw new FrameworkException("Cannot find HTML result summary file");
        }
        String projectName = "<!DOCTYPE html> \n<html> \n\t <head> \n\t\t <meta charset='UTF-8'> \n\t\t <title>"+ this._reportSettings.getProjectName()+ " - Automation Execution Results Summary</title> \n\n"+ this.GetThemeCss()+ this.GetJavascriptFunctions()+ "\t </head> \n";
        streamWriter.write(projectName);
        streamWriter.flush();
        streamWriter.close();
    }

    @Override
    public void InitializeTestLog() throws IOException, FrameworkException
    {
    	BufferedWriter streamWriter;
        try
        {
        	File file = new File(this._testLogPath);
        	file.setWritable(true);
            if (!file.exists())
            {
            	file.getParentFile().mkdir();
                file.createNewFile();
            }
        }
        catch (IOException oException)
        {
            System.out.println(oException.getMessage());
            throw new FrameworkException("Error while creating HTML test log file");
        }
        try
        {
        	File test = new File(this._resultSummaryPath);
            streamWriter = new BufferedWriter(new FileWriter(test, true));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println(fileNotFoundException.getStackTrace());
            throw new FrameworkException("Cannot find HTML test log file");
        }
        String projectName = "<!DOCTYPE html> \n<html> \n\t <head> \n\t\t <meta charset='UTF-8'> \n\t\t <title>"+ this._reportSettings.getProjectName()+ " - "+ this._reportSettings.getReportName()+ " Automation Execution Results</title> \n\n"+ this.GetThemeCss()+ this.GetJavascriptFunctions()+ "\t </head> \n";
        streamWriter.write(projectName);
        streamWriter.flush();
        streamWriter.close();
    }

    @Override
    public void UpdateResultSummary(String scenarioName, String testcaseName, String testcaseDescription, String executionTime, String testStatus) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
            String strArrays = "\t\t\t\t <tr class='content' > \n\t\t\t\t\t <td class='justified'>"+ scenarioName+ "</td> \n\t\t\t\t\t <td class='justified'><a href='"+ scenarioName+ "_"+ testcaseName+ ".html' target='about_blank'>"+ testcaseName+ "</a></td> \n\t\t\t\t\t <td class='justified'>"+ testcaseDescription+ "</td> \n\t\t\t\t\t <td>"+ executionTime+ "</td> \n";
            String str = strArrays;
            str = (!testStatus.toLowerCase().trim().equals("passed") ? str+ "\t\t\t\t\t <td class='fail'>"+ testStatus+ "</td> \n\t\t\t\t </tr> \n" : str+ "\t\t\t\t\t <td class='pass'>"+ testStatus+ "</td> \n\t\t\t\t </tr> \n");
            streamWriter.write(str);
            streamWriter.flush();
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while updating HTML result summary");
        }
    }

    @Override
    public void UpdateTestLog(String stepNumber, String stepName, String stepDescription, Status stepStatus, String screenShotName) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
            String lower = "\t\t\t\t <tr class='content' id='"+ this._currentSection+ this._currentSubSection+ this._currentContentNumber+ "'> \n\t\t\t\t\t <td>"+ stepNumber+ "</td> \n\t\t\t\t\t <td class='justified'>"+ stepName+ "</td> \n\t\t\t\t\t <td class='justified'>"+ stepDescription+ "</td> \n";
            String str = lower;
            HtmlReport htmlReport = this;
            htmlReport._currentContentNumber = htmlReport._currentContentNumber + 1;
            if (!(stepStatus.equals(Status.FAIL) ? false : !stepStatus.equals(Status.WARNING)))
            {
                if (!this._reportSettings.TakeScreenshotFailedStep)
                {
                    lower = str+ "\t\t\t\t\t <td class='"+ stepStatus.toString().toLowerCase()+ "'>"+ stepStatus+ "</td> \n";
                    str = lower;
                }
                else
                {
                    lower = str+ "\t\t\t\t\t <td class='"+ stepStatus.toString().toLowerCase()+ "'><a href='..\\Screenshots\\"+ screenShotName+ "'>"+ stepStatus+ "</a></td> \n";
                    str = lower;
                }
            }
            else if (stepStatus.equals(Status.PASS))
            {
                if (!this._reportSettings.TakeScreenshotPassedStep)
                {
                    lower = str+ "\t\t\t\t\t <td class='"+ stepStatus.toString().toLowerCase()+ "'>"+ stepStatus+ "</td> \n" ;
                    str = lower;
                }
                else
                {
                    lower = str+ "\t\t\t\t\t <td class='"+ stepStatus.toString().toLowerCase()+ "'><a href='..\\Screenshots\\"+ screenShotName+ "'>"+ stepStatus+ "</a></td> \n";
                    str = lower;
                }
            }
            else if (!stepStatus.equals(Status.SCREENSHOT))
            {
                lower = str+ "\t\t\t\t\t <td class='"+ stepStatus.toString().toLowerCase()+ "'>"+ stepStatus+ "</td> \n" ;
                str = lower;
            }
            else
            {
                lower = str+ "\t\t\t\t\t <td class='"+ stepStatus.toString().toLowerCase()+ "'><a href='..\\Screenshots\\"+ screenShotName+ "'>"+ stepStatus+ "</a></td> \n" ;
                str = lower;
            }
            str = str+ "\t\t\t\t\t <td><small>"+ Util.GetCurrentFormattedTime(this._reportSettings.getDateFormatString())+ "</small></td> \n\t\t\t\t </tr> \n";
            streamWriter.write(str);
            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while updating HTML test log");
        }
    }

	@Override
	public void UpdateTestLog(int totalPassCount, int totalFailCount, int totalRunCount, long totalRunTime, List<String> testNameList, List<String> exceptionList, List<String> messageList) throws FrameworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddTestLogSubHeading(Float subHeading1) throws FrameworkException {
		// TODO Auto-generated method stub
		
	}
}
