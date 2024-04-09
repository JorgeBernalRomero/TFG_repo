package eu.bigan.fed.edelivery.utils;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * 
 * @author Jorge Bernal Romero
 *
 */
public class EnvParameters {


	/**
	 * Fichero de properties del entorno Midas de la aplicacion
	 */
	public static final String FICHERO_CONFIG = "edeliveryEnvironment";

	/**
	 * Cache de ficheros de properties
	 */
	private static Properties parameters = new Properties();

	static {
		try {
			loadParameters(FICHERO_CONFIG);
		} catch (EdeliveryException e) {
			System.out.println("Error leyendo propiedades de entorno. " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Constructor vacio
	 */
	private EnvParameters() {
	}

	/**
	 * Recoge el fichero de properties
	 * 
	 * @param rutaFicheroProperties
	 */
	public static void loadParameters(String parameterFilePath)
			throws EdeliveryException {
		loadParameters(parameterFilePath, null);
	}

	/**
	 * Lee un fichero de propiedades
	 * 
	 * @param ficheroProperties
	 * @param locale
	 */
	public static synchronized void loadParameters(String parameterFilePath,
			Locale locale) throws EdeliveryException {
		PropertyResourceBundle propertyRB = null;

		// Leer el fichero de propiedades
		try {
			if (locale == null) {
				propertyRB = (PropertyResourceBundle) PropertyResourceBundle
						.getBundle(parameterFilePath, Locale.getDefault(), Thread.currentThread().getContextClassLoader());
			} else {
				propertyRB = (PropertyResourceBundle) PropertyResourceBundle
						.getBundle(parameterFilePath, locale, Thread.currentThread().getContextClassLoader());
			}
		} catch (MissingResourceException me) {
			String msg = "Ha sido imposible recuperar el fichero de propiedades de la conexion.";

			throw new EdeliveryException(msg, me);
		}

		// Recuperar propiedades
		Enumeration<String> keys = propertyRB.getKeys();
		String aKey = null;
		parameters.clear();
		
		while (keys.hasMoreElements()) {
			aKey = (String) keys.nextElement();
			parameters.put(aKey, propertyRB.getString(aKey));
		}
	}

	/**
	 * Devuelve la cache de properties.
	 */
	public static Properties getCacheParameters() {
		return (Properties) parameters.clone();
	}

	/**
	 * Este metodo devuelve el valor de configuracion asociado a la key
	 * introducida.
	 * 
	 * @param keyValorConfiguracion
	 * @return
	 */
	public static String getParameter(String parameterKey) {
		return parameters.getProperty(parameterKey);
	}

	/**
	 * Añade un parametro a la lista de parametros del sistema
	 * 
	 * @param codigo
	 * @param valor
	 * @param descripcion
	 */
	public static void addProperty(String code, String value) throws EdeliveryException {
		parameters.put(code, value);
	}

	/**
	 * Actualiza un parametro de la lista de parametros del sistema
	 * 
	 * @param codigo
	 * @param valor
	 */
	public static void updateProperty(String code, String value)
			throws EdeliveryException {
		parameters.setProperty(code, value);
	}

}
