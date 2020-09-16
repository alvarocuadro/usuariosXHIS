import java.util.Properties;
import javax.naming.Context;


public class Ldap extends Logueo {	
	
	
	Properties env = new Properties();//mantiene los parámetros de conexión
	
	public void conectarLDAP() {	
		
		String usuario, pw;
				
		usuario = Logueo.userName.toString();
		pw = Logueo.contrasenia.toString();	
		
		
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, "ldap://Servidor:puerto");
	env.put(Context.SECURITY_PRINCIPAL, usuario+"@dominio.com");
	env.put(Context.SECURITY_CREDENTIALS, pw);
	
	}	

}
	
