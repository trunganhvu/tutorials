package com.example.integration;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.remote.gateway.AbstractRemoteFileOutboundGateway;
import org.springframework.integration.file.remote.session.DelegatingSessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.dsl.Ftp;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
@Profile("gateway")
class GatewayConfiguration {

	@Bean
	RouterFunction<ServerResponse> routes() {
		var in = this.incoming();
		return route()
			.POST("/put/{sfn}", request -> {
				var name = request.pathVariable("sfn");
				var msg = MessageBuilder.withPayload(name).build();
				var sent = in.send(msg);
				return ServerResponse.ok().body(sent);
			})
			.build();
	}

	@Bean
	FtpRemoteFileTemplate ftpRemoteFileTemplate(DelegatingSessionFactory<FTPFile> dsf) {
		var ftpRemoteFileTemplate = new FtpRemoteFileTemplate(dsf);
		ftpRemoteFileTemplate.setRemoteDirectoryExpression(new LiteralExpression(""));
		return ftpRemoteFileTemplate;
	}

	///
	@Bean
	MessageChannel incoming() {
		return MessageChannels.direct().get();
	}

	@Bean
	IntegrationFlow gateway(
		FtpRemoteFileTemplate template,
		DelegatingSessionFactory<FTPFile> dsf) {
		return f -> f
			.channel(incoming())
			.handle((GenericHandler<Object>) (key, messageHeaders) -> {
				dsf.setThreadKey(key);
				return key;
			})
			.handle(Ftp
				.outboundGateway(template, AbstractRemoteFileOutboundGateway.Command.PUT, "payload")
				.fileExistsMode(FileExistsMode.IGNORE)
				.options(AbstractRemoteFileOutboundGateway.Option.RECURSIVE)
			)
			.handle((GenericHandler<Object>) (key, messageHeaders) -> {
				dsf.clearThreadKey();
				return null;
			});
	}

	@Bean
	DelegatingSessionFactory<FTPFile> dsf(Map<String, DefaultFtpSessionFactory> ftpSessionFactories) {
		return new DelegatingSessionFactory<>(ftpSessionFactories::get);
	}

//	@Bean
//	DefaultFtpSessionFactory two(@Value("${ftp2.username}") String username, @Value("${ftp2.password}") String pw, @Value("${ftp2.host}") String host, @Value("${ftp2.port}") int port) {
//		return this.createSessionFactory(username, pw, host, port);
//	}

//	@Bean
//	DefaultFtpSessionFactory one(@Value("${ftp1.username}") String username, @Value("${ftp1.password}") String pw, @Value("${ftp1.host}") String host, @Value("${ftp1.port}") int port) {
//		return this.createSessionFactory(username, pw, host, port);
//	}
//
//	private DefaultFtpSessionFactory createSessionFactory(String username, String pw, String host, int port) {
//		var defaultFtpSessionFactory = new DefaultFtpSessionFactory();
//		defaultFtpSessionFactory.setPassword(pw);
//		defaultFtpSessionFactory.setUsername(username);
//		defaultFtpSessionFactory.setHost(host);
//		defaultFtpSessionFactory.setPort(port);
//		return defaultFtpSessionFactory;
//	}
}
