package  com.bhanu.master.excel
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import com.bhanu.master.file.Gfileprocess;

class Gexcel {

    def wrexcel=new Gfileprocess()

    def workbook
    def sheet
    def cell
    def row
    def column
    int rowcount
    int colcount

    def excel2003(FileInputStream excelfile) {
        HSSFWorkbook wb = new HSSFWorkbook(excelfile)
        workbook = wb
        HSSFCell c = cell
        HSSFRow r = row
        HSSFSheet sh = sheet
    }

    def excel2007(FileInputStream excelfile) {
        XSSFWorkbook wb = new XSSFWorkbook(excelfile)
        workbook = wb
        XSSFCell c = cell
        XSSFRow r = row
        XSSFSheet sh = sheet
    }

    def loadexcelfile(String filename, String sheetname) {
        FileInputStream file = new FileInputStream(new File(filename))
        excel2007(file)
        getrowcolcount(sheetname)
    }

    def getrowcolcount(String sheetname) {
        sheet = sheetname                                              //Assign The Sheet Name Directly
        colcount = workbook.getSheet(sheet).getRow(0).getLastCellNum()//Header And Also As Col Count
        rowcount = workbook.getSheet(sheet).getPhysicalNumberOfRows()
    }

    def gcell(int row, int col) {
        return workbook.getSheet(sheet).getRow(row).getCell(col)
    }

    def exceltocsv(String csvfilename)
    {
        wrexcel.writefile(csvfilename)
        def tmparry=[]

        for (int i=0;i<rowcount;i++)//Row Iteration
        {
            for(int j=0;j<colcount;j++)//Col Iteration
            {
                tmparry<<'"'+gcell(i, j)+'"'//Loading In To Array
            }
            wrexcel.wfile.append(tmparry.join(",")+"\n")//Write Line By Line To File
            tmparry=[]                              //Empty The Array For Each Row
        }
        //String pth=wrexcel.wfile.path
        println "File Generated Successfully in ... "+wrexcel.wfile.path
    }


}
//Usage
/*def poochi =new Gexcel()
poochi.loadexcelfile("/home/krishnan/Desktop/readex1.xlsx","Sheet1")
poochi.exceltocsv("/home/krishnan/Desktop/Bhanuexcel.csv")*/