package org.example.groovyandroid.bean

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@CompileStatic
@ToString(includeNames = true)
@EqualsAndHashCode
class Status implements Serializable {
    String talks
    String speakers
    String agenda
    String favorites
}