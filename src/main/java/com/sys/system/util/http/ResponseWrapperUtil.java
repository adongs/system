package com.sys.system.util.http;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @Author yudong
 * @Date 2018年05月06日 上午10:19
 */
public class ResponseWrapperUtil extends HttpServletResponseWrapper {

    ByteArrayOutputStream output;
    FilterServletOutputStreamUtil filterOutput;

    public ResponseWrapperUtil(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new FilterServletOutputStreamUtil(output);
        }
        return filterOutput;
    }

    public byte[] getDataStream() {
        return output.toByteArray();
    }

}
