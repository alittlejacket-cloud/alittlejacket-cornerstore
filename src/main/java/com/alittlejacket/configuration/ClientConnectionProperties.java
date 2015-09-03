package com.alittlejacket.configuration;

public class ClientConnectionProperties {

    static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
    static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 30000;

    private final int syncClientMaxTotalConnections;
    private final int syncClientMaxConnectionsPerRoute;
    private final int syncClientReadTimeoutMilliseconds;
    private final int asyncClientMaxTotalConnections;
    private final int asyncClientMaxConnectionsPerRoute;
    private final int asyncClientReadTimeoutMilliseconds;

    private ClientConnectionProperties(Builder builder) {
        this.syncClientMaxTotalConnections = builder.syncClientMaxTotalConnections;
        this.syncClientMaxConnectionsPerRoute = builder.syncClientMaxConnectionsPerRoute;
        this.syncClientReadTimeoutMilliseconds = builder.syncClientReadTimeoutMilliseconds;
        this.asyncClientMaxTotalConnections = builder.asyncClientMaxTotalConnections;
        this.asyncClientMaxConnectionsPerRoute = builder.asyncClientMaxConnectionsPerRoute;
        this.asyncClientReadTimeoutMilliseconds = builder.asyncClientReadTimeoutMilliseconds;
    }

    public int getSyncClientMaxTotalConnections() {
        return syncClientMaxTotalConnections;
    }

    public int getSyncClientMaxConnectionsPerRoute() {
        return syncClientMaxConnectionsPerRoute;
    }

    public int getSyncClientReadTimeoutMilliseconds() {
        return syncClientReadTimeoutMilliseconds;
    }

    public int getAsyncClientMaxTotalConnections() {
        return asyncClientMaxTotalConnections;
    }

    public int getAsyncClientMaxConnectionsPerRoute() {
        return asyncClientMaxConnectionsPerRoute;
    }

    public int getAsyncClientReadTimeoutMilliseconds() {
        return asyncClientReadTimeoutMilliseconds;
    }

    public static class Builder {

        private int syncClientMaxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS;
        private int syncClientMaxConnectionsPerRoute = DEFAULT_MAX_CONNECTIONS_PER_ROUTE;
        private int syncClientReadTimeoutMilliseconds = DEFAULT_READ_TIMEOUT_MILLISECONDS;
        private int asyncClientMaxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS;
        private int asyncClientMaxConnectionsPerRoute = DEFAULT_MAX_CONNECTIONS_PER_ROUTE;
        private int asyncClientReadTimeoutMilliseconds = DEFAULT_READ_TIMEOUT_MILLISECONDS;

        public Builder syncClientMaxTotalConnections(Integer syncClientMaxTotalConnections) {
            this.syncClientMaxTotalConnections = toInt(syncClientMaxTotalConnections, DEFAULT_MAX_TOTAL_CONNECTIONS);
            return this;
        }

        public Builder syncClientMaxConnectionsPerRoute(Integer syncClientMaxConnectionsPerRoute) {
            this.syncClientMaxConnectionsPerRoute = toInt(syncClientMaxConnectionsPerRoute,
                    DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
            return this;
        }

        public Builder syncClientReadTimeoutMilliseconds(Integer syncClientReadTimeoutMilliseconds) {
            this.syncClientReadTimeoutMilliseconds = toInt(syncClientReadTimeoutMilliseconds,
                    DEFAULT_READ_TIMEOUT_MILLISECONDS);
            return this;
        }

        public Builder asyncClientMaxTotalConnections(Integer asyncClientMaxTotalConnections) {
            this.asyncClientMaxTotalConnections = toInt(asyncClientMaxTotalConnections, DEFAULT_MAX_TOTAL_CONNECTIONS);
            return this;
        }

        public Builder asyncClientMaxConnectionsPerRoute(Integer asyncClientMaxConnectionsPerRoute) {
            this.asyncClientMaxConnectionsPerRoute = toInt(asyncClientMaxConnectionsPerRoute,
                    DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
            return this;
        }

        public Builder asyncClientReadTimeoutMilliseconds(Integer asyncClientReadTimeoutMilliseconds) {
            this.asyncClientReadTimeoutMilliseconds = toInt(asyncClientReadTimeoutMilliseconds,
                    DEFAULT_READ_TIMEOUT_MILLISECONDS);
            return this;
        }

        private int toInt(Integer value, int defaultValue) {
            if (value == null) {
                return defaultValue;
            }
            return value;
        }

        public ClientConnectionProperties build() {
            return new ClientConnectionProperties(this);
        }
    }
}
