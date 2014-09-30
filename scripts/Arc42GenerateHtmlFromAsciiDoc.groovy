import org.asciidoctor.*

target(generateHtml: "compile AsciiDoc to HTML") {

    def asciidoctor = Asciidoctor.Factory.create()
                      
    def output = asciidoctor.renderFile(
                                new File('arc42/index.ad'),
                                [
                                    'in_place':false,
                                    'backend':'html5',
                                    'header_footer':true,
                                    'safe':'SERVER',
                                    'attributes':[
                                        'toc-position':'left',
                                        'toc2':true,
                                        'numbered':true,
                                        'linkcss':true,
                                    ]
                                ]
                            )
    new File('generated/docu.html').write(output,'utf-8')									
    println "finished generating docu at generated/docu.html"									
    output = asciidoctor.renderFile(
                            new File('arc42/index.ad'),
                            [
                                'in_place':false,
                                'backend':'docbook5',
                                'header_footer':true,
                                'safe':'SERVER',
                                'attributes':[
                                    'toc-position':'left',
                                    'toc2':true,
                                    'numbered':true,
                                    'linkcss':true,
                                ]
                            ]
                        )
    new File('generated/docu.xml').write(output,'utf-8')									
}