<?xml version="1.0" encoding="UTF-8" ?> 
	<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		<xsl:template match="/">
		<html>
		<head>
		<meta charset="utf-8" /> 
		<title>Атрибут lang</title> 
		<style>
		h2 { 
			font-family: Verdana, Arial, Helvetica, sans-serif; 
			text-align: center; 
		} 
		h1 { 
			font-family: Verdana, Arial, Helvetica, sans-serif; 
			text-align: center;
		} 
		tr { 
			font-family: Verdana, Arial, Helvetica, sans-serif; 
		}
		</style> 
  		</head>
		<body>
  		<h1>Салат</h1> 
		<xsl:if test="salad/vegetable">
  		<h2>Приборы для развлечений</h2> 
			<table border="1" align="center">
			<tr bgcolor="#c1c1c1">
			<th>Название</th> 
			<th>Тип</th> 
			<th>Калории</th> 
			<th>Страна производитель</th> 
			</tr>
			<xsl:for-each select="salad/vegetable">
			<tr>
				<td>
				<xsl:value-of select="name" /> 
				</td>
			
				<td>
  				<xsl:value-of select="type" /> 
				</td>
				
				<td>
  				<xsl:value-of select="calories" />  
  				</td>
  				
  				<td>
  				<xsl:value-of select="country" />  
  				</td>
  			</tr>
  			</xsl:for-each>
  			</table>
  		</xsl:if>
  </body>
  </html>
  </xsl:template>
  </xsl:stylesheet>