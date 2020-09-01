import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class Buscar {

	String documento;
	String nombre;
	String email;
	String categoria;
	String perfil;
	String servicio;
	String usuario;
	boolean error = false;//
	
	public  Buscar listarUsuarios(String args) {
		
		Ldap resultado = new Ldap();
		Buscar busqueda = new Buscar();
		
		resultado.conectarLDAP();
		
		
	 try {
	        LdapContext ctx = new InitialLdapContext(resultado.env, null);
	        ctx.setRequestControls(null);
	        String dni = args;
	        String searchFilter = "(EmployeeID="+dni+")";//la búsqueda se realiza por el dni (EmployeeID de AD)
	        SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	        NamingEnumeration<?> namingEnum = ctx.search("OU=Accounts, DC=finochietto,DC=com",searchFilter, constraints );
	        if(namingEnum.hasMore() == false) {
	        	System.out.println("no existe el user en Bucar.jar");
	        busqueda.error=true;//retorna el error
	      
	        }
	        else {
	        while (namingEnum.hasMore ()) {
	            SearchResult result = (SearchResult) namingEnum.next (); 
	            Attributes attrs = result.getAttributes ();
	            busqueda.documento=(attrs.get("EmployeeID")).toString();
	            busqueda.nombre=(attrs.get("cn")).toString();
	            busqueda.email=(attrs.get("mail")).toString();
	            busqueda.usuario=(attrs.get("sAMAccountName")).toString();
	            busqueda.categoria=(attrs.get("extensionAttribute5")).toString();
	            busqueda.servicio=(attrs.get("extensionAttribute7")).toString();
	            busqueda.perfil=(attrs.get("extensionAttribute8")).toString();
	            busqueda.error=false;
	            
	        namingEnum.close();}
	 }} catch (Exception e) {
	    	System.out.println("error en el try de Buscar.java " + e);
	        e.printStackTrace();
	    }
	return busqueda;//retorna todos los parámetros
		}
}
