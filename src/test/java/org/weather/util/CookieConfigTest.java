package org.weather.util;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CookieConfigTest {
    private static final String USER_SESSION = "CurrentSession";
    private static final int EXPIRATION = 60 * 60 * 2;

    @InjectMocks
    private CookieConfig cookieConfig = new CookieConfig();
    @Mock
    private HttpServletResponse response;

    @Test
    public void addCookie_shouldAddCookieWithCorrectProperties() {
        String token = "5dee8aed-e3f1-4416-95e9-4671b8e902c3";

        cookieConfig.createCookie(response, token);

        verify(response).addCookie(argThat(cookie ->
                USER_SESSION.equals(cookie.getName()) &&
                        token.equals(cookie.getValue()) &&
                        cookie.isHttpOnly() &&
                        cookie.getPath().equals("/") &&
                        cookie.getMaxAge() == EXPIRATION
        ));

    }

    @Test
    public void addCookie_incorrectValue() {
        String token = "5dee8aed-e3f1-4416-95e9-4671b8e902c3";
        cookieConfig.createCookie(response, token);

        verify(response, never()).addCookie(argThat(cookie ->
                "IncorrectToken".equals(cookie.getName())));
    }
}
