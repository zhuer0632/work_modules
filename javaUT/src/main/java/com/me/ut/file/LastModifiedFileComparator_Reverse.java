package com.me.ut.file;


import java.io.File;

import org.apache.commons.io.comparator.LastModifiedFileComparator;


/**
 * 
 * 最新的在最前面
 *  
 * @author ZHU
 * 
 */
public class LastModifiedFileComparator_Reverse extends
        org.apache.commons.io.comparator.LastModifiedFileComparator
{

    private static final long serialVersionUID = 1L;


    @Override
    public int compare(File file1,
                       File file2)
    {

        return LastModifiedFileComparator.LASTMODIFIED_REVERSE.compare(file1,
                                                                       file2);
    }

}
