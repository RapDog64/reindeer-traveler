package com.rangiffler.tests.api;

import com.rangiffler.api.service.grpc.GeoGrpcClient;
import com.rangiffler.api.service.grpc.PhotoGrpcClient;
import com.rangiffler.jupiter.annotation.GrpcTest;

@GrpcTest
public abstract class BaseGrpcTest {

    protected final GeoGrpcClient countryService = new GeoGrpcClient();
    protected final PhotoGrpcClient photoGrpcClient = new PhotoGrpcClient();
}
