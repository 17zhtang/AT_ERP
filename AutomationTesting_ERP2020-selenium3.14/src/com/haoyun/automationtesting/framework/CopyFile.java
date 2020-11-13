package com.haoyun.automationtesting.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 关于文件的复制
 */
public class CopyFile {
	public static String path ="\\resource\\";// 生成文件路径 
   // public static String time;

    /**
     * 将指定路径下的文件复制到指定文件夹中
     * @throws IOException 
     */
   // @Test
    public void copyFiles(String time) throws IOException{
    	//time = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileCopy=path+"case.xls";
        //被复制文件
        File directory = new File(".");
        File copyFile=new File(directory.getCanonicalPath()+fileCopy);
        System.out.println(copyFile);
        //指定文件夹路径
        String copiedFolderPath=path;
        //指定文件夹

        try {

            //复制后文件的路径与命名
            String copiedFilePath=copiedFolderPath+"case"+time+".xls";
            File copiedFile=new File(directory.getCanonicalPath()+copiedFilePath);
//            if(!copiedFile.exists()){
//                System.out.println("copiedFile not exists"+copiedFolderPath);
//                copiedFile.createNewFile();
//            }
//            System.out.println(copiedFile);
            //复制内容到指定文件中
            copyFileContent(copyFile,copiedFile);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    /**
     * 将文件fromFile 的内容复制到  toFile文件中
     * 复制的可以是Excel等多种格式
     * @param fromFile
     * @param toFile
     * @throws IOException
     */
    public  void copyFileContent(File fromFile, File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n = 0;
        while ((n = ins.read(b)) != -1) {
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }
}