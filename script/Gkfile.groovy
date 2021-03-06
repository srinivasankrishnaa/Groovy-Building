package  com.bhanu.master.file
class Gfileprocess {

    String rfilepath
    String wfilepath
    File rfile
    File wfile
    def dstptrnop = []
    int filelines = 0
    int ccount = 0
    Map fix = [:]
    def csvarr = []
    int pos = 0
    boolean writetoarray=false
    File colfile
    public String txtdltr=","

//ex://def ptrn=[["StructureEvent Type = "," : notificationId"],["X.733::EventType = "," | neTime "]]

    def loadfile(String filename) { rfile = new File(filename) }

    def readfile() { rfile.eachLine { println it } }

    def countlines() {
        println "Reading The File " + rfile.path.toString() + "..."
        rfile.eachWithIndex { val, num -> filelines = num }
        println "The File Contains " + filelines.toString() + " Lines..."; return filelines.toString()
    }

    def countword(String word) {
        println "Reading The File " + rfile.path.toString() + "..."
        rfile.eachLine {
            ccount = ccount + it.count(word).toInteger()
        } println "The File Contains $word " + ccount.toInteger() + " Times..."; return '"'+word+'" Is Found '+ccount.toInteger()+" Times..."; ccount = 0;
    }

    def countword(List<String> word) {//["string1","string2","string-N"]
        println "Reading The File " + rfile.path.toString() + "..."
        word.each { wrd ->
            rfile.eachLine {
                ccount = ccount + it.count(wrd).toInteger()
            } println "The File Contains $wrd " + ccount.toInteger() + " Times..."; ccount = 0
        }
    }

    def writefile(String filename) { wfile = new File(filename) }

    def distinctpattern(List<String> pttrn, String containingstring) {
        //distinctpattern([["string1","string2"]],"containingstring of the line")
        def str = []
        def op = []
        rfile.eachLine { src ->
            if (src.contains(containingstring)) {
                pttrn.each { pat ->
                    str = src.split(pat[0])
                    str = str[1].split(pat[1])
                    op << str[0]
                }
                dstptrnop << op
                op = []
            }
        }
        dstptrnop.countBy { it }.each { println it }
    }

    def getlinescontaining(String str) {
        rfile.each {
            if (it.contains(str)) {
                wfile.append(it + "\n")
            }
        }
    }

    def getlinesdoesnotcontaining(String str) {
        rfile.each {
            if (it.contains(str)) {}
            else
            {wfile.append(it + "\n")}
        }
    }

    def mappattern(Map mp, String strtline, String endline, List<String> splitdltr, String savedltr) {
        //Map fix=["HDD Device 0":"","HDD Model ID":"", "HDD Serial No":"", "HDD Revision":"", "HDD Size":"", "Interface":"", "Temperature":"", "Health":"", "Performance":"", "Power on Time":"", "Est. Lifetime":""]
        fix = mp
        int ct = 0
        def val = []
        def key, value
        boolean st = false

        fix.keySet().each { wfile.append(it.toString() + savedltr) }
        wfile.append("\n")

        rfile.eachLine { fld ->
            //println it
            //println key
            //println value
            //println value1

            if (endline == "") {

                if (fld.contains(strtline)) {

                    //st=true
                    fix.each { mapval ->
                        //println mapval
                        wfile.append(mapval.value.toString() + savedltr)
                        //println mapval.value.toString()+savedltr
                    }
                    ct = ct + 1
                    //mp==fix.keySet()
                    //fix=["HDD Device 0":"","HDD Model ID":"", "HDD Serial No":"", "HDD Revision":"", "HDD Size":"", "Interface":"", "Temperature":"", "Health":"", "Performance":"", "Power on Time":"", "Est. Lifetime":""]
                    fix = mp
                    //println mp
                    //mp=fix
                    wfile.append("\n")
                    println "Processed " + "Entries... " + ct.toInteger() + "\n============================================================"
                    //st=false
                }

            }

            if (fld.contains(strtline)) {
                st = true
            }

            if (endline != "") {

                if (fld.startsWith(endline)) {
                    //println fld+endline
                    //println "False ntry"
                    fix.each { mapval ->
                        //println mapval
                        wfile.append(mapval.value.toString() + savedltr)
                    }
                    ct = ct + 1
                    //mp==fix.keySet()
                    //fix=["HDD Device 0":"","HDD Model ID":"", "HDD Serial No":"", "HDD Revision":"", "HDD Size":"", "Interface":"", "Temperature":"", "Health":"", "Performance":"", "Power on Time":"", "Est. Lifetime":""]
                    fix = mp
                    //println mp
                    //mp=fix
                    wfile.append("\n")
                    println "Processed " + "Entries... " + ct.toInteger() + "\n============================================================"
                    st = false
                } else {
                    //println "Entry..."
                    if (st == true) {
                        splitdltr.each { sdtr ->

                            if (fld.contains(sdtr)) {
                                //println sdtr +"====="+it
                                val = (fld + " ").split(sdtr)
                                //println val
                                key = val[0].trim()//+sdtr
                                value = val[1].trim()
                                //if(value==null){value=" "}
                                //println value
                                fix[key] = '"' + value + '"'
                            }
                        }
                    }
                }
            } else {
                //println "Entry..."
                if (st == true) {
                    splitdltr.each { sdtr ->

                        if (fld.contains(sdtr)) {
                            //println sdtr +"====="+it
                            val = (fld + " ").split(sdtr)
                            //println val
                            key = val[0].trim()//+sdtr
                            value = val[1].trim()
                            //if(value==null){value=" "}
                            //println value
                            fix[key] = '"' + value + '"'
                        }
                    }
                }
            }
        }

        println "File Located At : " + wfile.path.toString()
//usage=>mappattern(["HDD Device 0":"","HDD Model ID":"", "HDD Serial No":"", "HDD Revision":"", "HDD Size":"", "Interface":"", "Temperature":"", "Health":"", "Performance":"", "Power on Time":"", "Est. Lifetime":""],"------completed------",":","^")
    }

    def getdistinctkeys(List<String> dltr, String flstrt, String flend) {
        def tt = []
        def yy = []
        boolean st = false
        //def dltr=[":","="]

        rfile.eachLine { fl ->

            if (flend == "") {
                if (fl.contains(flstrt)) {
                    st = true
                }
            } else {
                if (fl.startsWith(flend)) {
                    st = false
                }//Ending
                if (fl.contains(flstrt)) {
                    st = true
                }//Starting
            }

            if (st == true) {       //Get The Distinct Key
                dltr.each { dtr ->
                    if (fl.contains(dtr)) {
                        ///println fl
                        yy = fl.tokenize(dtr)
                        tt << yy[0].trim()//+"$dtr"
                        yy = []
                    }
                }
            }
        }
        return tt.countBy { it }.each { it }.keySet()
        //usage=>getdistinctkeys(["=",":"],"-----------ME START----------------","---------------------------")
    }

    def csvwriter(String strtline,String endline,boolean useendline)
    {
        rfile.eachLine {str->

            if(str.startsWith(strtline))
            {
                if(useendline==false)
                {
                    wfile.append(csvarr.join(txtdltr) + "\n")
                    csvarr = []
                    pos=0
                }
                writetoarray=true
            }

            if(useendline==true)
            {
                if (str.startsWith(endline)) {
                    wfile.append(csvarr.join(txtdltr) + "\n")
                    csvarr = []
                    pos=0
                    writetoarray = false
                }
            }

            if(writetoarray==true)
            {
                csvarr[pos] = '"' +str+'"'
                pos = pos + 1
            }

        }
        wfile.append(csvarr.join(txtdltr) + "\n")   //Flush The Remaining Lat Entry
        //Usage=>poochi.csvwriter("Date^Mon May 21","node-type^",true)
    }

def csvwriterwithcolnames(String colfilepath,String strtline,String endline,boolean useendline) {
    //Writing Col Names
    colfile = new File(colfilepath)
    colfile.eachLine { txt ->
        txt = txt.replace("^", "").replace(":", "")
        csvarr << '"' + txt + '"'
    }
    wfile.append(csvarr.join(txtdltr) + "\n")
    //println csvarr.join(",")//===========================
    println "Column Names Written..."
    sleep(1000)
    csvarr = []

    rfile.eachLine { str ->
        //println str
        //str = str.replace("}", "").replace('"', "")
        if (str.contains(strtline)) {
            if (useendline == false) {
                wfile.append(csvarr.join(txtdltr) + "\n")
                csvarr = []
                pos = 0
            }
            writetoarray = true
        }

        if (useendline == true) {
            if (str.startsWith(endline)) {
                wfile.append(csvarr.join(txtdltr) + "\n")
                //println csvarr.join(",")//===========================
                csvarr = []
                pos = 0
                writetoarray = false
            }
        }

        if (writetoarray == true) {

            colfile.eachLine { String dat ->

                if (str.startsWith(dat)) {
                    csvarr[pos] = '"' + str.replace(dat, "") + '"'

                }
                pos = pos + 1

            }
            pos = 0
        }

    }
    wfile.append(csvarr.join(txtdltr) + "\n")   //Flush The Remaining Lat Entry
//Usage=>poochi.csvwriterwithcolnames("/home/krishnan/Desktop/col-OutCardFail.txt","Date^Mon May 21","=======================================",true)
}

    def csvwriterwithcolnamesspl(String colfilepath,String strtline,String endline,boolean useendline) {
        //Writing Col Names
        boolean proc=false
        colfile = new File(colfilepath)
        colfile.eachLine { txt ->
            txt = txt.replace("^", "").replace(":", "")
            csvarr << '"' + txt + '"'
        }
        wfile.append(csvarr.join(txtdltr) + "\n")
        //println csvarr.join(",")//===========================
        println "Column Names Written..."
        sleep(1000)
        csvarr = []

        rfile.eachLine { str ->
            proc=false
            //println str
            //str = str.replace("}", "").replace('"', "")
            if (str.contains(strtline)) {
                if (useendline == false) {
                    wfile.append(csvarr.join(txtdltr) + "\n")
                    csvarr = []
                    pos = 0
                }
                writetoarray = true
            }

            if (useendline == true) {
                if (str.startsWith(endline)) {
                    wfile.append(csvarr.join(txtdltr) + "\n")
                    //println csvarr.join(",")//===========================
                    csvarr = []
                    pos = 0
                    writetoarray = false
                }
            }

            if (writetoarray == true) {

                colfile.eachLine { String dat ->

                    if (proc == false) {
                        if (str.contains(dat)) {
                            csvarr << str.replace(dat, "")
                            proc = true
                        }
                    }

                }
                //pos = 0
                /*if(proc==false)
                {
                    println "Yes Found..."
                    csvarr<<""
                }*/
            }


        }
        wfile.append(csvarr.join(txtdltr) + "\n")   //Flush The Remaining Lat Entry
//Usage=>poochi.csvwriterwithcolnames("/home/krishnan/Desktop/col-OutCardFail.txt","Date^Mon May 21","=======================================",true)
    }



    def filereplace(String colfilepath)
    {
        colfile = new File(colfilepath)                     //File Containing The Replace Values

        rfile.eachLine {dat->                               //Src Data
            colfile.eachLine {str->                         //Replace String
            dat=dat.replace(str,"")               //Replacing Function
            }
            wfile.append(dat+"\n")                          //Write To File
        }
        //Usage filereplace("File Name")

    }//End Of filereplace





}//End Of Class

//def poochi=new Gfileprocess()
//def poochi2=new Gfileprocess()
//poochi.readfile()
//poochi.loadfile("/home/krishnan/Desktop/NoInfo99.txt")
//poochi2.loadfile("/home/krishnan/Desktop/cennames.txt")
//poochi.wfile=new File("/home/krishnan/Desktop/prettyNoInfo99.txt")
//poochi2.wfile=new File("/home/krishnan/Desktop/NoInfo99.txt")
//poochi.readfile()
//poochi.distinctpattern([["StructureEvent Type = "," : notificationId"],["X.733::EventType = "," | neTime "]])
//poochi.countlines()
//poochi.countword(["gokul","NT_ALARM","MANAGED_ELEMENT","communicationsAlarm"])
//poochi.countword("NT_ALARM")
/*========================================================================================================*/
//Cisco Dump Processing
//---------------------------------------------------------------------------------------------------------
/*int i=0
poochi.rfile.eachLine {line->
   i=i+1
    println "Processing Line: $i"
    poochi.wfile.append("fmCorrelationParser.txt: INFO [2018-03-31T09:33:20\n" +
            "718] LogUtil: putLog(): Final EventData:: EventData [eventVaribleMap="+"\n")
    line.tokenize(",").each{

       if(it.contains("[eventVaribleMap"))
       {it.tokenize("{").each{poochi.wfile.append(it+"\n")}}
       else poochi.wfile.append(it+"\n")}
}
println("Process Completed...")*/
/*
def bb=[]
bb=poochi.countword(["object","EventVaraible"])
//def tty=poochi.countword("object")
bb.each {println "From Main "+it}
//println tty.toInteger()*/
//Arrange Columns
//---------------------
//Data Standardization
/*
int i=0
boolean fnd=false
boolean nfnd=false
poochi.rfile.eachLine { line ->
    i = i + 1
    println "Processing Line: $i"

    println line.tokenize("^").size().toString()
    if (line.tokenize("^").size() > 45) {
        //sleep(5000)
    }
    //println line.tokenize("^").size().toString()

    poochi2.rfile.eachLine { ord ->

        line.tokenize("^").each {//poochi.wfile.append(it+"\n")
            if (it.contains(ord)) {
                poochi.wfile.append(it + "^")
                //print it+"^"
                fnd = true
            }
        }
        if (fnd == false) {
            poochi.wfile.append(ord + "=Not Found^")
        }
        fnd = false
        //print "Not Found^"
        //println ""
        //sleep(5000)
    }

    //All Found or Not Check
    line.tokenize("^").each {it1->
        poochi2.rfile.eachLine {ord1->
            if (it1.contains(ord1))
            {
                nfnd=true
            }

        }
        if(nfnd==false){poochi.wfile.append(it1+"^")}
        nfnd    =false

    }

poochi.wfile.append("\n")
}
println("Process Completed...")
*/
/*
int i=0
poochi.rfile.eachLine {
    println i++
    if (it.contains("FMInventoryName=")) {}//poochi.wfile.append(it+"\n")}
        else {
            poochi.wfile.append(it + "\n")
        }
    }
println "Completed...."*/
/*
def poochi=new Gfileprocess()
poochi.loadfile("/home/krishnan/Desktop/maptest.txt")
poochi.writefile("/home/krishnan/Desktop/jgdemo/meout.txt")

def ss= poochi.getdistinctkeys(["="],"+++++++++++++++++++","")
Map mm=[:]
ss.each{mm[it]=""}
println mm
poochi.mappattern(mm,"+++++++++++++++++++","",["="],"^")*/

//BHANUCHANDER//