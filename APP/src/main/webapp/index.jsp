<%@page import="java.util.ArrayList"%>
<%@page import="com.example.Pala"%>
<%@page import="com.example.SmartyPadel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SmartyPadel</title>

<link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet"> 
 	<link rel = "stylesheet" type = "text/css" href = "index.css" />
</head>
<body background="img/Fondo.jpg">
	
   	
   		<!--------------------------- Cabecera ---------------------------->
  <header>
		<section class="row">
			<h1 class="col-6">SmartyPadel</h1>
      		<section class="col-1 offset-4 logo">
				<img src="img/logopala.jpeg" alt="Logo" />
			</section>
		</section>
	</header>
	<hr/>
	<!------------------------------------------------------------->





	<!------------------- Cuerpo central de la página ---------------->
	<section class="row">
		<section class="col-10 offset-1">
		    <form action="smartypadel" method="get"> 
		   		<section class="col-1 logo">
					<img src="img/logopala.jpeg" alt="Searcher" />
				</section>
		    	<input style="margin-top:10px" type="text" placeholder="Buscar..." id="query" name="query"/>
		   	</form>
	   	</section>
	   	
	
	   	
	   	<section class="col-8 offset-1" style="width:72%;">   <!--  Hueco para artículos  --> 
	   		<section class="row">
	   		
	   		<%
	   			if(request.getParameter("query") == null){
	   				out.print("Introduzca el modelo de pala a comparar");
	   			}else{
	   				ArrayList<Pala> Palas = (ArrayList<Pala>)request.getAttribute("MatchedProducts");
	   				if(Palas.size() != 0){
	   				for(int i = 0; i < Palas.size(); i++){ Pala p = Palas.get(i);%>
		   				<article class="col-4 articulo"> <!-- ARTICULO -->       
		   					<section class="offset-1 col-10">                     
	   							 <img src=<% out.print(p.getImagen()); %> alt="foto-articulo" />
	   						</section>
	   						<h5 class="offset-1 col-10"><% out.print(p.getNombre());
	   													   out.print("<br>");
	   													   out.print(p.getPuntuacion());%></h5>

	   						<% 
	   							if(p.getOfertas()!=null){
	   							for(int j=0; j<p.getOfertas().size(); j++){ 
	   							String enlace="";
	   							enlace+=p.getOfertas().get(j).getEnlace();
						   		//out.print("<h5 class=\"offset-1 col-6\">Precio: " + p.getOfertas().get(j).getMercado() + " " + p.getOfertas().get(j).getPrecio()+" </h5>"+
						   		//		"<a class=\"offset-1 col-1\" target=\"_blank\" href=" + enlace + ">Ver</a>");
						   		out.print("<h5 class=\"offset-1 col-6\">Precio: " + p.getOfertas().get(j).getMercado() + " " + p.getOfertas().get(j).getPrecio()+" </h5>"+
						   		"<a class=\"offset-0 col-4\" target=\"_blank\" href=" + enlace + ">");
						   		if(p.getOfertas().get(j).getMercado()=="Amazon")
						   			out.print("<img src="+"img/Amazon.png"+" alt="+"LogoAmazon"+"widht=120% height=100%/></a>");
						   		if(p.getOfertas().get(j).getMercado()=="Time2Padel")
						   			out.print("<img src="+"img/Time2Padel.png"+" alt="+"T2PLogo"+" widht=120% height=100%/></a>");
						   		if(p.getOfertas().get(j).getMercado()=="PadelSuite")
						   			out.print("<img src="+"img/PadelSuite.png"+" alt="+"PSLogo"+" widht=120% height=100%/></a>");
						   		if(p.getOfertas().get(j).getMercado()=="PadelZoom")
						   			out.print("<img src="+"img/PadelZoom.png"+" alt="+"PZLogo"+" widht=100% height=100%/></a>");
              					 }} %>

						</article> <% 
	   				}
	   				}else{
	   					out.print("La búsqueda no obtuvo resultados");
	   				}
	   			}
	   		%>	   		
	   		</section>
	   	</section>
   	</section>

</body>
</html>