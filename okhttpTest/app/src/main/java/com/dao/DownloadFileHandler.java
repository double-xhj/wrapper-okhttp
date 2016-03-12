package com.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by xhj on 2016/2/24.
 */
public abstract  class DownloadFileHandler extends CBHandler<File>{

    protected final File mFile;
    private static final int BUFFER_SIZE = 1024*16;

    public  DownloadFileHandler(File file){
        mFile = file;
    }
    @Override
    File getResponseData(Response response) throws Exception {
        if (null != response) {
            InputStream instream = response.body().byteStream();
            if (null != instream) {
                long contentLength = response.body().contentLength();
                FileOutputStream buffer = new FileOutputStream(mFile);
                int count = 0;
                try {
                    byte[] tmp = new byte[BUFFER_SIZE];
                    int l;
                    // do not send messages if request has been cancelled
                    while ((l = instream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {
                        count += l;
                        buffer.write(tmp, 0, l);
                    }
                } finally {
                    buffer.flush();
                }
                if(contentLength == count) {
                    return this.mFile;
                }
            }
        }
        return null;
    }
}
