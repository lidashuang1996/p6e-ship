package club.p6e.ship.netty;

import club.p6e.ship.api.Inlet;
import club.p6e.ship.api.InletPipe;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyInlet implements Inlet {

    private final Bootstrap bootstrap;
    private final EventLoopGroup workerGroup;

    private NettyInlet() {
        this.workerGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap()
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(new NettyCodec());
                        channel.pipeline().addLast(new NettyInletHandler());
                    }
                });
    }

    @Override
    public void connect(InletPipe pipe) {
        this.bootstrap.connect(pipe.getHost(), pipe.getPort());
    }

    @Override
    public void shutdown() {
        this.workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        new NettyInlet().connect(new InletPipe() {
            @Override
            public int getPort() {
                return 9999;
            }

            @Override
            public String getHost() {
                return "127.0.0.1";
            }
        });
    }
}
