package practice008.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

public class FileUtils {

    /******************************************************
     * XMLファイルを読み込み、指定されたクラスのオブジェクトに変換する
     * @param path ディレクトリのフルパス
     * @param clazz 変換対象のクラス(ルート)
     * @return 変換後のオブジェクト
     ******************************************************/
    public static <T> List<T> readDirectory(String path, Class<T> clazz) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null){
            return null;
        }
        List<T> returnList = new ArrayList<>();
        for (File file : files) {
            returnList.add(readXmlWithPath(file.getPath(), clazz));
        }
        return returnList;
    }
    /******************************************************
     * XMLファイルを読み込み、指定されたクラスのオブジェクトに変換する
     * @param path ファイルのフルパス
     * @param clazz 変換対象のクラス(ルート)
     * @return 変換後のオブジェクト
     ******************************************************/
    public static <T> T readXmlWithPath(String path,Class<T> clazz) {
        T info = (T) JAXB.unmarshal(new File(path), clazz);
        return info;
    }

}
