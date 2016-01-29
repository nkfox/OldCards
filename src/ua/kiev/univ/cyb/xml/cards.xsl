<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:math="http://www.w3.org/2005/xpath-functions/math"
    xmlns:myspace="http://aaa.com/Purchase"
    exclude-result-prefixes="xs"
    version="3.0">
    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <tr>
                        <td>id</td>
                        <td>theme</td>
                        <td>type</td>
                        <td>sent</td>
                        <td>country</td>
                        <td>year</td>
                        <td>valuable</td>
                        <td>painter</td>
                        <td>poet</td>
                    </tr>
                    
                    <xsl:for-each select="oldCards/oldCard"> 
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>  
                            </td>
                            <td>
                                <xsl:value-of select="theme"/>
                            </td>
                            <td>
                                <xsl:value-of select="type"/>
                            </td>
                            <td>
                                <xsl:value-of select="sent"/>
                            </td>
                            <td>
                                <xsl:value-of select="country"/>
                            </td>
                            <td>
                                <xsl:value-of select="year"/>
                            </td>
                            <td>
                                <xsl:value-of select="valuable"/>
                            </td>
                            <td>
                                <xsl:value-of select="authors/myspace:painter"/>
                            </td>
                            <td>
                                <xsl:value-of select="authors/myspace:poet"/>
                            </td>
                        </tr>
                        
                    </xsl:for-each>   
                    
                </table>
            </body>
        </html>
        
        
    </xsl:template>   
    
    
</xsl:stylesheet>