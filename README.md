# generate-xml-classes-kotlin

findbugs.xml from https://github.com/find-sec-bugs/find-sec-bugs

Example usage:
```console
python3 gen.py findbugs.xml example/src/main/kotlin/xmlorm.kt
```

In example/ folder:
```console
mvn compile
mvn exec:java -Dexec.args="../findbugs.xml"
```
