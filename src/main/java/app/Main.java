package app;

import app.search.chain.ChainExcelFile;
import app.search.chain.ChainTxtFile;
import app.search.Search;
import app.ui.Presenter;
import app.ui.View;

public class Main
{
    public static void main(String[] args)
    {
        String path=System.getProperty("user.dir");
        ChainExcelFile chain=new ChainExcelFile(new ChainTxtFile());
        new Presenter(new View(),new Search(path,chain)).start();
    }
}