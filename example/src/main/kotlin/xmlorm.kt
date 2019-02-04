package xmlorm


import com.fasterxml.jackson.annotation.JsonProperty


data class FindbugsPlugin(
    @set:JsonProperty("pluginid")
    var Pluginid : String ?= null
    ,@set:JsonProperty("provider")
    var Provider : String ?= null
    ,@set:JsonProperty("defaultenabled")
    var Defaultenabled : String ?= null
    ,@set:JsonProperty("website")
    var Website : String ?= null
    ,@set:JsonProperty("noNamespaceSchemaLocation")
    var Nonamespaceschemalocation : String ?= null
    ,@set:JsonProperty("EngineRegistrar")
    var Engineregistrar : EngineRegistrar ?= null
    ,@set:JsonProperty("Detector")
    var Detectors : List<Detector> = ArrayList()
    ,@set:JsonProperty("BugPattern")
    var Bugpatterns : List<BugPattern> = ArrayList()
)


data class EngineRegistrar(
    @set:JsonProperty("class")
    var Class : String ?= null
)


data class Detector(
    @set:JsonProperty("class")
    var Class : String ?= null
    ,@set:JsonProperty("reports")
    var Reports : String ?= null
)


data class BugPattern(
    @set:JsonProperty("type")
    var Type : String ?= null
    ,@set:JsonProperty("abbrev")
    var Abbrev : String ?= null
    ,@set:JsonProperty("category")
    var Category : String ?= null
)

