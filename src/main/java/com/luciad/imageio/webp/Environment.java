package com.luciad.imageio.webp;

public class Environment {
	
	  private static String OS = System.getProperty("os.name").toLowerCase();
	  
	  private static String ARCH = System.getProperty("os.arch").toLowerCase();

	  public static boolean isWindows() {
	    return OS.indexOf("win") >= 0;
	  }

	  public static boolean isUnix() {
	    return (OS.indexOf("nix") >= 0) || (OS.indexOf("nux") >= 0) || (OS.indexOf("aix") >= 0);
	  }

	  public static boolean is64Bit()
	  {
	    return ARCH.indexOf("64") > 0;
	  }

	  public static String getSharedLibraryName(String paramString) {
	    if (isUnix()){
	    	return String.format("lib%s.so", new Object[] { paramString });
	    }
	     throw new UnsupportedOperationException();
	  }

	  public static String getJniLibraryName(String paramString) {
	    if (isUnix()) {
	      String str = is64Bit() ? "64" : "32";
	      return String.format("lib%s-%s.so", new Object[] { paramString,str});
	    }
	    if (isWindows()) {
		      String str = is64Bit() ? "64" : "32";
		      return String.format("lib%s-%s.dll", new Object[] { paramString, str });
		    }
	    throw new UnsupportedOperationException();
	  }

	  public static String getJniLibraryExtension() {
	    return isUnix() ? "so" : "dll";
	  }
}
