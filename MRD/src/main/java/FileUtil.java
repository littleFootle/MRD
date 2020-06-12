


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

//操作文件的工具类
public class FileUtil {
    //文本词典的相对路径
    static String txtPath = "DataFiles/牛津大学英语词典-UTF-8.txt";

    //文本词典经过解析后保存的路径
    static String mrdPath = "DataFiles/Dictionary.mrd";


    //得到项目的绝对路径
    public static String getAbsolutePath() {

        return System.getProperty("user.dir");

    }

    //将指定文件读取到内存，为解析做准备
    public static LinkedList<String> readDicFromFile() {

        try {
            BufferedReader in = new BufferedReader(new FileReader(txtPath));
            String str;
            String letter = new String();
            //判断读取的内容是否要记录下来
            int flag = 1;
            //每个
            LinkedList<String> letters = new LinkedList<String>();
            while ((str = in.readLine()) != null) {
                //完成一个字母的读取
                if (flag == 1 && ParseUtil.IsLetterEnd(str)) {
                    letters.add(letter);
                    letter = "";
                    flag = 0;
                }
                if (flag == 1) {
                    letter += '\n' + str;
                }
                if (flag == 0 && ParseUtil.IsLetterStart(str)) {
                    flag = 1;
                }
            }
            System.out.println("字母个数：" + letters.size());
            //System.out.println(letters.get(25));
            in.close();
            return letters;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //将解析好的保存在内存中的信息写入文件中
    public static int writeDicInFile() {
        try {
            FileOutputStream out = new FileOutputStream(mrdPath, false);
            OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
            for (Letter letter : ParseUtil.letterList
            ) {
                writer.append(ParseUtil.lStartFlag);
                writer.append(letter.name);
                writer.append(ParseUtil.crlf);
                for (Word word : letter.words
                ) {
                    writer.append(word.name + ParseUtil.crlf);
                    writer.append(word.phSymbol+ParseUtil.crlf);
                    writer.append(word.meaning + ParseUtil.crlf);
                }
                writer.append(ParseUtil.lEndFlag+ParseUtil.crlf);

            }
            writer.close();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return 1;
    }

    //将mrd读取至内存，以供搜索
    public static ArrayList<Letter> readFromMrd() {
        String name="";
        String symbol="";
        String meaning="";
        char lName='A';
        ArrayList<Letter> letters = new ArrayList<>();
        try {
           BufferedReader in =new BufferedReader(new FileReader(mrdPath));
            String str="";
            //int flag=1;
            while (str!=null) {
                    str=in.readLine();
                    //如果读到字母了
                    if(ParseUtil.IsLetterBegin(str)){
                        ArrayList<Word> words=new ArrayList<>();
                        lName=str.charAt(7);
                        //当没有到字母结束时
                        while(!ParseUtil.IsLetterFinished(str=in.readLine())){
                            name=str;
                            str=in.readLine();
                            symbol=str;
                            str=in.readLine();
                            meaning=str;
                            Word word =new Word(name,symbol,meaning);
                            words.add(word);
                        }
                        //一个字母结束后，完成一个Letter的创建，并把它加入Letters中
                        Letter letter=new Letter(lName,words,words.size());
                        letters.add(letter);
                    }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return letters;
    }


    public static String getTxtPath() {
        return txtPath;
    }

    public static void setTxtPath(String txtPath) {
        FileUtil.txtPath = txtPath;
    }

    public static String getMrdPath() {
        return mrdPath;
    }

    public static void setMrdPath(String mrdPath) {
        FileUtil.mrdPath = mrdPath;
    }


}
