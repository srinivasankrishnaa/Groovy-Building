package com.snmp.snmptrap.snmptable

class GKsnmptable {

    boolean parsesnmptable(String unformatted_snmptable_path,String formatted_snmptable_path) {

        //File fl=new File("/home/krishnan/Desktop/MyFiles/kiran/2018/ALCATEL-WDM/SNMP-Trap/otn_27-06_1.txt")
        //File wr=new File("/home/krishnan/Desktop/MyFiles/kiran/2018/ALCATEL-WDM/SNMP-Trap/gk-normalized-otn_27-06_1.txt")

        File fl=new File(unformatted_snmptable_path)
        File wr=new File(formatted_snmptable_path)

        boolean cntns = true

        def arr = ['qualityofServiceAlarm', 'communicationsAlarm', 'equipmentAlarm', 'processingErrorAlarm']
        def sev = ['warning', 'minor', 'major', 'critical']

        String resrv = ''

        def sevarr = []

        def resrvarr = []

        String tmp = ''

        def arr2 = []
        def col123 = []
        def op = []


        fl.eachLine { String str ->
            while (str.contains('  ')) {
                str = str.replace('  ', ' ')
            }

            arr.each { String it ->
                str = str.replaceFirst(it, "^$it^")
            }

            if (str.contains('friendlyName')) {
                wr.append('currentAlarmId,friendlyName,eventTime,eventType,probableCause,perceivedSeverity,additionalText|specificProblems|acknowledgementStatus,reserveStatus,additionalInformation|neLocationName,managedobjectInstance,acknowledgementUserName,asIdentity\n')
            }

            if (str.contains('^')) {

                arr2 = str.tokenize("^")

                col123 = arr2[0].trim().tokenize(' ')

                op[0] = col123[0].trim()
                op[2] = col123[col123.size() - 1].trim()

                col123[0] = ' '
                col123[col123.size() - 1] = ' '
                op[1] = col123.join(' ').trim()
                op[3] = arr2[1].trim()


                tmp = arr2[2]
                sev.each { String it ->
                    tmp = tmp.replaceFirst(it, "^$it^")
                }
                tmp = tmp.trim()

                sevarr = tmp.tokenize('^')
                op[4] = sevarr[0].trim()
                op[5] = sevarr[1].trim()

                resrv = sevarr[2].replaceFirst('notReserved', '^notReserved^').replace('{', '^{').replace('}', '}^')
                resrvarr = resrv.tokenize('^')


                op[6] = resrvarr[0].trim()
                op[7] = resrvarr[1].trim()
                op[8] = resrvarr[2].trim()
                op[9] = resrvarr[3].trim()

                if (resrvarr[4].trim().tokenize(' ').size() == 2) {
                    op[10] = resrvarr[4].trim().tokenize(' ')[0]
                    op[11] = resrvarr[4].trim().tokenize(' ')[1]
                } else {
                    op[10] = ''; op[11] = resrvarr[4].trim()
                }


                //wr.append(op.join(',') + "\n")
                op.each {String wrstr->
                wr.append('"'+wrstr+'",')
                }
                wr.append('\n')
                op = []
                col123 = []
                arr2 = []
                resrvarr = []


            }

        }
        return true
    }
}