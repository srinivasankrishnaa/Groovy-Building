package  com.bhanu.master.xml
import groovy.xml.*
import com.bhanu.master.excel.Gexcel;
import com.bhanu.master.file.Gfileprocess
import org.ietf.jgss.GSSException;

class Gxmlprocess {

    def obj1 = new Gfileprocess()
    def exobj=new Gexcel()
    public def rdr

    def loadxmlfile(String filename) {
        //xmlfile=new File(filename)
        //obj1.loadfile(filename)
        rdr = new XmlSlurper().parse(filename)
    }
    def xmltxtparse(String txt)
    {
        rdr = new XmlSlurper().parseText(txt)
        //println rdr
    }

    def readxmlfile() { obj1.rfile.eachLine { println it } }

    def writexmlfile(String filename) { obj1.writefile(filename) }

    def xmlserialize(Boolean print, Boolean savefile)
    {
        //xmlserialize(false,true)
        def out = XmlUtil.serialize(rdr)

        if (print == true) {println out}      //Serialized Output On screen
        if (savefile == true) {               //Redirecting The Output To The File
            try {
                obj1.wfile.append(out)
            }
            catch (Exception ex) {
                println "Write File Not Specified...\nTerminating..."; return 0
            }

            def pth = obj1.wfile.path.toString()
            println "File Generated Successfully in $pth ..."
        }
        return out
    }

    def xmlinsertorreplace(String path,String refvar,String refval,String updtrvar,String updtrval,boolean insrt_true_or_replace_false) {
    //xmlinsert("make","Company","Mercedes","Owner","Bhanuchander")
        def root=rdr
        def outputBuilder = new StreamingMarkupBuilder()

        println refvar+":"+refval
        println updtrvar+":"+updtrvar

       if(insrt_true_or_replace_false==true) {root."$path".findAll { (it.@"$refvar" == "$refval") }.@"$updtrvar" = updtrval}  //xml Insert
        else {root."$path".findAll {(it.@"$refvar" == "$refval")}.@"$updtrvar" = updtrval}                                  //xml Replace
        //rdr.make.find {(it.@Company == 'Mercedes')}.@Added = 'Bhanuchander'
        String result = outputBuilder.bind { mkp.yield root }
        println result
        xmltxtparse(result)

        obj1.wfile.write(xmlserialize(false,false))
        return xmlserialize(false,false)

    }
    /*def xmlrep(String path,String refvar,String refval,String updtrvar,String updtrval) {
        //xmlinsert("make","Company","Mercedes","Owner","Bhanuchander")
        def root=rdr
        def outputBuilder = new StreamingMarkupBuilder()
        root."$path".findAll {(it.@"$refvar" == refval)}."$updtrvar" = updtrval
        //rdr.make.find {(it.@Company == 'Mercedes')}.@Added = 'Bhanuchander'
        String result = outputBuilder.bind { mkp.yield root }
        xmltxtparse(result)
        return xmlserialize(result)
    }*/

    //===================================================================================================================================================
    //Usage Code





}//End Of Class