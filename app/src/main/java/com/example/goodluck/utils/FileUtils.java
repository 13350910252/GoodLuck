package com.example.goodluck.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 对文件进行操作
 */
public class FileUtils {
    public static void copyDataBase() {
        //数据库文件夹
        File fileDatabases = new File(Environment.getDataDirectory(), "data/com.example.goodluck/databases");
        if (!fileDatabases.exists()) {
            return;
        }
        File[] filesDb = fileDatabases.listFiles();
        if (filesDb == null) {
            return;
        }
        for (int i = 0; i < filesDb.length; i++) {
            copyFile(filesDb[i], new File(AppUtils.getExternalStorageDirectory(), "GoodLuck/data"));
        }
    }

    private static boolean copyFile(File oldFile, File newFile) {
        try {
            if (!oldFile.exists()) {
                return false;
            } else if (!oldFile.isFile()) {
                return false;
            } else if (!oldFile.canRead()) {
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(oldFile);
            File newFileEnd = new File(newFile.getAbsolutePath(), oldFile.getName());
            if (!newFileEnd.exists()) {
                newFileEnd.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(newFileEnd);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);

            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean copyFile(String oldPath$Name, String newPath$Name) {
        try {
            File oldFile = new File(oldPath$Name);
            if (!oldFile.exists()) {
                return false;
            } else if (!oldFile.isFile()) {
                return false;
            } else if (!oldFile.canRead()) {
                return false;
            }
            Log.d("--Method--", "copyFile: " + oldPath$Name + "--" + newPath$Name);
            FileInputStream fileInputStream = new FileInputStream(oldPath$Name);
            Log.d("--Method--", "copyFile: " + oldPath$Name + "--" + newPath$Name);
            String news = newPath$Name + oldPath$Name.
                    substring(oldPath$Name.lastIndexOf("/"), oldPath$Name.length());
            Log.d("--Method--", "copyFile: " + news);
            File file = new File(news);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
//            Log.d("--Method--", "copyFile: "+fileInputStream.read(buffer));
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);

            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
