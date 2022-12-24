package app;

import app.search.chain.ChainExcelFile;
import app.search.chain.ChainTxtFile;
import app.search.Search;

import java.io.File;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String path=args[0];
        String textToSearch=args[1];
        ChainExcelFile chain=new ChainExcelFile(new ChainTxtFile());
        Search search=new Search(path,chain);
        List<File> list=search.startSearch(textToSearch);
        for(File file: list)
            System.out.println(file.getPath());
    }
}