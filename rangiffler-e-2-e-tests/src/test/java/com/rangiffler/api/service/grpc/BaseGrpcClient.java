package com.rangiffler.api.service.grpc;

import com.rangiffler.api.listener.grpc.CustomAllureGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

public abstract class BaseGrpcClient {

    private final String serviceUrl;
    private final int servicePort;

    protected final Channel CHANNEL;

    public BaseGrpcClient(String serviceUrl, int servicePort) {
        this.serviceUrl = serviceUrl;
        this.servicePort = servicePort;
        CHANNEL = ManagedChannelBuilder
                .forAddress(this.serviceUrl, this.servicePort)
                .intercept(new CustomAllureGrpc().setRequestTemplate("grpc-request.ftl")
                        .setResponseTemplate("grpc-response.ftl"))
                .usePlaintext()
                .build();
    }
}
