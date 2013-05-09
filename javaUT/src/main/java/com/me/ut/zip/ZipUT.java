package com.me.ut.zip;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import com.me.ut.string.StringUT;


public class ZipUT
{

    /**
     * 将指定文件夹下的所有文件(子文件夹，隐藏文件除外)添加到指定zip文件中
     */
    public static void zipFiles_WithoutFolder(String targetfolder,
                                              String zipFilePath)
    {

        check(targetfolder,
              zipFilePath);

        File folder = new File(targetfolder);
        File[] files = folder.listFiles();

        if (noFiles(targetfolder))
        {
            throw new RuntimeException("指定的目标路径没有文件");
        }

        ZipArchiveOutputStream zaos = null;
        try
        {
            File zipFile = new File(zipFilePath);
            zaos = new ZipArchiveOutputStream(zipFile);
            // Use Zip64 extensions for all entries where they are required
            zaos.setUseZip64(Zip64Mode.AsNeeded);

            // 将每个文件用ZipArchiveEntry封装
            // 再用ZipArchiveOutputStream写到压缩文件中
            for (File file : files)
            {
                if (file != null)
                {

                    if (file.isDirectory()
                            || file.isHidden()
                            || file.getCanonicalPath().toLowerCase()
                                    .equals(zipFile.getCanonicalPath()
                                            .toLowerCase()))
                    {
                        continue;
                    }

                    System.out.println("正在压缩：" + file.getCanonicalPath());
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(
                                                                          file,
                                                                              file
                                                                                      .getName());
                    zaos.putArchiveEntry(zipArchiveEntry);
                    IOUtils.copy(new FileInputStream(file),
                                 zaos);
                    zaos.closeArchiveEntry();
                }
            }

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            try
            {
                if (zaos != null)
                {
                    zaos.finish();
                }
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * 检查目标文件夹是否是没有文件[有文件夹不算有文件]
     * 
     * @param targetfolder
     * @return
     */
    private static boolean noFiles(String targetfolder)
    {

        File rootf = new File(targetfolder);
        File[] fs = rootf.listFiles();
        for (int i = 0; i < fs.length; i++)
        {
            File f = fs[i];
            if (f.isFile())
            {
                return false;
            }
        }
        return true;
    }


    /**
     * 
     * 打包targetfolder下所有文件[basePath-作为子文件夹]
     * 
     * @param basePath
     * @param targetfolder
     * @param zipFilePath
     */
    private static void zipFiles(String basePath,
                                 String targetfolder,
                                 String zipFilePath)
    {

        System.out.println("正在压缩：" + targetfolder);

        check(targetfolder,
              zipFilePath);

        File folder = new File(targetfolder);
        File[] files = folder.listFiles();
        try
        {
            // File zipFile = new File(zipFilePath);

            // 将每个文件用ZipArchiveEntry封装
            // 再用ZipArchiveOutputStream写到压缩文件中
            for (File file : files)
            {
                if (file != null)
                {

                    if (file.isDirectory()
                            || file.isHidden()
                            || zipFile.getCanonicalPath().toLowerCase()
                                    .equals(file.getCanonicalPath()
                                            .toLowerCase()))
                    {
                        continue;
                    }

                    String base = "";
                    if (!StringUT.isEmpty(basePath))
                    {
                        base = basePath + File.separatorChar;
                    }

                    System.out.println("正在压缩：" + file.getCanonicalPath());
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(
                                                                          file,
                                                                              base
                                                                                      + file
                                                                                              .getName());
                    zaos.putArchiveEntry(zipArchiveEntry);
                    IOUtils.copy(new FileInputStream(file),
                                 zaos);
                    zaos.closeArchiveEntry();
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }


    /**
     * 
     * 添加一个空文件夹[绝对路径，替换掉最外面的文件夹]
     * 
     * @param targetfolder
     * @param zipFilePath
     * 
     */
    private static void zipEmptyFolder(String targetfolder,
                                       String targetBasefolder,
                                       String zipFilePath)
    {

        try
        {
            File zipFile = new File(zipFilePath);
            zaos = new ZipArchiveOutputStream(zipFile);
            // Use Zip64 extensions for all entries where they are required
            zaos.setUseZip64(Zip64Mode.AsNeeded);

            // 将每个文件用ZipArchiveEntry封装
            // 再用ZipArchiveOutputStream写到压缩文件中
            File file = new File(targetfolder);
            {
                if (file != null)
                {
                    if (file.isFile()
                            || file.isHidden()
                            || file.getCanonicalPath().toLowerCase()
                                    .equals(zipFile.getCanonicalPath()
                                            .toLowerCase()))
                    {
                        return;
                    }
                    System.out.println("正在压缩空文件夹：" + file.getCanonicalPath());
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(
                                                                          file,
                                                                              targetBasefolder);
                    zaos.putArchiveEntry(zipArchiveEntry);
                    // IOUtils.copy(new FileInputStream(file), zaos);
                    zaos.closeArchiveEntry();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    private static String first_targetfolder = "";
    private static ZipArchiveOutputStream zaos = null;
    private static File zipFile = null;
    private static int folder_count = 0;


    /**
     * 
     * 打包zipfolder下的所有文件到zipfilepath中
     * 
     */
    public static void zipFiles_WithFolder(String targetfolder,
                                           String zipFilePath)
    {
        check(targetfolder,
              zipFilePath);
        if (StringUT.isEmpty(first_targetfolder))
        {
            if (isEmptyFolder(new File(targetfolder)))
            {
                throw new RuntimeException("指定的跟目录为空文件夹");
            }

            try
            {
                folder_count = 1;
                zipFile = new File(zipFilePath);
                zaos = new ZipArchiveOutputStream(zipFile);
                // Use Zip64 extensions for all entries where they are required
                zaos.setUseZip64(Zip64Mode.AsNeeded);
                first_targetfolder = (new File(targetfolder))
                        .getCanonicalPath();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            // 必须先遍历所有的空文件夹
            doAllEmpty(first_targetfolder,
                       zipFilePath);

        }

        File folder = new File(targetfolder);
        File[] files = folder.listFiles();

        List<File> files_ = new ArrayList<File>();
        List<File> folders_ = new ArrayList<File>();

        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                folders_.add(files[i]);
            }
            else
            {
                files_.add(files[i]);
            }
        }
        try
        {

            if (isEmptyFolder(new File(targetfolder)))
            {
                // 所有的空文件夹上面已经处理完毕
                return;
            }
            // 压缩当前目录中的文件
            if (!StringUT.isEmpty(files_))
            {
                String base = "";
                if (first_targetfolder.length() < targetfolder.length())
                {
                    base = folder.getCanonicalPath()
                            .replace(first_targetfolder + File.separatorChar,
                                     "");
                }
                zipFiles(base,
                         targetfolder,
                         zipFilePath);
            }

            // 压缩当前目录下的文件夹
            if (!StringUT.isEmpty(folders_))
            {
                for (int i = 0; i < folders_.size(); i++)
                {
                    folder_count++;
                    String subfolderpath = folders_.get(i).getCanonicalPath();
                    zipFiles_WithFolder(subfolderpath,
                                        zipFilePath);
                }
            }
            folder_count--;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            if (folder_count == 0)
            {
                zaos.finish();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 
     * 实现处理所有的空文件夹啊
     * 
     * @param targetfolder
     * @param zipFilePath
     */
    private static void doAllEmpty(String targetfolder,
                                   String zipFilePath)
    {

        try
        {
            File rootfile = new File(targetfolder);
            File[] fs = rootfile.listFiles();
            for (int i = 0; i < fs.length; i++)
            {

                File f = fs[i];
                if (!f.isDirectory())
                {
                    continue;
                }
                if (isEmptyFolder(f))
                {
                    String emptyBasefoleder = f.getCanonicalPath()
                            .replace(first_targetfolder + File.separatorChar,
                                     "");
                    zipEmptyFolder(f.getCanonicalPath(),
                                   emptyBasefoleder,
                                   zipFilePath);
                    return;
                }
                else
                {
                    doAllEmpty(f.getCanonicalPath(),
                               zipFilePath);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    /**
     * 
     * 没有任何文件
     * 
     * @param folder
     * @return
     */
    private static boolean isEmptyFolder(File folder)
    {
        if (!folder.isDirectory())
        {
            throw new RuntimeException("不是文件夹");
        }

        File[] fs = folder.listFiles();
        if (fs == null || fs.length == 0)
        {
            return true;
        }

        for (int i = 0; i < fs.length; i++)
        {
            if (fs[i].isFile())
            {
                return false;
            }
        }
        return false;
    }


    /**
     * 把文件放在前面，需要先处理完所有的文件，再处理文件夹。
     * 
     * @param files
     * @return
     */
    private static File[] order(File[] files)
    {

        List<File> files_ = new ArrayList<File>();
        List<File> folders_ = new ArrayList<File>();

        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isFile())
            {
                files_.add(files[i]);
            }
            else
            {
                folders_.add(files[i]);
            }
        }

        files_.addAll(folders_);
        File[] out = new File[files_.size()];
        return files_.toArray(out);
    }


    private static boolean noFoler(String targetfolder)
    {
        File f = new File(targetfolder);
        if (f.isDirectory() && f.listFiles() != null)
        {
            for (int i = 0; i < f.listFiles().length; i++)
            {
                File temp = f.listFiles()[i];
                if (temp.isDirectory())
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    private static void check(String targetfolder,
                              String zipFilePath)
    {
        File folder = new File(targetfolder);
        if (!folder.exists())
        {
            throw new RuntimeException("指定的文件夹不存在" + targetfolder);
        }

        if (!folder.isDirectory())
        {
            throw new RuntimeException("指定的路径不是文件夹" + targetfolder);
        }
        if (!isZip(zipFilePath))
        {
            throw new RuntimeException("指定的压缩包文件不存在" + zipFilePath);
        }
    }


    private static boolean isZip(String zipFilePath)
    {
        boolean flag = false;
        if (zipFilePath != null && !"".equals(zipFilePath.trim()))
        {
            if (zipFilePath.toLowerCase().endsWith(".zip"))
            {
                flag = true;
            }
        }
        return flag;
    }

}
