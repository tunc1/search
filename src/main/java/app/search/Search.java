package app.search;

import app.search.chain.Chain;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Search
{
    private final String path;
    private final Chain chain;
    private List<File> foundFiles;
    public Search(String path,Chain chain)
    {
        this.path=path;
        this.chain=chain;
    }
    public List<File> startSearch(String textToSearch) throws Exception
    {
        foundFiles=new LinkedList<>();
        searchInFolder(textToSearch,path);
        return foundFiles;
    }
    private void searchInFolder(String text,String path) throws Exception
    {
        File folder=new File(path);
        if(folder.listFiles()!=null)
        {
            for(File file:folder.listFiles())
            {
                if(file.isDirectory())
                    searchInFolder(text,file.getAbsolutePath());
                else
                {
                    if(chain.doAction(file,text))
                        foundFiles.add(file);
                }
            }
        }
    }
}