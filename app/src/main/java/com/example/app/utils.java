package com.example.app;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class utils {
    public static void writefile(String fileName,String data){
        try {
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/";
            File root = new File(rootPath);
            if (!root.exists()) {
                root.mkdirs();
            }
            File f = new File(rootPath + fileName);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            FileOutputStream out = new FileOutputStream(f);
            byte[] b = data.getBytes();
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File readfiles(String filename){
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/";
        File file = new File(rootPath+filename);
        return file;
    }
    public static void createfile(String filename){
        File f;
        try {
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/";
            File root = new File(rootPath);
            if (!root.exists()) {
                root.mkdirs();
            }
                f = new File(rootPath + filename);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            FileOutputStream out = new FileOutputStream(f);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
