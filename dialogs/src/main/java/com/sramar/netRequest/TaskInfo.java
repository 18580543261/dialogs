package com.sramar.netRequest;

public class TaskInfo {
    private String nameTmp;//文件名
    private String name;//文件名
    private String dir;//文件夹
    private String url;//链接
    private long contentLen;//文件总长度
    /**
     * 迄今为止java虚拟机都是以32位作为原子操作，而long与double为64位，当某线程
     * 将long/double类型变量读到寄存器时需要两次32位的操作，如果在第一次32位操作
     * 时变量值改变，其结果会发生错误，简而言之，long/double是非线程安全的，volatile
     * 关键字修饰的long/double的get/set方法具有原子性。
     */
    private volatile long completedLen;//已完成长度

    private OnProgressListener progressListener;

    public interface OnProgressListener{
        void onProgress(long total, long completed);
    }

    public OnProgressListener getProgressListener() {
        return progressListener;
    }

    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public String getNameTmp() {
        return nameTmp;
    }

    public void setNameTmp(String nameTmp) {
        this.nameTmp = nameTmp;
    }

    public String getDir() {
        return dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathTmp() {
        return dir+"/"+ nameTmp;
    }
    public String getPath() {
        return dir+"/"+ name;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getContentLen() {
        return contentLen;
    }

    public void setContentLen(long contentLen) {
        this.contentLen = contentLen;
    }

    public long getCompletedLen() {
        return completedLen;
    }

    public void setCompletedLen(long completedLen) {
        this.completedLen = completedLen;
    }
}