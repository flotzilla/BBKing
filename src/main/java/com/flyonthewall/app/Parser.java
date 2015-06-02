package com.flyonthewall.app;

import com.flyonthewall.entity.Day;
import com.flyonthewall.entity.Program;
import com.flyonthewall.entity.Week;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: obyte
 * Date: 02.06.13
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class Parser {
    private File file;

    public boolean validate(File file) {
        if (file == null) {
            System.out.println("Null pointer");
            return false;
        }
        if (!file.exists()) {
            System.out.println("File not exists");
            return false;
        }
        if (file.length() == 0) {
            System.out.println("File is empty");
            return false;
        }
        System.out.println("All is Ok");
        return true;
    }

    public Parser(File file) {
        this.file = file;
    }

    public Week parse() {
        String[] text = readFile();
        Pattern approachPattern = Pattern.compile("\\d{1,2}\\u0029\\d{1,3}\\w\\d{1,2}");
        Pattern approachPattern2 = Pattern.compile("\\d{1,2}\\u0029\\d{1,3}\\w\\d{1,2}-\\d{1,2}");
        Matcher m;
        Matcher m2;

        Week week = new Week();
        for (int i = 0; i < text.length; i++) {
            if (text[i].startsWith("Day")) {
                Day d = new Day();
                for (int j = i + 1; j < text.length; j++) {
                    m = approachPattern.matcher(text[j]);
                    m2 = approachPattern2.matcher(text[j]);
                    if (m.matches()) {
                        int i1 = text[j].indexOf(')');
                        int i2 = text[j].indexOf('x');
                        float mass = Float.parseFloat(text[j].substring(i1 + 1, i2));
                        int minRepeats = Integer.parseInt(text[j].substring(i2 + 1));
                        d.addApproach(mass, minRepeats, 0);
                    } else if (m2.matches()) {
                        int i1 = text[j].indexOf(')');
                        int i2 = text[j].indexOf('x');
                        int i3 = text[j].lastIndexOf('-');
                        float mass = Float.parseFloat(text[j].substring(i1 + 1, i2));
                        int minRepeats = Integer.parseInt(text[j].substring(i2 + 1, i3));
                        int maxRepeats = Integer.parseInt(text[j].substring(i3 + 1));
                        d.addApproach(mass, minRepeats, maxRepeats);
                    } else {
                        week.addDay(d);
                        i = j;
                        break;
                    }
                    if (j == text.length-1) {
                       week.addDay(d);
                    }
                }
            }  //endif start with day
        }

        return week;

    }

    public String[] readFile() {
        String line;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            br = new BufferedReader(new FileReader(file.getPath()));
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append('\n');
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        } catch (IOException e) {
            System.out.println("IO ex");
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("IO ex");
                }
        }
        return sb.toString().split("\\n");
    }
}



