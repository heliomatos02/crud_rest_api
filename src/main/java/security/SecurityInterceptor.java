package security;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class SecurityInterceptor implements ContainerRequestFilter {

	private final SecretKey CHAVE =  Keys.hmacShaKeyFor(
			"7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mRffnP2nc!lfGoZ|d?f&RNbDHUX6".getBytes(StandardCharsets.UTF_8));

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		try {
			String token = authorizationHeader.substring("Bearer".length()).trim();

			Jwts.parserBuilder().setSigningKey(CHAVE).build().parseClaimsJws(token);
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token Inválido.").build());
		}

	}

}
