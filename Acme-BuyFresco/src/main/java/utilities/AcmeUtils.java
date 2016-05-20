package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class AcmeUtils {
	/**
	 * Convierte un string a MD5
	 * @param pass string a convertir
	 * @return string convertido a MD5
	 */
	public static String passMD5Encode(String pass){
		String result;
		Md5PasswordEncoder m = new Md5PasswordEncoder();
		
		result = m.encodePassword(pass, null);
		
		return result;
	}
	
	/**
	 * Convierte un String a Date
	 * @param date String
	 * @return fecha Date
	 */
	public static Date convertStringToDate(String date){
		SimpleDateFormat format;
		Date fecha;
		
		format = new SimpleDateFormat("dd/MM/yyyy");
		fecha = null;
		
		try{
			fecha = format.parse(date);
		}catch (ParseException ex){
			System.out.println(ex);
		}
		return fecha;
	}
	
	/** Compara que date1 es menor que date2
	 */
	public static boolean compareDates(Date date1, Date date2){
		int aux;
		Boolean result = false;
		aux = date2.compareTo(date1);
		if(aux == 1){
			result = true;
		}
		return result;
	}
	
	/** Convierte una duracion de double en milisegundos
	 */
	public static long durationToMiliseconds(Double duration){
		
		long miliseconds = 0;
		int milisecond_factor = 3600000;
		int hour = duration.intValue();
		int minutes = 0;
		if(duration.floatValue() == 0.5){
			minutes = 30;
		}
		miliseconds = (hour*milisecond_factor) + (minutes*60000);

		return miliseconds;
	}
}

