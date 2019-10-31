 package com.soholy.cb.utils;
 
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import org.apache.commons.io.IOUtils;
 
 
 
 
 
 
 
 
 
 public class FileIoUtils
 {
   public static void writeFile(File file, String conent, String encoding, boolean append)
     throws IOException
   {
     OutputStream out = null;
     try {
       if (file.exists()) {
         if (file.isDirectory()) {
           throw new IOException("File '" + file + "' exists but is a directory");
         }
         if (!file.canWrite()) {
           throw new IOException("File '" + file + "' cannot be written to");
         }
       } else {
         File parent = file.getParentFile();
         if ((parent != null) && (!parent.exists()) && 
           (!parent.mkdirs())) {
           throw new IOException("File '" + file + "' could not be created");
         }
       }
       
       out = new FileOutputStream(file, append);
       IOUtils.write(conent, out, encoding);
     } finally {
       IOUtils.closeQuietly(out);
     }
   }
 }


