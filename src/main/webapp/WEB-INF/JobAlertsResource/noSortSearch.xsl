<?xml version="1.0"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0">
	<xsl:output indent="yes"/>
	<xsl:param name="keyword"/>

	<xsl:template match="/jobs">
		<rss version="2.0">
			<channel>  
				<title>Job Alert</title>
				<link>http://foundit-server/jobalerts</link>     
				<description>This is your customized job alert</description>   

				<xsl:for-each select="job" >
					<xsl:variable name="t" select="title" />
					<xsl:variable name="d" select="description" />
					<xsl:variable name="l" select="link" />
					<xsl:analyze-string select="." regex='{$keyword}+?'>
						<xsl:matching-substring>
							<item>
								<title><xsl:value-of select="$t"/></title>
								<link><xsl:value-of select="$l"/></link>
								<description><xsl:value-of select="$d"/></description>
							</item>
						</xsl:matching-substring>
					</xsl:analyze-string>
				</xsl:for-each>

			</channel>
		</rss>  
	</xsl:template>

</xsl:stylesheet>