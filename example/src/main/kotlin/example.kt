package example

import java.io.File

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

import xmlorm.FindbugsPlugin


fun main(args : Array<String>) {

    val xmlMapper = XmlMapper(
            JacksonXmlModule()
                    .apply {setDefaultUseWrapper(false) }
    )
            .registerKotlinModule()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val obj = xmlMapper.readValue<FindbugsPlugin>(File(args[0]))
    println(obj)
}

