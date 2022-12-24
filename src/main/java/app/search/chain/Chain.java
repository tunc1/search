package app.search.chain;

import java.io.File;

public abstract class Chain
{
    protected Chain next;
    public Chain(Chain next)
    {
        this.next=next;
    }
    public abstract boolean doAction(File file,String text) throws Exception;
}