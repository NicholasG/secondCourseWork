package com.yana.kursova.excel;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ExcelFileFilter extends FileFilter {

    private String ext;
    private String description;

    public ExcelFileFilter() {
        this.ext = "xlsx";
        this.description = "xlsx - Документ Microsoft Excel 2007";
    }

    @Override
    public boolean accept( File f ) {
        if ( f != null ) {
            if ( f.isDirectory() ) {
                return true;
            }
            String extension = getExtension( f );
            if ( extension == null )
                return (ext.length() == 0);
            return ext.equals( extension );
        }
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private String getExtension( File f ) {
        if ( f != null ) {
            String filename = f.getName();
            int i = filename.lastIndexOf( '.' );
            if ( i > 0 && i < filename.length() - 1 ) {
                return filename.substring( i + 1 ).toLowerCase();
            }
        }
        return null;
    }

}

