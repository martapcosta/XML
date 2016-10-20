<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:color="xalan://ch.masrad.xml.xpath.RandomColor"
	xmlns:svg="http://www.w3.org/2000/svg" xmlns:xalan="http://xml.apache.org/xalan"
	xmlns="http://www.w3.org/2000/svg"
	exclude-result-prefixes="fn color xalan">
	<xsl:output method="xml" encoding="utf-8" />
	<xalan:component prefix="color" functions="read">
		<xalan:script lang="javaclass"
			src="xalan://ch.masrad.xml.xpath.RandomColor" />
	</xalan:component>

	<xsl:template match="document">

		<xsl:variable name="color" select="fn:concat('#', color:random())" />
		<svg width="100mm" height="100mm" viewBox="0 0 354.33071 354.33071"
			id="svg2" version="1.1">
			<defs id="defs4">
				<clipPath clipPathUnits="userSpaceOnUse" id="clipPath3490">
					<ellipse
						style="opacity:1;fill:#b3b3b3;fill-opacity:1;stroke:#000000;stroke-width:3;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1"
						id="ellipse3492" cx="184.28571" cy="873.43365" rx="127.85714" ry="126.07143" />
				</clipPath>
			</defs>
			<g id="layer1" transform="translate(0,-698.0315)" style="display:inline">
				<image xlink:href="file:{@image}" transform="translate(0,-26)"
					clip-path="url(#clipPath3490)" style="display:inline" y="697.05402"
					x="-0.97749847" id="image3449" preserveAspectRatio="none" height="356.28571"
					width="356.28571" />
				<ellipse
					style="display:inline;opacity:1;fill:none;fill-opacity:1;stroke:{$color};stroke-width:3;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1"
					id="path3336-3" cx="184.28571" cy="847.79077" rx="127.85714" ry="126.07143" />
				<text xml:space="preserve"
					style="font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:60px;line-height:125%;font-family:Arial;-inkscape-font-specification:Arial;text-align:center;letter-spacing:0px;word-spacing:0px;text-anchor:middle;fill:{$color};fill-opacity:1;stroke:none;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1"
					x="176.25716" y="1029.3623" id="text4294"><tspan><xsl:value-of select="@text" /></tspan></text>
			</g>
		</svg>
	</xsl:template>
</xsl:stylesheet>