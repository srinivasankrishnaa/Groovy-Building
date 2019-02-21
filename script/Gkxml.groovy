package  com.bhanu.master.xml

import com.sun.org.apache.xpath.internal.res.XPATHErrorResources
import groovy.xml.*
import com.bhanu.master.excel.Gexcel;
import com.bhanu.master.file.Gfileprocess
import javax.xml.xpath.*

import javax.xml.parsers.DocumentBuilderFactory

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument
import org.ietf.jgss.GSSException;

class Gxmlprocess {

    def obj1 = new Gfileprocess()
    def exobj = new Gexcel()
    public def rdr
    def arrpth = []
    //def node
    def param=[:]


    def loadxmlfile(String filename) {
        //xmlfile=new File(filename)
        //obj1.loadfile(filename)
        rdr = new XmlSlurper().parse(filename)
        //rdr = new XmlParser().parse(filename)
    }

    def xmltxtparse(String txt) {
        rdr = new XmlSlurper().parseText(txt)
        //println rdr
    }

    def readxmlfile() { obj1.rfile.eachLine { println it } }

    def writexmlfile(String filename) { obj1.writefile(filename) }

    def xmlserialize(Boolean print, Boolean savefile) {
        //xmlserialize(false,true)
        def out = XmlUtil.serialize(rdr)

        if (print == true) {
            println out
        }      //Serialized Output On screen
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

    def xmlinsertorreplace(def path, String refvar, String refval, String updtrvar, String updtrval, boolean insrt_true_or_replace_false) {

        //xmlinsert("make","Company","Mercedes","Owner","Bhanuchander")
        def root = rdr

       /* def arr1=["make","@Company"]

        arr1.each {

            root=root."${it}"

        }

        println root.name()//.replaceBody arr1[0]*/
/*
        def xmlString = '''<Cars Origin='Germany'>
  <make Company='Mercedes'>
    <Model>D220</Model>
<hi name='Gayathri'>
<rail age='15'>Berlin</rail>
</hi>
    <Price>25Lakhs</Price>
    <Type>Sport</Type>
  </make>
  <make Company='BMW'>
    <Model>New</Model>
    <Price>23Lakhs</Price>
    <Type>Sport</Type>
  </make>
<make Company='RollsRoyce'>
    <Model>Phanthom IV</Model>
    <Price>1cr</Price>
    <Type>Luxury</Type>
  </make>
</Cars>'''


        //def xml = new XmlSlurper().parse("/home/krishnan/Desktop/Bhanutesting.xml")
        def xml = new XmlSlurper().parseText(xmlString)

        //node=xml
        param["make.hi.rail"]="Berlin"

        param.each { key,value ->
            def node = xml

            key.split("\\.").each {
                node = node."${it}"
            }

            node.replaceBody value
        }

        //println XmlUtil.serialize(xml)
*/


        /*def xmlString = '''<soapenv:Envelope   xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Header/>
  <soapenv:Body>
      <web:GetWeather xmlns:web="http://www.webserviceX.NET">
          <web:CityName>Test</web:CityName>
          <web:CountryName>Test</web:CountryName>
      </web:GetWeather>
  </soapenv:Body>
</soapenv:Envelope>'''
*/



    /*    def xml = new XmlSlurper().parse("/home/krishnan/Desktop/Bhanutesting.xml")
        def param = [:]
// since envelope is the root node it's not necessary
        param["make.hi.rail.attribute('age')"] = "25"
        //param["Body.GetWeather.CountryName"] = "Germany"

        //def xml = new XmlSlurper().parseText(xmlString)

        param.each { key,value ->
            def node = xml

            key.split("\\.").each {

                node = node."${it}"
            }
            println node.name()
            node.replaceBody value

            println node
        }
        println XmlUtil.serialize(xml)

*/





        //def node=root

        //root.make.hi.@name="Saravanan"

       /* arrpth = path.tokenize("/")

        arrpth.each {
          //  node = node."${it}"
        }*/

        //println node

        /*def  str=["make"]
        str.each {nm->

            rdr = rdr[nm]
        }
        println rdr*/




        def outputBuilder = new StreamingMarkupBuilder()

        //println refvar+":"+refval
//Preserve
    /*def nrt=root
        path.each {pth->
           nrt=nrt."${it}"


        }

        println nrt*/
        //String str1="root.make.hi.@name"
        //def str2=str1.text
        def str3=''
        path.split("\\.").each {

        }


        if (insrt_true_or_replace_false == true) {
           // println "${path}"
            //"${root.make.hi}".findAll { (it.@"$refvar" == "$refval") }.@"$updtrvar" = updtrval
        }  //xml Insert
        else {
            println "Entry"
            root."${nrt}".findAll { (it.@"$refvar" == "$refval") }."$updtrvar" = updtrval
        }                                  //xml Replace





       // def param = [:]
// since envelope is the root node it's not necessary
        //param["make.hi.rail"]="Berlin/chennai"
        //param["make.hi.rail.@age"]="15/16"
        //param["make.hi.@name"] = "Germany"
        //param["make.hi.rail.@age"] = "America"

        //param["Body.GetWeather.CountryName"] = "Germany"

        //def xml = new XmlSlurper().parseText(xmlString)

      /*  param.each { key,value ->
            def node = root

            key.split("\\.").each {

                node = node."${it}"
                //println node.name()
                //println node
                                if (insrt_true_or_replace_false == true) {
                    root.node.findAll { (it.@"$refvar" == "$refval") }.@"$updtrvar" = updtrval
                }  //xml Insert
                else {
                    node.findAll { (it.@"$refvar" == "$refval") }."$updtrvar" = updtrval
                }
        def arr
                arr=value.toString().tokenize("/")
                if(node==arr[0])
                {
                    node.replaceBody arr[1]
                }
                else
                {
                    //println "!!!!!!!!!!!!!"
                }

            }
            println node.name()*/
            //println node.text()+"=>"



//            println node.name
        //}

        //println XmlUtil.serialize(rdr)

                                     //xml Replace



        /*List vals = []
        //root."**".each{
        root.make.each{v->//p
            //println v//  iprinltn t.name() + "---"+ren()=='')//println it.children()
            v.each { va ->
                vals << va.name()
            }
        }
         vals.unique().each{println it}*/
        //println "GHello"

                                 //rdr.make.find {(it.@Company == 'Mercedes')}.@Added = 'Bhanuchander'
       String result = outputBuilder.bind { mkp.yield rdr }
                             //println result
       xmltxtparse(result)

                                //obj1.wfile.write(lserialize(false,false))
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

def poochi =new com.bhanu.master.xml.Gxmlprocess()

poochi.loadxmlfile("/home/krishnan/Desktop/Bhanutesting.xml")
//println poochi.xmlinsertorreplace('root.make.hi.@name',"name","Gayathri","name","Preethi",true)

//def tt= poochi.rdr


/*def xpath = XPathFactory.newInstance().newXPath()


def builder     = DocumentBuilderFactory.newInstance().newDocumentBuilder()
def inputStream = new ByteArrayInputStream( xml.bytes )
def records     = builder.parse(inputStream).documentElement*/

//xpath.evaluate( '/make/hi/rail', tt )
/*
def testxml = '''
    <records>
    
      <car name="HSV Maloo" make="Holden" year="2006">
        <country>Australia</country>
        <record type="speed">Production Pickup Truck with speed of 271kph</record>
      </car>
      <car name="220d" make="Mercedes" year="2006">
        <country>Germany</country>
        <record type="speed">Production Pickup Truck with speed of 271kph</record>
      </car>
      
    </records>
  '''

def processXml( String xml, String xpathQuery ) {
    def xpath = XPathFactory.newInstance().newXPath()
    def builder     = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    def inputStream = new ByteArrayInputStream( xml.bytes )
    def records     = builder.parse(inputStream).documentElement
    xpath.evaluate( xpathQuery, records )
}
//rdr2 = new XmlSlurper().parseText("/home/krishnan/Desktop/Bhanutesting.xml")

//File fl= new File("/home/krishnan/Desktop/Bhanutesting.xml")


println processXml(testxml, '/records/car/country')*/

/*
def xmlString = '''<soapenv:Envelope   xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Header/>
  <soapenv:Body>
      <web:GetWeather xmlns:web="http://www.webserviceX.NET">
          <web:CityName>Test</web:CityName>
          <web:CountryName>Test</web:CountryName>
      </web:GetWeather>
  </soapenv:Body>
</soapenv:Envelope>'''

def param = [:]
// since envelope is the root node it's not necessary
param["Body.GetWeather.CityName"] = "Berlin"
param["Body.GetWeather.CountryName"] = "Germany"

def xml = new XmlSlurper().parseText(xmlString)

param.each { key,value ->
    def node = xml

    key.split("\\.").each {

        node = node."${it}"
    }

    node.replaceBody value
}

println XmlUtil.serialize(xml)

*/
def xpath(def n,def r){
    n.split('\\.').inject(r) { r1, n1 ->  r1."$n1"}
}

def nm=[]
def val=[]

String nodepath="make.@Company"
def childrenlist =[]

//xpath(nodepath,poochi.rdr).children().each{
xpath(nodepath,poochi.rdr).each{
    //nm<<it.name()
    //val<<it.text()

    //println it

    if(it.name()=="Company")
    {
        //println it
        if(it.text()=="Mercedes")
        {
            nm<<it.name()
            val<<it.text()
        }
    }
}

nm.eachWithIndex{ i,j->
    println nm[j]+"="+val[j]
 }
    //!childrenlist.contains(nodepath+'.'+it.name()) ? childrenlist.add(nodepath+'.'+it.name()): {}}
//println  childrenlist



