package app;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ChainExcelFile extends Chain
{
    private DataFormatter dataFormatter;
    public ChainExcelFile(Chain next)
    {
        super(next);
        dataFormatter=new DataFormatter();
    }
    public boolean doAction(File file,String text) throws Exception
    {
        if(file.getName().endsWith(".xlsx")&&!file.getName().startsWith("~"))
        {
            FileInputStream fileInputStream=new FileInputStream(file);
            Workbook workbook=new XSSFWorkbook(fileInputStream);
            Sheet datatypeSheet=workbook.getSheetAt(0);
            for(Row row:datatypeSheet)
            {
                for(Cell cell: row)
                {
                    String value=dataFormatter.formatCellValue(cell);
                    if(value.toLowerCase().contains(text.toLowerCase()))
                    {
                        workbook.close();
                        return true;
                    }
                }
            }
            workbook.close();
            return false;
        }
        else if(next!=null)
            return next.doAction(file,text);
        else
            return false;
    }
}