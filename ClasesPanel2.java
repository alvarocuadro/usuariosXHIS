import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class ClasesPanel2 {
	
	
	public String CrearUsuario(String arg1, String arg2) {
		char nombre= arg1.charAt(0);
		StringTokenizer token = new StringTokenizer(arg2);
		String apellido=token.nextToken();
		String usuario = nombre+apellido;
		return  usuario.toLowerCase();
	}
	
	
	public String CrearPass(String arg1, String arg2) {
		String pass="";
		arg1 = arg1.toUpperCase();
		arg2 = arg2.toLowerCase();
		
				
		char nombre= arg1.charAt(0);
		char apellido= arg2.charAt(0);
		
		pass=""+nombre+apellido;
		
		 Calendar fecha = new GregorianCalendar();                                                  
	        int anio = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH);
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        String diaf,mesf,aniof;
	        
	        
	        mes = mes+1;
	        if(mes<10) {
	        	mesf = "0"+Integer.toString(mes); 
	        }else {mesf=Integer.toString(mes);}
	        
	        if(dia<10) {
	        	diaf="0"+Integer.toString(dia);
	        }else {diaf=Integer.toString(dia);}
	        aniof=Integer.toString(anio);
	        aniof = aniof.substring(2);
	        
		
		pass += diaf+mesf+aniof;
		return pass;
		
		
	}
	

}
