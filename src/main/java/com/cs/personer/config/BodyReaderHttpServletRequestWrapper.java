package com.cs.personer.config;

import com.cs.personer.utils.HttpHelper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;


/**
 * @author Jack
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        String bodyString = HttpHelper.getBodyString(request);
        body = bodyString.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }
}
