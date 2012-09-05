package net.caprazzi.tools.sbatti.io.netty;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.caprazzi.tools.sbatti.io.Capture.Captured;
import net.caprazzi.tools.sbatti.io.Capture.CapturedReceipt;
import net.caprazzi.tools.sbatti.io.netty.client.NettyMessageStoreClientHandler;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.joda.time.Instant;

import com.google.protobuf.MessageLite;

public abstract class SimpleNettyProtobufServerHandler
	<TReceive extends MessageLite, TSend extends MessageLite> 
		extends SimpleChannelUpstreamHandler {

	private static final Logger Log = Logger
			.getLogger(NettyMessageStoreClientHandler.class.getName());
	
	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		if (e instanceof ChannelStateEvent) {
			Log.info(e.toString());
		}
		super.handleUpstream(ctx, e);
	}
	 
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		
		TReceive captured = (TReceive) e.getMessage();
		
		/*
		TSend response = processMessage(e.getMessage());
		
		System.out.println("Received " + captured);
		
		e.getChannel().write(CapturedReceipt.newBuilder()
				.setId(captured.getId())
				.setTimestamp(Instant.now().getMillis())
				.setSender("netty-server")
				.setSuccess(true));
		*/		
	}
	
	protected abstract TSend processMessage(TReceive message);


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		Log.log(
			Level.WARNING, 
			"Unexpected exception from downstream.",
			e.getCause());
		e.getChannel().close();
	}
	
}
