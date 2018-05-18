package com.sys.system.util.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author yudong
 * @Date 2018年05月06日 上午10:22
 */
public class FilterServletOutputStreamUtil extends ServletOutputStream {



    DataOutputStream output;
    public FilterServletOutputStreamUtil(OutputStream output) {
        this.output = new DataOutputStream(output);
    }

    @Override
    public void write(int arg0) throws IOException {
        output.write(arg0);
    }

    @Override
    public void write(byte[] arg0, int arg1, int arg2) throws IOException {
        output.write(arg0, arg1, arg2);
    }

    @Override
    public void write(byte[] arg0) throws IOException {
        output.write(arg0);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
