package app.search.chain;

import java.io.File;
import java.util.Scanner;

public class ChainTxtFile extends Chain
{
    public ChainTxtFile(Chain next)
    {
        super(next);
    }
    public ChainTxtFile()
    {
        this(null);
    }
    public boolean doAction(File file,String text) throws Exception
    {
        if(file.getName().endsWith(".txt"))
        {
            Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine())
            {
                String string=scanner.nextLine();
                if(string.toLowerCase().contains(text.toLowerCase()))
                {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
            return false;
        }
        else if(next!=null)
            return next.doAction(file,text);
        else
            return false;
    }
}