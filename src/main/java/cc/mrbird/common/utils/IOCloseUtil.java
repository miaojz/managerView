package cc.mrbird.common.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IOCloseUtil 
{

	public static void closeQuietly(Reader input) 
	{
		closeQuietly((Closeable)input);
	}
	
	public static void closeQuietly(Writer output) 
	{
		closeQuietly((Closeable)output);
	}
	 
	public static void closeQuietly(InputStream input) 
	{
		closeQuietly((Closeable)input);
	}
		
	public static void closeQuietly(OutputStream output) 
	{
        closeQuietly((Closeable)output);
    }
	
	public static void closeQuietly(Closeable closeable) 
	{
        try 
        {
            if (closeable != null) 
            {
                closeable.close();
            }
        } 
        catch (IOException ioe) 
        {
            // log ioe
        }
    }
}
