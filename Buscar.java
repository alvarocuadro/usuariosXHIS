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
	            if(attrs.get("extensionAttribute5")!=null) {busqueda.categoria=(attrs.get("extensionAttribute5")).toString();}else {busqueda.categoria="extensionAttribute5:";}
	            if(attrs.get("extensionAttribute7")!=null) {busqueda.servicio=(attrs.get("extensionAttribute7")).toString();}else {busqueda.servicio="extensionAttribute7:";}
	            if(attrs.get("extensionAttribute8")!=null) {busqueda.perfil=(attrs.get("extensionAttribute8")).toString();}else {busqueda.perfil="extensionAttribute8:";}
	            busqueda.error=false;
	            
	        namingEnum.close();}
	 }} catch (Exception e) {
	    	//System.out.println("error en el try de Buscar.java " + e);
	       // e.printStackTrace();
	    }
	return busqueda;//retorna todos los parámetros
		}
	
	public  Buscar buscarUsuario(String args) {
		Ldap resultado = new Ldap();
		Buscar busqueda = new Buscar();
		
		resultado.conectarLDAP();
		
		
	 try {
	        LdapContext ctx = new InitialLdapContext(resultado.env, null);
	        ctx.setRequestControls(null);
	        String user = args;
	        String searchFilter = "(sAMAccountName="+user+")";
	        SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	        NamingEnumeration<?> namingEnum = ctx.search("OU=Accounts, DC=finochietto,DC=com",searchFilter, constraints );
	        if(namingEnum.hasMore() == false) {
	        busqueda.error=false;
	      
	        }
	        else {
            busqueda.error=true;
	        namingEnum.close();
	 }} catch (Exception e) {
	    	//System.out.println("error en el try de Buscar.java " + e);
	       // e.printStackTrace();
	    }
	return busqueda;
		
		
		
	}
		
}
