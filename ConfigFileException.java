package config;
/*
    Esta excepción se lanza cuando:
    - El archivo de configuración no se encuentra en la ruta especificada
    - La clave/key no se encuentra en el archivo CONFIG
    - El valor de la clave no cumple con el formato especificado
*/
public class ConfigFileException extends Exception 
{
    public ConfigFileException(String msg)
    {
	super(msg);
    }
}
