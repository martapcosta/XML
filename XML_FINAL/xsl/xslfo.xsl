<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" encoding="utf-8"/>
    <xsl:template match="document">
        <root>
            <layout-master-set>
                <simple-page-master master-name="content"
                    page-width="210mm" page-height="297mm">
                    <region-body/>
                </simple-page-master>
            </layout-master-set>
            <page-sequence master-reference="content">
                <flow flow-name="xsl-region-body">
                    <block font-weight="bold" font-size="72pt">
                        <xsl:value-of select="@title"/>
                    </block>
                    <block font-size="12pt">
                        <xsl:apply-templates />
                    </block>
                </flow>
            </page-sequence>
        </root>
    </xsl:template>
</xsl:stylesheet>
