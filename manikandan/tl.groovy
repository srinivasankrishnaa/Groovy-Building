package com.alcatel.tl

class Gktl {
    def arr2 = []
    def arr3 = []

    def arr = []
    boolean strt = false

    String tmp = ""

    def op = []

    boolean parsetl(String srcfl, String colfl, String outputfl) {

        File fl = new File(srcfl)
        File wr = new File(outputfl)

        File col = new File(colfl)

        arr = fl.readLines()

        def colnm = col.readLines()

//wr.append('connectionName$connectionRate$allocationCost$connectionState$direction$operationalState$serviceState$A NEName$A PortRate$A PortName$Z NEName$ Z PortRate$ Z PortName$EndType\n')
        wr.append(colnm.join(',').replace('{', '').replace(':', '').replace(' ', '') + "\n")

        arr.eachWithIndex { String str, int pos ->
            str = str.replace('\\', '')
            str = str.trim()

            str = str.replace('{"connectionName":', '~{"connectionName":')


            arr2 = str.tokenize("~")

            arr2.each { String conn ->
                conn = conn.replace(',"A1End":', '~"A1End":').replace(',"A2End":', '~"A2End":').replace(',"Z1End":', '~"Z1End":').replace(',"Z2End":', '~"Z2End":')
                arr3 = conn.tokenize('~')

                arr3[0].replace('","', '"~"').tokenize("~").each { String pt ->

                    colnm.eachWithIndex { String entry, int i ->

                        if (pt.contains(entry)) {
                            //println entry
                            op[i] = pt.replace(entry, '')
                        }

                        if (arr3[1].toString().startsWith(entry)) {
                            op[i] = arr3[1].toString().replace(entry, '').replace('","', '"|"').replace(',','')
                        }

                        if (arr3[2].toString().startsWith(entry)) {
                            op[i] = arr3[2].toString().replace(entry, '').replace('","', '"|"').replace(',','')
                        }

                        if (arr3[3].toString().startsWith(entry)) {
                            op[i] = arr3[3].toString().replace(entry, '').replace('","', '"|"').replace(',','')
                        }

                        if (arr3[4].toString().startsWith(entry)) {
                            op[i] = arr3[4].toString().replace(entry, '').replace('","', '"|"').replace(',','')
                        }

                    }
                    //wr.append(pt+"\n")
                }
                wr.append(op.join(',') + "\n")
                op = []


            }

        }
    return true
    }

}





















