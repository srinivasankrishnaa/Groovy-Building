package  com.bhanu.master.excel

//import com.sun.rowset.internal.Row
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import com.bhanu.master.file.Gfileprocess

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

//import org.apache.poi.xssf.usermodel.XSSFWorkbook

class Gexcel {

    def wrexcel=new Gfileprocess()

    def workbook
    def sheet
    def cell
    def row
    def column
    public int rowcount
    public int colcount

    //Write Excel
    Workbook wrworkbook = new XSSFWorkbook()
    String writefilename
    Sheet wrsht
    //int rowrcv=0
    //Row rowobjcpy=wrsht.createRow(rowrcv)

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
//        println filename
        FileInputStream file = new FileInputStream(new File(filename))
        if(filename.endsWith(".xlsx")) {
        //    println '2007'
            excel2007(file)
        }
        else if (filename.endsWith(".xls")) {
        //    println '2003'
            excel2003(file)
        }

        getrowcolcount(sheetname)
    }

    def getrowcolcount(String sheetname) {
        sheet = sheetname                                              //Assign The Sheet Name Directly
        colcount = workbook.getSheet(sheet).getRow(0).getLastCellNum()//Header And Also As Col Count
        rowcount = workbook.getSheet(sheet).getPhysicalNumberOfRows()
    }

    def gcell(int row, int col) {
        if(workbook.getSheet(sheet).getRow(row).getCell(col)==null) {return "".toString()}
        else {return workbook.getSheet(sheet).getRow(row).getCell(col).toString()}
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
            wrexcel.wfile.append(tmparry.join(',')+"\n")//Write Line By Line To File
            tmparry=[]                              //Empty The Array For Each Row
        }
        //String pth=wrexcel.wfile.path
        println "File Generated Successfully in ... "+wrexcel.wfile.path
    }

    List getsheetnames(String filename)
{
    FileInputStream file = new FileInputStream(new File(filename))
    if(filename.endsWith(".xlsx")) {
        //    println '2007'
        excel2007(file)
    }
    else if (filename.endsWith(".xls")) {
        //    println '2003'
        excel2003(file)
    }

    List<String> sheetNames = new ArrayList<String>()
    for (int i=0; i<workbook.getNumberOfSheets(); i++) {
        sheetNames.add( workbook.getSheetName(i) )
    }
    return sheetNames
}


    def writeexcel(String wrflname,String wrsheetnm)
    {
        println "Entry..."
        writefilename=wrflname

        //Workbook workbook = WorkbookFactory.create(new File(wrflname))
        wrsht = wrworkbook.createSheet(wrsheetnm)

        /*
        HSSFWorkbook wrworkbook
        //File file = new File(context.getExternalFilesDir(null), wrflname)
        File file = new File(wrflname)
        //FileOutputStream fileOut = new FileOutputStream(file)

        if(file.exists())
        {
            println "File Exist"
            InputStream iss= new FileInputStream(file)
            wrworkbook = new HSSFWorkbook(iss)
            HSSFSheet sheet2 = wrworkbook.createSheet(wrsheetnm)

            sheet2.createRow(1).createCell(1).setCellValue("Bhanuchander")

            //is.close()
            wrworkbook.write()
            wrworkbook.close()

        }
        else
        {
            //wrworkbook = new HSSFWorkbook()
            println "File Does No Exist"
        }

*/


        /*if (file.exists()) {
            try {
                wrworkbook = (HSSFWorkbook)WorkbookFactory.create(file)
            }
            catch (Exception e) {
                e.printStackTrace()
            }
            HSSFSheet sheet2 = wrworkbook.createSheet(wrsheetnm)
            sheet2.createRow(1).createCell(1).setCellValue("Gokulakrishnan")
        }
        else{
            wrworkbook = new HSSFWorkbook()
            HSSFSheet sheet2 = wrworkbook.createSheet(wrsheetnm)

            //XSSFRow.
            sheet2.createRow(1).createCell(1).setCellValue("Bhanuchander")
        }*/


        //wrworkbook.write(fileOut)
        //fileOut.close()
    }


    def rowset(int rownum)
    {
        Row rowobj=wrsht.createRow(rownum)

        //rowobjcpy=rowobj
        println ""

    }

    def writecell(int row,int col,String str)
    {
        wrsht.createRow(row).createCell(col).setCellValue(str)
        /*Row rr=wrsht.createrow(row)

        arr.eachWithIndex{String it,int cl->
            rr.createCell(cl).setCellValue(it)
        }*/

    }

    def wrflush()
    {
        FileOutputStream fileOut = new FileOutputStream(writefilename)
        wrworkbook.write(fileOut)
        fileOut.close()
        wrworkbook.close()
    }


}
//Usage
/*def poochi =new Gexcel()
poochi.loadexcelfile("/home/krishnan/Desktop/readex1.xlsx","Sheet1")
poochi.exceltocsv("/home/krishnan/Desktop/Bhanuexcel.csv")*/