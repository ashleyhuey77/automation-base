
package com.data.loki;

import com.data.config.Service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

        @JsonProperty("name")
        private List<String> names;

        @JsonProperty("phone_number")
        private List<String> phoneNumber;

        @JsonProperty("role_prefix")
        private List<String> rolePrefix;

        @JsonProperty("role_suffix")
        private List<String> roleSuffix;

        @JsonProperty("group_prefix")
        private List<String> groupPrefix;

        @JsonProperty("group_suffix")
        private List<String> groupSuffix;

        @JsonProperty("permission")
        private List<String> permission;

        public String name() {
            Collections.shuffle(names);
            return names.get(0);
        }

        public String phoneNumber() {
            Collections.shuffle(phoneNumber);
            return Service.numerify(phoneNumber.get(0));
        }

        public String role() {
            Collections.shuffle(rolePrefix);
            Collections.shuffle(roleSuffix);
            return rolePrefix.get(0) + " " + roleSuffix.get(0);
        }

        public String groupName() {
            Collections.shuffle(groupPrefix);
            Collections.shuffle(groupSuffix);
            return groupPrefix.get(0) + " " + groupSuffix.get(0);
        }

        public String permission() {
            Collections.shuffle(permission);
            return permission.get(0);
        }

}
