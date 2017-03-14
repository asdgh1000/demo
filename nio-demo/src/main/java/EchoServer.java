import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Jo on 3/4/17.
 */
public class EchoServer {
	private final int port;

	public EchoServer(int port){
		this.port = port;
	}

	public void start(){
		EventLoopGroup group = new NioEventLoopGroup();
		{
			try{
				ServerBootstrap b = new ServerBootstrap();
				b.group(group).channel(NioServerSocketChannel.class).localAddress(port)
						.childHandler(new ChannelInitializer<Channel>() {
							protected void initChannel(Channel channel) throws Exception {
								channel.pipeline().addLast(new EchoServerHandler());
							}

						})
			}
		}
	}
}
