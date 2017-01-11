package reporting.framework.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import reporting.framework.utilities.FrameworkException;
import reporting.framework.utilities.Util;

public class TestRunnerReport implements ReportType{
	
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
    
    private ArrayList<ReportType> _reportTypes = new ArrayList<ReportType>();
    
    public TestRunnerReport(ReportSettings reportSettings, ReportTheme reportTheme)
    {
        this._reportSettings = reportSettings;
        this._reportTheme = reportTheme;
        String reportPath = reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results" + Util.GetFileSeparator() + reportSettings.getReportName() + ".html";
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
        	String html = "\t\t\t\t <div class='header.message'> \n\t\t\t\t\t <h1> \n\t\t\t\t\t\t "+ heading.toUpperCase()+ " \n\t\t\t\t\t </h1> \n\t\t\t\t </div> \n";
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
        	String html = "\n\t\t <div class='header.message'> \n\t\t\t\t\t <h1> \n\t\t\t\t\t\t "+ heading+ " \n\t\t\t\t\t </h1> \n\t </div> \n";
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
            String strArrays = str1+ "\t\t\t <tbody> \n\t\t\t\t <tr class='section'> \n\t\t\t\t\t <td colspan='5' onclick=\"toggleMenu('"+ this._currentSection+ "')\">+ "+ section+ "</td> \n\t\t\t\t </tr> \n\t\t\t </tbody> \n\t\t\t <tbody id='"+ this._currentSection+ "' style='display:inline'> \n" ;
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
    public void AddTestLogSubHeading(Float subHeading1) throws FrameworkException
    {
        try
        {
        	String barColor = null;
        	
        	if (subHeading1 <= 25.99999999)
        	{
        		barColor = "progress-bar progress-bar-danger progress-bar-striped active";
        	}
        	if (subHeading1 >= 26 && subHeading1 <= 50.99999999)
        	{
        		barColor = "progress-bar progress-bar-warning progress-bar-striped active";
        	}
        	if (subHeading1 >= 51 && subHeading1 <= 75.999999999)
        	{
        		barColor = "progress-bar progress-bar-info progress-bar-striped active";
        	}
        	if (subHeading1 >= 76)
        	{
        		barColor = "progress-bar progress-bar-success progress-bar-striped active";
        	}
        	
        	
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String formattedFloat = String.format(java.util.Locale.US, "%.2f", subHeading1);
        	//String strArrays = "\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <th>&nbsp;"+ subHeading1.replace(" ", "&nbsp;")+ "</th> \n\t\t\t\t\t <th></th> \n\t\t\t\t </tr> \n";
        	String strArrays = "\n <div class='subheading container'> \n\t\t\t\t <h2>Test Case Pass Rate</h2> \n\t\t\t\t <div class='progress'> \n\t\t\t\t <div class='" + barColor + "' \t\t\t\t role='progressbar' \t\t\t\t aria-valuenow='" + formattedFloat + "' \t\t\t\t aria-valuemin='0' \t\t\t\t aria-valuemax='100' \t\t\t\t style='width:" + formattedFloat + "%'> " + formattedFloat + "% \t\t\t\t</div> \n </div> \n </div> \n";
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
        	String html = "\t\t\t <div> \n\t\t\t\t <div class='result.heading'> \n\t\t\t\t\t <p>Test Run Results: </p> \n\t\t\t\t\t</div>\n\t\t\t\t\t</div> \n\n";
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
        	String html = "\n <body> \n <div id='header'> \n\t\t\t </div> /n";
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
        	String html = "\n\t\t <div id='main'> \n\t\t\t </div> \n\n";
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
        	String html = "\t <body> \n\t\t <div id='header'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <div> \n";
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
        	String html = "\t\t\t </div> \n\t\t </div> \n\n\t\t <div id='main'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 8%' /> \n\t\t\t\t <col style='width: 12%' /> \n\t\t\t\t <col style='width: 62%' /> \n\t\t\t\t <col style='width: 8%' /> \n\t\t\t\t <col style='width: 10%' /> \n\t\t\t </colgroup> \n\n";
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

    @SuppressWarnings("unused")
	private String GetJavascriptFunctions()
    {
        return "";
    }

    private String GetThemeCss()
    {
        String Body = Css.getBody().toString();
        String Subheading = Css.getDivSubHeading().toString();
        String Section = Css.getDivSection().toString();
        String Subsection = Css.getDivSubSection().toString();
        String Content = Css.getTrContent().toString();
        String H1 = Css.getH1().toString();
        String H2 = Css.getH2().toString();
        String para = Css.getP().toString();
        String Table = Css.getTable().toString();
        String Th = Css.getTh().toString();
        String Td = Css.getTd().toString();
        String Button = Css.getButtonAccordion().toString();
        String ButtonShow = Css.getAccordionShow().toString();
        String Panel = Css.getPanel().toString();
        String Show = Css.getPanelShow().toString();
        String H3 = Css.getH3().toString();
        String ActiveBtn = Css.getBtnAccordionActive().toString();
        String ButtonHover = Css.getBtnHover().toString();
        
        String fullCss = Body + Subheading + Section + Subsection + Content + H1 + H2 + para + Table + Th + Td + Button + ButtonShow + Panel + Show + H3 + ActiveBtn + ButtonHover;
        String noFirstBracket = fullCss.replace("[", "");
        String nocommas = noFirstBracket.replace(",", "");
        String finalFullCss = nocommas.replace("]", "");
        return finalFullCss;
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
        String projectName = "<!DOCTYPE html> \n<html> \n\t <head> \n\t\t <meta charset='UTF-8'> \n\t\t <title>"+ this._reportSettings.getProjectName()+ " - Automation Execution Results Summary</title> \n\n <style type='text/css'> \t\t"+ this.GetThemeCss()+ " </style> \t \n\t\t<meta name='viewport' \t\t\t\t\t content='width=device-width, initial-scale=1' \t\t\t\t\t > \n\t\t\t\t\t <link rel='stylesheet' \t\t\t\t\t href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' \t\t\t\t\t > \n\t\t\t\t\t </head> \n";
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
        String projectName = "<!DOCTYPE html> \n<html> \n\t <head> \n\t\t <meta charset='UTF-8'> \n\t\t <title>"+ this._reportSettings.getProjectName()+ " - Automation Execution Results Summary</title> \n\t\t\t\t <meta name='viewport' \t\t\t\t\t content='width=device-width, initial-scale=1' \t\t\t\t\t > \n\t\t\t\t\t <link rel='stylesheet' \t\t\t\t\t href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' \t\t\t\t\t > \n\t\t\t \n\n <style type='text/css'> \t\t"+ this.GetThemeCss()+ " </style> \t \n\t\t </head> \n";
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
            String strArrays = "\t\t\t\t <tr class='content' > \n\t\t\t\t\t <td class='justified' colspan='5'>"+ scenarioName+ "</td> \n\t\t\t\t\t <td class='justified'><a href='"+ scenarioName+ "_"+ testcaseName+ ".html' target='about_blank'>"+ testcaseName+ "</a></td> \n\t\t\t\t\t <td class='justified'>"+ testcaseDescription+ "</td> \n\t\t\t\t\t <td>"+ executionTime+ "</td> \n";
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
    public void UpdateTestLog(int totalPassCount, int totalFailCount, int totalRunCount, long totalRunTime, List<String> testNameList, List<String> exceptionList, List<String> messageList) throws FrameworkException
    {
        try
        {
        	File test = new File(this._resultSummaryPath);
        	BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, true));
        	String table = "\n <div class='content'> \n\t\t\t\t <table> \n\t\t <tbody> \n\t";
            String totalTestRun = "\n\t\t\t <tr> \n <th> Total Tests: </th> \n\t\t\t\t\t <td>"+ totalRunCount +"</td> \n\t\t\t\t\t </tr> \n ";
            String totalPass = "\n <tr> \n\t\t\t <th>Total Passed:</th> \n\t\t\t\t\t <td>"+ totalPassCount+ "</td> \n\t\t\t\t\t </tr> \n ";
            String totalFails = "\n <tr> \n\t\t\t <th>Total Failed:</th> \n\t\t\t\t\t <td>"+ totalFailCount+ "</td> \n\t\t\t\t\t </tr> \n ";
            String totalRuntime = "\n <tr> \n\t\t <th>Total Runtime:</th> \n\t\t\t\t\t <td>"+ totalRunTime+ "</td> \n\t\t </tr> </tbody> \n\t\t </table> \n </div> \n\n";
            String button = "";
            String accordianContent = "";
            String newContentItem = "";

            String str = table + totalTestRun + totalPass + totalFails + totalRuntime;
            TestRunnerReport htmlReport = this;
            htmlReport._currentContentNumber = htmlReport._currentContentNumber + 1;
            
            //str = str+ "\t\t\t\t\t <td><small>"+ Util.GetCurrentFormattedTime(this._reportSettings.getDateFormatString())+ "</small></td> \n\t\t\t\t </tr> \n";
            streamWriter.write(str);
            
            if (testNameList.size() != 0)
            {
            	newContentItem = "\n <div class='result.heading'> \n\t\t <p>Test Run Results: </p> \n\t\t </div>  \n\t\t\t <div class='content'> \n\t\t\t\t";
            	String Js = "<script> \n\t\t\t\t var acc = document.getElementsByClassName('accordion'); \n\t\t\t\t var i; \n\t\t\t\t for (i = 0; i < acc.length; i++) \n\t\t\t\t {acc[i].onclick = function(){ \n\t\t\t\t this.classList.toggle('active'); \n\t\t\t\t this.nextElementSibling.classList.toggle('show');}} \n\t\t\t\t </script> \n\t\t\t\t </div> \n\t\t\t\t ";
            	streamWriter.write(newContentItem);
            	for(int i = 0; i < testNameList.size(); ++i)
            	{
            		button = "\n\t\t\t\t <button class='accordion'> \n\t\t\t\t <b>Test Case Name &amp; Class:</b> \n\t\t\t\t" + testNameList.get(i) + " \t\t\t\t</button> \n\t\t\t\t";
            		accordianContent = "\n\t\t\t\t <div class='panel'> \n\t\t\t\t <h3> \t\t <b>Exception Thrown:</b> " + exceptionList.get(i) + " <br> \n\t\t\t\t <b> Message:</b> " + messageList.get(i) + "</h3> \n\t\t\t\t </div> \n\t\t\t\t";
            		streamWriter.write(button + accordianContent);
            	}
            	streamWriter.write(Js);
            }

            streamWriter.close();
        }
        catch (IOException oException)
        {
            System.out.println(oException.getStackTrace());
            throw new FrameworkException("Error while updating HTML test log");
        }
    }
    
    public void InitializeReportTypes() throws IOException
    {
        /*if (this._reportSettings.GenerateExcelReports)
        {
            Directory.CreateDirectory(string.Concat(this._reportSettings.ReportPath, Util.GetFileSeparator(), "Excel Results"));
            ExcelReport excelReport = new ExcelReport(this._reportSettings, this._reportTheme);
            this._reportTypes.Add(excelReport);
        }*/
        if (this._reportSettings.GenerateHtmlReports)
        {
            new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results");
            HtmlReport htmlReport = new HtmlReport(this._reportSettings, this._reportTheme);
            this._reportTypes.add(htmlReport);
        }
        if (this._reportSettings.IncludeTestDataInReport)
        {
        	new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Datatables");
        }
        File screenshots = new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Screenshots");
        screenshots.mkdir();
        screenshots.createNewFile();
    }

	@Override
	public void UpdateTestLog(String stepNumber, String stepName, String stepDescription, Status stepStatus,
			String screenshotName) throws FrameworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddTestLogSubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4)
			throws FrameworkException {
		// TODO Auto-generated method stub
		
	}

}
