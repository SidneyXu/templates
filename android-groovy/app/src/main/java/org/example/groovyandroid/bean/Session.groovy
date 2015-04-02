package org.example.groovyandroid.bean

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@CompileStatic
@ToString(includeNames = true)
@EqualsAndHashCode
class Session implements Serializable{
    Long id
    Long speakerId
    Slot slot
    String title
    String summary
    List<String> tags
}