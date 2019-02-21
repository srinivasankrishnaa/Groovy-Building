package  com.bhanu.master.nbidump
import com.bhanu.master.file.Gfileprocess

//File colfl=new File("/home/krishnan/Desktop/mecol.txt")

class Gknbidumps {

    File rfile
    File wfile
    File colfile

    def me(String readfile, String writefile, String colfilepath, String strtline, String endline, boolean useendline) {

        //File rfile=new File("/home/krishnan/Desktop/My Files/GokulaKrishnan/Cygapp/Tejas_Mumb_EMS_ME_12june.txt")
        //File wfile=new File("/home/krishnan/Desktop/newoutme.csv")
        def arr=[]
        if (writefile.contains(".")) {

            arr = writefile.tokenize(".")
            writefile = arr[0] + ".csv"
        } else {
            writefile = writefile + ".csv"
        }

         rfile = new File(readfile)
         wfile = new File(writefile)

        boolean writetoarray = false
        int pos = 0

        def csvarr = []
        //Writing Col Names
        colfile = new File(colfilepath)

        colfile.eachLine { txt ->
            txt = txt.replace("^", "").replace(":", "")
            csvarr << '"' + txt + '"'
        }
        wfile.append(csvarr.join(",") + "\n")
        //println csvarr.join(",")//===========================
        println "Column Names Written..."
        sleep(1000)
        csvarr = []

        rfile.eachLine { str ->
            //println str
            //str = str.replace("}", "").replace('"', "")
            if (str.contains(strtline)) {
                if (useendline == false) {

                    csvarr << csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
                    csvarr = []
                    pos = 0
                }
                writetoarray = true
            }

            if (useendline == true) {
                if (str.startsWith(endline)) {

                    csvarr << csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")
                    //println csvarr.join(",")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
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
        wfile.append(csvarr.join(",") + "\n")   //Flush The Remaining Lat Entry
//Usage=>poochi.csvwriterwithcolnames("/home/krishnan/Desktop/col-OutCardFail.txt","Date^Mon May 21","=======================================",true)
    }

    def ptp(String readfile, String writefile, String colfilepath, String strtline, String endline, boolean useendline) {

        //File rfile=new File("/home/krishnan/Desktop/My Files/GokulaKrishnan/Cygapp/Tejas_Mumb_EMS_ME_12june.txt")
        //File wfile=new File("/home/krishnan/Desktop/newoutme.csv")

        def arr = []
        if (writefile.contains(".")) {
            arr = writefile.tokenize(".")
            writefile = arr[0].toString() + ".csv"
        } else {
            writefile = (writefile + ".csv").toString()
        }

        rfile = new File(readfile)
        wfile = new File(writefile)

        boolean writetoarray = false
        int pos = 0

        def csvarr = []
        //Writing Col Names
        colfile = new File(colfilepath)
        colfile.eachLine { txt ->
            txt = txt.replace("^", "").replace(":", "")
            csvarr << '"' + txt + '"'
        }
        wfile.append(csvarr.join(",") + "\n")
        //println csvarr.join(",")//===========================
        println "Column Names Written..."
        sleep(1000)
        csvarr = []

        rfile.eachLine { str ->
            //println str
            //str = str.replace("}", "").replace('"', "")
            if (str.contains(strtline)) {
                if (useendline == false) {

                    csvarr[13] = csvarr[2].toString().replace('"', "") + "_" + csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
                    csvarr = []
                    pos = 0
                }
                writetoarray = true
            }

            if (useendline == true) {
                if (str.startsWith(endline)) {

                    csvarr[13] = csvarr[2].toString().replace('"', "") + "_" + csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")
                    //println csvarr.join(",")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
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
        wfile.append(csvarr.join(",") + "\n")   //Flush The Remaining Lat Entry
//Usage=>poochi.csvwriterwithcolnames("/home/krishnan/Desktop/col-OutCardFail.txt","Date^Mon May 21","=======================================",true)
    }

    def tl(String readfile, String writefile, String colfilepath, String strtline, String endline, boolean useendline) {

        //File rfile=new File("/home/krishnan/Desktop/My Files/GokulaKrishnan/Cygapp/Tejas_Mumb_EMS_ME_12june.txt")
        //File wfile=new File("/home/krishnan/Desktop/newoutme.csv")
        boolean proc = false
        def arr = []
        if (writefile.contains(".")) {
            arr = writefile.tokenize(".")
            writefile = arr[0].toString() + ".csv"
        } else {
            writefile = (writefile + ".csv").toString()
        }

        rfile = new File(readfile)
        wfile = new File(writefile)

        boolean writetoarray = false
        int pos = 0

        def csvarr = []
        //Writing Col Names
        colfile = new File(colfilepath)
        colfile.eachLine { txt ->
            txt = txt.replace("^", "").replace(":", "")
            csvarr << '"' + txt + '"'
        }
        wfile.append(csvarr.join(",") + "\n")
        //println csvarr.join(",")//===========================
        println "Column Names Written..."
        sleep(1000)
        csvarr = []

        rfile.eachLine { str ->
            proc = false
            //println str
            //str = str.replace("}", "").replace('"', "")
            if (str.contains(strtline)) {
                if (useendline == false) {

                    //csvarr[8]=csvarr[2].toString().replace('"',"")+"_"+csvarr[1].toString().replace('"',"")+"_"+csvarr[0].toString().replace('"',"")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
                    csvarr = []
                    pos = 0
                }
                writetoarray = true
            }

            if (useendline == true) {
                if (str.startsWith(endline)) {

                    csvarr[12] = csvarr[8].toString().replace('"', "") + "_" + csvarr[7].toString().replace('"', "") + "_" + csvarr[6].toString().replace('"', "")
                    csvarr[13] = csvarr[11].toString().replace('"', "") + "_" + csvarr[10].toString().replace('"', "") + "_" + csvarr[9].toString().replace('"', "")
                    csvarr[14] =csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")
                    //println csvarr.join(",")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
                    //println csvarr.join(",")//===========================
                    csvarr = []
                    pos = 0
                    writetoarray = false
                }
            }

            if (writetoarray == true) {

                colfile.eachLine { String dat ->

                    if (proc == false) {

                        if (str.startsWith(dat)) {
                            csvarr << '"' + str.replace(dat, "") + '"'
                            proc = true

                        }
                        //pos = pos + 1
                    }
                    //pos = 0
                }
                if (proc == false) {
                    arr << ""
                }
            }

        }
        wfile.append(csvarr.join(",") + "\n")   //Flush The Remaining Lat Entry
//Usage=>poochi.csvwriterwithcolnames("/home/krishnan/Desktop/col-OutCardFail.txt","Date^Mon May 21","=======================================",true)
    }






    def snc(String readfile, String writefile, String colfilepath, String strtline, String endline, boolean useendline) {

        //File rfile=new File("/home/krishnan/Desktop/My Files/GokulaKrishnan/Cygapp/Tejas_Mumb_EMS_ME_12june.txt")
        //File wfile=new File("/home/krishnan/Desktop/newoutme.csv")
        def arr=[]
        if (writefile.contains(".")) {

            arr = writefile.tokenize(".")
            writefile = arr[0] + ".csv"
        } else {
            writefile = writefile + ".csv"
        }

        rfile = new File(readfile)
        wfile = new File(writefile)

        boolean writetoarray = false
        int pos = 0

        def csvarr = []
        //Writing Col Names
        colfile = new File(colfilepath)

        colfile.eachLine { txt ->
            txt = txt.replace("^", "").replace(":", "")
            csvarr << '"' + txt + '"'
        }
        wfile.append(csvarr.join(",") + "\n")
        //println csvarr.join(",")//===========================
        println "Column Names Written..."
        sleep(1000)
        csvarr = []

        rfile.eachLine { str ->
            //println str
            //str = str.replace("}", "").replace('"', "")
            if (str.contains(strtline)) {
                if (useendline == false) {

                    csvarr << csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
                    csvarr = []
                    pos = 0
                }
                writetoarray = true
            }

            if (useendline == true) {
                if (str.startsWith(endline)) {

                    csvarr << csvarr[1].toString().replace('"', "") + "_" + csvarr[0].toString().replace('"', "")
                    //println csvarr.join(",")

                    wfile.append(csvarr.collect { it ? it : '' }.join(",") + "\n")
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
        wfile.append(csvarr.join(",") + "\n")   //Flush The Remaining Lat Entry
//Usage=>poochi.csvwriterwithcolnames("/home/krishnan/Desktop/col-OutCardFail.txt","Date^Mon May 21","=======================================",true)
    }









}

//me("/home/krishnan/Desktop/My Files/GokulaKrishnan/Cygapp/Tejas_Mumb_EMS_ME_12june.txt","/home/krishnan/Desktop/newoutme.csv","/home/krishnan/Desktop/mecol.txt","-----------ME START----------------","---------------------------",true)
//ptp("/home/krishnan/Desktop/Tejas_Mumb_EMS_PTP_12june.txt","/home/krishnan/Desktop/newoutptp.csv","/home/krishnan/Desktop/ptpcol.txt","----------------TP Start-------------------","----------------TP End-------------------",true)
//tl("/home/krishnan/Desktop/Tejas_Mumb_EMS_TL_13june.txt","/home/krishnan/Desktop/tloutme.csv","/home/krishnan/Desktop/tlcol.txt","-------------------- TopologicalLink Start --------------","-------------------- TopologicalLink End --------------",true)




















/*def tr=[]
tr[0]=1
tr[1]=2
tr[2]="Hello"
tr[4]="Welcome"
println tr.collect{it ? it :''}.join(",")*/


