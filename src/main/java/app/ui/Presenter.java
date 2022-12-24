package app.ui;

import app.search.Search;

import java.awt.*;
import java.io.File;
import java.util.List;

public class Presenter
{
    private View view;
    private Search search;
    public Presenter(View view,Search search)
    {
        this.view=view;
        this.search=search;
    }
    public void start()
    {
        view.show();
        view.onSearchButtonClicked(this::startSearching);
        view.onListItemDoubleClicked(this::openFile);
    }
    private void openFile()
    {
        try
        {
            File file=new File(view.getSelectedValue());
            Desktop.getDesktop().open(file);
        }
        catch(Exception e)
        {
            handleException(e);
        }
    }
    private void handleException(Exception e)
    {
        view.showMessage(e.getClass().getName()+": "+e.getMessage());
    }
    private void startSearching()
    {
        try
        {
            view.clearList();
            view.setSearchButtonEnabled(false);
            List<File> files=search.startSearch(view.getTextToSearch());
            for(File file: files)
                view.addToList(file.getPath());
            view.setSearchButtonEnabled(true);
            view.showMessage("Done");
        }
        catch(Exception e)
        {
            handleException(e);
        }
    }
}
