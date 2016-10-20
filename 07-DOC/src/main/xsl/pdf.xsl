<?xml version="1.0" encoding="UTF-8"?>

<!--
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:d="http://docbook.org/ns/docbook"
				xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:xslthl="http://xslthl.sf.net"
				exclude-result-prefixes="xslthl d"
				version='1.0'>

	<xsl:import href="http://docbook.sourceforge.net/release/xsl/current/fo/docbook.xsl"/>
	<xsl:import href="http://docbook.sourceforge.net/release/xsl/current/fo/highlight.xsl"/>
	<xsl:import href="common.xsl"/>

	<!-- Extensions -->
	<xsl:param name="fop1.extensions" select="1"/>

	<xsl:param name="paper.type" select="'A4'"/>
	<xsl:param name="page.margin.top" select="'1cm'"/>
	<xsl:param name="region.before.extent" select="'1cm'"/>
	<xsl:param name="body.margin.top" select="'1.5cm'"/>

	<xsl:param name="body.margin.bottom" select="'1.5cm'"/>
	<xsl:param name="region.after.extent" select="'1cm'"/>
	<xsl:param name="page.margin.bottom" select="'1cm'"/>
	<xsl:param name="title.margin.left" select="'0cm'"/>
	
	<!--
	<xsl:param name="body.font.family">Times</xsl:param>-->
</xsl:stylesheet>