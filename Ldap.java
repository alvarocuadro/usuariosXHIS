import java.util.Properties;

import javax.naming.Context;


public class Ldap {	
		
	Properties env = new Properties();//mantiene los parámetros de conexión
	
	public void conectarLDAP() {	
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, "ldap://DomainController.com:389");
	env.put(Context.SECURITY_PRINCIPAL, "CN del usuario");
	env.put(Context.SECURITY_CREDENTIALS, "Password");}	
}
	
