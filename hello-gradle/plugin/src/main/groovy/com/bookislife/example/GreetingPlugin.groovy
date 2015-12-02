package com.bookislife.example

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by SidneyXu on 2015/12/02.
 */
class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        // Add the 'greeting' extension object
        project.extensions.create("greeting", GreetingPluginExtension)

        // Add a task that uses the configuration
        project.task('hello') << {
            println "${project.greeting.message} from ${project.greeting.greeter}"
        }
    }
}

class GreetingPluginExtension {
    String message
    String greeter
}