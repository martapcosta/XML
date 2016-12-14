<?xml version="1.0" encoding="UTF-8" ?>
<p:declare-step version="1.0" name="pipeline"
    xmlns:p="http://www.w3.org/ns/xproc"
    xmlns:c="http://www.w3.org/ns/xproc-step"
    xmlns:l="http://xproc.org/library">

    <p:documentation xmlns="http://www.w3.org/1999/xhtml">
        <div>
            <h1>Exercice XProc</h1>
            <p>
                Validation d'un fichier .xml à l'aide de RELAX NG, puis
                transformation en HTML, SVG et XSL-FO.
            </p>
            <p>
                Hélas, la sortie en PDF pose toujours problème. Un ticket a été
                ouvert sur
                <a href="https://github.com/ndw/xmlcalabash1/issues/244">Github</a>.
            </p>
        </div>
    </p:documentation>

    <!-- Point d'entrée -->
    <p:input port="source" primary="true"/>
    <!-- Paramètres généraux -->
    <p:input port="parameters" kind="parameter" primary="true"/>

    <!-- Schema RELAX NG -->
    <p:input port="schema"/>

    <!-- HTML -->
    <p:input port="html.xsl"/>
    <p:output port="html">
        <p:pipe step="html" port="result"/>
    </p:output>

    <p:serialization port="html" method="html"/>

    <!-- SVG -->
    <p:input port="svg.xsl"/>

    <p:output port="svg">
        <p:pipe step="svg" port="result"/>
    </p:output>

    <!-- XSL-FO / PDF -->
    <p:input port="xslfo.xsl"/>
    <p:option name="href"/>
    <!-- La sortie en PDF pose problème...
         https://github.com/ndw/xmlcalabash1/issues/244
	
    <p:output port="result" primary="true">
        <p:pipe step="pdf" port="result"/>
    </p:output>

    <p:output port="result" primary="true"/>
    <p:serialization port="result" method="xml"/>
        else:
-->
    <p:output port="fo">
        <p:pipe step="fo" port="result"/>
    </p:output>
    <!-- endif -->

    <!-- XInclude -->
    <p:xinclude name="inclusion">
        <p:input port="source">
            <p:pipe step="pipeline" port="source"/>
        </p:input>
    </p:xinclude>

    <!-- Validation -->
    <p:validate-with-relax-ng name="validation">
        <p:input port="source">
            <p:pipe step="inclusion" port="result"/>
        </p:input>
        <p:input port="schema">
            <p:pipe step="pipeline" port="schema"/>
        </p:input>
    </p:validate-with-relax-ng>

    <!-- XML to HTML -->

    <p:xslt name="html">
        <p:input port="source">
            <p:pipe step="validation" port="result"/>
        </p:input>
        <p:input port="stylesheet">
            <p:pipe step="pipeline" port="html.xsl"/>
        </p:input>
    </p:xslt>

    <!-- XML to SVG -->
    <p:xslt name="svg">
        <p:input port="source">
            <p:pipe step="validation" port="result"/>
        </p:input>
        <p:input port="stylesheet">
            <p:pipe step="pipeline" port="svg.xsl"/>
        </p:input>
    </p:xslt>

    <!-- XML to XSL-FO -->
    <p:xslt name="fo">
        <p:input port="source">
            <p:pipe step="validation" port="result"/>
        </p:input>
        <p:input port="stylesheet">
            <p:pipe step="pipeline" port="xslfo.xsl"/>
        </p:input>
    </p:xslt>

    <!-- XSL-FO to PDF -->
    <!-- Cassé 
    <p:xsl-formatter name="pdf" content-type="application/pdf">
        <p:with-option name="href" select="$pdf"/>
        <p:input port="source">
            <p:pipe step="fo" port="result"/>
        </p:input>
    </p:xsl-formatter>
   --> 
</p:declare-step>
