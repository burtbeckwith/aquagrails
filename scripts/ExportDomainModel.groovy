// Copyright 2014 the arc42-Team and all respective contributors
// Licensed under the Apache License, Version 2.0 (the "License")
// original source https://github.com/arc42/aquagrails

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import grails.util.GrailsNameUtils

//includeTargets << grailsScript("_Init")
includeTargets << grailsScript("_GrailsPackage")
includeTargets << grailsScript("_GrailsBootstrap")
includeTargets << grailsScript("_GrailsCreateArtifacts")

target(exportDomainModel: "export Grails Domain Model as Java-Code") { 
    depends(checkVersion, parseArguments, packageApp,loadApp)
    def convertType =  {def type->
        def types= ['[B':'Byte[]','[C':'Char[]','[I':'Integer']
        def res = type.toString()-"class "-"java.lang."-"java.util."-"java.math."
        res = types[res]?:res
        if (res.startsWith('[L')) {
            res = convertType(res[2..-1])
        }
        return res
    }
    println " starting domain class export ".center(80,"=")
    def domainClasses = grailsApp.domainClasses

    if (!domainClasses) {
        println "No domain classes found in grails-app/domain, trying hibernate mapped classes..."
        bootstrap()
        domainClasses = grailsApp.domainClasses
    }

    grailsApp.domainClasses.each { dc ->
        def code = new StringBuilder()
        def packageName = dc.clazz.name-".${dc.clazz.simpleName}"
        code << "package ${packageName};\n\n"
        code << "public class ${dc.clazz.simpleName} {\n"
        def domainInstance = new DefaultGrailsDomainClass(grailsApp.classLoader.loadClass("${dc.fullName}"))
        domainInstance.getPersistentProperties().each {prop ->
            if (! dc.associationMap[prop.name]) {
                code << "   public ${convertType(prop.type)} ${prop.name};\n"
            }
        }
        dc.associationMap.each {name,type->
            code << "   public ${convertType(type)}[] ${name};\n"
        }
        code << "\n}\n" 
        def path = "documentation/domain/"+packageName.replaceAll(/[.]/,'/')+"/"
        new File(path).mkdirs()
        new File(path+dc.clazz.simpleName+".java").write(code.toString())
    }
}
setDefaultTarget(exportDomainModel)
