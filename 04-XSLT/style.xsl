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
                        background: #f00;
                    }
                </style>
            </head>
            <body>
                <h1>
                    <xsl:value-of select="@title"/>
                </h1>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="page">
        <section>
            <h2><xsl:value-of select="@title"/></h2>
            <xsl:apply-templates />
        </section>
    </xsl:template>

    <xsl:template match="p">
        <p>
            <em><xsl:apply-templates /></em>
        </p>
    </xsl:template>
</xsl:stylesheet>
