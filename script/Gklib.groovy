package com.bhanu.master.db
import groovy.sql.Sql
class Gdatabase {

    def header = []
    def values = []
    def val
    Sql sql = null
    String flnm
    String dbqry
    String dburl
    String dbusrnm
    String dbpasswd
    String dbdriver
    //String tty

    File file

    def dbcredntls(String url, String usrnm, String passwd, String dbtyp) {
        dbtyp = dbtyp.toLowerCase()
        switch (dbtyp) {
            case 'oracle':
                dburl = "jdbc:oracle:thin:@$url"
                dbusrnm = usrnm
                dbpasswd = passwd
                dbdriver = "oracle.jdbc.driver.OracleDriver"
                break;
            case 'mysql':
                dburl = "jdbc:mysql://$url"
                dbusrnm = usrnm
                dbpasswd = passwd
                dbdriver = "com.mysql.jdbc.Driver"
                break;
            default: println "Unable To Recognize Database Type"
                return

                dbinit() //Connection Initiation
        }//Usage dbcredntls("10.5.179.24:1535:OPTSA2","saperf","welcome#123","oracle")
    }

        def oratocsv(String qrey, String outptflnme) {
            dbinit()
            flnm = outptflnme
            if (flnm.equals("")) {
                flnm = "./oratocsv.csv"
            }
            dbqry = qrey
            gen("oracle")
        }//Usage oratocsv("select * from me","out.csv")

        def mysqltocsv(String qrey, String outptflnme) {
            dbinit()
            flnm = outptflnme
            if (flnm.equals("")) {
                flnm = "./mysqltocsv.csv"
            }
            dbqry = qrey
            gen("mysql")
        }

        def dbinit() {
            if (sql == null) {
                sql = Sql.newInstance(dburl, dbusrnm, dbpasswd, dbdriver)
                println "Connection Successful..."
            } else {
                sql.close(); sql = Sql.newInstance(dburl, dbusrnm, dbpasswd, dbdriver)
            }
        }

        def gen(String str) {//Data Processing From DB to OutputFile...
            val = sql.rows(dbqry)

            if(val.empty){
                println "No Records Has Been Found For The Given Query..."
                return 0
            }
            //try {
            val.eachWithIndex { rowset, id ->
                header << rowset.keySet()
                values << rowset.values()
            }
            file = new File(flnm)
            file.append('"' + header[0].join('","') + '"' + "\n")
            values.each { file.append('"' + it.join('","') + '"' + "\n") }
            switch (str) {
                case 'oracle': println "Oracle Data Fetched..."; break
                case 'mysql': println "Mysql Data Fetched..."; break
            }
            //sql.close()
            header = []
            values = []
            println "The File is stored in " + file.path.toString()
            println "======================================================"

           /* }
            catch (Exception ex)
            {
                println "Query Returns No Output..."
            }*/

        }

        def crttbl(String tablename, List<String> columns, List<String> datatyp) {
            def str = []
            columns.eachWithIndex { val, int num ->
                String datyp = ""
                //datyp=datatyp[num]!=null?datatyp[num]:"varchar(255)" //Contitional Operator
                //str<<"$val $datyp"
                if (datatyp[num] == null || datatyp[num] == "") {
                    str << "$val varchar(255)"
                } else {
                    datyp = datatyp[num]; str << "$val $datyp"
                }
            }
            String tmp = str.join(",")
            //check For Table
            if (checktableexist(tablename) == false) { //Table Not Found.Hence Creating...
                dbqry = "create table $tablename ($tmp)"
                println("Create Table Query: $dbqry")
                sql.execute(dbqry)
                println "Table $tablename Created Successfully..."
            } else {
                println "Table Poochi Already Exists..."
            }//Table Exist...
        }

        def checktableexist(String tblname) {

            def tables = sql.connection.getMetaData().getTables(null, null, tblname, null)
            boolean exists = tables.next()

            if (exists) {
                return exists
            }
            //tbl.add(tblname) //println exists
            else return exists//println exists
        }

        def dbinsertval(String tablename, List<String> values)//List<String> colnames,
        {
            def str = []
            values.eachWithIndex { val1, idx ->
                str << val1
                //str1<<values[idx]
            }
            // def col=str.join(",")
            String val = str.join("','")
            //println val
            //println col
            //println val
            dbqry = "INSERT INTO $tablename values ('$val')"
            //dbqry="INSERT INTO poochi values(23,'Vicky','Central')"
            //dbqry="INSERT INTO poochi values(23,'kiran','velachary')"
            println "Insert Query : " + dbqry
            println "Inserting The Data On To The Database..."
            sql.execute(dbqry)
            println "Insertion Successful..."
        }

        def dbinsert(String tablename, List<String> colnames, List<String> values) {
            if (checktableexist(tablename) == false) {
                println "The Table $tablename Was Not Found In The Database..."; println "Data Cannot Be Inserted...";
                println "Suggestions: \n1.Try Creating The Table Using crttbl() Method...\n2.Try Manually Creating The Table...";
                return
            }

            def str = []
            def str1 = []
            colnames.eachWithIndex { val1, idx ->
                str << val1
                if (values[idx] == null) {
                    str1 << ""
                } else {
                    str1 << values[idx]
                }
            }
            String col = str.join(",")
            String val = str1.join("','")
            //println val
            //println col
            //println val
            dbqry = "INSERT INTO $tablename ($col)values ('$val')"
            //dbqry="INSERT INTO poochi values(23,'Vicky','Central')"
            //dbqry="INSERT INTO poochi values(23,'kiran','velachary')"
            println "Insert Query : " + dbqry
            println "Inserting The Data On To The Database..."
            sql.execute(dbqry)
            println "Insertion Successful..."
        }

        def dbdroptable(String tablename) {
            dbqry = "drop table $tablename"
            println "Drop Table Query : $dbqry"
            sql.execute(dbqry)
            println "Table $tablename Dropped Successfully..."
        }


        def exec() { println "******************Execution Started*******************" }

}//End Of Class
//usage
///def poochi1=new Gdatabase()
//poochi.pt()
//poochi.oratocsv("192.168.9.56:1521:DB11G","xmlnbitest","xmlnbitest","SELECT * from ME where rownum<=5","/home/krishnan/Desktop/ora.csv")
//poochi.mysqltocsv("192.168.11.170/CygNetPOCDBHuawei","root","nmsgk","select * from ME limit 10","/home/krishnan/Desktop/mysql.csv")
//poochi.crttbl("POOCHI",["col1","col2","col3"],[""])//,["number(10)","varchar(255)","varchar(255)"])
//poochi.dbcredntls("192.168.9.56:1521:DB11G","xmlnbitest","xmlnbitest","oracle")
//poochi.dbinit()
//poochi.crttbl("POCHI",['MOID','NAME','LOCATION'],['varchar(255)','varchar(255)','varchar(255)'])
///poochi1.exec()
//poochi1.dbcredntls("192.168.9.56:1521:DB11G","xmlnbitest","xmlnbitest","oracle")
//poochi1.crttbl("POOCHI",['MOID','NAME','LOCATION'],['number(10)','varchar(255)','varchar(255)'])
//poochi1.dbinsertval("poochi",["34","vijay","porur"])//["MOID","NAME","LOCATION"],
///poochi1.dbinsert("poochi",["MOID","NAME","LOCATION"],["40","Ilayaraja","T.Nagar"])
//poochi1.oratocsv("select * from poochi","/home/krishnan/Desktop/pooch.csv")
//poochi1.oratocsv("select * from poochi where rownum<=3","/home/krishnan/Desktop/pooch1.csv")

//poochi1.dbcredntls("localhost/CygNetPOCDBHuawei","root","nmsgk","mysql")
//poochi1.mysqltocsv("select * from ManagedObject limit 10","/home/krishnan/Desktop/mysql.csv")
//poochi1.mysqltocsv("select * from ME limit 10","/home/krishnan/Desktop/mysql1.csv")

//poochi1.dbcredntls("192.168.9.56:1521:DB11G","xmlnbitest","xmlnbitest","oracle")
//poochi1.dbdroptable("poochi")
