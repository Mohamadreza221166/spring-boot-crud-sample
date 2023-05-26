package com.example.springcrudsample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@ConfigurationProperties(prefix = "additional", ignoreUnknownFields = false)
public class AdditionalProperties {

    private final Database database = new Database();
    private final Logging logging = new Logging();
    private final Security security = new Security();
    private final CorsConfiguration cors = new CorsConfiguration();

    public Database getDatabase() {
        return database;
    }

    public Logging getLogging() {
        return logging;
    }

    public Security getSecurity() {
        return security;
    }
    public CorsConfiguration getCors() {
        return cors;
    }

    public static class Database {
        private final Couchbase couchbase = new Couchbase();

        public Database() {
        }

        public Couchbase getCouchbase() {
            return this.couchbase;
        }

        public static class Couchbase {
            private String bucketName;
            private String scopeName;

            public Couchbase() {
            }

            public String getBucketName() {
                return this.bucketName;
            }

            public Couchbase setBucketName(String bucketName) {
                this.bucketName = bucketName;
                return this;
            }

            public String getScopeName() {
                return this.scopeName;
            }

            public Couchbase setScopeName(String scopeName) {
                this.scopeName = scopeName;
                return this;
            }
        }
    }

    public static class Logging {
        private boolean useJsonFormat = false;
        private final Logstash logstash = new Logstash();

        public Logging() {
        }

        public boolean isUseJsonFormat() {
            return this.useJsonFormat;
        }

        public void setUseJsonFormat(boolean useJsonFormat) {
            this.useJsonFormat = useJsonFormat;
        }

        public Logstash getLogstash() {
            return this.logstash;
        }

        public static class Logstash {
            private boolean enabled = false;
            private String host = "localhost";
            private int port = 5000;
            private int ringBufferSize = 512;

            public Logstash() {
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return this.host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return this.port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public int getRingBufferSize() {
                return this.ringBufferSize;
            }

            public void setRingBufferSize(int ringBufferSize) {
                this.ringBufferSize = ringBufferSize;
            }
        }
    }

    public static class Security {
        private final Jwt jwt = new Jwt();
        public Security() {
        }

        public Jwt getJwt() {
            return this.jwt;
        }

        public static class Jwt {
            private String base64Secret;
            private long tokenValidityInSeconds;
            private long tokenValidityInSecondsForRememberMe;

            public Jwt() {
//                this.base64Secret = "NmRhMWE5YzY2NTBkZWY0MmNlNTA5MzBiMmU1NjdmZDRmNGU5OGJlYzgxZjZkYTg3ZTg3NDhlMWM2MmVkM2U0NWVhOTAxMTczNGEzOWZhNTJmOTBkNjc2NmNlZmJkZDI5OTA2N2U3ZmFjZDE0MzY3ZGYzODE1MzFmN2MwMGQ0MmI=";
//                this.tokenValidityInSeconds = 86400L; // 24 ساعت
//                this.tokenValidityInSecondsForRememberMe = 2592000L;
            }

            public String getBase64Secret() {
                return this.base64Secret;
            }

            public void setBase64Secret(String base64Secret) {
                this.base64Secret = base64Secret;
            }

            public long getTokenValidityInSeconds() {
                return this.tokenValidityInSeconds;
            }

            public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                this.tokenValidityInSeconds = tokenValidityInSeconds;
            }

            public long getTokenValidityInSecondsForRememberMe() {
                return this.tokenValidityInSecondsForRememberMe;
            }

            public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
            }
        }
    }
}
