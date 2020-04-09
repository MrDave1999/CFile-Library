package config;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class ConfigurationFile
{
	private Properties ppr;
	private final String nameFile;
	
	public ConfigurationFile(String path) throws ConfigFileException
	{
            ppr = new Properties();
            try
            {
		ppr.load(new FileReader(path));
            }
            catch(IOException e)
            {
		throw new ConfigFileException(e.getMessage());
            }
            this.nameFile = new File(path).getName();
	}
        
        public ConfigurationFile(Class<?> ob, String nameFile) throws ConfigFileException
        {
            this(getPathFile(ob, nameFile));
        }
	
	public static String getPathFile(Class<?> ob, String nameFile)
	{
            String path = ob.getProtectionDomain().getCodeSource().getLocation().getPath();
            for(int i = path.length()-1; i != -1; --i)
            {
                if(path.charAt(i) == '/')
                {
                    path = path.substring(0, i + 1);
                    break;
                }
            }
            return path.replaceAll("%20", " ") + nameFile;
	}
	
	public String read(String key) throws ConfigFileException
	{
            String value = ppr.getProperty(key);
            if(value == null)
		throw new ConfigFileException("No se encontró la clave "+ key + " en el archivo " + nameFile);
            return value;
	}
	
	public int readInt(String key) throws ConfigFileException
	{
            int value;
            try
            {
		value = Integer.parseInt(read(key));
            }
            catch(NumberFormatException e)
            {
		throw new ConfigFileException("El valor de la clave "+ key + " debe ser numerico");
            }
            return value;
	}
	
	public float readFloat(String key) throws ConfigFileException
	{
            float value;
            try
            {
		value = Float.parseFloat(read(key));
            }
            catch(NumberFormatException e)
            {
		throw new ConfigFileException("El valor de la clave "+ key + " debe ser un numero decimal");
            }
            return value;
	}
	
	public double readDouble(String key) throws ConfigFileException
	{
            double value;
            try
            {
		value = Double.parseDouble(read(key));
            }
            catch(NumberFormatException e)
            {
		throw new ConfigFileException("El valor de la clave "+ key + " debe ser un numero decimal");
            }
            return value;
	}
}
