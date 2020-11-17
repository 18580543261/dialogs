package com.sramar.dialogs.defindViews.Dialog;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

public class FileRenameUtil {

    //修改文件名，并转为MD5编码
    public static String renameFile(String oldName){
        String fileNamea = encryptMD5(getFileNameNoEx(oldName) +"-"+ UUID.randomUUID()+new Date().getTime());
        final String renamed = fileNamea+getExtensionName(oldName);
        return renamed;
    }
    public static String encodeNameFile(File file){
        if (file.exists()){
            return renameFile(file.getName());
        }else {
            try {
                throw new FileNotFoundException("FileRenameUtil: renameFile: file不存在");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public static void renameFile(File file,String newName){
        String newPath = "";
        if (file.exists()){
            if (newName.startsWith("/") || newName.startsWith("\\"))
                newPath = newName;
            else
                newPath = file.getParentFile().getAbsolutePath()+"\\"+newName;
            Log.e("momo","FileRenameUtil: renameFile: newPath: "+newPath);
            file.renameTo(new File(newPath));

        }else {
            try {
                throw new FileNotFoundException("FileRenameUtil: renameFile: file不存在");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void renameFile(String filepath,String newName){
        File file = new File(filepath);
        renameFile(file,newName);
    }

     //Java文件操作 获取文件扩展名
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return "."+filename.substring(dot + 1);
            }
        }
        return filename;
    }
     //Java文件操作 获取不带扩展名的文件名
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static String encryptMD5(String input) {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
