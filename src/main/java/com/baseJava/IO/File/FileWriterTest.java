package com.baseJava.IO.File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-01
 * @Description: FileWriteLearn
 */
public class FileWriterTest {

    public static void main(String[] args) {
        File file = new File("D:\\Project\\SomeCodes\\src\\main\\java\\com\\baseJava\\IO\\ChineseTXT.txt");
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file,true);
            String writeStr = "这是我手动填入的文本";
            fileWriter.write(writeStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
