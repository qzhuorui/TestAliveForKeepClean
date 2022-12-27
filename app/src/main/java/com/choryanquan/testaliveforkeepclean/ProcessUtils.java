package com.choryanquan.testaliveforkeepclean;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ryan on 2022/12/19 10:27.
 * Function :
 */
public class ProcessUtils {
    public static String getProcessName() {
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Exception e;
        try {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedReader2 = new BufferedReader(new FileReader(new File("/proc/self/cmdline")));
            try {
                String trim = bufferedReader2.readLine().trim();
                try {
                    bufferedReader2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return trim;
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return "";
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader2 = null;
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            try {
                throw th;
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
