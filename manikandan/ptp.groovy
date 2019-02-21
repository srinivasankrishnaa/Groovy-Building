package com.alcatel.ptp

class Gkptp {


    def arr = []
    def arr2 = []

//def arr3=[]

    def op = []

    boolean parseptp(String srcfl, String colfl, String outputfl) {

        File fl = new File(srcfl)
        File wr = new File(outputfl)
        File col = new File(colfl)



    def arr3 = col.readLines()

    wr.append ( arr3.join ( "," ).replace ( '{', '' ).replace ( ' ', '' ).replace ( ':', '' ) + "\n" )

    fl.eachLine {
        String str ->
        str = str.replace('\\', '')

        arr = str.replace('{"NEName":', '^{"NEName":').tokenize("^")

        arr.each { String val ->

            if (val.contains(',"')) {
                val = val.replace(',"', '^"')
            }
            arr2 = val.tokenize('^')

            arr2.each { String rep ->

                col.eachWithIndex { String entry, int i ->

                    if (rep.startsWith(entry)) {
                        op[i] = rep.replace(entry, '')
                    }

                }
            }
            wr.append(op.join(",") + "\n")
            op = []

        }
    }

/*arr3.unique().each {
    col.append(it+"\n")
}*/
        return true
}

}