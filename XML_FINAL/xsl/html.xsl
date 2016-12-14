<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="utf-8" doctype-system="about:legacy-compat"/>
    <xsl:template match="document">
        <html>
            <head>
                <title>
                    <xsl:value-of select="@title"/>
                </title>
                <style type="text/css">
                    body {
                        color: #eee;
                        background: #222;
                    }
                </style>
            </head>
            <body>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
