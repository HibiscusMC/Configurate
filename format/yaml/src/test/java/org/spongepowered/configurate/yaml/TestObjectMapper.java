package org.spongepowered.configurate.yaml;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.ObjectMapper;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.io.File;

public class TestObjectMapper {

    public static void main(final String[] args) throws ConfigurateException {
        File file = new File("C:\\Users\\Kiz\\Documents\\Development\\HMC\\HMCClaims\\run\\plugins\\HMCClaims\\test.yml");
        YamlConfigurationLoader.Builder builder = YamlConfigurationLoader.builder()
                .path(file.toPath())
                .defaultOptions(opts -> opts
                        .shouldCopyDefaults(true)
                        .implicitInitialization(true)
                )
                .indent(2)
                .commentsEnabled(true)
                .nodeStyle(NodeStyle.BLOCK);

        YamlConfigurationLoader loader = builder.build();

        CommentedConfigurationNode node = loader.load();
        ObjectMapper instance = node.get(ObjectMapper.class);

        loader.save(node);

        System.out.println(instance);
        System.out.println(instance.testString());
    }

    @ConfigSerializable
    static class ObjectMapper {

        @Setting("test-string")
        private String testString = "This is a test string";

        public String testString() {
            return testString;
        }

        @Override
        public String toString() {
            return "[ObjectMapper testString=" + testString + "]";
        }
    }

}
