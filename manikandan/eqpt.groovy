package com.alcatel.eqpt


class Gkeqpt {

    boolean parseeqpt(String srcfl, String colfl, String outputfl) {


        File fl = new File(srcfl)
        File wr = new File(outputfl)
        File col = new File(colfl)

        def colval = col.readLines()

        def arr = []
        def arr2 = []
        def op = []

        wr.append(colval.join(',').replace(':', '') + "\n")

        fl.eachLine {
            String str ->

                str = str.replace('{"neName":', '^"neName":').replace('],"', ',~"').replace('}},', '').replace('"attributeNameValue":{', '')
                arr = str.tokenize('^')
                arr.each { String prs ->
                    //wr.append(it+"\n")
                    arr2 = prs.replace('","', '"~"').replace('null,', 'null~').tokenize('~')

                    arr2.each { String src ->
                        //wr.append(src+"\n")

                        colval.eachWithIndex { String entry, int i ->

                            if (src.startsWith(entry)) {
                                op[i] = src.replace(entry, '')
                            }


                        }
                    }
                    wr.append(op.join(',') + "\n")
                    op = []


                }

        }
        return true
    }
}