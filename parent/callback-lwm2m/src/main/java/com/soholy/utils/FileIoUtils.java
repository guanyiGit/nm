package com.soholy.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileIoUtils {

    /**
     *   
     * @Description (写入文件，不存在则创建)
     * @param file 文件
     * @param conent 内容
     * @param encoding 编码
     * @param append 是否追加
     * @throws IOException
     */
    public static void writeFile(File file, String conent, String encoding, boolean append) throws IOException {
        OutputStream out = null;
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    throw new IOException("File '" + file + "' exists but is a directory");
                }
                if (file.canWrite() == false) {
                    throw new IOException("File '" + file + "' cannot be written to");
                }
            } else {
                File parent = file.getParentFile();
                if (parent != null && parent.exists() == false) {
                    if (parent.mkdirs() == false) {
                        throw new IOException("File '" + file + "' could not be created");
                    }
                }
            }
            out = new FileOutputStream(file, append);
            IOUtils.write(conent, out, encoding);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

}
