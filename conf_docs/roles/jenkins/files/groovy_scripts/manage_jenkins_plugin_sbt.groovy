#!/usr/bin/env groovy

import jenkins.model.*
import groovy.json.*









/* SCRIPT */

def List<Boolean> has_changed = []

try {
    def Jenkins jenkins_instance = Jenkins.getInstance()
    def desc = jenkins_instance.getDescriptor(
            'package org.jvnet.hudson.plugins.SbtPluginBuilder')

    // Get arguments data
    def Map data = parse_data(args[0])

    // Manage new configuration
    has_changed.push(manage_server(desc, data))
    has_changed.push(manage_card_provider(desc, data))
    has_changed.push(manage_credential_id(desc, data))
    has_changed.push(manage_v2_enabled(desc, data))
    has_changed.push(manage_room(desc, data))
    has_changed.push(manage_send_as(desc, data))

    // Save new configuration to disk
    desc.save()
    jenkins_instance.save()
}
catch(Exception e) {
    throw new RuntimeException(e.getMessage())
}

// Build json result
result = new JsonBuilder()
result {
    changed has_changed.any()
    output {
        changed has_changed
    }
}

println result