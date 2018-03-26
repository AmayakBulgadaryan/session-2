/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.sbt.jschool.session2;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 */
public class OutputFormatter {
    private PrintStream out;



    public static void main(String[] args) {

        DecimalFormat dc = new DecimalFormat("###,###");
        System.out.println(dc.format(1872658021));


        int[][] data = new int[13][5];
        for (int i = 0; i < 13 ; i++) {
            for (int j = 0; j <5 ; j++) {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(data.length);

        Object obj = .12;
        double d = (double) obj;
        System.out.println(d);
        Date date = new Date();
        System.out.println(date);
        System.out.println();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Date date1 = new Date(14-1900,0,1);

        String l = "1234";




        System.out.println(sdf.format(date1));




//        double d = Double.parseDouble(".1245");
//        System.out.println(d);
//        System.out.printf("%,5.2f",d);

        int reg = 30;


        //formatter.format("%,7.2f", 212334909.5544);
        System.out.println("Amayak");
        System.out.format("%,"+reg+".2f", 56283750.123);
        System.out.println();
        System.out.printf("%,"+reg+"d", 192875769);

        Locale.setDefault(new Locale("fr", "FR"));
        Locale.setDefault(Locale.FRENCH);
        Locale.setDefault(new Locale("ru","RUSSIA"));

        System.out.println();
        System.out.printf("%,"+reg+".2f", 56283750.123);
    }

    public OutputFormatter(PrintStream out) {
        this.out = out;
    }

    private String GridLine(String[] names, Object[][] data)
    {
       int[] countOfTraitInLine = new int[names.length];

        for (int i = 0; i < names.length; i++) {

            Object obj = data[0][i];
            int max = names[i].length();

            if (obj instanceof Double) {

                DecimalFormat dc = new DecimalFormat("###,##0.00");

                for (int j = 0; j < data.length; j++) {
                    if (data[j][i]!=null) {
                        double dObj = (double) data[j][i];
                        int lengthOfNumber = dc.format(dObj).length();
                        if (lengthOfNumber > max) max = lengthOfNumber;
                    }
                }
            }
            if (obj instanceof Integer) {

                DecimalFormat dc = new DecimalFormat("###,###");

                for (int j = 0; j < data.length; j++) {
                    if (data[j][i]!=null) {
                        int iObj = (int) data[j][i];
                        int lengthOfNumber = dc.format(iObj).length();
                        if (lengthOfNumber > max) max = lengthOfNumber;
                    }
                }
            }
            if (obj instanceof Date) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                for (int j = 0; j < data.length; j++) {
                    if (data[j][i] != null) {
                        Date dateObj = (Date) data[j][i];
                    int lengthOfNumber = sdf.format(dateObj).length();
                    if (lengthOfNumber > max) max = lengthOfNumber;
                }
                }
            }
            if (obj instanceof String) {

                for (int j = 0; j < data.length; j++) {
                    if (data[j][i]!=null&&data[i][j]!="") {
                        String sObj = (String) data[j][i];
                        int lengthOfLine = sObj.length();
                        if (lengthOfLine > max) max = lengthOfLine;
                    }
                }
            }


            countOfTraitInLine[i] = max;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < countOfTraitInLine.length; i++) {
            for (int j = 0; j < countOfTraitInLine[i]; j++) {
                sb.append("-");
            }
            sb.append("+");
        }

        return sb.toString();
    }

    private void drawToGrid(String gridLine)
    {

    }


    private String renderingOfStringData(Object obj)
    {
        String sObj = (String) obj;

        return null;
    }


    private String renderingOfNumberData(Object obj)
    {
        int iObj = (int) obj;

        return null;
    }

    private String renderingOfDoubleData(Object obj)
    {
        double dObj = (double) obj;

        return null;

    }

    private String renderingOfDateData(Object obj)
    {
        Date value = (Date) obj;

        return null;
    }


    public void output(String[] names, Object[][] data) {
        //TODO: implement me.

        String gridLine = GridLine(names,data);




    }
}
