package com.me.ut.file;


import com.me.ut.date.DatetimeUT;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileUT
{


    /**
     * 取得项目的根目录
     *
     * @return
     */
    public static String getClassRootPath()
    {
        String path = FileUT.class.getResource("/").getPath();
        return path;
    }


    /**
     * 检查folderPath下是否存在文件名fileName，如果存在则以fileName为基础生成一个不存在的文件名
     * <br>返回值是完整路径
     *
     * @param folderPath
     * @param fileName
     * @return
     */
    public static String getNoExistFileName(String folderPath,
                                            String fileName)
    {
        File f = new File(folderPath + File.separatorChar + fileName);
        if (f.exists())
        {
            List<String> listfileNames = getFileNames(new File(folderPath));
            int j = 1;
            OK:
            while (true)
            {
                fileName = j + fileName;
                boolean oneeq = false;
                for (int i = 0; i < listfileNames.size(); i++)
                {
                    String existName = listfileNames.get(i);
                    if (existName.toLowerCase().equals(fileName.toLowerCase()))
                    {
                        oneeq = true;
                        break;
                    } else
                    {
                        oneeq = false;
                    }
                }
                if (oneeq)
                {
                    continue;
                } else
                {
                    break OK;
                }
            }
        }
        return folderPath + File.separatorChar + fileName;
    }


    /**
     * 取得指定路径下的所有文件的名字
     *
     * @param dir
     * @return
     */
    private static List<String> getFileNames(File dir)
    {
        if (dir.isDirectory())
        {
            List<String> filename = new ArrayList<String>();
            File[] fs = dir.listFiles();
            for (int i = 0; i < fs.length; i++)
            {
                if (fs[i].isFile())
                {
                    filename.add(fs[i].getName());
                }
            }
            return filename;
        }
        throw new RuntimeException("参数不是路径");
    }


    /**
     * 查找指定文件夹下最后修改的一个文件
     *
     * @param foler_path
     * @return
     */
    public static File getLastModifyDateFile(String foler_path)
    {
        File out = null;

        File path_file = new File(foler_path);
        if (!path_file.isDirectory() || !path_file.exists())
        {
            return null;
        }

        List<File> list = new ArrayList<File>();
        File[] files = path_file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                continue;
            }
            list.add(files[i]);
        }

        LastModifiedFileComparator_Reverse compare = new LastModifiedFileComparator_Reverse();

        list = compare.sort(list);

        if (list == null || list.size() == 0)
        {
            return null;
        }
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(DatetimeUT.getDatetimeStamp(new Date(list.get(i)
                    .lastModified()))
                    + "    " + list.get(i).getName());
        }
        out = list.get(0);
        return out;
    }


    /**
     * 将文本内容写入到指定的文件中
     *
     * @param filepath
     */
    public static void writeAll(String filepath,
                                String content,
                                String code
    )
    {
        try
        {
            FileUtils.write(new File(filepath), content, code);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String readAll2String(String filepath, String code)
    {
        String rs = "";
        try
        {
            rs = FileUtils.readFileToString(new File(filepath), code);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return rs;
    }


}
