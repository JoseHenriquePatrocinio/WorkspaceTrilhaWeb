package br.com.gcontato.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AdicionaContatoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public AdicionaContatoServlet() {
		super();
	}
	
	
	/*
	protected void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{

	
		PrintWriter out = response.getWriter();
		
		out.print("<!doctype html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title> Informações do cliente </title>");
		out.print("<body>");
		out.print("<h1>Informações do cliente</h1>");
		
		out.print("<p>");
		String nome = request.getParameter("nome");
		out.println(nome);
		out.print("</p>");
		
		out.print("<p>");
		String endereco = request.getParameter("endereco");
		out.println(endereco);
		out.print("</p>");
		
		out.print("<p>");
		String telefone = request.getParameter("telefone");
		out.println(telefone);
		out.print("</p>");
		
		out.print("</body>");
		out.print("</html>");

	
	}
	*/
	
	
	 
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	 		
	        PrintWriter out = response.getWriter();
	        
	        out.print("<!doctype html>");
	        out.print("<html>");
	        out.print("<head>");
	        out.print("<title> Informações do cliente </title>");
	        out.print("</head>");
	        out.print("<body>");
	        out.print("<h1>Informações do cliente</h1>");

	        out.print("<p>");
	        String nome = request.getParameter("nome");
	        out.println(nome);
	        out.print("</p>");

	        out.print("<p>");
	        String endereco = request.getParameter("endereco");
	        out.println(endereco);
	        out.print("</p>");

	        out.print("<p>");
	        String telefone = request.getParameter("telefone");
	        out.println(telefone);
	        out.print("</p>");

	        out.print("</body>");
	        out.print("</html>");
	        
	        
	    }

	
				
	    	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    	            throws ServletException, IOException {
	    	        
					/*
	    	        PrintWriter out = response.getWriter();
	    	        
	    	        out.print("<!doctype html>");
	    	        out.print("<html>");
	    	        out.print("<head>");
	    	        out.print("<title> Informações do cliente </title>");
	    	        out.print("</head>");
	    	        out.print("<body>");
	    	        out.print("<h1>Informações do cliente</h1>");

	    	        out.print("<p>");
	    	        String nome = request.getParameter("nome");
	    	        out.println(nome);
	    	        out.print("</p>");

	    	        out.print("<p>");
	    	        String endereco = request.getParameter("endereco");
	    	        out.println(endereco);
	    	        out.print("</p>");

	    	        out.print("<p>");
	    	        String telefone = request.getParameter("telefone");
	    	        out.println(telefone);
	    	        out.print("</p>");

	    	        out.print("</body>");
	    	        out.print("</html>");
	    	        */
	    	    }
	    	    
	    	}
