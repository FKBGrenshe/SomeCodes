package com.baseJava.IO.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-31
 * @Description: 随机访问文件类
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) throws IOException {


        String packagePath = RandomAccessFileDemo.class.getResource("").getPath();
        String filePath = packagePath + "randomAccessFileTest.txt";
        File file = new File(filePath);
        if (!file.exists()){
            boolean newFile = file.createNewFile();
            System.out.println("没有该文件，对该文件进行创建，创建结果" + (newFile ? "成功" : "失败"));
        }


        try {

            System.out.println(filePath);

            // 使用 randomaccessfile写入
            writeToFile(filePath,"Hello, this is congChuan");

            System.out.println("-----------------------");
            // 使用 randomaccessfile 读取文件
            String content = readFromFile(filePath);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeToFile(String filePath, String content) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"rw")){
            // 将文件指针移动到文件莫问，追加内容
            randomAccessFile.seek(randomAccessFile.length());

            // 写入内容
            randomAccessFile.writeUTF(content);
        }
    }

    private static String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"r")){
            // 将文件指针移动到文件开始处
            randomAccessFile.seek(0);

            content.append(randomAccessFile.readUTF());
        }
        return content.toString();
    }

}
