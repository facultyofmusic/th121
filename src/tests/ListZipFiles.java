package tests;

//-----------------------------------------------------------------------------
//ListZipFiles.java
//-----------------------------------------------------------------------------

/*
 * =============================================================================
 * Copyright (c) 1998-2005 Jeffrey M. Hunter. All rights reserved.
 *
 * All source code and material located at the Internet address of
 * http://www.idevelopment.info is the copyright of Jeffrey M. Hunter, 2005 and
 * is protected under copyright laws of the United States. This source code may
 * not be hosted on any other site without my express, prior, written
 * permission. Application to host any of the material elsewhere can be made by
 * contacting me at jhunter@idevelopment.info.
 *
 * I have made every effort and taken great care in making sure that the source
 * code and other content included on my web site is technically accurate, but I
 * disclaim any and all responsibility for any loss, damage or destruction of
 * data or any other property which may arise from relying on it. I will in no
 * case be liable for any monetary damages arising from such loss, damage or
 * destruction.
 *
 * As with any code, ensure to test this code in a development environment
 * before attempting to run it in production.
 * =============================================================================
 */

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * -----------------------------------------------------------------------------
 * Used to provide an example of listing the contents (files) of a zip file.
 * 
 * @version 1.0
 * @author Jeffrey M. Hunter (jhunter@idevelopment.info)
 * @author http://www.idevelopment.info
 *         ------------------------------------------
 *         -----------------------------------
 */

public class ListZipFiles {

	private static void doListFiles(String zipFileName) {

		try {

			System.out.println("Opening zip file " + zipFileName);
			ZipFile zf = new ZipFile(zipFileName);

			int counter = 0;

			// Enumerate each entry
			for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {

				counter++;

				ZipEntry ze = (ZipEntry) entries.nextElement();
				// Get the entry name
				String zipEntryName = ze.getName();
				System.out.println("Entry " + counter + " : " + zipEntryName);
				if (zipEntryName.equals("applet/applet.html")) {
					InputStream in = zf.getInputStream(ze);
					for (int c = in.read(); c != -1; c = in.read()) {
						System.out.write(c);
					}
					in.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Sole entry point to the class and application.
	 * 
	 * @param args
	 *            Array of String arguments.
	 */
	public static void main(String[] args) throws IOException {

		// doListFiles("C:/ziptest/slick.zip");

		String outputFile = "C:/ziptest/general1.iss";
		// Default to maximum compression
		int level = 0;

		FileOutputStream fout = new FileOutputStream(outputFile);
		ZipOutputStream zout = new ZipOutputStream(fout);
		zout.setLevel(level);
		
		String[] files = {"C:/ziptest/general1.ssic", "C:/ziptest/general1.png"};
		
		for (int i = 0; i < files.length; i++) {
			ZipEntry ze = new ZipEntry(files[i]);
			FileInputStream fin = new FileInputStream(files[i]);
			try {
				System.out.println("Compressing " + files[i]);
				zout.putNextEntry(ze);
				for (int c = fin.read(); c != -1; c = fin.read()) {
					zout.write(c);
				}
			} finally {
				fin.close();
			}
		}
		zout.close();
	}

}
