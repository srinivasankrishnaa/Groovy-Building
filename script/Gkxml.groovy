package  com.bhanu.master.xml
import groovy.xml.*
import com.bhanu.master.excel.Gexcel;
import com.bhanu.master.file.Gfileprocess
import org.ietf.jgss.GSSException;

class Gxmlprocess {
    def obj1 = new Gfileprocess()
    def exobj=new Gexcel()
    def rdr

    def loadxmlfile(String filename) {
        //xmlfile=new File(filename)
        obj1.loadfile(filename)
        rdr = new XmlSlurper().parse(obj1.rfile)
    }
    def xmltxtparse(String txt)
    {
        rdr = new XmlSlurper().parseText(txt)
    }

    def readxmlfile() { obj1.rfile.eachLine { println it } }

    def writexmlfile(String filename) { obj1.writefile(filename) }

    def xmlserialize(Boolean print, Boolean savefile)
    {
        //xmlserialize(false,true)
        def out = XmlUtil.serialize(rdr)

        if (print == true) {println out}      //Serialized Output On scree
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

    }

    def xmlinsert(String path,String refvar,String refval,String updtrvar,String updtrval) {
    //xmlinsert("make","Company","Mercedes","Owner","Bhanuchander")
        def root=rdr
        def outputBuilder = new StreamingMarkupBuilder()
       root."$path".find {(it.@"$refvar" == refval)}.@"$updtrvar" = updtrval
        //rdr.make.find {(it.@Company == 'Mercedes')}.@Added = 'Bhanuchander'
        String result = outputBuilder.bind { mkp.yield root }
        xmltxtparse(result)
        return result
    }
    def xmlrep(String path,String refvar,String refval,String updtrvar,String updtrval) {
        //xmlinsert("make","Company","Mercedes","Owner","Bhanuchander")
        def root=rdr
        def outputBuilder = new StreamingMarkupBuilder()
        root."$path".findAll {(it.@"$refvar" == refval)}."$updtrvar" = updtrval
        //rdr.make.find {(it.@Company == 'Mercedes')}.@Added = 'Bhanuchander'
        String result = outputBuilder.bind { mkp.yield root }
        xmltxtparse(result)
        return result
    }

}