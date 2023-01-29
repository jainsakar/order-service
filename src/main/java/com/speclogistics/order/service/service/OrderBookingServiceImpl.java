package com.speclogistics.order.service.service;

import com.speclogistics.order.service.grpcservice.ItemResp;
import com.speclogistics.order.service.grpcservice.OrderBookingDetails;
import com.speclogistics.order.service.grpcservice.OrderBookingResp;
import com.speclogistics.order.service.grpcservice.ReactorOrderBookingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Flux;

@GrpcService
public class OrderBookingServiceImpl extends ReactorOrderBookingServiceGrpc.OrderBookingServiceImplBase {

    @Override
    public Flux<OrderBookingResp> bookOrders(Flux<OrderBookingDetails> request) {
        return request.flatMap(orderDetail -> {
            ItemResp item = ItemResp.newBuilder().setProductId(1).setQuantity(5).setPrice(1010).build();
            OrderBookingResp orderBookingResp = OrderBookingResp.newBuilder()
                                                .setClientId(orderDetail.getClientId())
                                                .setParentId(orderDetail.getParentId())
                                                .setClientType(orderDetail.getClientType())
                                                .setTotalBillingAmount(1010)
                                                .addItemResponses(item).build();
            return Flux.just(orderBookingResp);
        });
    }

}
