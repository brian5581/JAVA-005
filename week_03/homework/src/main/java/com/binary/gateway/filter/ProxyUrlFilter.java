package com.binary.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;


/**
 * @author binary
 * @since 8/23/2021
 */
public class ProxyUrlFilter implements HttpRequestFilter {
    public static ProxyUrlFilter getInstance() {
        return new ProxyUrlFilter();


    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String url = fullRequest.uri();
        System.out.println("The full url is" + url);

        if (url.startsWith("/java")) {
            // pass
        } else {
            throw new RuntimeException("Not support this url");
        }
        HttpHeaders heads = fullRequest.headers();
        if (heads == null) {
            heads = new DefaultHttpHeaders();
        }
        fullRequest.headers().set("tag", "binary");
    }
}
