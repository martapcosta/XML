<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<!-- <xsl:output method="html" encoding="utf-8" doctype-system="about:legacy-compat" 
		/> -->
	<xsl:template match="/lfm">
		<html>

			<head>
				<title>Top 1000 Last FM Tracks</title>
				<!-- </!><link rel="stylesheet" href="style.css" type="text/css"> -->
			</head>
			<body>
				<h2>Top 1000 Last FM Tracks</h2>
				<table class="rwd-table" border="1">
					<tr bgcolor="#E6E6FA">
						<th>Top</th>
						<th>Track</th>
						<th>Artist name</th>
						<th>Times played</th>
						<th>Number of listeners</th>
						<th>About</th>
					</tr>
					<xsl:for-each select="tracks/track">
						<xsl:sort select="playcount" order="descending"
							data-type="number" />
						<tr>
							<td>
								<count>
									<xsl:value-of select="position()" />
								</count>
							</td>
							<td>
								<xsl:for-each select="url[1]">
									<a target="_blank">
										<xsl:attribute name="href">
											<xsl:value-of select="." />
										</xsl:attribute>
										<xsl:value-of select="../name" />
									</a>
								</xsl:for-each>
							</td>
							<td>
								<xsl:value-of select="artist/name" />
							</td>
							<td>
								<xsl:value-of select="playcount" />
							</td>
							<td>
								<xsl:value-of select="listeners" />
							</td>

							<xsl:for-each select="artist">
								<td>
									<a target="_blank">
										<xsl:attribute name="href">
											<xsl:value-of select="./url" />
										</xsl:attribute>
										<img alt="more about...">
											<xsl:attribute name="src">
											<xsl:value-of select="../image[@size='medium']" />
										</xsl:attribute>
										</img>
									</a>
								</td>
							</xsl:for-each>
						</tr>
					</xsl:for-each>
				</table>
				<h2>Top Artists from the 1000 Last FM Top Tracks</h2>
				<table class="rwd-table" border="1">
					<tr bgcolor="green">
						<th>Top</th>
						<th>Track</th>
						<th>Artist name</th>
						<th>Times played</th>
						<th>Number of listeners</th>
						<th>About</th>
					</tr>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>