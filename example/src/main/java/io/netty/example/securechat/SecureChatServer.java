/*
 * Copyright 2011 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.example.securechat;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.nio.NioServerSocketChannelFactory;
import io.netty.example.telnet.TelnetServer;

/**
 * Simple SSL chat server modified from {@link TelnetServer}.
 */
public class SecureChatServer {

    private final int port;

    public SecureChatServer(int port) {
        this.port = port;
    }

    public void run() {
        // Configure the server.
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool()));

        // Configure the pipeline factory.
        bootstrap.setPipelineFactory(new SecureChatServerPipelineFactory());

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(port));
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8443;
        }
        new SecureChatServer(port).run();
    }
}
