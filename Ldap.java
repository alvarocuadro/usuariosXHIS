import java.util.Properties;
import javax.naming.Context;


public class Ldap extends Logueo {	
	
	
	Properties env = new Properties();//mantiene los parámetros de conexión
	
	public void conectarLDAP() {	
		
		String usuario, pw;
				
		usuario = Logueo.userName.toString();
		pw = Logueo.contrasenia.toString();	
		
		
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, "ldap://DCSF001.finochietto.com:389");
	env.put(Context.SECURITY_PRINCIPAL, usuario+"@finochietto.com");
	env.put(Context.SECURITY_CREDENTIALS, pw);
	
	}	

}
	