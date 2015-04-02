package org.example.groovyandroid.bean

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@CompileStatic
@ToString(includeNames = true)
@EqualsAndHashCode
class Speaker implements Serializable{
    Long id
    String name
    String bio
    String employer
    String image
    String twitter
    List<Session> talks = []
}