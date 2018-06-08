package com.luciad.imageio.webp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NativeLibraryLoader {

	private static String sharedLibraryName = Environment.getJniLibraryName("webp-imageio");
	  private static String tempFilePrefix = "libwebp-imageio";
	  private static String tempFileSuffix = "." + Environment.getJniLibraryExtension();

	  public static void loadLibrary()
	    throws IOException
	  {
	    File localFile=File.createTempFile(tempFilePrefix, tempFileSuffix);;
	    localFile.deleteOnExit();
	    if (!localFile.exists()) {
	      throw new RuntimeException("File " + localFile.getAbsolutePath() + " does not exist.");
	    }
	    byte[] arrayOfByte = new byte[102400];

	    InputStream localInputStream = NativeLibraryLoader.class.getClassLoader().getResourceAsStream(sharedLibraryName);
	    if (localInputStream == null) {
	      throw new RuntimeException(sharedLibraryName + " was not found inside JAR.");
	    }

	    FileOutputStream localFileOutputStream = null;
	    try {
	      localFileOutputStream = new FileOutputStream(localFile);
	      int i;
	      while ((i = localInputStream.read(arrayOfByte)) != -1)
	        localFileOutputStream.write(arrayOfByte, 0, i);
	    }
	    finally {
	      if (localFileOutputStream != null) {
	        localFileOutputStream.close();
	      }
	      if (localInputStream != null) {
	        localInputStream.close();
	      }
	    }
	    System.load(localFile.getAbsolutePath());
	  }
}
