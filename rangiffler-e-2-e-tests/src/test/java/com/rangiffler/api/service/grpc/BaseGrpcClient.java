package com.rangiffler.api.service.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.qameta.allure.grpc.AllureGrpc;

public abstract class BaseGrpcClient {

    private final String serviceUrl;
    private final int servicePort;

    protected final Channel CHANNEL;

    public BaseGrpcClient(String serviceUrl, int servicePort) {
        this.serviceUrl = serviceUrl;
        this.servicePort = servicePort;
        CHANNEL = ManagedChannelBuilder
                .forAddress(this.serviceUrl, this.servicePort)
                .intercept(new AllureGrpc())
                .usePlaintext()
                .build();
    }
}
