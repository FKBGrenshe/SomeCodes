package com.baseJava.IO.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-01
 * @Description: Filereaderlearn
 */
public class FileReaderTest {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("D:\\Project\\SomeCodes\\src\\main\\java\\com\\baseJava\\IO\\ChineseTXT.txt");

        try(FileReader  fileReader = new FileReader(file)) {
            char[] buffer = new char[32];
            int len;
            while ((len = fileReader.read(buffer,0, buffer.length)) != -1){
                System.out.println(new String(buffer, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
