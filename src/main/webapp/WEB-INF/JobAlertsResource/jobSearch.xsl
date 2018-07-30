<?xml version="1.0"?>
<xsl:stylesheet
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="2">
<xsl:output indent="yes"/>
<xsl:template match="/">
	<jobs>
		<xsl:apply-templates select="/rss/channel/item"/>
	</jobs>
</xsl:template>

<xsl:template match="item">
	<job>
		<title><xsl:value-of select="title"/></title>
		<description><xsl:value-of select="description"/></description>
		<link><xsl:value-of select="link"/></link>
	</job>
</xsl:template>

</xsl:stylesheet>