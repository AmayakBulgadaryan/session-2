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


import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 */
public class OutputFormatter {
    private PrintStream out;

    public static void main(String[] args) throws ParseException, IOException { }


    public OutputFormatter(PrintStream out) {
        this.out = out;
    }


    private String[] ComputeGridLines(String[] names, Object[][] data)
    {
       int[] countOfTraitInLines = new int[names.length];
       String[] gridLines = new String[names.length];

        for (int i = 0; i < names.length; i++) {

            int k = 0;
            int max = names[i].length();

            if (data.length != 0) {

                if (data[0][i] == null) {
                    for (int j = 0; j < data.length; j++) {
                        if (data[j][i] != null) {
                            break;
                        }
                        k++;
                    }

                }
                if (k == data.length) {
                    countOfTraitInLines[i] = max;
                    continue;
                }

                Object obj = data[k][i];


                if (obj instanceof Double) {

                    DecimalFormat dc = new DecimalFormat("###,##0.00");

                    for (int j = 0; j < data.length; j++) {
                        if (data[j][i] != null) {
                            double dObj = (double) data[j][i];
                            int lengthOfNumber = dc.format(dObj).length();
                            if (lengthOfNumber > max) max = lengthOfNumber;
                        }
                    }
                }
                if (obj instanceof Integer) {

                    DecimalFormat dc = new DecimalFormat("###,###");

                    for (int j = 0; j < data.length; j++) {
                        if (data[j][i] != null) {
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
                if (obj instanceof String || obj == "") {

                    for (int j = 0; j < data.length; j++) {
                        if (data[j][i] != null && data[j][i] != "") {
                            String sObj = (String) data[j][i];
                            int lengthOfLine = sObj.length();
                            if (lengthOfLine > max) max = lengthOfLine;
                        }
                    }
                }

            }
                countOfTraitInLines[i] = max;
            }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < countOfTraitInLines.length; i++) {
            for (int j = 0; j < countOfTraitInLines[i]; j++) {
                sb.append("-");
            }
            gridLines[i] = sb.toString();
            sb.delete(0, sb.length());
        }

        return gridLines;
    }

    private void drawToGrid(String[] gridLines, String[] names, Object[][] data)
    {
        DecimalFormat dfDouble = new DecimalFormat("###,##0.00");
        DecimalFormat dfInt = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        //Вычисляю отступ для центрирования заголовков таблицы.
        int[] indentForTitles = new int[names.length];
        for (int i = 0; i < names.length ; i++) {
            indentForTitles[i] = (gridLines[i].length() - names[i].length())/2;
        }

        //Получаю формат горизонтальной линии таблицы.
        String gridLine = "";
        for (int i = 0; i < gridLines.length ; i++) {

            gridLine+="+";
            gridLine+=gridLines[i];
        }
        gridLine+="+";

        //Рисую заголовок таблицы.
        out.println(gridLine);
        for (int i = 0; i < names.length; i++) {
            out.print("|");
            for (int j = 0; j < indentForTitles[i]; j++)
            {
               out.print(" ");
            }
            int countSymbol = gridLines[i].length() - indentForTitles[i];
            out.printf("%-"+countSymbol+"s",names[i]);
        }
        out.println("|");
        out.println(gridLine);

        //Рисую остальную таблицу с данными:
        for (int i = 0; i < data.length ; i++) {

            for (int j = 0; j < names.length ; j++) {
                out.print("|");
                int lengthOfLine = gridLines[j].length();

                    Object obj = data[i][j];

                    if (obj==null)
                    {
                        for (int k = 0; k < data.length ; k++) {
                            if (data[k][j]!=null)
                            {
                                if (data[k][j] instanceof Double)
                                {out.printf("%"+lengthOfLine+"s", "-"); break;}
                                if (data[k][j] instanceof Integer)
                                {out.printf("%"+lengthOfLine+"s", "-"); break;}
                                if (data[k][j] instanceof Date)
                                {out.printf("%"+lengthOfLine+"s", "-"); break;}
                                if (data[k][j] instanceof String)
                                {out.printf("%-"+lengthOfLine+"s", "-"); break;}
                            }
                        }
                    }

                if (obj instanceof Double) {
                    double dObj = (double) obj;
                    out.printf("%"+lengthOfLine+"s", dfDouble.format(dObj));
                }
                if (obj instanceof Integer) {
                    int iObj = (int) obj;
                    out.printf("%"+lengthOfLine+"s", dfInt.format(iObj));
                }
                if (obj instanceof Date) {
                    Date dateObj = (Date) obj;
                    out.printf("%"+lengthOfLine+"s", sdf.format(dateObj));
                }
                if (obj instanceof String) {
                    String stringObj = (String) obj;
                    out.printf("%-"+lengthOfLine+"s", stringObj);
                }

            }
            out.println("|");
            out.println(gridLine);
        }
    }

    public void output(String[] names, Object[][] data) {
        //TODO: implement me.
        String[] gridLines = ComputeGridLines(names,data);
        drawToGrid(gridLines, names, data);
    }
}
