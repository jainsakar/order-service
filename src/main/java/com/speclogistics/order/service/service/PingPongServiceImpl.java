package com.speclogistics.order.service.service;

import com.speclogistics.order.service.grpcservice.PingRequest;
import com.speclogistics.order.service.grpcservice.PongResponse;
import com.speclogistics.order.service.grpcservice.ReactorPingPongServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
public class PingPongServiceImpl extends ReactorPingPongServiceGrpc.PingPongServiceImplBase {

    @Override
    public Mono<PongResponse> ping(Mono<PingRequest> request){
        return request.map(req -> PongResponse.newBuilder()
                        .setPong(req.getPing() + " PONG!!!")
                        .build());
    }
}
