import java.util.Properties;

import javax.naming.Context;


public class Ldap {	
		
	Properties env = new Properties();//mantiene los parámetros de conexión
	
	public void conectarLDAP() {	
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, "ldap://DCSF001.finochietto.com:389");
	env.put(Context.SECURITY_PRINCIPAL, "CN=Alvaro Cuadro,OU=XHis,OU=980-Ayacucho,OU=Accounts,DC=finochietto,DC=com");
	env.put(Context.SECURITY_CREDENTIALS, "Paraboloide3141");}	
}
	