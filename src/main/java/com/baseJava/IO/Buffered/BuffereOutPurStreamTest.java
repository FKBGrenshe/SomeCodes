package com.baseJava.IO.Buffered;

import java.io.*;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-01
 * @Description:
 */
public class BuffereOutPurStreamTest {
    public static void main(String[] args) {

        String filePath = "D:\\Project\\SomeCodes\\src\\main\\java\\com\\baseJava\\IO\\bigFile.pdf";
        String normalCopyFile = "D:\\Project\\SomeCodes\\src\\main\\java\\com\\baseJava\\IO\\normalCopyBifFile.pdf";
        String BufferedCopyFile = "D:\\Project\\SomeCodes\\src\\main\\java\\com\\baseJava\\IO\\bufferedCopyBifFile.pdf";

        Thread normalCopy = new Thread(new Runnable() {
            @Override
            public void run() {
                normalCopyFunc(filePath,normalCopyFile);
            }
        }, "normalCopy");

        Thread bufferedCopy = new Thread(new Runnable() {
            @Override
            public void run() {
                bufferedCopyFunc(filePath,BufferedCopyFile);
            }
        }, "bufferedCopy");

        normalCopy.start();
        bufferedCopy.start();

    }

    private static void normalCopyFunc(String filePath, String copyFileName) {
        long start = System.currentTimeMillis();

        try (
                FileInputStream fileInputStream = new FileInputStream(filePath);
                FileOutputStream fileOutputStream = new FileOutputStream(copyFileName)
        ){

            int b;
            while ((b = fileInputStream.read())!= -1){
                fileOutputStream.write(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + (end-start));
    }

    private static void bufferedCopyFunc(String filePath, String copyFileName){

        long start = System.currentTimeMillis();

        try(
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(copyFileName))
        ){

            int len;
            byte[] buffer = new byte[8 * 1024];
            while ((len = bufferedInputStream.read(buffer))!= -1){
                bufferedOutputStream.write(buffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

    }
}
