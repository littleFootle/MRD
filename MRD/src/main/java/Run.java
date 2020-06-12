import java.util.LinkedList;
import java.util.Scanner;

public class Run {
    static int read=0;
    public static void main(String[] args) {


    Login();


    }

    public static void Login() { //登入主程序
        System.out.println("输入以下命名以执行功能！");
        System.out.println("Q或q：查找单词");
        System.out.println("P或p：重新解析文本词典");
        System.out.println("E或e：退出程序");
        Scanner scan = new Scanner(System.in);
        String flag = scan.next();
        //查询
        if (flag.equals("Q") || flag.equals("q")) {
            System.out.print("请输入查询单词:");
            if(read==0){
            //将MRD解析并加载到内存
            SearchUtil.letterList = FileUtil.readFromMrd();
            read=1;
            }
            SearchUtil.searchByWords(scan.next());
            Login();
        } else if (flag.equals("P") || flag.equals("p")) {
            System.out.println("重新解析中...");
            //解析并将文本词典转换为机器可读词典保存在文件中
             LinkedList<String> list=FileUtil.readDicFromFile();
             ParseUtil.ParseWords(list);
             FileUtil.writeDicInFile();
            System.out.println("解析完成!");
            Login();
        } else if (flag.equals("E") || flag.equals("e")) {
           System.exit(0);
        }
else {
    Login();
        }
    }
}


