package club.p6e.ship.netty;

import club.p6e.ship.api.Outlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyOutlet implements Outlet {

    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final ServerBootstrap serverBootstrap;

    public NettyOutlet() {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(new NettyCodec());
                        channel.pipeline().addLast(new NettyOutletHandler());
                    }
                });
    }

    @Override
    public void bind(int port) {
        this.serverBootstrap.bind(port);
    }

    @Override
    public void shutdown() {
        this.bossGroup.shutdownGracefully();
        this.workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        new NettyOutlet().bind(9999);
    }

}
