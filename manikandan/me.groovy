package com.alcatel.me

class Gkme {
    def arr = []
    def arr2 = []
    def arr3 = []
    def arr4 = []
    def op = []
    def src = []


    boolean parseme(String srcfl, String colfl, String outputfl) {

        File fl = new File(srcfl)
        File wr = new File(outputfl)

        File col = new File(colfl)

        src = col.readLines()

        wr.append(src.join(",").replace(' ', '').replace(':', '') + "\n")
/*src.each {String colval->
   wr.append('"'+colval.trim().replace(':','')+'",')
}
wr.append("\n")*/

        boolean cllt = false
        boolean cls = false

        fl.eachLine { String str ->

            str = str.replace('\\', '')
            if (str.contains('{"userLabel":')) {
                str = str.replace('{"userLabel":', '^"userLabel":')
            }
            str = str.trim().replace('"[', '').replace(']"', '').replace('"attributeNameValue":{', '').replace(',"clusterNEs":', '~"clusterNEs":"').replace('}}]', '')


            arr = str.tokenize('^')

            arr.each { String it ->

                if (it.contains('"userLabel":')) {
                    arr3 = it.tokenize("~")

                    arr2 = arr3[0].replace('","', '"^"').replace('null,', 'null^').tokenize("^")
                    arr2.each { String usrlbl ->
                        //wr.append(usrlbl+"\n")
                        op << usrlbl
                    }
                    //arr3[1]=arr3[1].replace('[]}}','[]"')
                    op << arr3[1].replace('},{', '}|{').replace('}},', '"').replace('","', '"|"').replace('][', ']"')
                    //wr.append(arr3[1].replace('},{','}|{').replace('}},','"').replace('","','"|"')+"\n")

                    //wr.append(arr3[1].replace('}},','"').replace('","','"|"').replace('},{','}|{')+"\n")

                    op.each { String opstr ->

                        src.eachWithIndex { String entry, int i ->
                            if (opstr.startsWith(entry.trim())) {
                                arr4[i] = opstr.replace(entry.trim(), '')
                            }

                        }

                    }
                    wr.append(arr4.join(',') + "\n")
                    arr4 = []
                    op = []

                }

            }

        }

        wr.append('""')
        return true
    }

    boolean parsemerest(String srcfl, String colfl, String outputfl) {

        File fl = new File(srcfl)
        File wr = new File(outputfl)

        File col = new File(colfl)

        src = col.readLines()

        wr.append(src.join(",").replace(' ', '').replace(':', '') +","+'ClusterNEName,ClusterNEIPAddress,Type'+"\n")
/*src.each {String colval->
   wr.append('"'+colval.trim().replace(':','')+'",')
}
wr.append("\n")*/

        boolean cllt = false
        boolean cls = false

        fl.eachLine {String str->
            /*if(str.contains('": "')) {
                str = str.replace('": "', '^')
                arr = str.tokenize("^")
                arr2 << arr[0]
            }*/
            str=str.trim()

            if (str.contains('"userLabel": ')) {
                wr.append(arr.join(",") +","+arr2.join(",")+ "\n")
                arr = []
                arr2=[]
                cllt = false

                cllt = true
                cls=false
            }

            src.eachWithIndex { String entry, int i ->

                if (cllt == true) {

                    if (str.contains(entry)) {

                        if(str.endsWith(',')) {arr[i] = str.substring(0, str.length()-1).replace(entry,'')}
                        else {arr[i] = str.replace(entry,'')}

                    }


                }
            }

            if (str.contains('"clusterNEs":')) {
                cls=true
            }

            if(cls==true)
            {
                if(str.contains('"ClusterNEName": ')){arr2<<str.substring(0, str.length()-1).replace('"ClusterNEName": ','')}
                if(str.contains('"ClusterNEIPAddress": ')){arr2<<str.substring(0, str.length()-1).replace('"ClusterNEIPAddress": ','')}
                if(str.contains('"Type": ')){arr2<<str.replace('"Type": ','')}
            }

        }
        wr.append(arr.join(",") +","+arr2.join(",")+ "\n")

    return true
    }


    }
