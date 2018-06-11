package com.bhanu.master.datetime

import com.bhanu.master.db.Gdatabase

import java.text.SimpleDateFormat

class Gdatetime{


    static SimpleDateFormat ipfrmt=new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss")
    String opfrmt

    def dateformat(String yourformat)
    {
        opfrmt=yourformat
        return yourformat
    }

    def  tmcnvrsnfromepoch(long epochtym,Closure fmt={})
    {
        if(fmt.call()==null)
        {
            fmt={it->"dd-MMM-yyyy_hh:mm:ss"}
        }
        return new Date(epochtym).format(fmt.call())
    }//Usage poochi.tmcnvrsnfromepoch(1528050601417){"MMM:dd:yyyy*hh-mm-ss"}

    def  tmcnvrsntoepoch(String tym,Closure fmt={})
    {
        if(fmt.call()!=null) {ipfrmt=new SimpleDateFormat(fmt.call())}
        return ipfrmt.parse(tym).getTime()
    }//Usage tmcnvrsntoepoch("08-JUN-2018_05:23"){it->"dd-MMM-yyyy_hh:mm"}

}

/*def poochi =new Gdatetime()
println poochi.tmcnvrsnfromepoch(1528050601056)
println poochi.tmcnvrsnfromepoch(1528050601417)*/