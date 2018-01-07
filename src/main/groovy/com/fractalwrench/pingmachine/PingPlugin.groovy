package com.fractalwrench.pingmachine

import org.gradle.api.Plugin
import org.gradle.api.Project


class PingPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("pingPlugin", PingPluginExtension)

        project.afterEvaluate {
            project.android.applicationVariants.all { variant ->
                variant.outputs.each { output ->
                    playAudioOnBuildCompletion(output)
                }
            }
        }
    }

    private void playAudioOnBuildCompletion(output) {
        output.assemble.doLast {
            InputStream is = getClass().classLoader.getResourceAsStream("audio.wav")

            if (is == null) {
                project.logger.error("Could not find Audio File")
            }
            new AudioPlayer().play(is)
        }
    }
}