package DriverFactory;

import org.openqa.selenium.WebDriver;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
static WebDriver driver;

public void ERP_Account()throws Throwable{
//creating reference object for excel util methods
ExcelFileUtil xl= new ExcelFileUtil();
// iterating all row in masterTestcases sheet
for(int i=1;i<=xl.rowCount("MasterTestCases");i++){
if(xl.getCellData("MasterTestCases",i, 2).equalsIgnoreCase("Y")){
// store module name into TcModule	
String TcModule= xl.getCellData("MasterTestCases", i, 1);
//iteratate all rows in TcModule sheet
for (int j=1;j<=xl.rowCount(TcModule);j++){
	//read all the columns from TC Module
String Description= xl.getCellData(TcModule, j, 0);
String Function_Name= xl.getCellData(TcModule, j, 1);
String Locator_Type= xl.getCellData(TcModule, j, 2);
String Locator_Value= xl.getCellData(TcModule, j, 3);
String Test_Data= xl.getCellData(TcModule, j, 4);
try
{
if(Function_Name.equalsIgnoreCase("startBrowser")){
driver= FunctionLibrary.startBrowser();
}
else if (Function_Name.equalsIgnoreCase("openApplication")){
FunctionLibrary.openApplication(driver);
}
else if(Function_Name.equalsIgnoreCase("waitForElement"))
{
FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
}
else if(Function_Name.equalsIgnoreCase("typeAction")){
FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
}
else if (Function_Name.equalsIgnoreCase("clickAction")){
FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
}else if (Function_Name.equalsIgnoreCase("closeBrowser"))
{
FunctionLibrary.closeBrowser(driver);
}
//write as pass into status column
xl.setCellData(TcModule, j, 5, "Pass");
xl.setCellData("MasterTestCases", i, 3, "Pass");

}
catch(Throwable t)
{
	System.out.println("Exception Handled");
	xl.setCellData(TcModule, j, 5, "Fail");
	xl.setCellData("MasterTestCases", i, 3, "Fail");
}
}
}
else
{
//write as not executed into status in masterTestcases
 xl.setCellData("MasterTestCases", i, 3, "Not Executed");
}
}
}
}
