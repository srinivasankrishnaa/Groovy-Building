import jxl.*
import jxl.write.*

//to read from excel
//Workbook excelFile= Workbook.getWorkbook(new File("/home/krishnan/Desktop/testexcel.xlsx"))
//Sheet sheet = excelFile.getSheet('Sheet1')
//Cell a1 = sheet.getCell(0,0)
//log.info a1 // getCell(row,column) -- place some values in myfile.xls
//Cell b2 = sheet.getCell(1,1)  // then those values will be acessed using a1, b2 & c3 Cell.
//Cell c2 = sheet.getCell(2,1)
//String s1 = a1.getContents().toString()
//println s1
//log.info s1

//Workbook excelFile= Workbook.getWorkbook("/home/krishnan/Desktop/testexcel.xlsx")
/*
Workbook excelFile= Workbook.getWorkbook(new File('/home/krishnan/Desktop/testexcel.xlsx'))
Sheet inputSheet = excelFile.getSheet("Sheet1")
println inputSheet.getCell(0,0).toString()
excelFile.close()*/
/*
// To write in excel
WritableWorkbook workbook1 = Workbook.createWorkbook(new File("/home/krishnan/Desktop/testexcel.xlsx"))
WritableSheet sheet1 = workbook1.createSheet("Worksheet Number 1", 0)
//log.info(sheet1.isHidden())
Label label = new Label(0, 1, "some data to put")
sheet1.addCell(label)
workbook1.write()
workbook1.close()*/

Workbook rdworkbook = Workbook.getWorkbook(new File("/home/krishnan/Desktop/test.xls"))

WritableWorkbook wrworkbook = Workbook.createWorkbook(new File("/home/krishnan/Desktop/testexcel-new.xls"))

Sheet rdsheet = rdworkbook.getSheet(0)
WritableSheet wrsheet = wrworkbook.createSheet("Worksheet 1", 0)

//Workbook workbook = Workbook.getWorkbook(new File("myfile.xls"))
WritableWorkbook copy = Workbook.createWorkbook(new File("/home/krishnan/Desktop/testexcel-new.xls"),rdworkbook)


//rdsheet.getRows()
//rdsheet.getColumns()

for(int i=0;i<rdsheet.getRows();i++)
{

    for(int j=0;j<rdsheet.getColumns();j++) {

//sheet1.getCell(0,2) // getCell(row,column) -- place some values in myfile.xls
//Cell b2 = sheet1.getCell(2,2)  // then those values will be acessed using a1, b2 & c3 Cell.
//Cell c2 = sheet1.getCell(2,1)
//log.info a1.getContents()
//log.info b2.getContents()
//log.info c2.getContents()
        //workbook1.close()

        String str=rdsheet.getCell(j, i).getContents().toString()
        //if(str=='RollsRoyce') str='I Have Modified The Data'
        wrsheet.addCell(new Label(j, i,str))

    }
}

wrworkbook.write()
wrworkbook.close()
rdworkbook.close()


class Gexcelwrite {

    Workbook rdworkbook
    WritableWorkbook wrworkbook

    def xlsxtoxls()
    {

    }






}
