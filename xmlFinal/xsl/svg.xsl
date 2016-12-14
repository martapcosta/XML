<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
    exclude-result-prefixes="xsl">
    <xsl:output method="xml" encoding="utf-8"/>
    <xsl:template match="document">
        <svg width="100mm" height="100mm">
            <text x="5" y="25" fill="#000000"><xsl:value-of select="@title"/></text>
            <text x="5" y="50" file="#000000"><xsl:apply-templates /></text>
        </svg>
    </xsl:template>
</xsl:stylesheet>
